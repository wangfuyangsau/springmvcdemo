var limit50=true;
function scanbtn( obj) {
    $('[name="getMoneyNumber"]').val(obj.innerText);
}
function getotherbtn() {
    limit50=false;
    $('[name="getMoneyNumber"]').attr("placeholder","请输入取款金额");
}
function drgetbtn() {
    var flag1=false;
    var flag2=false;
    var moneyNumber= $("#getMoneyNumber").val();
    var re = new RegExp("^[1-9][0-9]*$");
    if(re.test(moneyNumber)){
        if(limit50){
            if(moneyNumber%50==0){
                var htmlText="请确认取"+moneyNumber+"元？"
                var r = confirm(htmlText);
                if (r == true) {
                    $.ajax({
                        type:"POST",
                        async:false,
                        dataType:'text',
                        url:"../BankAccount/getMoney",
                        contentType : "application/json",
                        data:JSON.stringify({getMoneyNumber:moneyNumber,
                            billAffairType:"get",
                            billTradeBalance:moneyNumber,
                            billCurrencyType:"CNY"}),
                        success:function (data) {
                            if(data=="true"){
                                flag1=true;
                                $.ajax({
                                    type:"POST",
                                    async:false,
                                    dataType:'text',
                                    url:"../BankBill/insertBill",
                                    contentType : "application/json",
                                    data:JSON.stringify({getMoneyNumber:moneyNumber,
                                        billAffairType:"get",
                                        billTradeBalance:moneyNumber,
                                        billCurrencyType:"CNY"}),
                                    success:function (data) {
                                        if(data=="true"){
                                            flag2=true;
                                            alert("操作成功！");
                                            window.location.href="showBalanceView.html"
                                        }
                                        else{
                                            alert("操作失败！");
                                            $("#getMoneyNumber").val("");
                                        }
                                    }
                                });

                            }
                            else{
                                alert("操作失败！");
                                $("#getMoneyNumber").val("");
                            }
                        }
                    });


                } else {
                    alert("已取消");
                }
            }
            else{
                $("#showMoney").text("请输入50整数");
            }
        }
        else{
            var htmlText="请确认取"+moneyNumber+"元？"
            var r = confirm(htmlText);
            if (r == true) {

                $.ajax({
                    type:"POST",
                    async:false,
                    dataType:'text',
                    url:"../BankAccount/getMoney",
                    contentType : "application/json",
                    data:JSON.stringify({getMoneyNumber:moneyNumber,
                        billAffairType:"get",
                        billTradeBalance:moneyNumber,
                        billCurrencyType:"CNY"}),
                    success:function (data) {
                        if(data=="true"){
                            flag1=true;
                            $.ajax({
                                type:"POST",
                                async:false,
                                dataType:'text',
                                url:"../BankBill/insertBill",
                                contentType : "application/json",
                                data:JSON.stringify({getMoneyNumber:moneyNumber,
                                    billAffairType:"get",
                                    billTradeBalance:moneyNumber,
                                    billCurrencyType:"CNY"}),
                                success:function (data) {
                                    if(data=="true"){
                                        flag2=true;
                                        alert("操作成功！");
                                        window.location.href="showBalanceView.html"
                                    }
                                    else{
                                        alert("操作失败！");
                                        $("#getMoneyNumber").val("");
                                    }
                                }
                            });
                        }
                        else{
                            alert("操作失败！");
                            $("#getMoneyNumber").val("");
                        }
                    }
                });

            } else {
                alert("已取消");
            }
        }
    }
    else{
        $("#showMoney").text("输入不合法");
    }
}
