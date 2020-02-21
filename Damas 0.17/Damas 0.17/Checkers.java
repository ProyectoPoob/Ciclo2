import java.util.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.HashMap;               

/**
* Juego de damas
*
* @author Andres Felipe Davila Gutierrez
* @author Javier Esteban López Peña
* @version 0.14 (18/02/2020)
* @version 0.15 (19/02/2020)
* @version 0.16 (20/02/2020)
* @version 0.17 (21/02/2020)
*/
public class Checkers   
{
        private int width;
        private Board boardInactive;
        private Board boardActive;
        private HashMap<String,String> savedBoards;
        private int contKingPieces=0;
        private boolean isSwapped=false;
        private boolean itWorks=false;
        private char[][] writenInactive;
        private char[][] writenActive; 
        /**
         * Constructor for objects of class Checkers
         * @param width, entero que da tamaño al tablero
         */
        public Checkers(int width)
        {                        
            this.width=width;
            int distancia=width*40;
            savedBoards = new HashMap<String, String>();
            if (width%2==0)
            {
                boardInactive=new Board(width,0,0);
                boardActive=new Board(width,distancia,0);     
            } else
            {
                JOptionPane.showMessageDialog(null,"Valor impar invalido #JEG"); //Johann es gurrero
            }                   
        }
          
        /**
         * Selecciona una ficha para moverla
         * @param row entero, fila coordenada x
         * @param column entero, columna coordenada y
         */
        public void select(int row,int column)
        {
            boardActive.select(row,column);
            boardActive.kingSelect(row,column);
            itWorks=true;
        } 

        /**
         * Salta una ficha en alguna casilla de las esquinas
         * @param top booleano, si salta para arriba o abajo
         * @param right booleano, si salta para derecha o izquierda
         */
        public void jump(boolean top ,boolean right)
        {
            if(isSwapped){
                boardActive.jump(top,right);
                itWorks=true;
            }else
            {
                itWorks=false;
            }
        } 
        /**
         * mueve fichas en el tablero de juego
         * @param notation String, "right" o "left" para moverlo
         */
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
        
        /**
         * mueve fichas en el tablero de configuracion
         * @param top booleano, si se mueve para arriba o abajo
         * @param right booleano, si se mueve para derecha o izquierda
         */
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
        
        /**
         * Guarda la secuencia de caracteres del tablero actual
         * @param name String, nombre llave para guardar el tablero
         */
        public void save(String name)
        {
            String cadena= write();
            System.out.println(cadena);
            savedBoards.put(name,cadena);
        }
        
        /**
         * Llama con llave un tablero guardado
         * @param name String, llave para llamar el tablero
         * @return String, secuencia de caracteres del tablero guardado
         */
        public String recover(String name)
        {
            return savedBoards.get(name);
        }
        
        /**
         * añade una ficha a la vez de tipo normal
         * @param king boolean, true si la añadir es rey y false si no.
         * @param row entero, fila coordenada x
         * @param column entero, columna coordenada y
         */
        public void add(boolean king ,int row,int column)
        {
            if(row<=width && column <=width){
                if(!isSwapped){
                    if(!boardActive.isFill[row-1][column-1])
                    {
                        if(king)
                        {
                            if(!boardActive.addKing(row,column,"white"))
                            {
                               JOptionPane.showMessageDialog(null,"Casilla invalida #JEG");
                            } else
                            {
                                itWorks=true;
                            }
                        }        
                        else
                        {
                            if(!boardActive.add(row,column,"white"))
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
                }else
                {
                    itWorks=false;
                }
            }else{
                itWorks=false;
                JOptionPane.showMessageDialog(null,"La posición está fuera del tablero");
            }
        }
    
        /**
         * añade varias fichas a la vez de tipo normal
         * @param piecess matriz entera, posicion de las piezas a a añadir
         */
        public void add(int[][] men)
        {
            if(!isSwapped){
                for (int i=0; i<men.length; i++)
                {
                    int row=men[i][0];
                    int column=men[i][1];
                    add(false,row,column);
                }
                itWorks=true;
            }else
            {
                itWorks=false;
            }
        }
        
        /**
         * remueve una sola ficha a la vez
         * @param row entero, fila coordenada x
         * @param column entero, columna coordenada y
         */
        public void remove(int row,int column)
        {
            if(!isSwapped){
                boardActive.removeKing(row,column);
                boardActive.remove(row,column);
                itWorks=true;
            }else
            {
                itWorks=false;
            }
        }                            //{{1,2},{3,4},{5,6}}            
        
        /**
         * Remueve varias fichas a la vez
         * @param piecess matriz entera, posicion de las piezas a remover 
         */
        public void remove(int[][] piecess)
        {
            if(!isSwapped){
                int pL=piecess.length;
                for (int i=0; i<pL; i++)
                {
                    int row=piecess[i][0];
                    int column=piecess[i][1];
                    remove(row,column);
                }
                itWorks=true;
            }else
            {
                itWorks=false;
            }
        }
        
        /**
         * Cambia el tablero activo
         */
        public void swap()
        {
            if(!isSwapped)
            {
                boardActive.señalador2();
                boardInactive.señalador2();
                isSwapped=true;
                writenInactive = boardInactive.write();
                writenActive = boardActive.write();
                copyPieces();
            }else
            {
                boardInactive.señalador1();
                boardActive.señalador1();
                isSwapped=false;
            }            
            Board aux=boardActive;
            boardActive=boardInactive;
            boardInactive=aux;
            if(!isSwapped)
            {
                boardInactive.BoardInfo=writenActive;
                boardActive.BoardInfo=writenInactive;
            }
            itWorks=true;
        }
        
        /**
         * Copia las piezas del tablero de configuración al de juego
         */
        private void copyPieces()
        {
            System.out.println("copyPieces");
            char[][] secuenceCopy = boardActive.write();
            boardInactive.resetBoard();
            boardInactive.readBoard(secuenceCopy);
        }
        
        //"-b-.-.-..-.-.-.--.-.-.-.B-.-w-.--.-.-W-.w-.-.-.--.-w-w-..-.-.-.-"
        //"-b-b-b-bb-b-b-b--b-b-b-b.-.-.-.--.-.-.-.w-w-w-w--w-w-w-ww-w-w-w-"
        /**
         * Lee una string y la traduce a un tablero con fichas
         * @param checkerboard string, secuencia de caractares que 
         * simulan el tablero
         */
        public void read(String checkerboard)
        {            
            if(!isSwapped)
            {
                double len=checkerboard.length();
                boardActive.resetBoard();
                char[][] input= new char[(int)Math.sqrt(len)][(int)Math.sqrt(len)];
                int cont=0;
                for(int i=0; i <(int)Math.sqrt(len);i++) 
                {
                    for(int j=0; j <(int)Math.sqrt(len);j++) 
                    {                 
                        input[i][j]=checkerboard.charAt(cont); 
                        //System.out.println(input[i][j]);
                        cont++;
                    }
                }                
                boardActive.readBoard(input);
            }
        }
        
        /**
         * Traduce el tablero a una secuencia de caracteres
         * @return 
         */
        public String write()
        {
            char[][] secuence = boardActive.write();
            String writenBoard = "";
            for(int i=0; i <secuence.length;i++) 
            {
                for(int j=0; j <secuence.length;j++) 
                {
                    writenBoard+=secuence[i][j];
                }
            }
            return writenBoard;
        }
        
        /**
         * Permite consultar las fichas que se encuentran en el tablero activo
         * @return una matriz de enteros con las fichas dentro del tablero en orden {{0,x,y}} 
         */
        public int[][] consult()
        {
            return boardActive.consult();
        }
        
        /**
         * Hace visible las fichas y el tablero. Si ya lo estan, no hace nada.
         */
        public void makeVisible()
        {
            boardInactive.makeVisible();
            boardActive.makeVisible();
            itWorks=true;
        }
        
        /**
         * Hace invisible las fichas y el tablero. Si ya lo estan, no hace nada.
         */
        public void makeInvisible()
        {
            boardInactive.makeInvisible();
            boardActive.makeInvisible();
            itWorks=true;
        }
        /**
         * Finaliza el juego 
         */
        public void finish()
        {
            System.exit(0);
        }
        /**
         * Nos permite observar si se completo la ultima ejecución
         * @return itWorks boolean, true si se completo la ultima ejecución y false si falló
         */
        public boolean ok()
        {
            return itWorks;
        }
}
