import { DocumentData } from '../model/DocumentData';
import { FileDescription } from '../model/FileDescription';

export class DocumentService {
    public static uploadFile(file) {
        if (file) {
            let formData = new FormData();
            formData.append("upload_file", file);
            return $.ajax({
                url: 'document/fileUpload',
                method: 'POST',
                contentType: false,
                processData: false,
                data: formData,
                cache: false
            });
        }
    }

    public static updatePublicationdata(data: DocumentData, handler) {
        $.ajax({
            url: 'document/updateFileData',
            context: document.body,
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            async: true,
            data: JSON.stringify(data)
        }).done(handler);
    }

    public static getUserPublications(user: string, handler: any) {
        $.ajax({
            url: 'document/myDocuments',
            context: document.body,
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            async: true,
            data: JSON.stringify({ user: user })
        }).done(handler);
    }

    public static deletePublication(id: number, handler: any) {
        $.ajax({
            url: 'document/' + id,
            context: document.body,
            type: 'DELETE',
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            async: true
        }).done(handler);
    }

    public static openPublication(id: number) {
        window.location.href = "document/" + id;
    };

    public static editPublication(id: number) {
        window.location.href = "docedit"+id;
    };

    public static getFile(id: number) {
        window.location.href = 'document/fileDownload/' + id;
    };

    public static getAvatar(id: number){
        window.location.href = 'document/avatar/' + id;
    }

    public static getDocumentData(id: number) {
        return $.ajax({
            url: 'document/' + id,
            type: 'GET',
            context: document.body,
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            async: true 
        });
    }

}