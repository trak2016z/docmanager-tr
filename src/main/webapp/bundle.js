/******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;
/******/
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ([
/* 0 */
/***/ function(module, exports, __webpack_require__) {

	"use strict";
	/// <reference path="../typings/index.d.ts" />
	//@ sourceMappingUrl=../bundle.js.map
	var RegisterView_1 = __webpack_require__(1);
	var LoginView_1 = __webpack_require__(4);
	var App = (function () {
	    function App() {
	    }
	    return App;
	}());
	exports.App = App;
	window.onload = function () {
	    var openedSite = window.location.toString().split("/").pop();
	    var currentView;
	    switch (openedSite) {
	        case 'register':
	            currentView = new RegisterView_1.RegisterView();
	            break;
	        case 'login':
	            currentView = new LoginView_1.LoginView();
	            break;
	    }
	};


/***/ },
/* 1 */
/***/ function(module, exports, __webpack_require__) {

	"use strict";
	var RegisterService_1 = __webpack_require__(2);
	var UserValidator_1 = __webpack_require__(3);
	var RegisterView = (function () {
	    function RegisterView() {
	        this.userValidator = new UserValidator_1.UserValidator();
	        this.init();
	    }
	    RegisterView.prototype.init = function () {
	        var _this = this;
	        this.form = $('#form-register');
	        this.registerButton = this.form.find('.form-register-button');
	        this.registerFailedModal = $('#registerFailedModal');
	        this.registerSuccededModal = $('#registerSuccededModal');
	        this.emailInput = this.form.find('.email-edit');
	        this.passwordInput = this.form.find('.pw-edit');
	        this.firstNameInput = this.form.find('.fname-edit');
	        this.middleNameInput = this.form.find('.mname-edit');
	        this.lastNameInput = this.form.find('.lname-edit');
	        this.dobInput = this.form.find('.dob-edit');
	        this.phoneInput = this.form.find('.phone-edit');
	        this.emailInput.change();
	        this.registerButton.click(function () {
	            var data = _this.getDataFromForm();
	            var isValid = _this.validateData(data);
	            if (isValid) {
	                data = _this.encryptPassword(data);
	                RegisterService_1.RegisterService.registerUser(data, function () {
	                    _this.registerSuccededModal.find('.succededbtn').click(function () {
	                        window.location.href = window.location.href + '/..';
	                    });
	                    _this.registerSuccededModal.modal('toggle');
	                }, function () {
	                    _this.registerFailedModal.modal('toggle');
	                });
	            }
	        });
	    };
	    RegisterView.prototype.encryptPassword = function (userData) {
	        userData.password = CryptoJS.SHA512(userData.password).toString();
	        return userData;
	    };
	    RegisterView.prototype.getDataFromForm = function () {
	        var email = this.emailInput.val();
	        var password = this.passwordInput.val();
	        var firstName = this.firstNameInput.val();
	        var lastName = this.lastNameInput.val();
	        var phone = this.phoneInput.val();
	        var dob = this.dobInput.val();
	        var middlename = this.middleNameInput.val();
	        var data = {
	            dob: dob,
	            firstName: firstName,
	            password: password,
	            lastName: lastName,
	            phone: phone,
	            email: email,
	            middleName: middlename
	        };
	        return data;
	    };
	    RegisterView.prototype.validateData = function (userData) {
	        var isValid = true;
	        if (!this.validateEmail(userData.email)) {
	            isValid = false;
	        }
	        if (!this.validateDob(userData.dob)) {
	            isValid = false;
	        }
	        if (!this.validatePassword(userData.password)) {
	            isValid = false;
	        }
	        if (!this.validateFirstName(userData.firstName)) {
	            isValid = false;
	        }
	        if (!this.validateLastName(userData.lastName)) {
	            isValid = false;
	        }
	        if (!this.validatePhone(userData.phone)) {
	            isValid = false;
	        }
	        return isValid;
	    };
	    RegisterView.prototype.validateFirstName = function (firstName) {
	        var warn = this.form.find('.fname-group .fname-warn');
	        warn.text('');
	        if (!firstName) {
	            warn.text('Nie podano imienia');
	            return false;
	        }
	        return true;
	    };
	    RegisterView.prototype.validateLastName = function (lastName) {
	        var warn = this.form.find('.lname-group .lname-warn');
	        warn.text('');
	        if (!lastName) {
	            warn.text('Nie podano nazwiska');
	            return false;
	        }
	        return true;
	    };
	    RegisterView.prototype.validatePhone = function (phoneNumber) {
	        var warn = this.form.find('.phone-group .phone-warn');
	        warn.text('');
	        if (!phoneNumber) {
	            warn.text('Nie podano numeru telefonu');
	            return false;
	        }
	        return true;
	    };
	    RegisterView.prototype.validateDob = function (dob) {
	        var warn = this.form.find('.dob-group .dob-warn');
	        warn.text('');
	        if (!dob) {
	            warn.text('Nie podano daty urodzenia');
	            return false;
	        }
	        return true;
	    };
	    RegisterView.prototype.validatePassword = function (password) {
	        var warn = this.form.find('.pw-group .pw-warn');
	        if (!password) {
	            warn.text('Nie podano has��a');
	            return false;
	        }
	        else if (password.length < 8) {
	            warn.text('Has��o jest za krotkie');
	            return false;
	        }
	        return true;
	    };
	    RegisterView.prototype.validateEmail = function (email) {
	        var warn = this.form.find('.email-group .email-warn');
	        warn.text('');
	        if (!email) {
	            warn.text('Brak adresu email');
	            return false;
	        }
	        if (!this.userValidator.isEmailValid(email)) {
	            warn.text('Nieprawid��owy adres');
	            return false;
	        }
	        RegisterService_1.RegisterService.checkAvailableEmail(email, function (item) {
	            warn.text('Adres zaj��ty');
	            return !item;
	        });
	        return true;
	    };
	    return RegisterView;
	}());
	exports.RegisterView = RegisterView;


/***/ },
/* 2 */
/***/ function(module, exports) {

	"use strict";
	var RegisterService = (function () {
	    function RegisterService() {
	    }
	    RegisterService.registerUser = function (user, successHandler, failHandler) {
	        var request = $.ajax({
	            url: 'register/registerUser',
	            context: document.body,
	            type: 'POST',
	            contentType: 'application/json; charset=utf-8',
	            dataType: 'text',
	            async: false,
	            data: JSON.stringify(user)
	        }).done(successHandler).fail(failHandler);
	    };
	    RegisterService.checkAvailableEmail = function (email, handler) {
	        var request = $.ajax({
	            url: 'register/userAvailable',
	            context: document.body,
	            type: 'GET',
	            contentType: 'application/json; charset=utf-8',
	            dataType: 'text',
	            async: false,
	            data: 'email=' + email
	        }).done(handler);
	    };
	    return RegisterService;
	}());
	exports.RegisterService = RegisterService;


/***/ },
/* 3 */
/***/ function(module, exports) {

	"use strict";
	var UserValidator = (function () {
	    function UserValidator() {
	    }
	    UserValidator.prototype.isEmailValid = function (email) {
	        var emailParts = email.split('@');
	        if (emailParts.length !== 2) {
	            return false;
	        }
	        var domainParts = emailParts[1].split('.');
	        if (domainParts.length < 2) {
	            return false;
	        }
	        // let regexp = new RegExp('[a-z][A-Z][0-9]','gi')
	        // let permittedSymbols = email.match('');
	        return true;
	    };
	    return UserValidator;
	}());
	exports.UserValidator = UserValidator;


/***/ },
/* 4 */
/***/ function(module, exports, __webpack_require__) {

	"use strict";
	var LoginService_1 = __webpack_require__(5);
	var LoginView = (function () {
	    function LoginView() {
	        this.loginForm = $('#form-login');
	        this.resetForm = $('#form-reset');
	        this.loginFailedModal = $('#loginFailedModal');
	        this.setLoginButton();
	        this.setResetPasswordButton();
	    }
	    LoginView.prototype.setLoginButton = function () {
	        var _this = this;
	        var loginButton = this.loginForm.find('.form-login-button');
	        loginButton.click(function () {
	            var email = _this.loginForm.find('.email-edit').val();
	            var password = _this.loginForm.find('.pw-edit').val();
	            password = CryptoJS.SHA512(password).toString();
	            var data = {
	                email: email,
	                password: password
	            };
	            LoginService_1.LoginService.loginUser(data, function () {
	                document.cookie = "cookie1=authenticated;";
	                window.location.href = window.location.href + '/..';
	            }, function () {
	                _this.loginFailedModal.modal('toggle');
	            });
	        });
	    };
	    LoginView.prototype.setResetPasswordButton = function () {
	        var _this = this;
	        var resetButton = this.resetForm.find('.form-reset-button');
	        resetButton.click(function () {
	            var email = _this.resetForm.find('.email-edit').val();
	            LoginService_1.LoginService.resetPassword(email, function () {
	                $('#resetSucceededModal').modal('toggle');
	            }, function () {
	                $('#resetFailedModal').modal('toggle');
	            });
	        });
	    };
	    return LoginView;
	}());
	exports.LoginView = LoginView;


/***/ },
/* 5 */
/***/ function(module, exports) {

	"use strict";
	var LoginService = (function () {
	    function LoginService() {
	    }
	    LoginService.loginUser = function (user, successHandler, failHandler) {
	        var request = $.ajax({
	            url: 'loginservice/login',
	            context: document.body,
	            type: 'POST',
	            contentType: 'application/json; charset=utf-8',
	            dataType: 'text',
	            async: false,
	            data: JSON.stringify(user)
	        }).done(successHandler).fail(failHandler);
	    };
	    LoginService.resetPassword = function (email, successHandler, failHandler) {
	        var request = $.ajax({
	            url: 'loginservice/reset',
	            context: document.body,
	            type: 'GET',
	            dataType: 'text',
	            async: false,
	            data: 'email=' + email
	        }).done(successHandler).fail(failHandler);
	    };
	    return LoginService;
	}());
	exports.LoginService = LoginService;


/***/ }
/******/ ]);
//# sourceMappingURL=bundle.js.map