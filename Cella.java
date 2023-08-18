/**
 * An object of Cella class represents a single cell of the Nonogram
 * 
 * 
 * 
 * 
 */
public class Cella{
    public static void main(String args[]){

    }
    /**
     * carattere variable represent the character in the cell represented by the invoking object
     */
    private Enum.tipo_carattere carattere=null;
    /**
     * riga variable represents the index of the cell's row of the nonogram.
     * if it is equal to -1 it means that the variable has not been initialized
     */
    private final int riga;
    /**
     * colonna variable represents the index of the cell's column of the nonogram.
     * if it is equal to -1 it means that the variable has not been initialized
     */
    private final int colonna;
    public Enum.tipo_carattere get_carattere(){
        return carattere;
    }
    /**
     * The method put an heart in the cell
     */
    public void set_blocco(){
        this.carattere=Enum.tipo_carattere.blocco;
    }
    /**
     * The method put an X in the cell
     */
    public void set_x(){
        this.carattere=Enum.tipo_carattere.x;
    }
    public int get_riga() {
        return riga;
    }
    public int get_colonna() {
        return colonna;
    }
    /**
     * The method create the cell with carattere inside.
     * The index of the cell's row is equals to the riga parameter.
     * The index of the cell's column is equals to the column parameter.
     * @param carattere future character inside 
     * @param riga index of the cell's row
     * @param colonna index of the cell's column
     */
    public Cella(Enum.tipo_carattere carattere,int riga,int colonna){
        this.carattere=carattere;
        this.riga=riga;
        this.colonna=colonna;
    }
    /**
     * The method create the white cell.
     * The index of the cell's row is equals to the riga parameter.
     * The index of the cell's column is equals to the column parameter.
     * @param riga index of the cell's row
     * @param colonna index of the cell's column
     */
    public Cella(int riga,int colonna){
        this(Enum.tipo_carattere.vuoto,riga,colonna);
    }

    public Cella copia(){
        return new Cella(carattere,riga,colonna);
    }
}