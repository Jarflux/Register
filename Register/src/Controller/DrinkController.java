/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

/**
 * @author: Ben Oeyen
 * @date: 9-feb-2013
 */
public class DrinkController {
    private static DrinkController drinkController;
    
    private void DrinkController(){      
        // load drinks;
    }
    
    public static DrinkController getInstance(){
        if(drinkController == null){
            drinkController = new DrinkController();
        }    
        return drinkController;
    }
}
