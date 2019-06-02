package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class Model {

    public static final int WIDTH = 300;
    public static final int HEIGTH = 400;
    public static final int BOTTOM_EDGE = 390;
    public static final int INIT_PADDLE_X = 200;
    public static final int INIT_PADDLE_Y = 360;
    public static final int INIT_BALL_X = 230;
    public static final int INIT_BALL_Y = 355;
    public static final int DELAY = 1000;
    public static final int PERIOD = 10;

    private Ball ball;
    private Paddle paddle;
    private Brick bricks[];

    private int numberOfBricks;
    private boolean inGame = true;

    private Level level1;
    private Level level2;

    private List<GameEndListener> gameEndListeners =  new ArrayList<>();


    public Model() {
        this.level1 = new Level() {
            @Override
            public int getPaddleSpeed() {
                return 2;
            }

            @Override
            public int getNumOfBricks() {
                return 30;
            }

            @Override
            public void generateMap(Brick[] brick) {
                int k = 0;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 6; j++) {
                        bricks[k] = new Brick(j * 40 + 30, i * 10 + 50);
                        k++;
                    }
                }
            }
        };
        this.level2 = new Level() {
            @Override
            public int getPaddleSpeed() {
                return 1;
            }

            @Override
            public int getNumOfBricks() {
                return 48;
            }

            @Override
            public void generateMap(Brick[] brick) {
                int k = 0;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 6; j++) {
                        bricks[k] = new Brick(j * 40 + 30, i * 10 + 50);
                        k++;
                    }
                }
            }
        };
        gameInit(level1);
    }

    public void addListener(GameEndListener toAdd) {
        gameEndListeners.add(toAdd);
    }

    private void gameEnd(String message) {
        for (GameEndListener listener : gameEndListeners)
            listener.gameEnd(message);
    }

    public int getNumberOfBricks() {
        return numberOfBricks;
    }

    public Ball getBall() {
        return ball;
    }

    public Paddle getPaddle() {
        return paddle;
    }

    public Brick[] getBricks() {
        return bricks;
    }

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public void gameInit(Level level) {
        ball = new Ball();
        paddle = new Paddle();
        paddle.setSpeed(level.getPaddleSpeed());

        numberOfBricks = level.getNumOfBricks();
        bricks = new Brick[numberOfBricks];
        level.generateMap(bricks);
    }

    public void moveBall() {
        ball.move();
    }

    public void stopPaddle() {
        paddle.stopMove();
    }

    public void setPaddleRight() {
        paddle.moveRight();
    }

    public void setPaddleLeft() {
        paddle.moveLeft();
    }

    public void movePaddle() {
        paddle.move();
    }

    public void checkCollision() {

        if (ball.getRect().getMaxY() > BOTTOM_EDGE) {
            gameEnd("Game loss");
        }

        for (int i = 0, j = 0; i < numberOfBricks; i++) {

            if (bricks[i].isDestroyed()) {
                j++;
            }

            if (j == numberOfBricks) {
                gameEnd("Game vin");
            }
        }

        if ((ball.getRect()).intersects(paddle.getRect())) {

            int paddleLPos = (int) paddle.getRect().getMinX();
            int ballLPos = (int) ball.getRect().getMinX();

            int first = paddleLPos + 8;
            int second = paddleLPos + 16;
            int third = paddleLPos + 24;
            int fourth = paddleLPos + 32;

            if (ballLPos < first) {
                ball.setXDir(-1);
                ball.setYDir(-1);
            }

            if (ballLPos >= first && ballLPos < second) {
                ball.setXDir(-1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos >= second && ballLPos < third) {
                ball.setXDir(0);
                ball.setYDir(-1);
            }

            if (ballLPos >= third && ballLPos < fourth) {
                ball.setXDir(1);
                ball.setYDir(-1 * ball.getYDir());
            }

            if (ballLPos > fourth) {
                ball.setXDir(1);
                ball.setYDir(-1);
            }
        }

        for (int i = 0; i < numberOfBricks; i++) {

            if ((ball.getRect()).intersects(bricks[i].getRect())) {

                int ballLeft = (int) ball.getRect().getMinX();
                int ballHeight = (int) ball.getRect().getHeight();
                int ballWidth = (int) ball.getRect().getWidth();
                int ballTop = (int) ball.getRect().getMinY();

                Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
                Point pointLeft = new Point(ballLeft - 1, ballTop);
                Point pointTop = new Point(ballLeft, ballTop - 1);
                Point pointBottom = new Point(ballLeft, ballTop + ballHeight + 1);

                if (!bricks[i].isDestroyed()) {
                    if (bricks[i].getRect().contains(pointRight)) {
                        ball.setXDir(-1);
                    } else if (bricks[i].getRect().contains(pointLeft)) {
                        ball.setXDir(1);
                    }

                    if (bricks[i].getRect().contains(pointTop)) {
                        ball.setYDir(1);
                    } else if (bricks[i].getRect().contains(pointBottom)) {
                        ball.setYDir(-1);
                    }

                    bricks[i].setDestroyed(true);
                }
            }
        }
    }
    public Level getLevel1() {
        return level1;
    }

    public Level getLevel2() {
        return level2;
    }
}
