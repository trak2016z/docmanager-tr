export class UserValidator {

    public isEmailValid (email : string) : boolean {
        let emailParts = email.split('@');
        if (emailParts.length !== 2){
            return false;
        }
        let domainParts = emailParts[1].split('.');
        if (domainParts.length < 2){
            return false;
        }
        return true;
    }
}