$(function () {
    var $orderTableForm = $("#order-update-form");
    var settings = {
        url: ctx + "order/getDetail",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                orderId: $orderTableForm.find("input[name='orderId']").val(),
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'goodsId',
            visible: false
        },
        {
            field: 'purchaseCount',
            title: '采购数量'
        }, {
            field: 'purchasePrice',
            title: '采购总价'
        }, {
            field: 'price',
            title: '采购单价'
        }, {
            field: 'goodsName',
            title: '商品名称'
        }, {
            field: 'materialName',
            title: '材质'
        }, {
            field: 'unit',
            title: '制剂单位'
        }, {
            field: 'companyNameSc',
            title: '生产企业名称'
        },
        ]
    };

    $MB.initTable('orderDetailTable', settings);
});

function search() {
    $MB.refreshTable('orderDetailTable');
}

function refresh() {
    $(".order-update-form")[0].reset();
    $MB.refreshTable('orderDetailTable');
}

function deleteGoods() {
    var selected = $("#orderTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的订单！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].orderId;
        if (i !== (selected_length - 1)) ids += ",";

    }
    console.log(ids);

    $MB.confirm({
        text: "确定删除选中订单？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'order/delete', {"ids": ids}, function (r) {
            if (r.code === 0) {
                $MB.n_success(r.msg);
                refresh();
            } else {
                $MB.n_danger(r.msg);
            }
        });
    });
}

function exportUserExcel() {
    $.post(ctx + "order/excel", $(".order-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportUserCsv() {
    $.post(ctx + "order/csv", $(".order-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}