
import cars.Body;
import cars.Motor;
import cars.Spoiler;
import cars.Tires;
import garage.Garage;
import garage.GarageProduct;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller extends JFrame {

    private final int SCREEN_WIDTH  = 1200;
    private final int SCREEN_HEIGHT = 600;

    private final Color CAR_STATS_BG_COLOR     = Color.WHITE;
    private final Color SHOP_AND_RACE_BG_COLOR = new Color(230, 230, 230);

    private Garage garage;
    private int currentCategory;
    private int currentProduct;

    public Controller() {

        setupGarage();

        setTitle("MCR - Racers");

        // Car frame with statistics
        JPanel carPanel = new JPanel();
        carPanel.setBackground(CAR_STATS_BG_COLOR);
        GridLayout carPanelGridLayout = new GridLayout(1,2);
        carPanelGridLayout.setVgap(25);
        carPanel.setLayout(carPanelGridLayout);
        JPanel carPanelImage = new JPanel(); //garage.getCars().get(0);
        carPanelImage.setOpaque(false);
        carPanel.add(carPanelImage);
        JPanel carStatsPanel = new JPanel();
        carStatsPanel.setOpaque(false);
        carStatsPanel.setLayout(new GridLayout(5, 2));
        // Set car statistics (later with car.getAcceleration() etc...)
        JLabel accelerationLabel = new JLabel("xxx m/s2");
        JLabel weightLabel = new JLabel("xxx kg");
        JLabel adherenceLabel = new JLabel("xxx ??");
        JLabel maniabilityLabel = new JLabel("xxx ??");
        JLabel resistanceLabel = new JLabel("xxx ??");
        carStatsPanel.add(new JLabel("acceleration :"));
        carStatsPanel.add(accelerationLabel);
        carStatsPanel.add(new JLabel("weight :"));
        carStatsPanel.add(weightLabel);
        carStatsPanel.add(new JLabel("adherence :"));
        carStatsPanel.add(adherenceLabel);
        carStatsPanel.add(new JLabel("maniability :"));
        carStatsPanel.add(maniabilityLabel);
        carStatsPanel.add(new JLabel("resistance :"));
        carStatsPanel.add(resistanceLabel);
        carPanel.add(carStatsPanel);

        // Builder and race UI
        JPanel builderAndRacePanel = new JPanel();
        builderAndRacePanel.setBackground(SHOP_AND_RACE_BG_COLOR);
        builderAndRacePanel.setLayout(new GridLayout(1, 2));
        // Builder panel (carPart images + selection interface)
        JPanel builderPanel = new JPanel();
        builderPanel.setOpaque(false);
        builderPanel.setLayout(new GridLayout(2, 1));
        JPanel carPartPanel = new JPanel();
        carPartPanel.setOpaque(false);
        JPanel selectionPanel = new JPanel();
        selectionPanel.setOpaque(false);
        selectionPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // Selection labels
        JLabel categoryLabel = new JLabel(garage.getInventory().get(currentCategory).getProductLabel());
        categoryLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel productLabel = new JLabel("Product");
        JButton categoryLeftButton = new JButton("<");
        categoryLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentCategory = (currentCategory - 1 + garage.getInventory().size()) % garage.getInventory().size();
                categoryLabel.setText(garage.getInventory().get(currentCategory).getProductLabel());
                productLabel.setText(garage.getInventory().get(currentCategory).getProducts().get(currentProduct).getCategory());
            }
        });
        JButton categoryRightButton = new JButton(">");
        categoryRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentCategory = (currentCategory + 1) % garage.getInventory().size();
                categoryLabel.setText(garage.getInventory().get(currentCategory).getProductLabel());
            }
        });
        productLabel.setHorizontalAlignment(SwingConstants.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.anchor=GridBagConstraints.CENTER;
        c.gridy = 0;
        c.gridx = 0;
        c.weightx = 0.2;
        selectionPanel.add(categoryLeftButton, c);
        c.gridx = 1;
        c.weightx = 0.6;
        selectionPanel.add(categoryLabel, c);
        c.gridx = 2;
        c.weightx = 0.2;
        selectionPanel.add(categoryRightButton, c);
        c.gridy = 1;
        c.gridx = 0;
        c.weightx = 0.2;
        selectionPanel.add(new JButton("<"), c);
        c.gridx = 1;
        c.weightx = 0.6;
        selectionPanel.add(productLabel, c);
        c.gridx = 2;
        c.weightx = 0.2;
        selectionPanel.add(new JButton(">"), c);
        c.gridy = 2;
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        selectionPanel.add(new JButton("Mount to car"), c);
        builderPanel.add(carPartPanel);
        builderPanel.add(selectionPanel);

        // Race info panel
        JPanel racePanel = new JPanel();
        racePanel.setOpaque(false);
        racePanel.setLayout(new GridLayout(3, 2));
        racePanel.add(new JLabel("Racers :"));
        racePanel.add(new JLabel("5"));
        racePanel.add(new JLabel("Total distance :"));
        racePanel.add(new JLabel("5km"));
        racePanel.add(new JButton("Quit"));
        racePanel.add(new JButton("Start"));

        builderAndRacePanel.add(builderPanel);
        builderAndRacePanel.add(racePanel);

        setLayout(new GridLayout(2, 1));
        add(carPanel);
        add(builderAndRacePanel);

        //Resizing frame
        setSize(SCREEN_WIDTH,SCREEN_HEIGHT);
        //position center of screen
        setLocationRelativeTo(null);
        //set visible
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setupGarage() {
        garage = new Garage();

        // Products available in the garage
        // - Car bodies
        GarageProduct bodies = new GarageProduct("Bodies");
        bodies.addProduct(new Body("Body-1", "resources/cars/bodies/body-1.png", 2, 2, 2, 2, 2));
        // - Car motors
        GarageProduct motors = new GarageProduct("Motors");
        motors.addProduct(new Motor("Motor-1", "resources/cars/motors/motor-1.png", 2, 2, 2, 2, 2));
        // - Car tires
        GarageProduct tires = new GarageProduct("Tires");
        tires.addProduct(new Tires("Tires-1", "resources/cars/tires/tires-1.png", 2, 2, 2, 2, 2));
        // - Car spoilers
        GarageProduct spoilers = new GarageProduct("Spoilers");
        spoilers.addProduct(new Spoiler("Spoiler-1", "resources/cars/spoilers/spoiler-1.png", 2, 2, 2, 2, 2));

        // Adding to the garage inventory
        garage.addToInventory(bodies);
        garage.addToInventory(motors);
        garage.addToInventory(tires);
        garage.addToInventory(spoilers);

    }

}