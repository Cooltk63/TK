<script type="text/javascript">
    $(".decimal-2-places").numeric({ decimalPlaces: 2 });

    function check(){
        alert("called");
    }

</script>

<script src="./resources/js/multifreezer.js"></script>
<link rel="stylesheet" href="./resources/css/multifreezer.css">
<style>
    /*common*/
    #freezer-example { width: 100%; max-height: 1200px; overflow: hidden; margin: 1em auto; }
    #freezer-example .table th,#freezer-example .table td { white-space: nowrap; }
    /* #freezer-example .table th { outline: 1px solid crimson; background: #E74C3C; }
      #freezer-example .table thead th { outline: 1px solid gold; }*/
    #freezer-example .table col { width: 200px; }


</style>


<div class="wrapper">
    <div class="header header-filter" style="background-image: url('assets/img/bg2.jpeg');">
        <div class="container">
            <div class="row tim-row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="brand">
                        <h3 style="color: white;">Schedule 10</h3>
                    </div>
                </div>
            </div>

        </div>

    </div>

    <div class="main main-raised">




        <div class="section">

            <div class="container" ng-init="sc10.getInitialScreenData()">


                <!-- <div class="col-md-12" ><div class="row" > -->





                <form data-ng-submit="sc10.submitTen(sc10.row)" name="sc10Form" >
                    <input type="hidden" id="csrfPreventionSalt" value="${csrfPreventionSalt}"/>
                    <div id="freezer-example" style="margin:0"  >
                        <!-- table table-hover table-responsive no-padding dataTable no-footer 	<table  id="example1"class="table table-hover table-responsive table-bordered no-padding" style="width:4000px">
                        table table-condensed table-freeze-multi table-bordered-->




                        <table  id="example1"class="table table-condensed  table-hover  table-bordered no-padding dataTable table-freeze-multi"
                                data-scroll-height="650"
                                data-cols-number="2"  >
                            <colgroup>
                                <col style="width: 50px;background-color: white">
                                <col style="width: 600px;background-color: white">
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>

                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>
                                <col>


                            </colgroup>

                            <thead>



                            <tr >

                                <th style="text-align:center; width: 450px;" rowspan="2"><b>Sr.No</b></th>
                                <th style="text-align:center; width: 450px;" rowspan="2"><b>Particulars</b></th>
                                <th style="text-align:center; width: 280px;" colspan="5"><b>(A) FURNITURE & FITTINGS</b></th>
                                <th style="text-align:center; width: 280px;" colspan="10"><b>(B) MACHINERY & PLANT</b></th>
                                <th style="text-align:center; width: 450px;" rowspan="2"><b>Total Furniture & Fixtures <br>(A+B)</b></th>
                                <th style="text-align:center; width: 280px;" colspan="12"><b>(C) PREMISES</b></th>

                                <th style="text-align:center; width: 450px;" rowspan="2"><b>(D) Projects under <br>construction</b></th>
                                <th style="text-align:center; width: 450px;" rowspan="2"><b>Grand Total <br> (A + B + C + D)</b></th>


                            </tr>

                            <tr  style="white-space:nowrap;">
                                <%--<th><b>Sr.No</b></th>--%>
                                <%-- <th style="text-align:center; width: 700px;" ><b>Particulars</b></th>--%>
                                <th style="text-align:center; width: 280px;" ><b>i) At STCs & Staff Colleges <br>(For Local Head Office only)</b></th>
                                <th style="text-align:center; width: 280px;"><b>ii) At Officers'  Residences</b></th>
                                <th style="text-align:center; width: 280px;"><b>iii) At Other Premises</b></th>
                                <th style="text-align:center; width: 280px;"><b> iv) Electric Fittings <br>(include electric wiring,<br> switches, sockets, other<br> fittings & fans etc.)</b></th>
                                <th style="text-align:center; width: 280px;" ><b>TOTAL  (A)<br> (i+ii+iii+iv)</b></th>
                                <th style="text-align:center; width: 280px;"><b>i) Computer Hardware</b></th>
                                <th style="text-align:center; width: 280px;"><b>a. Computer Software <br>(forming integral part of<br> Hardware)</b></th>
                                <th style="text-align:center; width: 280px;"><b>b. Computer Software <br>(not forming integral <br>of Hardware)</b></th>
                                <th style="text-align:center; width: 280px;"><b>ii) Computer Software <br>Total (a+b)</b></th>
                                <th style="text-align:center; width: 280px;"><b>iii) Motor Vehicles </b></th>
                                <th style="text-align:center; width: 280px;"><b>a) At Officers' Residences</b></th>
                                <th style="text-align:center; width: 280px;"><b> b) At STCs <br>(For Local Head Office)</b></th>
                                <th style="text-align:center; width: 280px;"><b> c) At other Premises </b></th>
                                <th style="text-align:center; width: 280px;"><b>iv) Other Machinery & Plant <br>( a+b+c)</b> </th>
                                <th style="text-align:center; width: 280px;"><b>TOTAL <br> (B= i+ii+iii+iv)</b></th>

                                <!--new columns begin-->
                                <%--  <th style="text-align:center; width: 280px;"><b>Total Furniture & Fixtures <br>(A+B)</b></th>--%>
                                <th style="text-align:center; width: 280px;"><b>(a) Land (Not Revalued):<br> Cost</b></th>
                                <th style="text-align:center; width: 280px;"><b>(b) Land (Revalued): <br>Cost</b></th>
                                <th style="text-align:center; width: 280px;"><b>(c) Land (Revalued): <br>Enhancement due to <br>Revaluation</b></th>
                                <th style="text-align:center; width: 280px;"><b>(d) Office Building <br>(Not revalued): Cost </b></th>
                                <th style="text-align:center; width: 280px;"><b>(e) Office Building <br>(Revalued): Cost </b></th>
                                <th style="text-align:center; width: 280px;"><b>(f) Office Building <br>(Revalued): Enhancement <br>due to Revaluation</b></th>
                                <th style="text-align:center; width: 280px;"><b>(g) Residential Building <br>(Not revalued): Cost</b></th>
                                <th style="text-align:center; width: 280px;"><b>(h) Residential Building <br>(Revalued): Cost</b></th>
                                <th style="text-align:center; width: 280px;"><b>(i) Residential Building <br>(Revalued): Enhancement <br>due to Revaluation</b></th>
                                <th style="text-align:center; width: 280px;"><b>(j) Premises Total <br>(a+b+d+e+g+h)</b></th>
                                <th style="text-align:center; width: 280px;"><b>(k) Revaluation Total <br>(c+f+i)</b></th>
                                <th style="text-align:center; width: 280px;"><b>TOTAL <br>(C=j+k)</b></th>
                                <!--new columns  End-->


                                <%--<th style="text-align:center; width: 280px;"><b>Land</b> </br> (i)</th>
                                 <th style="text-align:center; width: 280px;"><b>Office <br>Building</b> </br>(ii)</th>
                                <th style="text-align:center; width: 280px;"><b>Residential <br>Quarters</b> </br> (iii)</th>
                                 <th style="text-align:center; width: 280px;"><b>TOTAL</b> </br>(i+ii+iii) {C}</th>--%>

                                <%--<th style="text-align:center; width: 280px;"><b>(D) Projects under <br>construction</b></th>
                                <th style="text-align:center; width: 280px;"><b>Grand Total <br> (A + B + C + D)</b></th>--%>

                            </tr>

                            </thead>

                            <tbody>


                            <tr>
                                <td style="text-align:left"><b>A</b></td>
                                <td><b>Total Original Cost / Revalued Value upto the end of previous year i.e. 31st March {{sc10.year1}}</b></td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff1" style="text-align:right;" name="stcNstaff1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA1" style="text-align:right;" name="offResidenceA1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA1" style="text-align:right;" name="otherPremisesA1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting1" style="text-align:right;" name="electricFitting1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA1" style="text-align:right;" name="totalA1" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers1" style="text-align:right;" name="computers1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt1" style="text-align:right;" name="compSoftwareInt1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint1" style="text-align:right;" name="compSoftwareNonint1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal1" style="text-align:right;" name="compSoftwareTotal1" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor1" style="text-align:right;" name="motor1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB1" style="text-align:right;" name="offResidenceB1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho1" style="text-align:right;" name="stcLho1" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB1" style="text-align:right;" name="otherPremisesB1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant1" style="text-align:right;" name="OtherMachineryPlant1" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB1" style="text-align:right;" name="totalB1" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>



                                <!--new col begin-->
                                <td><input type="text"  ng-model="sc10.row.totalFurnFix1" style="text-align:right;" name="totalFurnFix1" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev1" style="text-align:right;" name="landNotRev1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev1" style="text-align:right;" name="landRev1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh1" style="text-align:right;" name="landRevEnh1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev1" style="text-align:right;" name="offBuildNotRev1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev1" style="text-align:right;" name="offBuildRev1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh1" style="text-align:right;" name="offBuildRevEnh1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev1" style="text-align:right;" name="residQuartNotRev1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev1" style="text-align:right;" name="residQuartRev1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh1" style="text-align:right;" name="residQuartRevEnh1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal1" style="text-align:right;" name="premisTotal1" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal1" style="text-align:right;" name="revtotal1" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <!--new col End-->

                                <%--<td><input type="text"  ng-model="sc10.row.land1" style="text-align:right;" name="land1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.officeBuilding1" style="text-align:right;" name="officeBuilding1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residentialQuarters1" style="text-align:right;" name="residentialQuarters1" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC1" style="text-align:right;" name="totalC1" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons1" style="text-align:right;" name="premisesUnderCons1" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal1" style="text-align:right;" name="grandTotal1" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>


                            <tr>
                                <td></td>
                                <td><b>Addition</b></td>
                                <td colspan="30"></td></tr>

                            <%-- <tr>
                             <td class="text-center" >I.</td>
                             <td>Assets taken over from eABs & BMB on 01.04.17</td>
                                 <td><input type="text"  ng-model="sc10.row.stcNstaff2" style="text-align:right; " name="stcNstaff2" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.offResidenceA2" style="text-align:right;" name="offResidenceA2" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.otherPremisesA2" style="text-align:right;" name="otherPremisesA2" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.electricFitting2" style="text-align:right;" name="electricFitting2" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.totalA2" style="text-align:right;" name="totalA2" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                              <td><input type="text"  ng-model="sc10.row.computers2" style="text-align:right;" name="computers2" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.compSoftwareInt2" style="text-align:right;" name="compSoftwareInt2" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.compSoftwareNonint2" style="text-align:right;" name="compSoftwareNonint2" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.compSoftwareTotal2" style="text-align:right;" name="compSoftwareTotal2" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                              <td><input type="text"  ng-model="sc10.row.motor2" style="text-align:right;" name="motor2" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.offResidenceB2" style="text-align:right;" name="offResidenceB2" maxlength="18" class="form-control decimal-2-places"  /></td>
                              <td><input type="text"  ng-model="sc10.row.stcLho2" style="text-align:right;" name="stcLho2" maxlength="18" class="form-control decimal-2-places"  /></td>

                               <td><input type="text"  ng-model="sc10.row.otherPremisesB2" style="text-align:right;" name="otherPremisesB2" maxlength="18" class="form-control decimal-2-places"  /></td>
                              <td><input type="text"  ng-model="sc10.row.otherMachineryPlant2" style="text-align:right;" name="OtherMachineryPlant2" maxlength="18" class="form-control decimal-2-places"  readonly="true"/></td>
                              <td><input type="text"  ng-model="sc10.row.totalB2" style="text-align:right;" name="totalB2" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>
                              <td><input type="text"  ng-model="sc10.row.land2" style="text-align:right;" name="land2" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.officeBuilding2" style="text-align:right;" name="officeBuilding2" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.residentialQuarters2" style="text-align:right;" name="residentialQuarters2" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.totalC2" style="text-align:right;" name="totalC2" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                              <td><input type="text"  ng-model="sc10.row.premisesUnderCons2" style="text-align:right;" name="premisesUnderCons2" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.grandTotal2" style="text-align:right;" name="grandTotal2" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>




                             </tr>--%>








                            <tr>
                                <td class="text-center">a.</td>
                                <td>Original cost of items put to use during the year:</td>
                                <td colspan="30"></td></tr>


                            <tr>
                                <td style="text-align:right">(i)</td>
                                <td>{{sc10.row.particulars3}}
                                    <input type="hidden" ng-model="sc10.row.particulars3" name="particulars3">
                                </td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff3" style="text-align:right; " name="stcNstaff3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA3" style="text-align:right;" name="offResidenceA3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA3" style="text-align:right;" name="otherPremisesA3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting3" style="text-align:right;" name="electricFitting3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA3" style="text-align:right;" name="totalA3" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers3" style="text-align:right;" name="computers3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt3" style="text-align:right;" name="compSoftwareInt3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint3" style="text-align:right;" name="compSoftwareNonint3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal3" style="text-align:right;" name="compSoftwareTotal3" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor3" style="text-align:right;" name="motor3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB3" style="text-align:right;" name="offResidenceB3" maxlength="18" class="form-control decimal-2-places"  /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho3" style="text-align:right;" name="stcLho3" maxlength="18" class="form-control decimal-2-places"  /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB3" style="text-align:right;" name="otherPremisesB3" maxlength="18" class="form-control decimal-2-places"  /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant3" style="text-align:right;" name="OtherMachineryPlant3" maxlength="18" class="form-control decimal-2-places"  readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB3" style="text-align:right;" name="totalB3" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>

                                <td><input type="text"  ng-model="sc10.row.totalFurnFix3" style="text-align:right;" name="totalFurnFix3" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev3" style="text-align:right;" name="landNotRev3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev3" style="text-align:right;" name="landRev3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh3" style="text-align:right;" name="landRevEnh3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev3" style="text-align:right;" name="offBuildNotRev3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev3" style="text-align:right;" name="offBuildRev3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh3" style="text-align:right;" name="offBuildRevEnh3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev3" style="text-align:right;" name="residQuartNotRev3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev3" style="text-align:right;" name="residQuartRev3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh3" style="text-align:right;" name="residQuartRevEnh3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal3" style="text-align:right;" name="premisTotal3" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal3" style="text-align:right;" name="revtotal3" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <%-- <td><input type="text"  ng-model="sc10.row.land3" style="text-align:right;" name="land3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding3" style="text-align:right;" name="officeBuilding3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters3" style="text-align:right;" name="residentialQuarters3" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC3" style="text-align:right;" name="totalC3" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons3" style="text-align:right;" name="premisesUnderCons3" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal3" style="text-align:right;" name="grandTotal3" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>




                            <tr style="white-space:nowrap;">
                                <td style="text-align:right">(ii)</td>
                                <td>{{sc10.row.particulars4}}
                                    <input type="hidden" ng-model="sc10.row.particulars4" name="particulars4">
                                </td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff4" style="text-align:right;" name="stcNstaff4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA4" style="text-align:right;" name="offResidenceA4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA4" style="text-align:right;" name="otherPremisesA4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting4" style="text-align:right;" name="electricFitting4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA4" style="text-align:right;" name="totalA4" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers4" style="text-align:right;" name="computers4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt4" style="text-align:right;" name="compSoftwareInt4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint4" style="text-align:right;" name="compSoftwareNonint4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal4" style="text-align:right;" name="compSoftwareTotal4" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor4" style="text-align:right;" name="motor4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB4" style="text-align:right;" name="offResidenceB4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho4" style="text-align:right;" name="stcLho4" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB4" style="text-align:right;" name="otherPremisesB4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant4" style="text-align:right;" name="OtherMachineryPlant4" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB4" style="text-align:right;" name="totalB4" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>

                                <td><input type="text"  ng-model="sc10.row.totalFurnFix4" style="text-align:right;" name="totalFurnFix4" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev4" style="text-align:right;" name="landNotRev4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev4" style="text-align:right;" name="landRev4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh4" style="text-align:right;" name="landRevEnh4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev4" style="text-align:right;" name="offBuildNotRev4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev4" style="text-align:right;" name="offBuildRev4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh4" style="text-align:right;" name="offBuildRevEnh4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev4" style="text-align:right;" name="residQuartNotRev4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev4" style="text-align:right;" name="residQuartRev4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh4" style="text-align:right;" name="residQuartRevEnh4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal4" style="text-align:right;" name="premisTotal4" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal4" style="text-align:right;" name="revtotal4" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%--  <td><input type="text"  ng-model="sc10.row.land4" style="text-align:right;" name="land4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                  <td><input type="text"  ng-model="sc10.row.officeBuilding4" style="text-align:right;" name="officeBuilding4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                  <td><input type="text"  ng-model="sc10.row.residentialQuarters4" style="text-align:right;" name="residentialQuarters4" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC4" style="text-align:right;" name="totalC4" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons4" style="text-align:right;" name="premisesUnderCons4" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal4" style="text-align:right;" name="grandTotal4" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>



                            <tr>
                                <td class="text-center">b.</td>
                                <td>Increase in value of Fixed Assets due to Current Revaluation</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff36" style="text-align:right;" name="stcNstaff36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA36" style="text-align:right;" name="offResidenceA36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA36" style="text-align:right;" name="otherPremisesA36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting36" style="text-align:right;" name="electricFitting36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA36" style="text-align:right;" name="totalA36" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers36" style="text-align:right;" name="computers36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt36" style="text-align:right;" name="compSoftwareInt36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint36" style="text-align:right;" name="compSoftwareNonint36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal36" style="text-align:right;" name="compSoftwareTotal36" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor36" style="text-align:right;" name="motor36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB36" style="text-align:right;" name="offResidenceB36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho36" style="text-align:right;" name="stcLho36" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB36" style="text-align:right;" name="otherPremisesB36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant36" style="text-align:right;" name="OtherMachineryPlant36" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB36" style="text-align:right;" name="totalB36" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix36" style="text-align:right;" name="totalFurnFix36" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev36" style="text-align:right;" name="landNotRev36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev36" style="text-align:right;" name="landRev36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh36" style="text-align:right;" name="landRevEnh36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev36" style="text-align:right;" name="offBuildNotRev36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev36" style="text-align:right;" name="offBuildRev36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh36" style="text-align:right;" name="offBuildRevEnh36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev36" style="text-align:right;" name="residQuartNotRev36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev36" style="text-align:right;" name="residQuartRev36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh36" style="text-align:right;" name="residQuartRevEnh36" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal36" style="text-align:right;" name="premisTotal36" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal36" style="text-align:right;" name="revtotal36" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land5" style="text-align:right;" name="land5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding5" style="text-align:right;" name="officeBuilding5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters5" style="text-align:right;" name="residentialQuarters5" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC36" style="text-align:right;" name="totalC36" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons36" style="text-align:right;" name="premisesUnderCons5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal36" style="text-align:right;" name="grandTotal36" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>

                            <tr>
                                <td class="text-center">c.</td>
                                <td>Original cost of items transferred from other Circles/Groups/CC Departments</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff5" style="text-align:right;" name="stcNstaff5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA5" style="text-align:right;" name="offResidenceA5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA5" style="text-align:right;" name="otherPremisesA5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting5" style="text-align:right;" name="electricFitting5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA5" style="text-align:right;" name="totalA5" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers5" style="text-align:right;" name="computers5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt5" style="text-align:right;" name="compSoftwareInt5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint5" style="text-align:right;" name="compSoftwareNonint5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal5" style="text-align:right;" name="compSoftwareTotal5" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor5" style="text-align:right;" name="motor5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB5" style="text-align:right;" name="offResidenceB5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho5" style="text-align:right;" name="stcLho5" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB5" style="text-align:right;" name="otherPremisesB5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant5" style="text-align:right;" name="OtherMachineryPlant5" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB5" style="text-align:right;" name="totalB5" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix5" style="text-align:right;" name="totalFurnFix5" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev5" style="text-align:right;" name="landNotRev5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev5" style="text-align:right;" name="landRev5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh5" style="text-align:right;" name="landRevEnh5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev5" style="text-align:right;" name="offBuildNotRev5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev5" style="text-align:right;" name="offBuildRev5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh5" style="text-align:right;" name="offBuildRevEnh5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev5" style="text-align:right;" name="residQuartNotRev5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev5" style="text-align:right;" name="residQuartRev5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh5" style="text-align:right;" name="residQuartRevEnh5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal5" style="text-align:right;" name="premisTotal5" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal5" style="text-align:right;" name="revtotal5" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land5" style="text-align:right;" name="land5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding5" style="text-align:right;" name="officeBuilding5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters5" style="text-align:right;" name="residentialQuarters5" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC5" style="text-align:right;" name="totalC5" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons5" style="text-align:right;" name="premisesUnderCons5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal5" style="text-align:right;" name="grandTotal5" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>



                            <tr>
                                <td class="text-center">d.</td>
                                <td>Original cost of items transferred from other branches of the same Circle</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff6" style="text-align:right;" name="stcNstaff6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA6" style="text-align:right;" name="offResidenceA6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA6" style="text-align:right;" name="otherPremisesA6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting6" style="text-align:right;" name="electricFitting6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA6" style="text-align:right;" name="totalA6" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers6" style="text-align:right;" name="computers6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt6" style="text-align:right;" name="compSoftwareInt6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint6" style="text-align:right;" name="compSoftwareNonint6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal6" style="text-align:right;" name="compSoftwareTotal6" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor6" style="text-align:right;" name="motor6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB6" style="text-align:right;" name="offResidenceB6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho6" style="text-align:right;" name="stcLho6" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB6" style="text-align:right;" name="otherPremisesB6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant6" style="text-align:right;" name="OtherMachineryPlant6" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB6" style="text-align:right;" name="totalB6" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>

                                <td><input type="text"  ng-model="sc10.row.totalFurnFix6" style="text-align:right;" name="totalFurnFix6" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev6" style="text-align:right;" name="landNotRev6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev6" style="text-align:right;" name="landRev6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh6" style="text-align:right;" name="landRevEnh6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev6" style="text-align:right;" name="offBuildNotRev6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev6" style="text-align:right;" name="offBuildRev6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh6" style="text-align:right;" name="offBuildRevEnh6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev6" style="text-align:right;" name="residQuartNotRev6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev6" style="text-align:right;" name="residQuartRev6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh6" style="text-align:right;" name="residQuartRevEnh6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal6" style="text-align:right;" name="premisTotal6" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal6" style="text-align:right;" name="revtotal6" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land6" style="text-align:right;" name="land6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding6" style="text-align:right;" name="officeBuilding6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters6" style="text-align:right;" name="residentialQuarters6" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC6" style="text-align:right;" name="totalC6" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons6" style="text-align:right;" name="premisesUnderCons6" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal6" style="text-align:right;" name="grandTotal6" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>




                            <tr>
                                <td class="text-center">I</td>
                                <td><b>Total [a(i)+a(ii)+b+c+d]</b></td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff7" style="text-align:right;" name="stcNstaff7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA7" style="text-align:right;" name="offResidenceA7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA7" style="text-align:right;" name="otherPremisesA7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting7" style="text-align:right;" name="electricFitting7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalA7" style="text-align:right;" name="totalA7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers7" style="text-align:right;" name="computers7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt7" style="text-align:right;" name="compSoftwareInt7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint7" style="text-align:right;" name="compSoftwareNonint7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal7" style="text-align:right;" name="compSoftwareTotal7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor7" style="text-align:right;" name="motor7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB7" style="text-align:right;" name="offResidenceB7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho7" style="text-align:right;" name="stcLho7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB7" style="text-align:right;" name="otherPremisesB7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant7" style="text-align:right;" name="OtherMachineryPlant7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB7" style="text-align:right;" name="totalB7" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>

                                <td><input type="text"  ng-model="sc10.row.totalFurnFix7" style="text-align:right;" name="totalFurnFix7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev7" style="text-align:right;" name="landNotRev7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.landRev7" style="text-align:right;" name="landRev7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh7" style="text-align:right;" name="landRevEnh7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev7" style="text-align:right;" name="offBuildNotRev7" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev7" style="text-align:right;" name="offBuildRev7" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh7" style="text-align:right;" name="offBuildRevEnh7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev7" style="text-align:right;" name="residQuartNotRev7" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev7" style="text-align:right;" name="residQuartRev7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh7" style="text-align:right;" name="residQuartRevEnh7" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal7" style="text-align:right;" name="premisTotal7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal7" style="text-align:right;" name="revtotal7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%--  <td><input type="text"  ng-model="sc10.row.land7" style="text-align:right;" name="land7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                  <td><input type="text"  ng-model="sc10.row.officeBuilding7" style="text-align:right;" name="officeBuilding7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                  <td><input type="text"  ng-model="sc10.row.residentialQuarters7" style="text-align:right;" name="residentialQuarters7" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC7" style="text-align:right;" name="totalC7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons7" style="text-align:right;" name="premisesUnderCons7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal7" style="text-align:right;" name="grandTotal7" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>



                            <tr>
                                <td></td>
                                <td><b>Deduction</b></td>
                                <td  colspan="30"></td></tr>


                            <tr>
                                <td style="text-align:right">(i)</td>
                                <td>Short Valuation charged to Revaluation Reserve due to Current Downward Revaluation</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff37" style="text-align:right;" name="stcNstaff37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA37" style="text-align:right;" name="offResidenceA37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA37" style="text-align:right;" name="otherPremisesA37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting37" style="text-align:right;" name="electricFitting37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA37" style="text-align:right;" name="totalA37" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers37" style="text-align:right;" name="computers37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt37" style="text-align:right;" name="compSoftwareInt37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint37" style="text-align:right;" name="compSoftwareNonint37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal37" style="text-align:right;" name="compSoftwareTotal37" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor37" style="text-align:right;" name="motor37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB37" style="text-align:right;" name="offResidenceB37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho37" style="text-align:right;" name="stcLho37" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB37" style="text-align:right;" name="otherPremisesB37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant37" style="text-align:right;" name="OtherMachineryPlant37" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB37" style="text-align:right;" name="totalB37" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix37" style="text-align:right;" name="totalFurnFix37" maxlength="18" class="form-control decimal-2-places"  readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev37" style="text-align:right;" name="landNotRev37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev37" style="text-align:right;" name="landRev37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh37" style="text-align:right;" name="landRevEnh37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev37" style="text-align:right;" name="offBuildNotRev37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev37" style="text-align:right;" name="offBuildRev37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh37" style="text-align:right;" name="offBuildRevEnh37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev37" style="text-align:right;" name="residQuartNotRev37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev37" style="text-align:right;" name="residQuartRev37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh37" style="text-align:right;" name="residQuartRevEnh37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal37" style="text-align:right;" name="premisTotal37" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal37" style="text-align:right;" name="revtotal37" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land37" style="text-align:right;" name="land37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding37" style="text-align:right;" name="officeBuilding37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters37" style="text-align:right;" name="residentialQuarters37" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC37" style="text-align:right;" name="totalC37" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons37" style="text-align:right;" name="premisesUnderCons37" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal37" style="text-align:right;" name="grandTotal37" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>



                            <tr>
                                <td style="text-align:right">(ii)</td>
                                <td>Original cost of items sold/ discarded during the year</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff9" style="text-align:right;" name="stcNstaff9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA9" style="text-align:right;" name="offResidenceA9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA9" style="text-align:right;" name="otherPremisesA9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting9" style="text-align:right;" name="electricFitting9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA9" style="text-align:right;" name="totalA9" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers9" style="text-align:right;" name="computers9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt9" style="text-align:right;" name="compSoftwareInt9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint9" style="text-align:right;" name="compSoftwareNonint9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal9" style="text-align:right;" name="compSoftwareTotal9" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor9" style="text-align:right;" name="motor9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB9" style="text-align:right;" name="offResidenceB9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho9" style="text-align:right;" name="stcLho9" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB9" style="text-align:right;" name="otherPremisesB9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant9" style="text-align:right;" name="OtherMachineryPlant9" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB9" style="text-align:right;" name="totalB9" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix9" style="text-align:right;" name="totalFurnFix9" maxlength="18" class="form-control decimal-2-places"  readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev9" style="text-align:right;" name="landNotRev9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev9" style="text-align:right;" name="landRev9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh9" style="text-align:right;" name="landRevEnh9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev9" style="text-align:right;" name="offBuildNotRev9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev9" style="text-align:right;" name="offBuildRev9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh9" style="text-align:right;" name="offBuildRevEnh9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev9" style="text-align:right;" name="residQuartNotRev9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev9" style="text-align:right;" name="residQuartRev9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh9" style="text-align:right;" name="residQuartRevEnh9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal9" style="text-align:right;" name="premisTotal9" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal9" style="text-align:right;" name="revtotal9" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land9" style="text-align:right;" name="land9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding9" style="text-align:right;" name="officeBuilding9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters9" style="text-align:right;" name="residentialQuarters9" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC9" style="text-align:right;" name="totalC9" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons9" style="text-align:right;" name="premisesUnderCons9" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal9" style="text-align:right;" name="grandTotal9" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>

                            <!--New Row begin-->
                            <tr>
                                <td style="text-align:right">(iii)</td>
                                <td>Projects under construction capitalised during the year</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff33" style="text-align:right;" name="stcNstaff33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA33" style="text-align:right;" name="offResidenceA33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA33" style="text-align:right;" name="otherPremisesA33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting33" style="text-align:right;" name="electricFitting33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA33" style="text-align:right;" name="totalA33" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers33" style="text-align:right;" name="computers33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt33" style="text-align:right;" name="compSoftwareInt33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint33" style="text-align:right;" name="compSoftwareNonint33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal33" style="text-align:right;" name="compSoftwareTotal33" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor33" style="text-align:right;" name="motor33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB33" style="text-align:right;" name="offResidenceB33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho33" style="text-align:right;" name="stcLho33" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB33" style="text-align:right;" name="otherPremisesB33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant33" style="text-align:right;" name="OtherMachineryPlant33" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB33" style="text-align:right;" name="totalB33" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix33" style="text-align:right;" name="totalFurnFix33" maxlength="18" class="form-control decimal-2-places"  readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev33" style="text-align:right;" name="landNotRev33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev33" style="text-align:right;" name="landRev33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh33" style="text-align:right;" name="landRevEnh33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev33" style="text-align:right;" name="offBuildNotRev33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev33" style="text-align:right;" name="offBuildRev33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh33" style="text-align:right;" name="offBuildRevEnh33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev33" style="text-align:right;" name="residQuartNotRev33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev33" style="text-align:right;" name="residQuartRev33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh33" style="text-align:right;" name="residQuartRevEnh33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal33" style="text-align:right;" name="premisTotal33" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal33" style="text-align:right;" name="revtotal33" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land33" style="text-align:right;" name="land33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding33" style="text-align:right;" name="officeBuilding33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters33" style="text-align:right;" name="residentialQuarters33" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC33" style="text-align:right;" name="totalC33" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons33" style="text-align:right;" name="premisesUnderCons33" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal33" style="text-align:right;" name="grandTotal33" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>

                            <!--New Row End-->



                            <tr>
                                <td style="text-align:right">(iv)</td>
                                <td>Original cost of items transferred to other Circles/Groups/CC  Departments</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff10" style="text-align:right;" name="stcNstaff10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA10" style="text-align:right;" name="offResidenceA10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA10" style="text-align:right;" name="otherPremisesA10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting10" style="text-align:right;" name="electricFitting10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA10" style="text-align:right;" name="totalA10" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers10" style="text-align:right;" name="computers10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt10" style="text-align:right;" name="compSoftwareInt10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint10" style="text-align:right;" name="compSoftwareNonint10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal10" style="text-align:right;" name="compSoftwareTotal10" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor10" style="text-align:right;" name="motor10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB10" style="text-align:right;" name="offResidenceB10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho10" style="text-align:right;" name="stcLho10" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB10" style="text-align:right;" name="otherPremisesB10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant10" style="text-align:right;" name="OtherMachineryPlant10" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB10" style="text-align:right;" name="totalB10" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix10" style="text-align:right;" name="totalFurnFix10" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev10" style="text-align:right;" name="landNotRev10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev10" style="text-align:right;" name="landRev10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh10" style="text-align:right;" name="landRevEnh10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev10" style="text-align:right;" name="offBuildNotRev10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev10" style="text-align:right;" name="offBuildRev10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh10" style="text-align:right;" name="offBuildRevEnh10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev10" style="text-align:right;" name="residQuartNotRev10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev10" style="text-align:right;" name="residQuartRev10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh10" style="text-align:right;" name="residQuartRevEnh10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal10" style="text-align:right;" name="premisTotal10" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal10" style="text-align:right;" name="revtotal10" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land10" style="text-align:right;" name="land10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding10" style="text-align:right;" name="officeBuilding10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters10" style="text-align:right;" name="residentialQuarters10" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC10" style="text-align:right;" name="totalC10" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons10" style="text-align:right;" name="premisesUnderCons10" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal10" style="text-align:right;" name="grandTotal10" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>




                            <tr>
                                <td style="text-align:right">(v)</td>
                                <td>Original cost of items transferred to other branches in the same circle</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff11" style="text-align:right;" name="stcNstaff11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA11" style="text-align:right;" name="offResidenceA11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA11" style="text-align:right;" name="otherPremisesA11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting11" style="text-align:right;" name="electricFitting11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA11" style="text-align:right;" name="totalA11" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers11" style="text-align:right;" name="computers11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt11" style="text-align:right;" name="compSoftwareInt11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint11" style="text-align:right;" name="compSoftwareNonint11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal11" style="text-align:right;" name="compSoftwareTotal11" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor11" style="text-align:right;" name="motor11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB11" style="text-align:right;" name="offResidenceB11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho11" style="text-align:right;" name="stcLho11" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB11" style="text-align:right;" name="otherPremisesB11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant11" style="text-align:right;" name="OtherMachineryPlant11" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB11" style="text-align:right;" name="totalB11" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix11" style="text-align:right;" name="totalFurnFix11" maxlength="18" class="form-control decimal-2-places"  readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev11" style="text-align:right;" name="landNotRev11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev11" style="text-align:right;" name="landRev11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh11" style="text-align:right;" name="landRevEnh11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev11" style="text-align:right;" name="offBuildNotRev11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev11" style="text-align:right;" name="offBuildRev11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh11" style="text-align:right;" name="offBuildRevEnh11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev11" style="text-align:right;" name="residQuartNotRev11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev11" style="text-align:right;" name="residQuartRev11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh11" style="text-align:right;" name="residQuartRevEnh11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal11" style="text-align:right;" name="premisTotal11" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal11" style="text-align:right;" name="revtotal11" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land11" style="text-align:right;" name="land11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding11" style="text-align:right;" name="officeBuilding11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters11" style="text-align:right;" name="residentialQuarters11" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC11" style="text-align:right;" name="totalC11" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons11" style="text-align:right;" name="premisesUnderCons11" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal11" style="text-align:right;" name="grandTotal11" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>




                            <tr>
                                <td class="text-center">II</td>
                                <td><b>Total (i+ii+iii+iv+v)</b></td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff12" style="text-align:right;" name="stcNstaff12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA12" style="text-align:right;" name="offResidenceA12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA12" style="text-align:right;" name="otherPremisesA12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting12" style="text-align:right;" name="electricFitting12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalA12" style="text-align:right;" name="totalA12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers12" style="text-align:right;" name="computers12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt12" style="text-align:right;" name="compSoftwareInt12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint12" style="text-align:right;" name="compSoftwareNonint12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal12" style="text-align:right;" name="compSoftwareTotal12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor12" style="text-align:right;" name="motor12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB12" style="text-align:right;" name="offResidenceB12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho12" style="text-align:right;" name="stcLho12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB12" style="text-align:right;" name="otherPremisesB12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant12" style="text-align:right;" name="OtherMachineryPlant12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB12" style="text-align:right;" name="totalB12" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix12" style="text-align:right;" name="totalFurnFix12" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev12" style="text-align:right;" name="landNotRev12" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev12" style="text-align:right;" name="landRev12" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh12" style="text-align:right;" name="landRevEnh12" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev12" style="text-align:right;" name="offBuildNotRev12" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev12" style="text-align:right;" name="offBuildRev12" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh12" style="text-align:right;" name="offBuildRevEnh12" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev12" style="text-align:right;" name="residQuartNotRev12" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev12" style="text-align:right;" name="residQuartRev12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh12" style="text-align:right;" name="residQuartRevEnh12" maxlength="18" class="form-control decimal-2-places"readonly="true"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal12" style="text-align:right;" name="premisTotal12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal12" style="text-align:right;" name="revtotal12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%--  <td><input type="text"  ng-model="sc10.row.land12" style="text-align:right;" name="land12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                  <td><input type="text"  ng-model="sc10.row.officeBuilding12" style="text-align:right;" name="officeBuilding12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                  <td><input type="text"  ng-model="sc10.row.residentialQuarters12" style="text-align:right;" name="residentialQuarters12" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC12" style="text-align:right;" name="totalC12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons12" style="text-align:right;" name="premisesUnderCons12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal12" style="text-align:right;" name="grandTotal12" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>



                            <tr>
                                <td style="text-align:left"><b>B</b></td>
                                <td><b> Net Addition (I-II)</b></td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff13" style="text-align:right;" name="stcNstaff13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA13" style="text-align:right;" name="offResidenceA13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA13" style="text-align:right;" name="otherPremisesA13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting13" style="text-align:right;" name="electricFitting13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalA13" style="text-align:right;" name="totalA13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers13" style="text-align:right;" name="computers13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt13" style="text-align:right;" name="compSoftwareInt13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint13" style="text-align:right;" name="compSoftwareNonint13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal13" style="text-align:right;" name="compSoftwareTotal13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor13" style="text-align:right;" name="motor13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB13" style="text-align:right;" name="offResidenceB13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho13" style="text-align:right;" name="stcLho13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB13" style="text-align:right;" name="otherPremisesB13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant13" style="text-align:right;" name="OtherMachineryPlant13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB13" style="text-align:right;" name="totalB13" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix13" style="text-align:right;" name="totalFurnFix13" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev13" style="text-align:right;" name="landNotRev13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.landRev13" style="text-align:right;" name="landRev13" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh13" style="text-align:right;" name="landRevEnh13" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev13" style="text-align:right;" name="offBuildNotRev13" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev13" style="text-align:right;" name="offBuildRev13" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh13" style="text-align:right;" name="offBuildRevEnh13" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev13" style="text-align:right;" name="residQuartNotRev13" maxlength="18" class="form-control decimal-2-places"readonly="true"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev13" style="text-align:right;" name="residQuartRev13" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh13" style="text-align:right;" name="residQuartRevEnh13" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal13" style="text-align:right;" name="premisTotal13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal13" style="text-align:right;" name="revtotal13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land13" style="text-align:right;" name="land13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding13" style="text-align:right;" name="officeBuilding13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters13" style="text-align:right;" name="residentialQuarters13" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC13" style="text-align:right;" name="totalC13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons13" style="text-align:right;" name="premisesUnderCons13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal13" style="text-align:right;" name="grandTotal13" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>




                            <tr>
                                <td><b>C</b></td>
                                <td><b>Total Original Cost/ Revalued Value as at 31st March {{sc10.year2}} (A+B)</b></td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff14" style="text-align:right;" name="stcNstaff14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA14" style="text-align:right;" name="offResidenceA14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA14" style="text-align:right;" name="otherPremisesA14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting14" style="text-align:right;" name="electricFitting14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalA14" style="text-align:right;" name="totalA14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers14" style="text-align:right;" name="computers14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt14" style="text-align:right;" name="compSoftwareInt14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint14" style="text-align:right;" name="compSoftwareNonint14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal14" style="text-align:right;" name="compSoftwareTotal14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor14" style="text-align:right;" name="motor14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB14" style="text-align:right;" name="offResidenceB14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho14" style="text-align:right;" name="stcLho14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB14" style="text-align:right;" name="otherPremisesB14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant14" style="text-align:right;" name="OtherMachineryPlant14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB14" style="text-align:right;" name="totalB14" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix14" style="text-align:right;" name="totalFurnFix14" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev14" style="text-align:right;" name="landNotRev14" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev14" style="text-align:right;" name="landRev14" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh14" style="text-align:right;" name="landRevEnh14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev14" style="text-align:right;" name="offBuildNotRev14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev14" style="text-align:right;" name="offBuildRev14" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh14" style="text-align:right;" name="offBuildRevEnh14" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev14" style="text-align:right;" name="residQuartNotRev14" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev14" style="text-align:right;" name="residQuartRev14" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh14" style="text-align:right;" name="residQuartRevEnh14" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal14" style="text-align:right;" name="premisTotal14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal14" style="text-align:right;" name="revtotal14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land14" style="text-align:right;" name="land14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding14" style="text-align:right;" name="officeBuilding14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters14" style="text-align:right;" name="residentialQuarters14" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC14" style="text-align:right;" name="totalC14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons14" style="text-align:right;" name="premisesUnderCons14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal14" style="text-align:right;" name="grandTotal14" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>






                            <%--<tr>
                              <td><b>C2.</b></td>
                              <td>Net appreciation on Revaluation </td>
                              <td><input type="text"  ng-model="sc10.row.stcNstaff15" style="text-align:right;" name="stcNstaff15" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.offResidenceA15" style="text-align:right;" name="offResidenceA15" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.otherPremisesA15" style="text-align:right;" name="otherPremisesA15" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.electricFitting15" style="text-align:right;" name="electricFitting15" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.totalA15" style="text-align:right;" name="totalA15" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                              <td><input type="text"  ng-model="sc10.row.computers15" style="text-align:right;" name="computers15" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.compSoftwareInt15" style="text-align:right;" name="compSoftwareInt15" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.compSoftwareNonint15" style="text-align:right;" name="compSoftwareNonint15" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.compSoftwareTotal15" style="text-align:right;" name="compSoftwareTotal15" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                              <td><input type="text"  ng-model="sc10.row.motor15" style="text-align:right;" name="motor15" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.offResidenceB15" style="text-align:right;" name="offResidenceB15" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.stcLho15" style="text-align:right;" name="stcLho15" maxlength="18" class="form-control decimal-2-places"   /></td>

                               <td><input type="text"  ng-model="sc10.row.otherPremisesB15" style="text-align:right;" name="otherPremisesB15" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.otherMachineryPlant15" style="text-align:right;" name="OtherMachineryPlant15" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                              <td><input type="text"  ng-model="sc10.row.totalB15" style="text-align:right;" name="totalB15" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>
                              <td><input type="text"  ng-model="sc10.row.land15" style="text-align:right;" name="land15" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.officeBuilding15" style="text-align:right;" name="officeBuilding15" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.residentialQuarters15" style="text-align:right;" name="residentialQuarters15" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.totalC15" style="text-align:right;" name="totalC15" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                              <td><input type="text"  ng-model="sc10.row.premisesUnderCons15" style="text-align:right;" name="premisesUnderCons15" maxlength="18" class="form-control decimal-2-places"   /></td>
                              <td><input type="text"  ng-model="sc10.row.grandTotal15" style="text-align:right;" name="grandTotal15" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                               </tr>--%>







                            <%-- <tr>
                            <td style="text-align:left"><b>[C]</b></td>
                            <td><b>Total Addition including appreciation on Revaluation  (C1+C2)</b></td>
                            <td><input type="text"  ng-model="sc10.row.stcNstaff16" style="text-align:right;" name="stcNstaff16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            <td><input type="text"  ng-model="sc10.row.offResidenceA16" style="text-align:right;" name="offResidenceA16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            <td><input type="text"  ng-model="sc10.row.otherPremisesA16" style="text-align:right;" name="otherPremisesA16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            <td><input type="text"  ng-model="sc10.row.electricFitting16" style="text-align:right;" name="electricFitting16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            <td><input type="text"  ng-model="sc10.row.totalA16" style="text-align:right;" name="totalA16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            <td><input type="text"  ng-model="sc10.row.computers16" style="text-align:right;" name="computers16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            <td><input type="text"  ng-model="sc10.row.compSoftwareInt16" style="text-align:right;" name="compSoftwareInt16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            <td><input type="text"  ng-model="sc10.row.compSoftwareNonint16" style="text-align:right;" name="compSoftwareNonint16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            <td><input type="text"  ng-model="sc10.row.compSoftwareTotal16" style="text-align:right;" name="compSoftwareTotal16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            <td><input type="text"  ng-model="sc10.row.motor16" style="text-align:right;" name="motor16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            <td><input type="text"  ng-model="sc10.row.offResidenceB16" style="text-align:right;" name="offResidenceB16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            <td><input type="text"  ng-model="sc10.row.stcLho16" style="text-align:right;" name="stcLho16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                             <td><input type="text"  ng-model="sc10.row.otherPremisesB16" style="text-align:right;" name="otherPremisesB16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            <td><input type="text"  ng-model="sc10.row.otherMachineryPlant16" style="text-align:right;" name="OtherMachineryPlant16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            <td><input type="text"  ng-model="sc10.row.totalB16" style="text-align:right;" name="totalB16" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>
                            <td><input type="text"  ng-model="sc10.row.land16" style="text-align:right;" name="land16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            <td><input type="text"  ng-model="sc10.row.officeBuilding16" style="text-align:right;" name="officeBuilding16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            <td><input type="text"  ng-model="sc10.row.residentialQuarters16" style="text-align:right;" name="residentialQuarters16" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>
                            <td><input type="text"  ng-model="sc10.row.totalC16" style="text-align:right;" name="totalC16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                            <td><input type="text"  ng-model="sc10.row.premisesUnderCons16" style="text-align:right;" name="premisesUnderCons16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            <td><input type="text"  ng-model="sc10.row.grandTotal16" style="text-align:right;" name="grandTotal16" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                             </tr>--%>





                            <tr>
                                <td></td>
                                <td><b>Deduction</b></td>
                                <td colspan="30"></td> </tr>




                            <tr>
                                <td style="text-align:right">(i)</td>
                                <td>Depreciation upto the end of previous year i.e. 31st March {{sc10.year1}}</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff18" style="text-align:right;" name="stcNstaff18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA18" style="text-align:right;" name="offResidenceA18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA18" style="text-align:right;" name="otherPremisesA18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting18" style="text-align:right;" name="electricFitting18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA18" style="text-align:right;" name="totalA18" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers18" style="text-align:right;" name="computers18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt18" style="text-align:right;" name="compSoftwareInt18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint18" style="text-align:right;" name="compSoftwareNonint18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal18" style="text-align:right;" name="compSoftwareTotal18" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor18" style="text-align:right;" name="motor18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB18" style="text-align:right;" name="offResidenceB18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho18" style="text-align:right;" name="stcLho18" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB18" style="text-align:right;" name="otherPremisesB18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant18" style="text-align:right;" name="OtherMachineryPlant18" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB18" style="text-align:right;" name="totalB18" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix18" style="text-align:right;" name="totalFurnFix18" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev18" style="text-align:right;" name="landNotRev18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev18" style="text-align:right;" name="landRev18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh18" style="text-align:right;" name="landRevEnh18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev18" style="text-align:right;" name="offBuildNotRev18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev18" style="text-align:right;" name="offBuildRev18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh18" style="text-align:right;" name="offBuildRevEnh18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev18" style="text-align:right;" name="residQuartNotRev18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev18" style="text-align:right;" name="residQuartRev18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh18" style="text-align:right;" name="residQuartRevEnh18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal18" style="text-align:right;" name="premisTotal18" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal18" style="text-align:right;" name="revtotal18" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%--<td><input type="text"  ng-model="sc10.row.land18" style="text-align:right;" name="land18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.officeBuilding18" style="text-align:right;" name="officeBuilding18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residentialQuarters18" style="text-align:right;" name="residentialQuarters18" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC18" style="text-align:right;" name="totalC18" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons18" style="text-align:right;" name="premisesUnderCons18" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal18" style="text-align:right;" name="grandTotal18" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>








                            <%-- <tr>
                             <td style="text-align:right">ii)</td>
                             <td>Depreciation upto end of PY for eABs & BMB</td>
                             <td><input type="text"  ng-model="sc10.row.stcNstaff191" style="text-align:right;" name="stcNstaff191" maxlength="18" class="form-control decimal-2-places"   /></td>
                             <td><input type="text"  ng-model="sc10.row.offResidenceA191" style="text-align:right;" name="offResidenceA191" maxlength="18" class="form-control decimal-2-places"   /></td>
                             <td><input type="text"  ng-model="sc10.row.otherPremisesA191" style="text-align:right;" name="otherPremisesA191" maxlength="18" class="form-control decimal-2-places"   /></td>
                             <td><input type="text"  ng-model="sc10.row.electricFitting191" style="text-align:right;" name="electricFitting191" maxlength="18" class="form-control decimal-2-places"   /></td>
                             <td><input type="text"  ng-model="sc10.row.totalA191" style="text-align:right;" name="totalA191" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                             <td><input type="text"  ng-model="sc10.row.computers191" style="text-align:right;" name="computers191" maxlength="18" class="form-control decimal-2-places"   /></td>
                             <td><input type="text"  ng-model="sc10.row.compSoftwareInt191" style="text-align:right;" name="compSoftwareInt191" maxlength="18" class="form-control decimal-2-places"   /></td>
                             <td><input type="text"  ng-model="sc10.row.compSoftwareNonint191" style="text-align:right;" name="compSoftwareNonint191" maxlength="18" class="form-control decimal-2-places"   /></td>
                             <td><input type="text"  ng-model="sc10.row.compSoftwareTotal191" style="text-align:right;" name="compSoftwareTotal191" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                             <td><input type="text"  ng-model="sc10.row.motor191" style="text-align:right;" name="motor191" maxlength="18" class="form-control decimal-2-places"   /></td>
                             <td><input type="text"  ng-model="sc10.row.offResidenceB191" style="text-align:right;" name="offResidenceB191" maxlength="18" class="form-control decimal-2-places"   /></td>
                             <td><input type="text"  ng-model="sc10.row.stcLho191" style="text-align:right;" name="stcLho191" maxlength="18" class="form-control decimal-2-places"   /></td>

                              <td><input type="text"  ng-model="sc10.row.otherPremisesB191" style="text-align:right;" name="otherPremisesB191" maxlength="18" class="form-control decimal-2-places"   /></td>
                             <td><input type="text"  ng-model="sc10.row.otherMachineryPlant191" style="text-align:right;" name="OtherMachineryPlant191" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                             <td><input type="text"  ng-model="sc10.row.totalB191" style="text-align:right;" name="totalB191" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>
                             <td><input type="text"  ng-model="sc10.row.land191" style="text-align:right;" name="land191" maxlength="18" class="form-control decimal-2-places"   /></td>
                             <td><input type="text"  ng-model="sc10.row.officeBuilding191" style="text-align:right;" name="officeBuilding191" maxlength="18" class="form-control decimal-2-places"   /></td>
                             <td><input type="text"  ng-model="sc10.row.residentialQuarters191" style="text-align:right;" name="residentialQuarters191" maxlength="18" class="form-control decimal-2-places"   /></td>
                             <td><input type="text"  ng-model="sc10.row.totalC191" style="text-align:right;" name="totalC191" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                             <td><input type="text"  ng-model="sc10.row.premisesUnderCons191" style="text-align:right;" name="premisesUnderCons191" maxlength="18" class="form-control decimal-2-places"   /></td>
                             <td><input type="text"  ng-model="sc10.row.grandTotal191" style="text-align:right;" name="grandTotal191" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                             </tr>
                             --%>




                            <!--New Row begin-->
                            <tr>
                                <td style="text-align:right">(ii)</td>
                                <td>Short Valuation charged to depreciation upto end of previous year i.e.31st March {{sc10.year1}}</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff34" style="text-align:right;" name="stcNstaff34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA34" style="text-align:right;" name="offResidenceA34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA34" style="text-align:right;" name="otherPremisesA34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting34" style="text-align:right;" name="electricFitting34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA34" style="text-align:right;" name="totalA34" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers34" style="text-align:right;" name="computers34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt34" style="text-align:right;" name="compSoftwareInt34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint34" style="text-align:right;" name="compSoftwareNonint34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal34" style="text-align:right;" name="compSoftwareTotal34" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor34" style="text-align:right;" name="motor34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB34" style="text-align:right;" name="offResidenceB34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho34" style="text-align:right;" name="stcLho34" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB34" style="text-align:right;" name="otherPremisesB34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant34" style="text-align:right;" name="OtherMachineryPlant34" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB34" style="text-align:right;" name="totalB34" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix34" style="text-align:right;" name="totalFurnFix34" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev34" style="text-align:right;" name="landNotRev34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev34" style="text-align:right;" name="landRev34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh34" style="text-align:right;" name="landRevEnh34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev34" style="text-align:right;" name="offBuildNotRev34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev34" style="text-align:right;" name="offBuildRev34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh34" style="text-align:right;" name="offBuildRevEnh34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev34" style="text-align:right;" name="residQuartNotRev34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev34" style="text-align:right;" name="residQuartRev34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh34" style="text-align:right;" name="residQuartRevEnh34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal34" style="text-align:right;" name="premisTotal34" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal34" style="text-align:right;" name="revtotal34" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land34" style="text-align:right;" name="land34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding34" style="text-align:right;" name="officeBuilding34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters34" style="text-align:right;" name="residentialQuarters34" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC34" style="text-align:right;" name="totalC34" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons34" style="text-align:right;" name="premisesUnderCons34" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal34" style="text-align:right;" name="grandTotal34" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>



                            <tr>
                                <td style="text-align:right">(iii)</td>
                                <td> Depreciation on repatriation of Officials from Subsidiaries/ Associates </td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff38" style="text-align:right;" name="stcNstaff38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA38" style="text-align:right;" name="offResidenceA38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA38" style="text-align:right;" name="otherPremisesA38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting38" style="text-align:right;" name="electricFitting38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA38" style="text-align:right;" name="totalA38" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers38" style="text-align:right;" name="computers38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt38" style="text-align:right;" name="compSoftwareInt38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint38" style="text-align:right;" name="compSoftwareNonint38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal38" style="text-align:right;" name="compSoftwareTotal38" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor38" style="text-align:right;" name="motor38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB38" style="text-align:right;" name="offResidenceB38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho38" style="text-align:right;" name="stcLho38" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB38" style="text-align:right;" name="otherPremisesB38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant38" style="text-align:right;" name="OtherMachineryPlant38" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB38" style="text-align:right;" name="totalB38" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix38" style="text-align:right;" name="totalFurnFix38" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev38" style="text-align:right;" name="landNotRev38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev38" style="text-align:right;" name="landRev38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh38" style="text-align:right;" name="landRevEnh38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev38" style="text-align:right;" name="offBuildNotRev38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev38" style="text-align:right;" name="offBuildRev38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh38" style="text-align:right;" name="offBuildRevEnh38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev38" style="text-align:right;" name="residQuartNotRev38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev38" style="text-align:right;" name="residQuartRev38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh38" style="text-align:right;" name="residQuartRevEnh38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal38" style="text-align:right;" name="premisTotal38" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal38" style="text-align:right;" name="revtotal38" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%--<td><input type="text"  ng-model="sc10.row.land38" style="text-align:right;" name="land38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.officeBuilding38" style="text-align:right;" name="officeBuilding38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residentialQuarters38" style="text-align:right;" name="residentialQuarters38" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC38" style="text-align:right;" name="totalC38" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons38" style="text-align:right;" name="premisesUnderCons38" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal38" style="text-align:right;" name="grandTotal38" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>















                            <!--New Row End-->






                            <tr>
                                <td style="text-align:right">(iv)</td>
                                <td>Depreciation transferred from other Circles/Groups/CC Departments</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff19" style="text-align:right;" name="stcNstaff19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA19" style="text-align:right;" name="offResidenceA19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA19" style="text-align:right;" name="otherPremisesA19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting19" style="text-align:right;" name="electricFitting19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA19" style="text-align:right;" name="totalA19" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers19" style="text-align:right;" name="computers19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt19" style="text-align:right;" name="compSoftwareInt19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint19" style="text-align:right;" name="compSoftwareNonint19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal19" style="text-align:right;" name="compSoftwareTotal19" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor19" style="text-align:right;" name="motor19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB19" style="text-align:right;" name="offResidenceB19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho19" style="text-align:right;" name="stcLho19" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB19" style="text-align:right;" name="otherPremisesB19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant19" style="text-align:right;" name="OtherMachineryPlant19" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB19" style="text-align:right;" name="totalB19" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>



                                <td><input type="text"  ng-model="sc10.row.totalFurnFix19" style="text-align:right;" name="totalFurnFix19" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev19" style="text-align:right;" name="landNotRev19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev19" style="text-align:right;" name="landRev19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh19" style="text-align:right;" name="landRevEnh19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev19" style="text-align:right;" name="offBuildNotRev19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev19" style="text-align:right;" name="offBuildRev19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh19" style="text-align:right;" name="offBuildRevEnh19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev19" style="text-align:right;" name="residQuartNotRev19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev19" style="text-align:right;" name="residQuartRev19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh19" style="text-align:right;" name="residQuartRevEnh19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal19" style="text-align:right;" name="premisTotal19" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal19" style="text-align:right;" name="revtotal19" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land19" style="text-align:right;" name="land19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding19" style="text-align:right;" name="officeBuilding19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters19" style="text-align:right;" name="residentialQuarters19" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC19" style="text-align:right;" name="totalC19" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons19" style="text-align:right;" name="premisesUnderCons19" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal19" style="text-align:right;" name="grandTotal19" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>


                            <tr>
                                <td style="text-align:right">(v)</td>
                                <td>Depreciation transferred from other branches of the same circle.</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff20" style="text-align:right;" name="stcNstaff20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA20" style="text-align:right;" name="offResidenceA20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA20" style="text-align:right;" name="otherPremisesA20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting20" style="text-align:right;" name="electricFitting20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA20" style="text-align:right;" name="totalA20" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers20" style="text-align:right;" name="computers20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt20" style="text-align:right;" name="compSoftwareInt20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint20" style="text-align:right;" name="compSoftwareNonint20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal20" style="text-align:right;" name="compSoftwareTotal20" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor20" style="text-align:right;" name="motor20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB20" style="text-align:right;" name="offResidenceB20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho20" style="text-align:right;" name="stcLho20" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB20" style="text-align:right;" name="otherPremisesB20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant20" style="text-align:right;" name="OtherMachineryPlant20" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB20" style="text-align:right;" name="totalB20" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix20" style="text-align:right;" name="totalFurnFix20" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev20" style="text-align:right;" name="landNotRev20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev20" style="text-align:right;" name="landRev20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh20" style="text-align:right;" name="landRevEnh20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev20" style="text-align:right;" name="offBuildNotRev20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev20" style="text-align:right;" name="offBuildRev20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh20" style="text-align:right;" name="offBuildRevEnh20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev20" style="text-align:right;" name="residQuartNotRev20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev20" style="text-align:right;" name="residQuartRev20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh20" style="text-align:right;" name="residQuartRevEnh20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal20" style="text-align:right;" name="premisTotal20" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal20" style="text-align:right;" name="revtotal20" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%--<td><input type="text"  ng-model="sc10.row.land20" style="text-align:right;" name="land20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.officeBuilding20" style="text-align:right;" name="officeBuilding20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residentialQuarters20" style="text-align:right;" name="residentialQuarters20" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC20" style="text-align:right;" name="totalC20" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons20" style="text-align:right;" name="premisesUnderCons20" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal20" style="text-align:right;" name="grandTotal20" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>



                            <tr>
                                <td style="text-align:right">(vi)</td>
                                <td> Depreciation charged during the current year </td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff21" style="text-align:right;" name="stcNstaff21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA21" style="text-align:right;" name="offResidenceA21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA21" style="text-align:right;" name="otherPremisesA21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting21" style="text-align:right;" name="electricFitting21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA21" style="text-align:right;" name="totalA21" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers21" style="text-align:right;" name="computers21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt21" style="text-align:right;" name="compSoftwareInt21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint21" style="text-align:right;" name="compSoftwareNonint21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal21" style="text-align:right;" name="compSoftwareTotal21" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor21" style="text-align:right;" name="motor21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB21" style="text-align:right;" name="offResidenceB21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho21" style="text-align:right;" name="stcLho21" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB21" style="text-align:right;" name="otherPremisesB21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant21" style="text-align:right;" name="OtherMachineryPlant21" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB21" style="text-align:right;" name="totalB21" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix21" style="text-align:right;" name="totalFurnFix21" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev21" style="text-align:right;" name="landNotRev21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev21" style="text-align:right;" name="landRev21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh21" style="text-align:right;" name="landRevEnh21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev21" style="text-align:right;" name="offBuildNotRev21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev21" style="text-align:right;" name="offBuildRev21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh21" style="text-align:right;" name="offBuildRevEnh21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev21" style="text-align:right;" name="residQuartNotRev21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev21" style="text-align:right;" name="residQuartRev21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh21" style="text-align:right;" name="residQuartRevEnh21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal21" style="text-align:right;" name="premisTotal21" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal21" style="text-align:right;" name="revtotal21" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%--<td><input type="text"  ng-model="sc10.row.land21" style="text-align:right;" name="land21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.officeBuilding21" style="text-align:right;" name="officeBuilding21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residentialQuarters21" style="text-align:right;" name="residentialQuarters21" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC21" style="text-align:right;" name="totalC21" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons21" style="text-align:right;" name="premisesUnderCons21" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal21" style="text-align:right;" name="grandTotal21" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>


                            <tr>
                                <td style="text-align:right">(vii)</td>
                                <td>  Short Valuation charged to Depreciation during the current year due to Current Revaluation</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff39" style="text-align:right;" name="stcNstaff39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA39" style="text-align:right;" name="offResidenceA39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA39" style="text-align:right;" name="otherPremisesA39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting39" style="text-align:right;" name="electricFitting39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA39" style="text-align:right;" name="totalA39" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers39" style="text-align:right;" name="computers39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt39" style="text-align:right;" name="compSoftwareInt39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint39" style="text-align:right;" name="compSoftwareNonint39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal39" style="text-align:right;" name="compSoftwareTotal39" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor39" style="text-align:right;" name="motor39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB39" style="text-align:right;" name="offResidenceB39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho39" style="text-align:right;" name="stcLho39" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB39" style="text-align:right;" name="otherPremisesB39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant39" style="text-align:right;" name="OtherMachineryPlant39" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB39" style="text-align:right;" name="totalB39" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix39" style="text-align:right;" name="totalFurnFix39" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev39" style="text-align:right;" name="landNotRev39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev39" style="text-align:right;" name="landRev39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh39" style="text-align:right;" name="landRevEnh39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev39" style="text-align:right;" name="offBuildNotRev39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev39" style="text-align:right;" name="offBuildRev39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh39" style="text-align:right;" name="offBuildRevEnh39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev39" style="text-align:right;" name="residQuartNotRev39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev39" style="text-align:right;" name="residQuartRev39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh39" style="text-align:right;" name="residQuartRevEnh39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal39" style="text-align:right;" name="premisTotal39" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal39" style="text-align:right;" name="revtotal39" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%--<td><input type="text"  ng-model="sc10.row.land39" style="text-align:right;" name="land39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.officeBuilding39" style="text-align:right;" name="officeBuilding39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residentialQuarters39" style="text-align:right;" name="residentialQuarters39" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC39" style="text-align:right;" name="totalC39" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons39" style="text-align:right;" name="premisesUnderCons39" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal39" style="text-align:right;" name="grandTotal39" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>



                            <tr>
                                <td style="text-align:left"><b>D</b></td>
                                <td><b>Total (i+ii+iii+iv+v+vi+vii) </b></td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff22" style="text-align:right;" name="stcNstaff22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA22" style="text-align:right;" name="offResidenceA22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA22" style="text-align:right;" name="otherPremisesA22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting22" style="text-align:right;" name="electricFitting22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalA22" style="text-align:right;" name="totalA22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers22" style="text-align:right;" name="computers22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt22" style="text-align:right;" name="compSoftwareInt22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint22" style="text-align:right;" name="compSoftwareNonint22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal22" style="text-align:right;" name="compSoftwareTotal22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor22" style="text-align:right;" name="motor22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB22" style="text-align:right;" name="offResidenceB22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho22" style="text-align:right;" name="stcLho22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB22" style="text-align:right;" name="otherPremisesB22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant22" style="text-align:right;" name="OtherMachineryPlant22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB22" style="text-align:right;" name="totalB22" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>



                                <td><input type="text"  ng-model="sc10.row.totalFurnFix22" style="text-align:right;" name="totalFurnFix22" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev22" style="text-align:right;" name="landNotRev22" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev22" style="text-align:right;" name="landRev22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh22" style="text-align:right;" name="landRevEnh22" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev22" style="text-align:right;" name="offBuildNotRev22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev22" style="text-align:right;" name="offBuildRev22" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh22" style="text-align:right;" name="offBuildRevEnh22" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev22" style="text-align:right;" name="residQuartNotRev22" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev22" style="text-align:right;" name="residQuartRev22" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh22" style="text-align:right;" name="residQuartRevEnh22" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal22" style="text-align:right;" name="premisTotal22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal22" style="text-align:right;" name="revtotal22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%--<td><input type="text"  ng-model="sc10.row.land22" style="text-align:right;" name="land22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.officeBuilding22" style="text-align:right;" name="officeBuilding22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.residentialQuarters22" style="text-align:right;" name="residentialQuarters22" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC22" style="text-align:right;" name="totalC22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons22" style="text-align:right;" name="premisesUnderCons22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal22" style="text-align:right;" name="grandTotal22" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>



                            <tr>
                                <td></td>
                                <td><b>Less :</b></td>
                                <td colspan="30"></td> </tr>

                            <tr>
                                <td style="text-align:right">(i)</td>
                                <td>Past Short Valuation credited to Depreciation during the current year due to <br>Current Upward Revaluation</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff40" style="text-align:right;" name="stcNstaff40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA40" style="text-align:right;" name="offResidenceA40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA40" style="text-align:right;" name="otherPremisesA40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting40" style="text-align:right;" name="electricFitting40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA40" style="text-align:right;" name="totalA40" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers40" style="text-align:right;" name="computers40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt40" style="text-align:right;" name="compSoftwareInt40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint40" style="text-align:right;" name="compSoftwareNonint40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal40" style="text-align:right;" name="compSoftwareTotal40" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor40" style="text-align:right;" name="motor40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB40" style="text-align:right;" name="offResidenceB40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho40" style="text-align:right;" name="stcLho40" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB40" style="text-align:right;" name="otherPremisesB40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant40" style="text-align:right;" name="OtherMachineryPlant40" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB40" style="text-align:right;" name="totalB40" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix40" style="text-align:right;" name="totalFurnFix40" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev40" style="text-align:right;" name="landNotRev40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev40" style="text-align:right;" name="landRev40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh40" style="text-align:right;" name="landRevEnh40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev40" style="text-align:right;" name="offBuildNotRev40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev40" style="text-align:right;" name="offBuildRev40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh40" style="text-align:right;" name="offBuildRevEnh40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev40" style="text-align:right;" name="residQuartNotRev40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev40" style="text-align:right;" name="residQuartRev40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh40" style="text-align:right;" name="residQuartRevEnh40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal40" style="text-align:right;" name="premisTotal40" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal40" style="text-align:right;" name="revtotal40" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%--  <td><input type="text"  ng-model="sc10.row.land40" style="text-align:right;" name="land40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                  <td><input type="text"  ng-model="sc10.row.officeBuilding40" style="text-align:right;" name="officeBuilding40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                  <td><input type="text"  ng-model="sc10.row.residentialQuarters40" style="text-align:right;" name="residentialQuarters40" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC40" style="text-align:right;" name="totalC40" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons40" style="text-align:right;" name="premisesUnderCons40" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal40" style="text-align:right;" name="grandTotal40" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>



                            <tr>


                            <tr>
                                <td style="text-align:right">(ii)</td>
                                <td>Depreciation previously provided on fixed assets sold/ discarded</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff24" style="text-align:right;" name="stcNstaff24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA24" style="text-align:right;" name="offResidenceA24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA24" style="text-align:right;" name="otherPremisesA24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting24" style="text-align:right;" name="electricFitting24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA24" style="text-align:right;" name="totalA24" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers24" style="text-align:right;" name="computers24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt24" style="text-align:right;" name="compSoftwareInt24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint24" style="text-align:right;" name="compSoftwareNonint24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal24" style="text-align:right;" name="compSoftwareTotal24" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor24" style="text-align:right;" name="motor24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB24" style="text-align:right;" name="offResidenceB24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho24" style="text-align:right;" name="stcLho24" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB24" style="text-align:right;" name="otherPremisesB24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant24" style="text-align:right;" name="OtherMachineryPlant24" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB24" style="text-align:right;" name="totalB24" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix24" style="text-align:right;" name="totalFurnFix24" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev24" style="text-align:right;" name="landNotRev24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev24" style="text-align:right;" name="landRev24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh24" style="text-align:right;" name="landRevEnh24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev24" style="text-align:right;" name="offBuildNotRev24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev24" style="text-align:right;" name="offBuildRev24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh24" style="text-align:right;" name="offBuildRevEnh24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev24" style="text-align:right;" name="residQuartNotRev24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev24" style="text-align:right;" name="residQuartRev24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh24" style="text-align:right;" name="residQuartRevEnh24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal24" style="text-align:right;" name="premisTotal24" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal24" style="text-align:right;" name="revtotal24" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%--  <td><input type="text"  ng-model="sc10.row.land24" style="text-align:right;" name="land24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                  <td><input type="text"  ng-model="sc10.row.officeBuilding24" style="text-align:right;" name="officeBuilding24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                  <td><input type="text"  ng-model="sc10.row.residentialQuarters24" style="text-align:right;" name="residentialQuarters24" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC24" style="text-align:right;" name="totalC24" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons24" style="text-align:right;" name="premisesUnderCons24" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal24" style="text-align:right;" name="grandTotal24" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>



                            <tr>
                                <td style="text-align:right">(iii)</td>
                                <td>Depreciation transferred to other Circles/Groups/CC Departments</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff25" style="text-align:right;" name="stcNstaff25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA25" style="text-align:right;" name="offResidenceA25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA25" style="text-align:right;" name="otherPremisesA25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting25" style="text-align:right;" name="electricFitting25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA25" style="text-align:right;" name="totalA25" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers25" style="text-align:right;" name="computers25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt25" style="text-align:right;" name="compSoftwareInt25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint25" style="text-align:right;" name="compSoftwareNonint25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal25" style="text-align:right;" name="compSoftwareTotal25" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor25" style="text-align:right;" name="motor25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB25" style="text-align:right;" name="offResidenceB25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho25" style="text-align:right;" name="stcLho25" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB25" style="text-align:right;" name="otherPremisesB25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant25" style="text-align:right;" name="OtherMachineryPlant25" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB25" style="text-align:right;" name="totalB25" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>



                                <td><input type="text"  ng-model="sc10.row.totalFurnFix25" style="text-align:right;" name="totalFurnFix25" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev25" style="text-align:right;" name="landNotRev25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev25" style="text-align:right;" name="landRev25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh25" style="text-align:right;" name="landRevEnh25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev25" style="text-align:right;" name="offBuildNotRev25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev25" style="text-align:right;" name="offBuildRev25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh25" style="text-align:right;" name="offBuildRevEnh25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev25" style="text-align:right;" name="residQuartNotRev25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev25" style="text-align:right;" name="residQuartRev25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh25" style="text-align:right;" name="residQuartRevEnh25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal25" style="text-align:right;" name="premisTotal25" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal25" style="text-align:right;" name="revtotal25" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land25" style="text-align:right;" name="land25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding25" style="text-align:right;" name="officeBuilding25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters25" style="text-align:right;" name="residentialQuarters25" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC25" style="text-align:right;" name="totalC25" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons25" style="text-align:right;" name="premisesUnderCons25" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal25" style="text-align:right;" name="grandTotal25" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>





                            <tr>
                                <td style="text-align:right">(iv)</td>
                                <td>Depreciation transferred to other branches of the same Circle.</td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff26" style="text-align:right;" name="stcNstaff26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA26" style="text-align:right;" name="offResidenceA26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA26" style="text-align:right;" name="otherPremisesA26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting26" style="text-align:right;" name="electricFitting26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA26" style="text-align:right;" name="totalA26" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers26" style="text-align:right;" name="computers26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt26" style="text-align:right;" name="compSoftwareInt26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint26" style="text-align:right;" name="compSoftwareNonint26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal26" style="text-align:right;" name="compSoftwareTotal26" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor26" style="text-align:right;" name="motor26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB26" style="text-align:right;" name="offResidenceB26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho26" style="text-align:right;" name="stcLho26" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB26" style="text-align:right;" name="otherPremisesB26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant26" style="text-align:right;" name="OtherMachineryPlant26" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB26" style="text-align:right;" name="totalB26" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix26" style="text-align:right;" name="totalFurnFix26" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev26" style="text-align:right;" name="landNotRev26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev26" style="text-align:right;" name="landRev26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh26" style="text-align:right;" name="landRevEnh26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev26" style="text-align:right;" name="offBuildNotRev26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev26" style="text-align:right;" name="offBuildRev26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh26" style="text-align:right;" name="offBuildRevEnh26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev26" style="text-align:right;" name="residQuartNotRev26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev26" style="text-align:right;" name="residQuartRev26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh26" style="text-align:right;" name="residQuartRevEnh26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal26" style="text-align:right;" name="premisTotal26" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal26" style="text-align:right;" name="revtotal26" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land26" style="text-align:right;" name="land26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding26" style="text-align:right;" name="officeBuilding26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters26" style="text-align:right;" name="residentialQuarters26" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC26" style="text-align:right;" name="totalC26" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons26" style="text-align:right;" name="premisesUnderCons26" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal26" style="text-align:right;" name="grandTotal26" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>



                            <tr>
                                <td style="text-align:left"><b>E</b></td>
                                <td><b>Total (i+ii+iii+iv)</b></td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff27" style="text-align:right;" name="stcNstaff27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA27" style="text-align:right;" name="offResidenceA27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA27" style="text-align:right;" name="otherPremisesA27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting27" style="text-align:right;" name="electricFitting27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalA27" style="text-align:right;" name="totalA27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers27" style="text-align:right;" name="computers27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt27" style="text-align:right;" name="compSoftwareInt27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint27" style="text-align:right;" name="compSoftwareNonint27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal27" style="text-align:right;" name="compSoftwareTotal27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor27" style="text-align:right;" name="motor27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB27" style="text-align:right;" name="offResidenceB27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho27" style="text-align:right;" name="stcLho27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB27" style="text-align:right;" name="otherPremisesB27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant27" style="text-align:right;" name="OtherMachineryPlant27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB27" style="text-align:right;" name="totalB27" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix27" style="text-align:right;" name="totalFurnFix27" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev27" style="text-align:right;" name="landNotRev27" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev27" style="text-align:right;" name="landRev27" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh27" style="text-align:right;" name="landRevEnh27" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev27" style="text-align:right;" name="offBuildNotRev27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev27" style="text-align:right;" name="offBuildRev27" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh27" style="text-align:right;" name="offBuildRevEnh27" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev27" style="text-align:right;" name="residQuartNotRev27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev27" style="text-align:right;" name="residQuartRev27" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh27" style="text-align:right;" name="residQuartRevEnh27" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal27" style="text-align:right;" name="premisTotal27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal27" style="text-align:right;" name="revtotal27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land27" style="text-align:right;" name="land27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding27" style="text-align:right;" name="officeBuilding27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters27" style="text-align:right;" name="residentialQuarters27" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC27" style="text-align:right;" name="totalC27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons27" style="text-align:right;" name="premisesUnderCons27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal27" style="text-align:right;" name="grandTotal27" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>




                            <tr>
                                <td style="text-align:left"><b>F</b></td>
                                <td><b>Net Depreciation (D-E)</b></td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff28" style="text-align:right;" name="stcNstaff28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA28" style="text-align:right;" name="offResidenceA28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA28" style="text-align:right;" name="otherPremisesA28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting28" style="text-align:right;" name="electricFitting28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalA28" style="text-align:right;" name="totalA28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers28" style="text-align:right;" name="computers28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt28" style="text-align:right;" name="compSoftwareInt28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint28" style="text-align:right;" name="compSoftwareNonint28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal28" style="text-align:right;" name="compSoftwareTotal28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor28" style="text-align:right;" name="motor28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB28" style="text-align:right;" name="offResidenceB28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho28" style="text-align:right;" name="stcLho28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB28" style="text-align:right;" name="otherPremisesB28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant28" style="text-align:right;" name="OtherMachineryPlant28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB28" style="text-align:right;" name="totalB28" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix28" style="text-align:right;" name="totalFurnFix28" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev28" style="text-align:right;" name="landNotRev28" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev28" style="text-align:right;" name="landRev28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh28" style="text-align:right;" name="landRevEnh28" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev28" style="text-align:right;" name="offBuildNotRev28" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev28" style="text-align:right;" name="offBuildRev28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh28" style="text-align:right;" name="offBuildRevEnh28" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev28" style="text-align:right;" name="residQuartNotRev28" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev28" style="text-align:right;" name="residQuartRev28" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh28" style="text-align:right;" name="residQuartRevEnh28" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal28" style="text-align:right;" name="premisTotal28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal28" style="text-align:right;" name="revtotal28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%--<td><input type="text"  ng-model="sc10.row.land28" style="text-align:right;" name="land28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.officeBuilding28" style="text-align:right;" name="officeBuilding28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.residentialQuarters28" style="text-align:right;" name="residentialQuarters28" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC28" style="text-align:right;" name="totalC28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons28" style="text-align:right;" name="premisesUnderCons28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal28" style="text-align:right;" name="grandTotal28" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>





                            <tr>
                                <td style="text-align:left"><b>G</b></td>
                                <td><b>Net Book Value as at 31st March {{sc10.year2}} (C-F) </b></td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff29" style="text-align:right;" name="stcNstaff29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA29" style="text-align:right;" name="offResidenceA29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA29" style="text-align:right;" name="otherPremisesA29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting29" style="text-align:right;" name="electricFitting29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalA29" id="sc10.row.totalA29"style="text-align:right;" name="totalA29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers29" style="text-align:right;" name="computers29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt29" style="text-align:right;" name="compSoftwareInt29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint29" style="text-align:right;" name="compSoftwareNonint29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal29" style="text-align:right;" name="compSoftwareTotal29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor29" style="text-align:right;" name="motor29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB29" style="text-align:right;" name="offResidenceB29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho29" style="text-align:right;" name="stcLho29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB29" style="text-align:right;" name="otherPremisesB29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant29" style="text-align:right;" name="OtherMachineryPlant29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB29" id="sc10.row.totalB29"style="text-align:right;" name="totalB29" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix29" style="text-align:right;" name="totalFurnFix29" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev29" style="text-align:right;" name="landNotRev29" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev29" style="text-align:right;" name="landRev29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh29" style="text-align:right;" name="landRevEnh29" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev29" style="text-align:right;" name="offBuildNotRev29" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev29" style="text-align:right;" name="offBuildRev29" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh29" style="text-align:right;" name="offBuildRevEnh29" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev29" style="text-align:right;" name="residQuartNotRev29" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev29" style="text-align:right;" name="residQuartRev29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh29" style="text-align:right;" name="residQuartRevEnh29" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal29" style="text-align:right;" name="premisTotal29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal29" style="text-align:right;" name="revtotal29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%--<td><input type="text"  ng-model="sc10.row.land29" style="text-align:right;" name="land29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.officeBuilding29" style="text-align:right;" name="officeBuilding29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.residentialQuarters29" style="text-align:right;" name="residentialQuarters29" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC29"  id="sc10.row.totalC29"style="text-align:right;" name="totalC29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons29" id="sc10.row.premisesUnderCons29"style="text-align:right;" name="premisesUnderCons29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal29" style="text-align:right;" name="grandTotal29" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>





                            <tr>
                                <td style="text-align:left"><b>H</b></td>
                                <td><b>Sale Price of fixed assets</b></td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff30" style="text-align:right;" name="stcNstaff30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA30" style="text-align:right;" name="offResidenceA30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA30" style="text-align:right;" name="otherPremisesA30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting30" style="text-align:right;" name="electricFitting30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA30" style="text-align:right;" name="totalA30" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers30" style="text-align:right;" name="computers30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt30" style="text-align:right;" name="compSoftwareInt30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint30" style="text-align:right;" name="compSoftwareNonint30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal30" style="text-align:right;" name="compSoftwareTotal30" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor30" style="text-align:right;" name="motor30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB30" style="text-align:right;" name="offResidenceB30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho30" style="text-align:right;" name="stcLho30" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB30" style="text-align:right;" name="otherPremisesB5" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant30" style="text-align:right;" name="OtherMachineryPlant30" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB30" style="text-align:right;" name="totalB30" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix30" style="text-align:right;" name="totalFurnFix30" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev30" style="text-align:right;" name="landNotRev30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev30" style="text-align:right;" name="landRev30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh30" style="text-align:right;" name="landRevEnh30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev30" style="text-align:right;" name="offBuildNotRev30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev30" style="text-align:right;" name="offBuildRev30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh30" style="text-align:right;" name="offBuildRevEnh30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev30" style="text-align:right;" name="residQuartNotRev30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev30" style="text-align:right;" name="residQuartRev30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh30" style="text-align:right;" name="residQuartRevEnh30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal30" style="text-align:right;" name="premisTotal30" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal30" style="text-align:right;" name="revtotal30" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land30" style="text-align:right;" name="land30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding30" style="text-align:right;" name="officeBuilding30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters30" style="text-align:right;" name="residentialQuarters30" maxlength="18" class="form-control decimal-2-places"   /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC30" style="text-align:right;" name="totalC30" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons30" style="text-align:right;" name="premisesUnderCons30" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal30" style="text-align:right;" name="grandTotal30" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>





                            <tr>
                                <td style="text-align:left"><b>I</b></td>
                                <td><b> Book Value of fixed assets sold [II (ii)-E(ii)]</b></td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff31" style="text-align:right;" name="stcNstaff31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA31" style="text-align:right;" name="offResidenceA31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA31" style="text-align:right;" name="otherPremisesA31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting31" style="text-align:right;" name="electricFitting31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalA31" style="text-align:right;" name="totalA31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers31" style="text-align:right;" name="computers31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt31" style="text-align:right;" name="compSoftwareInt31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint31" style="text-align:right;" name="compSoftwareNonint31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal31" style="text-align:right;" name="compSoftwareTotal31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor31" style="text-align:right;" name="motor31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB31" style="text-align:right;" name="offResidenceB31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho31" style="text-align:right;" name="stcLho31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB31" style="text-align:right;" name="otherPremisesB31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant31" style="text-align:right;" name="OtherMachineryPlant31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB31" style="text-align:right;" name="totalB31" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix31" style="text-align:right;" name="totalFurnFix31" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev31" style="text-align:right;" name="landNotRev31" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev31" style="text-align:right;" name="landRev31" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh31" style="text-align:right;" name="landRevEnh31" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev31" style="text-align:right;" name="offBuildNotRev31" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev31" style="text-align:right;" name="offBuildRev31" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh31" style="text-align:right;" name="offBuildRevEnh31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev31" style="text-align:right;" name="residQuartNotRev31" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev31" style="text-align:right;" name="residQuartRev31" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh31" style="text-align:right;" name="residQuartRevEnh31" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal31" style="text-align:right;" name="premisTotal31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal31" style="text-align:right;" name="revtotal31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%-- <td><input type="text"  ng-model="sc10.row.land31" style="text-align:right;" name="land31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                 <td><input type="text"  ng-model="sc10.row.officeBuilding31" style="text-align:right;" name="officeBuilding31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                 <td><input type="text"  ng-model="sc10.row.residentialQuarters31" style="text-align:right;" name="residentialQuarters31" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC31" style="text-align:right;" name="totalC31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons31" style="text-align:right;" name="premisesUnderCons31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal31" style="text-align:right;" name="grandTotal31" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>

                            <!--New Row begin-->
                            <tr>
                                <td style="text-align:left"><b>J</b></td>
                                <td><b>GST on Sale of fixed assets</b></td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff35" style="text-align:right;" name="stcNstaff35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA35" style="text-align:right;" name="offResidenceA35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA35" style="text-align:right;" name="otherPremisesA35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting35" style="text-align:right;" name="electricFitting35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.totalA35" style="text-align:right;" name="totalA35" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers35" style="text-align:right;" name="computers35" maxlength="18" class="form-control decimal-2-places"  /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt35" style="text-align:right;" name="compSoftwareInt35" maxlength="18" class="form-control decimal-2-places"  /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint35" style="text-align:right;" name="compSoftwareNonint35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal35" style="text-align:right;" name="compSoftwareTotal35" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.motor35" style="text-align:right;" name="motor35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB35" style="text-align:right;" name="offResidenceB35" maxlength="18" class="form-control decimal-2-places"  /></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho35" style="text-align:right;" name="stcLho35" maxlength="18" class="form-control decimal-2-places"   /></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB35" style="text-align:right;" name="otherPremisesB35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant35" style="text-align:right;" name="OtherMachineryPlant35" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.totalB35" style="text-align:right;" name="totalB35" maxlength="18" class="form-control decimal-2-places"  readonly="true"/></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix35" style="text-align:right;" name="totalFurnFix35" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev35" style="text-align:right;" name="landNotRev35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev35" style="text-align:right;" name="landRev35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh35" style="text-align:right;" name="landRevEnh35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev35" style="text-align:right;" name="offBuildNotRev35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev35" style="text-align:right;" name="offBuildRev35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh35" style="text-align:right;" name="offBuildRevEnh35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev35" style="text-align:right;" name="residQuartNotRev35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev35" style="text-align:right;" name="residQuartRev35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh35" style="text-align:right;" name="residQuartRevEnh35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal35" style="text-align:right;" name="premisTotal35" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal35" style="text-align:right;" name="revtotal35" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%--  <td><input type="text"  ng-model="sc10.row.land35" style="text-align:right;" name="land35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                  <td><input type="text"  ng-model="sc10.row.officeBuilding35" style="text-align:right;" name="officeBuilding35" maxlength="18" class="form-control decimal-2-places"  /></td>
                                  <td><input type="text"  ng-model="sc10.row.residentialQuarters35" style="text-align:right;" name="residentialQuarters35" maxlength="18" class="form-control decimal-2-places"    /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC35" style="text-align:right;" name="totalC35" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons35" style="text-align:right;" name="premisesUnderCons35" maxlength="18" class="form-control decimal-2-places"   /></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal35" style="text-align:right;" name="grandTotal35" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                            </tr>
                            <!--New Row End-->

                            <tr>
                                <td style="text-align:left"><b>K</b></td>
                                <td><b>Profit/ (Loss) on sale of fixed assets [H-(I+J)]</b></td>
                                <td><input type="text"  ng-model="sc10.row.stcNstaff32" style="text-align:right;" name="stcNstaff32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceA32" style="text-align:right;" name="offResidenceA32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherPremisesA32" style="text-align:right;" name="otherPremisesA32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.electricFitting32" style="text-align:right;" name="electricFitting32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalA32" style="text-align:right;" name="totalA32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.computers32" style="text-align:right;" name="computers32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareInt32" style="text-align:right;" name="compSoftwareInt32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareNonint32" style="text-align:right;" name="compSoftwareNonint32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.compSoftwareTotal32" style="text-align:right;" name="compSoftwareTotal32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.motor32" style="text-align:right;" name="motor32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offResidenceB32" style="text-align:right;" name="offResidenceB32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.stcLho32" style="text-align:right;" name="stcLho32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.otherPremisesB32" style="text-align:right;" name="otherPremisesB32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.otherMachineryPlant32" style="text-align:right;" name="OtherMachineryPlant32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.totalB32" style="text-align:right;" name="totalB32" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>


                                <td><input type="text"  ng-model="sc10.row.totalFurnFix32" style="text-align:right;" name="totalFurnFix32" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landNotRev32" style="text-align:right;" name="landNotRev32" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landRev32" style="text-align:right;" name="landRev32" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.landRevEnh32" style="text-align:right;" name="landRevEnh32" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildNotRev32" style="text-align:right;" name="offBuildNotRev32" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRev32" style="text-align:right;" name="offBuildRev32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.offBuildRevEnh32" style="text-align:right;" name="offBuildRevEnh32" maxlength="18" class="form-control decimal-2-places"  readonly="true" /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartNotRev32" style="text-align:right;" name="residQuartNotRev32" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRev32" style="text-align:right;" name="residQuartRev32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.residQuartRevEnh32" style="text-align:right;" name="residQuartRevEnh32" maxlength="18" class="form-control decimal-2-places" readonly="true"  /></td>
                                <td><input type="text"  ng-model="sc10.row.premisTotal32" style="text-align:right;" name="premisTotal32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.revtotal32" style="text-align:right;" name="revtotal32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <%--<td><input type="text"  ng-model="sc10.row.land32" style="text-align:right;" name="land32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.officeBuilding32" style="text-align:right;" name="officeBuilding32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.residentialQuarters32" style="text-align:right;" name="residentialQuarters32" maxlength="18" class="form-control decimal-2-places"   readonly="true" /></td>--%>
                                <td><input type="text"  ng-model="sc10.row.totalC32" style="text-align:right;" name="totalC32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>

                                <td><input type="text"  ng-model="sc10.row.premisesUnderCons32" style="text-align:right;" name="premisesUnderCons32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                                <td><input type="text"  ng-model="sc10.row.grandTotal32" style="text-align:right;" name="grandTotal32" maxlength="18" class="form-control decimal-2-places"   readonly="true"/></td>
                            </tr>






                            </tbody>
                        </table>

                        <div>
                            <%--<input type="hidden"  ng-model="sc10.row.save" id="sc10.row.totalB29" readonly="true"/>    --%>
                        </div>
                        <!-- start: div for alert message -->

                        <!--end: div for alert message -->
                    </div>
                    <div class="tim-row" >
                        <button class="btn btn-warning" data-dismiss="modal" type="submit" ng-click="sc10.row.save = true">Save</button>
                        <button class="btn btn-success" data-dismiss="modal" type="submit" ng-click="sc10.row.save = false">Submit</button>

                        <!--  </div>   </div>

                         <button type="button" class="btn btn-default btn-success"  ng-click="submitScrTwo(row);" >Submit</button>   -->
                    </div>

                </form>





            </div>
        </div>
    </div>
</div>



<div class="modal fade" id="myModal1" role="dialog" style="z-index : 1400">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #E74C3C";>
                <!--<button type="button" class="close" data-dismiss="modal">�</button> -->
                <h4 class="modal-title" style="color: white;">Attention!</h4>
            </div>
            <div class="modal-body" id="popup">
                {{sc10.displayMessage}}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-success" data-dismiss="modal" ng-click="sc10.redirect();">Continue</button>
            </div>


        </div>
    </div>



</div>


<div class="modal fade" id="myModal2" role="dialog" style="z-index : 1400">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #E74C3C";>
                <!--<button type="button" class="close" data-dismiss="modal">�</button> -->
                <h4 class="modal-title" style="color: white;">Errors!</h4>
            </div>
            <div class="modal-body" id="popup">
                <table id="example2" class="table table-hover table-responsive no-padding dataTable no-footer" style="width: 100%">

                    <tr data-ng-repeat="row in validateArray">
                        <td>#. </td><td>{{row}}</td></tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-success" data-dismiss="modal">Continue</button>
            </div>
        </div>



    </div>

</div>








<div class="modal fade" id="myModal3" role="dialog" style="z-index : 1400">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #E74C3C";>
                <!--<button type="button" class="close" data-dismiss="modal">�</button> -->
                <h4 class="modal-title" style="color: white;">Attention!</h4>
            </div>
            <div class="modal-body" id="popup">
                {{sc10.displayMessage111}}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-success" ng-click="sc10.YesSubmitSC10(sc10.row);" data-dismiss="modal">YES</button>

                <button type="button" class="btn btn-default btn-success"  data-dismiss="modal" >No</button>
            </div>


        </div>
    </div>



</div>

<div class="modal fade" id="myModal5" role="dialog" style="z-index : 1400">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #E74C3C";>
                <!--<button type="button" class="close" data-dismiss="modal">�</button> -->
                <h4 class="modal-title" style="color: white;">Attention!</h4>
            </div>
            <div class="modal-body" id="popup">
                {{sc10.displayMessage}}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-success"  data-dismiss="modal" >Continue</button>
            </div>


        </div>
    </div>
</div>


<!--// For SFTP Success Modal //-->
<div class="modal fade" id="successSFTP" role="dialog" style="z-index : 1400">

    <div class="modal-dialog">
        <div class="modal-content" style="border-radius: 20px;">
            <div class="modal-header bg-success" style="border-radius: 20px;">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style=" margin-bottom: 12px;color: black;font-weight: 300;">Schedule-10</h4>
            </div>
            <div class="modal-body">
                {{sc10.displayMessage}}
            </div>
            <div class="modal-footer">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    Continue
                </button>
            </div>

        </div>
    </div>
</div>
<!--// For SFTP Success Modal //-->


<!--// failedSFTP Modal //-->
<div class="modal fade" id="failedSFTP" role="dialog" style="z-index : 1400">

    <div class="modal-dialog">
        <div class="modal-content" style="border-radius: 20px;">
            <div class="modal-header bg-danger" style="border-radius: 20px;">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" style=" margin-bottom: 12px;color: black;font-weight: 300;">Failed</h4>
            </div>
            <div class="modal-body">
                {{sc10.displayMessage}}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal" ng-click="sc10.redirect();"
                        data-backdrop="false" style="border-radius: 25px;
    margin-bottom: 8px;
    margin-right: 5px;">Continue
                </button>
            </div>

        </div>
    </div>
</div>
<!--// failedSFTP Modal //-->
