import { RegisteredUser } from '../model/RegisteredUser';
import { App } from '../App'
export class RegisterService {

    public static registerUser(user: RegisteredUser, successHandler, failHandler) {

        let request = $.ajax({
            url: 'register/registerUser',
            context: document.body,
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            async: false,
            data: JSON.stringify(user)
        }).done(successHandler).fail(failHandler);

    }

    public static checkAvailableEmail(email: string, handler: any) {
        let request = $.ajax({
            url: 'register/userAvailable',
            context: document.body,
            type: 'GET',
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            async: false,
            data: 'email=' + email,
        }).done(handler);
    }

}