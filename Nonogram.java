//import java.rmi.registry.RegistryHandler;
import java.util.Scanner;

//import javax.lang.model.util.ElementScanner14;
/**
 * An object of Nonogram class represent a Nonogram.
 * Any Nonogram have:
 * 1)a matrix of cells.(it represents all cells of the nonogram)
 */
public class Nonogram{
    public static void main(String[] args)
    {
        Nonogram meme=Metodi.creo_nonogram(1);
        meme.stampo();
        meme.risolvo();
        meme.stampo();
    }
    final private Cella celle[][];
    /**
     * This vector represents the rows of the nonogram
     */
    final private Settore vet_righe[];
    /**
     * This vector represents the columns of the nonogram
     */
    final private Settore vet_colonne[];
    final private int base;
    final private int altezza;

    public Cella[][] get_celle(){
        return celle;
    }

    public Settore get_riga(int index){
        try{
            return vet_righe[index];
        }catch(NullPointerException e){
            throw new NullPointerException("vet_righe is null.");
        }catch(ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException("the index "+index+" is not correct because the nonogram has "+vet_righe.length+" rows");
        }
    }

    public Settore get_colonna(int index){
        try{
            return vet_colonne[index];
        }catch(NullPointerException e){
            throw new NullPointerException("vet_colonne is null.");
        }catch(ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException("the index "+index+" is not correct because the nonogram has "+vet_righe.length+" columns");
        }
    }

   

    public int get_base(){
        return base;
    }
    public int get_altezza(){
        return altezza;
    }
    /**
     * Il metodo non controlla che celle sia una matrice quadrata di celle con attributi riga/colonna coincidenti
     * con l'indice che ha la cella nella matrice celle[][].
     * Inoltre le celle dei settori devono coincidere con le celle di celle,
     * Il metodo non controlla che i parametri siano diversi da null
     * @param vet_righe
     * @param vet_colonne
     * @param celle
     */
    public Nonogram(Settore vet_righe[],Settore vet_colonne[],Cella celle[][]){
        this.vet_righe=vet_righe;
        this.vet_colonne=vet_colonne;
        this.celle=celle;
        this.base=celle[0].length;
        this.altezza=celle.length;

    }
    public Nonogram(int numeri_righe[][],int numeri_colonne[][],Cella celle[][]){
        this.celle=celle;
        this.vet_righe=new Settore[numeri_righe.length];
        this.vet_colonne=new Settore[numeri_colonne.length];  
        for(int i=0;i<numeri_righe.length;i++)
            vet_righe[i]=new Settore(Enum.tipo_settore.riga,i,numeri_righe[i],Metodi.get_riga(i, celle));
        for(int i=0;i<numeri_colonne.length;i++)
            vet_colonne[i]=new Settore(Enum.tipo_settore.colonna,i,numeri_colonne[i],Metodi.get_colonna(i, celle));
        this.base=numeri_colonne.length;
        this.altezza=numeri_righe.length;
    }
    public Nonogram(int numeri_righe[][],int numeri_colonne[][]){
        this(numeri_righe,numeri_colonne,Metodi.creo_tabella_di_celle(numeri_colonne.length, numeri_righe.length));
    }
    //METODI DI CLASSE
    //Ritorna il numero di blocchi che ha la riga con più blocchi
    public int massimo_righe(){
        int max=-1;
        for(int i=0;i<vet_righe.length;i++)
            if(max<vet_righe[i].get_numeri().length)
                max=vet_righe[i].get_numeri().length;
        return max;
    }
    //ritorna il numero di blocchi che ha la colonna con più blocchi
    public int massimo_colonne(){
        int max=-1;
        for(int i=0;i<vet_colonne.length;i++)
            if(max<vet_colonne[i].get_numeri().length)
                max=vet_colonne[i].get_numeri().length;
        return max;
    }

    public void stampo(int inutile){
        for(int i=0;i<altezza;i++){
            System.out.print("Riga "+i+": ");
            Metodi.stampo(vet_righe[i].get_numeri());
        }
        for(int i=0;i<base;i++){
            System.out.print("colonna "+i+": ");
            Metodi.stampo(vet_colonne[i].get_numeri());
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
                System.out.print(celle[i][j].get_carattere().toString());
            System.out.println("");
        }
    }
    public boolean finito(){
        for(int i=0;i<altezza;i++)
            for(int j=0;j<base;j++)
                if(celle[i][j].get_carattere()==Enum.tipo_carattere.vuoto)
                    return false;
        return true;
    }
    public int[][] get_numeri_righe(){
        int numeri_righe[][]=new int[altezza][0];
        for(int i=0;i<altezza;i++)
            numeri_righe[i]=vet_righe[i].get_numeri();
        return numeri_righe;
    }
    public int[][] get_numeri_colonne(){
        int numeri_colonne[][]=new int[base][0];
        for(int i=0;i<base;i++)
            numeri_colonne[i]=vet_colonne[i].get_numeri();
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
    public Nonogram copia(){
        Nonogram risultato=new Nonogram(get_numeri_righe(),get_numeri_colonne());
        for(int i=0;i<altezza;i++)
            for(int j=0;j<base;j++)
                switch(celle[i][j].get_carattere()){
                    case blocco:
                        risultato.get_celle()[i][j].set_blocco();
                        break;
                    case x:
                        risultato.get_celle()[i][j].set_x();
                        break;
                    case vuoto:
                        break;
                }
        return risultato;
    }
    //TECNICHE RISOLUTIVE
    public boolean risolvo(){
        for(int i=0;i<altezza;i++)
            if(vet_righe[i].tecnica(this)){
                if(finito())
                    return true;
                return risolvo();
            }
        for(int i=0;i<base;i++)
            if(vet_colonne[i].tecnica(this)){
                if(finito())
                    return true;
                return risolvo();
            }
        return false;
    }
    public Nonogram[] tentativi(){
        risolvo();
        if(finito()){
            Nonogram vet[]=new Nonogram[1];
            vet[0]=copia();
            return vet;
        }
        if(assurdo())
            return new Nonogram[0];
        Nonogram vet[]=new Nonogram[0];
        for(int i=0;i<altezza;i++){
            for(int j=0;j<base;j++)
                if(get_celle()[i][j].get_carattere()==Enum.tipo_carattere.vuoto){
                    Nonogram nonogram1=copia();
                    Nonogram nonogram2=copia();
                    nonogram1.get_celle()[i][j].set_x();;
                    nonogram2.get_celle()[i][j].set_blocco();;
                    vet=Metodi.aggiungo_vet_al_vettore(vet,nonogram1.tentativi());
                    vet=Metodi.aggiungo_vet_al_vettore(vet,nonogram2.tentativi());
                    return vet;
                }
        }
        return new Nonogram[0];//non ci arriva mai qua
    }
}