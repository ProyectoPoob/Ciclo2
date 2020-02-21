import java.util.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
/**
 * Constructor de piezas tipo rey
 * 
 * @author Javier Esteban López Peña
 * @author Andres Felipe Davila Gutierrez
 * @version 0.14 (18/02/2020)
 * @version 0.15 (19/02/2020)
 * @version 0.16 (20/02/2020)
 * @version 0.17 (21/02/2020)
 */
public class KingPieces
{
    private int i;
    private int j;
    private String color;
    private Triangle triangle;
    /**
     * Constructor for objects of class KingPieces
     * @param row entero, x coordenada
     * @param column entero, y coordenada
     * @param color String, color a pintar
     * @param xPos entero, distancia en x en la que se pintara sumada a row
     */
    public KingPieces(int row, int column,String color,int xPos)
    {
        triangle= new Triangle(25+(column*30)+ xPos,15+(row*30),color);
        i=row;
        j=column;
        this.color=color;        
    }
    /**
     * Constructor for objects of class KingPieces
     * @param xPos entero, x coordenada
     * @param yPos entero, y coordenada
     * @param color String, color a pintar
     */
    public  KingPieces(String color,int xPos,int yPos)
    {
        triangle= new Triangle(xPos,yPos,color);
        this.color=color;
    }
    /**
     * Hace blink al objeto
     */
    public void select()
    {
        triangle.blick();
    }
    /**
     * Vuelve invisible al objeto, si ya lo esta no hace nada
     */
    public void makeInvisible()
    {
        triangle.makeInvisible();
    }
    /**
     * Vuelve visible al objeto, si ya lo esta no hace nada
     */
    public void makeVisible()
    {
        triangle.makeVisible();
    }
    /**
     * Remueve el objeto
     */
    public void removeKing()
    {
        makeInvisible();
    }
    /**
     * Mueve horizontalmente el objeto
     * @param x entero, distancia a recorrer en pixeles
     */
    private void moveHorizontal(int x)
    {
        triangle.moveHorizontal(x);
    }
    /**
     * Mueve verticalmente el objeto
     * @param y entero, distancia a recorrer en pixeles
     */
    private void moveVertical(int y)
    {
        triangle.moveVertical(y);
    }
    /**
     * Mueve el objeto
     * @param x entero, distancia a recorrer en pixeles
     * @param y entero, distancia a recorrer en pixeles
     */
    public void moveking(int x, int y){
        moveHorizontal(x);
        moveVertical(y);
    }
}
