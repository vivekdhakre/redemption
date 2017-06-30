// // check is device mobile or not
//
// // redirect to http://marketeer.uahoy.com/ahoy/login.jspx if device is desktop
// // if (!window.mobileAndTabletcheck()) {
// //     location.href = "http://marketeer.uahoy.com/ahoy/login.jspx";
// // }
//
// // console.log(window.location.href);
//
// var url_string = window.location.href;
// var url = new URL(url_string);
// var uid = url.searchParams.get("merchantId");
// console.log(uid);
// var marchent_logo = document.getElementById('merchant_logo');
// if (uid != undefined && uid != null && uid != '') {
//     marchent_logo.setAttribute('src', 'assets/images/merhant_logo/' + uid + '.png')
// } else {
//     marchent_logo.setAttribute('src', 'assets/icon/merhant.svg')
// }
//
// // login method
// function login() {
//     var email = document.getElementById('icon_mail').value;
//     var password = document.getElementById('icon_password').value;
//     location.href = 'views/layouts/redeemCoupon.html';
// }
//
// // function openCampaignWindow() {
// //     $('#modal1').modal('open');
// // }
//
// // function getTemplateImage() {
// //     $('#dealImageTemplate').modal('open');
// // }
//
// // function saveDeal() {
// //     $('#confirmationModal').modal('open');
// // }
//
// // function redeemCoupon() {
// //     $('#confirmationModal').modal('open');
// // }
//
// // function editDeal() {
// //     $('#editModal').modal('open');
// // }
// function couponIsValid(coupon) {
//     if (coupon === 'AH00000000') {
//         return true;
//     } else {
//         return false;
//     }
// }
//
// function checkCoupon(next, pre) {
//     var textCode = document.getElementById("text_coupon").value;
//     if (textCode !== null && textCode !== '' && couponIsValid(textCode)) {
//         document.getElementById(next).style.display = 'block';
//         document.getElementById(pre).style.display = 'none';
//     } else {
//         $('.confirm-modal').modal('open');
//     }
// }
//
// function checkAgain(next, pre) {
//     document.getElementById(next).style.display = 'block';
//     document.getElementById(pre).style.display = 'none';
// }
//
//
// /**
//  * Created by Himanshu-Machine on 3/30/2017.
//  */