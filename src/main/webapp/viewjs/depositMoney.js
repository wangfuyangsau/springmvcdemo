
function desavebtn() {
    var flag1=false;
    var flag2=false;
    var moneyNumber= $("#saveMoneyNumber").val();
    var currenyType=$('[name="currencyType"]').val();
    var currenyTypehtml= $('[name="currencyType"]').find("option:selected").text();
    var re = new RegExp("^[1-9][0-9]*$");
    if(re.test(moneyNumber)){
        if(moneyNumber%100==0){
            var htmlText="请确认存储"+moneyNumber+currenyTypehtml+"?";
            var r = confirm(htmlText);
            if (r == true) {
                $.ajax({
                    type:"POST",
                    async:false,
                    dataType:'text',
                    url:"../BankAccount/saveMoney",
                    contentType : "application/json",
                    data:JSON.stringify({
                        saveMoneyNumber:moneyNumber,
                        saveMoneyType:currenyType,
                        billAffairType:"save",
                        billTradeBalance:moneyNumber,
                        billCurrencyType:currenyType}),
                    success:function (data) {
                        if(data=="true"){
                           flag1=true;
                            $.ajax({
                                type:"POST",
                                async:false,
                                dataType:'text',
                                url:"../BankBill/insertBill",
                                contentType : "application/json",
                                data:JSON.stringify({
                                    saveMoneyNumber:moneyNumber,
                                    saveMoneyType:currenyType,
                                    billAffairType:"save",
                                    billTradeBalance:moneyNumber,
                                    billCurrencyType:currenyType}),
                                success:function (data) {
                                    if(data=="true"){
                                        flag2=true;
                                        alert("存储成功！");
                                        window.location.href="showBalanceView.html"
                                    }
                                    else{
                                        alert("存储失败！");
                                        $("#savebtn").val("");
                                    }

                                }
                            });

                        }
                        else{
                            alert("存储失败！");
                            $("#savebtn").val("");
                        }

                    }
                });

            } else {
                alert("已取消");
            }
        }
        else{
            $("#showMoney").text("请输入100整数");
        }
    }
    else{
        $("#showMoney").text("输入不合法");
    }

}
