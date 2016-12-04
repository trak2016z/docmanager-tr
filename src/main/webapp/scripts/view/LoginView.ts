import * as jQuery from "jquery";
import { LoginData } from '../model/LoginData'
import { LoginService } from '../services/LoginService'
export class LoginView {

    private loginForm : JQuery;
    private resetForm : JQuery;
    private encryptor;

    constructor() {
        this.loginForm = $('#form-login');
        this.resetForm = $('#form-reset');
        this.setLoginButton();
    }

    private setLoginButton (){
        let loginButton = this.loginForm.find('.form-login-button');
        loginButton.click(()=>{
            let email = this.loginForm.find('.email-edit').val();
            let password = this.loginForm.find('.pw-edit').val();
            password = CryptoJS.SHA512(password).toString();
            let data : LoginData = {
                email: email,
                password: password
            }
            LoginService.loginUser(data, ()=>{},()=>{});
        });
    }
}