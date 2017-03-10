package be.skdebrug.model;

import be.skdebrug.service.PriceService;
import org.jdom2.Element;

/**
 * Developer: Ben Oeyen
 * Date: 10/03/2017
 */

public class Beverage {

    private String name;
    private Double price;
    private String picture;
    
    public Beverage(String name, Double price, String picture) {
        this.name = name;
        this.price = price;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Element save() {
        Element beverageElement = new Element("Beverage");
        beverageElement.setAttribute("name", name);
        return beverageElement;
    }

    public static Beverage load(Element beverageElement) {
        Beverage beverage = PriceService.getInstance().getDrinkByName(
                (beverageElement.getAttributeValue("name")));
        return beverage;
    }

    @Override
    public String toString() {
        return String.format("%1$-20s %2$5.2f �", name, price);
    }
}
