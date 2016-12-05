import * as jQuery from "jquery";
import { DocumentService } from '../services/DocumentService';

export class DocumentView {

    private getFiles: Function;
    private fileInput: HTMLElement;

    constructor() {
        this.fileInput = $('#fileinput')[0];
        var getFiles = function (element) {
            return element.files[0];
        }
        this.getFiles = getFiles;
        let button = $('#docupload-form .uploadButton');
        button.click(()=>{
            let file = this.getFiles(this.fileInput);
            DocumentService.uploadFile(file);
        });
    }



}

interface External {
    botify: Function;
}