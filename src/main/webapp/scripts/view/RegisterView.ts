export class RegisterView {
    private mainBar : JQuery;
    
    constructor(){
        this.init();
    }

    public init () :void {
        this.initializeView();
    }

    public initializeView() : void {
        $(document).click(function (){
            console.log("dasdsa");
        });
        this.mainBar = $('#register-menu');
        this.mainBar.puimegamenu();
    }
    
}
window.onload = () => {
    var registerView = new RegisterView();
}

