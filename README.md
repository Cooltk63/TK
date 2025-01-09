angular.module('mainController',['authServices'])
.controller('mainCtrl',function($scope,Auth,$location,$timeout,$rootScope,$interval,loginFactory,AuthService,$state,$window,AuthToken,Idle, Keepalive, $modal, ModalService,AUTH_EVENTS, AES256){
	var app =this;
	if(Auth.isLoggedIn()){
		var token = AuthToken.getToken();
		if(token == null || token == undefined){
			Auth.logout();   
		} else{
			self.parseJwt = function(token){
				var base64Url = JSON.stringify(token).split('.')[1];
				var base64 = base64Url.replace('-','+').replace('_','/');
				return JSON.parse($window.atob(base64));
			}
			var expiredTime = self.parseJwt(token);
			var timeStamp = Math.floor(Date.now() / 1000);
			var timeCheck = expiredTime.exp - timeStamp;
			if(timeCheck  <= 0){    //////      to check already exist token expiry time
				Auth.logout();
			}
			else{
				app.user = token.currentUser;
				var capability = expiredTime.capacity;
				if(capability=='52'){
					//$state.go('circle_admin.userDetails',{ id : '2'});
					$state.go('circlechecker_module.module');
				} else if(capability=='51'){
					//$state.go('circle_maker.home');
					$state.go('circle_module.module');
				} else if(capability=='53'){
					$state.go('circle_auditor.home');
				} else if(capability=='62'){
					$state.go('frt_admin.home');
				} else if(capability=='61'){
					$state.go('frt_maker.home');
				} else if(capability=='71'){
					$state.go('super_maker.home');
				} else if(capability=='72'){
					$state.go('super_admin.home');
				}
				else if(capability=='81'){
					$state.go('gitc_user.home');
				}
				else if(capability=='91'){
					$state.go('ifrs_user.home');
				}
			}
			
		}


        }
        app.isUserLoggedIn = false;
        Auth.logout();   // to invalidate old session
        var interval;
        $scope.sessionUser = {};
        if (app.user == undefined)
            app.user = {};
        app.login = function () {
            //app.user.userId = document.getElementById('userIdTemp').value;
            app.user.userId = AES256.encrypt(document.getElementById('userIdTemp').value);
            var ddd = AES256.encrypt(document.getElementById('userIdTemp').value);
            /*console.log('Returned Id'+app.user.userId);
            console.log('Returned Password'+app.user.password);*/
            loginFactory.fetchUser(app.user)
                .then(
                    function (data) {
                        $scope.flag = true;
                        if (data.isUserExist == '-1') {
                            $scope.flag = false;
                            $scope.notification = 'danger';
                            $scope.message = 'User Does Not Exist';
                            //alert('User Does Not Exist');
                            return
                        }

                        if (data.status == 'P') {
                            $scope.flag = false;
                            $scope.notification = 'info';
                            $scope.message = 'User creation request has already been submitted and pending with FRT Checker';
                            return;
                        }


                        var token = data.token;
                        self.parseJwt = function (token) {
                            var base64Url = JSON.stringify(token).split('.')[1];
                            var base64 = base64Url.replace('-', '+').replace('_', '/');
                            return JSON.parse($window.atob(base64));
                        }
                        var decryptedToken = self.parseJwt(token);
                        app.user.token = token;
                        app.user.userId = decryptedToken.userId;
                        app.user.userName = decryptedToken.userName;
                        app.user.circleCode = decryptedToken.circleCode;
                        app.user.circleName = decryptedToken.circleName;
                        app.user.role = decryptedToken.role;
                        app.user.capacity = decryptedToken.capacity;
                        app.user.status = decryptedToken.status;
                        app.user.isUserExist = decryptedToken.isUserExist;
                        app.user.quarterEndDate = decryptedToken.quarterEndDate;
                        app.user.previousQuarterEndDate = decryptedToken.previousQuarterEndDate;
                        app.user.previousYearEndDate = decryptedToken.previousYearEndDate;
                        app.user.financialYear = decryptedToken.financialYear;
                        app.user.quarter = decryptedToken.quarter;
                        app.user.isBranchFinal = decryptedToken.isBranchFinal;
                        app.user.isCircleFreeze = decryptedToken.isCircleFreeze;
                        app.user.isCheckerDig = decryptedToken.isCheckerDig;
        			app.user.frRMId=decryptedToken.frRMId;
                        app.user.isAuditorDig = decryptedToken.isAuditorDig;

                        //app.user=data;
                        if (app.user.status == 'A') {
                            if (app.user.capacity == '52') {
                                Idle.watch();
                                $scope.started = true;
                                app.isUserLoggedIn = true;
                                app.user.role = 'Circle Checker';
                                //window.sessionStorage.setItem("user",JSON.stringify(app.user));
                                //console.log(JSON.parse(window.sessionStorage.getItem("user")));
                                //$scope.sessionUser=JSON.parse(window.sessionStorage.getItem("user"));
                                var user = AES256.encrypt(JSON.stringify(app.user));
                                AuthService.createJWTToken(user, app.user.token);
                                AuthService.setCredentials();
                                $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
                                //app.checkSession();       *************************
                                //$state.go('circle_admin.userDetails', {id: '1'});
								$state.go('circlechecker_module.module');
                            } else if (app.user.capacity == '51') {
                                Idle.watch();
                                $scope.started = true;
                                app.isUserLoggedIn = true;
                                app.user.role = 'Circle Maker';
                                //window.sessionStorage.setItem("user",JSON.stringify(app.user));
                                //console.log(JSON.parse(window.sessionStorage.getItem("user")));
                                //$scope.sessionUser=JSON.parse(window.sessionStorage.getItem("user"));
                                var user = AES256.encrypt(JSON.stringify(app.user));
                                AuthService.createJWTToken(user, app.user.token);
                                AuthService.setCredentials();
                                $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
                                //app.checkSession();         ************************
                                $state.go('circle_module.module');
                                //$state.go('circle_maker.home');
                            } else if (app.user.capacity == '53') {
                                Idle.watch();
                                $scope.started = true;
                                app.isUserLoggedIn = true;
                                app.user.role = 'Circle Approver';
                                //window.sessionStorage.setItem("user",JSON.stringify(app.user));
                                //console.log(JSON.parse(window.sessionStorage.getItem("user")));
                                //$scope.sessionUser=JSON.parse(window.sessionStorage.getItem("user"));
                                var user = AES256.encrypt(JSON.stringify(app.user));
                                AuthService.createJWTToken(user, app.user.token);
                                AuthService.setCredentials();
                                $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
                                //app.checkSession();         ************************
                                $state.go('circle_auditor.home');
                            } else if (app.user.capacity == '61') {
                                Idle.watch();
                                $scope.started = true;
                                app.isUserLoggedIn = true;
                                app.user.role = 'FRT Maker';
                                //window.sessionStorage.setItem("user",JSON.stringify(app.user));
                                //console.log(JSON.parse(window.sessionStorage.getItem("user")));
                                //$scope.sessionUser=JSON.parse(window.sessionStorage.getItem("user"));
                                var user = AES256.encrypt(JSON.stringify(app.user));
                                AuthService.createJWTToken(user, app.user.token);
                                AuthService.setCredentials();
                                $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
                                //app.checkSession();         ************************
                                $state.go('frt_maker.home');
                            } else if (app.user.capacity == '62') {
                                Idle.watch();
                                $scope.started = true;
                                app.isUserLoggedIn = true;
                                app.user.role = 'FRT Checker';
                                //window.sessionStorage.setItem("user",JSON.stringify(app.user));
                                //console.log(JSON.parse(window.sessionStorage.getItem("user")));
                                //$scope.sessionUser=JSON.parse(window.sessionStorage.getItem("user"));
                                var user = AES256.encrypt(JSON.stringify(app.user));
                                AuthService.createJWTToken(user, app.user.token);
                                AuthService.setCredentials();
                                $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
                                //app.checkSession();         ************************
                                $state.go('frt_admin.home');
                            } else if (app.user.capacity == '71') {
                                Idle.watch();
                                $scope.started = true;
                                app.isUserLoggedIn = true;
                                app.user.role = 'Super Maker';
                                //window.sessionStorage.setItem("user",JSON.stringify(app.user));
                                //console.log(JSON.parse(window.sessionStorage.getItem("user")));
                                //$scope.sessionUser=JSON.parse(window.sessionStorage.getItem("user"));
                                var user = AES256.encrypt(JSON.stringify(app.user));
                                AuthService.createJWTToken(user, app.user.token);
                                AuthService.setCredentials();
                                $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
                                //app.checkSession();         ************************
                                $state.go('super_maker.home');
                            } else if (app.user.capacity == '72') {
                                Idle.watch();
                                $scope.started = true;
                                app.isUserLoggedIn = true;
                                app.user.role = 'Super Checker';
                                //window.sessionStorage.setItem("user",JSON.stringify(app.user));
                                //console.log(JSON.parse(window.sessionStorage.getItem("user")));
                                //$scope.sessionUser=JSON.parse(window.sessionStorage.getItem("user"));
                                var user = AES256.encrypt(JSON.stringify(app.user));
                                AuthService.createJWTToken(user, app.user.token);
                                AuthService.setCredentials();
                                $rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
                                //app.checkSession();         ************************
                                $state.go('super_admin.home');
                            }
							else if(app.user.capacity=='81'){
								Idle.watch();
								$scope.started = true;
								app.isUserLoggedIn=true;
								app.user.role = 'gitc User';
								//window.sessionStorage.setItem("user",JSON.stringify(app.user));
								//console.log(JSON.parse(window.sessionStorage.getItem("user")));
								//$scope.sessionUser=JSON.parse(window.sessionStorage.getItem("user"));
								var user = AES256.encrypt(JSON.stringify(app.user));
								AuthService.createJWTToken(user, app.user.token);
								AuthService.setCredentials();
								$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
								//app.checkSession();         ************************
								$state.go('gitc_user.home');
							}
							else if(app.user.capacity=='91'){
								Idle.watch();
								$scope.started = true;
								app.isUserLoggedIn=true;
								app.user.role = 'ifrs User';
								//window.sessionStorage.setItem("user",JSON.stringify(app.user));
								//console.log(JSON.parse(window.sessionStorage.getItem("user")));
								//$scope.sessionUser=JSON.parse(window.sessionStorage.getItem("user"));
								var user = AES256.encrypt(JSON.stringify(app.user));
								AuthService.createJWTToken(user, app.user.token);
								AuthService.setCredentials();
								$rootScope.$broadcast(AUTH_EVENTS.loginSuccess);
								//app.checkSession();         ************************
								$state.go('ifrs_user.home');
							}
                        }
                    },
                    function (errResponse) {
                    }
                );
        }

	 
	app.clearcheckcircle=function(){
		$scope.flag = true;
	}
	app.checkSession = function(){
		if(Auth.isLoggedIn()){
			app.checkingSession =true;
			interval = $interval(function(){
				var token = AuthToken.getToken().token;
				if(token == null || token == undefined){
					showModal(2);
					$interval.cancel(interval);
				} else {
					self.parseJwt = function(token){
						var base64Url = token.split('.')[1];
						var base64 = base64Url.replace('-','+').replace('_','/');
						return JSON.parse($window.atob(base64));
					}
					var expiredTime = self.parseJwt(token);
					var timeStamp = Math.floor(Date.now() / 1000);
					var timeCheck = expiredTime.exp - timeStamp;
					if(timeCheck  <= 0){
						showModal(1);
						$interval.cancel(interval);
					}
				}
			},2000);
		}
		};
		
		//app.checkSession();               **************************
		
			app.redirect= function (){
				$timeout(function(){
					$state.go('circle_maker.worklist');
					 },500);
			    
		    };
		    
		var showModal = function(option){
			app.choiceMade = false;
		app.modalHeader = undefined;
			app.modalBody = undefined;
			app.hideButtons = false;
			if(option === 1)
		{
			app.modalHeader = 'Timeout Warning';
			app.modalBody = 'Your Session will expire in 5 minutes. Would you like to renew your session ? ';
		$('#myModal').modal({backdrop : "static"});
		} else if(option === 2) {
		//	console.log('8');
			app.hideButtons = true ;
		//	console.log('9');
		 app.modalHeader = 'Logging Out' ;
		// console.log('10');
		 $('#myModal').modal({backdrop : "static"});
		// console.log('11');
			 //console.log('12');
			// console.log('check user b4 reseting1 '+app.user.userId);
			 var userId = app.user.userId;
		//	 console.log('check user b4 reseting 2'+userId);
			 Auth.resetTimeStamp(userId).then(function(data){
			//	 console.log(userId);
			//		console.log('resetting time stamp '+data);
					Auth.logout();
					$location.path('/');
				},function(errResponse){
					Auth.logout();
					$location.path('/');
					console.log('error in resetting time stamp ');
				});
				//Auth.logout();
			//	console.log('13');
			 //$location.path('/');
			// console.log('14');
		hideModal();
		} else if(option === 3) {                   ///   logout whole circle users
			app.hideButtons = true ;
		 app.modalHeader = 'Logging Out' ;
		 $('#myModal').modal({backdrop : "static"});
			 var userId = app.user.userId;
			 var circleCode = app.user.circleCode;
			 Auth.logoutCircleUsers(circleCode).then(function(data){
					Auth.logout();
					$location.path('/');
				},function(errResponse){
					Auth.logout();
					$location.path('/');
				});
		hideModal();
		}
		};
		app.refresh = function(){
			var option = confirm('ALL THE SAVED DATA WILL BE LOST. CONTINUE ?')
			if(option == true){
				 //window.location.reload();
				$state.reload();
			} else {
				return false;
			}
		}
		
		app.renewSession = function(){
			app.choiceMade = true;
			loginFactory.reNewSession(app.user)
	           .then(
	           		function(data) {
	    				//$scope.sessionUser=JSON.parse(window.sessionStorage.getItem("user"));
	    				//$scope.sessionUser.token = data;
	    				app.user.token=data;
	    				//Auth.logout();
	    				//AuthToken.removeToken();
	    				AuthService.createJWTToken(app.user, app.user.token);
	                     AuthService.setCredentials();
	    				//AuthToken.setToken(data.token);
	    				app.checkSession();
				},
	           function(errResponse){
	           }
	       );
			
		/*	Auth.renewSession(app.user)
			.then(function(data){
				console.log(' returned data '+data);
				//$scope.sessionUser=JSON.parse(window.sessionStorage.getItem("user"));
				//$scope.sessionUser.token = data;
				app.user.token=data;
				//Auth.logout();
				console.log('user '+app.user);
				console.log('token '+app.user.token);
				console.log('rootscope 1'+JSON.stringify($rootScope.globals));
				AuthToken.removeToken();
				console.log('rootscope 2'+JSON.stringify($rootScope.globals));
				AuthService.createJWTToken(app.user, app.user.token);
                 AuthService.setCredentials();
				//AuthToken.setToken(data.token);
				app.checkSession();
			},function(errResponse){
				console.log(errResponse);
			});*/
			hideModal();
			};
			
		app.endSession = function(){
			$interval.cancel(interval);
			app.choiceMade = true;
			hideModal();
			closeModals();
	        Idle.unwatch();
	        $scope.started = false;
			$timeout(function(){
			showModal(2);
			},2000);
			};

			var hideModal = function(){
				$('#myModal').modal('hide');
			}
			/*$rootScope.$on('$stateChangeStart',function(){
				console.log('stateChange ');
				if(!app.checkingSession){
					app.checkSession();
				}
				if(Auth.isLoggedIn()){
				console.log('User is already Logged in');
				Auth.getUser().then(function(data){
					app.username= data.data.username;
				});
				} else{

				}
			});*/
	this.logout = function(param){
		if(param == 1){                          ///// for branch list finalize logout
			$timeout(function(){
				app.logout();
			},500);	
			return;
		} else if(param == 2){                          ///// in between user sing details update logout
			$timeout(function(){
					app.userId='';
					app.user = {};
					showModal(3);
					$interval.cancel(interval);
					closeModals();
			        Idle.unwatch();
			        $scope.started = false;
			        $location.path('/login');
			},500);	
			return;
		}
		app.userId='';
		app.user = {};
	//	console.log('1');

		//commented by falguni modal dont show up and not need
	// 	showModal(2);
	//	console.log('2');
		$interval.cancel(interval);
	//	console.log('3');
		window.sessionStorage.clear();
        window.localStorage.clear();
        
		closeModals();
	//	console.log('4');
        Idle.unwatch();
      //  console.log('5');
        $scope.started = false;
        $location.path('/login');
       // console.log('6');
        //$location.path('/login');
        //console.log('7');
		/*Auth.logout();
		$interval.cancel(interval);
		$location.path('/logout');
		$timeout(function(){
			$location.path('/');
		},2000);*/
	}
	
	this.downloadDocs = function(){
		loginFactory.downloadDocs()
        .then(
        		function(data) {
        			var file= new Blob([data],{type : 'application/zip'});
        			saveAs(file,"BS_Help_Doc.zip");
        		},function(errReponse){
        			
        		});
	}
	
	function closeModals() { //2
        if ($scope.warning) {
            $scope.warning.close();
            $scope.warning = null;
        }
        if ($scope.timedout) {
            $scope.timedout.setEnabled = false;
            $scope.timedout.close();
            $scope.timedout = null;
        }
    }
    $scope.$on('IdleStart', function () {
        if(app.isUserLoggedIn)
        {	closeModals();
        $scope.warning = $modal.open({ templateUrl: 'warning-dialogSecurity.html', windowClass: 'modal-danger' });
        }
    });
    $scope.$on('IdleEnd', function ()
    { closeModals(); });
    $scope.$on('IdleTimeout', function () {
    	 if(app.isUserLoggedIn){
    		 showModal(2);
    			$interval.cancel(interval);
    		 closeModals();
    	        Idle.unwatch();
    	        $scope.started = false;
    	        $state.go('login');
    	 }
    	/*showModal(2);
		$interval.cancel(interval);
        //$window.sessionStorage.clear();
        //window.localStorage.clear();
        closeModals();
        Idle.unwatch();
        $scope.started = false;
        $location.path('/login');
        logoutFactory
        .logoutUser()
         .then(function (data) {
             closeModals();
             Idle.unwatch();
             $scope.started = false;
             $location.path('/login');
         })
        closeModals();*/
    });
    $scope.start = function () {
        closeModals();
        Idle.watch();
        $scope.started = true;
    }; $scope.stop = function () {
        closeModals();
        Idle.unwatch();
        $scope.started = false;
    };
});
