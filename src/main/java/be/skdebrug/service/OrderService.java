package be.skdebrug.service;

/**
 * Developer: Ben Oeyen
 * Date: 10/03/2017
 */

public class OrderService {
    private static OrderService orderService;
    
    private void OrderService(){      
        // load orders;
    }
    
    public static OrderService getInstance(){
        if(orderService == null){
            orderService = new OrderService();
        }    
        return orderService;
    }
     
    
    
    
}
