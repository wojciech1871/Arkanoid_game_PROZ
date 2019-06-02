package model;

import javax.swing.ImageIcon;

public class Paddle extends Sprite implements Commons {

    private int dx;

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

    public void move() {

        x += dx;

        if (x <= 0) {
            x = 0;
        }

        if (x >= WIDTH - imageWidth) {
            x = WIDTH - imageWidth;
        }
    }

    public void moveRight() {
        dx = 1;
    }

    public void moveLeft() {
        dx = -1;
    }

    public void stopMove() {
        dx = 0;
    }

    private void resetState() {

        x = INIT_PADDLE_X;
        y = INIT_PADDLE_Y;
    }
}
