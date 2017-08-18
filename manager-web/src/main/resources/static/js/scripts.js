jQuery(document).ready(function() {
    $('.page-container form').submit(function(){
        var username = $(this).find('.username').val();
        var password = $(this).find('.password').val();
        if(username == '') {
            var usernameObj =  $(this).parent().find('.username');
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', usernameObj.position().top);
            });
            $(this).find('.error').fadeIn('fast', function(){
                usernameObj.focus();
            });
            return false;
        }

        if(password == '') {
            var passwordObj =  $(this).parent().find('.password');
            $(this).find('.error').fadeOut('fast', function(){
                $(this).css('top', passwordObj.position().top);
            });
            $(this).find('.error').fadeIn('fast', function(){
                passwordObj.focus();
            });
            return false;
        }
    });

    $('.page-container form .username, .page-container form .password').keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
    });
});
