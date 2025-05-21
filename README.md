 <div class="container">
        <div class="row">
            <div class="col-md-1"></div>
            <div class="col-md-4">
                <div class="card card-signin">
                    <form name="signForm" ng-submit="main.login(user);" novalidate>
                        <div class="header header-primary text-center">
                            <h2>Balance Sheet</h2>
                            <!-- <div class="social-line">
                                    <a href="#pablo" class="btn btn-simple btn-just-icon">
                                        <i class="fa fa-facebook-square"></i>
                                    </a>
                                    <a href="#pablo" class="btn btn-simple btn-just-icon">
                                        <i class="fa fa-twitter"></i>
                                    </a>
                                    <a href="#pablo" class="btn btn-simple btn-just-icon">
                                        <i class="fa fa-google-plus"></i>
                                    </a>
                                </div> -->
                        </div>
                        <!-- <p class="text-divider">Or Be Classical</p> -->
                        <div class="content">

                            <div class="input-group" style="width: 100%;">
                                <i class="material-icons" style="margin-right: -13px">person</i> <span class="input-group-addon"
                                                                 style="padding: 12px 2px 0px;"> </span> <input
                                    type="text"
                                    class="form-control" placeholder="UserName" maxlength="16"
                                    id="userIdTemp" <%if(flag) {%>readonly="readonly"
                                <%} %>> <input
                                    type="hidden" ng-model="main.user.userId" name="main.user"/>
                            </div>

                            <!-- 	<div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="material-icons">email</i>
                                    </span>
                                    <input type="text" class="form-control" placeholder="Email...">
                                </div> -->
                            <!--
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <i class="material-icons">lock_outline</i>
                                    </span>
                                    <input type="password" class="form-control" placeholder="Password" name="user.password" id="password" ng-model="main.user.password">
                                </div> -->

                            <!-- If you want to add a checkbox to this form, uncomment this code

                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="optionsCheckboxes" checked>
                                        Subscribe to newsletter
                                    </label>
                                </div> -->
                        </div>
                        <div class="footer text-center">
                            <!-- <a href="#pablo" class="btn btn-simple btn-primary btn-lg">login</a> -->
                            <button type="submit" class="btn btn-primary btn-round">Sign
                                In
                            </button>
                        </div>
                    </form>
                </div>

            </div>
            <div class="col-md-2"></div>
            <div class="col-md-4" style="margin-top: 10px">
                <div class="panel panel-danger">
                    <div class="panel-heading">
                        <i class="fa fa-exclamation-triangle margin-r-5"></i> <b>Legal Warning</b></div>
                    <div class="panel-body">
                        <p class="text-justify"><b>This application is for the use of authorised users for authorised purposes only.
                            All the activities on this application may be recorded and monitored by the bank.
                            Individuals, using this application without authority or in excess of their authority
                            shall be treated as having violated the IT policy of the bank and are subject to
                            legal/ disciplinary actions as per the policy of the Bank.</b></p>
                    </div>
                </div>
            </div>
            <div class="col-md-1"></div>
        </div>
    </div>


Vulnerability	:HTML5: Form Validation Turned Off
Vulnerability Description in Detail: HTML5 validation of input form fields is disabled.
Likely Impact	:HTML5 validation of input form fields is disabled.
Recommendation: While it is much more important to validate input on the server side, validation on the client side adds another layer of protection and makes the process of filling out HTML forms more user-friendly. Avoid using novalidate and formnovalidate attributes.

    
