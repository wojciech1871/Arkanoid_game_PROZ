package view;

import model.*;

import javax.swing.*;

public class View extends JFrame {

    private Model theModel;
    private Board theBoard;

    public View(Model theModel) {

        this.theModel = theModel;
        this.theBoard = new Board(theModel);
        initUI();
    }
    
    private void initUI() {
        
        add(theBoard);
        setTitle("Breakout");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Commons.WIDTH, Commons.HEIGTH);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public Board getTheBoard() {
        return theBoard;
    }
}
