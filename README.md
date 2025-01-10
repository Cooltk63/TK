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
							// AppSec Change Here-4
								//START
							else{
								Auth.logout();
								$location.path('/login');
							}
							//END
                        }


                        make this aboce code more faster and efficent way without changing the existing logic and functionality 
                        
