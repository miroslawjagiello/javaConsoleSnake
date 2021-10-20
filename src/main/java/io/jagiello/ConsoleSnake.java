package io.jagiello;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ConsoleSnake {
    public static void main(String[] args) throws IOException, InterruptedException {

        Snake snake = new Snake();
        Food food = new Food(15, 5);
        int score = 0;
        Screen screen = new Screen();
        InputController controller = new InputController();

        final int[] keyPressed = {0};
        ConsoleUtils.runKeyListener(keyPressed);

        while (true) {
            screen.cleanScreen();

            screen.setPixel(food.getX(), food.getY(), '%');

            controller.moveSnake(keyPressed[0], snake);
            keyPressed[0] = 0;

            snake.collisionDetectSnakeVsWorld();

            snake.move();

            snake.collisionDetectSnakeVSnake();

            if (snake.collisionDetectSnakeVsFood(food, screen)){
                score++;
            }

            screen.printScore(score);

            // Draw snake body
            for(SnakeSegment segment : snake.getSnakeSegments()){
                screen.setPixel(segment.getX(), segment.getY(), snake.isDead() ? '+' : 'O');
            }

            // Draw Snake Head
            screen.setPixel(snake.getHead().getX(), snake.getHead().getY(), snake.isDead() ? 'X' : '@');


            screen.prepareScreen();

            ConsoleUtils.drawScreen(screen);
            TimeUnit.MILLISECONDS.sleep(200);
            ConsoleUtils.clearScreen();
        }
    }
}
