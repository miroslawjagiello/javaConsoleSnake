package io.jagiello;

public class Screen {
    public static int SCREEN_WIDTH = 60;
    public static int SCREEN_HEIGHT = 20;

    public static String TITLE_AND_SCORE = "jagiello.io - SNAKE    || SCORE = ";

    private StringBuffer screen;

    public Screen() {
        this.screen = new StringBuffer();
        for (int i = 0; i < SCREEN_WIDTH ; i++) {
            screen.append('=');
        }
        for (int i = 0; i < SCREEN_WIDTH ; i++) {
            screen.append(' ');
        }

        int logoIndex = SCREEN_WIDTH + 3;
        for (char c : TITLE_AND_SCORE.toCharArray()) {
            screen.setCharAt(logoIndex++, c);
        }

        for (int i = 0; i < SCREEN_WIDTH ; i++) {
            screen.append('=');
        }
        for (int i = 3; i < SCREEN_HEIGHT ; i++) {
            for (int j = 0; j < SCREEN_WIDTH; j++) {
                screen.append(' ');
            }
        }
        screen.append('\n');
    }

    public void cleanScreen() {
        for (int i = 3 * SCREEN_WIDTH; i < SCREEN_HEIGHT * SCREEN_WIDTH; i++) {
            screen.setCharAt(i,' ');
        }
    }

    public void setPixel(int x, int y, char _char){
        screen.setCharAt(x + y * SCREEN_WIDTH, _char);
    }

    public void prepareScreen(){
        for (int i = 1; i < SCREEN_HEIGHT + 1; i++) {
            screen.setCharAt(i * SCREEN_WIDTH - 1, '\n');
        }
    }

    public void printScore(int score) {
        int scorePosition = SCREEN_WIDTH + TITLE_AND_SCORE.length() + 3;
        for(char c : Integer.toString(score).toCharArray()) {
            screen.setCharAt(scorePosition++, c);
        }
    }

    public StringBuffer getScreen() {
        return screen;
    }
}
