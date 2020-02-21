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
     * @version 0.14 (18/02/2020)
     * @version 0.15 (19/02/2020)
     * @version 0.16 (20/02/2020)
     * @version 0.17 (21/02/2020)
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
    public Pieces pieceSelect;
    public KingPieces kingPieceSelect;
    private int[] pieceSelectpos=new int[2];
    private Pieces verifier;
    private int width;    
    public KingPieces señalador1;
    public KingPieces señalador2;
    private int[][] consult;
    public char[][] BoardInfo;
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
        BoardInfo=new char[width][width];
        for(int i=0; i <BoardInfo.length;i++) 
        {
            for(int j=0; j <BoardInfo.length;j++) 
            {
                //if(BoardInfo[i][j]!='W' && BoardInfo[i][j]!='w' && BoardInfo[i][j]!='b' && BoardInfo[i][j]!='B')
                //{
                    int k=i+j;
                    if(k%2!=0)
                    {
                        BoardInfo[i][j]='.';
                    }else
                    {
                        BoardInfo[i][j]='-';
                    }
                //}
            }
        }
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
            señalador1 = new KingPieces("red",width*55,width*35);   
            señalador1.makeVisible();
        }
    }

    /**
     * Ayuda a señalar el tablero activo
     */
    public void señalador1()
    {
        señalador1 = new KingPieces("red",width*55,width*35);  
        señalador1.makeVisible();
        if(señalador2!=null)
        {
            señalador2.makeInvisible();       
        }
    }
    
    /**
     * Ayuda a señalar el tablero activo
     */
    public void señalador2()
    {
        señalador2 = new KingPieces("red",width*15,width*35);
        señalador2.makeVisible();
        if (señalador1!=null)
        {
            señalador1.makeInvisible(); 
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
            if(color=="white")
            {
                BoardInfo[row-1][column-1]='W';
            }else if(color=="blue"){                    
                BoardInfo[row-1][column-1]='B';
            }
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
            if(color=="white")
            {
                BoardInfo[row-1][column-1]='w';
            }else if(color=="blue"){                    
                BoardInfo[row-1][column-1]='b';
            }
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
            BoardInfo[row-1][column-1]='.';
            
        }else
        {
            JOptionPane.showMessageDialog(null,"No se puede remover en casillas vacias");
           
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
            //System.out.println("entro al remove");
            pieces[row-1][column-1].removePiece();
            pieces[row-1][column-1]=null;
            isFill[row-1][column-1]=false;
            BoardInfo[row-1][column-1]='.';
        }
    }
    
    /*
     * Mueve los tableros
     * @param x entero, desplazamiento en x 
     */
    /*
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
        
    }*/
    /**
     * Ayuda a consultar las piensas del tablero
     * @return int[][], matriz con las posiciones y tipos de piezas en el tablero
     */
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
            //System.out.println("ficha seleccionada");
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
     * Mueve la ficha hacia abajo
     * @param notation cadena, puede ser left o right moviendolo en esa dirección
     * @param salta entero, 1 si la ficha se mueve sin saltar y 2 si va a saltar
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
     * Mueve la ficha rey hacia abajo
     * @param notation cadena, puede ser left o right moviendolo en esa dirección
     * @param salta entero, 1 si la ficha se mueve sin saltar y 2 si va a saltar
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
    
    /**
     * Mueve la ficha rey hacia arriba
     * @param notation cadena, puede ser left o right moviendolo en esa dirección
     * @param salta entero, 1 si la ficha se mueve sin saltar y 2 si va a saltar
     */
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
    
    /**
     * Mueve la ficha hacia arriba
     * @param notation cadena, puede ser left o right moviendolo en esa dirección
     * @param salta entero, 1 si la ficha se mueve sin saltar y 2 si va a saltar
     */
    public void moveTop(String notation,int salta)
    {
        //System.out.println("Entro a mover afuera");
        if(pieceSelect!=null)
        {
                //System.out.println("Entro a mover");
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
    
    /**
     * Realiza un salto la ficha matando a la ficha que saltó
     * @param top booleano, si se mueve para arriba o abajo
     * @param right booleano, si se mueve para derecha o izquierda
     */
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
    
    /**
     * Detecta fichas en las cuatro esquinas de los alrededores de las coordenadas dadas
     * @param row entero, fila coordenada x
     * @param column entero, columna coordenada y
     */
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
                //System.out.println("Abajo derecha");
                jump(true , true);
            }
        }
    }
    
    /**
     * Comprueba en la esquina si hay una ficha
     * @param r entero, fila coordenada x
     * @param c entero, columna coordenada y
     */
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
    
    /**
     * Remueve las fichas saltadas
     * @param row entero, fila coordenada x
     * @param column entero, columna coordenada y
     */
    private void matar(int row, int column)
    {
        //System.out.println("entro al matar");
        removeKing(row+1,column+1);
        remove(row+1,column+1);
        
    }
    
    /**
    * mueve fichas en el tablero de configuracion
    * @param top booleano, si se mueve para arriba o abajo
    * @param right booleano, si se mueve para derecha o izquierda
    */
    public void shift(boolean top, boolean right)
    {
            if(top && right)
            {
                moveKingTop("right",1);
                moveTop("right",1);
                BoardInfo[pieceSelectpos[0]-1][pieceSelectpos[1]+1]=BoardInfo[pieceSelectpos[0]][pieceSelectpos[1]];
                BoardInfo[pieceSelectpos[0]][pieceSelectpos[1]]='.';                
            } else if(!top && !right)
            {
                moveKing("left",1);
                move("left",1);
                BoardInfo[pieceSelectpos[0]+1][pieceSelectpos[1]+1]=BoardInfo[pieceSelectpos[0]][pieceSelectpos[1]];
                BoardInfo[pieceSelectpos[0]][pieceSelectpos[1]]='.'; 
            } else if(top && !right)
            {
                moveKingTop("left",1);
                moveTop("left",1);
                BoardInfo[pieceSelectpos[0]-1][pieceSelectpos[1]-1]=BoardInfo[pieceSelectpos[0]][pieceSelectpos[1]];
                BoardInfo[pieceSelectpos[0]][pieceSelectpos[1]]='.'; 
            } else if(!top && right)
            {
                moveKing("right",1);
                move("right",1);
                BoardInfo[pieceSelectpos[0]+1][pieceSelectpos[1]+1]=BoardInfo[pieceSelectpos[0]][pieceSelectpos[1]];
                BoardInfo[pieceSelectpos[0]][pieceSelectpos[1]]='.'; 
            }
    }
    
    /**
     * Reinicia el tablero dejandolo en blanco
     */
    public void resetBoard()
    {
        this.makeInvisible();
        for (int i=0; i < pieces.length ; i++)
        {
            for (int j=0; j < pieces.length ; j++)
            {
                if(pieces[i][j]!=null)
                {
                    remove(i+1,j+1);
                    removeKing(i+1,j+1);
                }
                pieces[i][j]=null;
                kingPieces[i][j]=null;
                isFill[i][j]=false;
            }
        }
        
    }

    /**
    * Traduce el tablero a una secuencia de caracteres
    * @return 
    */
    public char[][] write()
    {        
        return BoardInfo;
    }
        
    /**
     * Traduce la caracteres a un tablero
     * @param input char[][], entrada de caracteres representando un tablero
     */
    public void readBoard(char[][] input)
    {
        
        this.makeVisible();
        for(int i=0; i <input.length;i++) 
        {
            for(int j=0; j <input.length;j++) 
            {
                //System.out.println(input[i][j]);
                if(input[i][j] == 'W')
                {
                    
                    addKing(i+1, j+1,"white");
                }
                else if(input[i][j] == 'B')
                {
                    //Cambiar el color
                    addKing(i+1, j+1,"blue");
                }
                else if(input[i][j] == 'w')
                {
                    add(i+1, j+1,"white");
                }
                else if(input[i][j] == 'b')
                {
                    //Cambiar el color
                    add(i+1, j+1, "blue");
                }
            }
        }
        BoardInfo=input;
    }
}

