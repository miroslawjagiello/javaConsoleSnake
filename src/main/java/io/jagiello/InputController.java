package io.jagiello;

public class InputController {
    public static int KEY_LEFT = 68;
    public static int KEY_RIGHT = 67;

    private boolean keyLeft = false;
    private boolean keyRight = false;
    private boolean keyLeftOld = false;
    private boolean keyRightOld = false;
    public void moveSnake(int key, Snake snake){
        keyRight = key == KEY_RIGHT;
        keyLeft = key == KEY_LEFT;

        if (keyRight && !keyRightOld) {
            snake.turnRight();
        }

        if (keyLeft && !keyLeftOld) {
            snake.turnLeft();
        }
        keyRightOld = keyRight;
        keyLeftOld = keyLeft;
    }
}
