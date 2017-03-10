package be.skdebrug.model;

import java.util.ArrayList;
import java.util.List;
import org.jdom2.Element;

/**
 * Developer: Ben Oeyen
 * Date: 10/03/2017
 */

public class Order {

    private List<Beverage> fBeverages;

    public Order() {
        this.fBeverages = new ArrayList<Beverage>();
    }

    public void addBeverage(Beverage drink) {
        fBeverages.add(drink);
    }

    public void removeBeverage(Beverage drink) {
        fBeverages.remove(drink);
    }

    public List<Beverage> getAllBeverages() {
        return this.fBeverages;
    }

    public int getTotalAmountBeverages() {
        return fBeverages.size();
    }

    public Double getTotalPrice() {
        double total = 0.0;
        for (Beverage d : fBeverages) {
            total += d.getPrice();
        }
        return total;
    }

    void clean() {
        fBeverages.clear();
    }

    public String toString() {
        String string = "=======================" + "/n";
        for (Beverage drink : fBeverages) {
            string += drink.toString() + "/n";
        }
        string += "---------------- + " + "/n";
        string += "" + getTotalPrice() + "/n";
        return string;
    }

    public Element save() {
        Element orderElement = new Element("Order");
        for (Beverage d : fBeverages) {
            orderElement.addContent(d.save());
        }
        return orderElement;
    }

    public static Order load(Element orderElement) {
        Order order = new Order();

        @SuppressWarnings("unchecked")
        List<Element> allChildren = (List<Element>) orderElement.getChildren();

        for (int counter = 0; counter < allChildren.size(); counter++) {
            Beverage tempBeverage = Beverage.load(allChildren.get(counter));
            order.addBeverage(tempBeverage);
        }
        return order;
    }
}
