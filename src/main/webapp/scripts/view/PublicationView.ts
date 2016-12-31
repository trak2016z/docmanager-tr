/// <reference path="../../typings/index.d.ts" />
import { PublicationIndicator } from '../model/PublicationIndicator';
import { DocumentService } from '../services/DocumentService';
import { CookieUtil } from '../utils/CookieUtil';
import * as $ from 'jquery';
import * as moment from 'moment';
import * as Collections from 'typescript-collections';


export class PublicationView {

    private publicationTable: JQuery;
    private availablePublications: Collections.Dictionary<string, number>;

    constructor() {
        this.init();
    }

    private init(): void {
        this.publicationTable = $('#publications tbody');
        this.resolveUserPublications();
    }

    private resolveUserPublications() {

        let createDeleteButton = (id: number) => {
            return '<button class="btn btn-danger delete" value="' + id + '">Usu�</button>';
        };

        let createOpenButton = (id: number) => {
            return '<button class="btn btn-default open" value="' + id + '">Otworz</button>';
        };

        let createEditButton = (id: number) => {
            return '<button class="btn btn-default edit" value="' + id + '">Edytuj</button>';
        };

        let handler = (response: string) => {
            let publications: Array<PublicationIndicator> = JSON.parse(response);
            let tableBody: string = "";
            this.availablePublications = new Collections.Dictionary<string, number>();
            publications.forEach(element => {
                tableBody += '<tr><td>' + element.name + '</td><td>' + moment(element.lastUpdate).format("DD-MM-YYYY HH:mm") + '</td><td>' + createOpenButton(element.id) +
                    '</td><td>' + createEditButton(element.id) + '</td><td>' + createDeleteButton(element.id) + '</td></tr>';
                this.availablePublications[element.name] = element.id;
            });
            this.publicationTable.append(tableBody);

            this.publicationTable.find('.delete').click(function () {
                let val: number = Number.parseInt($(this).attr("value"));
                DocumentService.deletePublication(val, () => { this.resolveUserPublications(); });
            });
            this.publicationTable.find('.open').click(function () {
                let val: number = Number.parseInt($(this).attr("value"));
                DocumentService.openPublication(val);
            });
            this.publicationTable.find('.edit').click(function () {
                let val: number = Number.parseInt($(this).attr("value"));
                DocumentService.editPublication(val);
            });
        }
        let user = CookieUtil.getCookie('user');
        DocumentService.getUserPublications(user, handler);
    }
}
