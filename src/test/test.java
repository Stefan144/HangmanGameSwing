public class test {

        char a = 'a';
        LetterFigure letter = new LetterFigure(a, "images/", ".png");

        if ((letter.getWidth() == 0) && (letter.getHeight() == 0)) {
            System.out.println("size is ok");
        } else {
            System.out.println("size is not ok");
        }

        if (letter.getText() == "") {
            System.out.println("text is ok");
        } else {
            System.out.println("text is not ok");
        }
    }
