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
        // let regexp = new RegExp('[a-z][A-Z][0-9]','gi')
        // let permittedSymbols = email.match('');
        return true;
    }
}