import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImageCollectionModel extends Model {
    /** The observers that are watching this model for changes. */
    private List<Observer> observers;

    private List<ImageModel> imageModels;

    private boolean showGridView;
    private int filter;

    /**
     * Create a new model.
     */
    public ImageCollectionModel() {
        this.observers = new ArrayList();
        this.imageModels = new ArrayList<>();

        this.showGridView = true;

        loadImages();

//        this.addImage("src/main/images/cat.jpg");
//        this.addImage("src/main/images/cat.jpg");
//        this.addImage("src/main/images/cat.jpg");
    }

    public void addImage(String path) {
        this.imageModels.add(new ImageModel(path, this));
        notifyObservers();
    }

    public void saveImages() {
        Properties prop = new Properties();
        OutputStream output = null;

        try {

            output = new FileOutputStream("config.images");

            // set the properties value
            prop.setProperty("totalNum", Integer.toString(this.imageModels.size()));
            String image = "image";
            int num = 0;
            for (ImageModel imageModel: this.imageModels) {
                prop.setProperty(image + num, imageModel.getFile().getAbsolutePath());
                num++;
            }

            // save properties to project root folder
            prop.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void loadImages() {
        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.images");

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            int num = Integer.parseInt(prop.getProperty("totalNum"));

            for (int i = 0; i < num; i++) {
                this.addImage(prop.getProperty("image" + i));
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Getters
     */
    public List<ImageModel> getImageModels() {
        return imageModels;
    }

    public boolean isShowGridView() {
        return showGridView;
    }

    public int getFilter() {
        return filter;
    }

    /**
     * Setters
     */

    public void setShowGridView(boolean showGridView) {
        this.showGridView = showGridView;
        notifyObservers();
    }

    public void setFilter(int filter) {
        this.filter = filter;
        notifyObservers();
    }


    /**
     * Add an observer to be notified when this model changes.
     */
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    /**
     * Remove an observer from this model.
     */
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    /**
     * Notify all observers that the model has changed.
     */
    public void notifyObservers() {
        for (Observer observer: this.observers) {
            observer.update(this);
        }
        for (ImageModel imageModel: this.imageModels) {
            imageModel.notifyObservers();
        }
    }
}
