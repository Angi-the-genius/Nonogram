import java.rmi.registry.RegistryHandler;
import java.util.Scanner;
import javax.lang.model.util.ElementScanner14;
public class Nonogram{
    public static void main(String[] args)
    {
        Nonogram nonogram=Metodi.creo_nonogram(3);
        nonogram.stampo(0);
        nonogram.stampo();
        nonogram.risolvo();
        nonogram.stampo();
    }
    private Cella celle[][];
    public void set_cella(int i,int j,String carattere){
        celle[i][j].set_carattere(carattere);
    }
    public void set_celle(Cella nonogram[][]){
        this.celle=nonogram;
    }
    public void set_celle(){
        celle=new Cella[altezza][base];
        for(int i=0;i<altezza;i++)
            for(int j=0;j<base;j++)
                celle[i][j]=new Cella("   ",i*base+j,base,altezza);
    }
    public Cella[][] get_celle(){
        return celle;
    }
    public Cella get_cella(int i,int j){
        return celle[i][j];
    }
    private Settore vet_righe[];
    private Settore vet_colonne[];
    public Settore get_riga(int index){
        return vet_righe[index];
    }
    public Settore get_colonna(int index){
        return vet_colonne[index];
    }
    public void set_settori(){
        vet_righe=new Settore[altezza];
        vet_colonne=new Settore[base];
        for(int i=0;i<altezza;i++)
            vet_righe[i]=new Settore(i,"Riga",celle,base,altezza);
        for(int i=0;i<base;i++)
            vet_colonne[i]=new Settore(i,"Colonna",celle,base,altezza);
    }
    public void set_riga(int i,Settore riga){
        vet_righe[i]=riga;
    }
    public void set_colonna(int i,Settore colonna){
        vet_colonne[i]=colonna;
    }
    private int base;
    private int altezza;
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
    public Nonogram(int base,int altezza){
        this.base=base;
        this.altezza=altezza;
        set_celle();
        set_settori();
    }
    public Nonogram(int base,int altezza,int numeri_righe[][],int numeri_colonne[][]){
        this(base,altezza);
        set_numeri(numeri_righe,numeri_colonne);
    }
    public Nonogram(int base,int altezza,Cella nonogram[][],int numeri_righe[][],int numeri_colonne[][]){
        this(base,altezza,numeri_righe,numeri_colonne);
        set_celle(nonogram);
    }
    //METODI DI CLASSE
    public void set_numeri(int numeri_righe[][],int numeri_colonne[][]){
        for(int i=0;i<altezza;i++)
            vet_righe[i].set_numeri(numeri_righe[i]);
        for(int i=0;i<base;i++)
            vet_colonne[i].set_numeri(numeri_colonne[i]);
    }
    public int massimo_righe(){//ritorna quanto è lungo la riga con piu indizi
        int massimo=-1;
        for(int i=0;i<vet_righe.length;i++)
            if(vet_righe[i].get_numeri().length>massimo)
                massimo=vet_righe[i].get_numeri().length;
        return massimo;
    }
    public int massimo_colonne(){//ritorna quanto è lungo la riga con piu indizi
        int massimo=-1;
        for(int i=0;i<vet_colonne.length;i++)
            if(vet_colonne[i].get_numeri().length>massimo)
                massimo=vet_colonne[i].get_numeri().length;
        return massimo;
    }
    public void leggo(){
        System.out.println("Inserire righe:");
        for(int i=0;i<altezza;i++)//per ogni riga   
            vet_righe[i].leggo();
        System.out.println("Inserire colonne:");
        for(int i=0;i<base;i++)
            vet_colonne[i].leggo();
    }
    public void stampo(int inutile){
        for(int i=0;i<altezza;i++){
            System.out.print("Riga "+i+": ");
            Metodi.stampo(get_riga(i).get_numeri());
        }
        for(int i=0;i<base;i++){
            System.out.print("colonna "+i+": ");
            Metodi.stampo(get_colonna(i).get_numeri());
        }
    }
    public void stampo(){
        int max_righe=massimo_righe(),max_colonne=massimo_colonne();
        for(int i=0;i<max_colonne;i++){
            for(int j=0;j<max_righe;j++)
                System.out.print("   ");
            for(int j=0;j<base;j++){
                int cont=max_colonne-vet_colonne[j].get_numeri().length;//quanti spazi mettere alla i-esima colonna
                if(cont>i)
                    System.out.print("   ");
                else
                    if(vet_colonne[j].get_numero(i-cont)>=10)
                        System.out.print(vet_colonne[j].get_numero(i-cont)+" ");
                    else
                        System.out.print(" "+vet_colonne[j].get_numero(i-cont)+" ");
            }
            System.out.println("");
        }
        for(int i=0;i<altezza;i++){
            int cont=max_righe-vet_righe[i].get_numeri().length;
            for(int j=0;j<cont;j++)
                System.out.print("   ");
            for(int j=0;j<vet_righe[i].get_numeri().length;j++)
                if(vet_righe[i].get_numero(j)>=10)
                    System.out.print(vet_righe[i].get_numero(j)+" ");
                else
                    System.out.print(" "+vet_righe[i].get_numero(j)+" ");
            for(int j=0;j<base;j++)
                System.out.print(celle[i][j].get_carattere());
            System.out.println("");
        }
    }
    public boolean finito(){
        return Metodi.finito(this.celle);
    }
    public int[][] get_numeri_righe(){
        int numeri_righe[][]=new int[altezza][0];
        for(int i=0;i<altezza;i++)
            numeri_righe[i]=get_riga(i).get_numeri();
        return numeri_righe;
    }
    public int[][] get_numeri_colonne(){
        int numeri_colonne[][]=new int[base][0];
        for(int i=0;i<base;i++)
            numeri_colonne[i]=get_colonna(i).get_numeri();
        return numeri_colonne;
    }
    public boolean assurdo(){//ritorna true se il nonogram è assurdo 
        for(int i=0;i<altezza;i++)
            if(vet_righe[i].creo_configurazioni().length==0)
                return true;
        for(int i=0;i<base;i++)
            if(vet_colonne[i].creo_configurazioni().length==0)
                return true;
        return false;
    }
    public Nonogram copia_nonogram(){
        Nonogram nonogram=new Nonogram(base,altezza);
        for(int i=0;i<altezza;i++)
            nonogram.set_riga(i,vet_righe[i].copia_settore());
        for(int j=0;j<base;j++)
            nonogram.set_colonna(j,vet_colonne[j].copia_settore());
        return nonogram;
    }
    //TECNICHE RISOLUTIVE
    public boolean risolvo(){
        Scanner tastiera=new Scanner(System.in);
        for(int i=0;i<altezza;i++)
            if(vet_righe[i].tecnica(this.celle,get_numeri_righe(),get_numeri_colonne())){
                System.out.println("Applico tecnica alla riga "+i);
                stampo();
                //int interrompo_flusso=tastiera.nextInt();
                if(finito())
                    return true;
                risolvo();
            }
        for(int i=0;i<base;i++)
            if(vet_colonne[i].tecnica(this.celle,get_numeri_righe(),get_numeri_colonne())){
                System.out.println("Applico tecnica alla colonna "+i);
                stampo();
                //int interrompo_flusso=tastiera.nextInt();
                if(finito())
                    return true;
                risolvo();
            }
        if(finito())
            return true;
        else{
            System.out.println("Con le tecniche a disposizione non sono in grado di risolvere il nonogram");
            return false;
        }
    }
    public Nonogram[] tentativi(){
        risolvo();
        if(finito()){
            Nonogram vet[]=new Nonogram[1];
            vet[0]=copia_nonogram();
            return vet;
        }
        if(assurdo())
            return new Nonogram[0];
        Nonogram vet[]=new Nonogram[0];
        for(int i=0;i<altezza;i++){
            for(int j=0;j<base;j++)
                if(get_cella(i,j).get_carattere()=="   "){
                    Nonogram nonogram1=copia_nonogram();
                    Nonogram nonogram2=copia_nonogram();
                    nonogram1.set_cella(i,j," X ");
                    nonogram2.set_cella(i,j," "+(char)3+" ");
                    vet=Metodi.aggiungo_vet_al_vettore(vet,nonogram1.tentativi());
                    vet=Metodi.aggiungo_vet_al_vettore(vet,nonogram2.tentativi());
                    return vet;
                }
        }
        return new Nonogram[0];//non ci arriva mai qua
    }
}