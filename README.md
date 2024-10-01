<head>
    <style>
        .validateColor {
            border: "1px solid red";
        }

        .validateColor1 {
            border: "0px solid white";
        }
    </style>

</head>
<div class="wrapper">
    <div class="header header-filter" style="background-image: url('assets/img/bg2.jpeg');">
        <div class="container">
            <div class="row tim-row">
                <div class="col-md-8 col-md-offset-2">
                    <div class="brand">
                        <h3 style="color: white;">Schedule 9</h3>
                    </div>
                </div>
            </div>

        </div>

    </div>
    <div class="main main-raised ">
        <div class="section">
            <div class="container" ng-cloak class="ng-cloak" ng-init="sc09.getInitialScreenData()">
                <div class="col-md-12">

                    <script type="text/javascript">
                        $(".decimal-2-places").numeric({decimalPlaces: 2});

                    </script>


                    <form name="sc09Form" ng-submit="sc09.SubmitSC09Report(sc09.row);">
                        <input type="hidden" id="csrfPreventionSalt" value="${csrfPreventionSalt}"/>
                        <table id="example1" class="table" border="1">
                            <thead>
                            <tr>
                                <th style="text-align:center;">Classification of Advances (Excluding non-advances<br>related
                                    items debited to Recalled Assets<br>and interest free Staff Advances)(A1 = A2 = A3)
                                </th>
                                <th style="text-align:center;">Standard</th>
                                <th style="text-align:center;">Sub-standard</th>
                                <th style="text-align:center;">Doubtful</th>
                                <th style="text-align:center;">Loss</th>
                                <th style="text-align:center;" ng-if="sc09.adjColmn==true">Adjustment</th>
                                <th style="text-align:center;">Total</th>
                                <th style="text-align:center;">YSA DATA</th>
                            </tr>
                            </thead>
                            <tbody ng-readonly="true">
                            <tr>
                                <td colspan="7"><b>A-1. Facility Wise Classification</b></td>


                            <tr>
                                <td>[i] Bills Purchased and Discounted less bills rediscounted $</td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.facility_Standard_1"
                                           style="text-align:right;" name="facility_Standard_1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.facility_SubStandard_1"
                                           style="text-align:right;" name="facility_SubStandard_1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.facility_Doubtful_1"
                                           style="text-align:right;" name="facility_Doubtful_1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.facility_Loss_1"
                                           style="text-align:right;" name="facility_Loss_1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td ng-if="sc09.adjColmn==true">
                                    <input type="text" maxlength="18"
                                    ng-model="sc09.row.facility_Adj_1"
                                    style="text-align:right;" name="facility_Adj_1"
                                    class="form-control decimal-2-places"
                                    ng-readonly="sc09.adjColmn==true"/></td>



								<%--Change Here :: New Column Added Here--%>
								<td><input type="hidden" ng-model="sc09.row.facility_Total_1" style="text-align:right;"
                                           name="facility_Total_1" class="form-control decimal-2-places"
                                           ng-readonly="true"/>
                                     <%--Additional Total--%>
                                 <input type="text" class="form-control decimal-2-places" ng-model="sc09.row.facility_Total_Display1_SUM"
                                           style="text-align:right;" <%--id="facility_Total_Display1"--%>
                                           <%--name="facility_Total_Display1"--%> ng-readonly="true"/>
                                </td>

                                <%--DOWN--%>
                                <td><input type="hidden" ng-model="sc09.row.facility_Total_1" style="text-align:right;"
                                           name="facility_Total_1" class="form-control decimal-2-places"
                                           ng-readonly="true"/>

                                    <input type="text" ng-model="sc09.row.facility_Total_Display1"
                                           style="text-align:right;" id="facility_Total_Display1"
                                           name="facility_Total_Display1"  class="form-control decimal-2-places" " ng-readonly="true"/>
                                </td>
                            </tr>


                            <tr>
                                <td>[ii] Cash Credits, Overdrafts, Loans repayable on Demand and Recalled Assets $$</td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.facility_Standard_2"
                                           style="text-align:right;" name="facility_Standard_2"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.facility_SubStandard_2"
                                           style="text-align:right;" name="facility_SubStandard_2"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.facility_Doubtful_2"
                                           style="text-align:right;" name="facility_Doubtful_2"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.facility_Loss_2"
                                           style="text-align:right;" name="facility_Loss_2"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td ng-if="sc09.adjColmn==true"><input type="text" maxlength="18"
                                                                       ng-model="sc09.row.facility_Adj_2"
                                                                       style="text-align:right;" name="facility_Adj_2"
                                                                       class="form-control decimal-2-places"
                                                                       ng-readonly="sc09.adjColmn==true"/></td>


                                <%--Change Here :: New Column Added Here--%>
								<td><input type="hidden" ng-model="sc09.row.facility_Total_2" style="text-align:right;"
                                           name="facility_Total_2" class="form-control decimal-2-places"
                                           ng-readonly="true"/>
                                    <input type="text" ng-model="sc09.row.facility_Total_Display2_SUM"
                                           style="text-align:right;" <%--id="facility_Total_Display2"--%>
                                           <%--name="facility_Total_Display2"--%>  class="form-control decimal-2-places" " ng-readonly="true"/>
                                </td>

                                <%--DOWN--%>

                                <td><input type="hidden" ng-model="sc09.row.facility_Total_2" style="text-align:right;"
                                           name="facility_Total_2" class="form-control decimal-2-places"
                                           ng-readonly="true"/>
                                    <input type="text" ng-model="sc09.row.facility_Total_Display2"
                                           style="text-align:right;" id="facility_Total_Display2"
                                           name="facility_Total_Display2"  class="form-control decimal-2-places" " ng-readonly="true"/>
                                </td>
                            </tr>


                            <tr>
                                <td>[iii] Term Loans , Agricultural Term Loans, FCNRB Term Loan</td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.facility_Standard_3"
                                           style="text-align:right;" name="facility_Standard_3"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.facility_SubStandard_3"
                                           style="text-align:right;" name="facility_SubStandard_3"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.facility_Doubtful_3"
                                           style="text-align:right;" name="facility_Doubtful_3"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.facility_Loss_3"
                                           style="text-align:right;" name="facility_Loss_3"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td ng-if="sc09.adjColmn==true"><input type="text" maxlength="18"
                                                                       ng-model="sc09.row.facility_Adj_3"
                                                                       style="text-align:right;" name="facility_Adj_3"
                                                                       class="form-control decimal-2-places"
                                                                       ng-readonly="sc09.adjColmn==true"/></td>

								<%--Change Here--%>
								<td><input type="hidden" ng-model="sc09.row.facility_Total_3" style="text-align:right;"
                                           name="facility_Total_3" class="form-control decimal-2-places"
                                           ng-readonly="true"/>
                                    <input type="text" ng-model="sc09.row.facility_Total_Display3_SUM"
                                           style="text-align:right;" <%--name="facility_Total_Display3"--%>
                                           <%--id="facility_Total_Display3"--%>  class="form-control decimal-2-places" " ng-readonly="true"/>
								</td>

                                <%--DOWN--%>


                                <td><input type="hidden" ng-model="sc09.row.facility_Total_3" style="text-align:right;"
                                           name="facility_Total_3" class="form-control decimal-2-places"
                                           ng-readonly="true"/>
                                    <input type="text" ng-model="sc09.row.facility_Total_Display3"
                                           style="text-align:right;" name="facility_Total_Display3"
                                           id="facility_Total_Display3"  class="form-control decimal-2-places" ng-readonly="true"/>
                                </td>
                            </tr>


                            <tr>
                                <td>Total A-1</td>
                                <td>
                                    <input type="text" value="" ng-model="sc09.row.facility_Standard_Total"
                                           id="facility_Standard_Total" style="text-align:right;"
                                           name="facility_Standard_Total" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                                <td><input type="text" ng-model="sc09.row.facility_SubStandard_Total"
                                           style="text-align:right;" id="facility_SubStandard_Total"
                                           name="facility_SubStandard_Total" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                                <td><input type="text" ng-model="sc09.row.facility_Doubtful_Total"
                                           style="text-align:right;" id="facility_Doubtful_Total"
                                           name="facility_Doubtful_Total" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                                <td><input type="text" ng-model="sc09.row.facility_Loss_Total" style="text-align:right;"
                                           id="facility_Loss_Total" name="facility_Loss_Total"
                                           class="form-control decimal-2-places" ng-readonly="true"/></td>
                                <td ng-if="sc09.adjColmn==true"><input type="text"
                                                                       ng-model="sc09.row.facility_Adj_Total"
                                                                       style="text-align:right;" id="facility_Adj_Total"
                                                                       name="facility_Adj_Total"
                                                                       class="form-control decimal-2-places"
                                                                       ng-readonly="true"/></td>


								<%--Change Here--%>
								<td><input type="hidden" ng-model="sc09.row.facility_Total_Total"
                                           style="text-align:right;" name="facility_Total_Total"
                                           class="form-control decimal-2-places" ng-readonly="true"/>
                                    <%--Final Total--%>
                                    <input type="text" class="form-control decimal-2-places" ng-model="sc09.row.facility_Total_TotalDisplay_SUM"
                                           style="text-align:right;"<%-- id="facility_Total_TotalDisplay"--%>
                                           <%--name="facility_Total_TotalDisplay"--%>
                                           ng-readonly="true"/>
								</td>

                                <%--DOWN--%>

                                <td><input type="hidden" ng-model="sc09.row.facility_Total_Total"
                                           style="text-align:right;" name="facility_Total_Total"
                                           class="form-control decimal-2-places" ng-readonly="true"/>
                                    <input type="text" ng-model="sc09.row.facility_Total_TotalDisplay"
                                           style="text-align:right;" id="facility_Total_TotalDisplay"
                                           name="facility_Total_TotalDisplay" class="form-control decimal-2-places"
                                           ng-readonly="true"/>
                                </td>

                            </tr>


                            <tr>
                                <td colspan="7"><b>A. 2 Security Wise Classifications</b></td>

                            </tr>


                            <tr>
                                <td>[i] Secured by Tangible Assets</td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.security_Standard_1"
                                           style="text-align:right;" name="security_Standard_1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.security_SubStandard_1"
                                           style="text-align:right;" name="security_SubStandard_1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.security_Doubtful_1"
                                           style="text-align:right;" name="security_Doubtful_1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.security_Loss_1"
                                           style="text-align:right;" name="security_Loss_1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td ng-if="sc09.adjColmn==true"><input type="text" maxlength="18"
                                                                       ng-model="sc09.row.security_Adj_1"
                                                                       style="text-align:right;" name="security_Adj_1"
                                                                       class="form-control decimal-2-places"/></td>

                                <td><input type="text" ng-model="sc09.row.security_Total_1" style="text-align:right;"
                                           name="security_Total_1" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                            </tr>


                            <tr>
                                <td>[ii] Covered by Bank/ DICGC/ ECGC/ CGTSI/ Govt Guarantee</td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.security_Standard_2"
                                           style="text-align:right;" name="security_Standard_2"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.security_SubStandard_2"
                                           style="text-align:right;" name="security_SubStandard_2"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.security_Doubtful_2"
                                           style="text-align:right;" name="security_Doubtful_2"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.security_Loss_2"
                                           style="text-align:right;" name="security_Loss_2"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td ng-if="sc09.adjColmn==true"><input type="text" maxlength="18"
                                                                       ng-model="sc09.row.security_Adj_2"
                                                                       style="text-align:right;" name="security_Adj_2"
                                                                       class="form-control decimal-2-places"/></td>

                                <td><input type="text" ng-model="sc09.row.security_Total_2" style="text-align:right;"
                                           name="security_Total_2" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                            </tr>


                            <tr>
                                <td>[iii] Unsecured</td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.security_Standard_3"
                                           style="text-align:right;" name="security_Standard_3"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.security_SubStandard_3"
                                           style="text-align:right;" name="security_SubStandard_3"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.security_Doubtful_3"
                                           style="text-align:right;" name="security_Doubtful_3"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.security_Loss_3"
                                           style="text-align:right;" name="security_Loss_3"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td ng-if="sc09.adjColmn==true"><input type="text" maxlength="18"
                                                                       ng-model="sc09.row.security_Adj_3"
                                                                       style="text-align:right;" name="security_Adj_3"
                                                                       class="form-control decimal-2-places"/></td>

                                <td><input type="text" ng-model="sc09.row.security_Total_3" style="text-align:right;"
                                           name="security_Total_3" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                            </tr>


                            <tr>
                                <td>Total A-2</td>
                                <td><input type="text" ng-model="sc09.row.security_Standard_Total"
                                           style="text-align:right;" id="security_Standard_Total"
                                           name="security_Standard_Total" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                                <td><input type="text" ng-model="sc09.row.security_SubStandard_Total"
                                           style="text-align:right;" id="security_SubStandard_Total"
                                           name="security_SubStandard_Total" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                                <td><input type="text" ng-model="sc09.row.security_Doubtful_Total"
                                           style="text-align:right;" id="security_Doubtful_Total"
                                           name="security_Doubtful_Total" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                                <td><input type="text" ng-model="sc09.row.security_Loss_Total" style="text-align:right;"
                                           id="security_Loss_Total" name="security_Loss_Total"
                                           class="form-control decimal-2-places" ng-readonly="true"/></td>
                                <td ng-if="sc09.adjColmn==true"><input type="text"
                                                                       ng-model="sc09.row.security_Adj_Total"
                                                                       style="text-align:right;" id="security_Adj_Total"
                                                                       name="security_Adj_Total"
                                                                       class="form-control decimal-2-places"
                                                                       ng-readonly="true"/></td>

                                <td><input type="text" ng-model="sc09.row.security_Total_Total"
                                           style="text-align:right;" id="security_Total_Total"
                                           name="security_Total_Total" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                            </tr>


                            <tr>
                                <td colspan="7"><b>A.3 Sector -Wise Classifications</b></td>

                            </tr>


                            <tr>
                                <td colspan="7">a)In India</td>

                            </tr>


                            <tr>
                                <td>[i] Priority</td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Standard_a1"
                                           style="text-align:right;" name="sector_Standard_a1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_SubStandard_a1"
                                           style="text-align:right;" name="sector_SubStandard_a1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Doubtful_a1"
                                           style="text-align:right;" name="sector_Doubtful_a1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Loss_a1"
                                           style="text-align:right;" name="sector_Loss_a1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td ng-if="sc09.adjColmn==true"><input type="text" maxlength="18"
                                                                       ng-model="sc09.row.sector_Adj_a1"
                                                                       style="text-align:right;" name="sector_Adj_a1"
                                                                       class="form-control decimal-2-places"/></td>

                                <td><input type="text" ng-model="sc09.row.sector_Total_a1" style="text-align:right;"
                                           name="sector_Total_a1" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                            </tr>


                            <tr>
                                <td> [ii] Public</td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Standard_a2"
                                           style="text-align:right;" name="sector_Standard_a2"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_SubStandard_a2"
                                           style="text-align:right;" name="sector_SubStandard_a2"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Doubtful_a2"
                                           style="text-align:right;" name="sector_Doubtful_a2"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Loss_a2"
                                           style="text-align:right;" name="sector_Loss_a2"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td ng-if="sc09.adjColmn==true"><input type="text" maxlength="18"
                                                                       ng-model="sc09.row.sector_Adj_a2"
                                                                       style="text-align:right;" name="sector_Adj_a2"
                                                                       class="form-control decimal-2-places"/></td>

                                <td><input type="text" ng-model="sc09.row.sector_Total_a2" style="text-align:right;"
                                           name="sector_Total_a2" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                            </tr>


                            <tr>
                                <td>[iii] Banks in India*</td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Standard_a3"
                                           style="text-align:right;" name="sector_Standard_a3"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_SubStandard_a3"
                                           style="text-align:right;" name="sector_SubStandard_a3"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Doubtful_a3"
                                           style="text-align:right;" name="sector_Doubtful_a3"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Loss_a3"
                                           style="text-align:right;" name="sector_Loss_a3"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td ng-if="sc09.adjColmn==true"><input type="text" maxlength="18"
                                                                       ng-model="sc09.row.sector_Adj_a3"
                                                                       style="text-align:right;" name="sector_Adj_a3"
                                                                       class="form-control decimal-2-places"/></td>

                                <td><input type="text" ng-model="sc09.row.sector_Total_a3" style="text-align:right;"
                                           name="sector_Total_a3" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                            </tr>


                            <tr>
                                <td>[iv] Others</td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Standard_a4"
                                           style="text-align:right;" name="sector_Standard_a4"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_SubStandard_a4"
                                           style="text-align:right;" name="sector_SubStandard_a4"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Doubtful_a4"
                                           style="text-align:right;" name="sector_Doubtful_a4"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Loss_a4"
                                           style="text-align:right;" name="sector_Loss_a4"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td ng-if="sc09.adjColmn==true"><input type="text" maxlength="18"
                                                                       ng-model="sc09.row.sector_Adj_a4"
                                                                       style="text-align:right;" name="sector_Adj_a4"
                                                                       class="form-control decimal-2-places"/></td>

                                <td><input type="text" ng-model="sc09.row.sector_Total_a4" style="text-align:right;"
                                           name="sector_Total_a4" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                            </tr>


                            <tr>
                                <td>TOTAL IN INDIA (i+ii+iii+iv)</td>
                                <td><input type="text" ng-model="sc09.row.sector_Standard_a_Total"
                                           style="text-align:right;" name="sector_Standard_a_Total"
                                           class="form-control decimal-2-places" ng-readonly="true"/></td>
                                <td><input type="text" ng-model="sc09.row.sector_SubStandard_a_Total"
                                           style="text-align:right;" name="sector_SubStandard_a_Total"
                                           class="form-control decimal-2-places" ng-readonly="true"/></td>
                                <td><input type="text" ng-model="sc09.row.sector_Doubtful_a_Total"
                                           style="text-align:right;" name="sector_Doubtful_a_Total"
                                           class="form-control decimal-2-places" ng-readonly="true"/></td>
                                <td><input type="text" ng-model="sc09.row.sector_Loss_a_Total" style="text-align:right;"
                                           name="sector_Loss_a_Total" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                                <td ng-if="sc09.adjColmn==true"><input type="text"
                                                                       ng-model="sc09.row.sector_Adj_a_Total"
                                                                       style="text-align:right;"
                                                                       name="sector_Adj_a_Total"
                                                                       class="form-control decimal-2-places"
                                                                       ng-readonly="true"/></td>

                                <td><input type="text" ng-model="sc09.row.sector_a_Total_a_Total"
                                           style="text-align:right;" name="sector_a_Total_a_Total"
                                           class="form-control decimal-2-places" ng-readonly="true"></td>
                            </tr>


                            <tr>
                                <td colspan="7">b) Outside India ( Excluding Foreign LCs and BGs)</td>

                            </tr>


                            <tr>
                                <td>[i] Due from Banks</td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Standard_b1"
                                           style="text-align:right;" name="sector_Standard_b1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_SubStandard_b1"
                                           style="text-align:right;" name="sector_SubStandard_b1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Doubtful_b1"
                                           style="text-align:right;" name="sector_Doubtful_b1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Loss_b1"
                                           style="text-align:right;" name="sector_Loss_b1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td ng-if="sc09.adjColmn==true"><input type="text" maxlength="18"
                                                                       ng-model="sc09.row.sector_Adj_b1"
                                                                       style="text-align:right;" name="sector_Adj_b1"
                                                                       class="form-control decimal-2-places"/></td>

                                <td><input type="text" ng-model="sc09.row.sector_Total_b1" style="text-align:right;"
                                           name="sector_Total_b1" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                            </tr>


                            <tr>
                                <td colspan="7">[ii] Due from Others</td>
                                <%--	<td><input type="text" maxlength="18" ng-model="sc09.row.sector_Standard_b2" style="text-align:right;" name="sector_Standard_b2"    class="form-control decimal-2-places"  ng-readonly="sc09.adjColmn==true"/></td>
                                    <td><input type="text" maxlength="18" ng-model="sc09.row.sector_SubStandard_b2" style="text-align:right;" name="sector_SubStandard_b2"    class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/></td>
                                    <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Doubtful_b2" style="text-align:right;" name="sector_Doubtful_b2"    class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true" /></td>
                                    <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Loss_b2" style="text-align:right;" name="sector_Loss_b2"    class="form-control decimal-2-places"ng-readonly="sc09.adjColmn==true" /></td>
                                        <td ng-if="sc09.adjColmn==true"><input type="text" maxlength="18" ng-model="sc09.row.sector_Adj_b2" style="text-align:right;" name="sector_Adj_b2"    class="form-control decimal-2-places" /></td>

                                        <td><input type="text" ng-model="sc09.row.sector_Total_b2" style="text-align:right;"name="sector_Total_b2" class="form-control decimal-2-places" ng-readonly="true"/></td>--%>
                            </tr>


                            <tr>
                                <td>[1] Bills Purchased and Discounted</td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Standard_1"
                                           style="text-align:right;" name="sector_Standard_1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_SubStandard_1"
                                           style="text-align:right;" name="sector_SubStandard_1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Doubtful_1"
                                           style="text-align:right;" name="sector_Doubtful_1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Loss_1"
                                           style="text-align:right;" name="sector_Loss_1"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td ng-if="sc09.adjColmn==true"><input type="text" maxlength="18"
                                                                       ng-model="sc09.row.sector_Adj_1"
                                                                       style="text-align:right;" name="sector_Adj_1"
                                                                       class="form-control decimal-2-places"/></td>

                                <td><input type="text" ng-model="sc09.row.sector_Total_1" style="text-align:right;"
                                           name="sector_Total_1" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                            </tr>


                            <tr>
                                <td>[2] Syndicated Loans</td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Standard_2"
                                           style="text-align:right;" name="sector_Standard_2"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_SubStandard_2"
                                           style="text-align:right;" name="sector_SubStandard_2"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Doubtful_2"
                                           style="text-align:right;" name="sector_Doubtful_2"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Loss_2"
                                           style="text-align:right;" name="sector_Loss_2"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td ng-if="sc09.adjColmn==true"><input type="text" maxlength="18"
                                                                       ng-model="sc09.row.sector_Adj_2"
                                                                       style="text-align:right;" name="sector_Adj_2"
                                                                       class="form-control decimal-2-places"/></td>

                                <td><input type="text" ng-model="sc09.row.sector_Total_2" style="text-align:right;"
                                           name="sector_Total_2" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                            </tr>


                            <tr>
                                <td> [3] Others</td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Standard_3"
                                           style="text-align:right;" name="sector_Standard_3"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_SubStandard_3"
                                           style="text-align:right;" name="sector_SubStandard_3"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Doubtful_3"
                                           style="text-align:right;" name="sector_Doubtful_3"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td><input type="text" maxlength="18" ng-model="sc09.row.sector_Loss_3"
                                           style="text-align:right;" name="sector_Loss_3"
                                           class="form-control decimal-2-places" ng-readonly="sc09.adjColmn==true"/>
                                </td>
                                <td ng-if="sc09.adjColmn==true"><input type="text" maxlength="18"
                                                                       ng-model="sc09.row.sector_Adj_3"
                                                                       style="text-align:right;" name="sector_Adj_3"
                                                                       class="form-control decimal-2-places"/></td>

                                <td><input type="text" ng-model="sc09.row.sector_Total_3" style="text-align:right;"
                                           name="sector_Total_3" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                            </tr>


                            <tr>
                                <td>TOTAL IN OUTSIDE INDIA (i+ ii.1+ii.2+ii.3)</td>
                                <td><input type="text" ng-model="sc09.row.sector_Standard_b_Total"
                                           style="text-align:right;" name="sector_Standard_b_Total"
                                           class="form-control decimal-2-places" ng-readonly="true"/></td>
                                <td><input type="text" ng-model="sc09.row.sector_SubStandard_b_Total"
                                           style="text-align:right;" name="sector_SubStandard_b_Total"
                                           class="form-control decimal-2-places" ng-readonly="true"/></td>
                                <td><input type="text" ng-model="sc09.row.sector_Doubtful_b_Total"
                                           style="text-align:right;" name="sector_Doubtful_b_Total"
                                           class="form-control decimal-2-places" ng-readonly="true"/></td>
                                <td><input type="text" ng-model="sc09.row.sector_Loss_b_Total" style="text-align:right;"
                                           name="sector_Loss_b_Total" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                                <td ng-if="sc09.adjColmn==true"><input type="text"
                                                                       ng-model="sc09.row.sector_Adj_b_Total"
                                                                       style="text-align:right;"
                                                                       name="sector_Adj_b_Total"
                                                                       class="form-control decimal-2-places"
                                                                       ng-readonly="true"/></td>

                                <td><input type="text" ng-model="sc09.row.sector_b_Total_b_Total"
                                           style="text-align:right;" name="sector_b_Total_b_Total"
                                           class="form-control decimal-2-places" ng-readonly="true"></td>
                            </tr>


                            <tr>
                                <td><b>Total advances A3 ( a+b)</b></td>
                                <td><input type="text" ng-model="sc09.row.sector_Standard_ab_Total"
                                           style="text-align:right;" id="sector_Standard_ab_Total"
                                           name="sector_Standard_ab_Total" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                                <td><input type="text" ng-model="sc09.row.sector_SubStandard_ab_Total"
                                           style="text-align:right;" id="sector_SubStandard_ab_Total"
                                           name="sector_SubStandard_ab_Total" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                                <td><input type="text" ng-model="sc09.row.sector_Doubtful_ab_Total"
                                           style="text-align:right;" id="sector_Doubtful_ab_Total"
                                           name="sector_Doubtful_ab_Total" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                                <td><input type="text" ng-model="sc09.row.sector_Loss_ab_Total"
                                           style="text-align:right;" id="sector_Loss_ab_Total"
                                           name="sector_Loss_ab_Total" class="form-control decimal-2-places"
                                           ng-readonly="true"/></td>
                                <td ng-if="sc09.adjColmn==true"><input type="text"
                                                                       ng-model="sc09.row.sector_Adj_ab_Total"
                                                                       style="text-align:right;"
                                                                       id="sector_Adj_ab_Total"
                                                                       name="sector_Adj_ab_Total"
                                                                       class="form-control decimal-2-places"
                                                                       ng-readonly="true"/></td>

                                <td><input type="text" ng-model="sc09.row.sector_ab_Total_ab_Total"
                                           style="text-align:right;" id="sector_ab_Total_ab_Total"
                                           name="sector_ab_Total_ab_Total" class="form-control decimal-2-places"
                                           ng-readonly="true"></td>
                            </tr>


                            </tbody>
                        </table>
                        <button class="btn btn-warning" id="savebtn" type="submit" ng-click="sc09.row.save = true">
                            Save
                        </button>

                        <button type="submit" class="btn btn-default btn-success" id="submitbtn"
                                ng-click="sc09.row.save = false">Submit
                        </button>

                    </form>

                    <%--<div ng-if="sc09.showView==true">
                        <button type="submit" class="btn btn-default btn-primary" ng-click="sc09.viewSubmitData('V');">View</button>    </fieldset>
                ng-if="sc09.isData ==false"
                    </div>--%>


                    <%--<div class="checkbox" ng-if="sc09.showView==true">
                        <label> <input type="checkbox" ng-click="sc09.viewSubmitData('V');"
                                       name="checkView" id="checkView"
                                       ng-model="sc09.checkView"
                        /><span
                                class="checkbox-material"><span class="check"></span></span>
                            <br>
                        </label><span class="label label-primary">View</span>
                    </div> </fieldset>
                    <fieldset ng-disabled="sc09.isData ==true" >

                    --%>


                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal2" role="dialog" style="z-index : 1400">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #E74C3C" ;>
                <!--<button type="button" class="close" data-dismiss="modal">�</button> -->
                <h4 class="modal-title" style="color: white;">Errors!</h4>
            </div>
            <div class="modal-body" id="popup">
                <table id="example2" class="table table-hover table-responsive no-padding dataTable no-footer"
                       style="width: 100%">
                    <thead>
                    <tr>
                        <!-- 	<th style="width: 5%"></th> -->
                    </thead>
                    <tr data-ng-repeat="row in validateArray">
                        <td>#.</td>
                        <td>{{row}}</td>
                    </tr>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-success" data-dismiss="modal">Continue</button>
            </div>
        </div>


    </div>

</div>


<div class="modal fade" id="myModal1" role="dialog" style="z-index : 1400">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #E74C3C" ;>
                <!--<button type="button" class="close" data-dismiss="modal">�</button> -->
                <h4 class="modal-title" style="color: white;">Attention!</h4>
            </div>
            <div class="modal-body" id="popup">
                {{sc09.displayMessage}}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-success" data-dismiss="modal"
                        ng-click="sc09.redirect();">Continue
                </button>
            </div>


        </div>
    </div>


</div>


<div class="modal fade" id="myModal3" role="dialog" style="z-index : 1400">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #E74C3C" ;>
                <!--<button type="button" class="close" data-dismiss="modal">�</button> -->
                <h4 class="modal-title" style="color: white;">Attention!</h4>
            </div>
            <div class="modal-body" id="popup">
                {{sc09.displayMessage111}}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-success" ng-click="sc09.YesSubmitSc09(sc09.row);"
                        data-dismiss="modal">YES
                </button>

                <button type="button" class="btn btn-default btn-success" data-dismiss="modal">No</button>
            </div>


        </div>
    </div>


</div>


<div class="modal fade" id="myModal9" role="dialog" style="z-index : 1400">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header" style="background-color: #E74C3C" ;>
                <!--<button type="button" class="close" data-dismiss="modal">?</button> -->
                <h4 class="modal-title" style="color: white;">Attention!</h4>
            </div>
            <div class="modal-body" id="popup">
                {{sc09.displayMessage}}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-success" data-dismiss="modal">Continue</button>
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
                <h4 class="modal-title" style=" margin-bottom: 12px;color: black;font-weight: 300;">Success</h4>
            </div>
            <div class="modal-body">
                {{sc09.displayMessage}}
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
                {{sc09.displayMessage}}
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal" ng-click="sc09.redirect();"
                        data-backdrop="false" style="border-radius: 25px;
    margin-bottom: 8px;
    margin-right: 5px;">Continue
                </button>
            </div>

        </div>
    </div>
</div>
<!--// failedSFTP Modal //-->

This is my JSP page on this page save Button whenevr i press enter the Save button automatically get hitted how to prevent this button press on hitting of enter.


    
