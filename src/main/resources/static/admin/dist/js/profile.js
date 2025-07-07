$(function () {
    // Update personal information
    $('#updateUserNameButton').click(function () {
        $("#updateUserNameButton").attr("disabled", true);
        var userName = $('#loginUserName').val();
        var nickName = $('#nickName').val();
        if (validUserNameForUpdate(userName, nickName)) {
            // Ajax submit data
            var params = $("#userNameForm").serialize();
            $.ajax({
                type: "POST",
                url: "/admin/profile/name",
                data: params,
                success: function (r) {
                    $("#updateUserNameButton").attr("disabled", false);
                    console.log(r);
                    if (r == 'success') {
                        alert('Edit successful');
                    } else {
                        alert('Fail to edit');
                    }
                }
            });
        } else {
            $("#updateUserNameButton").attr("disabled", false);
        }
    });

    // Update password
    $('#updatePasswordButton').click(function () {
        $("#updatePasswordButton").attr("disabled", true);
        var originalPassword = $('#originalPassword').val();
        var newPassword = $('#newPassword').val();
        if (validPasswordForUpdate(originalPassword, newPassword)) {
            var params = $("#userPasswordForm").serialize();
            $.ajax({
                type: "POST",
                url: "/admin/profile/password",
                data: params,
                success: function (r) {
                    $("#updatePasswordButton").attr("disabled", false);
                    console.log(r);
                    if (r == 'success') {
                        alert('Edit successful');
                        window.location.href = '/admin/login';
                    } else {
                        alert('Fail to edit');
                    }
                }
            });
        } else {
            $("#updatePasswordButton").attr("disabled", false);
        }
    });
});

/**
 * Username validation
 */
function validUserNameForUpdate(userName, nickName) {
    if (isNull(userName) || userName.trim().length < 1) {
        $('#updateUserName-info').css("display", "block");
        $('#updateUserName-info').html("Please enter login name!");
        return false;
    }
    if (isNull(nickName) || nickName.trim().length < 1) {
        $('#updateUserName-info').css("display", "block");
        $('#updateUserName-info').html("Nickname cannot be empty!");
        return false;
    }
    if (!validUserName(userName)) {
        $('#updateUserName-info').css("display", "block");
        $('#updateUserName-info').html("Please enter a valid login name!");
        return false;
    }
    if (!validCN_ENString2_18(nickName)) {
        $('#updateUserName-info').css("display", "block");
        $('#updateUserName-info').html("Please enter a valid nickname!");
        return false;
    }
    return true;
}

/**
 * Password validation
 */
function validPasswordForUpdate(originalPassword, newPassword) {
    if (isNull(originalPassword) || originalPassword.trim().length < 1) {
        $('#updatePassword-info').css("display", "block");
        $('#updatePassword-info').html("Please enter the original password!");
        return false;
    }
    if (isNull(newPassword) || newPassword.trim().length < 1) {
        $('#updatePassword-info').css("display", "block");
        $('#updatePassword-info').html("New password cannot be empty!");
        return false;
    }
    if (!validPassword(newPassword)) {
        $('#updatePassword-info').css("display", "block");
        $('#updatePassword-info').html("Please enter a valid password!");
        return false;
    }
    return true;
}
