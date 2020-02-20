import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
/**
     * Tablero del Juego de damas
     *
     * @author Andres Felipe Davila Gutierrez
     * @author Javier Esteban López Peña
     * @version 0.14
     */
public class Board
{
    // instance variables - replace the example below with your own
    private ArrayList<Rectangle> blocks;
    public Boolean[][] isFill;
    private Boolean[][] numBlocks;
    private int cont=1;
    public static int black=0;
    private Pieces[][] pieces;
    private KingPieces[][] kingPieces; 
    private int xPos;
    private int yPos;
    private boolean isSelect=false;
    private Pieces pieceSelect;
    private KingPieces kingPieceSelect;
    private int[] pieceSelectpos=new int[2];
    private Pieces verifier;
    /**
     * Constructor for objects of class Board
     * @param width entero, es el numero de cuadros del tablero
     * @param xPos entero, posicion en x 
     * @param yPos entero, posicion en y
     */
    public Board(int width,int xPos,int yPos)
    {
        int cont=0;
        this.xPos=xPos;
        this.yPos=yPos;
        // initialise instance variables
        isFill = new Boolean[width][width];
        pieces = new Pieces[width][width];
        kingPieces = new KingPieces[width][width];
        blocks = new ArrayList<Rectangle>();
        for (int i=0; i < width; i++)
        {        
          for (int j=0; j < width; j++)
          {
              isFill[i][j]=false;
              pieces[i][j]=null;
              kingPieces[i][j]=null;
          }
        }       
        drawBoard(width);
        verifier= new Pieces("blue",width*55,width*35);
    }

    /**
     * Dibujador del tablero
     * @param width entero, es el numero de cuadros del tablero
     */
    private void drawBoard(int width)
    {
       cont=1;      
       for (int i=0; i < width/2; i++)
       {        
           for (int j=0; j < width; j++)
           {             
              if(cont%2==0)
              {
                   Rectangle rectangle = new Rectangle(10+(i*60)+xPos,10+(j*30));
                   blocks.add(rectangle);
              } 
              else if(cont%2!=0)
              {
                   Rectangle rectangle = new Rectangle(10+(i*60)+30+xPos,10+(j*30));
                   blocks.add(rectangle);
              }
              cont++;
           }
       }
    }
    
    /**
     * Añade un rey al tablero
     * @param row entero, posicion en fila
     * @param column entero, posicion en columna
     * @param color cadena, color del rey
     */
    public boolean addKing(int row,int column,String color)
    {
        int sum=row+column;
        if(sum%2==0)
        {
            return false;
        }
        
        KingPieces king = new KingPieces(row-1,column-1,color);
        kingPieces[row-1][column-1]=king;
        isFill[row-1][column-1]=true;
        return true;
    }
    /**
     * Añade una ficha al tablero
     * @param row entero, posicion en fila
     * @param column entero, posicion en columna
     * @param color cadena, color de la ficha
     */
    public boolean add(int row,int column,String color)
    {
        int sum=row+column;
        if(sum%2==0)
        {
            return false;
        }
        
        Pieces piece= new Pieces(row-1,column-1,color,xPos);
        pieces[row-1][column-1]=piece;
        isFill[row-1][column-1]=true;
        return true;
    }
    /**
     * Quita un rey al tablero
     * @param row entero, posicion en fila
     * @param column entero, posicion en columna
     */
    public void removeKing(int row, int column)
    {
        if(kingPieces[row-1][column-1]!=null)
        {
            kingPieces[row-1][column-1].removeKing();
            kingPieces[row-1][column-1]=null;
            isFill[row-1][column-1]=false;
        }
    }
      
    /**
     * Quita una ficha al tablero
     * @param row entero, posicion en fila
     * @param column entero, posicion en columna
     */
    public void remove(int row, int column)
    {
        if(pieces[row-1][column-1]!=null)
        {
            pieces[row-1][column-1].removePiece();
            pieces[row-1][column-1]=null;
            isFill[row-1][column-1]=false;
        }
    }
    
    /**
     * Mueve los tableros
     * @param x entero, desplazamiento en x 
     */
    public void move(int x)
    {
        for(Rectangle r: blocks)
        {
            r.moveHorizontal(x);
        }
        for(int i=0;i<pieces.length;i++)
        {
            for (int j=0;j<pieces.length;j++)
            {
                if(pieces[i][j]!=null)
                {
                    pieces[i][j].moveHorizontal(x);
                }
            }
        }
        for(int i=0;i<kingPieces.length;i++)
        {
            for (int j=0;j<kingPieces.length;j++)
            {
                if(kingPieces[i][j]!=null)
                {
                    kingPieces[i][j].moveHorizontal(x);
                }
            }
        }
        
    }
    
    /**
     * Selecciona una ficha del tablero
     * @param row entero, posicion en fila
     * @param column entero, posicion en columna
     */
    public void select(int row,int column)
    {
        
      if(pieces[row-1][column-1]!=null)
        {
            isSelect=true;
            pieces[row-1][column-1].select();
            pieceSelect=pieces[row-1][column-1];
            pieceSelectpos[0]=row-1;
            pieceSelectpos[1]=column-1;
            //System.out.println("ficha seleccionada");
        }else{
            JOptionPane.showMessageDialog(null,"no hay fichas en esta casilla #JEG");
        } 
    }
        /**
     * Selecciona una ficha del tablero
     * @param row entero, posicion en fila
     * @param column entero, posicion en columna
     */
    public void kingSelect(int row,int column)
    {
        
      if(kingPieces[row-1][column-1]!=null)
        {
            isSelect=true;
            kingPieces[row-1][column-1].select();
            kingPieceSelect=kingPieces[row-1][column-1];
            pieceSelectpos[0]=row-1;
            pieceSelectpos[1]=column-1;
            System.out.println("ficha seleccionada");
        }else{
            //JOptionPane.showMessageDialog(null,"no hay fichas en esta casilla #JEG");
        } 
    }
    
    /**
     * Vuelve visible, si ya lo esta no hace nada
     */
    public void makeVisible()
    {
            for(Rectangle r: blocks)
            {
                r.makeVisible();
            }
            for(int i=0;i<pieces.length;i++)
            {
                for (int j=0;j<pieces.length;j++)
                {
                    if(pieces[i][j]!=null)
                    {
                        pieces[i][j].makeVisible();
                    }
                }
            }
    }
    
    /**
     * Vuelve invisible, si ya lo esta no hace nada
     */
    public void makeInvisible()
    {
        for(Rectangle r: blocks)
            {
                r.makeInvisible();
            }
        for(int i=0;i<pieces.length;i++)
            {
                for (int j=0;j<pieces.length;j++)
                {
                    if(pieces[i][j]!=null)
                    {
                        pieces[i][j].makeInvisible();
                    }
                }
            }
    }
    
    /**
     * Mueve la ficha
     * @param notation cadena, puede ser left o right moviendolo en esa dirección
     */
    public void move(String notation)
    {
        if(pieceSelect!=null)
        {
            if (notation=="left")
            {
                pieceSelect.move(-30,30);
                pieces[pieceSelectpos[0]][pieceSelectpos[1]]=null;
                pieces[pieceSelectpos[0]+1][pieceSelectpos[1]-1]=pieceSelect;
            }
            else if(notation=="right")
            {
                pieceSelect.move(30,30);
                pieces[pieceSelectpos[0]][pieceSelectpos[1]]=null;
                pieces[pieceSelectpos[0]+1][pieceSelectpos[1]+1]=pieceSelect;
            }
        }
    }
    /**
     * Mueve la ficha rey
     * @param notation cadena, puede ser left o right moviendolo en esa dirección
     */
    public void moveKing(String notation)
    {        
        if(kingPieceSelect!=null)
        {
            if (notation=="left")
            {
                kingPieceSelect.moveking(-30,30);
                kingPieces[pieceSelectpos[0]][pieceSelectpos[1]]=null;
                kingPieces[pieceSelectpos[0]+1][pieceSelectpos[1]-1]=kingPieceSelect;
            }
            else if(notation=="right")
            {
                kingPieceSelect.moveking(30,30);
                kingPieces[pieceSelectpos[0]][pieceSelectpos[1]]=null;
                kingPieces[pieceSelectpos[0]+1][pieceSelectpos[1]+1]=kingPieceSelect;
            }
        }
    }
}

