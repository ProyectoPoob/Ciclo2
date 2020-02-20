
import java.util.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
              
/**
* Juego de damas
*
* @author Andres Felipe Davila Gutierrez
* @author Javier Esteban López Peña
* @version 0.15
*/
public class Checkers   
{
        private int width;
        private Board boardJ;
        private Board boardActive;
        private int contKingPieces=0;
        private boolean isSwapped=false;
        private boolean itWorks=false;
        /**
         * Constructor for objects of class Checkers
         * @param width, entero que da tamaño al tablero
         */
        public Checkers(int width)
        {                        
            this.width=width;
            int distancia=width*40;
            if (width%2==0)
            {
                boardJ=new Board(width,0,0);
                boardActive=new Board(width,distancia,0);       
            } else
            {
                JOptionPane.showMessageDialog(null,"Valor impar invalido #JEG"); //Johann es gurrero
            }                   
        }
          
        public void select(int row,int column)
        {
            boardActive.select(row,column);
            boardActive.kingSelect(row,column);
            itWorks=true;
        } 

        public void jump(boolean top ,boolean right)
        {
            boardActive.jump(top,right);
            itWorks=true;
        } 
        public void move(String notation)
        {
            if(isSwapped)
            {
                boardActive.moveKing(notation,1);
                boardActive.move(notation,1);     
                itWorks=true;
            } else
            {
                itWorks=false;
            }
        }        
        
        public void shift(boolean top, boolean right)
        {
            if(!isSwapped)
            {
                boardActive.shift(top,right);
                itWorks=true;
            }else
            {
                itWorks=false;
            }
        }
        
        public void add(boolean king ,int row,int column)
        {
            if(!boardActive.isFill[row-1][column-1])
            {
                if(king)
                {
                    if(!boardActive.addKing(row,column,"yellow"))
                    {
                       JOptionPane.showMessageDialog(null,"Casilla invalida #JEG");
                    } else
                    {
                        itWorks=true;
                    }
                }        
                else
                {
                    if(!boardActive.add(row,column,"yellow"))
                    {
                       JOptionPane.showMessageDialog(null,"Casilla invalida #JEG");
                    } else
                    {
                        itWorks=true;
                    }
                }
            } else
            {
                JOptionPane.showMessageDialog(null,"Casilla ocupada #JEG");
                itWorks=false;
            }
        }
    
        
        public void add(int[][] men)
        {
            for (int i=0; i<men.length; i++)
            {
                int row=men[i][0];
                int column=men[i][1];
                add(false,row,column);
            }
            itWorks=true;
        }
        
        public void remove(int row,int column)
        {
            boardActive.removeKing(row,column);
            boardActive.remove(row,column);
            itWorks=true;
        }                            //{{1,2},{3,4},{5,6}}            
        
        public void remove(int[][] piecess)
        {
            int pL=piecess.length;
            for (int i=0; i<pL; i++)
            {
                int row=piecess[i][0];
                int column=piecess[i][1];
                remove(row,column);
            }
            itWorks=true;
        }
        public void swap()
        {
            if(!isSwapped)
            {
                boardActive.Señalador2();
                boardJ.Señalador2();
                isSwapped=true;
            }else
            {
                boardJ.Señalador1();
                boardActive.Señalador1();
                isSwapped=false;
            }
            Board aux=boardActive;
            boardActive=boardJ;
            boardJ=aux;
            itWorks=true;
        }
        
        
        public int[][] consult()
        {
            return boardActive.consult();
        }
        
        /**
         * Hace visible las fichas y el tablero. Si ya lo estan, no hace nada.
         */
        public void makeVisible()
        {
            boardJ.makeVisible();
            boardActive.makeVisible();
            itWorks=true;
        }
        
        /**
         * Hace invisible las fichas y el tablero. Si ya lo estan, no hace nada.
         */
        public void makeInvisible()
        {
            boardJ.makeInvisible();
            boardActive.makeInvisible();
            itWorks=true;
        }
        public void finish()
        {
            System.exit(0);
        }
        public boolean ok()
        {
            return itWorks;
        }
}
