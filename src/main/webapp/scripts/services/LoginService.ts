import { App } from '../App'
import { LoginData } from '../model/LoginData'

export class LoginService {
    public static loginUser(user: LoginData, successHandler, failHandler) {

        let request = $.ajax({
            url: 'loginservice/login',
            context: document.body,
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            async: false,
            data: JSON.stringify(user)
        }).done(successHandler).fail(failHandler);

    }
}