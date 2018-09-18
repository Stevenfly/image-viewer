
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class View extends JFrame implements Observer {

    private Model model;

    private static final int minWidth = 550, minHeight = 300;

    /**
     * Create a new View.
     */
    public View(Model model) {
        // Set up the window.
        this.setTitle("Fotag");
        this.setMinimumSize(new Dimension(minWidth, minHeight));
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Hook up this observer so that it will be notified when the model
        // changes.
        this.model = model;
        model.addObserver(this);

        setVisible(true);
    }

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        validate();
        System.out.println("View changed!");
    }
}
