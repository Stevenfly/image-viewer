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

public class StarRating extends JPanel implements Observer {

    private ImageModel model;

    private BufferedImage starOn;
    private BufferedImage starOff;

    ImageIcon iconStarOn;
    ImageIcon iconStarOff;

    private JButton label1;
    private JButton label2;
    private JButton label3;
    private JButton label4;
    private JButton label5;

    private JPanel starPanel;
    private JButton clearButton;

    /**
     * Create a new View.
     */
    public StarRating(ImageModel model) {
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
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

        setIcons(model.getRating());

        label1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcons(1);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setIcons(model.getRating());
            }
        });

        label1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRating(1);
            }
        });

        label2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcons(2);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setIcons(model.getRating());
            }
        });

        label2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRating(2);
            }
        });

        label3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcons(3);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setIcons(model.getRating());
            }
        });

        label3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRating(3);
            }
        });

        label4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setIcons(4);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setIcons(model.getRating());
            }
        });

        label4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRating(4);
            }
        });

        label5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent arg0) {
                setIcons(5);
            }
            @Override
            public void mouseExited(MouseEvent arg0) {
                setIcons(model.getRating());
            }
        });

        label5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRating(5);
            }
        });

        clearButton = new JButton("Clear");

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRating(0);
            }
        });

        starPanel = new JPanel();
        starPanel.setBackground(Color.WHITE);

        starPanel.add(label1);
        starPanel.add(label2);
        starPanel.add(label3);
        starPanel.add(label4);
        starPanel.add(label5);

        this.add(starPanel);
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
        setIcons(model.getRating());
        System.out.println("StarRating changed!");
    }
}
