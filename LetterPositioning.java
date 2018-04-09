import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;


public class LetterPositioning extends JPanel
{

    private String IMG_CATALOG, IMG_TYPE, wordToGuess;
    private int POSITIONING_COLS, POSITIONING_ROWS, CAPACITY;
    private ArrayList<LetterFigure> positioning;
    private GridLayout LETTER_POSITIONING_LAYOUT;


    public LetterPositioning(String inPassword, String imageDirectory,
                      String imageType)
    {
        POSITIONING_COLS = 8;
        POSITIONING_ROWS = 2;
        LETTER_POSITIONING_LAYOUT = new GridLayout(POSITIONING_ROWS, POSITIONING_COLS);
        LETTER_POSITIONING_LAYOUT.setVgap(10);
        CAPACITY = POSITIONING_ROWS * POSITIONING_COLS;

        IMG_TYPE = imageType;
        IMG_CATALOG = imageDirectory;

        wordToGuess = inPassword;
        positioning = new ArrayList<>();

        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setLayout(LETTER_POSITIONING_LAYOUT);
        loadPositioning();
    }


    private void loadPositioning()
    {
        buildPositioning();
        for (LetterFigure figure : positioning)
            add(figure);
    }


    private void buildPositioning()
    {
        StringBuilder passwordBuilder =
                new StringBuilder(wordToGuess.toLowerCase());
        Random rand = new Random();
        int i = 0, j = 0;

        ArrayList<Character> figures = new ArrayList<>();

        while (passwordBuilder.length() > 0)
        {

            if (!figures.contains(passwordBuilder.charAt(0)))
            {
                figures.add(passwordBuilder.charAt(0));
                i++;
            }
            passwordBuilder.deleteCharAt(0);
        }


        for (; i < CAPACITY; i++)
        {
            Character c = 'a';
            do
            {
                c = (char) (rand.nextInt(26) + 'a');
            } while (figures.contains(c));
            figures.add(c);
        }



        for (i = 0; i < CAPACITY; i++)
        {
            j = rand.nextInt(figures.size());
            positioning.add(new LetterFigure(figures.get(j),
                    IMG_CATALOG,
                    IMG_TYPE));
            figures.remove(j);
        }
    }


    public void attachListeners(MouseListener listener)
    {
        for (LetterFigure figure : positioning)
            figure.addFigureListener(listener);
    }


    public void removeListeners()
    {
        for (LetterFigure figure : positioning)
            figure.removeFigureListener();
    }
}