var validator;
var $orderAddForm = $("#order-add-form");

$(function () {
    validateRule();
    $("#order-add .btn-save").click(function () {
        var name = $(this).attr("name");
        var validator = $orderAddForm.validate();
        var flag = validator.form();
        if (flag) {
            if (name === "save") {
                $.post(ctx + "good/add", $orderAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("goodTable");
                    } else $MB.n_danger(r.msg);
                });
            }
            if (name === "update") {
                $.post(ctx + "good/update", $orderAddForm.serialize(), function (r) {
                    if (r.code === 0) {
                        closeModal();
                        $MB.n_success(r.msg);
                        $MB.refreshTable("goodTable");
                    } else $MB.n_danger(r.msg);
                });
            }
        }
    });

    $("#good-add .btn-close").click(function () {
        closeModal();
    });

});

function closeModal() {
    $("#good-add-button").attr("name", "save");
    validator.resetForm();
    $("#user-add-modal-title").html('新增药品');
    $MB.closeAndRestModal("good-add");

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
            medicinemodel: {
                required: true
            },
            outlook: {
                required: true
            },
            factor: {
                required: true
            },
            unit: {
                required: true
            },
            materialName: {
                required: true
            },
            companyNameSc: {
                required: true
            },
            price: {
                required: true
            },

        },

        messages: {
            username: {
                required: icon + "请输入药品名",
               /* minlength: icon + "用户名长度3到10个字符",
                remote: icon + "用户名已经存在"*/
            },
            medicinemodel: icon + "剂型输入不对",
            outlook: icon + "规格输入错误",
            factor: icon + "转换系数输入错误",
            unit: icon + "制剂单位输入错误",
            materialName: icon + "材质输入错误",
            companyNameSc: icon + "生产企业名称输入错误",
            price: icon + "价格输入错误",

        }
    });
}
