import { RegisteredUser } from '../model/RegisteredUser';
import { App } from '../App'
export class RegisterService {

    public static registerUser(user: RegisteredUser): boolean {
        let val = false;
        $.ajax({
            url: 'register/registerUser',
            context: document.body,
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            async: false,
            data: JSON.stringify(user),
            success: ()=>{val = true},
            error: ()=>{val = false}
        });
        return val;
    }

}