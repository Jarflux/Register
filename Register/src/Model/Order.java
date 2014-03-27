/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.List;
import org.jdom2.Element;

/**
 * @author: Ben Oeyen
 * @date: 9-feb-2013
 */
public class Order {

    private List<Drink> fDrinks;

    public Order() {
        this.fDrinks = new ArrayList<Drink>();
    }

    public void addDrink(Drink drink) {
        fDrinks.add(drink);
    }

    public void removeDrink(Drink drink) {
        fDrinks.remove(drink);
    }

    public List<Drink> getAllDrinks() {
        return this.fDrinks;
    }

    public int getTotalAmountDrinks() {
        return fDrinks.size();
    }

    public Double getTotalPrice() {
        double total = 0.0;
        for (Drink d : fDrinks) {
            total += d.getPrice();
        }
        return total;
    }

    void clean() {
        fDrinks.clear();
    }

    public String toString() {
        String string = "=======================" + "/n";
        for (Drink drink : fDrinks) {
            string += drink.toString() + "/n";
        }
        string += "---------------- + " + "/n";
        string += "" + getTotalPrice() + "/n";
        return string;
    }

    public Element save() {
        Element orderElement = new Element("Order");
        for (Drink d : fDrinks) {
            orderElement.addContent(d.save());
        }
        return orderElement;
    }

    public static Order load(Element orderElement) {
        Order order = new Order();

        @SuppressWarnings("unchecked")
        List<Element> allChildren = (List<Element>) orderElement.getChildren();

        for (int counter = 0; counter < allChildren.size(); counter++) {
            Drink tempDrink = Drink.load(allChildren.get(counter));
            order.addDrink(tempDrink);
        }
        return order;
    }
}
