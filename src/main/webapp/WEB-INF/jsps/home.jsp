<%--
  Created by IntelliJ IDEA.
  User: Vivek
  Date: 29/6/17
  Time: 1:07 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>${merchantDo.merchantName} - Redemption</title>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0" />
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!--Import materialize.css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/materialize/css/materialize.css" media="screen,projection">
    <!--Import style.css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css" media="all">
    <!--Import jQuery-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/jquery/jquery.js"></script>
    <!--Import materialize -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/materialize/js/materialize.js"></script>
    <!--Import Charts -->
    <!--<script type="text/javascript" src="lib/vendor/chart/js/chart.js"></script>-->

    <meta name="apple-itunes-app" content="app-id=1234567890">
    <meta name="HandheldFriendly" content="true">

    <meta name="theme-color" content="#0589ca">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Uahoy Merchant">
    <link rel="manifest" href="${pageContext.request.contextPath}/assets/manifest.json">

</head>

<body>
<div class="navbar-fixed">
    <nav class="light-blue darken-1">
        <div id="headerNav" class="nav-wrapper app-nav container">
            <div class="nav-wrapper app-nav">
                <a href="${pageContext.request.contextPath}/home" class="brand-logo">
                    <img width="50" src="http://marketeer.uahoy.com/ahoy/merchant/logo/${merchantDo.merchantLogo}" onerror="src='${pageContext.request.contextPath}/assets/icon/merhant.svg'">
                </a>
                <ul class="right">
                    <li><a href="logout">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>
</div>
<main>
    <div class="row">
        <div class="col s12">
            <div class="container">
                <div class="row">
                    <div class="col s12"></div>
                </div>

                <div id="checkCoupon" class="card-panel create-campaign-card">
                    <form action="couponinfo" method="post">
                        <div class="input-field col s12">
                            <span class="font-size-20 light-blue-text text-darken-1">Check coupon code here</span>
                        </div>

                        <div class="input-field col s12">
                            <input id="txt-coupon" name="coupon" type="text" class="validate">
                            <label for="txt-coupon">Enter Coupon Code</label>
                        </div>

                        <div class="input-field col s12 center-align">
                            <button class="btn waves-effect waves-light orange darken-3" type="submit" id="btn-info">Check Coupon</button>
                        </div>
                    </form>
                </div>



                <div id="redeemCoupon" style="display: none;" class="card-panel create-campaign-card">
                    <c:if test="${couponResponse !=null && couponResponse.header ne null && couponResponse.header.code eq 200}">

                        <c:set var="couponDetails" value="${couponResponse.couponDetails}"/>

                        <div class="input-field col s12">
                            <input type="text" class="blue-grey-text" id="coupon_" name="coupon" value="${coupon}" readonly="true">
                            <label for="coupon_" class="active">Coupon Code</label>
                        </div>

                        <div class="input-field col s12">
                            <textarea id="description" class="materialize-textarea" readonly="true" style="height: 20.9969px;">${couponDetails.campaign}</textarea>
                            <label for="description" class="active">Campaign Description</label>
                        </div>

                        <div class="input-field col s12">
                            <input id="createdOn" type="text" class="blue-grey-text" value="${couponDetails.createdOn}" readonly="true">
                            <label for="createdOn" class="active">Created On</label>
                        </div>

                        <c:choose>
                            <c:when test="${couponDetails.deviceid ne null && couponDetails.deviceid ne ''}">
                                <div class="input-field col s12">
                                    <input id="dev_id" type="text" class="blue-grey-text" value="${couponDetails.deviceid}" readonly="true">
                                    <label for="dev_id" class="active">Device Id</label>
                                </div>
                            </c:when>
                            <c:when test="${couponDetails.googleid ne null && couponDetails.googleid ne ''}">
                                <div class="input-field col s12">
                                    <input id="g_id" type="text" class="blue-grey-text" value="${couponDetails.deviceid}" readonly="true">
                                    <label for="g_id" class="active">Google Id</label>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${couponDetails.name ne null && couponDetails.name ne ''}">
                                    <div class="input-field col s12">
                                        <input id="u_name" type="text" class="blue-grey-text"
                                               value="${couponDetails.name}" readonly="true">
                                        <label for="u_name">User</label>
                                    </div>
                                </c:if>
                                <c:if test="${couponDetails.email ne null && couponDetails.email ne ''}">

                                    <div class="input-field col s12">
                                        <input id="u_email" type="text" class="blue-grey-text"
                                               value="${couponDetails.email}" readonly="true">
                                        <label for="u_email">Email</label>
                                    </div>
                                </c:if>
                                <c:if test="${couponDetails.msisdn ne null && couponDetails.msisdn ne ''}">
                                    <div class="input-field col s12">
                                        <input id="u_msisdn" type="text" class="blue-grey-text"
                                               value="${couponDetails.msisdn}" readonly="true">
                                        <label for="u_msisdn">Mobile Number</label>
                                    </div>
                                </c:if>
                            </c:otherwise>
                        </c:choose>

                        <div class="input-field col s12">
                            <input id="coupon_status" type="text" class="blue-grey-text" value="${couponDetails.status}" readonly="true">
                            <label for="coupon_status" class="active">Status</label>
                        </div>

                        <c:choose>
                            <c:when test="${couponDetails.isRedeem eq 0 && couponDetails.status eq 'Valid'}">
                                <div class="input-field col s12">
                                    <input id="coupon_valid" type="text" class="blue-grey-text"
                                           value="${couponDetails.validity}" readonly="true">
                                    <label for="coupon_valid">Valid Till</label>
                                </div>
                                <form action="redeem" method="post">
                                    <div class="input-field col s12">
                                        <c:set var="branchList" value="${couponDetails.branchList}"/>
                                        <select id="c_branch" name="branchid">
                                            <option disabled selected>Choose your branch</option>
                                            <c:if test="${branchList ne null && branchList.size() gt 0}">
                                                <c:forEach items="${branchList}" var="branchObj">
                                                    <option value="${branchObj[0]}">${branchObj[1]}, ${branchObj[2]}</option>
                                                </c:forEach>
                                            </c:if>
                                        </select>
                                        <label for="c_branch">Select Branch</label>
                                    </div>

                                    <div class="input-field col s6 center-align">
                                        <button class="btn waves-effect waves-light orange darken-3" type="button" onclick="location.href='${pageContext.request.contextPath}/home'">Home</button>
                                    </div>
                                    <div class="input-field col s6 center-align">
                                        <input type="text" class="blue-grey-text" name="couponvalueid"
                                               value="${couponDetails.id}" readonly="true" style="display:none">

                                        <input id="coupon" type="text" class="blue-grey-text" name="coupon"
                                               value="${coupon}" readonly="true" style="display:none">

                                        <button type="submit"  id="btn-redeem" class="waves-effect waves-gray btn teal white-text">Redeem Now</button>
                                    </div>

                                </form>
                            </c:when>
                            <c:when test="${couponDetails.isRedeem eq 1}">
                                <div class="input-field col s12">
                                    <input id="redeemed_on" type="text" class="blue-grey-text"
                                           value="${couponDetails.redeemedOn}" readonly="true">
                                    <label for="redeemed_on">Redeemed On</label>
                                </div>
                                <div class="input-field col s6 center-align">
                                    <button class="btn waves-effect waves-light orange darken-3" onclick="location.href='${pageContext.request.contextPath}/home'" type="button">Home</button>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="input-field col s6 center-align">
                                    <button class="btn waves-effect waves-light orange darken-3" type="button" onclick="location.href='${pageContext.request.contextPath}/home'" >Home</button>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</main>
<footer class="page-footer grey darken-1" id="footer">
    <div class="footer-copyright">
        <div class="container">
            &copy; <span id="year"></span> Ahoy Telecom Pvt. Ltd.
        </div>
    </div>
</footer>


<div id="errorModal" class="modal confirm-modal">
    <div class="modal-content">
        <h5 id="resphead"></h5>
        <span id="respmessage"></span>
    </div>
    <div class="modal-footer"><button class="modal-action modal-close waves-effect waves-green btn-flat">Close</button></div>
</div>
<!--<script type="text/javascript" src="./lib/src/js/tile.js"></script>-->
<script type="text/javascript" src="./assets/js/custom.js"></script>

<script>
    $(document).ready(function() {
        // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
        $('.modal').modal();
        $('select').material_select();
    });
    var w = window,
        d = document,
        e = d.documentElement,
        g = d.getElementsByTagName('body')[0],
        y = w.innerHeight || e.clientHeight || g.clientHeight;
    var height = y - document.getElementById('footer').offsetHeight;
    var date = new Date();
    var year = date.getFullYear();
    document.getElementById("year").innerText = year;
</script>

<script type="text/javascript">

    $(document).ready(function () {

        if(${responseHead ne null}){
            $("#resphead").text('${responseHead.head}');
            $("#respmessage").text('${responseHead.message}');
            $('#errorModal').modal('open');
        }

        if(${couponResponse ne null}){
            if(${couponResponse.header ne null && couponResponse.header.code eq 200}){
                $('#checkCoupon').hide();
                $('#redeemCoupon').show();
            }else{
                $("#resphead").text('');
                $("#respmessage").text('${couponResponse.header.message}');
                $('#errorModal').modal('open');
            }
        }


        $('#btn-info').click(function () {
            var txtCoupon = $('#txt-coupon').val();
            if (txtCoupon == null || txtCoupon == '') {
                $('#errorModal').modal('open');
                $("#resphead").text('');
                $("#respmessage").text('Please Enter Coupon');
                return false;
            }
            return true;
        });

        $('#btn-redeem').click(function () {
            var branchid = $('#c_branch').val();
            if (branchid == null || branchid == '') {
                $('#errorModal').modal('open');
                $("#resphead").text('');
                $("#respmessage").text('Please Select Branch');
                return false;
            }

            return true;
        });
    });

</script>

</body>

</html>