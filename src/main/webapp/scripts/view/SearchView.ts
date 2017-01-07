import { BrowserService } from '../services/BrowserService';
import { BrowserData } from '../model/BrowserData';
import { BrowserDataContainer } from '../model/BrowserDataContainer';
import { Page } from '../model/Page';
import { SimpleSearchRequest } from '../model/SimpleSearchRequest';
import { DocumentService } from '../services/DocumentService';
import { RegexUtil } from '../utils/RegexUtil';

export class SearchView {

    private searchInput: JQuery;
    private publicationsTable: JQuery;
    private pageNumberInput: JQuery;
    private pageSizeInput: JQuery;
    private timeout: number;
    private TIMEOUT_VALUE = 1000;

    constructor() {
        this.searchInput = $('.search-input');
        this.publicationsTable = $('.publications-table tbody');
        this.pageNumberInput = $('.page-number');
        this.pageSizeInput = $('.page-size');
        this.initiateSearchInputActions(this.searchInput);
        this.performSearch();
    }

    private buildTable(data: Array<BrowserDataContainer>) {
        let template = '';
        data.forEach(element => {
            template += '<tr val="' + element.id + '"><td>' + element.name + '</td><td>' + element.firstName + ' ' + element.lastName +
                '</td><td>' + element.modified + '</td><td>' + element.rate + '</td></tr>';
        });
        this.publicationsTable.html(template);

        this.publicationsTable.find('tr').click(function () {
            let val: number = Number.parseInt($(this).attr("val"));
            DocumentService.openPublication(val);
        });
    }

    private providePage(): Page {
        let pageSize: number = this.pageSizeInput.val();
        let pageNumber: number = this.pageNumberInput.val();
        let page: Page = {
            number: pageNumber,
            size: pageSize
        }
        return page;
    }

    private provideSimpleCriteria() {
        let unprocessedCriteria: string = this.searchInput.val();
        return RegexUtil.removeNonAlphanumericChars(unprocessedCriteria);
    }


    private initiateSearchInputActions(searchInput: JQuery) {
        searchInput.keyup(() => {
            clearTimeout(this.timeout);
            this.timeout = setTimeout(() => {
                this.performSearch();
            }, this.TIMEOUT_VALUE);
        });
    }

    private performSearch() {
        let requestData: SimpleSearchRequest = {
            page: this.providePage(),
            criteria: this.provideSimpleCriteria()
        };
        BrowserService.simpleSearchRecords(requestData).done((response) => {
            let convertedDataResponse: BrowserData = JSON.parse(response);
            this.buildTable(convertedDataResponse.dataContainer);
            //TODO: PAGING
        })
    }

}