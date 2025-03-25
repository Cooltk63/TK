// Function for fetching the List of Circles from DB
        sc10.getCirclesList = function () {
            // alert("Calling the getCirclesList");
            let isCircleExist=false;
            var mapData={
                "CircleCode" : circleCode
            }
            sc10Factory
                .getIFAMSSCircleList(mapData)
                .then(function (data) {

                        console.log("Inside getCirclesList Data output :::"+data);
                        isCircleExist=data;
                    },
                    function (errResponse) {
                        console
                            .error('Error getting saved data in getCircleList');
                    });


            console.log("Final Result :::"+isCircleExist );
            return isCircleExist;
        }


         // Get Data Main Fn with SFTP Method
        sc10.getInitialScreenData = function () {
            

            // Checked if Circle Authorized to Fetch Files/Data
            if (sc10.getCirclesList()) {
                console.log(circleCode + " exists in the list.");
                }

                I am getting issue here that even if I have received response true in isCircleExist true it still printing the false becuase of this ajax call even if response returned the true it set the false and my Final Result ::: log print before my Inside getCirclesList Data output ::: how do i handle this ajax call where i amusing angular js 
