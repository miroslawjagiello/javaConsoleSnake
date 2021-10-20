package io.jagiello;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Snake {
    private LinkedList<SnakeSegment> snakeSegments;
    private int direction = 3;
    private boolean dead = false;

    public Snake() {
        this.snakeSegments = new LinkedList<>();
        for(int i=0; i < 5; i++) {
            this.snakeSegments.add(new SnakeSegment(30 + i, 10));
        }
    }

    public void move(){
        if(dead){
            return;
        }
        snakeSegments.removeLast();
        switch(direction){
            case 0: // UP
                snakeSegments.addFirst(new SnakeSegment(getHead().getX(), getHead().getY() - 1));
                break;
            case 1: // RIGHT
                snakeSegments.addFirst(new SnakeSegment(getHead().getX() + 1, getHead().getY()));
                break;
            case 2: // DOWN
                snakeSegments.addFirst(new SnakeSegment(getHead().getX(), getHead().getY() + 1));
                break;
            case 3: // LEFT
                snakeSegments.addFirst(new SnakeSegment(getHead().getX() - 1, getHead().getY()));
                break;
        }
    }

    public void turnRight(){
        direction++;
        if (direction == 4) direction = 0;
    }

    public void turnLeft(){
        direction--;
        if (direction == -1) direction = 3;
    }

    public void collisionDetectSnakeVsWorld() {
        if (getHead().getX() < 1 || getHead().getX() >= Screen.SCREEN_WIDTH - 2){
            dead = true;
        }
        if (getHead().getY() < 3 || getHead().getY() >= Screen.SCREEN_HEIGHT - 1){
            dead = true;
        }
    }

    public boolean collisionDetectSnakeVsFood(Food food, Screen screen) {
        if (getHead().getX() == food.getX() &&
            getHead().getY() == food.getY()){
            while (screen.getScreen().charAt(food.getX() + Screen.SCREEN_WIDTH * food.getY()) != ' ') {
                int newY = ThreadLocalRandom.current().nextInt(4, Screen.SCREEN_HEIGHT - 1);
                int newX = ThreadLocalRandom.current().nextInt(1, Screen.SCREEN_WIDTH);
                food.setX(newX);
                food.setY(newY);
            }
            snakeSegments.addLast(new SnakeSegment(
                    snakeSegments.getLast().getX(),
                    snakeSegments.getLast().getY()
            ));
            return true;
        } else {
            return false;
        }
    }

    public void collisionDetectSnakeVSnake(){
        for (SnakeSegment s : snakeSegments) {
            if (s != getHead() &&
                getHead().getX() == s.getX() &&
                getHead().getY() == s.getY()
            ) {
                dead = true;
                break;
            }
        }
    }

    public SnakeSegment getHead(){
        return snakeSegments.getFirst();
    }

    public List<SnakeSegment> getSnakeSegments() {
        return snakeSegments;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

}
