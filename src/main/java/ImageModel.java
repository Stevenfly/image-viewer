import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImageModel extends Model {
    /** The observers that are watching this model for changes. */
    private ImageCollectionModel model;
    private List<Observer> observers;

    private File file;
    private String createDate;
    private int rating;

    /**
     * Create a new model.
     */
    public ImageModel(String path, ImageCollectionModel model) {
        this.model = model;
        this.observers = new ArrayList();

        this.file = new File(path);
        this.rating = 0;

        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy/MM/dd");
        createDate = ft.format(date);
    }

    /**
     * Getters
     */
    public File getFile() {
        return file;
    }

    public String getCreateDate() {
        return createDate;
    }

    public int getRating() {
        return rating;
    }

    public boolean isGridMetadata() {
        return model.isShowGridView();
    }

    public int getFilter() {
        return model.getFilter();
    }

    /**
     * Setters
     */

    public void setRating(int rating) {
        this.rating = rating;
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
    }
}
