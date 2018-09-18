import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageView extends JPanel implements Observer {

    private ImageModel model;

    private JPanel metadata;
    private BufferedImage img;
    JButton imgButton;
    private JLabel imageName;
    private JLabel imageDate;
    private JPanel imageRating;

    /**
     * Create a new View.
     */
    public ImageView(ImageModel model) {
        // Set up the window.
        this.setBackground(Color.WHITE);
//        this.setAlignmentX(Component.LEFT_ALIGNMENT);
//        this.setPreferredSize(new Dimension(300, 200));
//        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Hook up this observer so that it will be notified when the model
        // changes.
        this.model = model;
        model.addObserver(this);

        setVisible();

        // Load Image
        try {
            img = ImageIO.read(model.getFile());
        } catch (IOException e) {
            System.out.println("img failed!");
        }

        ImageIcon icon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT));
        imgButton = new JButton();
        imgButton.setBorder(BorderFactory.createEmptyBorder());
        imgButton.setContentAreaFilled(false);
        imgButton.setIcon(icon);

        // image click event
        imgButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame editorFrame = new JFrame("Image");
                editorFrame.setMaximumSize(new Dimension(800,600));
                editorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                ImageIcon imageIcon = new ImageIcon(img);
                JLabel jLabel = new JLabel();
                jLabel.setIcon(imageIcon);
                editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

                editorFrame.pack();
                editorFrame.setLocationRelativeTo(null);
                editorFrame.setVisible(true);
            }
        });

        this.add(imgButton);

        // Metadata Panel
        metadata = new JPanel();
        metadata.setBackground(Color.WHITE);
        metadata.setLayout(new BoxLayout(metadata, BoxLayout.PAGE_AXIS));

        imageName = new JLabel(model.getFile().getName());
        imageName.setBorder(new EmptyBorder(0,10,0,0));
        imageDate = new JLabel(model.getCreateDate());
        imageDate.setBorder(new EmptyBorder(0,10,0,0));
        imageRating = new StarRating(model);

        imageName.setAlignmentX(Component.LEFT_ALIGNMENT);
        imageDate.setAlignmentX(Component.LEFT_ALIGNMENT);
        imageRating.setAlignmentX(Component.LEFT_ALIGNMENT);

        metadata.add(imageName);
        metadata.add(imageDate);
        metadata.add(imageRating);

//        Insets insets = this.getInsets();
//        Dimension size = metadata.getPreferredSize();
//        metadata.setBounds(160 + insets.left, 10 + insets.top, size.width, size.height);

        this.add(metadata);
    }

    private void setLayout() {
        if (model.isGridMetadata()) {
            this.setPreferredSize(new Dimension(150,250));
            this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        } else {
            this.setPreferredSize(new Dimension(500,150));
            this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        }
    }

    private void setVisible() {
        if (model.getFilter() <= model.getRating()) {
            this.setVisible(true);
        } else {
            this.setVisible(false);
        }
    }

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        setLayout();
        setVisible();
        repaint();
        System.out.println("ImageView changed!");
    }
}
