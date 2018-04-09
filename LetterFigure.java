import javax.swing.JLabel;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.Dimension;
import java.awt.Graphics;



public class LetterFigure extends JLabel
{

    private char IMG_LETTER;
    private int PREFERRED_WIDTH, PREFERRED_HEIGHT;
    private String IMG_CATALOG, IMG_TYPE, path;
    private BufferedImage img;
    private MouseListener figureListener;

    public LetterFigure(char imageLetter, String imageDirectory, String imageType)
    {
        IMG_CATALOG = imageDirectory;
        IMG_TYPE = imageType;
        IMG_LETTER = imageLetter;

        PREFERRED_WIDTH = PREFERRED_HEIGHT = 50;

        setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
        path = IMG_CATALOG + IMG_LETTER + IMG_TYPE;
        img = loadImage(path);
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


    public char guess()
    {
        loadNewImage("guessed");
        removeFigureListener();
        return IMG_LETTER;
    }


    private void loadNewImage(String suffix)
    {
        path = IMG_CATALOG + IMG_LETTER + "_" + suffix + IMG_TYPE;
        img = loadImage(path);
        repaint();
    }


    public void addFigureListener(MouseListener l)
    {
        figureListener = l;
        addMouseListener(figureListener);
    }


    public void removeFigureListener() { removeMouseListener(figureListener); }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(img,
                0,
                0,
                PREFERRED_WIDTH,
                PREFERRED_HEIGHT,
                null);
    }
}