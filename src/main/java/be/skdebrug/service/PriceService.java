package be.skdebrug.service;

import be.skdebrug.model.Beverage;
import java.util.ArrayList;

/**
 * Developer: Ben Oeyen
 * Date: 10/03/2017
 */

public class PriceService {

    private static PriceService priceService;
    private ArrayList<Beverage> beverages;

    public static PriceService getInstance() {
        if (priceService == null) {
            priceService = new PriceService();
        }
        return priceService;
    }

    private PriceService() {
        this.beverages = new ArrayList<>();
        this.beverages.add(new Beverage("Pils", 1.8, "jupiler"));
        this.beverages.add(new Beverage("Palm", 1.8, "palm"));
        this.beverages.add(new Beverage("Grimbergen", 2.4, "grimbergen"));
        this.beverages.add(new Beverage("Duvel", 2.4, "duvel"));
        this.beverages.add(new Beverage("Hoegaarden", 1.8, "hoegaarden"));
        this.beverages.add(new Beverage("Kriek", 1.8, "kriek"));
        this.beverages.add(new Beverage("Tourtel Blond", 1.8, "tourtel"));
        this.beverages.add(new Beverage("Cola", 1.8, "cola"));
        this.beverages.add(new Beverage("Cola Light", 1.8, "colalight"));
        this.beverages.add(new Beverage("Fanta", 1.8, "fanta"));
        this.beverages.add(new Beverage("Fruitsap", 1.8, "fruitsap"));
        this.beverages.add(new Beverage("Bitter Lemon", 1.8, "bitterlemon"));
        this.beverages.add(new Beverage("Tonic", 1.8, "tonic"));
        this.beverages.add(new Beverage("Ice Tea", 1.8, "icetea"));
        this.beverages.add(new Beverage("Water Plat", 1.8, "waterplat"));
        this.beverages.add(new Beverage("Fles Wit", 9.0, "wijnwitfles"));
        this.beverages.add(new Beverage("Fles Rood", 9.0, "wijnroodfles"));
        this.beverages.add(new Beverage("Glas Wit", 2.4, "wijnwitglas"));
        this.beverages.add(new Beverage("Glas Rood", 2.4, "wijnroodglas"));
        this.beverages.add(new Beverage("Water Bruis", 1.8, "waterbruis"));
        this.beverages.add(new Beverage("Koffie", 1.8, "koffie"));
        this.beverages.add(new Beverage("Koffie Decaf", 1.8, "koffiedecaf"));
        this.beverages.add(new Beverage("Thee", 1.8, "thee"));
    }

    public int getSize() {
        return beverages.size();
    }

    public Beverage getDrinkByIndex(int index) {
        return beverages.get(index);
    }

    public Beverage getDrinkByName(String name) {
        for (Beverage b : beverages) {
            if (name.equals(b.getName())) {
                return b;
            }
        }
        return null;
    }

    public String toString() {
        String summary = "QUIZ SK De Brug Prijslijst \n---------------------------\n";
        for (Beverage b : beverages) {
            summary = summary + b.toString() + "\n";
        }
        return summary;
    }
}
