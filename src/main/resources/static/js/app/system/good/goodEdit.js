function updateGood() {
    var selected = $("#goodTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的药品！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个药品！');
        return;
    }
    var goodsId = selected[0].goodsId;
    $.post(ctx + "good/getGood", {"goodsId": goodsId}, function (r) {
        if (r.code === 0) {
            var $form = $('#good-add');
            $form.modal();
            var good = r.msg;
            $("#user-add-modal-title").html('修改药品');
            $form.find("input[name='goodsName']").val(good.goodsName);
            $form.find("input[name='goodsId']").val(good.goodsId);
            $form.find("input[name='medicinemodel']").val(good.medicinemodel);
            $form.find("input[name='outlook']").val(good.outlook);
            $form.find("input[name='factor']").val(good.factor);
            $form.find("input[name='unit']").val(good.unit);
            $form.find("input[name='materialName']").val(good.materialName);
            $form.find("input[name='companyNameSc']").val(good.companyNameSc);
            $form.find("input[name='price']").val(good.price);
            $("#good-add-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}