import { DocumentData } from '../model/DocumentData';

export class DocumentService {
    public static uploadFile(file, data : DocumentData) {
        var url = 'document/fileUpload';
        var xhr = new XMLHttpRequest();
        var formData = new FormData();
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

    public static uploadAvatar(file, data){
        let url = 'document/avatar';
        var xhr = new XMLHttpRequest();
        var formData = new FormData();
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

    public static getFile(id : number){
        window.location.href = 'document/fileDownload/'+id;
    }

}