var validator;
var $orderAddForm = $("#order-add-form");
var $hasOrder=$("input[name='hasOrder']");

$(function () {
    $hasOrder.change(function () {
        var hasOrder = $("input[name='hasOrder']:checked").val();
        var $oldOrderName=$("select[name='oldOrderName']");
        var $newOrderName=$("input[name='newOrderName']");
        var $orderRemarks = $("#remarksDiv");
        if (hasOrder == 1){
            $oldOrderName.empty();
            $.post(ctx+"order/hasOrder","hasOrder="+hasOrder,function (r) {
                if (r.code === 0){
                    $newOrderName.attr("hidden","hidden");
                    $orderRemarks.attr("hidden","hidden");
                    $oldOrderName.removeAttrs("hidden");
                    var oldOrderNameArr=r.msg;
                    $(oldOrderNameArr).each(function (index,element) {
                        $oldOrderName.append("<option value="+element.orderId+">"+element.orderName
                            +"</option>");
                    });
                } else $MB.n_danger(r.msg);
            });
        }else{
            $oldOrderName.attr("hidden","hidden");
            $newOrderName.removeAttrs("hidden");
            $orderRemarks.removeAttrs("hidden");
        }

    });

    validateRule();
    $("#order-add .btn-save").click(function () {
        var validator = $orderAddForm.validate();
        $.post(ctx + "order/add", $orderAddForm.serialize(), function (r) {
            if (r.code === 0) {
                closeModal();
                $MB.n_success(r.msg);
                $MB.refreshTable("goodTable");
            } else $MB.n_danger(r.msg);
        });

    });

    $("#order-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#order-add-button").attr("name", "save");
    validator.resetForm();
    $("#order-add-modal-title").html('新增采购单');
    $MB.closeAndRestModal("order-add");

}

function validateRule() {
    var icon = "<i class='zmdi zmdi-close-circle zmdi-hc-fw'></i> ";
    validator = $orderAddForm.validate({
        rules: {
            goodsName: {
                required: true
                /*minlength: 3,
                maxlength: 10,*/
            },

            companyNameSc: {
                required: true
            },
            price: {
                required: true
            },
            purchaseCount: {
                required: true
            },

        },

        messages: {
            username: {
                required: icon + "请输入药品名",
               /* minlength: icon + "用户名长度3到10个字符",
                remote: icon + "用户名已经存在"*/
            },
            companyNameSc: icon + "生产企业名称输入错误",
            price: icon + "价格输入错误",
            purchaseCount: icon + "采购数量输入错误",
        }
    });
}
