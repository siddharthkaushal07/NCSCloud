/**
 * Define global constants for the application
 */
export class GlobalConstants {

    //Production Server
   // public static SERVER_URL = "http://nenosystems.com:9097/NCSDOCS";
    public static DOC_API = "http://nenosystems.com:9097/NCSDOCS/api/doc/";
    
    //Devlopment Server
    public static SERVER_URL = "http://localhost:9102/NCSCloud";
    //public static DOC_API = "http://localhost:9097/NCSDOCS/api/doc/";

    //Application context path
    public static CONTEXT_PATH: string = "NCSCloud";
             
    /**
     * Default organization ID and Role is used to signup a new user in the application 
     */
    public static DEFAULT_ORG_ID = 1608640805201;
    public static DEFAULT_ROLE = "Guest";

    public static getKey(str: string) {
        return GlobalConstants.CONTEXT_PATH + "-" + str;
    }
}