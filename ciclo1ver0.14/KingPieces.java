
import java.util.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 * Write a description of class KingPieces here.
 * 
 * @author Javier Esteban López Peña
 * @version (a version number or a date)
 */
public class KingPieces
{
    private int i;
    private int j;
    private String color;
    private Triangle triangle;
    /**
     * Constructor for objects of class KingPieces
     */
    public KingPieces(int row, int column,String color,int xPos)
    {
        triangle= new Triangle(25+(column*30)+ xPos,15+(row*30),"yellow");
        i=row;
        j=column;
        this.color=color;        
    }
    public void select()
    {
        triangle.blick();
    }
    private void makeInvisible()
    {
        triangle.makeInvisible();
    }
    public void removeKing()
    {
        makeInvisible();
    }
    public void moveHorizontal(int x)
    {
        triangle.moveHorizontal(x);
    }
    public void moveVertical(int y)
    {
        triangle.moveVertical(y);
    }
    public void moveking(int x, int y){
        moveHorizontal(x);
        moveVertical(y);
    }
}
