/// <reference path="../typings/index.d.ts" />
import { RegisterView } from './view/RegisterView';

class App {

    constructor() {
        this.init();
    }

    private init () : void {
        new RegisterView();
    }
}
