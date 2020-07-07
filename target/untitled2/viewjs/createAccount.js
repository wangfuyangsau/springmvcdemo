function checkInfo() {
    var flag=true;
    var identifyReg = new RegExp("^[1-9]{6}(1|2)[0-9]{11}$");
    var cardId=$("[name='userIdentify']").val();
    if(!identifyReg.test(cardId)){
        alert("请输入合法的身份证号");
        $("[name='userIdentify']").text("");
        flag=false;
    }
    var identifyReg = new RegExp("^1[0-9]{10}$");
    var telNumber=$("[name='userTelnum']").val();
    if(!identifyReg.test(telNumber)){
        alert("请输入合法的手机号码");
        $("[name='userTelnum']").text("");
        flag=false;
    }
    if($("[name='userName']").val()=="")
    {
        alert("请输入姓名");
        flag=false;
    }
    if($("[name='userAddress']").val()=="")
    {
        alert("请输入住址");
        flag=false;
    }
    checkPwd();
    if(flag){
        ajax();
    }
}
function checkPwd() {
    var pwd=$("[name='accountPassword']").val();
    var rpwd=$("[name='rpwd']").val();
    if(pwd!=rpwd){
        alert("密码输入不一致");
      $("[name='accountPassword']").val("");
      $("[name='rpwd']").val("");
      flag=false;
    }

}
function cancelbtn() {
    window.location.href="../selectLanguage.html";

}
function ajax() {
    var flag=false;
    var accTypr="";
    var accounttype=$('[name="accountType"]');
    for(var i=0;i<accounttype.length;i++){
    if(accounttype[i].checked==true) {
        accTypr = accounttype[i].value;
    }
}

    var jsondatato={
        userName:$('[name="userName"]').val(),
        userIdentify:$('[name="userIdentify"]').val(),
        userTelnum:$('[name="userTelnum"]').val(),
        userAddress:$('[name="userAddress"]').val(),
        accountType:accTypr,
        accountPassword:$('[name="accountPassword"]').val()
       };

        $.ajax({
            type:"POST",
            async:false,
            dataType:'html',
            contentType : "application/json",
            url:"../BankUser/createUser",
            data: JSON.stringify(jsondatato),
            success:function (data) {
              if(data=="true"){
                  flag=true;
              }
            }
        });

    $.ajax({
        type:"POST",
        async:true,
        dataType:'html',
        contentType : "application/json",
        url:"../BankAccount/createAccount",
        data: JSON.stringify(jsondatato),
        success:function (data) {
            if(data!="fail"&&flag){
              alert("创建成功！账号为："+data);
              window.location.href="login.html";
            }
        }
    });
}