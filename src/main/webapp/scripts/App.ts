/// <reference path="../typings/index.d.ts" />
//@ sourceMappingUrl=../bundle.js.map
import { RegisterView } from './view/RegisterView';
import * as jQuery from "jquery"
export class App {

    constructor() {
    }

}
window.onload = () => {
    var openedSite = window.location.toString().split("/").pop();
    var currentView;
    switch (openedSite) {
        case 'register' : 
        currentView = new RegisterView();
        break;
    }
}

