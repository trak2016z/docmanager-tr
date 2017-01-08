import { BrowserService } from '../services/BrowserService';
import { BrowserData } from '../model/BrowserData';
import { BrowserDataContainer } from '../model/BrowserDataContainer';
import { Page } from '../model/Page';
import { SimpleSearchRequest } from '../model/SimpleSearchRequest';
import { DocumentService } from '../services/DocumentService';
import { RegexUtil } from '../utils/RegexUtil';
import * as moment from 'moment';

export class SearchView {

    private searchInput: JQuery;
    private publicationsTable: JQuery;
    private pageFirstInput: JQuery;
    private pageLastInput: JQuery;
    private pageNextInput: JQuery;
    private pagePreviousInput: JQuery;
    private pageCurrentOutput: JQuery;
    private pageSizeInput: JQuery;
    private timeout: number;
    private TIMEOUT_VALUE = 1000;
    private totalPages: number;
    private currentPage: number = 1;

    constructor() {
        this.searchInput = $('.search-input');
        this.publicationsTable = $('.publications-table tbody');
        this.pageSizeInput = $('.page-size');
        this.pageCurrentOutput = $('.criteria .current-page');
        this.initNavigationButtons();
        this.initiateSearchInputActions(this.searchInput);
        this.performSearch();
    }

    private initNavigationButtons() {
        this.pageFirstInput = $('.criteria .first-page');
        this.pagePreviousInput = $('.criteria .previous-page');
        this.pageNextInput = $('.criteria .next-page');
        this.pageLastInput = $('.criteria .last-page');

        this.pageFirstInput.click(() => {
            this.currentPage = 1;
            this.performSearch();
            this.pageCurrentOutput.text(this.currentPage)
        });
        this.pagePreviousInput.click(() => {
            if (this.currentPage > 1) {
                this.currentPage--;
                this.performSearch();
                this.pageCurrentOutput.text(this.currentPage)
            }
        });
        this.pageLastInput.click(() => {
            if (this.totalPages) {
                this.currentPage = this.totalPages;
                this.performSearch();
                this.pageCurrentOutput.text(this.currentPage)
            }
        });
        this.pageNextInput.click(() => {
            if (this.currentPage < this.totalPages) {
                this.currentPage++;
                this.performSearch();
                this.pageCurrentOutput.text(this.currentPage);
            }
        });

    }

    private buildTable(data: Array<BrowserDataContainer>) {
        let template = '';


        data.forEach(element => {
            template += '<tr val="' + element.id + '"><td>' + element.name + '</td><td>' + element.firstName + ' ' + element.lastName +
                '</td><td>' + moment(element.modified).format("DD-MM-YYYY HH:mm") + '</td><td>' + (element.rate ? element.rate : "(brak ocen)") + '</td></tr>';
        });
        this.publicationsTable.html(template);

        this.publicationsTable.find('tr').click(function () {
            let val: number = Number.parseInt($(this).attr("val"));
            DocumentService.openPublication(val);
        });
    }

    private providePage(): Page {
        let pageSize: number = this.pageSizeInput.val();
        let pageNumber: number = this.currentPage;
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
            this.currentPage = 1;
            this.pageCurrentOutput.val(1);
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
            let page = convertedDataResponse.page;
            this.totalPages = Math.ceil(page.total / parseInt(this.pageSizeInput.val()));
        })
    }

}