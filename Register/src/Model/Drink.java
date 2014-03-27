/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.PriceController;
import org.jdom2.Element;

/**
 * @author: Ben Oeyen
 * @date: 9-feb-2013
 */
public class Drink {

    private String fName;
    private Double fPrice;
    private String fPicture;
    
    public Drink(String name, Double price, String picture) {
        this.fName = name;
        this.fPrice = price;
        this.fPicture = picture;
    }

    public String getName() {
        return fName;
    }

    public void setName(String name) {
        this.fName = name;
    }

    public Double getPrice() {
        return fPrice;
    }

    public void setPrice(Double price) {
        this.fPrice = price;
    }

    public String getPicture() {
        return fPicture;
    }

    public void setPicture(String picture) {
        this.fPicture = picture;
    }

    public Element save() {
        Element beverageElement = new Element("Drink");
        beverageElement.setAttribute("name", fName);
        return beverageElement;
    }

    public static Drink load(Element beverageElement) {
        Drink beverage = PriceController.getInstance().getDrinkByName(
                (beverageElement.getAttributeValue("name")));
        return beverage;
    }

    @Override
    public String toString() {
        return String.format("%1$-20s %2$5.2f �", fName, fPrice);
    }
}
