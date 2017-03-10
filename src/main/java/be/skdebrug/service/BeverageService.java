package be.skdebrug.service;

/**
 * Developer: Ben Oeyen
 * Date: 10/03/2017
 */

public class BeverageService {
    private static BeverageService beverageService;
    
    private void BeverageService(){
        // load drinks;
    }
    
    public static BeverageService getInstance(){
        if(beverageService == null){
            beverageService = new BeverageService();
        }    
        return beverageService;
    }
}
