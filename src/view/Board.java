package view;

import model.*;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private String message;
    private Model theModel;

    private Paddle paddle;
    private Ball ball;
    private Brick bricks[];

    public Board(Model theModel) {

        this.theModel = theModel;
        boardInit();
    }

    public void boardInit() {
        paddle = theModel.getPaddle();
        ball = theModel.getBall();
        bricks = theModel.getBricks();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        if (theModel.isInGame()) {
            drawObjects(g2d);
        } else {
            gameFinished(g2d);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics2D g2d) {

        g2d.drawImage(ball.getImage(), ball.getX(), ball.getY(),
                ball.getImageWidth(), ball.getImageHeight(), this);
        g2d.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                paddle.getImageWidth(), paddle.getImageHeight(), this);

        for (int i = 0; i < theModel.getNumberOfBricks(); i++) {
            if (!bricks[i].isDestroyed()) {
                g2d.drawImage(bricks[i].getImage(), bricks[i].getX(),
                        bricks[i].getY(), bricks[i].getImageWidth(),
                        bricks[i].getImageHeight(), this);
            }
        }
    }

    private void gameFinished(Graphics2D g2d) {

        Font font = new Font("Verdana", Font.BOLD, 18);
        FontMetrics metr = this.getFontMetrics(font);

        g2d.setColor(Color.BLACK);
        g2d.setFont(font);
        g2d.drawString(message,
                (Model.WIDTH - metr.stringWidth(message)) / 2,
                Model.WIDTH / 2);
    }
}
