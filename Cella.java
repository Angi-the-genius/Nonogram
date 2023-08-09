/**
 * An object of Cella class represents a single cell of the Nonogram
 * 
 * 
 * 
 * 
 */
public class Cella{
    public static void main(String args[]){
        Nonogram nonogram=new Nonogram(5,3);
        nonogram.leggo();
        nonogram.risolvo();
        nonogram.stampo();
    }
    /**
     * carattere variable represent the character in the cell represented by the invoking object
     */
    private String carattere;  
    /**
     * This attribute is true when the ce
     */
    private boolean ce_nella_riga;
    private boolean ce_nella_colonna;
    private int posiz;
    public String get_carattere(){
        return carattere;
    }
    public void set_carattere(String carattere){
        this.carattere=carattere;
    }
    /**
     * The method put an heart in the cell
     */
    public void set_blocco(){
        set_carattere(" "+(char)3+" ");
    }
    /**
     * The method put an X in the cell
     */
    public void set_x(){
        set_carattere(" X ");
    }

    public boolean get_ce_nella_riga(){
        return ce_nella_riga;
    }
    public boolean get_ce_nella_colonna(){
        return ce_nella_colonna;
    }
    /**
     * The method sets the ce_nella_riga equals to false
     */
    public void tolgo_dalla_riga(){
        ce_nella_riga=false;
    }
    /**
     * The method sets the ce_nella_colonna equals to false
     */
    public void tolgo_dalla_colonna(){
        ce_nella_colonna=false;
    }


    public void set_posiz(int posiz){
        this.posiz=posiz;
    }
    public int get_posiz(){
        return posiz;
    }
    public Cella(String carattere,int posiz,int base,int altezza){
        set_carattere(carattere);
        set_posiz(posiz);
        set_base(base);
        set_altezza(altezza);
        ce_nella_riga=true;
        ce_nella_colonna=true;
    }
    
    private int base;//base del nonogram a cui appartiene la cella
    private int altezza;//alteza del nonogram a cui appartiene la cella
    public void set_base(int base){
        this.base=base;
    }
    public void set_altezza(int altezza){
        this.altezza=altezza;
    }
    public int get_base(){
        return base;
    }
    public int get_altezza(){
        return altezza;
    }
    //METODI DI CLASSE
    public int get_riga(){
        return (int)posiz/base;
    }
    public int get_colonna(){
        return posiz%altezza;
    }
}