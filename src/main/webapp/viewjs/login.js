
function loginbtn() {
    var jsondatato={accountCardId:$('[name="accountCardId"]').val(),
    accountPassword: $('[name="accountPassword"]').val()};
    $.ajax({
        type:"POST",
        async:true,
        dataType:'text',
        contentType : "application/json",
        url:"../BankAccount/loginCheck",
        data: JSON.stringify(jsondatato),
        success:function (data) {
            if(data=="true"){
                alert("登录成功");
                window.location.href="mainView.html"
            }
           else{
                alert("登录失败");
                window.location.href="login.html"
            }
        }
    });

}
function cancelbtn() {
  window.location.href="../selectLanguage.html"
}
function createbtn() {
window.location.href="createAccount.html";
}