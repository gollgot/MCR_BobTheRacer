package garage;

import cars.parts.PaintJob;

import java.util.ArrayList;

/**
 * MCR PROJECT : Builder Design Pattern
 * Author      : Bottin Stéphane, Demarta Robin, Dessaules Loïc, Kot Chau-Ying
 *
 * Description : Garage containing all material to build a car
 */
public class Garage {

    // Inventory indexes
    public static final int CATEGORY_BODY     = 0;
    public static final int CATEGORY_MOTORS   = 1;
    public static final int CATEGORY_TIRES    = 2;
    public static final int CATEGORY_SPOILERS = 3;

    private ArrayList<GarageProduct> inventory;
    private ArrayList<PaintJob> paintJobs;

    public Garage() {
        inventory = new ArrayList<>();
        paintJobs = new ArrayList<>();
    }

    /**
     * Add a product to the garage inventory
     * @param product : product to add
     */
    public void addToInventory(GarageProduct product) {
        inventory.add(product);
    }

    /**
     * Get the garage inventory
     * @return garage inventory
     */
    public ArrayList<GarageProduct> getInventory() {
        return inventory;
    }

    /**
     * Add a paint job
     * @param pj : paint job
     */
    public void addPaintJob(PaintJob pj) {
        this.paintJobs.add(pj);
    }

    /**
     * Get the garage paint jobs
     * @return garage paint jobs
     */
    public ArrayList<PaintJob> getPaintJobs() {
        return paintJobs;
    }
}
