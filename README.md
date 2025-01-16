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
        .state('frt_admin', {
            url: "/frt_admin",
            abstract: true,
            templateUrl: "views/frt/admin/frtadmin.jsp",
            data: {
                authorizedRoles: [USER_ROLES.frtadmin]
            }
        })
        .state('frt_admin.home', {
            url: "/home",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/frt/admin/frtadminindex.jsp",
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }
            }
        })

        .state('frt_admin.FRtoIRIS', {
            url: "/home",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/frt/admin/FRtoIris.jsp",
                    controller: 'FRtoIrisController',
                    controllerAs: 'FRTIR',
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }
            }
        })


        .state('frt_admin.DWHFile', {
            url: "/DWHFile",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/frt/admin/generateFile.jsp",
                    controller: 'DWHFileController',
                    controllerAs: 'DWHFile',
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }
            }

        })


        .state('frt_maker.AuditMaster', {
            url: "/AuditMaster",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/AuditMaster.jsp",
                    controller:'AuditMasterController',
                    controllerAs:'AuditMaster',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.dashboard', {
            url: "/dashboard",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/super/maker/frtDashoard.jsp",
                    controller: 'DashboardController',
                    controllerAs: 'dashboard',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_admin.circleMapping', {
            url: "/ChangeCircle",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/frt/admin/ChkCircleMap.jsp",
                    controller: "CircleChangeController",
                    controllerAs: "changeCircle",
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }

            }
        })
        .state('frt_maker.circleMapping', {
            url: "/CircleMapping",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/BranchCircleMap.jsp",
                    controller: "CircleMappingController",
                    controllerAs: "circle",
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }

            }
        })

        .state('super_maker.dashboard', {
            url: "/dashboard",
            views: {
                'supermakerContent': {
                    templateUrl: "views/super/maker/frtDashoard.jsp",
                    controller: 'DashboardController',
                    controllerAs: 'dashboard',
                    data: {
                        authorizedRoles: [USER_ROLES.supermaker]
                    }
                }
            }
        })
        .state('frt_maker.AuditReports', {
            url: "/AuditReports",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/AuditReports.jsp",
                    controller:'AuditReportsController',
                    controllerAs:'AuditReports',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.AuditBranch', {
            url: "/AuditBranch",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/AuditMapping.jsp",
                    controller:'AuditBranchController',
                    controllerAs:'AuditBranch',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_admin.pnlVar',{
            url:"/pnlVarReport",
            views:{
                'frtAdminContent': {
                    templateUrl:"views/frt/pnlVar.jsp",
                    controller:"pnlVarController",
                    controllerAs:"pnlVar",
                    data:{
                        authorizedRoles:[USER_ROLES.frtadmin]
                    }
                }

            }
        })
        .state('frt_maker.pnlVar',{
            url:"/pnlVarReport",
            views:{
                'frtMakerContent': {
                    templateUrl:"views/frt/pnlVar.jsp",
                    controller:"pnlVarController",
                    controllerAs:"pnlVar",
                    data:{
                        authorizedRoles:[USER_ROLES.frtmaker]
                    }
                }

            }
        })


        .state('frt_admin.compCodeData', {
            url: "/CompCodeReport",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/frt/compCodeData.jsp",
                    controller: "compCodeDataController",
                    controllerAs: "compCodeData",
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }

            }
        })
        .state('frt_maker.compCodeData', {
            url: "/CompCodeReport",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/compCodeData.jsp",
                    controller: "compCodeDataController",
                    controllerAs: "compCodeData",
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }

            }
        })

        .state('frt_maker.branchwise',{
            url:"/branchwise",
            views:{
                'frtMakerContent': {
                    templateUrl:"views/frt/branchwise.jsp",
                    controller:"branchwiseController",
                    controllerAs:"branchwise",
                    data:{
                        authorizedRoles:[USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_admin.branchwise',{
            url:"/branchwise",
            views:{
                'frtAdminContent': {
                    templateUrl:"views/frt/branchwise.jsp",
                    controller:"branchwiseController",
                    controllerAs:"branchwise",
                    data:{
                        authorizedRoles:[USER_ROLES.frtadmin]
                    }
                }
            }
        })

        .state('frt_maker.mocReport',{
            url:"/mocReport",
            views:{
                'frtMakerContent': {
                    templateUrl:"views/frt/mocReport.jsp",
                    controller:"mocReportController",
                    controllerAs:"mocReport",
                    data:{
                        authorizedRoles:[USER_ROLES.frtmaker]
                    }
                }
            }
        })



        .state('frt_admin.mocReport',{
            url:"/mocReport",
            views:{
                'frtAdminContent': {
                    templateUrl:"views/frt/mocReport.jsp",
                    controller:"mocReportController",
                    controllerAs:"mocReport",
                    data:{
                        authorizedRoles:[USER_ROLES.frtadmin]
                    }
                }
            }
        })

        .state('frt_maker.miscReport', {
            url: "/miscReport",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/miscReport.jsp",
                    controller: 'miscReportController',
                    controllerAs: 'miscReport',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })

        .state('frt_maker.frReturn', {
            url: "/frReturn",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/frReturn.jsp",
                    controller: 'frReturnController',
                    controllerAs: 'frReturn',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        //Circle Maker Annexure2C Report
        .state('circle_maker.ANNEX_2C', {
            url: "/annex2CReport",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/ANNEX_2C.jsp",
                    controller: 'annex2CCircleReportController',
                    controllerAs: 'annex2C',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })

        // FRT Annexure2C Report
        .state('frt_maker.annexure2C', {
            url: "/annex2CReport",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/FRTAnnexure2CReport.jsp",
                    controller: 'annex2CFRTReportController',
                    controllerAs: 'annex2CFRT',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })

        .state('frt_maker.frRelPart', {
            url: "/frRelPart",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/FRRelPart.jsp",
                    controller: 'FRRelPartController',

                    controllerAs: 'RelPart',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        .state('frt_maker.frExpConRsk', {
            url: "/ExpConRsk",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/ExpConRsk.jsp",
                    controller: 'ExpConRskController',
                    controllerAs: 'ExpConRsk',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        .state('frt_maker.frDetUnsecAdv', {
            url: "/DetUnsecAdv",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/DetUnsecAdv.jsp",
                    controller: 'DetUnsecAdvController',
                    controllerAs: 'DetUnsecAdv',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        .state('frt_maker.frT47', {
            url: "/T47",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T47.jsp",
                    controller: 'T47Controller',
                    controllerAs: 'T47',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        .state('frt_maker.T29', {
            url: "/T29",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T29.jsp",
                    controller: 'T29Controller',
                    controllerAs: 'T29',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        .state('frt_maker.Investment', {
            url: "/Inv",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/Investment.jsp",
                    controller:'InvestmentController',
                    controllerAs:'Inv',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })


        .state('frt_maker.AssetQuality', {
            url: "/AsstQlty",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/AssetQuality.jsp",
                    controller:'AssetQualityController',
                    controllerAs:'AsstQlty',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })



        .state('frt_maker.Segments', {
            url: "/Sgm",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/FRSegments.jsp",
                    controller:'SegmentsController',
                    controllerAs:'Sgm',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })


        .state('frt_maker.T02', {
            url: "/T02",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T02.jsp",
                    controller:'T02Controller',
                    controllerAs:'T02',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })


        .state('frt_maker.T01', {
            url: "/T01",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T01.jsp",
                    controller:'T01Controller',
                    controllerAs:'T01',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })



        .state('frt_maker.T03', {
            url: "/T03",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/FR_T3.jsp",
                    controller:'FR_T3Controller',
                    controllerAs:'T03',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.T56', {
            url: "/T56",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T56.jsp",
                    controller: 'T56Controller',
                    controllerAs: 'T56',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        /*  .state('frt_maker.SC01A', {
              url: "/SC01A",
              views: {
                  'frtMakerContent': {
                      templateUrl: "views/frt/maker/frReturn/SC01A.jsp",
                      controller:'SC01AController',
                      controllerAs:'SC01A',
                      data: {
                          authorizedRoles: [USER_ROLES.frtmaker]
                      }
                  }
              }
          })*/



        .state('frt_maker.SC10', {
            url: "/FR_SC10",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/FR_SC10.jsp",
                    controller:'FR_SC10Controller',
                    controllerAs:'FR_SC10',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.SC12', {
            url: "/FR_SC12",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/FR_SC12.jsp",
                    controller:'FR_SC12Controller',
                    controllerAs:'FR_SC12',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })



        .state('frt_maker.frCmnTxtRep', { // common jsp loader
            url: "/frCmnTxtRep",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/CommonFrTextScreen.jsp",
                    controller: 'CommonTxtReportCtrl',
                    controllerAs: 'TxtCtrl',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.frCapAd', {
            url: "/frCapAd",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/FRCapAd.jsp",
                    controller: 'FRCapAdController',
                    controllerAs: 'FRCapAd',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.frRepTrans', {
            url: "/frRepTrans",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/FRRepTrans.jsp",
                    controller: 'FRRepTransController',
                    controllerAs: 'FRRepTrans',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.secWiseNPA', {
            url: "/secWiseNPA",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/SecWiseNPA.jsp",
                    controller: 'SecWiseNPAController',
                    controllerAs: 'SecWiseNPA',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.T30', {
            url: "/T30",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T30.jsp",
                    controller: 'T30Controller',
                    controllerAs: 'T30',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        ///////////////Author Shilpa///////////////////////////////
        .state('frt_maker.T33', {
            url: "/T33",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/CommonFRAddRow.jsp",
                    controller: 'CommonFRAddRowController',
                    controllerAs: 'FRAddRow',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        .state('frt_maker.T34', {
            url: "/T34",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/CommonFRAddRow.jsp",
                    controller: 'CommonFRAddRowController',
                    controllerAs: 'FRAddRow',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        .state('frt_maker.T35', {
            url: "/T35",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/CommonFRAddRow.jsp",
                    controller: 'CommonFRAddRowController',
                    controllerAs: 'FRAddRow',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        ////////////////////////////////////////////////////////////////////////////


        //Author : V1012297
        .state('frt_maker.T39', {
            url: "/T39",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T39.jsp",
                    controller: 'FRDiscAssetQualityController',
                    controllerAs: 'FRDiscAsset',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        //Author : V1012297
        .state('frt_maker.T51', {
            url: "/T51",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T51.jsp",
                    controller: 'T51Controller',
                    controllerAs: 'T51',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        //Author : V1012297
        .state('frt_maker.T59', {
            url: "/T59",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T59.jsp",
                    controller: 'SegmentsSecondayController',
                    controllerAs: 'SegControl',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.T63', {
            url: "/T63",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T63.jsp",
                    controller: 'T63Controller',
                    controllerAs: 'T63',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        .state('frt_maker.T71', {
            url: "/T71",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T71.jsp",
                    controller: 'T71Controller',
                    controllerAs: 'T71',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        .state('frt_maker.SC14', {
            url: "/SC14",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/SC14.jsp",
                    controller: 'SC14Controller',
                    controllerAs: 'SC14',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.SC02', {
            url: "/FR_SC02",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/FR_SC02.jsp",
                    controller: 'FR_SC02Controller',
                    controllerAs: 'FR_SC02',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })


        .state('frt_maker.FR_SC08', {
            url: "/FR_SC08",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/FR_SC08.jsp",
                    controller: 'FR_SC08Controller',
                    controllerAs: 'FR_SC08',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })


        .state('frt_maker.FR_SC09', {
            url: "/FR_SC09",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/FR_SC09.jsp",
                    controller: 'FR_SC09Controller',
                    controllerAs: 'FR_SC09',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })

        .state('frt_maker.FR_SC16', {
            url: "/FR_SC16",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/FR_SC16.jsp",
                    controller: 'FR_SC16Controller',
                    controllerAs: 'FR_SC16',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })

        .state('frt_maker.T17', {
            url: "/FR_T17",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T17.jsp",
                    controller: 'T17Controller',
                    controllerAs: 'T17',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })


        .state('frt_maker.T36', {
            url: "/T36",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T36.jsp",
                    controller: 'T36Controller',
                    controllerAs: 'T36',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })


        .state('frt_maker.T38', {
            url: "/T38",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T38.jsp",
                    controller: 'T38Controller',
                    controllerAs: 'T38',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })

        .state('frt_maker.T41', {
            url: "/T41",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T41.jsp",
                    controller: 'T41Controller',
                    controllerAs: 'T41',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.T57', {
            url: "/T57",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T57.jsp",
                    controller: 'T57Controller',
                    controllerAs: 'T57',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })


        .state('frt_maker.FR_CF6', {
            url: "/FR_CF6",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/FR_CF6.jsp",
                    controller: 'FR_CF6Controller',
                    controllerAs: 'FR_CF6',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.FR_CF7', {
            url: "/FR_CF7",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/FR_CF7.jsp",
                    controller: 'FR_CF7Controller',
                    controllerAs: 'FR_CF7',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.T42', {
            url: "/T42",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T42.jsp",
                    controller: 'T42Controller',
                    controllerAs: 'T42',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.T44', {
            url: "/T44",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T44.jsp",
                    controller: 'T44Controller',
                    controllerAs: 'T44',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.T45', {
            url: "/T45",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T45.jsp",
                    controller: 'T45Controller',
                    controllerAs: 'T45',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.T68', {
            url: "/T68",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T68.jsp",
                    controller: 'T68Controller',
                    controllerAs: 'T68',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.T70', {
            url: "/T70",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T70.jsp",
                    controller: 'T70Controller',
                    controllerAs: 'T70',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.WN09', {
            url: "/WN09",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/WN09.jsp",
                    controller: 'WN09Controller',
                    controllerAs: 'WN09',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.WN03', {
            url: "/WN03",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/WN03.jsp",
                    controller: 'WN03Controller',
                    controllerAs: 'WN03',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.T48', {
            url: "/T48",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T48.jsp",
                    controller: 'T48Controller',
                    controllerAs: 'T48',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })

        .state('frt_maker.T62', {
            url: "/T62",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T62.jsp",
                    controller: 'T62Controller',
                    controllerAs: 'T62',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.T66', {
            url: "/T66",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T66.jsp",
                    controller: 'T66Controller',
                    controllerAs: 'T66',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })
        .state('frt_maker.T31', {
            url: "/T31",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T31.jsp",
                    controller: 'T31Controller',
                    controllerAs: 'T31',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.T49', {
            url: "/T49",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T49.jsp",
                    controller: 'T49Controller',
                    controllerAs: 'T49',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.T53', {
            url: "/T53",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T53.jsp",
                    controller: 'T53Controller',
                    controllerAs: 'T53',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.T23', {
            url: "/T23",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/T23.jsp",
                    controller: 'T23Controller',
                    controllerAs: 'T23',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.FR_CashPro', {
            url: "/FR_CashPro",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/FR_CashPro.jsp",
                    controller: 'FR_CashProController',
                    controllerAs: 'FR_CashPro',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.FR_WN04', {
            url: "/FR_WN04",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/FR_WN04.jsp",
                    controller: 'FR_WN04Controller',
                    controllerAs: 'FR_WN04',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_maker.Dta_dtl', {
            url: "/Dta_dtl",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frReturn/DTA_DTL.jsp",
                    controller: 'DtadtlController',
                    controllerAs: 'dtadtl',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('frt_admin.ChkMiscReport', {
            url: "/ChkMiscReport",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/frt/admin/ChkMiscReport.jsp",
                    controller: 'miscReportController',
                    controllerAs: 'miscReport',
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }
            }

        })

        .state('frt_admin.frReturnChk', {
            url: "/frReturnChk",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/frt/admin/frReturnChk.jsp",
                    controller: 'frReturnController',
                    controllerAs: 'frReturn',
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }
            }

        })

        .state('frt_maker.tranche1',{
            url:"/tranche1",
            views:{
                'frtMakerContent': {
                    templateUrl:"views/frt/maker/tranche1.jsp",
                    controller:"tranche1Controller",
                    controllerAs:"tranche1",
                    data:{
                        authorizedRoles:[USER_ROLES.frtmaker]
                    }
                }
            }
        })




        .state('super_maker.mntAscii',{
            url:"/mntAsciiUpload",
            views:{
                'supermakerContent': {
                    templateUrl:"views/frt/maker/mntAscii.jsp",
                    controller:'mntAsciiController',
                    controllerAs:'mntAscii',
                    data:{
                        authorizedRoles:[USER_ROLES.supermaker]
                    }
                }
            }
        })
        .state('frt_maker.GSTINMaster', {
            url: "/GSTINMaster",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/GSTINMaster.jsp",
                    controller:'GSTINMasterController',
                    controllerAs:'GSTINMaster',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })


        .state('frt_maker.GSTINReports', {
            url: "/GSTINReports",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/GSTINwiseReports.jsp",
                    controller:'GSTINReportController',
                    controllerAs:'GSTINReport',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        .state('frt_admin.GSTINReports', {
            url: "/GSTINReports",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/frt/maker/GSTINwiseReports.jsp",
                    controller:'GSTINReportController',
                    controllerAs:'GSTINReport',
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }
            }
        })


        .state('frt_maker.branchMapping', {
            url: "/branchGstinMapping",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/branchGstinMapping.jsp",
                    controller:'branchGstinController',
                    controllerAs:'branchGstin',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })


        .state('frt_admin.adjustment', {
            url: "/adjustment",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/frt/admin/adjustment.jsp",
                    controller: 'adminAdjustController',
                    controllerAs: 'adminAdjust',
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }
            }

        })

        .state('frt_admin.prevAdjustment', {
            url: "/prevAdjustment",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/frt/admin/prevAdjustment.jsp",
                    controller: 'adminPrevAdjustController',
                    controllerAs: 'adminPrevAdjust',
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }
            }

        })

        .state('frt_admin.UserConfirmation', {
            url: "/UserConfirmation",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/frt/admin/UserConfirmation.jsp",
                    controller: 'UserConfirmController',
                    controllerAs: 'user',
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }
            }

        })
        .state('frt_admin.downloadJrxmls', {
            url: "/downloadJrxmls",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/circle/admin/downloadJrxmls.jsp",
                    controller: 'downloadJrxmls',
                    controllerAs: 'downloadJrxmls',
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }
            }

        })
        .state('frt_admin.downloadController', {
            url: "/download",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/circle/admin/downloadReports.jsp",
                    controller: 'downloadController',
                    controllerAs: 'download',
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }
            }
        })
        .state('frt_admin.ReopenReports', {
            url: "/ReopenReports",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/frt/admin/ReopenReports.jsp",
                    controller: 'ReOpenController',
                    controllerAs: 'reopen',
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }
            }

        })
        .state('frt_admin.CreatedReports', {
            url: "/CreatedReports",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/frt/admin/CreatedReports.jsp",
                    controller: 'CreatedReportsController',
                    controllerAs: 'createdRepos',
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }
            }
        })
        .state('frt_maker', {
            url: "/frt_maker",
            abstract: true,
            templateUrl: "views/frt/maker/frtmaker.jsp",
            data: {
                authorizedRoles: [USER_ROLES.frtmaker]
            }
        })




        .state('frt_maker.inter_se_adjustment', {
            url: "/inter_se",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/inter_se_adjustment.html",
                    controller: 'interController',
                    controllerAs: 'inter',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })

        .state('frt_maker.uploadMOC', {
            url: "/uploadMOC",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/uploadMOC.jsp",
                    controller: 'uploadMocController',
                    controllerAs: 'uploadMoc',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })





        .state('frt_maker.provision_adjustment', {
            url: "/provision",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/provision_adjustment.jsp",
                    controller: 'provisionController',
                    controllerAs: 'provision',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })









        .state('frt_admin.inter_se_adjustment', {
            url: "/inter_se",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/frt/maker/inter_se_adjustment.html",
                    controller: 'interController',
                    controllerAs: 'inter',
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }
            }

        })




        ///////////// scedule 4


        .state('frt_maker.schedule4', {
            url: "/schedule4",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/sc04.jsp",
                    controller: 'schedule4Controller',
                    controllerAs: 'schedule4',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })


        .state('frt_maker.home', {
            url: "/home",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/frtmakerindex.jsp",
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        .state('frt_maker.adjustment', {
            url: "/adjustment",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/adjustment.jsp",
                    controller: 'makerAdjustController',
                    controllerAs: 'makerAdjust',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })
        .state('frt_maker.prevAdjustment', {
            url: "/PrevAdjustment",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/PrevAdjustment.jsp",
                    controller: 'makerPrevAdjustController',
                    controllerAs: 'makerPrevAdjust',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })
        .state('frt_maker.worklist', {
            url: "/worklist",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/worklist.jsp",
                    controller: 'frtMakerWorklist',
                    controllerAs: 'frtMakerWorklist',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })


        .state('frt_maker.Appropriations', {
            url: "/Appropriations",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/maker/Appropriations.jsp",
                    controller: 'AppropriationsController',
                    controllerAs: 'Apropriatn',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        .state('frt_maker.CreatedReports', {
            url: "/CreatedReports",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/admin/CreatedReports.jsp",
                    controller: 'CreatedReportsController',
                    controllerAs: 'createdRepos',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })

        .state('frt_maker.downloadJrxmls', {
            url: "/downloadJrxmls",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/circle/admin/downloadJrxmls.jsp",
                    controller: 'downloadJrxmls',
                    controllerAs: 'downloadJrxmls',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })

        .state('frt_maker.UserManagement', {
            url: "/UserManagement",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/circle/admin/userCreation.jsp",
                    controller: 'UserManageController',
                    controllerAs: 'user',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }

        })

        .state('frt_maker.UserModification', {
            url: "/userModification",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/circle/admin/userModification.jsp",
                    controller: 'UserManageController',
                    controllerAs: 'user',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('super_maker.updateBSmap', {
            url: "/editmaptable",
            views: {
                'supermakerContent': {
                    templateUrl: "views/admin/edit_map_table.jsp",
                    controller: 'bsMapController',
                    controllerAs: 'bsMap',
                    data: {
                        authorizedRoles: [USER_ROLES.supermaker]
                    }
                }
            }
        })

        .state('frt_maker.SH02', {
            url: "/SH02",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/circle/maker/SH02.jsp",
                    controller: 'sh02Controller',
                    controllerAs: 'sh02',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })


        .state('frt_maker.SC09', {
            url: "/SC09",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/circle/maker/SC09.jsp",
                    controller: 'sc09Controller',
                    controllerAs: 'sc09',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        .state('frt_maker.SHC01', {
            url: "/SHC01",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/circle/maker/SHC01.jsp",
                    controller: 'SHC01Controller',
                    controllerAs: 'shc01',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        .state('circle_admin.CheckerWorklist', {
            url: "/worklist",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/worklist.jsp",
                    controller: 'CheckerWorklist',
                    controllerAs: 'checkerWorklist',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })


        .state('circle_admin.downloadController', {
            url: "/download",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/downloadReports.jsp",
                    controller: 'downloadController',
                    controllerAs: 'download',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })


        .state('circle_admin.UserModification', {
            url: "/userModification",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/userModification.jsp",
                    controller: 'UserManageController',
                    controllerAs: 'user',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })


        .state('circle_admin.downloadJrxmls', {
            url: "/downloadJrxmls",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/downloadJrxmls.jsp",
                    controller: 'downloadJrxmls',
                    controllerAs: 'downloadJrxmls',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })


        .state('circle_admin.userDetails', {
            url: "/userDetails/:id",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/userDetails.jsp",
                    controller: 'userDetails',
                    controllerAs: 'bsp',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })


        .state('circle_admin.AdminLog', {
            url: "/adminlog",
            views: {
                'adminContent': {
                    templateUrl: "views/admin/adminlog.jsp",
                    controller: 'AdminLogController',
                    controllerAs: 'adminlog',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })


        .state('circle_admin', {
            url: "/circle_admin",
            abstract: true,
            templateUrl: "views/circle/admin/admin.jsp",
            controller: 'adminCtrl',
            data: {
                authorizedRoles: [USER_ROLES.circleadmin]
            }
        })
        .state('circle_admin.home', {
            url: "/home",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/report.jsp",
                    controller: 'reportController',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })
        .state('circle_admin.UserManagement', {
            url: "/userCreation",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/userCreation.jsp",
                    controller: 'UserManageController',
                    controllerAs: 'user',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })

        .state('circle_admin.ConfigureSign', {
            url: "/ConfigureSign",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/ConfigureSign.jsp",
                    controller: 'ConfigureSignController',
                    controllerAs: 'configuresign',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }

        })

        .state('circle_maker.asciiLoader', {
            url: "/asciiLoad",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/admin/AsciiLoad.jsp",
                    controller: 'asciiLoadController',
                    controllerAs: 'asciiLoad',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })

        .state('circle_maker.miscWorklist', {
            url: "/MakerMiscWorklist",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/MakerMiscWorklist.jsp",
                    controller: 'miscMakerWorklistController',
                    controllerAs: 'MiscMakerWlist',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })


        .state('circle_admin.CheckerMiscWorklist', {
            url: "/CheckerMiscWorklist",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/CheckerMiscWorklist.jsp",
                    controller: 'MiscCheckerWorklistController',
                    controllerAs: 'MiscCheckerWlist',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })



        .state('circle_admin.uploadFile', {
            url: "/uploadFile",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/uploadFile.jsp",
                    controller: 'uploadFileController',
                    controllerAs: 'uploadFile',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })

        .state('circle_admin.mocreversal', {
            url: "/mocreversal",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/mocreversal.jsp",
                    controller: 'mocReversalController',
                    controllerAs: 'mocReversalC',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })


        .state('frt_admin.mocreversal', {
            url: "/mocreversal",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/circle/admin/mocreversal.jsp",
                    controller: 'mocReversalController',
                    controllerAs: 'mocReversalC',
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }
            }
        })






        .state('circle_admin.postReports', {
            url: "/postReports",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/postReports.jsp",
                    controller: 'postReportsController',
                    controllerAs: 'postReports',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })
        .state('circle_admin.preReports', {
            url: "/preReports",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/report3.jsp",
                    controller: 'reportController3',
                    controllerAs: 'report3',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })

        .state('circle_admin.sign', {
            url: "/sign",
            views: {
                'adminContent': {
                    templateUrl: "displayPDF.jsp",
                    controller: 'signController',
                    controllerAs: 'sign',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin, USER_ROLES.circleauditor]
                    }
                }
            }
        })
        .state('circle_admin.displaySignedPDF', {
            url: "/displaySignedPDF",
            views: {
                'adminContent': {
                    templateUrl: "displaySignedPDF.jsp",
                    controller: 'signedPDFController',
                    controllerAs: 'signedPDF',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin, USER_ROLES.circleauditor]
                    }
                }
            }
        })
        .state('circle_admin.acceptReport', {
            url: "/acceptReport",
            views: {
                'adminContent': {
                    templateUrl: "acceptReport.jsp",
                    controller: 'acceptController',
                    controllerAs: 'accept',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin, USER_ROLES.circleauditor]
                    }
                }
            }
        })
        .state('circle_admin.downloadAscii', {
            url: "/downloadAscii",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/downloadAscii.jsp",
                    controller: 'downloadAsciiFilesController',
                    controllerAs: 'dAsFiles',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })
        .state('circle_admin.home2', {
            url: "/home2",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/report2.jsp",
                    controller: 'reportController2',
                    controllerAs: 'report2',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })
        .state('circle_admin.misc', {
            url: "/misc",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/misc.jsp",
                    controller: 'miscController',
                    controllerAs: 'misc',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })
        .state('circle_admin.SH01', {
            url: "/SH01",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/SH01.jsp",
                    controller: 'sh01Controller',
                    controllerAs: 'sh01',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })
        .state('circle_admin.SH01_new', {
            url: "/SH01_new",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/viewreport.jsp",
                    controller: 'sh01ControllerNew',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })

        .state('circle_maker.MOCFreezed', {
            url: "/circle_mocFreezed",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/MOCFreezed.jsp",
                    controller: 'mocFreezedController',
                    controllerAs: 'mocFreezed',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })
        .state('circle_admin.MOCFreezed', {
            url: "/circle_mocFreezed",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/maker/MOCFreezed.jsp",
                    controller: 'mocFreezedController',
                    controllerAs: 'mocFreezed',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })
        .state('circle_maker.ignoredMOC', {
            url: "/circle_mocFreezed",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/ignoredMOC.jsp",
                    controller: 'mocFreezedController',
                    controllerAs: 'mocFreezed',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })

        .state('circle_maker.dicgc', {
            url: "/dicgcReport",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/DICGC.jsp",
                    controller: 'dicgcReportController',
                    controllerAs: 'dicgcReport',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })



        .state('circle_admin.SH05', {
            url: "/SH05",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/SH05.jsp",
                    controller: 'sh05Controller',
                    controllerAs: 'sh05',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })
        .state('circle_admin.SH04', {
            url: "/SH04",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/SH04_old.jsp",
                    controller: 'sh04Controller',
                    controllerAs: 'sh04',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })
        .state('circle_admin.SH04_view', {
            url: "/SH04_view",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/SH04.jsp",
                    controller: 'sh04ControllerView',
                    controllerAs: 'sh04_view',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })
        .state('circle_admin.SH03', {
            url: "/SH03",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/SH03.jsp",
                    controller: 'sh03Controller',
                    controllerAs: 'sh03',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })
        .state('circle_admin.view', {
            url: "/view",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/viewreport.jsp",
                    controller: 'viewController',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })
        .state('circle_admin.QB', {
            url: "/QB",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/quarterBranch.jsp",
                    controller: 'qbController',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })
        .state('circle_admin.viewReports', {
            url: "/viewReport",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/viewReports.jsp",
                    controller: 'viewReportsController',
                    controllerAs: 'view',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })
        .state('circle_admin.mocrejacc', {
            url: "/mocrejacc",
            views: {
                'adminContent': {
                    templateUrl: "views/circle/admin/mocrejacc.jsp",
                    controller: 'mocRejAccController',
                    controllerAs: 'mocRejAcc',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })



        .state('frt_admin.mocrejacc', {
            url: "/mocrejacc",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/circle/admin/mocrejacc.jsp",
                    controller: 'mocRejAccController',
                    controllerAs: 'mocRejAcc',
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin]
                    }
                }
            }
        })

        .state('circle_auditor', {
            url: "/circle_auditor",
            abstract: true,
            templateUrl: "views/circle/auditor/auditor.jsp",
            data: {
                authorizedRoles: [USER_ROLES.circleauditor]
            }
        })
        .state('circle_auditor.home', {
            url: "/home",
            views: {
                'auditorContent': {
                    templateUrl: "views/circle/auditor/auditorindex.jsp",
                    data: {
                        authorizedRoles: [USER_ROLES.circleauditor]
                    }
                }
            }
        })
        .state('circle_auditor.sign', {
            url: "/sign",
            views: {
                'auditorContent': {
                    templateUrl: "displayPDF.jsp",
                    controller: 'signController',
                    controllerAs: 'sign',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin, USER_ROLES.circleauditor]
                    }
                }
            }
        })
        .state('circle_auditor.userDetails', {
            url: "/userDetails/:id",
            views: {
                'auditorContent': {
                    templateUrl: "views/circle/admin/userDetails.jsp",
                    controller: 'userDetails',
                    controllerAs: 'bsp',
                    data: {
                        authorizedRoles: [USER_ROLES.circleauditor]
                    }
                }
            }
        })
        .state('circle_auditor.acceptReport', {
            url: "/acceptReport",
            views: {
                'auditorContent': {
                    templateUrl: "acceptReport.jsp",
                    controller: 'acceptController',
                    controllerAs: 'accept',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin, USER_ROLES.circleauditor]
                    }
                }
            }
        })
        .state('circle_auditor.postReports', {
            url: "/postReports",
            views: {
                'auditorContent': {
                    templateUrl: "views/circle/admin/postReports.jsp",
                    controller: 'postReportsController',
                    controllerAs: 'postReports',
                    data: {
                        authorizedRoles: [USER_ROLES.circleauditor]
                    }
                }
            }
        })
        .state('circle_auditor.downloadJrxmls', {
            url: "/downloadJrxmls",
            views: {
                'auditorContent': {
                    templateUrl: "views/circle/admin/downloadJrxmls.jsp",
                    controller: 'downloadJrxmls',
                    controllerAs: 'downloadJrxmls',
                    data: {
                        authorizedRoles: [USER_ROLES.circleauditor]
                    }
                }
            }
        })
        .state('circle_auditor.preReports', {
            url: "/preReports",
            views: {
                'auditorContent': {
                    templateUrl: "views/circle/admin/report3.jsp",
                    controller: 'reportController3',
                    controllerAs: 'report3',
                    data: {
                        authorizedRoles: [USER_ROLES.circleauditor]
                    }
                }
            }
        })

        .state('circlemaker_ifrs', {
            url: "/circle_Maker_IFRS",
            abstract: true,
            templateUrl: "views/circle/maker/makerIfrs.jsp",
            controller: 'makerCtrl',
            data: {
                authorizedRoles: [USER_ROLES.circlemaker]
            }
        })
        .state('circlechecker_ifrs', {
            url: "/Circle_ifrs",
            abstract: true,
            templateUrl: "views/circle/admin/checkerIfrs.jsp",
            controller: 'ifrsCtrl',
            data: {
                authorizedRoles: [USER_ROLES.circleadmin]
            }
        })
        .state('circlemaker_ifrs.home', {
            url: "/ifrs_worklist",
            views: {
                'makerIfrsContent': {
                    templateUrl: "views/circle/maker/makerWorklistIFRS.jsp",
                    controller: 'ifrsWorklistController',
                    controllerAs:'ifrsWorklist',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })

        .state('circlemaker_ifrs.report', {
            url: "/liabilities",
            views: {
                'makerIfrsContent': {
                    templateUrl: "views/circle/maker/ifrsLiabilities.jsp",
                    controller: 'ifrsLiabilitiesController',
                    controllerAs:'liabilities',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })

        .state('circlemaker_ifrs.downloadArchives', {
            url: "/Download",
            views: {
                'makerIfrsContent': {
                    templateUrl: "views/circle/maker/IFRSDownloadArchives.jsp",
                    controller: 'IFRSDownloadArchives',
                    controllerAs:'IFRSArchives',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })

        .state('circlechecker_ifrs.downloadArchives', {
            url: "/ArchiveDownloads",
            views: {
                'checkerifrsContent': {
                    templateUrl: "views/circle/maker/IFRSDownloadArchives.jsp",
                    controller: 'IFRSDownloadArchives',
                    controllerAs:'IFRSArchives',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })

        .state('ifrs_user', {
            url: "/ifrs",
            abstract: true,
            templateUrl: "views/ifrs/ifrsUser.jsp",
            controller: 'ifrsCtrl',
            data: {
                authorizedRoles: [USER_ROLES.ifrsUser]
            }
        })

        .state('ifrs_user.home', {
            url: "/IFRSuser_Home",
            views: {
                'ifrsContent': {
                    templateUrl: "views/ifrs/ifrsMakerIndex.jsp",
                    controller: 'ifrsUserWorklistController',
                    controllerAs:'ifrsUser',
                    data: {
                        authorizedRoles: [USER_ROLES.ifrsUser]
                    }
                }
            }
        })

        .state('ifrs_user.worklist', {
            url: "/ifrsuser_worklist",
            views: {
                'ifrsContent': {
                    templateUrl: "views/ifrs/IFRSWorklist.jsp",
                    controller: 'ifrsUserWorklistController',
                    controllerAs:'ifrsUser',
                    data: {
                        authorizedRoles: [USER_ROLES.ifrsUser]
                    }
                }
            }
        })
        .state('ifrs_user.configLiabilities', {
            url: "/ifrsuser_ReportConfig",
            views: {
                'ifrsContent': {
                    templateUrl: "views/ifrs/configLiabilities.jsp",
                    controller: 'ifrsconfigLiabilities',
                    controllerAs:'configLiabilities',
                    data: {
                        authorizedRoles: [USER_ROLES.ifrsUser]
                    }
                }
            }
        })
        .state('ifrs_user.ciclewisereport', {
            url: "/ifrsuser_circleWiseReports",
            views: {
                'ifrsContent': {
                    templateUrl: "views/ifrs/IFRSCircleWiseReport.jsp",
                    controller: 'ifrsUserWorklistController',
                    controllerAs:'ifrsUser',
                    data: {
                        authorizedRoles: [USER_ROLES.ifrsUser]
                    }
                }
            }
        })

        .state('ifrs_user.downloadJrxmls', {
            url: "/Download",
            views: {
                'ifrsContent': {
                    templateUrl: "views/circle/admin/downloadJrxmls.jsp",
                    controller: 'downloadJrxmls',
                    controllerAs: 'downloadJrxmls',
                    data: {
                        authorizedRoles: [USER_ROLES.ifrsUser]
                    }
                }
            }

        })

        .state('ifrs_user.archiveDownloads', {
            url: "/ifrsuser_Downloads",
            views: {
                'ifrsContent': {
                    templateUrl: "views/ifrs/archiveDownloadsIFRS.jsp",
                    controller: 'IFRSDownloadArchives',
                    controllerAs:'IFRSArchives',
                    data: {
                        authorizedRoles: [USER_ROLES.ifrsUser]
                    }
                }
            }
        })

        .state('circlechecker_ifrs.home', {
            url: "/ifrsworklist",
            views: {
                'checkerifrsContent': {
                    templateUrl: "views/circle/admin/checkerWorklistIFRS.jsp",
                    controller: 'ifrsWorklistController',
                    controllerAs:'ifrsWorklist',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })


        .state('circle_module', {
            url: "/circle_module",
            abstract: true,
            templateUrl: "views/circle/maker/circleMaker.jsp",
            controller: 'makerCtrl',
            data: {
                authorizedRoles: [USER_ROLES.circlemaker]
            }
        })

        .state('circle_module.module', {
            url: "/maker_home",
            views: {
                'moduleContent': {
                    templateUrl: "views/circle/maker/makerModule.jsp",
                    controller: 'makerModuleController',
                    controllerAs:'module',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })

        .state('circlechecker_module', {
            url: "/checker_module",
            abstract: true,
            templateUrl: "views/circle/admin/circleChecker.jsp",
            controller: 'adminCtrl',
            data: {
                authorizedRoles: [USER_ROLES.circleadmin]
            }
        })

        .state('circlechecker_module.module', {
            url: "/checker_home",
            views: {
                'checkerModule': {
                    templateUrl: "views/circle/admin/checkerModule.jsp",
                    controller: 'makerModuleController',
                    controllerAs:'module',
                    data: {
                        authorizedRoles: [USER_ROLES.circleadmin]
                    }
                }
            }
        })



        .state('circle_maker', {
            url: "/circle_maker",
            abstract: true,
            templateUrl: "views/circle/maker/maker.jsp",
            controller: 'makerCtrl',
            data: {
                authorizedRoles: [USER_ROLES.circlemaker]
            }
        })

        .state('circle_maker.home', {
            url: "/circle_home",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/makerindex.jsp",
                    controller: 'makerIndexController',
                    controllerAs:'makerIndex',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })


        .state('frt_maker.SCH10', {
            url: "/SCH10",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/circle/maker/Schedule10.jsp",
                    controller: 'SCH10controller',
                    controllerAs: 'SCH10',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

        .state('circle_maker.SC10', {
            url: "/SC10",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/SC10.jsp",
                    controller: 'SC10Controller',
                    controllerAs: 'sc10',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })


        .state('circle_maker.SC9C', {
            url: "/SC9C",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/SC9C.jsp",
                    controller: 'SC9Ccontroller',
                    controllerAs: 'sc9c',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })

        .state('circle_maker.SC9cMigration', {
            url: "/SC9cMigration",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/SC9cMigration.jsp",
                    controller: 'SC9cMigrationcontroller',
                    controllerAs: 'sc9cMigrate',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })


        .state('circle_maker.SC9B', {
            url: "/SC9B",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/SC9B.jsp",
                    controller: 'SC9Bcontroller',
                    controllerAs: 'sc9b',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })

        .state('circle_maker.SC09Supl', {
            url: "/SC09Supl",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/SC09Supl.jsp",
                    controller: 'SC9Suplcontroller',
                    controllerAs: 'sc9Supl',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })

        .state('circle_maker.QRC1', {
            url: "/SC09Supl",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/QRC1.jsp",
                    controller: 'QRC1',
                    controllerAs: 'qrc1',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })

        .state('circle_maker.QRC4', {
            url: "/QRC4",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/QRC4.jsp",
                    controller: 'QRC4',
                    controllerAs: 'qrc4',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })


        .state('circle_maker.QRC16', {
            url: "/QRC16",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/QRC16.jsp",
                    controller: 'QRC16',
                    controllerAs: 'qrc16',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })



        .state('circle_maker.SC9A', {
            url: "/SC9A",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/SC9A.jsp",
                    controller: 'SC9Acontroller',
                    controllerAs: 'SC9A',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })


        .state('circle_maker.downloadJrxmls', {
            url: "/downloadJrxmls",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/admin/downloadJrxmls.jsp",
                    controller: 'downloadJrxmls',
                    controllerAs: 'downloadJrxmls',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })
        .state('circle_maker.downloadController', {
            url: "/download",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/admin/downloadReports.jsp",
                    controller: 'downloadController',
                    controllerAs: 'download',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })


        .state('circle_maker.YSA', {
            url: "/YSA",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/YSA.jsp",
                    controller: 'YSAController',
                    controllerAs: 'ysa',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })
        .state('circle_maker.PNL-SUP', {
            url: "/PNL-SUP",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/PNL-SUP.jsp",
                    controller: 'pnl-sup-Controller',
                    controllerAs: 'plsup',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })
        .state('circle_maker.SH02', {
            url: "/SH02",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/SH02.jsp",
                    controller: 'sh02Controller',
                    controllerAs: 'sh02',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })
        .state('circle_maker.SC09', {
            url: "/SC09",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/SC09.jsp",
                    controller: 'sc09Controller',
                    controllerAs: 'sc09',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })
        .state('circle_maker.SHC01', {
            url: "/SHC01",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/SHC01.jsp",
                    controller: 'SHC01Controller',
                    controllerAs: 'shc01',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })
        .state('circle_maker.worklist', {
            url: "/worklist",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/worklist.jsp",
                    controller: 'worklistController',
                    controllerAs: 'worklist',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })
        .state('circle_maker.moccircle', {
            url: "/circle_moccircle",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/moccircle.jsp",
                    controller: 'mocCircleController',
                    controllerAs: 'mocCircle',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })

        .state('circle_maker.mocedit', {
            url: "/circle_mocedit",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/mocedit.jsp",
                    controller: 'mocEditController',
                    controllerAs: 'mocEdit',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })




        .state('frt_maker.moccircle', {
            url: "/frt_moccircle",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/circle/maker/moccircle.jsp",
                    controller: 'mocCircleController',
                    controllerAs: 'mocCircle',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })
        .state('frt_maker.mocedit', {
            url: "/frt_mocedit",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/circle/maker/mocedit.jsp",
                    controller: 'mocEditController',
                    controllerAs: 'mocEdit',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })



        .state('circle_maker.mocreversal', {
            url: "/circle_mocreversal",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/mocreversal.jsp",
                    controller: 'mocReversalController',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })










        .state('circle_maker.preReports', {
            url: "/preReports",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/admin/report3.jsp",
                    controller: 'reportController3',
                    controllerAs: 'report3',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })
        .state('circle_maker.ysasupl', {
            url: "/circle_ysasupl",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/ysasupl.jsp",
                    controller: 'ysaSuplController',
                    contollerAs: 'ysaSupl',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }

                }
            }
        })
        .state('circle_maker.view', {
            url: "/circle_makerview",
            views: {
                'makerContent': {
                    templateUrl: "views/circle/maker/viewreport.jsp",
                    controller: 'makerViewController',
                    controllerAs: 'makerView',
                    data: {
                        authorizedRoles: [USER_ROLES.circlemaker]
                    }
                }
            }
        })
        .state('super_maker', {
            url: "/super_maker",
            abstract: true,
            templateUrl: "views/super/maker/supermaker.jsp",
            data: {
                authorizedRoles: [USER_ROLES.supermaker]
            }
        })
        .state('super_maker.home', {
            url: "/home",
            views: {
                'supermakerContent': {
                    templateUrl: "views/super/maker/supermakerindex.jsp",
                    data: {
                        authorizedRoles: [USER_ROLES.supermaker]
                    }
                }
            }
        })
        .state('super_maker.upload', {
            url: "/upload",
            views: {
                'supermakerContent': {
                    templateUrl: "views/super/maker/UploadFile2.jsp",
                    controller: 'UploadFile2Controller',
                    controllerAs: 'UploadFile2',
                    data: {
                        authorizedRoles: [USER_ROLES.supermaker]
                    }
                }
            }
        })


        .state('super_maker.updatePassword', {
            url: "/updateIp",
            views: {
                'supermakerContent': {
                    templateUrl: "views/super/maker/encrypt.jsp",
                    controller: 'updateIp',
                    controllerAs: 'updateIp',
                    data: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                }
            }
        })


        .state('super_maker.UserModification', {
            url: "/userModification",
            views: {
                'supermakerContent': {
                    templateUrl: "views/circle/admin/userModification.jsp",
                    controller: 'UserManageController',
                    controllerAs: 'user',
                    data: {
                        authorizedRoles: [USER_ROLES.supermaker]
                    }
                }
            }
        })
        .state('super_maker.UserManagement', {
            url: "/UserManagement",
            views: {
                'supermakerContent': {
                    templateUrl: "views/circle/admin/userCreation.jsp",
                    controller: 'UserManageController',
                    controllerAs: 'user',
                    data: {
                        authorizedRoles: [USER_ROLES.supermaker]
                    }
                }
            }

        })

        .state('super_maker.branchManagement', {
            url: "/branchManagement",
            views: {
                'supermakerContent': {
                    templateUrl: "views/admin/branchManagement.jsp",
                    controller: 'branchManagementController',
                    controllerAs: 'branchManagement',
                    data: {
                        authorizedRoles: [USER_ROLES.supermaker]
                    }
                }
            }
        })
        .state('frt_maker.footer',{
            url:"/updateFooter",
            views:{
                'frtMakerContent':{
                    templateUrl:"views/frt/maker/updateFooter.jsp",
                    controller:'BsFooterController',
                    controllerAs:'BsFooter',
                    data:{
                        authorizedRoles:[USER_ROLES.frtmaker]
                    }
                }
            }
        })


        .state('super_maker.anotheradmin', {
            url: "/anotheradmin",
            views: {
                'supermakerContent': {
                    templateUrl: "views/super/admin/anotheradmin.jsp",
                    controller: 'anotheradminController',
                    controllerAs: 'anotheradmin',
                    data: {
                        authorizedRoles: [USER_ROLES.supermaker]
                    }
                }
            }
        })

        .state('super_admin', {
            url: "/super_admin",
            abstract: true,
            templateUrl: "views/super/admin/superadmin.jsp",
            data: {
                authorizedRoles: [USER_ROLES.supermaker]
            }
        })
        .state('super_admin.home', {
            url: "/home",
            views: {
                'superAdminContent': {
                    templateUrl: "views/super/admin/superadminindex.jsp"
                }
            }

        })

        .state('frt_admin.dashboard', {
            url: "/dashboard",
            views: {
                'frtAdminContent': {
                    templateUrl: "views/frt/admin/dashboard.jsp",
                    controller: 'dashboardController',
                    controllerAs: 'dash',
                    data: {
                        authorizedRoles: [USER_ROLES.frtadmin, USER_ROLES.frtmaker]
                    }
                }
            }

        })
        .state('gitc_user.dashboard', {
            url: "/dashboard",
            views: {
                'gitcUserContent': {
                    templateUrl: "views/super/maker/frtDashoard.jsp",
                    controller: 'DashboardController',
                    controllerAs: 'dashboard',
                    data: {
                        authorizedRoles: [USER_ROLES.gitcUser]
                    }
                }
            }

        })

        .state('gitc_user', {
            url: "/gitc_user",
            abstract: true,
            templateUrl: "views/gitc/gitcUser.jsp",
            data: {
                authorizedRoles: [USER_ROLES.gitcUser]
            }
        })

        .state('gitc_user.home', {
            url: "/home",
            views: {
                'gitcUserContent': {
                    templateUrl: "views/gitc/gitcuserindex.jsp"
                }
            }

        })

        .state('gitc_user.downloadJrxmls', {
            url: "/downloadJrxmls",
            views: {
                'gitcUserContent': {
                    templateUrl: "views/circle/admin/downloadJrxmls.jsp",
                    controller: 'downloadJrxmls',
                    controllerAs: 'downloadJrxmls',
                    data: {
                        authorizedRoles: [USER_ROLES.gitcUser]
                    }
                }
            }

        })

        .state('frt_maker.downloadJrxmls2', {
            url: "/downloadJrxmls2",
            views: {
                'frtMakerContent': {
                    templateUrl: "views/frt/downloadJrxmls2.jsp",
                    controller: 'downloadJrxmls2Controller',
                    controllerAs: 'downloadJrxmls2',
                    data: {
                        authorizedRoles: [USER_ROLES.frtmaker]
                    }
                }
            }
        })

    $urlRouterProvider.otherwise("login");


});


/*app.config(['$httpProvider',function($httpProvider){
    $httpProvider.interceptors.push('FCsrfTokenInterceptorService');
}]);*/
app.config(function ($httpProvider) {
    $httpProvider.interceptors.push([
        '$injector',
        function ($injector) {
            return $injector.get('AuthInterceptor');
        }
    ]);
    // AppSec Change Here-1
    // New Change for Additional Token Security- AppSec
    // START

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


    // END

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
                // AppSec Change (Comment log)
                // console.log("decryptedToken--"+JSON.stringify(decryptedToken));
                var userId =  decryptedToken.userId;

                /////////////////////
                var capacity =  decryptedToken.capacity;
                // AppSec Change (Comment log)
                // console.log("capacity--"+capacity);
                // AppSec Change (Comment log)
                // console.log("$location.path()--"+$location.path());
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

