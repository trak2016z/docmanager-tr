import * as jQuery from "jquery";
import { LoginData } from '../model/LoginData'
import { LoginService } from '../services/LoginService'
export class LoginView {

    private loginForm: JQuery;
    private resetForm: JQuery;
    private loginFailedModal: JQuery;
    private encryptor;

    constructor() {
        this.loginForm = $('#form-login');
        this.resetForm = $('#form-reset');
        this.loginFailedModal = $('#loginFailedModal');
        this.setLoginButton();
        this.setResetPasswordButton();
    }

    private setLoginButton() {
        let loginButton = this.loginForm.find('.form-login-button');
        loginButton.click(() => {
            let email = this.loginForm.find('.email-edit').val();
            let password = this.loginForm.find('.pw-edit').val();
            password = CryptoJS.SHA512(password).toString();
            let data: LoginData = {
                email: email,
                password: password
            }
            LoginService.loginUser(data, () => {
                document.cookie = "cookie1=authenticated;";
                window.location.href = window.location.href + '/..';
            }, () => {
                this.loginFailedModal.modal('toggle');
            });
        });
    }

    private setResetPasswordButton () {
        let resetButton = this.resetForm.find('.form-reset-button');
        resetButton.click(()=>{
            let email :string = this.resetForm.find('.email-edit').val();
            LoginService.resetPassword(email, ()=>{
                $('#resetSucceededModal').modal('toggle');
            }, ()=>{
                $('#resetFailedModal').modal('toggle');
            });
        });
    }
}