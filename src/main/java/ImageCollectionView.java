import javax.swing.*;
import java.awt.*;

public class ImageCollectionView extends JPanel implements Observer {

    private ImageCollectionModel model;

    /**
     * Create a new View.
     */
    public ImageCollectionView(ImageCollectionModel model) {
        // Set up the window.
        this.setMinimumSize(new Dimension(128, 128));
        this.setSize(800, 600);
        this.setBackground(Color.WHITE);

        // Hook up this observer so that it will be notified when the model
        // changes.
        this.model = model;
        model.addObserver(this);

        setVisible(true);

        // Scroll

//        JScrollPane scrollBar = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
//                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//        this.add(scrollBar);

        // Add images
        displayImages();
    }

    public void displayImages() {
        this.removeAll();
        for (ImageModel imageModel: model.getImageModels()) {
            ImageView imageView = new ImageView(imageModel);
            this.add(imageView);
            System.out.println("imageModel added!");
        }
        revalidate();
        repaint();
    }

    private void setLayout() {
        if (model.isShowGridView()) {
            this.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        } else {
            this.setLayout(new FlowLayout(FlowLayout.CENTER, 5000, 20));
            this.setPreferredSize(new Dimension(500, getHeight()));
        }
    }

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        displayImages();
        setLayout();
        System.out.println("ImageCollectionView changed!");
    }
}
