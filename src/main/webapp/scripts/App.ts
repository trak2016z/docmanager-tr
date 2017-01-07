/// <reference path="../typings/index.d.ts" />
//@ sourceMappingUrl=../bundle.js.map
import { RegisterView } from './view/RegisterView';
import { LoginView } from './view/LoginView';
import { DocumentView } from './view/DocumentView';
import { PublicationView } from './view/PublicationView';
import { SearchView } from './view/SearchView';
export class App {
    constructor() {
    }
}
window.onload = () => {
    var openedSite = window.location.toString().split("/").pop();
    var currentView;
    if (!openedSite || openedSite == window.location.host) {
        currentView = new SearchView();
    }
    else {
        
        switch (openedSite) {
            case 'register':
                currentView = new RegisterView();
                break;
            case 'login':
                currentView = new LoginView();
                break;
            case 'upload':
                currentView = new DocumentView();
                break;
            case 'mydocuments':
                currentView = new PublicationView();
                break;
            default:
                if (openedSite.includes("docedit")) {
                    var extractedId = openedSite.substr(7);
                    var id = Number.parseInt(extractedId);
                    currentView = new DocumentView(id);
                }
                break;
        }
    }
}

