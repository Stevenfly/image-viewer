import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ToolbarView extends JPanel implements Observer {

    private ImageCollectionModel model;

    private JPanel leftControl;
    private JPanel middleControl;
    private JPanel rightControl;

    private BufferedImage gridImg;
    private BufferedImage listImg;
    private BufferedImage openImg;

    private ImageIcon iconGrid;
    private ImageIcon iconList;
    private ImageIcon iconOpen;

    private JButton gridToggle;
    private JButton listToggle;

    private JLabel title;
    private JLabel filterText;
    private JPanel imageFilter;

    private JButton loadButton;

    /**
     * Create a new View.
     */
    public ToolbarView(ImageCollectionModel model) {
        // Set up the window.
        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        // Hook up this observer so that it will be notified when the model
        // changes.
        this.model = model;
        model.addObserver(this);

        setVisible(true);

        // Panels
        leftControl = new JPanel();
        middleControl = new JPanel();
        rightControl = new JPanel();

        leftControl.setBackground(Color.WHITE);
        middleControl.setBackground(Color.WHITE);
        rightControl.setBackground(Color.WHITE);

        // Left Controls

        try {
            gridImg = ImageIO.read(new File("src/main/icons/if_grid.png"));
            listImg = ImageIO.read(new File("src/main/icons/if_list.png"));
            openImg = ImageIO.read(new File("src/main/icons/if_open.png"));
        } catch (IOException e) {
            System.out.println("gridIcon or listIcon failed!");
        }

        iconGrid = new ImageIcon(new ImageIcon(gridImg).getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
        iconList = new ImageIcon(new ImageIcon(listImg).getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));
        iconOpen = new ImageIcon(new ImageIcon(openImg).getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT));

        gridToggle = new JButton();
        gridToggle.setIcon(iconGrid);
        listToggle = new JButton();
        listToggle.setIcon(iconList);

        gridToggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setShowGridView(true);
            }
        });

        listToggle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setShowGridView(false);
            }
        });

        leftControl.add(gridToggle);
        leftControl.add(listToggle);

        // Middle Controls

        title = new JLabel("Fotag!");

        middleControl.add(title);

        // Right Controls

        loadButton = new JButton();
        loadButton.setIcon(iconOpen);

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                jfc.setDialogTitle("Choose image file(s) to open:");
                jfc.setMultiSelectionEnabled(true);
                jfc.setAcceptAllFileFilterUsed(false);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and JPG images", "png", "jpg", "jpeg");
                jfc.addChoosableFileFilter(filter);

                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File[] files = jfc.getSelectedFiles();
                    System.out.println("Files Found\n");
                    Arrays.asList(files).forEach(x -> {
                        if (x.isFile()) {
                            model.addImage(x.getAbsolutePath());
                            System.out.println(x.getAbsolutePath());
                        }
                    });
                }

            }
        });

        filterText = new JLabel("Filter:");
        imageFilter = new StarFilter(model);

        rightControl.add(loadButton);
        rightControl.add(filterText);
        rightControl.add(imageFilter);

        this.add(leftControl, BorderLayout.LINE_START);
        this.add(middleControl, BorderLayout.CENTER);
        this.add(rightControl, BorderLayout.LINE_END);
    }

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        System.out.println("ToolbarView changed!");
    }
}
