$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/orders/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'orderId', index: 'orderId', width: 50, key: true, hidden: true},
            {label: 'Order No', name: 'orderNo', index: 'orderNo', width: 120},
            {label: 'Total Price', name: 'totalPrice', index: 'totalPrice', width: 60},
            {label: 'Order Status', name: 'orderStatus', index: 'orderStatus', width: 80, formatter: orderStatusFormatter},
            {label: 'Payment Method', name: 'payType', index: 'payType', width: 80,formatter:payTypeFormatter},
            {label: 'Recipient Address', name: 'userAddress', index: 'userAddress', width: 10, hidden: true},
            {label: 'Creation Time', name: 'createTime', index: 'createTime', width: 120},
            {label: 'Actions', name: 'createTime', index: 'createTime', width: 120, formatter: operateFormatter}
        ],
        height: 760,
        rowNum: 20,
        rowList: [20, 50, 80],
        styleUI: 'Bootstrap',
        loadtext: 'Loading information...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data.list",
            page: "data.currPage",
            total: "data.totalPage",
            records: "data.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },
        gridComplete: function () {
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

    function operateFormatter(cellvalue, rowObject) {
        return "<a href=\'##\' onclick=openOrderItems(" + rowObject.rowId + ")>View Order Details</a>" +
            "<br>" +
            "<a href=\'##\' onclick=openExpressInfo(" + rowObject.rowId + ")>View Recipient Info</a>";
    }

    function orderStatusFormatter(cellvalue) {
        // Order statuses: 0: Pending Payment, 1: Paid, 2: Packed, 3: Shipped, 4: Completed, -1: Manually Closed, -2: Timeout Closed, -3: Merchant Closed
        if (cellvalue == 0) {
            return "Pending Payment";
        }
        if (cellvalue == 1) {
            return "Paid";
        }
        if (cellvalue == 2) {
            return "Packed";
        }
        if (cellvalue == 3) {
            return "Shipped";
        }
        if (cellvalue == 4) {
            return "Completed";
        }
        if (cellvalue == -1) {
            return "Manually Closed";
        }
        if (cellvalue == -2) {
            return "Timeout Closed";
        }
        if (cellvalue == -3) {
            return "Merchant Closed";
        }
    }

    function payTypeFormatter(cellvalue) {
        if (cellvalue == 0) {
            return "None";
        }
        if (cellvalue == 1) {
            return "TNG";
        }
        if (cellvalue == 2) {
            return "Grab Pay";
        }
    }

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

});

function reload() {
    initFlatPickr();
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

function openOrderItems(orderId) {
    $('.modal-title').html('Order Details');
    $.ajax({
        type: 'GET',
        url: '/admin/order-items/' + orderId,
        contentType: 'application/json',
        success: function (result) {
            if (result.resultCode == 200) {
                $('#orderItemModal').modal('show');
                var itemString = '';
                for (i = 0; i < result.data.length; i++) {
                    itemString += result.data[i].carsName + ' x ' + result.data[i].carsCount + ' Cars ID ' + result.data[i].carsId + ";<br>";
                }
                $("#orderItemString").html(itemString);
            } else {
                Swal.fire({
                    text: result.message,
                    icon: "error", iconColor: "#f05b72",
                });
            }
        },
        error: function () {
            Swal.fire({
                text: "Operation failed",
                icon: "error", iconColor: "#f05b72",
            });
        }
    });
}

function openExpressInfo(orderId) {
    var rowData = $("#jqGrid").jqGrid("getRowData", orderId);
    $('.modal-title').html('Recipient Info');
    $('#expressInfoModal').modal('show');
    $("#userAddressInfo").html(rowData.userAddress);
}

function orderEdit() {
    reset();
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    var rowData = $("#jqGrid").jqGrid("getRowData", id);
    $('.modal-title').html('Edit Order');
    $('#orderInfoModal').modal('show');
    $("#orderId").val(id);
    $("#userAddress").val(rowData.userAddress);
    $("#totalPrice").val(rowData.totalPrice);
}

$('#saveButton').click(function () {
    var totalPrice = $("#totalPrice").val();
    var userName = $("#userName").val();
    var userPhone = $("#userPhone").val();
    var userAddress = $("#userAddress").val();
    var id = getSelectedRowWithoutAlert();
    var url = '/admin/orders/update';
    var data = {
        "orderId": id,
        "totalPrice": totalPrice,
        "userName": userName,
        "userPhone": userPhone,
        "userAddress": userAddress
    };
    $.ajax({
        type: 'POST',
        url: url,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
            if (result.resultCode == 200) {
                $('#orderInfoModal').modal('hide');
                Swal.fire({
                    text: "Saved successfully",
                    icon: "success", iconColor: "#1d953f",
                });
                reload();
            } else {
                $('#orderInfoModal').modal('hide');
                Swal.fire({
                    text: result.message,
                    icon: "error", iconColor: "#f05b72",
                });
            }
        },
        error: function () {
            Swal.fire({
                text: "Operation failed",
                icon: "error", iconColor: "#f05b72",
            });
        }
    });
});

function orderCheckDone() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    var orderNos = '';
    for (i = 0; i < ids.length; i++) {
        var rowData = $("#jqGrid").jqGrid("getRowData", ids[i]);
        if (rowData.orderStatus != 'Paid') {
            orderNos += rowData.orderNo + " ";
        }
    }
    if (orderNos.length > 0 && orderNos.length < 100) {
        Swal.fire({
            text: orderNos + " order status is not paid, cannot mark as packed",
            icon: "error", iconColor: "#f05b72",
        });
        return;
    }
    if (orderNos.length >= 100) {
        Swal.fire({
            text: "You selected too many orders that are not paid, cannot mark as packed",
            icon: "error", iconColor: "#f05b72",
        });
        return;
    }
    Swal.fire({
        title: "Confirmation",
        text: "Are you sure you want to mark these orders as packed?",
        icon: "warning", iconColor: "#dea32c",
        showCancelButton: true,
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel'
    }).then((flag) => {
        if (flag.value) {
            $.ajax({
                type: "POST",
                url: "/admin/orders/checkDone",
                contentType: "application/json",
                data: JSON.stringify(ids),
                success: function (r) {
                    if (r.resultCode == 200) {
                        Swal.fire({
                            text: "Marked as packed",
                            icon: "success", iconColor: "#1d953f",
                        });
                        $("#jqGrid").trigger("reloadGrid");
                    } else {
                        Swal.fire({
                            text: r.message,
                            icon: "error", iconColor: "#f05b72",
                        });
                    }
                }
            });
        }
    });
}

function orderCheckOut() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    var orderNos = '';
    for (i = 0; i < ids.length; i++) {
        var rowData = $("#jqGrid").jqGrid("getRowData", ids[i]);
        if (rowData.orderStatus != 'Paid' && rowData.orderStatus != 'Packed') {
            orderNos += rowData.orderNo + " ";
        }
    }
    if (orderNos.length > 0 && orderNos.length < 100) {
        Swal.fire({
            text: orderNos + " order status is not paid or packed, cannot mark as shipped",
            icon: "error", iconColor: "#f05b72",
        });
        return;
    }
    if (orderNos.length >= 100) {
        Swal.fire({
            text: "You selected too many orders that are not paid or packed, cannot mark as shipped",
            icon: "error", iconColor: "#f05b72",
        });
        return;
    }
    Swal.fire({
        title: "Confirmation",
        text: "Are you sure you want to mark these orders as shipped?",
        icon: "warning", iconColor: "#dea32c",
        showCancelButton: true,
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel'
    }).then((flag) => {
        if (flag.value) {
            $.ajax({
                type: "POST",
                url: "/admin/orders/checkOut",
                contentType: "application/json",
                data: JSON.stringify(ids),
                success: function (r) {
                    if (r.resultCode == 200) {
                        Swal.fire({
                            text: "Marked as shipped",
                            icon: "success", iconColor: "#1d953f",
                        });
                        $("#jqGrid").trigger("reloadGrid");
                    } else {
                        Swal.fire({
                            text: r.message,
                            icon: "error", iconColor: "#f05b72",
                        });
                    }
                }
            });
        }
    });
}

function closeOrder() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    Swal.fire({
        title: "Confirmation",
        text: "Are you sure you want to close these orders?",
        icon: "warning", iconColor: "#dea32c",
        showCancelButton: true,
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel'
    }).then((flag) => {
        if (flag.value) {
            $.ajax({
                type: "POST",
                url: "/admin/orders/close",
                contentType: "application/json",
                data: JSON.stringify(ids),
                success: function (r) {
                    if (r.resultCode == 200) {
                        Swal.fire({
                            text: "Closed successfully",
                            icon: "success", iconColor: "#1d953f",
                        });
                        $("#jqGrid").trigger("reloadGrid");
                    } else {
                        Swal.fire({
                            text: r.message,
                            icon: "error", iconColor: "#f05b72",
                        });
                    }
                }
            });
        }
    });
}

function reset() {
    $("#totalPrice").val(0);
    $("#userAddress").val('');
    $('#edit-error-msg').css("display", "none");
}
