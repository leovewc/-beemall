var editor;

$(function () {

    const E = window.wangEditor;

    const editorConfig = { MENU_CONF: {} }
    editorConfig.MENU_CONF['uploadImage'] = {

          server: '/admin/upload/files',

          timeout: 5 * 1000,
          fieldName: 'files',

          allowedFileTypes: ['image/*'],

          maxFileSize: 4 * 1024 * 1024,
          base64LimitSize: 5 * 1024,

          onBeforeUpload(file) {
            console.log('onBeforeUpload', file)

            return file
          },
          onProgress(progress) {
            console.log('onProgress', progress)
          },
          onSuccess(file, res) {
            console.log('onSuccess', file, res)
          },
          onFailed(file, res) {
            alert(res.message)
            console.log('onFailed', file, res)
          },
          onError(file, err, res) {
            alert(err.message)
            console.error('onError', file, err, res)
          },
          customInsert: function (result, insertImgFn) {
            if (result != null && result.resultCode == 200) {

                result.data.forEach(img => {
                    insertImgFn(img)
                });
            } else if (result != null && result.resultCode != 200) {
                Swal.fire({
                    text: result.message,
                    icon: "error", iconColor:"#f05b72",
                });
            } else {
                Swal.fire({
                    text: "error",
                    icon: "error", iconColor:"#f05b72",
                });
            }
          }
    }

    editor = E.createEditor({
      selector: '#editor-text-area',
      html: $(".editor-text").val(),
      config: editorConfig
    })

    const toolbar = E.createToolbar({
      editor,
      selector: '#editor-toolbar',
    })

    new AjaxUpload('#uploadcarsCoverImg', {
        action: '/admin/upload/file',
        name: 'file',
        autoSubmit: true,
        responseType: "json",
        onSubmit: function (file, extension) {
            if (!(extension && /^(jpg|jpeg|png|gif)$/.test(extension.toLowerCase()))) {
                Swal.fire({
                    text: "Only jpg, png, and gif formats are supported!",
                    icon: "error", iconColor:"#f05b72",
                });
                return false;
            }
        },
        onComplete: function (file, r) {
            if (r != null && r.resultCode == 200) {
                $("#carsCoverImg").attr("src", r.data);
                $("#carsCoverImg").attr("style", "width: 128px;height: 128px;display:block;");
                return false;
            } else if (r != null && r.resultCode != 200) {
                Swal.fire({
                    text: r.message,
                    icon: "error", iconColor:"#f05b72",
                });
                return false;
            }
            else {
                Swal.fire({
                    text: "error",
                    icon: "error", iconColor:"#f05b72",
                });
            }
        }
    });
});

$('#saveButton').click(function () {
    var carsId = $('#carsId').val();
    var carsCategoryId = $('#levelThree option:selected').val();
    var carsName = $('#carsName').val();
    var tag = $('#tag').val();
    var originalPrice = $('#originalPrice').val();
    var sellingPrice = $('#sellingPrice').val();
    var carsIntro = $('#carsIntro').val();
    var stockNum = $('#stockNum').val();
    var carsSellStatus = $("input[name='carsSellStatus']:checked").val();
    var carsDetailContent = editor.getHtml();
    var carsCoverImg = $('#carsCoverImg')[0].src;
    if (isNull(carsCategoryId)) {
        Swal.fire({
            text: "Please select a category",
            icon: "error", iconColor:"#f05b72",
        });
        return;
    }
    if (isNull(carsName)) {
        Swal.fire({
            text: "Please enter the product name",
            icon: "error", iconColor:"#f05b72",
        });
        return;
    }
    if (!validLength(carsName, 100)) {
        Swal.fire({
            text: "Product name is too long",
            icon: "error", iconColor:"#f05b72",
        });
        return;
    }
    if (isNull(tag)) {
        Swal.fire({
            text: "Please enter product tags",
            icon: "error", iconColor:"#f05b72",
        });
        return;
    }
    if (!validLength(tag, 100)) {
        Swal.fire({
            text: "Tag is too long",
            icon: "error", iconColor:"#f05b72",
        });
        return;
    }
    if (isNull(carsIntro)) {
        Swal.fire({
            text: "Please enter a product description",
            icon: "error", iconColor:"#f05b72",
        });
        return;
    }
    if (!validLength(carsIntro, 100)) {
        Swal.fire({
            text: "Description is too long",
            icon: "error", iconColor:"#f05b72",
        });
        return;
    }
    if (isNull(originalPrice) || originalPrice < 1) {
        Swal.fire({
            text: "Please enter the product price",
            icon: "error", iconColor:"#f05b72",
        });
        return;
    }
    if (isNull(sellingPrice) || sellingPrice < 1) {
        Swal.fire({
            text: "Please enter the selling price",
            icon: "error", iconColor:"#f05b72",
        });
        return;
    }
    if (isNull(stockNum) || sellingPrice < 0) {
        Swal.fire({
            text: "Please enter the product stock number",
            icon: "error", iconColor:"#f05b72",
        });
        return;
    }
    if (isNull(carsSellStatus)) {
        Swal.fire({
            text: "Please select the shelf status",
            icon: "error", iconColor:"#f05b72",
        });
        return;
    }
    if (isNull(carsDetailContent)) {
        Swal.fire({
            text: "Please enter the product introduction",
            icon: "error", iconColor:"#f05b72",
        });
        return;
    }
    if (!validLength(carsDetailContent, 50000)) {
        Swal.fire({
            text: "Product introduction is too long",
            icon: "error", iconColor:"#f05b72",
        });
        return;
    }
    if (isNull(carsCoverImg) || carsCoverImg.indexOf('img-upload') != -1) {
        Swal.fire({
            text: "Cover image cannot be empty",
            icon: "error", iconColor:"#f05b72",
        });
        return;
    }
    var url = '/admin/cars/save';
    var swlMessage = 'Saved successfully';
    var data = {
        "carsName": carsName,
        "carsIntro": carsIntro,
        "carsCategoryId": carsCategoryId,
        "tag": tag,
        "originalPrice": originalPrice,
        "sellingPrice": sellingPrice,
        "stockNum": stockNum,
        "carsDetailContent": carsDetailContent,
        "carsCoverImg": carsCoverImg,
        "carsCarousel": carsCoverImg,
        "carsSellStatus": carsSellStatus
    };
    if (carsId > 0) {
        url = '/admin/cars/update';
        swlMessage = 'Modified successfully';
        data = {
            "carsId": carsId,
            "carsName": carsName,
            "carsIntro": carsIntro,
            "carsCategoryId": carsCategoryId,
            "tag": tag,
            "originalPrice": originalPrice,
            "sellingPrice": sellingPrice,
            "stockNum": stockNum,
            "carsDetailContent": carsDetailContent,
            "carsCoverImg": carsCoverImg,
            "carsCarousel": carsCoverImg,
            "carsSellStatus": carsSellStatus
        };
    }
    console.log(data);
    $.ajax({
        type: 'POST',
        url: url,
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function (result) {
            if (result.resultCode == 200) {
                Swal.fire({
                    text: swlMessage,
                    icon: "success", iconColor:"#1d953f",
                    showCancelButton: false,
                    confirmButtonColor: '#1baeae',
                    confirmButtonText: 'Return to product list',
                    confirmButtonClass: 'btn btn-success',
                    buttonsStyling: false
                }).then(function () {
                    window.location.href = "/admin/cars";
                })
            } else {
                Swal.fire({
                    text: result.message,
                    icon: "error", iconColor:"#f05b72",
                });
            }
            ;
        },
        error: function () {
            Swal.fire({
                text: "Operation failed",
                icon: "error", iconColor:"#f05b72",
            });
        }
    });
});

$('#cancelButton').click(function () {
    window.location.href = "/admin/cars";
});

$('#levelOne').on('change', function () {
    $.ajax({
        url: '/admin/categories/listForSelect?categoryId=' + $(this).val(),
        type: 'GET',
        success: function (result) {
            if (result.resultCode == 200) {
                var levelTwoSelect = '';
                var secondLevelCategories = result.data.secondLevelCategories;
                var length2 = secondLevelCategories.length;
                for (var i = 0; i < length2; i++) {
                    levelTwoSelect += '<option value=\"' + secondLevelCategories[i].categoryId + '\">' + secondLevelCategories[i].categoryName + '</option>';
                }
                $('#levelTwo').html(levelTwoSelect);
                var levelThreeSelect = '';
                var thirdLevelCategories = result.data.thirdLevelCategories;
                var length3 = thirdLevelCategories.length;
                for (var i = 0; i < length3; i++) {
                    levelThreeSelect += '<option value=\"' + thirdLevelCategories[i].categoryId + '\">' + thirdLevelCategories[i].categoryName + '</option>';
                }
                $('#levelThree').html(levelThreeSelect);
            } else {
                Swal.fire({
                    text: result.message,
                    icon: "error", iconColor:"#f05b72",
                });
            }
            ;
        },
        error: function () {
            Swal.fire({
                text: "Operation failed",
                icon: "error", iconColor:"#f05b72",
            });
        }
    });
});

$('#levelTwo').on('change', function () {
    $.ajax({
        url: '/admin/categories/listForSelect?categoryId=' + $(this).val(),
        type: 'GET',
        success: function (result) {
            if (result.resultCode == 200) {
                var levelThreeSelect = '';
                var thirdLevelCategories = result.data.thirdLevelCategories;
                var length = thirdLevelCategories.length;
                for (var i = 0; i < length; i++) {
                    levelThreeSelect += '<option value=\"' + thirdLevelCategories[i].categoryId + '\">' + thirdLevelCategories[i].categoryName + '</option>';
                }
                $('#levelThree').html(levelThreeSelect);
            } else {
                Swal.fire({
                    text: result.message,
                    icon: "error", iconColor:"#f05b72",
                });
            }
            ;
        },
        error: function () {
            Swal.fire({
                text: "Operation failed",
                icon: "error", iconColor:"#f05b72",
            });
        }
    });
});
