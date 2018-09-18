import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View(model);

        ImageCollectionModel imageCollectionModel = new ImageCollectionModel();
        ImageCollectionView imageCollectionView = new ImageCollectionView(imageCollectionModel);

        ToolbarView toolbarView = new ToolbarView(imageCollectionModel);

        view.add(toolbarView, BorderLayout.NORTH);
        view.add(imageCollectionView, BorderLayout.CENTER);

//        model.notifyObservers();
        imageCollectionModel.notifyObservers();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            public void run() {
                imageCollectionModel.saveImages();
            }
        }));
    }
}
