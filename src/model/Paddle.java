package model;

import javax.swing.ImageIcon;

public class Paddle extends Sprite {

    private int dx;
    private int speed = 1;

    public Paddle() {
        
        initPaddle();        
    }
    
    private void initPaddle() {

        loadImage();
        getImageDimensions();

        resetState();
    }
    
    private void loadImage() {
        
        ImageIcon ii = new ImageIcon("src/resources/paddle.png");
        image = ii.getImage();        
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void move() {

        x += dx;

        if (x <= 0) {
            x = 0;
        }

        if (x >= Model.WIDTH - imageWidth) {
            x = Model.WIDTH - imageWidth;
        }
    }

    public void moveRight() {
        dx = speed;
    }

    public void moveLeft() {
        dx = -speed;
    }

    public void stopMove() {
        dx = 0;
    }

    private void resetState() {

        x = Model.INIT_PADDLE_X;
        y = Model.INIT_PADDLE_Y;
    }
}
