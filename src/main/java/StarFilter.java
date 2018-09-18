import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class StarFilter extends JPanel implements Observer {

    private ImageCollectionModel model;

    private BufferedImage starOn;
    private BufferedImage starOff;

    ImageIcon iconStarOn;
    ImageIcon iconStarOff;

    private JButton label1;
    private JButton label2;
    private JButton label3;
    private JButton label4;
    private JButton label5;

    private JButton clearButton;

    /**
     * Create a new View.
     */
    public StarFilter(ImageCollectionModel model) {
        this.setBackground(Color.WHITE);

        this.model = model;
        model.addObserver(this);

        setVisible(true);

        try {
            starOn = ImageIO.read(new File("src/main/icons/if_rated.png"));
            starOff = ImageIO.read(new File("src/main/icons/if_unrated.png"));
        } catch (IOException e) {
            System.out.println("starOn or starOff failed!");
        }

        iconStarOn = new ImageIcon(starOn);
        iconStarOff = new ImageIcon(starOff);

        label1 = new JButton();
        label1.setBorder(BorderFactory.createEmptyBorder());
        label1.setContentAreaFilled(false);
        label2 = new JButton();
        label2.setBorder(BorderFactory.createEmptyBorder());
        label2.setContentAreaFilled(false);
        label3 = new JButton();
        label3.setBorder(BorderFactory.createEmptyBorder());
        label3.setContentAreaFilled(false);
        label4 = new JButton();
        label4.setBorder(BorderFactory.createEmptyBorder());
        label4.setContentAreaFilled(false);
        label5 = new JButton();
        label5.setBorder(BorderFactory.createEmptyBorder());
        label5.setContentAreaFilled(false);

        setIcons(model.getFilter());

        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcons(1);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setIcons(model.getFilter());
            }
        });

        label1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setFilter(1);
            }
        });

        label2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcons(2);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setIcons(model.getFilter());
            }
        });

        label2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setFilter(2);
            }
        });

        label3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcons(3);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setIcons(model.getFilter());
            }
        });

        label3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setFilter(3);
            }
        });

        label4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcons(4);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setIcons(model.getFilter());
            }
        });

        label4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setFilter(4);
            }
        });

        label5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                setIcons(5);
            }
            @Override
            public void mouseExited(MouseEvent arg0) {
                setIcons(model.getFilter());
            }
        });

        label5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setFilter(5);
            }
        });

        clearButton = new JButton("Clear");

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setFilter(0);
            }
        });

        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);

        this.add(clearButton);
    }

    private void setIcons(int rate) {
        label1.setIcon((rate >= 1) ? iconStarOn : iconStarOff);
        label2.setIcon((rate >= 2) ? iconStarOn : iconStarOff);
        label3.setIcon((rate >= 3) ? iconStarOn : iconStarOff);
        label4.setIcon((rate >= 4) ? iconStarOn : iconStarOff);
        label5.setIcon((rate >= 5) ? iconStarOn : iconStarOff);
    }

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {
        // XXX Fill this in with the logic for updating the view when the model
        // changes.
        setIcons(model.getFilter());
        System.out.println("StarRating changed!");
    }
}
