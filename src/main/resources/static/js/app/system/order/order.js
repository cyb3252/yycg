$(function () {
    var $orderTableForm = $(".order-table-form");
    var settings = {
        url: ctx + "order/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                orderName: $orderTableForm.find("input[name='orderName']").val().trim()||'',
                companyNameSc: $orderTableForm.find("input[name='companyNameSc']").val(),
                hospitalName: $orderTableForm.find("input[name='hospitalName']").val()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'orderId',
            visible: false
        },
        {
            field: 'operate',
            title: '操作',
            formatter: function (value, row, index) {
                return "<a href='order/toOrderUpdate?orderId="+row.orderId+"' >"+"修改</a>"
            }
        },
        {
            field: 'hospitalName',
            title: '买方'
        }, {
            field: 'companyNameSc',
            title: '生产企业'
        }, {
            field: 'orderName',
            title: '订单名称',
            formatter: function (value, row, index) {
                return "<a href='order/detail?orderId="+row.orderId+"' >"+value+"</a>"
            }
        }, {
            field: 'orderAmount',
            title: '订单金额'
        }, {
            field: 'orderRemarks',
            title: '订单标识'
        }, {
            field: 'orderStatus',
            title: '订单状态'
        }, {
            field: 'addUserName',
            title: '订单添加人'
        }, {
            field: 'addTime',
            title: '订单添加时间'
        }

        ]
    };

    $MB.initTable('orderTable', settings);
});

function search() {
    $MB.refreshTable('orderTable');
}

function refresh() {
    $(".order-table-form")[0].reset();
    $MB.refreshTable('orderTable');
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