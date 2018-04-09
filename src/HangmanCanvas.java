import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

public class HangmanCanvas extends JFrame
{

    private String HANGMAN_IMG_CATALOG, HANGMAN_IMG_TYPE, HANGMAN_IMG_NAME_ROOT, LETTER_IMG_CATALOG,
            LETTER_IMG_TYPE, wordToGuess;
    private LetterPositioning gamePositioning;
    private Hangman HangmanIMG;
    private int BOARD_WIDTH, BOARD_HEIGHT, WRONG_GUESSES_BASELINE, SECRET_WORD_LENGTH_LIMIT, wrongCount;
    private JLabel wordDisplay, wrongCountDisplay;
    private StringBuilder wordToGuessHider;


    public HangmanCanvas()
    {
        BOARD_WIDTH = 500;
        BOARD_HEIGHT = 500;
        WRONG_GUESSES_BASELINE = 6;
        SECRET_WORD_LENGTH_LIMIT = 10;

        HANGMAN_IMG_CATALOG = LETTER_IMG_CATALOG = "images/";
        HANGMAN_IMG_TYPE = LETTER_IMG_TYPE = ".png";
        HANGMAN_IMG_NAME_ROOT = "hangman";

        setSize(BOARD_WIDTH, BOARD_HEIGHT);
        setResizable(false);
        setTitle("Hangman Game");
        addCloseWindowListener();

        launch();
    }


    private void launch()
    {
        wrongCount = 0;

        wordDisplay = new JLabel("Word to guess: ");
        wrongCountDisplay = new JLabel("Incorrect guesses counter: " + wrongCount);
        wordToGuess = new String();
        wordToGuessHider = new StringBuilder();

        getWordToGuess();
        textWindowAdder();
        letterPositioningAdder();
        appendHangman();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2,
                dim.height / 2 - getSize().height / 2 - 200);
        setVisible(true);
    }


    private void addCloseWindowListener()
    {

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                int prompt = JOptionPane.showConfirmDialog(null,
                        "Tired of playing and want to finish? Press yes!",
                        "Close game",
                        JOptionPane.YES_NO_OPTION);

                if (prompt == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
    }


    private void textWindowAdder()
    {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(1,2));
        textPanel.add(wordDisplay);
        textPanel.add(wrongCountDisplay);
        add(textPanel, BorderLayout.NORTH);
    }


    private void letterPositioningAdder()
    {
        gamePositioning = new LetterPositioning(wordToGuess,
                LETTER_IMG_CATALOG,
                LETTER_IMG_TYPE);
        gamePositioning.attachListeners(new FigureListener());
        add(gamePositioning, BorderLayout.SOUTH);
    }


    private void appendHangman()
    {
        JPanel hangmanFrame = new JPanel();
        HangmanIMG = new Hangman(HANGMAN_IMG_NAME_ROOT,
                HANGMAN_IMG_CATALOG,
                HANGMAN_IMG_TYPE);
        hangmanFrame.add(HangmanIMG);
        add(hangmanFrame, BorderLayout.CENTER);
    }


    private void getWordToGuess()
    {
        String[] options = {"Let's Play", "Quit"};
        JPanel wordToGuessPanel = new JPanel();
        JLabel wordToGuessLabel = new JLabel("Type the word to be guessed: ");
        JTextField wordToGuessText = new JTextField(SECRET_WORD_LENGTH_LIMIT);
        wordToGuessPanel.add(wordToGuessLabel);
        wordToGuessPanel.add(wordToGuessText);
        int checker = -1;

        while (wordToGuess.isEmpty())
        {
            checker = JOptionPane.showOptionDialog(null,
                    wordToGuessPanel,
                    "Enter Password",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            if (checker == 0)
            {
                wordToGuess = wordToGuessText.getText();

                if (wordToGuess.length() > SECRET_WORD_LENGTH_LIMIT ||
                        !wordToGuess.matches("[a-zA-Z]+"))
                {
                    JOptionPane.showMessageDialog(null,
                            "Word to guess should be less than 10 characters and " +
                                    "contain only english letters",
                            "Wrong secret word!",
                            JOptionPane.ERROR_MESSAGE);
                    wordToGuess = "";
                }
            }

            else if (checker == 1)
                System.exit(0);
        }

        wordToGuessHider.append(wordToGuess.replaceAll(".", "*"));
        wordDisplay.setText(wordDisplay.getText() + wordToGuessHider.toString());
    }


    private void playAgain()
    {
        int dialogResult = JOptionPane.showConfirmDialog(null,
                "Word you were guessing was: " + wordToGuess +
                        "\nTry again?",
                "Play again?",
                JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION)
            launch();
        else
            System.exit(0);
    }


    private class FigureListener implements MouseListener
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            Object source = e.getSource();
            if(source instanceof LetterFigure)
            {
                char c = ' ';
                int index = 0;
                boolean updated = false;


                LetterFigure figurePressed = (LetterFigure) source;
                c = figurePressed.guess();



                while ((index = wordToGuess.toLowerCase().indexOf(c, index)) != -1)
                {
                    wordToGuessHider.setCharAt(index, wordToGuess.charAt(index));
                    index++;
                    updated = true;
                }



                if (updated)
                {
                    wordDisplay.setText("Word: " + wordToGuessHider.toString());

                    if (wordToGuessHider.toString().equals(wordToGuess))
                    {
                        gamePositioning.removeListeners();
                        HangmanIMG.winImage();
                        playAgain();
                    }
                }


                else
                {
                    wrongCountDisplay.setText("Incorrect: " + ++wrongCount);

                    if (wrongCount >= WRONG_GUESSES_BASELINE)
                    {
                        HangmanIMG.loseImage();
                        gamePositioning.removeListeners();
                        playAgain();
                    }

                    else
                        HangmanIMG.switchIMG(Integer.toString(wrongCount));
                }
            }
        }


        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }
}