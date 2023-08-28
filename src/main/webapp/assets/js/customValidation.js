$(document).ready(function () {

    // $("#firstname_error").hide();
    // $("#middlename_error").hide();
    // $("#lastname_error").hide();
    // $("#email_error").hide();
    // $("#password_error").hide();
    // $("#confirm_password_error").hide();
    // $("#radio_error").hide();
    // $("#check_error").hide();
    // $("#pincode_error").hide();
    // $("#landmark_error").hide();
    // $("#street_error").hide();
    // $("#city_error").hide();
    // $("#state_error").hide();
    // $("#dob_error").hide();
    // $("#houseno_error").hide();


    var name_error = false;
    var firstname_error = false;
    var middlename_error = false;
    var lastname_error = false;
    var email_error = false;
    var username_error = false;
    var password_error = false;
    var hobbies_error = false;
    var confirm_password_error = false;
    // var contact_error = false;
    var check_error = false;
    var pincode_error = false;
    var landmark_error = false;
    var street_error = false;
    var city_error = false;
    var state_error = false;
    var dob_error = false;
    var houseno_error = false;
    //var selector;

    //Other field validation
    $("#firstname").focusout(function () {
        // $("#firstname_error").hide();
        // $("#firstname").css("border-bottom", "none");
    });
    $("#firstname").keyup(function () {
        check_name();
    });
    $("#firstname").focus(function () {
        check_name();
    });


    $("#middlename").focusout(function () {
        // $("#middlename_error").hide();
        // $("#middlename").css("border-bottom", "none");
    });
    $("#middlename").keyup(function () {
        check_middlename();
    });
    $("#middlename").focus(function () {
        check_middlename();
    });


    $("#lastname").focusout(function () {
        // $("#lastname_error").hide();
        // $("#lastname").css("border-bottom", "none");
    });
    $("#lastname").keyup(function () {
        check_lastname();
    });
    $("#lastname").focus(function () {
        check_lastname();
    });

    $("#emailid").focusout(function () {
        // $("#email_error").hide();
        // $("#mail").css("border-bottom", "none");
    });
    $("#emailid").keyup(function () {
        check_email();
    });
    $("#emailid").focus(function () {
        check_email();
    });

    $("#pwd").focusout(function () {
        // $("#password_error").hide();
        // $("#pwd").css("border-bottom", "none");
    });
    $("#pwd").keyup(function () {
        check_password();
    });
    $("#pwd").focus(function () {
        check_password();
    });

    $("#cpwd").focusout(function () {
        // $("#confirm_password_error").hide();
        // $("#cpwd").css("border-bottom", "none");
    });
    $("#cpwd").keyup(function () {
        check_confirm_password();
    });
    $("#cpwd").focus(function () {
        check_confirm_password();
    });


    $("#username").focusout(function () {
        // $("#lastname_error").hide();
        // $("#lastname").css("border-bottom", "none");
    });
    $("#username").keyup(function () {
        check_username();
    });
    $("#username").focus(function () {
        check_username();
    });

    $("#hobbies").focusout(function () {
        // $("#lastname_error").hide();
        // $("#lastname").css("border-bottom", "none");
    });
    $("#hobbies").keyup(function () {
        check_hobbies();
    });
    $("#hobbies").focus(function () {
        check_hobbies();
    });


    $('input[name="options"]').change(function () {
        check_checkbox();
    });
    $('input[name="options"]').focusout(function () {
        // $("#check_error").hide();
    });


    $("#dob").change(function () {
        check_date();
    });
    $("#dob").focusout(function () {
        // $("#dob_error").hide();
    });


    $('a#add-more').cloneData({
        mainContainerId: 'main-container', // Main container Should be ID
        cloneContainer: 'container-item', // Which you want to clone
        removeButtonClass: 'remove-item', // Remove button for remove cloned HTML
        removeConfirm: true, // default true confirm before delete clone item
        removeConfirmMessage: 'Are you sure want to delete?', // confirm delete message
        //append: '<a href="javascript:void(0)" class="remove-item btn btn-sm btn-danger remove-social-media">Remove</a>', // Set extra HTML append to clone HTML
        minLimit: 1, // Default 1 set minimum clone HTML required
        /* 			maxLimit : 5, // Default unlimited or set maximum limit of clone HTML
         */
        defaultRender: 1,
        afterRender: function () {

            for (var i = 0; i < $(".container-item").length; i++) {
                var selector = `.container-item[data-index="${i}"]`;

                setAllCallbacks(selector);

            }
        },
    });

    //Address field validation
    var defaultSelector = '.container-item[data-index="0"]';
    setAllCallbacks(defaultSelector);

    function setAllCallbacks(selector) {

        //street
        $(selector + " #street").focusout(function () {
            // $(selector + " #street_error").hide();
            // $(selector + " #street").css("border-bottom", "1px solid #ccc");
            check_street(selector);
        });
        $(selector + " #street").keyup(function () {
            check_street(selector);
        });
        $(selector + " #street").focus(function () {
            check_street(selector);
        });

        //pincode
        $(selector + " #pincode").focusout(function () {
            // $(selector + " #pincode_error").hide();
            // $(selector + " #pincode").css("border-bottom", "1px solid #ccc");
            check_pincode(selector);

        });
        $(selector + " #pincode").keyup(function () {
            check_pincode(selector);
        });
        $(selector + " #pincode").focus(function () {
            check_pincode(selector);
        });

        //landmark
        $(selector + " #landmark").focusout(function () {
            // $(selector + " #landmark_error").hide();
            // $(selector + " #landmark").css("border-bottom", "1px solid #ccc");
            check_landmark(selector);

        });
        $(selector + " #landmark").keyup(function () {
            check_landmark(selector);
        });
        $(selector + " #landmark").focus(function () {
            check_landmark(selector);
        });

        //house-no.
        $(selector + " #houseno").focusout(function () {
            // $(selector + " #houseno_error").hide();
            // $(selector + " #houseno").css("border-bottom", "1px solid #ccc");
            check_houseno(selector);

        });
        $(selector + " #houseno").keyup(function () {
            check_houseno(selector);
        });
        $(selector + " #houseno").focus(function () {
            check_houseno(selector);
        });


        //city
        $(selector + " #city").change(function () {
            check_city(selector);
        });
        $(selector + " #city").focusout(function () {
            // $(selector + " #city_error").hide();
            //check_city(defaultSelector);

        });

        //state
        $(selector + " #state").change(function () {
            check_state(selector);
        });
        $(selector + " #state").focusout(function () {
            // $(selector + " #state_error").hide();
        });

    }

    // validation for pincode
    function check_pincode(selector) {

        var pattern = /^[\w-_.]*$/ /*/^[0-9]+$/;*/
        var pincode = $(selector + " #pincode").val();
        if (pincode.length == 0) {
            $(selector + " #pincode_error").html("pincode can not be empty!!").css("color", "red");
            $(selector + " #pincode_error").show();
            $(selector + " #pincode").css("border-bottom", "4px solid #F90A0A");
            pincode_error = true;
        } else if (!pattern.test(pincode)) {
            $(selector + " #pincode_error").html("Pincode contain only numbers and letters").css("color", "red");
            $(selector + " #pincode_error").show();
            $(selector + " #pincode").css("border-bottom", "4px solid #F90A0A");
            pincode_error = true;
        } else {
            $(selector + " #pincode_error").hide();
            $(selector + " #pincode").css("border-bottom", "4px solid #34F458");
        }
    }

    function check_landmark(selector) {

        var landmark = $(selector + " #landmark").val();
        if (landmark.length == 0) {
            $(selector + " #landmark_error").html("landmark can not be empty!!").css("color", "red");
            $(selector + " #landmark_error").show();
            $(selector + " #landmark").css("border-bottom", "4px solid #F90A0A");
            landmark_error = true;
        } else {
            $(selector + " #landmark_error").hide();
            $(selector + " #landmark").css("border-bottom", "4px solid #34F458");
        }
    }

    function check_houseno(selector) {

        var houseno = $(selector + " #houseno").val();
        if (houseno.length == 0) {
            $(selector + " #houseno_error").html("house no. can not be empty!!").css("color", "red");
            $(selector + " #houseno_error").show();
            $(selector + " #houseno").css("border-bottom", "4px solid #F90A0A");
            houseno_error = true;
        } else {
            $(selector + " #houseno_error").hide();
            $(selector + " #houseno").css("border-bottom", "4px solid #34F458");
        }
    }

    function check_street(selector) {
        var street = $(selector + " #street").val();
        if (street.length == 0) {
            $(selector + " #street_error").html("Street can not be empty!!").css("color", "red");
            $(selector + " #street_error").show();
            $(selector + " #street").css("border-bottom", "4px solid #F90A0A");
            street_error = true;
        } else {
            $(selector + " #street_error").hide();
            $(selector + " #street").css("border-bottom", "4px solid #34F458");
        }

    }

    function check_state(selector) {
        var state = $(selector + " #state").val();
        if (state == "0") {
            $(selector + " #state_error").html("Please select State!!").css("color", "red");
            $(selector + " #state_error").show();
            state_error = true;
        } else {
            $(selector + " #state_error").hide();
        }
    }

    function check_city(selector) {
        var city = $(selector + " #city").val();
        if (city == "0") {
            $(selector + " #city_error").html("Please select City!!").css("color", "red");
            $(selector + " #city_error").show();
            city_error = true;
        } else {
            $(selector + " #city_error").hide();
        }
    }

    // FIRSTNAME !!
    function check_name() {
        var pattern = /^[a-zA-Z\s]+$/;
        var name = $("#firstname").val();
        if (name.length == 0) {
            $("#firstname_error").html("Name can not be empty!!").css("color", "red");
            $("#firstname_error").show();
            $("#firstname").css("border-bottom", "4px solid #F90A0A");
            firstname_error = true;

        } else if (!pattern.test(name)) {
            $("#firstname_error").html("Should contain only Characters").css("color", "red");
            $("#firstname_error").show();
            $("#firstname").css("border-bottom", "4px solid #F90A0A");
            firstname_error = true;

        } else {
            $("#firstname_error").hide();
            $("#firstname").css("border-bottom", "4px solid #34F458");
        }
    }


    function check_middlename() {
        var pattern = /^[a-zA-Z\s]+$/;
        var name = $("#middlename").val();
        if (name.length == 0) {
            $("#middlename_error").html("Name can not be empty!!").css("color", "red");
            $("#middlename_error").show();
            $("#middlename").css("border-bottom", "4px solid #F90A0A");
            middlename_error = true;
        } else if (!pattern.test(name)) {
            $("#middlename_error").html("Should contain only Characters").css("color", "red");
            $("#middlename_error").show();
            $("#middlename").css("border-bottom", "4px solid #F90A0A");
            middlename_error = true;
        } else {
            $("#middlename_error").hide();
            $("#middlename").css("border-bottom", "4px solid #34F458");
        }
    }


    function check_lastname() {
        var pattern = /^[a-zA-Z\s]+$/;
        var name = $("#lastname").val();
        if (name.length == 0) {
            $("#lastname_error").html("Name can not be empty!!").css("color", "red");
            $("#lastname_error").show();
            $("#lastname").css("border-bottom", "4px solid #F90A0A");
            lastname_error = true;
        } else if (!pattern.test(name)) {
            $("#lastname_error").html("Should contain only Characters").css("color", "red");
            $("#lastname_error").show();
            $("#lastname").css("border-bottom", "4px solid #F90A0A");
            lastname_error = true;
        } else {
            $("#lastname_error").hide();
            $("#lastname").css("border-bottom", "4px solid #34F458");
        }
    }


    function check_email() {
        var pattern = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
        var email = $("#emailid").val();
        if (email.length == 0) {
            $("#email_error").html("Email can not be empty!!").css("color", "red");
            $("#email_error").show();
            $("#emailid").css("border-bottom", "4px solid #F90A0A");
            email_error = true;
        } else if (!pattern.test(email)) {
            $("#email_error").html("Invalid Email!!").css("color", "red");
            $("#email_error").show();
            $("#emailid").css("border-bottom", "4px solid #F90A0A");
            email_error = true;
        } else {
            $("#email_error").hide();
            $("#emailid").css("border-bottom", "4px solid #34F458");
            email_error = false;
        }

    }

    function check_password() {
        var password_length = $("#pwd").val().length;
        if (password_length < 8) {
            $("#password_error").html("Password should be at least 8 characters!!").css("color", "red");
            $("#password_error").show();
            $("#pwd").css("border-bottom", "4px solid #F90A0A");
            password_error = true;
        } else {
            $("#password_error").hide();
            $("#pwd").css("border-bottom", "4px solid #34F458");
            password_error = false;
        }
    }

    function check_confirm_password() {
        var password = $("#pwd").val();
        var retype_password = $("#cpwd").val();
        if (retype_password === '') {
            $("#confirm_password_error").html("confirm password can not be empty!!").css("color", "red");
            $("#confirm_password_error").show();
            $("#cpwd").css("border-bottom", "4px solid #F90A0A");
            confirm_password_error = true;
        } else if (password !== retype_password) {
            $("#confirm_password_error").html("Passwords Did not Matched").css("color", "red");
            $("#confirm_password_error").show();
            $("#cpwd").css("border-bottom", "4px solid #F90A0A");
            confirm_password_error = true;
        } else if (password.length < 8) {
            $("#confirm_password_error").html("Passwords cannot be less than 8 characters!").css("color", "red");
            $("#confirm_password_error").show();
            $("#cpwd").css("border-bottom", "4px solid #F90A0A");
            confirm_password_error = true;
        } else {
            $("#confirm_password_error").hide();
            $("#cpwd").css("border-bottom", "4px solid #34F458");
        }
    }

    function check_username() {
        var pattern =  /^[a-zA-Z0-9!"#$%&'()*+,-./:;<=>?@^_`{|}~]+$/;
        var name = $("#username").val();
        if (name.length == 0) {
            $("#username_error").html("Username can not be empty!!").css("color", "red");
            $("#username_error").show();
            $("#username").css("border-bottom", "4px solid #F90A0A");
            username_error = true;
        } else if (!pattern.test(name)) {
            $("#username_error").html("Should can contain only Letters, Numbers and Special Characters").css("color", "red");
            $("#username_error").show();
            $("#username").css("border-bottom", "4px solid #F90A0A");
            username_error = true;
        } else {
            $("#username_error").hide();
            $("#username").css("border-bottom", "4px solid #34F458");
        }
    }


    function check_hobbies() {
        var pattern = /^[a-zA-Z\s]+$/;
        var name = $("#hobbies").val();
        if (name.length == 0) {
            $("#hobbies_error").html("Hobbies can not be empty!!").css("color", "red");
            $("#hobbies_error").show();
            $("#hobbies").css("border-bottom", "4px solid #F90A0A");
            lastname_error = true;
        } else if (!pattern.test(name)) {
            $("#hobbies_error").html("Should contain only Characters").css("color", "red");
            $("#hobbies_error").show();
            $("#hobbies").css("border-bottom", "4px solid #F90A0A");
            hobbies_error = true;
        } else {
            $("#hobbies_error").hide();
            $("#hobbies").css("border-bottom", "4px solid #34F458");
        }
    }


    function check_checkbox() {
        if ($("input:checkbox").filter(":checked").length == 0) {
            console.log("one must selected");
            $("#check_error").html("one input must be selected!!").css("color", "red");
            $("#check_error").show();
            check_error = true;
        } else {
            $("#check_error").hide();
        }
    }


    function check_date() {

        //getting today's date
        var today = new Date().toISOString().split('T')[0];

        var date = $("#dob").val();
        if (date.length == 0) {
            $("#dob_error").html("Date of Birth can not be empty!!").css("color", "red");
            $("#dob_error").show();
            dob_error = true;
        } else if (date < "1900-01-01") {
            $("#dob_error").html("Date of Birth can not be older than 1900 !!").css("color", "red");
            $("#dob_error").show();
            dob_error = true;
        } else if (date > today) {
            $("#dob_error").html("Date of Birth can not be greater than TODAY!!").css("color", "red");
            $("#dob_error").show();
            dob_error = true;
        } else {
            $("#dob_error").hide();
        }
    }

    /*for setting the max value of the date value in registrationjsp*/
    window.addEventListener('DOMContentLoaded', function () {
        var today = new Date().toISOString().split('T')[0];
        document.getElementById("dob").setAttribute("max", today);
    });


    $("#registration_form").submit(function () {
        firstname_error = true;
        middlename_error = false;
        lastname_error = false;
        email_error = false;
        password_error = false;
        confirm_password_error = false;
        pincode_error = false;
        landmark_error = false;
        street_error = false;
        houseno_error = false;
        city_error = false;
        state_error = false;
        dob_error = false;
        hobbies_error = false;
        username_error = false;


        check_name();
        check_middlename();
        check_lastname();
        check_email();
        check_password();
        check_confirm_password();
        check_checkbox();
        check_date();
        check_houseno();
        check_landmark();
        check_hobbies();
        check_username();

        if (firstname_error === false && middlename_error === false && lastname_error === false && email_error === false && password_error === false && confirm_password_error === false &&
            pincode_error === false && landmark_error === false && houseno_error === false && street_error === false && state_error === false && city_error === false && dob_error === false
            && hobbies_error === false && username_error === false) {
            alert("Registration Successful");
            return true;
        } else {
            alert("Please Fill the form Correctly");
            return false;
        }
    });


});

//
// /* validating the image inputs in registration jsp */
// $(document).ready(function () {
//     $("#image_error2").hide();
//     $("#img2").change(function () {
//         check_file2();
//         img_preview2(this);
//     });
//     $("#img2").focusout(function () {
//         // $("#image_error2").hide();
//     });
//
//     function check_file2() {
//         var file = $("#img2").val();
//         var fileType = file.split('.').pop().toLowerCase();
//         if ($("#img2")[0].files.length !== 0) {
//
//             $("#default_img2").css("display", "none");
//
//             if ($.inArray(fileType, ['png', 'jpg', 'jpeg']) == -1) {
//                 $("#image_error2").html("Please select valid file!!").css("color", "red");
//                 $("#image_error2").show();
//                 $("#image_preview2").css("display", "none");
//                 image_error = true;
//             }
//         }
//     }
//
//     function img_preview2(input) {
//         if (input.files && input.files[0]) {
//             var file = new FileReader();
//             file.onload = function (e) {
//                 $("#image_preview2").attr('src', e.target.result).css({
//                     "display": "block",
//                     "max-width": "170px",
//                     "max-height": "170px"
//                 });
//             }
//             file.readAsDataURL(input.files[0]);
//         }
//     }
//
// });