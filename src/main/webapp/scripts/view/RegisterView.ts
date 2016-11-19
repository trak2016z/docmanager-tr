import { RegisteredUser } from '../model/RegisteredUser';
import { UserValidator } from '../utils/UserValidator';
export class RegisterView {
    private form: JQuery;
    private registerButton: JQuery;
    private encryptor;

    constructor() {
        this.init();
    }

    public init(): void {
        this.form = $('#form-register');
        this.registerButton = this.form.find('.form-register-button');
        this.encryptor = new jsSHA('SHA-512', "TEXT");

        this.registerButton.click(()=>{
            let data = this.getDataFromForm();
            let isValid = this.validateData(data);
            if (isValid){
                data = this.encryptPassword(data);
                
            }
        });

    }

    private validateData(userData: RegisteredUser): boolean {
    
        let validator = new UserValidator();
        if (!userData.dob){
           return false; 
        }
        if (!userData.email || validator.isEmailValid(userData.email)){
            return false;
        }

        return true;
    }

    private encryptPassword (userData: RegisteredUser): RegisteredUser{ 
        let encryptedPw = this.encryptor.getHash(userData.password);
        userData.password = encryptedPw;
        return userData;
    }

    private getDataFromForm(): RegisteredUser {
        let email: string = this.form.find('.email-edit').val();
        let password: string = this.form.find('.pw-edit').val();
        let firstName: string = this.form.find('.fname-edit').val();
        let lastName: string = this.form.find('.lname-edit').val();
        let phone: string = this.form.find('.edit-phone').val();
        let dob: Date = this.form.find('.edit-dob').val();
        let middlename: string = this.form.find('.mname-edit').val();

        let data: RegisteredUser = {
            dob: dob,
            firstName: firstName,
            password: password,
            lastName: lastName,
            phone: phone,
            email: email,
            middleName: middlename
        }

        return data;
    }



}

