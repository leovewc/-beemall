$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/cars/list',
        datatype: "json",
        colModel: [
            {label: 'Cars ID', name: 'carsId', index: 'carsId', width: 60, key: true},
            {label: 'Cars Name', name: 'carsName', index: 'carsName', width: 120},
            {label: 'Cars Intro', name: 'carsIntro', index: 'carsIntro', width: 120},
            {label: 'Cars Image', name: 'carsCoverImg', index: 'carsCoverImg', width: 120, formatter: coverImageFormatter},
            {label: 'Stock Number', name: 'stockNum', index: 'stockNum', width: 60},
            {label: 'Selling Price', name: 'sellingPrice', index: 'sellingPrice', width: 60},
            {
                label: 'Sell Status',
                name: 'carsSellStatus',
                index: 'carsSellStatus',
                width: 80,
                formatter: carsSellStatusFormatter
            },
            {label: 'Creation Time', name: 'createTime', index: 'createTime', width: 60}
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

    function carsSellStatusFormatter(cellvalue) {
        // 0-on 1-out
        if (cellvalue == 0) {
            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm\" style=\"width: 80%;\">On Sale</button>";
        }
        if (cellvalue == 1) {
            return "<button type=\"button\" class=\"btn btn-block btn-secondary btn-sm\" style=\"width: 80%;\">Off Shelf</button>";
        }
    }

    function coverImageFormatter(cellvalue) {
        return "<img src='" + cellvalue + "' height=\"80\" width=\"80\" alt='Cars Main Image'/>";
    }

});

function reload() {
    initFlatPickr();
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}

function addCars() {
    window.location.href = "/admin/cars/edit";
}

function editCars() {
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    window.location.href = "/admin/cars/edit/" + id;
}

function putUpCars() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    Swal.fire({
        title: "Confirmation",
        text: "Are you sure you want to put these items up for sale?",
        icon: "warning",iconColor:"#dea32c",
        showCancelButton: true,
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel'
    }).then((flag) => {
            if (flag.value) {
                $.ajax({
                    type: "PUT",
                    url: "/admin/cars/status/0",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.resultCode == 200) {
                            Swal.fire({
                                text: "Put up for sale successfully",
                                icon: "success",iconColor:"#1d953f",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            Swal.fire({
                                text: r.message,
                                icon: "error",iconColor:"#f05b72",
                            });
                        }
                    }
                });
            }
        }
    )
    ;
}

function putDownCars() {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    Swal.fire({
        title: "Confirmation",
        text: "Are you sure you want to take these items off the shelf?",
        icon: "warning",iconColor:"#dea32c",
        showCancelButton: true,
        confirmButtonText: 'Confirm',
        cancelButtonText: 'Cancel'
    }).then((flag) => {
            if (flag.value) {
                $.ajax({
                    type: "PUT",
                    url: "/admin/cars/status/1",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.resultCode == 200) {
                            Swal.fire({
                                text: "Taken off the shelf successfully",
                                icon: "success",iconColor:"#1d953f",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            Swal.fire({
                                text: r.message,
                                icon: "error",iconColor:"#f05b72",
                            });
                        }
                    }
                });
            }
        }
    )
    ;
}
