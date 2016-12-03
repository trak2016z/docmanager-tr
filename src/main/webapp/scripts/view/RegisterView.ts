import { RegisteredUser } from '../model/RegisteredUser';
import { RegisterService } from '../services/RegisterService';
import { UserValidator } from '../utils/UserValidator';
export class RegisterView {
    private form: JQuery;
    private registerButton: JQuery;
    private encryptor;
    private registerFailedModal: JQuery;
    private registerSuccededModal: JQuery;
    private inputs: any;
    private userValidator: UserValidator;

    private emailInput: JQuery;
    private passwordInput: JQuery;
    private firstNameInput: JQuery;
    private middleNameInput: JQuery;
    private lastNameInput: JQuery;
    private dobInput: JQuery;
    private phoneInput: JQuery;

    constructor() {
        this.userValidator = new UserValidator();

        this.init();
    }

    public init(): void {
        this.form = $('#form-register');
        this.registerButton = this.form.find('.form-register-button');
        this.encryptor = new jsSHA('SHA-512', "TEXT");
        this.registerFailedModal = $('#registerFailedModal');
        this.registerSuccededModal = $('#registerSuccededModal');

        this.emailInput = this.form.find('.email-edit');
        this.passwordInput = this.form.find('.pw-edit');
        this.firstNameInput = this.form.find('.fname-edit');
        this.middleNameInput = this.form.find('.mname-input');
        this.lastNameInput = this.form.find('.lname-input');
        this.dobInput = this.form.find('.dob-edit');
        this.phoneInput = this.form.find('.phone-edit');

        this.emailInput.change()

        this.registerButton.click(() => {

            let data = this.getDataFromForm();
            let isValid = this.validateData(data);
            if (isValid) {
                data = this.encryptPassword(data);
                RegisterService.registerUser(data, () => {
                    this.registerSuccededModal.find('.succededbtn').click(() => {
                        window.location.href = window.location.href + '/..';
                    });
                    this.registerSuccededModal.modal('toggle');
                }, () => {
                    this.registerFailedModal.modal('toggle');
                });
            }
        });

    }

    private encryptPassword(userData: RegisteredUser): RegisteredUser {
        this.encryptor.update(userData.password);
        let encryptedPw = this.encryptor.getHash("HEX");
        userData.password = encryptedPw;
        return userData;
    }

    private getDataFromForm(): RegisteredUser {

        let email: string = this.emailInput.val();
        let password: string = this.passwordInput.val();
        let firstName: string = this.firstNameInput.val();
        let lastName: string = this.lastNameInput.val();
        let phone: string = this.phoneInput.val();
        let dob: Date = this.dobInput.val();
        let middlename: string = this.middleNameInput.val();
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

    private validateData(userData: RegisteredUser): boolean {

        let isValid = true;
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
    }

    private validateFirstName(firstName) {
        let warn = this.form.find('.fname-group .fname-warn');
        warn.text('');
        if (!firstName) {
            warn.text('Nie podano imienia');
            return false;
        }
        return true;
    }
    private validateLastName(lastName) {
        let warn = this.form.find('.lname-group .lname-warn');
        warn.text('');
        if (!lastName) {
            warn.text('Nie podano nazwiska');
            return false;
        }
        return true;
    }
    private validatePhone(phoneNumber) {
        let warn = this.form.find('.phone-group .phone-warn');
        warn.text('');
        if (!phoneNumber) {
            warn.text('Nie podano numeru telefonu');
            return false;
        }
        return true;
    }

    private validateDob(dob): boolean {
        let warn = this.form.find('.dob-group .dob-warn');
        warn.text('');
        if (!dob) {
            warn.text('Nie podano daty urodzenia');
            return false;
        }
        return true;
    }

    private validatePassword(password: string): boolean {
        let warn = this.form.find('.pw-group .pw-warn');
        if (!password) {
            warn.text('Nie podano hasła');
            return false;
        }
        else if (password.length < 8) {
            warn.text('Hasło jest za ktotkie');
            return false;
        }
        return true;
    }

    private validateEmail(email): boolean {
        let warn = this.form.find('.email-group .email-warn');
        warn.text('');
        if (!email) {
            warn.text('Brak adresu email');
            return false;
        }
        if (!this.userValidator.isEmailValid(email)) {
            warn.text('Nieprawidłowy adres');
            return false;
        }
        RegisterService.checkAvailableEmail(email, (item: boolean) => {
            warn.text('Adres zajęty');
            return !item;
        });
        return true;
    }

}