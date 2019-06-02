package controller;

import model.*;
import view.Board;
import view.View;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Commons, GameEndListener {

    private Timer timer;

    private Model theModel;
    private View theView;

    private Board theBoard;

    private Paddle paddle;
    private Ball ball;
    private Brick bricks[];

    public Controller(Model theModel, View theView) {
        this.theModel = theModel;
        this.theView = theView;
        this.theBoard = theView.getTheBoard();

        theBoard.addKeyListener(new TAdapter());
        theBoard.setFocusable(true);

        this.initController();
        theModel.addListener(this);
    }

    private void initController() {

        theBoard.boardInit();

        paddle = theModel.getPaddle();
        ball = theModel.getBall();
        bricks = theModel.getBricks();

        timer = new Timer();
        timer.scheduleAtFixedRate(new ScheduleTask(), DELAY, PERIOD);
    }


    @Override
    public void gameEnd(String message) {
        theBoard.setMessage(message);
        theModel.setInGame(false);
        theBoard.repaint();
        try {
            stopGame();
        }
        catch (InterruptedException e) {
        }
        finally {
            theModel.setInGame(true);
            theModel.gameInit();
            timer.cancel();
            initController();
        }
    }


    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }

    private class ScheduleTask extends TimerTask {

        @Override
        public void run() {
            ball.move();
            paddle.move();
            theModel.checkCollision();
            theBoard.repaint();
        }
    }

    private void stopGame() throws InterruptedException{
        Thread.sleep(5000);
    }
}
