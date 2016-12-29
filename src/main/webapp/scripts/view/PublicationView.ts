/// <reference path="../../typings/index.d.ts" />
import { PublicationIndicator } from "../model/PublicationIndicator";
import { DocumentService } from "../services/DocumentService";
import { CookieUtil } from "../utils/CookieUtil";
import * as Collections from 'typescript-collections';
import * as moment from 'moment';
import * as $ from 'jquery';

export class PublicationView {

    private publicationTable: JQuery;
    private availablePublications: Collections.Dictionary<string, number>;

    constructor() {
        this.init();
    }

    private init(): void {
        this.availablePublications = new Collections.Dictionary<string, number>();
        this.publicationTable = $('#publications tbody');
        this.resolveUserPublications();
    }

    private resolveUserPublications() {
        
        let handler = (response: string) => {
            let publications: Array<PublicationIndicator> = JSON.parse(response);
            let tableBody: string = "";
            publications.forEach(element => {
                tableBody += '<tr><td>' + element.name + '</td><td>' + moment(element.lastUpdate).format("DD-MM-YYYY HH:mm") + '</td></tr>';
                this.availablePublications[element.name] = element.id;
            });
            this.publicationTable.append(tableBody);
        }
        let user = CookieUtil.getCookie('user');
        DocumentService.getUserPublications(user, handler);
    }
}
