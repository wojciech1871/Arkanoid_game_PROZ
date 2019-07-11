package view;

import model.*;

import javax.swing.*;

public class View extends JFrame {

    private Board theBoard;

    public View(Model theModel) {

        this.theBoard = new Board(theModel);
        initUI();
    }
    
    private void initUI() {
        
        add(theBoard);
        setTitle("Arkanoid Game");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(Model.WIDTH, Model.HEIGTH);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public Board getTheBoard() {
        return theBoard;
    }
}
