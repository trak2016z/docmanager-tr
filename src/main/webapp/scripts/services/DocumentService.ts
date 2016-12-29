import { DocumentData } from '../model/DocumentData';


export class DocumentService {
    public static uploadFile(file, data: DocumentData) {
        let url = 'document/fileUpload';
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
        xhr.send(formData);
    }

    public static getUserPublications(user: string, handler: any) {
        $.ajax({
            url: 'document/myDocuments',
            context: document.body,
            type: 'POST',
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            async: true,
            data: JSON.stringify({user: user})
        }).done(handler);
    }

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
        xhr.send(formData);
    }

    public static getFile(id: number) {
        window.location.href = 'document/fileDownload/' + id;
    }

}