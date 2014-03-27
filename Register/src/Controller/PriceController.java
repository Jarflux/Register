/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Drink;
import java.util.ArrayList;

/**
 * @author: Ben Oeyen
 * @date: 21-mrt-2013
 */
public class PriceController {

    private static PriceController priceController;
    private ArrayList<Drink> beverages;

    public static PriceController getInstance() {
        if (priceController == null) {
            priceController = new PriceController();
        }
        return priceController;
    }

    private PriceController() {
        this.beverages = new ArrayList<>();
        this.beverages.add(new Drink("Pils", 1.8, "jupiler"));
        this.beverages.add(new Drink("Palm", 1.8, "palm"));
        this.beverages.add(new Drink("Grimbergen", 2.4, "grimbergen"));
        this.beverages.add(new Drink("Duvel", 2.4, "duvel"));
        this.beverages.add(new Drink("Hoegaarden", 1.8, "hoegaarden"));
        this.beverages.add(new Drink("Kriek", 1.8, "kriek"));
        this.beverages.add(new Drink("Tourtel Blond", 1.8, "tourtel"));
        this.beverages.add(new Drink("Cola", 1.8, "cola"));
        this.beverages.add(new Drink("Cola Light", 1.8, "colalight"));
        this.beverages.add(new Drink("Fanta", 1.8, "fanta"));
        this.beverages.add(new Drink("Fruitsap", 1.8, "fruitsap"));
        this.beverages.add(new Drink("Bitter Lemon", 1.8, "bitterlemon"));
        this.beverages.add(new Drink("Tonic", 1.8, "tonic"));
        this.beverages.add(new Drink("Ice Tea", 1.8, "icetea"));
        this.beverages.add(new Drink("Water Plat", 1.8, "waterplat"));
        this.beverages.add(new Drink("Fles Wit", 9.0, "wijnwitfles"));
        this.beverages.add(new Drink("Fles Rood", 9.0, "wijnroodfles"));
        this.beverages.add(new Drink("Glas Wit", 2.4, "wijnwitglas"));
        this.beverages.add(new Drink("Glas Rood", 2.4, "wijnroodglas"));
        this.beverages.add(new Drink("Water Bruis", 1.8, "waterbruis"));
        this.beverages.add(new Drink("Koffie", 1.8, "koffie"));
        this.beverages.add(new Drink("Koffie Decaf", 1.8, "koffiedecaf"));
        this.beverages.add(new Drink("Thee", 1.8, "thee"));
    }

    public int getSize() {
        return beverages.size();
    }

    public Drink getDrinkByIndex(int index) {
        return beverages.get(index);
    }

    public Drink getDrinkByName(String name) {
        for (Drink b : beverages) {
            if (name.equals(b.getName())) {
                return b;
            }
        }
        return null;
    }

    public String toString() {
        String summary = "QUIZ SK De Brug Prijslijst \n---------------------------\n";
        for (Drink b : beverages) {
            summary = summary + b.toString() + "\n";
        }
        return summary;
    }
}
