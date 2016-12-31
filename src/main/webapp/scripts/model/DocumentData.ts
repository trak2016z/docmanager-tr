import { FileDescription } from './FileDescription';
export interface DocumentData {
    name: string,
    note: string,
    keywords: string,
    isPublic: boolean,
    fileDescription?: FileDescription
}