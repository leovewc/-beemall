<!-- Regular Expression Validation Start-->
/**
 * Check if null
 *
 * @param obj
 * @returns {boolean}
 */
function isNull(obj) {
    if (obj == null || obj == undefined || obj.trim() == "") {
        return true;
    }
    return false;
}

/**
 * Parameter length validation
 *
 * @param obj
 * @param length
 * @returns {boolean}
 */
function validLength(obj, length) {
    if (obj.trim().length < length) {
        return true;
    }
    return false;
}

/**
 * URL validation
 *
 * @param str_url
 * @returns {boolean}
 */
function isURL(str_url) {
    var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
        + "(([0-9]{1,3}\.){3}[0-9]{1,3}"
        + "|"
        + "([0-9a-zA-Z_!~*'()-]+\.)*"
        + "([0-9a-zA-Z][0-9a-zA-Z-]{0,61})?[0-9a-zA-Z]\."
        + "[a-zA-Z]{2,6})"
        + "(:[0-9]{1,4})?"
        + "((/?)|"
        + "(/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$";
    var re = new RegExp(strRegex);
    return re.test(str_url);
}

/**
 * Username validation 4 to 16 characters (letters, numbers, underscores, hyphens)
 *
 * @param userName
 * @returns {boolean}
 */
function validUserName(userName) {
    var pattern = /^[a-zA-Z0-9_-]{4,16}$/;
    return pattern.test(userName.trim());
}

/**
 * Phone number validation Malaysia
 * @returns {boolean}
 */
function validPhoneNumber(phone) {
    return /^(?:\+60|60)?1\d{8,9}$|^0?1\d{7,8}$/.test(phone);
}


/**
 * Validate 2-18 character alphanumeric and Chinese strings
 *
 * @param str
 * @returns {boolean}
 */
function validCN_ENString2_18(str) {
    var pattern = /^[a-zA-Z0-9-\u4E00-\u9FA5_,， ]{2,18}$/;
    return pattern.test(str.trim());
}

/**
 * Validate 2-100 character alphanumeric and Chinese strings
 *
 * @param str
 * @returns {boolean}
 */
function validCN_ENString2_100(str) {
    var pattern = /^[a-zA-Z0-9-\u4E00-\u9FA5_,， ]{2,100}$/;
    return pattern.test(str.trim());
}

/**
 * Password validation Minimum 6 characters, maximum 20 characters (letters or numbers)
 *
 * @param password
 * @returns {boolean}
 */
function validPassword(password) {
    var pattern = /^[a-zA-Z0-9]{6,20}$/;
    return pattern.test(password.trim());
}

<!-- Regular Expression Validation End-->

/**
 * Get the selected row in jqGrid
 * @returns {*}
 */
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        Swal.fire({
            text: "Please select a record",
            icon: "warning", iconColor: "#dea32c",
        });
        return;
    }
    var selectedIDs = grid.getGridParam("selarrrow");
    if (selectedIDs.length > 1) {
        Swal.fire({
            text: "Only one record can be selected",
            icon: "warning", iconColor: "#dea32c",
        });
        return;
    }
    return selectedIDs[0];
}

/**
 * Get the selected row in jqGrid (without alert)
 * @returns {*}
 */
function getSelectedRowWithoutAlert() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        return;
    }
    var selectedIDs = grid.getGridParam("selarrrow");
    if (selectedIDs.length > 1) {
        return;
    }
    return selectedIDs[0];
}

/**
 * Get the selected rows in jqGrid
 * @returns {*}
 */
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        Swal.fire({
            text: "Please select a record",
            icon: "warning", iconColor: "#dea32c",
        });
        return;
    }
    return grid.getGridParam("selarrrow");
}
