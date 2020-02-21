import java.util.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 * Constructor de piezas tipo normal
 * 
 * @author Javier Esteban López Peña
 * @author Andres Felipe Davila Gutierrez
 * @version 0.14 (18/02/2020)
 * @version 0.15 (19/02/2020)
 * @version 0.16 (20/02/2020)
 * @version 0.17 (21/02/2020)
 */
public class Pieces
{
    private int i;
    private int j;
    private String color;
    private Circle circle;
    /**
     * Constructor for objects of class KingPieces
     * @param row entero, x coordenada
     * @param column entero, y coordenada
     * @param color String, color a pintar
     * @param xPos entero, distancia en x en la que se pintara sumada a row
     */
    public Pieces(int row, int column,String color,int xPos)
    {
        circle= new Circle(15+(column*30)+xPos,15+(row*30),color);
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
    public  Pieces(String color,int xPos,int yPos)
    {
        circle= new Circle(xPos,yPos,color);
        this.color=color;
    }
    /**
     * Vuelve invisible al objeto, si ya lo esta no hace nada
     */
    public void makeInvisible()
    {
        circle.makeInvisible();
    }
    /**
     * Remueve el objeto
     */
    public void removePiece()
    {
        makeInvisible();
    }
    /**
     * Mueve horizontalmente el objeto
     * @param x entero, distancia a recorrer en pixeles
     */
    public void moveHorizontal(int x)
    {
        circle.moveHorizontal(x);
    }
    /**
     * Mueve verticalmente el objeto
     * @param x entero, distancia a recorrer en pixeles
     */
    public void moveVertical(int y)
    {
        circle.moveVertical(y);
    }
    /**
     * Hace blick al objeto
     */
    public void select()
    {
        circle.blick();
    }
    /**
     * Vuelve visible al objeto, si ya lo esta no hace nada
     */
    public void makeVisible()
    {
        circle.makeVisible();
    }
    /**
     * Mueve el objeto
     * @param x entero, distancia a recorrer en pixeles
     * @param y entero, distancia a recorrer en pixeles
     */
    public void move(int x, int y){
        moveHorizontal(x);
        moveVertical(y);
    }
}
