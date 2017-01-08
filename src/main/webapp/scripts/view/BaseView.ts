import { LoginService } from '../services/LoginService';
import { CookieUtil } from '../utils/CookieUtil';

export class BaseView {

    private documentListButton: JQuery;
    private loginButton: JQuery;
    private registerViewButton: JQuery;
    private logoffButton: JQuery;
    private adminPanelButton: JQuery;
    private userPanelButton: JQuery;
    private loggedInTimer: number;

    constructor() {
        this.documentListButton = $('.documents-button');
        this.adminPanelButton = $('.admin-button');
        this.userPanelButton = $('.user-button');
        this.logoffButton = $('.logoff-button');
        this.registerViewButton = $('.register-button');
        this.loginButton = $('.loginButton');

        this.switchEvents();
    }

    private switchEvents() {
        if (CookieUtil.getCookie('auth_tkt')) {
            let permission = CookieUtil.getCookie('permission');
            if (permission && permission === 'S') {
                this.enableAdmin();
            }
            else if (permission) {
                this.enableLogged();
            }
        }
    }

    private setLogoffEvent() {
        this.logoffButton.click(() => {
            LoginService.logoff(() => {
                this.enableLoggedOff();
            });
        });
    }

    private isLoggedHandler() {
        this.loggedInTimer = setTimeout(() => {
            LoginService.isLogged((response) => {
                if (!response) {
                    this.enableLoggedOff();
                }
                else {
                    this.isLoggedHandler();
                }
            });
        }, 60 * 5 * 1000);

    }

    private disableLoggedCookies() {
        CookieUtil.deleteCookie('user');
        CookieUtil.deleteCookie('auth_tkt');
        CookieUtil.deleteCookie('permission');
    }

    private enableLogged(): void {
        this.documentListButton.css('display', 'inline');
        this.userPanelButton.css('display', 'inline');
        this.logoffButton.css('display', 'inline');
        this.loginButton.css('display', 'none');
        this.registerViewButton.css('display', 'none');
        this.setLogoffEvent();
        this.isLoggedHandler();
    }

    private enableAdmin(): void {
        this.enableLogged();
        this.adminPanelButton.css('display', 'inline');
    }

    private enableLoggedOff(): void {
        this.disableLoggedCookies();
        this.documentListButton.css('display', 'none');
        this.userPanelButton.css('display', 'none');
        this.logoffButton.css('display', 'none');
        this.loginButton.css('display', 'inline');
        this.registerViewButton.css('display', 'inline');
        this.adminPanelButton.css('display', 'none');
        window.location.href = "../index";
    }
}