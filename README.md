TypeError: Cannot read properties of undefined (reading 'general')
    at setValues (annex2CFRTReportController.js:205:107)
    at annex2CFRTReportController.js:97:21
    at processQueue (angular.js:18075:37)
    at angular.js:18123:27
    at Scope.$digest (angular.js:19242:15)
    at Scope.$apply (angular.js:19630:24)
    at done (angular.js:13473:47)
    at completeRequest (angular.js:13730:7)
    at XMLHttpRequest.requestLoaded (angular.js:13635:9) 'Possibly unhandled rejection: {}'


    Why I am getting this issue..? this error Iam getting whenever i have load the screen for 1st time

    My Angular JS Controller.js file as per below


    app.controller('annex2CFRTReportController', function ($scope, $rootScope, $filter, $http, $sce, $timeout, AES256,
                                                       $window, $sessionStorage, $state, $location,
                                                       annex2CFRTFactory, Idle, Keepalive, $modal, ModalService, userFactory) {

    var annex2CFRT = this;
    $scope.started = false;
    $scope.sessionUser = JSON.parse(AES256.decrypt($rootScope.globals.currentUser));


    let quarterEndDate = $scope.sessionUser.quarterEndDate;
    annex2CFRT.listOfData = {};
    annex2CFRT.row = {};

    /*annex2CFRT.row.input1="0.00"
    annex2CFRT.row.input2="0.00"
    annex2CFRT.row.input3="0.00"

    console.log("Value for annex2CFRT ROW Is: "+annex2CFRT.row.toString());
    console.log("Value for annex2CFRT ROW 1 Is: "+annex2CFRT.row.input1);
    console.log("Value for annex2CFRT ROW 2 Is: "+annex2CFRT.row.input2);
    console.log("Value for annex2CFRT ROW 3 Is: "+annex2CFRT.row.input3);*/


    // annex2CFRT.listOfDataYSA = {};
    // annex2CFRT.listOfDataIBG = {};
    // annex2CFRT.domAdvFCSum12 = {};
    //annex2CFRT.row.stdAssetSum1 = {};

    // annex2CFRT.allData={};
    // console.log("value for row : " +annex2CFRT.row.stdAssetSum1);

    let circleCode = $scope.sessionUser.circleCode;
    let previousYearEndDate = $scope.sessionUser.previousYearEndDate;

    var previousyear = previousYearEndDate.substring(6, 10);

    var year = quarterEndDate.substring(6, 10);
    var month = quarterEndDate.substring(3, 5);
    var date = quarterEndDate.substring(0, 2);

    annex2CFRT.quarterEndDate = quarterEndDate;
    annex2CFRT.circleCode = circleCode;
    annex2CFRT.year = year;
    annex2CFRT.month = month;
    annex2CFRT.previousYearEndDate = previousYearEndDate;
    annex2CFRT.previousyear = previousyear;

    ////alert("quarterEndDate is:" + quarterEndDate);


    console.log("quarterEndDate " + quarterEndDate, circleCode, year, month, date);
    // angular.element(document). ready()

    // Parse Float for Fn for Table Values
    annex2CFRT.parseFloat = function (value) {
        if (isNaN(value)) {
            value = 0.00;
        }
        return parseFloat(value * 1);
    }


    // Getting Screen Data from DB
    annex2CFRT.getAnnex2cList = function () {
        var params = {
            'user': $scope.sessionUser,
            'circleCode': $scope.sessionUser.circleCode,
            'qed': $scope.sessionUser.quarterEndDate,
            'reportID': '5005',
        }

        annex2CFRTFactory.getAnnex2cList(params).then(function (data) {
                if (data != null || data != 0)

                    annex2CFRT.datasize = data.length - 1;
                console.log("Data Size :" + data.length);
                console.log("LIST Data Received :" + JSON.stringify(data));
                annex2CFRT.listOfData = data;



                setValues(annex2CFRT);

                // Setting Standard Asset Provision Values
                setStandardAssetProvision();
            },
            function (errResponse) {
                console.log("Error While getting getAnnex2cList data >>>>>>>");
            });

        // Getting User Input Data From DB ---------->>>
        annex2CFRTFactory.getAnnex2cInputData(params).then(function (data) {
                if (data != null || data !== undefined) {
                    console.log("Input Data Received :" + JSON.stringify(data));
                    console.log("User Input Data from LESS IBG :" + annex2CFRT.lessIBGSum1)
                    annex2CFRT.row = data;
                    setValues(annex2CFRT);
                    setStandardAssetProvision();

                    console.log("After Setting Data to Annexure2C ROW : " + annex2CFRT.row.lessIBGSum1);
                }
            },
            function (errResponse) {
                console.log("Error While getting user Input Data");
            });
        /////////////////////////////////////////////////

    }


    // Setting Standard Asset Values after InputData Received from DB
    function setStandardAssetProvision() {
        // alert("11111 :START :setStandardAssetProvision");

        console.log("Data Size :" + annex2CFRT.datasize) //6

        // Standard Asset of the whole Bank  In CR
        $('#stdAssetSum1').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].general) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].general)).toFixed(2).toString());
        $('#stdAssetSum2').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].personalLoanNBFCNDSL) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].personalLoanNBFCNDSL)).toFixed(2).toString());
        $('#stdAssetSum3').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].exposureCapMkt) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].exposureCapMkt)).toFixed(2).toString());
        $('#stdAssetSum4').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].standard) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].standard)).toFixed(2).toString());
        $('#stdAssetSum5').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].npaUpgradedStdAsset) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].npaUpgradedStdAsset)).toFixed(2).toString());
        $('#stdAssetSum6').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].newRestructuredStdACS) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].newRestructuredStdACS)).toFixed(2).toString());
        $('#stdAssetSum7').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].memeRestStdAssets) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].memeRestStdAssets)).toFixed(2).toString());
        $('#stdAssetSum8').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].covid) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].covid)).toFixed(2).toString());
        $('#stdAssetSum9').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].commercialRealEstResdhousing) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].commercialRealEstResdhousing)).toFixed(2).toString());
        $('#stdAssetSum10').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].expCommRealEst) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].expCommRealEst)).toFixed(2).toString());
        $('#stdAssetSum11').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].others) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].others)).toFixed(2).toString());
        // New Column Added Here
        $('#stdAssetSum12').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].wilFul) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].wilFul)).toFixed(2).toString());
        $('#stdAssetSum13').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].secured) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].secured)).toFixed(2).toString());
        $('#stdAssetSum14').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].unSecured) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].unSecured)).toFixed(2).toString());
        $('#stdAssetSum15').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].restructure) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].restructure)).toFixed(2).toString());
        $('#stdAssetSum16').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].nonRestructure) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].nonRestructure)).toFixed(2).toString());
        // New Column Added Here

        $('#stdAssetSum17').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].allOthers) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].allOthers)).toFixed(2).toString());

        // Sum of ROW 13
        $('#stdAssetSum18').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 3].total) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 1].total)).toFixed(2).toString());


        // Provision required WB In Cr
        $('#provWBSum1').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].general) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].general)).toFixed(2).toString());
        $('#provWBSum2').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].personalLoanNBFCNDSL) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].personalLoanNBFCNDSL)).toFixed(2).toString());
        $('#provWBSum3').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].exposureCapMkt) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].exposureCapMkt)).toFixed(2).toString());
        $('#provWBSum4').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].standard) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].standard)).toFixed(2).toString());
        $('#provWBSum5').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].npaUpgradedStdAsset) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].npaUpgradedStdAsset)).toFixed(2).toString());
        $('#provWBSum6').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].newRestructuredStdACS) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].newRestructuredStdACS)).toFixed(2).toString());
        $('#provWBSum7').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].memeRestStdAssets) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].memeRestStdAssets)).toFixed(2).toString());
        $('#provWBSum8').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].covid) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].covid)).toFixed(2).toString());
        $('#provWBSum9').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].commercialRealEstResdhousing) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].commercialRealEstResdhousing)).toFixed(2).toString());
        $('#provWBSum10').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].expCommRealEst) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].expCommRealEst)).toFixed(2).toString());
        $('#provWBSum11').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].others) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].others)).toFixed(2).toString());
        // New Columns Added Here
        $('#provWBSum12').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].wilFul) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].wilFul)).toFixed(2).toString());
        $('#provWBSum13').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].secured) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].secured)).toFixed(2).toString());
        $('#provWBSum14').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].unSecured) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].unSecured)).toFixed(2).toString());
        $('#provWBSum15').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].restructure) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].restructure)).toFixed(2).toString());
        $('#provWBSum16').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].nonRestructure) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].nonRestructure)).toFixed(2).toString());

        // New Columns Added Here

        $('#provWBSum17').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 0].allOthers) + annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].allOthers)).toFixed(2).toString());

        console.log("Value Set to Row After setStandardAssetProvision Function :" + annex2CFRT.row.toString());
        console.log("JSON Value Set to Row After setStandardAssetProvision Function :" + JSON.stringify(annex2CFRT.row));
        // alert("11111 :END :setStandardAssetProvision");
    }

    // After Setting Value get form DB
    function setValues(annex2CFRT) {

        // alert("22222 :START :setValues");  /// 1111111

        // Less Already provided IN IBG User Input SUM  // Changes Here Added 18th IBGSum18 Instead of IBGSum13
        annex2CFRT.row.lessIBGSum18 =
            (annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum1) +
            annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum2) +
            annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum3) +
            annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum4) +
            annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum5) +
            annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum6) +
            annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum7) +
            annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum8) +
            annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum9) +
            annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum10) +
            annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum11) +
            annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum12) +
            annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum13) +
            annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum14) +
            annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum15) +
            annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum16) +
            annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum17)).toFixed(2);


        console.log("annex2CFRT.row.lessIBGSum12 :"+annex2CFRT.row.lessIBGSum12);
        console.log("annex2CFRT.row.lessIBGSum13 :"+annex2CFRT.row.lessIBGSum13);
        console.log("annex2CFRT.row.lessIBGSum14 :"+annex2CFRT.row.lessIBGSum14);
        console.log("annex2CFRT.row.lessIBGSum15 :"+annex2CFRT.row.lessIBGSum15);
        console.log("annex2CFRT.row.lessIBGSum16 :"+annex2CFRT.row.lessIBGSum16);


        //Net Provision held for FO in India
        annex2CFRT.row.netProvFOSum1 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].general) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum1)).toFixed(2)).toString();
        annex2CFRT.row.netProvFOSum2 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].personalLoanNBFCNDSL) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum2)).toFixed(2)).toString();
        annex2CFRT.row.netProvFOSum3 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].exposureCapMkt) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum3)).toFixed(2)).toString();
        annex2CFRT.row.netProvFOSum4 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].standard) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum4)).toFixed(2)).toString();
        annex2CFRT.row.netProvFOSum5 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].npaUpgradedStdAsset) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum5)).toFixed(2)).toString();
        annex2CFRT.row.netProvFOSum6 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].newRestructuredStdACS) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum6)).toFixed(2)).toString();
        annex2CFRT.row.netProvFOSum7 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].memeRestStdAssets) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum7)).toFixed(2)).toString();
        annex2CFRT.row.netProvFOSum8 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].covid) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum8)).toFixed(2)).toString();
        annex2CFRT.row.netProvFOSum9 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].commercialRealEstResdhousing) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum9)).toFixed(2)).toString();
        annex2CFRT.row.netProvFOSum10 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].expCommRealEst) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum10)).toFixed(2)).toString();
        annex2CFRT.row.netProvFOSum11 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].others) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum11)).toFixed(2)).toString();
        //New Columns Added Here
        annex2CFRT.row.netProvFOSum12 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].wilFul) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum12)).toFixed(2)).toString();
        annex2CFRT.row.netProvFOSum13 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].secured) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum13)).toFixed(2)).toString();
        annex2CFRT.row.netProvFOSum14 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].unSecured) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum14)).toFixed(2)).toString();
        annex2CFRT.row.netProvFOSum15 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].restructure) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum15)).toFixed(2)).toString();
        annex2CFRT.row.netProvFOSum16 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].nonRestructure) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum16)).toFixed(2)).toString();
        //New Columns Added Here

        annex2CFRT.row.netProvFOSum17 = ((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize].allOthers) + annex2CFRT.parseFloat(annex2CFRT.row.lessIBGSum17)).toFixed(2)).toString();

        annex2CFRT.row.netProvFOSum18 = (annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum1) +  // Changes here the Total Variable to netProvFOSum18 Before netProvFOSum13
            annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum2) +                                // ADDED THE 17 VARIABLE FOR CALCULATION INSTEAD OF 12 PREVIOUSLY
            annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum3) +
            annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum4) +
            annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum5) +
            annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum6) +
            annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum7) +
            annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum8) +
            annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum9) +
            annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum10) +
            annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum11) +
            annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum12) +
            annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum13) +
            annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum14) +
            annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum15) +
            annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum16) +
            annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum17)).toFixed(2);

        // Provison Required WB In CR SUM  // Changes Here the Variable  #provWBSum13 to provWBSum18
        $('#provWBSum18').val((annex2CFRT.parseFloat(annex2CFRT.listOfData[annex2CFRT.datasize - 2].total) + annex2CFRT.parseFloat(annex2CFRT.row.netProvFOSum13)).toFixed(2).toString());

        // Input Calculations

        // 'Total Provision on STD assets as on DD.MM.YYYY* (CURRENT YEAR)
        annex2CFRT.row.sumofInput9 = (annex2CFRT.parseFloat(annex2CFRT.row.input1) + annex2CFRT.parseFloat(annex2CFRT.row.input2) + annex2CFRT.parseFloat(annex2CFRT.row.input3) +
            annex2CFRT.parseFloat(annex2CFRT.row.input4) + annex2CFRT.parseFloat(annex2CFRT.row.input5) + annex2CFRT.parseFloat(annex2CFRT.row.input6) + annex2CFRT.parseFloat(annex2CFRT.row.input7) + annex2CFRT.parseFloat(annex2CFRT.row.input8) + annex2CFRT.parseFloat($('#provWBSum13').val())).toFixed(2);


        //   Additional Provision Required 12 Months FY YYYY-YY * (CURRENT FINANCIAL YEAR)
        annex2CFRT.row.sumofInput12 = (annex2CFRT.parseFloat(annex2CFRT.row.sumofInput9) - annex2CFRT.parseFloat(annex2CFRT.row.input10)).toFixed(2);


        //  Additional Provision Required 3 Months FY YYYY-YY * (CURRENT FINANCIAL YEAR) Q1/2/3/4*

        annex2CFRT.row.sumofInput13 = (annex2CFRT.parseFloat(annex2CFRT.row.sumofInput9) - annex2CFRT.parseFloat(annex2CFRT.row.input9)).toFixed(2);

        console.log("Values Set to ROW After Set Value Function : " + annex2CFRT.row);
        console.log("JSON Values Set to ROW After Set Value Function : " + JSON.stringify(annex2CFRT.row));

    }


    $scope.$watchCollection('annex2CFRT.row', function (newValue, oldValue) {

        if (typeof annex2CFRT.datasize === "undefined") {
            return false;
        }
        setStandardAssetProvision();
        setValues(annex2CFRT);


    });


// On Save Data Fn  --------------->
    annex2CFRT.saveData = function (row, flag) {

        console.log("annex2CFRT.row.lessIBGSum12 :"+annex2CFRT.row.lessIBGSum12);
        console.log("annex2CFRT.row.lessIBGSum13 :"+annex2CFRT.row.lessIBGSum13);
        console.log("annex2CFRT.row.lessIBGSum14 :"+annex2CFRT.row.lessIBGSum14);
        console.log("annex2CFRT.row.lessIBGSum15 :"+annex2CFRT.row.lessIBGSum15);
        console.log("annex2CFRT.row.lessIBGSum16 :"+annex2CFRT.row.lessIBGSum16);

        var userInputs = JSON.stringify(
            {
                1: annex2CFRT.row.input1,
                2: annex2CFRT.row.input2,
                3: annex2CFRT.row.input3,
                4: annex2CFRT.row.input4,
                5: annex2CFRT.row.input5,
                6: annex2CFRT.row.input6,
                7: annex2CFRT.row.input7,
                8: annex2CFRT.row.input8,
                9: annex2CFRT.row.input9,
                10: annex2CFRT.row.input10
            });


        console.log("Values in Pairs :" + JSON.stringify(userInputs));


        var parms = JSON.stringify({

            // ROW Object
            'row': userInputs,
            'circleCode': $scope.sessionUser.circleCode,
            'qed': $scope.sessionUser.quarterEndDate,
            'flag': flag,
            'reportID': '5005',


            // LESS Already Provided in IBG
            'lessIBGSum1': annex2CFRT.row.lessIBGSum1,
            'lessIBGSum2': annex2CFRT.row.lessIBGSum2,
            'lessIBGSum3': annex2CFRT.row.lessIBGSum3,
            'lessIBGSum4': annex2CFRT.row.lessIBGSum4,
            'lessIBGSum5': annex2CFRT.row.lessIBGSum5,
            'lessIBGSum6': annex2CFRT.row.lessIBGSum6,
            'lessIBGSum7': annex2CFRT.row.lessIBGSum7,
            'lessIBGSum8': annex2CFRT.row.lessIBGSum8,
            'lessIBGSum9': annex2CFRT.row.lessIBGSum9,
            'lessIBGSum10': annex2CFRT.row.lessIBGSum10,
            'lessIBGSum11': annex2CFRT.row.lessIBGSum11,
            'lessIBGSum12': annex2CFRT.row.lessIBGSum12,
            'lessIBGSum13': annex2CFRT.row.lessIBGSum13,
            'lessIBGSum14': annex2CFRT.row.lessIBGSum14,   // Change Here Added the Additional Columns 12-16
            'lessIBGSum15': annex2CFRT.row.lessIBGSum15,
            'lessIBGSum16': annex2CFRT.row.lessIBGSum16,
            'lessIBGSum17': annex2CFRT.row.lessIBGSum17,
            'lessIBGSum18': annex2CFRT.row.lessIBGSum18,


        });
        console.log("Inside Save Data :"+parms)
        console.log("Inside Save Data :"+JSON.stringify(parms));

        annex2CFRTFactory.saveData(parms).then(function (data) {
                if (data) {
                    console.log("Data Received :" + JSON.stringify(data));
                    annex2CFRT.displayMessage = "Report Submitted Successfully";
                    if (flag == 'I') {
                        $('#submitData')
                            .modal(
                                {
                                    backdrop: 'static',
                                    keyboard: false,
                                    modal: true
                                });
                        $('#submitData')
                            .on('shown.bs.modal',
                                function () {
                                    $('#submitData')
                                        .trigger('focus');
                                });
                    } else {
                        annex2CFRT.displayMessage = "Report Saved Successfully";
                        $('#saveData')
                            .modal(
                                {
                                    backdrop: 'static',
                                    keyboard: false,
                                    modal: true
                                });
                        $('#saveData').on('shown.bs.modal',
                            function () {
                                $('#saveData').trigger('focus');
                            });
                    }
                } else {
                    annex2CFRT.displayMessage = "Report Not Submitted";
                    if (flag == 'I') {
                        $('#FRTfailedSaveSubmit')
                            .modal(
                                {
                                    backdrop: 'static',
                                    keyboard: false,
                                    modal: true
                                });
                        $('#FRTfailedSaveSubmit')
                            .on('shown.bs.modal',
                                function () {
                                    $('#FRTfailedSaveSubmit')
                                        .trigger('focus');
                                });
                    } else {
                        annex2CFRT.displayMessage = "Report Not Saved ";
                        $('#FRTfailedSaveSubmit')
                            .modal(
                                {
                                    backdrop: 'static',
                                    keyboard: false,
                                    modal: true
                                });
                        $('#FRTfailedSaveSubmit').on('shown.bs.modal',
                            function () {
                                $('#FRTfailedSaveSubmit').trigger('focus');
                            });
                    }

                    ///

                    // alert("Error While Saving Data");
                }
            },
            function (errResponse) {
                console.log("Error While getting getAnnex2cList data >>>>>>>");
            });
    }

    annex2CFRT.gotoMiscWorklist = function () {
        /*$state.go('frt_maker.miscReport');
        $(".modal-backdrop.in").hide();*/
        $timeout(function () {
            $state.go('frt_maker.miscReport');
            $(".modal-backdrop.in").hide();
            // $state.reload();
        }, 500);
    }

//  checkNumber  --------------->
    annex2CFRT.checkNumber = function (numVal, numId) {
        console.log("into check num fn(NG-MODEL) :" + numVal);
        console.log("into check num ID :" + numId);

        var val = document.getElementById(numId).value;
        console.log("val- " + val);

        if (val.match(/\s/g)) {
            alert("Space is not allowed");
            if (numVal == "annex2CFRT.row.lessIBGSum1") {
                annex2CFRT.row.lessIBGSum1 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum2") {
                annex2CFRT.row.lessIBGSum2 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum3") {
                annex2CFRT.row.lessIBGSum3 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum4") {
                annex2CFRT.row.lessIBGSum4 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum5") {
                annex2CFRT.row.lessIBGSum5 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum6") {
                annex2CFRT.row.lessIBGSum6 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum7") {
                annex2CFRT.row.lessIBGSum7 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum8") {
                annex2CFRT.row.lessIBGSum8 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum9") {
                annex2CFRT.row.lessIBGSum9 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum10") {
                annex2CFRT.row.lessIBGSum10 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum11") {
                annex2CFRT.row.lessIBGSum11 = "0.00";
            }
            // Changes Here new Column Added
            else if (numVal == "annex2CFRT.row.lessIBGSum12") {
                annex2CFRT.row.lessIBGSum12 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum13") {
                annex2CFRT.row.lessIBGSum13 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum14") {
                annex2CFRT.row.lessIBGSum14 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum15") {
                annex2CFRT.row.lessIBGSum15 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum16") {
                annex2CFRT.row.lessIBGSum16 = "0.00";
            }
            // Change Here New Columns added
            else if (numVal == "annex2CFRT.row.lessIBGSum17") {
                annex2CFRT.row.lessIBGSum17 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum18") {
                annex2CFRT.row.lessIBGSum18 = "0.00";
            }



            // For FRT Inputs
            else if (numVal == "annex2CFRT.row.input1") {
                annex2CFRT.row.input1 = "0.00";
            } else if (numVal == "annex2CFRT.row.input2") {
                annex2CFRT.row.input2 = "0.00";
            } else if (numVal == "annex2CFRT.row.input3") {
                annex2CFRT.row.input3 = "0.00";
            } else if (numVal == "annex2CFRT.row.input4") {
                annex2CFRT.row.input4 = "0.00";
            } else if (numVal == "annex2CFRT.row.input5") {
                annex2CFRT.row.input5 = "0.00";
            } else if (numVal == "annex2CFRT.row.input6") {
                annex2CFRT.row.input6 = "0.00";
            } else if (numVal == "annex2CFRT.row.input7") {
                annex2CFRT.row.input7 = "0.00";
            } else if (numVal == "annex2CFRT.row.input8") {
                annex2CFRT.row.input8 = "0.00";
            } else if (numVal == "annex2CFRT.row.sumofInput9") {
                annex2CFRT.row.sumofInput9 = "0.00";
            } else if (numVal == "annex2CFRT.row.input9") {
                annex2CFRT.row.input9 = "0.00";
            } else if (numVal == "annex2CFRT.row.input10") {
                annex2CFRT.row.input10 = "0.00";
            } else if (numVal == "annex2CFRT.row.sumofInput12") {
                annex2CFRT.row.sumofInput12 = "0.00";
            } else if (numVal == "annex2CFRT.row.sumofInput13") {
                annex2CFRT.row.sumofInput13 = "0.00";
                // New Columns Added Here
            } else if (numVal == "annex2CFRT.row.sumofInput14") {
                annex2CFRT.row.sumofInput14 = "0.00";
            } else if (numVal == "annex2CFRT.row.sumofInput15") {
                annex2CFRT.row.sumofInput15 = "0.00";
            } else if (numVal == "annex2CFRT.row.sumofInput16") {
                annex2CFRT.row.sumofInput16 = "0.00";
            } else if (numVal == "annex2CFRT.row.sumofInput17") {
                annex2CFRT.row.sumofInput17 = "0.00";
            } else if (numVal == "annex2CFRT.row.sumofInput18") {
                annex2CFRT.row.sumofInput18 = "0.00";
            }


            return false;
        }

        var pattern = /-0\.\d{2}$/;
        if (val.match(pattern)) {
            alert("Please enter Valid Amount");
            // document.getElementById(numId).value = "0.00";
            /////
            if (numVal == "annex2CFRT.row.lessIBGSum1") {
                annex2CFRT.row.lessIBGSum1 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum2") {
                annex2CFRT.row.lessIBGSum2 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum3") {
                annex2CFRT.row.lessIBGSum3 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum4") {
                annex2CFRT.row.lessIBGSum4 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum5") {
                annex2CFRT.row.lessIBGSum5 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum6") {
                annex2CFRT.row.lessIBGSum6 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum7") {
                annex2CFRT.row.lessIBGSum7 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum8") {
                annex2CFRT.row.lessIBGSum8 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum9") {
                annex2CFRT.row.lessIBGSum9 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum10") {
                annex2CFRT.row.lessIBGSum10 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum11") {
                annex2CFRT.row.lessIBGSum11 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum12") {
                annex2CFRT.row.lessIBGSum12 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum13") {
                annex2CFRT.row.lessIBGSum13 = "0.00";
            }
            // New Columns Added Here
            else if (numVal == "annex2CFRT.row.lessIBGSum14") {
                annex2CFRT.row.lessIBGSum14 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum15") {
                annex2CFRT.row.lessIBGSum15 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum16") {
                annex2CFRT.row.lessIBGSum16 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum17") {
                annex2CFRT.row.lessIBGSum17 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum18") {
                annex2CFRT.row.lessIBGSum18 = "0.00";
            }
            // For FRT Inputs
            else if (numVal == "annex2CFRT.row.input1") {
                annex2CFRT.row.input1 = "0.00";
            } else if (numVal == "annex2CFRT.row.input2") {
                annex2CFRT.row.input2 = "0.00";
            } else if (numVal == "annex2CFRT.row.input3") {
                annex2CFRT.row.input3 = "0.00";
            } else if (numVal == "annex2CFRT.row.input4") {
                annex2CFRT.row.input4 = "0.00";
            } else if (numVal == "annex2CFRT.row.input5") {
                annex2CFRT.row.input5 = "0.00";
            } else if (numVal == "annex2CFRT.row.input6") {
                annex2CFRT.row.input6 = "0.00";
            } else if (numVal == "annex2CFRT.row.input7") {
                annex2CFRT.row.input7 = "0.00";
            } else if (numVal == "annex2CFRT.row.input8") {
                annex2CFRT.row.input8 = "0.00";
            } else if (numVal == "annex2CFRT.row.sumofInput9") {
                annex2CFRT.row.sumofInput9 = "0.00";
            } else if (numVal == "annex2CFRT.row.input9") {
                annex2CFRT.row.input9 = "0.00";
            } else if (numVal == "annex2CFRT.row.input10") {
                annex2CFRT.row.input10 = "0.00";
            } else if (numVal == "annex2CFRT.row.sumofInput12") {
                annex2CFRT.row.sumofInput12 = "0.00";
            } else if (numVal == "annex2CFRT.row.sumofInput13") {
                annex2CFRT.row.sumofInput13 = "0.00";
            }

            //////
            return false;
        }


        if (isNaN(val)) {
            alert("Please enter Numeric Value only");
            if (numVal == "annex2CFRT.row.lessIBGSum1") {
                annex2CFRT.row.lessIBGSum1 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum2") {
                annex2CFRT.row.lessIBGSum2 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum3") {
                annex2CFRT.row.lessIBGSum3 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum4") {
                annex2CFRT.row.lessIBGSum4 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum5") {
                annex2CFRT.row.lessIBGSum5 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum6") {
                annex2CFRT.row.lessIBGSum6 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum7") {
                annex2CFRT.row.lessIBGSum7 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum8") {
                annex2CFRT.row.lessIBGSum8 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum9") {
                annex2CFRT.row.lessIBGSum9 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum10") {
                annex2CFRT.row.lessIBGSum10 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum11") {
                annex2CFRT.row.lessIBGSum11 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum12") {
                annex2CFRT.row.lessIBGSum12 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum13") {
                annex2CFRT.row.lessIBGSum13 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum14") {
                annex2CFRT.row.lessIBGSum14 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum15") {
                annex2CFRT.row.lessIBGSum15 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum16") {
                annex2CFRT.row.lessIBGSum16 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum17") {
                annex2CFRT.row.lessIBGSum17 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum18") {
                annex2CFRT.row.lessIBGSum18 = "0.00";
            }
            // For FRT Inputs
            else if (numVal == "annex2CFRT.row.input1") {
                annex2CFRT.row.input1 = "0.00";
            } else if (numVal == "annex2CFRT.row.input2") {
                annex2CFRT.row.input2 = "0.00";
            } else if (numVal == "annex2CFRT.row.input3") {
                annex2CFRT.row.input3 = "0.00";
            } else if (numVal == "annex2CFRT.row.input4") {
                annex2CFRT.row.input4 = "0.00";
            } else if (numVal == "annex2CFRT.row.input5") {
                annex2CFRT.row.input5 = "0.00";
            } else if (numVal == "annex2CFRT.row.input6") {
                annex2CFRT.row.input6 = "0.00";
            } else if (numVal == "annex2CFRT.row.input7") {
                annex2CFRT.row.input7 = "0.00";
            } else if (numVal == "annex2CFRT.row.input8") {
                annex2CFRT.row.input8 = "0.00";
            } else if (numVal == "annex2CFRT.row.sumofInput9") {
                annex2CFRT.row.sumofInput9 = "0.00";
            } else if (numVal == "annex2CFRT.row.input9") {
                annex2CFRT.row.input9 = "0.00";
            } else if (numVal == "annex2CFRT.row.input10") {
                annex2CFRT.row.input10 = "0.00";
            } else if (numVal == "annex2CFRT.row.sumofInput12") {
                annex2CFRT.row.sumofInput12 = "0.00";
            } else if (numVal == "annex2CFRT.row.sumofInput13") {
                annex2CFRT.row.sumofInput13 = "0.00";
            }
            return false;
        }

        var c = /^(?:\-?\d{1,16}\.\d{1,2}|\-?\d{1,16})$/;

        if (c.test(val) || val == "") {
            console.log("in if of check c");
            var idot = val.indexOf('.');
            var len = val.lenght;
            console.log("idot 2 zero append:-   " + idot);
            if (idot == -1) {
                console.log("idot 2 zero append:-");
                if (val == "") {
                    //If value is Empty
                    console.log("into blank amount append 0.00");
                    document.getElementById(numId).value = val.concat("0.00");
                } else {
                    // Value is Not Empty & Adding 2 Decimal at End
                    document.getElementById(numId).value = val.concat(".00");
                    console.log("idot 2 zero append:-   " + idot + "val value:- " + val.concat(".00"))
                }

            } else if (idot != -1 && val.charAt(idot + 2) == "") {
                alert("More then 2 decimal point not allowed");
                console.log("idot 1 zero append:-");
                document.getElementById(numId).value = val.concat("0");
                console.log("idot 1 zero append:-   " + idot + "val value:- " + val.concat("0"))
            }
        } else {
            var idotELSE = val.indexOf('.');
            if (idotELSE != -1 && val.split(".")[1].length > 2) {
                alert("Maximum 2 decimal digit allowed.");
            } else {
                alert("Amount cannot be greater than 16 digits.");
            }

            if (numVal == "annex2CFRT.row.lessIBGSum1") {
                annex2CFRT.row.lessIBGSum1 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum2") {
                annex2CFRT.row.lessIBGSum2 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum3") {
                annex2CFRT.row.lessIBGSum3 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum4") {
                annex2CFRT.row.lessIBGSum4 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum5") {
                annex2CFRT.row.lessIBGSum5 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum6") {
                annex2CFRT.row.lessIBGSum6 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum7") {
                annex2CFRT.row.lessIBGSum7 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum8") {
                annex2CFRT.row.lessIBGSum8 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum9") {
                annex2CFRT.row.lessIBGSum9 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum10") {
                annex2CFRT.row.lessIBGSum10 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum11") {
                annex2CFRT.row.lessIBGSum11 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum12") {
                annex2CFRT.row.lessIBGSum12 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum13") {
                annex2CFRT.row.lessIBGSum13 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum14") {
                annex2CFRT.row.lessIBGSum14 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum15") {
                annex2CFRT.row.lessIBGSum15 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum16") {
                annex2CFRT.row.lessIBGSum16 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum17") {
                annex2CFRT.row.lessIBGSum17 = "0.00";
            } else if (numVal == "annex2CFRT.row.lessIBGSum18") {
                annex2CFRT.row.lessIBGSum18 = "0.00";
            }
            // For FRT Inputs
            else if (numVal == "annex2CFRT.row.input1") {
                annex2CFRT.row.input1 = "0.00";
            } else if (numVal == "annex2CFRT.row.input2") {
                annex2CFRT.row.input2 = "0.00";
            } else if (numVal == "annex2CFRT.row.input3") {
                annex2CFRT.row.input3 = "0.00";
            } else if (numVal == "annex2CFRT.row.input4") {
                annex2CFRT.row.input4 = "0.00";
            } else if (numVal == "annex2CFRT.row.input5") {
                annex2CFRT.row.input5 = "0.00";
            } else if (numVal == "annex2CFRT.row.input6") {
                annex2CFRT.row.input6 = "0.00";
            } else if (numVal == "annex2CFRT.row.input7") {
                annex2CFRT.row.input7 = "0.00";
            } else if (numVal == "annex2CFRT.row.input8") {
                annex2CFRT.row.input8 = "0.00";
            } else if (numVal == "annex2CFRT.row.sumofInput9") {
                annex2CFRT.row.sumofInput9 = "0.00";
            } else if (numVal == "annex2CFRT.row.input9") {
                annex2CFRT.row.input9 = "0.00";
            } else if (numVal == "annex2CFRT.row.input10") {
                annex2CFRT.row.input10 = "0.00";
            } else if (numVal == "annex2CFRT.row.sumofInput12") {
                annex2CFRT.row.sumofInput12 = "0.00";
            } else if (numVal == "annex2CFRT.row.sumofInput13") {
                annex2CFRT.row.sumofInput13 = "0.00";
            }
            return false;
        }
        return true;
    }
});




