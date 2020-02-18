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
        // instance variables - replace the example below with your own
        private int width;
        private Board boardJ;
        private Board boardC;
        private int contKingPieces=0;
        
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
                boardC=new Board(width,distancia,0);            
            } else
            {
                JOptionPane.showMessageDialog(null,"Valor impar invalido #JEG"); //Johann es gurrero
            }           
        }
          
        public void select(int row,int column)
        {
            boardJ.select(row,column);
            boardJ.kingSelect(row,column);
        } 

        public void jump(boolean top ,boolean right)
        {
            
        } 
        public void move(String notation)
        {
            boardJ.moveKing(notation);
            boardJ.move(notation);            
        }
        

        
        public void add(boolean king ,int row,int column)
        {
            if(!boardC.isFill[row-1][column-1])
            {
                if(king)
                {
                    if(!boardC.addKing(row,column,"yellow"))
                    {
                       JOptionPane.showMessageDialog(null,"Casilla invalida #JEG");
                    }
                }        
                else
                {
                    if(!boardC.add(row,column,"yellow"))
                    {
                       JOptionPane.showMessageDialog(null,"Casilla invalida #JEG");
                    }
                }
            } else
            {
                JOptionPane.showMessageDialog(null,"Casilla ocupada #JEG");  
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
        }
        
        public void remove(int row,int column)
        {
            boardC.removeKing(row,column);
            boardC.remove(row,column);
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
        }
        public void swap()
        {
            Board aux=boardC;
            boardC=boardJ;
            boardJ=aux;
        }
        
        
        /*public int[][] consult()
        {
            return boardC.isFill;
        }*/
        
        /**
         * Hace visible las fichas y el tablero. Si ya lo estan, no hace nada.
         */
        public void makeVisible()
        {
            boardJ.makeVisible();
            boardC.makeVisible();
        
        }
        
        /**
         * Hace invisible las fichas y el tablero. Si ya lo estan, no hace nada.
         */
        public void makeInvisible()
        {
            boardJ.makeInvisible();
            boardC.makeInvisible();
        }
        public void finish()
        {
            System.exit(0);
        }
        public boolean ok()
        {
            return true;
        }
}
