export class DocumentService {
    public static uploadFile(file) {
        var url = 'document/fileUpload';
        var xhr = new XMLHttpRequest();
        var formData = new FormData();
        xhr.open("POST", url, true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                // Every thing ok, file uploaded
                console.log(xhr.responseText); // handle response.
            }
        };
        formData.append("upload_file", file);
        xhr.send(formData);
    }
}