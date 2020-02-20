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
    public boolean[][] isFill;
    private Boolean[][] numBlocks;
    private int cont=1;
    public static int black=0;
    public Pieces[][] pieces;
    public KingPieces[][] kingPieces; 
    private int xPos;
    private int yPos;
    private boolean isSelect=false;
    private Pieces pieceSelect;
    private KingPieces kingPieceSelect;
    private int[] pieceSelectpos=new int[2];
    private Pieces verifier;
    private int width;    
    public Pieces Señalador1;
    public Pieces Señalador2;
    private int[][] consult;
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
        this.width=width;
        // initialise instance variables
        isFill = new boolean[width][width];
        pieces = new Pieces[width][width];
        kingPieces = new KingPieces[width][width];
        blocks = new ArrayList<Rectangle>();
        for (int i=0; i < width; i++)
        {        
          for (int j=0; j < width; j++)
          {
              //isFill[i][j]=false;
              pieces[i][j]=null;
              kingPieces[i][j]=null;
          }
        }       
        drawBoard(width);
        if(xPos==0)
        {                       
            Señalador1 = new Pieces("blue",width*55,width*35);   
            Señalador1.makeVisible();
        }
    }

    public void Señalador1()
    {
        Señalador1 = new Pieces("blue",width*55,width*35);  
        Señalador1.makeVisible();
        if(Señalador2!=null)
        {
            Señalador2.makeInvisible();       
        }
    }
    
    public void Señalador2()
    {
        Señalador2 = new Pieces("blue",width*15,width*35);
        Señalador2.makeVisible();
        if (Señalador1!=null)
        {
            Señalador1.makeInvisible(); 
        }
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
        if(sum%2!=0)
        {
            KingPieces king = new KingPieces(row-1,column-1,color,xPos);
            kingPieces[row-1][column-1]=king;
            isFill[row-1][column-1]=true;
            return true;
        } else
        {
            return false;
        }                
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
        if(sum%2!=0)
        {
            Pieces piece= new Pieces(row-1,column-1,color,xPos);
            pieces[row-1][column-1]=piece;
            isFill[row-1][column-1]=true;
            System.out.println(row-1 + " add i");
            System.out.println(column-1 + " add j");
            return true;            
        }else
        {
            return false;
        }
        
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
            System.out.println("entro al remove");
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
    
    public int[][] consult()
    {
        int contP=0;
        int contkP=0;
        for(int i=0;i<pieces.length;i++)
        {
            for(int j=0;j<pieces.length;j++)
            {
                if(pieces[i][j]!=null)
                {
                    contP++;
                } else if(kingPieces[i][j]!=null)
                {
                    contkP++;
                }
            }
        }
        consult= new int[contP+contkP][3];
        int cont=0;
        for(int i=0;i<pieces.length;i++)
        {
            for(int j=0;j<pieces.length;j++)
            {
                if(pieces[i][j]!=null)
                {
                    consult[cont][0]=i+1;
                    consult[cont][1]=j+1;
                    consult[cont][2]=0;
                    cont++;
                } else if(kingPieces[i][j]!=null)
                {
                    consult[cont][0]=i+1;
                    consult[cont][1]=j+1;
                    consult[cont][2]=1;
                    cont++;
                }
            }
        }
        return consult;
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
            //JOptionPane.showMessageDialog(null,"no hay fichas en esta casilla #JEG");
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
      }else
      {
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
    public void move(String notation,int salta)
    {
        if(pieceSelect!=null)
        {
                if (notation=="left")
                {
                    if((pieceSelectpos[0]+(1*salta))<width && (pieceSelectpos[1]-(1*salta))>0)
                    {
                        pieceSelect.move(-30*salta,30*salta);
                        pieces[pieceSelectpos[0]][pieceSelectpos[1]]=null;
                        pieces[pieceSelectpos[0]+(1*salta)][pieceSelectpos[1]-(1*salta)]=pieceSelect; 
                        isFill[pieceSelectpos[0]][pieceSelectpos[1]]=false;
                        isFill[pieceSelectpos[0]+(1*salta)][pieceSelectpos[1]-(1*salta)]=true;
                    }
                }
                else if(notation=="right")
                {
                    if((pieceSelectpos[0]+(1*salta))<width && (pieceSelectpos[1]+(1*salta))<width)
                    {
                        pieceSelect.move(30*salta,30*salta);
                        pieces[pieceSelectpos[0]][pieceSelectpos[1]]=null;
                        pieces[pieceSelectpos[0]+(1*salta)][pieceSelectpos[1]+(1*salta)]=pieceSelect;
                        isFill[pieceSelectpos[0]][pieceSelectpos[1]]=false;
                        isFill[pieceSelectpos[0]+(1*salta)][pieceSelectpos[1]+(1*salta)]=true;
                    }
                }
        }
    }
    
    /**
     * Mueve la ficha rey
     * @param notation cadena, puede ser left o right moviendolo en esa dirección
     */
    public void moveKing(String notation, int salta)
    {        
        if(kingPieceSelect!=null)
        {
            if (notation=="left")
            {
                if((pieceSelectpos[0]+(1*salta))>0 && (pieceSelectpos[0]+(1*salta))<width && (pieceSelectpos[1]-(1*salta))>0 && (pieceSelectpos[0]-(1*salta))<width)
                {
                    kingPieceSelect.moveking(-30*salta,30*salta);
                    kingPieces[pieceSelectpos[0]][pieceSelectpos[1]]=null;
                    kingPieces[pieceSelectpos[0]+(1*salta)][pieceSelectpos[1]-(1*salta)]=kingPieceSelect;
                    isFill[pieceSelectpos[0]][pieceSelectpos[1]]=false;
                    isFill[pieceSelectpos[0]+(1*salta)][pieceSelectpos[1]-(1*salta)]=true;
                }
            }
            else if(notation=="right")
            {
                if((pieceSelectpos[0]+(1*salta))>0 && (pieceSelectpos[0]+(1*salta))<width && (pieceSelectpos[1]-(1*salta))>0 && (pieceSelectpos[0]-(1*salta))<width)
                {
                    kingPieceSelect.moveking(30*salta,30*salta);
                    kingPieces[pieceSelectpos[0]][pieceSelectpos[1]]=null;
                    kingPieces[pieceSelectpos[0]+(1*salta)][pieceSelectpos[1]+(1*salta)]=kingPieceSelect;            
                    isFill[pieceSelectpos[0]][pieceSelectpos[1]]=false;
                    isFill[pieceSelectpos[0]+(1*salta)][pieceSelectpos[1]+(1*salta)]=true;
                }
            }    
        }
    }
    
    public void moveKingTop(String notation,int salta)
    {
        if(kingPieceSelect!=null)
        {
            if (notation=="left")
            {
                if((pieceSelectpos[0]+(1*salta))>0 && (pieceSelectpos[0]+(1*salta))<width && (pieceSelectpos[1]-(1*salta))>0 && (pieceSelectpos[0]-(1*salta))<width)
                {
                    kingPieceSelect.moveking(-30*salta,-30*salta);
                    kingPieces[pieceSelectpos[0]][pieceSelectpos[1]]=null;
                    kingPieces[pieceSelectpos[0]-(1*salta)][pieceSelectpos[1]-(1*salta)]=kingPieceSelect;
                    isFill[pieceSelectpos[0]][pieceSelectpos[1]]=false;
                    isFill[pieceSelectpos[0]-(1*salta)][pieceSelectpos[1]-(1*salta)]=true;
                }
            }
            else if(notation=="right")
            {
                if((pieceSelectpos[0]+(1*salta))>0 && (pieceSelectpos[0]+(1*salta))<width && (pieceSelectpos[1]-(1*salta))>0 && (pieceSelectpos[0]-(1*salta))<width)
                {
                    kingPieceSelect.moveking(30*salta,-30*salta);
                    kingPieces[pieceSelectpos[0]][pieceSelectpos[1]]=null;
                    kingPieces[pieceSelectpos[0]-(1*salta)][pieceSelectpos[1]+(1*salta)]=kingPieceSelect;    
                    isFill[pieceSelectpos[0]][pieceSelectpos[1]]=false;
                    isFill[pieceSelectpos[0]-(1*salta)][pieceSelectpos[1]+(1*salta)]=true;
                }
            }    
        }
    }
    
    public void moveTop(String notation,int salta)
    {
        System.out.println("Entro a mover afuera");
        if(pieceSelect!=null)
        {
                System.out.println("Entro a mover");
                if (notation=="left")
                {
                    if((pieceSelectpos[0]-1)<width && (pieceSelectpos[1]-1)>0)
                    {
                        pieceSelect.move(-30*salta,-30*salta);
                        pieces[pieceSelectpos[0]][pieceSelectpos[1]]=null;
                        pieces[pieceSelectpos[0]-(1*salta)][pieceSelectpos[1]-(1*salta)]=pieceSelect;              
                        isFill[pieceSelectpos[0]][pieceSelectpos[1]]=false;
                        isFill[pieceSelectpos[0]-(1*salta)][pieceSelectpos[1]-(1*salta)]=true;
                    }
                }
                else if(notation=="right")
                {
                    if((pieceSelectpos[0]-1)>0 && (pieceSelectpos[1]+1)<width)
                    {                        
                        pieceSelect.move(30*salta,-30*salta);
                        pieces[pieceSelectpos[0]][pieceSelectpos[1]]=null;
                        pieces[pieceSelectpos[0]-(1*salta)][pieceSelectpos[1]+(1*salta)]=pieceSelect;
                        isFill[pieceSelectpos[0]][pieceSelectpos[1]]=false;
                        isFill[pieceSelectpos[0]-(1*salta)][pieceSelectpos[1]+(1*salta)]=true;      
                    }
                }
        }
    }
    
    public void jump(boolean top ,boolean right)
    {//{{5,4},{4,5}}
        if(top && right)
        {
                if(isFill[pieceSelectpos[0]-1][pieceSelectpos[1]+1]==true && isFill[pieceSelectpos[0]-2][pieceSelectpos[1]+2]==false)
                {
                    moveKingTop("right",2);
                    moveTop("right",2);
                    matar(pieceSelectpos[0]-1,pieceSelectpos[1]+1);
                    pieceSelectpos[0]=pieceSelectpos[0]-2;
                    pieceSelectpos[1]=pieceSelectpos[1]+2;
                    detectar(pieceSelectpos[0],pieceSelectpos[1]);
                }
        } else if(!top && !right)
        {
                if(isFill[pieceSelectpos[0]+1][pieceSelectpos[1]-1]==true && isFill[pieceSelectpos[0]+2][pieceSelectpos[1]-2]==false)
                {
                    moveKing("left",2);
                    move("left",2);
                    matar(pieceSelectpos[0]+1,pieceSelectpos[1]-1); 
                    pieceSelectpos[0]=pieceSelectpos[0]+2;
                    pieceSelectpos[1]=pieceSelectpos[1]-2;
                    detectar(pieceSelectpos[0],pieceSelectpos[1]);
                }
        } else if(top && !right)
        {
                if(isFill[pieceSelectpos[0]-1][pieceSelectpos[1]-1]==true && isFill[pieceSelectpos[0]-2][pieceSelectpos[1]-2]==false)
                {
                    moveKingTop("left",2);
                    moveTop("left",2);
                    matar(pieceSelectpos[0]-1,pieceSelectpos[1]-1);
                    pieceSelectpos[0]=pieceSelectpos[0]-2;
                    pieceSelectpos[1]=pieceSelectpos[1]-2;
                    detectar(pieceSelectpos[0],pieceSelectpos[1]);
                }
        } else if(!top && right)
        {
                if(isFill[pieceSelectpos[0]+1][pieceSelectpos[1]+1]==true && isFill[pieceSelectpos[0]+2][pieceSelectpos[1]+2]==false)
                {
                    moveKing("right",2);
                    move("right",2);
                    matar(pieceSelectpos[0]+1,pieceSelectpos[1]+1); 
                    pieceSelectpos[0]=pieceSelectpos[0]+2;
                    pieceSelectpos[1]=pieceSelectpos[1]+2;
                    detectar(pieceSelectpos[0],pieceSelectpos[1]);
                }
        }       
    } 
    
    private void detectar(int row, int column)
    {//{{8,1},{7,2},{7,4},{7,6}}
        if(comprobar(row+1, column+1))
        {
            if(isFill[row+1][column+1]==true)
            {
                jump(false , true);
            }
        }
        if(comprobar(row-1, column-1))
        {
            if (isFill[row-1][column-1]==true)
            {
                jump(true , false);
            }
        }
        if(comprobar(row+1, column-1))
        {
            if(isFill[row+1][column-1]==true)
            {
                jump(false , false);
            }
        }
        if(comprobar(row-1, column+1))
        {
            if(isFill[row-1][column+1]==true)
            {
                System.out.println("Abajo derecha");
                jump(true , true);
            }
        }
    }
    
    private boolean comprobar(int r, int c)
    {
        if(r>=0 && r<width && c>=0 && c<width)
        {
            return true;
        } else
        {   
            return false;
        }
    }
    
    private void matar(int row, int column)
    {
        System.out.println("entro al matar");
        removeKing(row+1,column+1);
        remove(row+1,column+1);
        
    }
    
    public void shift(boolean top, boolean right)
    {
            if(top && right)
            {
                moveKingTop("right",1);
                moveTop("right",1);
            } else if(!top && !right)
            {
                moveKing("left",1);
                move("left",1);
            } else if(top && !right)
            {
                moveKingTop("left",1);
                moveTop("left",1);
            } else if(!top && right)
            {
                moveKing("right",1);
                move("right",1);
            }
    }
}

