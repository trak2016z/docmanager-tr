import { BrowserData } from "../model/BrowserData";
import { SimpleSearchRequest } from "../model/SimpleSearchRequest";

export class BrowserService {

    public static simpleSearchRecords(data : SimpleSearchRequest){
         return $.ajax({
            url: 'search/simple',
            type: 'POST',
            context: document.body,
            contentType: 'application/json; charset=utf-8',
            dataType: 'text',
            data: JSON.stringify(data),
            async: true 
        });
    }

}