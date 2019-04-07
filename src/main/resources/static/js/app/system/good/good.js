$(function () {
    var $goodTableForm = $(".good-table-form");
    var settings = {
        url: ctx + "good/list",
        pageSize: 10,
        queryParams: function (params) {
            return {
                pageSize: params.limit,
                pageNum: params.offset / params.limit + 1,
                goodsName: $goodTableForm.find("input[name='goodsName']").val().trim()||'',
                companyNameSc: $goodTableForm.find("input[name='companyNameSc']").val(),
                price: $goodTableForm.find("input[name='price']").val()
            };
        },
        columns: [{
            checkbox: true
        }, {
            field: 'goodsId',
            visible: false
        }, {
            field: 'operate',
            title: '操作',
            formatter: function (value, row, index) {
            return "<a href='javascript:void(0)'  onclick='addOrder(\""+row.goodsId+"\")' >采购</a>"
            }
        },
        {
            field: 'goodsName',
            title: '通用名'
        }, {
            field: 'medicinemodel',
            title: '剂型'
        }, {
            field: 'outlook',
            title: '规格'
        }, {
            field: 'factor',
            title: '转换系数'
        }, {
            field: 'unit',
            title: '制剂单位'
        }, {
            field: 'materialName',
            title: '材质'
        }, {
            field: 'companyNameSc',
            title: '生产企业名称'
        }, {
            field: 'price',
            title: '价格'
        }

        ]
    };

    $MB.initTable('goodTable', settings);
});

function addOrder(goodsId) {
    $.post(ctx + "good/getGood", {"goodsId": goodsId}, function (r) {
        if (r.code === 0) {
            var $form = $('#order-add');
            console.log($form);
            $form.modal();
            var good = r.msg;
            $("#order-add-modal-title").html('添加采购单');
            $form.find("input[name='goodsName']").val(good.goodsName);
            $form.find("input[name='goodsId']").val(good.goodsId);
            $form.find("input[name='medicinemodel']").val(good.medicinemodel);
            $form.find("input[name='outlook']").val(good.outlook);
            $form.find("input[name='factor']").val(good.factor);
            $form.find("input[name='unit']").val(good.unit);
            $form.find("input[name='materialName']").val(good.materialName);
            $form.find("input[name='companyNameSc']").val(good.companyNameSc);
            $form.find("input[name='price']").val(good.price);
            $("#order-add-button").attr("name", "add");
        } else {
            $MB.n_danger(r.msg);
        }
    });
}


function search() {
    $MB.refreshTable('goodTable');
}

function refresh() {
    $(".good-table-form")[0].reset();
    $MB.refreshTable('goodTable');
}

function deleteGoods() {
    var selected = $("#goodTable").bootstrapTable('getSelections');
    var selected_length = selected.length;
    var contain = false;
    if (!selected_length) {
        $MB.n_warning('请勾选需要删除的药品！');
        return;
    }
    var ids = "";
    for (var i = 0; i < selected_length; i++) {
        ids += selected[i].goodsId;
        if (i !== (selected_length - 1)) ids += ",";

    }
    console.log(ids);

    $MB.confirm({
        text: "确定删除选中药品？",
        confirmButtonText: "确定删除"
    }, function () {
        $.post(ctx + 'good/delete', {"ids": ids}, function (r) {
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
    $.post(ctx + "user/excel", $(".good-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}

function exportUserCsv() {
    $.post(ctx + "user/csv", $(".good-table-form").serialize(), function (r) {
        if (r.code === 0) {
            window.location.href = "common/download?fileName=" + r.msg + "&delete=" + true;
        } else {
            $MB.n_warning(r.msg);
        }
    });
}