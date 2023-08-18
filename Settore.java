import java.util.Scanner;

/**
 * An object of the Settore class represents a single row or a single column of the Nonogram.
 * The dinamical variable type tell us if the object represents a row or if it represents a column.
 * The elements of dinamical variable numeri represents the length of the block inside the sector.
 * numeri[0] represents the length of the first block()
 * index tell us the positiion of the sector in the Nonogram.
 * If index=0 and type=row then the sector is the row at the top
 */
public class Settore{
    public static void main(String[] args)
    {        
        Nonogram nonogram=Metodi.creo_nonogram(1);
        nonogram.stampo(0);
        nonogram.stampo();
        Nonogram vet[]=nonogram.tentativi();
        System.out.println("Il numero di nonogram possibili è: "+vet.length);
        nonogram.stampo();

    }
    final private int length;  
    /**
     * This integer vector contains numbers indicating the blocks of the invoking object.
     */
    final private int numeri[];
    /**
     * type tells us if the invoking object represents a row or if it represents a column.
     */
    final private Enum.tipo_settore type;
    /**
     * The index attribute represent the index of the sector.
     * This number is in {1,2,...,altezza} if the sector is a row.
     * This number is in {1,2,...,base} if the sector is a column
     */    
    final private int index;
    /**
     * The vector celle represents the cells that are contained in the sector.
     * If type=row then celle[0] is the cell at the left.
     * If type=column then cell[0] is the cell at the top
     */
    final private Cella celle[];
    
    public int getLength() {
        return length;
    }

    public int[] get_numeri(){
        return numeri;
    }
    /**
     * The method returns the i-th number of invoking object's numeri attribute
     * @param i index of the returned number
     * @return this.numeri[i]
     */
    public int get_numero(int i){
        try{
            return numeri[i];
        }catch(NullPointerException e){
            System.out.println("Errore: L'attributo numeri è nullo");
            throw new NullPointerException();
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Errore: i non compatibile con la lunghezza dell'attributo numeri.");
            System.out.println("i parameter: "+i);
            System.out.println("numeri.length: "+numeri.length);
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public Enum.tipo_settore get_type(){
        return type;
    }

    public int get_index(){
        return index;
    }

    public Cella[] get_celle(){
        return celle;
    }

    public Cella get_cella(int i){
        try{
            return celle[i];
        }catch(NullPointerException e){
            throw new NullPointerException("La variabile celle e' null.");
        }catch(ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException("L'indice i non è compatibile con la lunghezza di celle.\ni: "+i+"\ncelle.length: "+celle.length);
        }
    }

    public Settore(Enum.tipo_settore type,int index,int numeri[],Cella celle[]){
        this.type=type;
        this.index=index;
        this.numeri=numeri;
        this.celle=celle;
        this.length=celle.length;
    }

    public Settore(Enum.tipo_settore type,int index,int numeri[],Cella celle[][]){
        this(type,index,numeri,Metodi.get_settore(type,index,celle));
    }
    
    public void stampo(){
        System.out.print("Settore: "+type+" "+index+": |");
        for(int i=0;i<length;i++)
            System.out.print(celle[i].get_carattere().toString());
        System.out.println("|");
        System.out.print("numeri: ");
        Metodi.stampo(numeri);   
    }
    public Settore copia(){//restituisce un settore con attributi dello stesso valore degli attributi del settore invocante
        return new Settore(type,index,Metodi.copia(numeri),Metodi.copia(celle));
    }

    /**
     * Ritorna quante celle occupano tutti i blocchetti del settore nel caso in cui sono compattati il più possibile
     * @return numeri[0]+...+numeri[numeri.length-1]+numeri.length-1
     */
    private int somma_numeri(){//ritorna i numeri sommati dopo aver inserito il minimo numero di spazi
        int somma=numeri.length-1;//numero di spazi
        for(int i=0;i<numeri.length;i++)
            somma=somma+numeri[i];
        return somma;
    }

    

   
    /**
     * Il metodo ritorna il numero di celle in cui può essere messa la prima cella del primo blocco.
     * Questo calcolo viene fatto assumendo che il settore sia completamente bianco.
     * E' compito del metodo posso_inserire capire se effettivamente il primo blocco può iniziare in una determinata cella
     * @return
     */
    private int get_spazio(){
        return length-somma_numeri()+1;
    }

    
    /**

     * @param i
     * @return
     */
    /**
     * Il metodo ritorna true se si può inserire il primo blocco alla cella i.
     * Il metodo non controlla che ci sia abbastanza spazio per inserire i blocchi dopo.
     * Quindi prima di invocare questo metodo 
     * è bene controllare che 0<=i<get_spazio().
     * Il metodo non fa questo controllo per velocizzare tutto
     * @param i jhvjhv
     * @return
     */
    private boolean posso_inserire(int i){
        for(int j=0;j<i;j++)
            if(celle[j].get_carattere()==Enum.tipo_carattere.blocco)
                return false;//Non si può inserire il blocco perchè prima del blocco ci devono essere solo bianchi o x ma c'è un blocchetto alla j-esima cella del settore
        for(int j=i;j<i+numeri[0];j++)
            if(celle[j].get_carattere()==Enum.tipo_carattere.x)
                return false;//Non si può inserire il blocco perchè dove ci vorrebbe un blocco nero c'è una x
        if(length>i+numeri[0] && celle[i+numeri[0]].get_carattere()==Enum.tipo_carattere.blocco)
            return false;//Non si può inserire il blocco perchè dove ci andrebbe la x di delimitazione(quella a dx per le righe e sotto per le colonne) ci vorrebbe una x ma c'è un blocco
        return true;
    }
    /**
     * Il metodo ritorna la configurazione(sotto forma di 0 e 1) di quella parte del settore coinvolta 
     * nell'inserimento del primo blocchetto. Eventualmente il vettore ritornato termina per 0 se il primo blocchetto
     * non ha ultima cella corrispondente all'ultima cella del settore.
     * Il primo blocchetto viene inserito con prima cella nella i-esima cella del settore
     * Non viene controllato se effettivamente si può fare una tale cosa.
     * @param i indice della cella dalla quale partira il primo blocco
     * @return configurazione 0 0 1 1 1 1 0 ( caso i=2 , numeri[0]=4 , length>i+numeri[0] )
     */
    private int[] costruisco_prima_parte(int i){
        int temp[]=new int[i+numeri[0]];
        if(i+numeri[0]<length)
            temp=new int[i+numeri[0]+1];
        for(int j=0;j<i;j++)//sicuramente get_cella_vuota(j).get_carattere()!=" "+(char)3+" "
            temp[j]=0;
        for(int j=i;j<i+numeri[0];j++)//sicuramente get_cella_vuota(j).get_carattere()!=" X "
            temp[j]=1;
        if(i+numeri[0]<length)
            temp[i+numeri[0]]=0;
        return temp;
    }
    //TECNICHE RISOLUTIVE
    
    /**
     * Questo metodo viene invocato quando sono finiti i numeri del settore e quindi tutte le celle dovrebbero essere vuote.
     * Il metodo ritorna tale configuarazione se è tutto corretto se no ritorna new int[0][0]
     * @return la configurazione del settore se è tutto corretto
     */
    public int[][] niente_numeri(){
        int vet[][]=new int[1][length];
        for(int i=0;i<length;i++)
            if(celle[i].get_carattere()==Enum.tipo_carattere.blocco)
                return new int[0][0];//non ci sono casi possibili con le configurazioni precedenti
            else
                vet[0][i]=0;
        return vet;
    }
    
    /**
     * Il metodo ritorna il settore che si ottiene dopo aver tolto le prime i+numeri[0]+1 celle dal settore.
     * Se length==i+numeri[0] vengono ritornato un settore con 0 celle
     * Il settore ritornato non avrà in numeri il primo numero dell'oggetto invocante.
     * Il metodo, essendo privato, non fa un sacco di controlli.
     * Questo metodo va invocato quando effettivamente è possibile inserire il primo blocco a partire dalla cella i.
     * @param i indice della cella dove viene posizionato il primo blocco
     * @return settore che si ottiene dall'oggetto invocante dopo aver tolto le celle coinvolte nell'inserimento del primo blocco.
     */
    private Settore creo_settorino(int i){
        if(length>i+numeri[0]){
            return new Settore(type,index,Metodi.rimuovo_primo_elemento(numeri),Metodi.rimuovo_celle(celle,i+numeri[0]+1));
        }else if(length==i+numeri[0]){
            return new Settore(type,index,new int[0],new Cella[0]);
        }else
            throw new RuntimeException("Non è possibile inserire il primo blocco alla cella i(="+i+") perche' il settore è troppo corto.\nIl primo blocco sforerebbe(senza contare gli altri)");
    }
    /**
     * 
     * @param i deve essere <get_spazio()
     * @return
     */
    private int[][] creo_configurazioni(int i){//restituisce tutte le possibili configurazioni in cui è stato inserito un blocco lungo blocco_length la cui prima cella è in posizione i
        if(type==Enum.tipo_settore.colonna && index==6 && length==15)
            System.out.println("Entro in creo_configurazioni.i: "+i);

        int temp1[]=costruisco_prima_parte(i);//contiene la configurazione della parte del settore in cui è stato inserito il blocco

        int risultato[][]=new int[0][0];
        Settore settore=creo_settorino(i);
        if(settore.length>0){
            int temp[][]=settore.creo_configurazioni();
            if(temp.length>0){
                int vet1[][]=Metodi.creo_ipotesi(temp1,temp);
                risultato=Metodi.aggiungo_vet_al_vettore(risultato,vet1);
            }
        }else
            risultato=Metodi.aggiungo_vet_al_vettore(risultato,temp1);
        return risultato;
    }
    public int[][] creo_configurazioni(){//se non ci sono casi possibili ritorna new int[0][0];

        if(numeri.length==0)
            return niente_numeri(); 
        int vet[][]=new int[0][length];//contiene 1 o 0 in ogni riga c'è una possibile configurazione

        for(int i=0;i<get_spazio();i++){
            if(posso_inserire(i)){
                int temp[][]=creo_configurazioni(i);
                if(type==Enum.tipo_settore.colonna && index==6 && length==15){
                    System.out.println("Aggiungo seguente matrice");
                    Metodi.stampo(temp);
                }
                vet=Metodi.aggiungo_vet_al_vettore(vet,temp);
            }
        }
        return vet;
    }
    /**
     * Questo metodo ritorna true se è stato inserito qualche segno nel settore dopo l'appicazione della tecnica
     * @param nonogram
     * @param numeri_righe
     * @param numeri_colonne
     * @return
     */
    public boolean tecnica(Nonogram nonogram){
        System.out.println("Entro in tecnica alla "+type+" "+index);
        Scanner tastiera=new Scanner(System.in);
        int vet[][]=creo_configurazioni();
        if(vet.length==0){
            throw new RuntimeException("La "+type+" di indice "+index+" non ha configurazioni possibili");
        }
        System.out.println("possibili configurazioni della "+type+" "+index+":");
        Metodi.stampo(vet);
        Cella vettore_di_celle[]=new Cella[length];
        int somma[]=new int[length],cont=0;
        for(int i=0;i<somma.length;i++){
            somma[i]=0;
            for(int j=0;j<vet.length;j++)
                somma[i]+=vet[j][i];
            if(somma[i]==0 && celle[i].get_carattere()!=Enum.tipo_carattere.x){//nell'i-esima cella va inserito una x 
                celle[i].set_x();
                vettore_di_celle[cont++]=celle[i];
            }
            if(somma[i]==vet.length && celle[i].get_carattere()!=Enum.tipo_carattere.blocco){
                celle[i].set_blocco();
                vettore_di_celle[cont++]=celle[i];
            }
        }
        if(cont>0){
            vettore_di_celle=Metodi.accorcio_lunghezza(vettore_di_celle,cont);
            System.out.print("Inserito cuore o x nella "+type+" "+index+" nelle celle: ");
            Metodi.stampo_posiz(vettore_di_celle,nonogram.get_base());
            switch(type){
                case riga:
                    for(int i=0;i<cont;i++){
                        System.out.println("");
                        nonogram.stampo();
                        if(nonogram.finito())
                            return true;
                        tastiera.nextInt();
                        nonogram.get_colonna(vettore_di_celle[i].get_colonna()).tecnica(nonogram);
                    }
                    break;
                case colonna:
                    for(int i=0;i<cont;i++){
                        System.out.println("");
                        nonogram.stampo();
                        if(nonogram.finito())
                            return true;
                        tastiera.nextInt();
                        nonogram.get_riga(vettore_di_celle[i].get_riga()).tecnica(nonogram);
                    }
                    break;
            }
            return true;
        }else
            return false;
    }
}