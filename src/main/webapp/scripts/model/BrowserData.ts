import { Page } from "./Page";
import { BrowserDataContainer } from "./BrowserDataContainer";

export interface BrowserData {
    page : Page,
    dataContainer : Array<BrowserDataContainer>
}