// [CHANGE-APP-1] Secure Token Validation and Attachment in HTTP Requests
app.config(function ($httpProvider) {
    $httpProvider.interceptors.push(function ($q, $window) {
        return {
            request: function (config) {
                // [CHANGE-APP-2] Retrieve token from localStorage
                var token = $window.localStorage.getItem('token');

                if (token) {
                    try {
                        // [CHANGE-APP-3] Decode the token payload for validation
                        var payload = JSON.parse(atob(token.split('.')[1]));
                        var currentTime = Math.floor(Date.now() / 1000);

                        // [CHANGE-APP-4] Check if the token is expired
                        if (payload.exp && payload.exp < currentTime) {
                            console.warn('Token has expired'); // Log expiration
                            $window.localStorage.removeItem('token'); // Remove expired token
                        } else {
                            // [CHANGE-APP-5] Attach valid Bearer token to headers
                            config.headers.Authorization = 'Bearer ' + token;
                        }
                    } catch (e) {
                        // [CHANGE-APP-6] Handle invalid token format
                        console.error('Invalid token format:', e.message);
                        $window.localStorage.removeItem('token');
                    }
                }

                return config;
            },
            responseError: function (rejection) {
                // [CHANGE-APP-7] Handle Unauthorized (401) errors
                if (rejection.status === 401) {
                    console.warn('Unauthorized access - Token may be invalid or expired');
                    $window.localStorage.removeItem('token'); // Clear invalid token
                }
                return $q.reject(rejection);
            }
        };
    });
});


xxx

angular.module('mainController', ['authServices'])
.controller('mainCtrl', function($scope, Auth, $location, $timeout, $rootScope, $interval, loginFactory, AuthService, $state, $window, AuthToken, Idle, Keepalive, $modal, ModalService, AUTH_EVENTS, AES256) {
    var app = this;

    // [CHANGE-MAIN-1] Token validation during app initialization
    if (Auth.isLoggedIn()) {
        var token = AuthToken.getToken();
        if (!token) {
            Auth.logout();
        } else {
            self.parseJwt = function (token) {
                var base64Url = token.split('.')[1];
                var base64 = base64Url.replace('-', '+').replace('_', '/');
                return JSON.parse($window.atob(base64));
            };

            var expiredTime = self.parseJwt(token);
            var currentTime = Math.floor(Date.now() / 1000);
            if (expiredTime.exp <= currentTime) {
                Auth.logout();
            } else {
                app.user = token.currentUser;
            }
        }
    }

    app.isUserLoggedIn = false;

    // [CHANGE-MAIN-2] Enhanced Login with Token Validation
    app.login = function () {
        app.user.userId = AES256.encrypt(document.getElementById('userIdTemp').value);
        loginFactory.fetchUser(app.user)
            .then(function (data) {
                if (data.isUserExist === '-1') {
                    $scope.notification = 'danger';
                    $scope.message = 'User Does Not Exist';
                    return;
                }

                var token = data.token;
                self.parseJwt = function (token) {
                    var base64Url = token.split('.')[1];
                    var base64 = base64Url.replace('-', '+').replace('_', '/');
                    return JSON.parse($window.atob(base64));
                };

                var decryptedToken = self.parseJwt(token);
                app.user.token = token;
                app.user.userId = decryptedToken.userId;

                // [CHANGE-MAIN-3] Store token securely after login
                AuthService.createJWTToken(app.user, token);
                AuthService.setCredentials();

                // [CHANGE-MAIN-4] Redirect based on user role
                switch (decryptedToken.capacity) {
                    case '52':
                        $state.go('circlechecker_module.module');
                        break;
                    case '51':
                        $state.go('circle_module.module');
                        break;
                    case '61':
                        $state.go('frt_maker.home');
                        break;
                    case '62':
                        $state.go('frt_admin.home');
                        break;
                    case '71':
                        $state.go('super_maker.home');
                        break;
                    case '72':
                        $state.go('super_admin.home');
                        break;
                    default:
                        Auth.logout();
                        $location.path('/login');
                        break;
                }

                $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
            }, function (errResponse) {
                console.error('Login failed');
            });
    };

    // [CHANGE-MAIN-5] Centralized Session Check
    app.checkSession = function () {
        if (Auth.isLoggedIn()) {
            app.checkingSession = true;
            interval = $interval(function () {
                var token = AuthToken.getToken();
                if (!token) {
                    showModal(2);
                    $interval.cancel(interval);
                } else {
                    self.parseJwt = function (token) {
                        var base64Url = token.split('.')[1];
                        var base64 = base64Url.replace('-', '+').replace('_', '/');
                        return JSON.parse($window.atob(base64));
                    };

                    var expiredTime = self.parseJwt(token);
                    var currentTime = Math.floor(Date.now() / 1000);
                    if (expiredTime.exp <= currentTime) {
                        showModal(1);
                        $interval.cancel(interval);
                    }
                }
            }, 5000);
        }
    };
});