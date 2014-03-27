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
public class Team {

    private String fName;
    private int fNumber;
    private List<Order> fOrders;

    public Team(int number, String name) {
        this.fName = name;
        this.fNumber = number;
        this.fOrders = new ArrayList<Order>();
    }

    public Double getTotal() {
        Double total = 0.0;
        for (Order order : fOrders) {
            total += order.getTotalPrice();
        }
        return total;
    }

    public Order getOrder(int orderId) {
        return fOrders.get(orderId);
    }

    public List<Order> getAllOrders() {
        return fOrders;
    }

    public void addOrder(Order order) {
        fOrders.add(order);
    }

    public void removerOrder(Order order) {
        fOrders.remove(order);
    }

    public String getName() {
        return fName;
    }

    public void setName(String name) {
        this.fName = name;
    }

    public int getNumber() {
        return fNumber;
    }

    public int getTotalAmountDrinks() {
        int total = 0;
        for (Order o : fOrders) {
            total += o.getTotalAmountDrinks();
        }
        return total;
    }

    public Double getTotalPrice() {
        double total = 0.0;
        for (Order o : fOrders) {
            total += o.getTotalPrice();
        }
        return total;
    }

    public void setNumber(int number) {
        this.fNumber = number;
    }
    
    public void clean(){
        for(Order o: fOrders){
            o.clean();     
        }
        fOrders.clear();
    }

    @Override
    public String toString() {
        String string = "" + fName + ": " + fNumber + "/n";
        for (Order order : fOrders) {
            string += order.toString() + "/n";
        }
        return string;
    }

    public Element save() {
        Element teamElement = new Element("Team");
        teamElement.setAttribute("number", Integer.toString(fNumber));
        teamElement.setAttribute("name", fName);
        for (Order o : fOrders) {
            teamElement.addContent(o.save());
        }
        return teamElement;
    }

    public static Team Load(Element teamElement) {
        String name = teamElement.getAttributeValue("name");
        int number = Integer.parseInt(teamElement.getAttributeValue("number"));  
        Team team = new Team(number, name);

        @SuppressWarnings("unchecked")
        List<Element> allChildren = (List<Element>) teamElement.getChildren();

        for (int counter = 0; counter < allChildren.size(); counter++) {
            Order tempOrder = Order.load(allChildren.get(counter));
            team.addOrder(tempOrder);
        }
        return team;
    }
}
