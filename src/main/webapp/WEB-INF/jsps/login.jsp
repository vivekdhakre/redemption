<%--
  Created by IntelliJ IDEA.
  User: Vivek
  Date: 29/6/17
  Time: 12:01 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>${mname ne null ? mname : ''} Redemption</title>
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

    <meta name="apple-itunes-app" content="app-id=1234567890">
    <meta name="HandheldFriendly" content="true">
    <meta name="theme-color" content="#0589ca">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Uahoy Merchant">
    <link rel="manifest" href="${pageContext.request.contextPath}/assets/manifest.json">

</head>

<body>
<main>
    <div class="login-main-page" id="login_view">
        <div class="row">
            <form action="${pageContext.request.contextPath}/login" method="post"  class="col s12">
                    <span class="brand_logo">
                    <img id="merchant_logo" src="http://marketeer.uahoy.com/ahoy/merchant/logo/${logo}" onerror="src='${pageContext.request.contextPath}/assets/icon/merhant.svg'" alt="">
                </span>
                <div class="card-panel z-depth-3">
                    <!--<h5 class="cyan-text darken-3 font-size-18 weight-5">Login with</h5>-->
                    <div class="row">
                        <div class="input-field col s12">
                            <i class="material-icons prefix">account_circle</i>
                            <input id="uname" name="uname" type="text">
                            <label for="uname">User Name</label>
                        </div>
                        <div class="input-field col s12">
                            <i class="material-icons prefix">lock</i>
                            <input id="passwd" name="passwd" type="password">
                            <label for="passwd">Password</label>
                        </div>
                        <div class="input-field col s12 right-align">
                            <button class="btn waves-effect waves-light" id="loginbtn" type="submit">
                                Login
                                <i class="material-icons right">exit_to_app</i>
                            </button>
                        </div>
                    </div>
                    <span class="login-footer">Powered By <img src="${pageContext.request.contextPath}/assets/icon/logo/ahoylogo.png" alt=""></span>
                </div>
            </form>
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


<!--Import tiles -->
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/custom.js"/>

<script>
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

<script>
    $(document).ready(function(){
        $('.modal').modal();
        if(${responseHead ne null}){
            $("#resphead").text('${responseHead.head}');
            $("#respmessage").text('${responseHead.message}');
            $('#errorModal').modal('open');
        }


        $("#loginbtn").click(function(){
            var email = $("#uname").val();
            if(email == null || email ==''){
                $('#errorModal').modal('open');
                $("#resphead").text('Unauthorized');
                $("#respmessage").text('Please Enter valid Email');
                $("#uname").focus();
                return false;
            }

            var passwd = $('#passwd').val();
            if(passwd==null || passwd ==''){
                $('#errorModal').modal('open');
                $("#resphead").text('Unauthorized');
                $("#respmessage").text('Please Enter Password');
                $("#passwd").focus();
                return false;
            }
            return true;
        })

    });

</script>



</body>

</html>