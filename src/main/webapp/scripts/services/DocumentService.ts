import { DocumentData } from '../model/DocumentData';
import { FileDescription } from '../model/FileDescription';

export class DocumentService {
    public static uploadFile(file, data: DocumentData, handler) {
        let url = 'document/fileUpload';
        let xhr = new XMLHttpRequest();
        xhr.responseType = 'json';        
        let formData = new FormData();
        xhr.open("POST", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                let fileData: FileDescription = xhr.response;
                data.fileDescription = fileData;
                DocumentService.updatePublicationdata(data, handler);
            }
        };
        formData.append("upload_file", file);
        xhr.send(formData);
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
        window.location.href = "document/" + id + '/edit';
    };

    public static uploadAvatar(file, data) {
        let url = 'document/avatar';
        let xhr = new XMLHttpRequest();
        let formData = new FormData();
        xhr.open("POST", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                console.log(xhr.responseText);
            }
        };
        formData.append("data", JSON.stringify(data));
        formData.append("upload_file", file);
        xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded; charset=UTF-8');
        xhr.send(formData);
    }

    public static getFile(id: number) {
        window.location.href = 'document/fileDownload/' + id;
    }

}