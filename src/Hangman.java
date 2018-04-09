import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class Hangman extends JLabel
{

    private String HANGMAN_IMG_NAME_ROOT, IMG_CATALOG, IMG_TYPE, path;
    private int PREFERRED_WIDTH, PREFERRED_HEIGHT;
    private BufferedImage image;


    public Hangman(String imgRootName, String imgCatalog,
                   String imgType)
    {
        PREFERRED_HEIGHT = 250;
        PREFERRED_WIDTH = 500;

        IMG_CATALOG = imgCatalog;
        IMG_TYPE = imgType;
        HANGMAN_IMG_NAME_ROOT = imgRootName;

        setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        path = IMG_CATALOG + HANGMAN_IMG_NAME_ROOT + "_0" + IMG_TYPE;
        image = loadImage(path);
    }


    private BufferedImage loadImage(String imagePath)
    {

        BufferedImage img = null;

        try
        {
            img = ImageIO.read(new File(imagePath));
        }

        catch (IOException ex)
        {
            System.err.println("loadImage(): Error: Image at "
                    + imagePath + " could not be found");
            System.exit(1);
        }

        return img;
    }



    public void switchIMG(String suffix)
    {
        path = IMG_CATALOG + HANGMAN_IMG_NAME_ROOT + "_" + suffix + IMG_TYPE;
        image = loadImage(path);
        repaint();
    }


    public void loseImage() {
        switchIMG("lost");
    }

    public void winImage() {
        switchIMG("won");
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image,
                0,
                0,
                PREFERRED_WIDTH,
                PREFERRED_HEIGHT,
                null);
    }
}