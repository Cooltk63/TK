 if ($scope.sc09Form.$dirty == true && sc09.isCircleInList(circleCode))
                    {
                        alert("You cant Make changes here");
                        console.log("Cant Make Chnage HERERERERERERERERER")
                        
                        sc09.redirect();
                    }

sc09.redirect = function () {
                $timeout(function () {

                    if ($scope.sessionUser.capacity == "61") {
                        $state.go('frt_maker.worklist');
                    } else {
                        // event.preventDefault();
                        $scope.preventDefault();
                        $state.go('circle_maker.worklist');
                    }
                }, 500);
            }


            on save button it check if ($scope.sc09Form.$dirty == true && sc09.isCircleInList(circleCode)) if this true thrn it execute the sc09.redirect function but it still stucked and save function executed and user will redirected to worklist location with not having changes or clickable options there means totally stuck need to reload to re-login the application
