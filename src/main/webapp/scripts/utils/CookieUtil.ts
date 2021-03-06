export class CookieUtil {
    public static getCookie(key: string): string {
        let cookieName = key + '=';
        let cookiesArray = document.cookie.split(';');
        let foundCookie = (cookiesArray as any).find((element: string) => {
            return element.indexOf(cookieName) >= 0;
        });
        if (foundCookie){
            let cookieElements = (foundCookie as string).split(cookieName);
            foundCookie = cookieElements[cookieElements.length-1];
        }
        return foundCookie;
    }

    public static deleteCookie(key : string){
        document.cookie = key + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    }
}