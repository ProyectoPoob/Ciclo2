import java.util.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 * Write a description of class Pieces here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Pieces
{
    private int i;
    private int j;
    private String color;
    private Circle circle;
    /**
     * Constructor for objects of class KingPieces
     */
    public Pieces(int row, int column,String color,int xPos)
    {
        circle= new Circle(15+(column*30)+xPos,15+(row*30),"yellow");
        i=row;
        j=column;
        this.color=color;
    }
    public  Pieces(String color,int xPos,int yPos)
    {
        circle= new Circle(xPos,yPos,"blue");
        this.color=color;
    }
    
    public void makeInvisible()
    {
        circle.makeInvisible();
    }
    
    public void removePiece()
    {
        makeInvisible();
    }
    public void moveHorizontal(int x)
    {
        circle.moveHorizontal(x);
    }
    public void moveVertical(int y)
    {
        circle.moveVertical(y);
    }
    public void select()
    {
        circle.blick();
    }
    public void makeVisible()
    {
        circle.makeVisible();
    }
    public void move(int x, int y){
        moveHorizontal(x);
        moveVertical(y);
    }
}
