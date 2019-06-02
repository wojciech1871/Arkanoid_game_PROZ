package model;

public interface Level {

    public int getPaddleSpeed();
    public int getNumOfBricks();
    public void generateMap(Brick[] brick);
}
