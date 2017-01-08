import { DocumentData } from '../model/DocumentData'
import { DocumentService } from '../services/DocumentService';
import { FileDescription } from '../model/FileDescription';
import { BaseView } from './BaseView';

export class DocumentView extends BaseView {

    private getFiles: Function;
    private fileInput: HTMLElement;
    private nameInput: JQuery;
    private keywordsInput: JQuery;
    private publicInput: JQuery;
    private noteInput: JQuery;
    private avatarInput: HTMLElement;
    private oldData: DocumentData;
    private oldDataService: JQueryXHR;
    private oldAvatarChanged = false;
    private oldFileChanged = false;
    private imageNode: JQuery;

    constructor(id?: number) {
        super();
        this.fileInput = $('#fileinput')[0];
        let imageInput = $('#avatarinput');
        this.avatarInput = imageInput[0];

        imageInput.change(() => {
            if (getFiles(this.avatarInput)) {
                let reader = new FileReader();
                reader.onload = (e) => {
                    $(this.imageNode).attr('src', (<any>e.target).result);
                }
                reader.readAsDataURL(getFiles(this.avatarInput));
            }
        });

        var getFiles = function (element) {
            if (element.files && element.files[0]) {
                return element.files[0];
            }
            else {
                return false;
            }
        }
        this.getFiles = getFiles;

        this.nameInput = $('#docupload-form .name-input');
        this.noteInput = $('#docupload-form .note-input');
        this.keywordsInput = $('#docupload-form .keywords-input');
        this.publicInput = $('#docupload-form .public-input');
        this.imageNode = $(".documentAvatar");

        let button = $('#docupload-form .uploadButton');
        button.click(() => {
            let file = this.getFiles(this.fileInput);
            let avatar = this.getFiles(this.avatarInput);
            let data: DocumentData = {
                isPublic: this.publicInput.is(':checked'),
                keywords: this.keywordsInput.val(),
                name: this.nameInput.val(),
                note: this.noteInput.val()
            };
            if (id) {
                data.id = id;
            }
            let fileRequest = file ? DocumentService.uploadFile(file) : null;
            let avatarRequest = avatar ? DocumentService.uploadFile(avatar) : null;
            $.when(fileRequest, avatarRequest).done((fileData, avatarData) => {
                if (fileData) {
                    data.fileDescription = fileData[0];
                }
                if (avatarData) {
                    data.avatarFile = avatarData[0].filename;
                }
                DocumentService.updatePublicationdata(data, () => { });
            });
        });
        if (id) {
            this.insertPreparedData(id);
        }
    }

    private insertPreparedData(id: number) {
        this.oldDataService = DocumentService.getDocumentData(id);
        $.when(this.oldDataService).done((response) => {
            this.oldData = JSON.parse(response);
            this.nameInput.val(this.oldData.name);
            this.noteInput.val(this.oldData.note);
            this.keywordsInput.val(this.oldData.keywords);
            this.publicInput.prop('checked', true);
            if (this.oldData.avatarFile) {
                this.imageNode.attr('src', 'document/avatar/' + id);
            }
        });
    }

}