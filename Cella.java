public class Cella{
    public static void main(String args[]){
        Nonogram nonogram=new Nonogram(5,3);
        nonogram.leggo();
        nonogram.risolvo();
        nonogram.stampo();
    }
    private String carattere;
    public String get_carattere(){
        return carattere;
    }
    public void set_carattere(String carattere){
        this.carattere=carattere;
    }
    public void set_blocco(){//mette un cuore nella cella
        set_carattere(" "+(char)3+" ");
    }
    public void set_x(){
        set_carattere(" X ");
    }
    
    private boolean ce_nella_riga;
    private boolean ce_nella_colonna;
    public boolean get_ce_nella_riga(){
        return ce_nella_riga;
    }
    public boolean get_ce_nella_colonna(){
        return ce_nella_colonna;
    }
    public void tolgo_dalla_riga(){
        ce_nella_riga=false;
    }
    public void tolgo_dalla_colonna(){
        ce_nella_colonna=false;
    }

    private int posiz;
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