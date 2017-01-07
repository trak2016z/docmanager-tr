export class RegexUtil {
    
    public static removeNonAlphanumericChars (source : string) : string {
        let regex : RegExp = new RegExp('[^A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ _]');
        return source.replace(regex, "");
    }
}