function updateOrder() {
    var selected = $("#orderTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    if (!selected_length) {
        $MB.n_warning('请勾选需要修改的订单！');
        return;
    }
    if (selected_length > 1) {
        $MB.n_warning('一次只能修改一个订单！');
        return;
    }
    var orderId = selected[0].orderId;
    window.location.href = ctx + "order/toOrderUpdate?orderId="+orderId;
   /* $.post(ctx + "order/toOrderUpdate", {"orderId":orderId}, function (r) {
        if (r.code === 0) {
            var $form = $('#order-update');
            $form.modal();
            var order = r.msg;
            $("#order-update-modal-title").html('修改订单');
            $form.find("input[name='orderName']").val(order.orderName);
            $form.find("input[name='orderId']").val(order.orderId);
            $form.find("input[name='orderRemarks']").val(order.orderRemarks);
            $("#order-update-button").attr("name", "update");
        } else {
            $MB.n_danger(r.msg);
        }
    });*/
}