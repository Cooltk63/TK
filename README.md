//'use strict';

/*var App = angular.module('myApp',[]);*/
var app = angular.module('myApp', ['ngAnimate', 'mainController',
    'ngIdle', 'ui.bootstrap', 'ui.router',
    'ngStorage', 'angularModalService', 'blockUI',
    'angular.filter', 'authServices',
    'ngCookies', 'ngPatternRestrict'])
//var app = angular.module('myApp', [ 'ui.router']);
app.constant('USER_ROLES', {
    all: '*',
    circlemaker: '51',
    circleadmin: '52',
    circleauditor: '53',
    frtmaker: '61',
    frtadmin: '62',
    frtauditor: '63',
    supermaker: '71',
    superadmin: '72',
    gitcUser: '81',
    ifrsUser: '91',
    guest: 'guest'
}).constant('AUTH_EVENTS', {
    loginSuccess: 'auth-login-success',
    loginFailed: 'auth-login-failed',
    logoutSuccess: 'auth-logout-success',
    sessionTimeout: 'auth-session-timeout',
    notAuthenticated: 'auth-not-authenticated',
    notAuthorized: 'auth-not-authorized'
});


app.config(function ($stateProvider, $urlRouterProvider,$httpProvider, USER_ROLES) {
    $httpProvider.defaults.cache = false;
    // $httpProvider.interceptors.push('CsrfTokenInterceptorService');
    // $httpProvider.interceptors.push('CsrfTokenInterceptorService');
    if (!$httpProvider.defaults.headers.get) {
        $httpProvider.defaults.headers.get = {};
    }
    $httpProvider.defaults.headers.get['If-Modified-Since'] = '0';
    $urlRouterProvider.otherwise("login");
    $stateProvider
        .state('adminlog', {
            url: "/adminlog",
            templateUrl: "views/admin/adminlog.jsp",
            data: {
                authorizedRoles: [USER_ROLES.all]
            }
        })
        .state('login', {
            url: "/login",
            templateUrl: "views/login.jsp",
            data: {
                authorizedRoles: [USER_ROLES.all]
            }
        })
		
		// This is number of roles we had in i cannot specify there are more than 4K + lines 
        .state('frt_admin', {
            url: "/frt_admin",
            abstract: true,
            templateUrl: "views/frt/admin/frtadmin.jsp",
            data: {
                authorizedRoles: [USER_ROLES.frtadmin]
            }
        })


$urlRouterProvider.otherwise("login");


});


app.config(function ($httpProvider) {
    $httpProvider.interceptors.push([
        '$injector',
        function ($injector) {
            return $injector.get('AuthInterceptor');
        }
    ]);
});


app.config(['KeepaliveProvider', 'IdleProvider', 'TitleProvider', function (KeepaliveProvider, IdleProvider, TitleProvider) {
    TitleProvider.enabled(true);
    IdleProvider.idle(900);    // 5 minutes idle pop up will come
    IdleProvider.timeout(10);   // popup showing time in seconds
    KeepaliveProvider.interval(10);   // 
}]);


app.run(['Idle', function (Idle) {
    Idle.watch();
}]);

app.run(['$rootScope', '$location', '$cookieStore', '$http', 'AUTH_EVENTS', 'Auth', 'AES256', '$state',
    function ($rootScope, $location, $cookieStore, $http, AUTH_EVENTS, Auth, AES256) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};

        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Bearer ' + $rootScope.globals.token;
        }

        $rootScope.isSubmitted = false;

        /*Working Scope Function */
        $rootScope.$on('$locationChangeStart',function (event, next, current) {

            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login', '/register', '/admin.login', '/adm.register', '/admin', '/app', '/dashboard']) === -1;
            $rootScope.globals = $cookieStore.get('globals') || {};
            console.log("$rootScope.globals--"+JSON.stringify($rootScope.globals));
            var loggedIn = $rootScope.globals.currentUser;
            sessionStorage.setItem("IsValid", "Valid");

            /*SESSION MGT START HERE*/
            if (!loggedIn) {
                $location.path('/login');
            }

            if (loggedIn) {

                var decryptedToken = self.parseJwt($rootScope.globals.token);
                console.log("decryptedToken--"+JSON.stringify(decryptedToken));
                var userId =  decryptedToken.userId;

                /////////////////////
                var capacity =  decryptedToken.capacity;
                console.log("capacity--"+capacity);
                console.log("$location.path()--"+$location.path());
                if(capacity == 61 && !$location.path().contains("/frt_maker/")){
                    console.log("into maker condition");
                    Auth.logout();
                    $location.path('/');
                }
                else if(capacity == 62 && !$location.path().contains("/frt_admin/")){
                    console.log("into checker condition");
                    Auth.logout();
                    $location.path('/');
                }
                else if(capacity == 71 && !$location.path().contains("/super_maker/")){
                    Auth.logout();
                    $location.path('/');
                }
                else if(capacity == 51 && !($location.path().contains("/circle_maker/") || $location.path().contains("/circle_Maker_IFRS/")  || $location.path().contains("/circle_module/"))){
                    Auth.logout();
                    $location.path('/');
                }else if(capacity == 52 && !($location.path().contains("/circle_admin/") || $location.path().contains("/Circle_ifrs/")  || $location.path().contains("/checker_module/"))){
                    Auth.logout();
                    $location.path('/');
                }else if(capacity == 53 && !$location.path().contains("/circle_auditor/")){
                    Auth.logout();
                    $location.path('/');
                }




                /////////////////////////

                $http.post("./Security/getsession ",userId)
                    .then(
                        function (response) {

                            /*if(response === " " || response === null || response === {})
                            {
                                alert("This is 1 st Session");
                            }*/
                            /* console.log('User LoggedIn :'+$rootScope.globals.currentUser.userId);*/
                            angular.forEach(response.data, function (value, key) {


                                if (key === "dbUserSessionId") {

                                    if (value !== $rootScope.globals.token) {

                                        sessionStorage.setItem("IsValid", "Invalid");

                                        event.preventDefault();

                                        // ///////
                                        //var userId = "4444444"; //$rootScope.globals.get().currentUser; //app.user.userId;
                                        var decryptedToken = self.parseJwt($rootScope.globals.token);
                                        var userId =  decryptedToken.userId;
                                        ///////////////////////

                                        Auth.resetTimeStamp(userId).then(function(data){
                                            Auth.logout();
                                            $location.path('/');
                                        },function(errResponse){
                                            Auth.logout();
                                            $location.path('/');
                                        });
                                        /*alert("Another Session Exists Logging out from current Session..!!")*/


                                    }
                                }
                            });
                        },
                        function (errResponse) {
                            angular.forEach(errResponse, function (value, key) {
                            });
                        }
                    );
            }
        });




        $rootScope.$on('$stateChangeStart', function (event, next) {
            /////////////// browser and back end token match
            //console.log("############### Inside OLD SESSION CHECK FUNCTION ##############################")


            if (Auth.isLoggedIn()) {
                $rootScope.globals = $cookieStore.get('globals') || {};
                //console.log($rootScope.globals.currentUser);
                var currentUser = JSON.parse(AES256.decrypt($rootScope.globals.currentUser));
                Auth.getUserToken(currentUser).then(function (data) {
                    if (Auth.isLoggedIn()) {
                        /* //console.log('##### get from back end ' + data.data);
                         //console.log('#####  get from back end length ' + data.data.length);
                         //console.log('f#####  ront end ' +currentUser.token);
                         //console.log('#####  front end length ' +currentUser.token.length);*/
                        if (data.data != currentUser.token) {
                            $http.defaults.headers.common['Authorization'] = 'Bearer ';
                            //console.log('token are not equal');
                            Auth.logout();
                            $location.path('/login');
                            // alert('You have been forcefully logged out.');
                        }
                    }
                }, function (errResponse) {
                    //console.log('error getting token');
                    /*app.logout();*/
                })
            }


            ///// prevent privelege escalation
            if (Auth.isLoggedIn()) {
                var authorizedRoles = next.data.authorizedRoles;
                //console.log("### Authorized roles...."+authorizedRoles);
                if (!Auth.isAuthorized(authorizedRoles)) {
                    //console.log("#### prevented....");
                    event.preventDefault();
                    //alert('You are not authorize to access this path');
                }
            }

        });
    }
]);



app.directive('fileModel', ['$parse', function ($parse) {
    return {


        restrict: 'A',
        link: function (scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            element.bind('change', function () {
                scope.$apply(function () {
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);

app.config(['$compileProvider', function ($compileProvider) {
    $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|file|blob|chrome-extension):/);
}]);
