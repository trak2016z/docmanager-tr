import { PublicationIndicator } from "../model/PublicationIndicator";
import { DocumentService } from "../services/DocumentService";
import { CookieUtil } from "../utils/CookieUtil";
import * as moment from "moment";
export class PublicationView {

    private publicationTable: JQuery;

    constructor() {
        this.init();
    }

    private init(): void {
        this.publicationTable = $('#publications tbody');
        this.resolveUserPublications();
    }

    private resolveUserPublications() {
        
        let handler = (response: string) => {
            let publications: Array<PublicationIndicator> = JSON.parse(response);
            let tableBody: string = "";
            publications.forEach(element => {
                tableBody += '<tr><td>' + element.name + '</td><td>' + moment(element.lastUpdate).format("DD-MM-YYYY HH:mm") + '</td></tr>';
            });
            this.publicationTable.append(tableBody);
        }
        let user = CookieUtil.getCookie('user');
        DocumentService.getUserPublications(user, handler);
    }
}
