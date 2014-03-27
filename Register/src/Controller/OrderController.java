/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Model.Drink;
import Model.Order;
import java.util.List;
import org.jdom2.Element;

/**
 * @author: Ben Oeyen
 * @date: 9-feb-2013
 */
public class OrderController {
    private static OrderController orderController;
    
    private void OrderController(){      
        // load orders;
    }
    
    public static OrderController getInstance(){
        if(orderController == null){
            orderController = new OrderController();
        }    
        return orderController;
    }
     
    
    
    
}
