import { DocumentData } from '../model/DocumentData'
import * as jQuery from "jquery";
import { DocumentService } from '../services/DocumentService';

export class DocumentView {

    private getFiles: Function;
    private fileInput: HTMLElement;
    private nameInput: JQuery;
    private keywordsInput: JQuery;
    private publicInput: JQuery;
    private noteInput: JQuery;

    constructor() {
        this.fileInput = $('#fileinput')[0];
        var getFiles = function (element) {
            return element.files[0];
        }
        this.getFiles = getFiles;

        this.nameInput = $('#docupload-form .name-input');
        this.noteInput = $('#docupload-form .note-input');
        this.keywordsInput = $('#docupload-form .keywords-input');
        this.publicInput = $('#docupload-form .public-input');

        let button = $('#docupload-form .uploadButton');
        button.click(() => {
            let file = this.getFiles(this.fileInput);
            let data: DocumentData = {
                isPublic: this.publicInput.is(':checked'),
                keywords: this.keywordsInput.val(),
                name: this.nameInput.val(),
                note: this.noteInput.val()
            };
            DocumentService.uploadFile(file, data);
        });
    }



}

interface External {
    botify: Function;
}