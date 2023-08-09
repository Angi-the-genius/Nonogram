import java.util.Scanner;
import javax.imageio.spi.ImageReaderWriterSpi;
import javax.lang.model.util.ElementScanner14;


import java.util.InputMismatchException;
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
    /**
     * The base attribute represents the length of the nonogram's base.
     * This is also equals to the number of nonogram's columns.
     */
    private int base;
    /**
     * The altezza attribute represents the length of the nonogram's altezza.
     * This is also equals to the number of nonogram's rows.
     */
    private int altezza;    
    /**
     * This integer vector contains numbers indicating the blocks of the invoking object.
     */
    private int numeri[];
    /**
     * type tells us if the invoking object represents a row or if it represents a column.
     */
    private Enum.tipo_settore type=null;
    /**
     * The index attribute represent the index of the sector.
     * This number is in {1,2,...,altezza} if the sector is a row.
     * This number is in {1,2,...,base} if the sector is a column
     */    
    private int index;
    /**
     * The vector celle represents the cells that are contained in the sector.
     * If type=row then celle[0] is the cell at the left.
     * If type=column then cell[0] is the cell at the top
     */
    private Cella celle[];
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

    public void set_numeri(int numeri[]){//se trova 0 in numeri non inserisce più nullat
        this.numeri=numeri;     
    }

    public Enum.tipo_settore get_type(){
        return type;
    }
    public void set_type(Enum.tipo_settore type){
        this.type=type;
    }

    public void set_index(int index){
        this.index=index;
    }
    public int get_index(){
        return index;
    }
    
    /**
     * The methods returns the nonogram's cells that are still white
     * @return only the empty cells
     */
    public Cella[] get_celle_vuote(){
        Cella result[]=new Cella[celle.length];
        int cont=0;
        switch(type){
            case riga:
                for(int i=0;i<celle.length;i++)
                    if(celle[i].get_ce_nella_riga())
                        result[cont++]=celle[i];
                break;
            case colonna:
                for(int i=0;i<celle.length;i++)
                    if(celle[i].get_ce_nella_riga())
                        result[cont++]=celle[i];
                break;
        }
        return Metodi.accorcio_lunghezza(result, cont);
    }
    public Cella[] get_celle(int inutile){
        return celle;
    }
    public Cella get_cella_vuota(int index){//non ritorna celle[index] ma get_celle_vuote()[index]
        return get_celle_vuote()[index];
    }
    public Cella get_cella(int index,int inutile){
        return get_celle(inutile)[index];
    }
    public void set_celle(Cella celle[]){
        this.celle=celle;
    }
    public void set_cella(String carattere,int index){
        get_cella_vuota(index).set_carattere(carattere);
    }
    public void set_cella(int posiz,int index){
        get_cella_vuota(index).set_posiz(posiz);
    }
    public void set_cella(Cella cella,int index){
        //get_cella_vuota(index)=cella;questo non si puo fare quimdi bisogna ripetere get-celle()
        int cont=0;
        Cella vettore_di_celle[]=new Cella[celle.length];
        for(int i=0;i<celle.length;i++)
            if(type==Enum.tipo_settore.riga){
                if(celle[i].get_ce_nella_riga())
                    vettore_di_celle[cont++]=celle[i];
            }
            else if(type==Enum.tipo_settore.colonna){
                if(celle[i].get_ce_nella_colonna())
                    vettore_di_celle[cont++]=celle[i];
            }
            else
                System.out.println("Errore, type sbagaliato in set_cella()");
        for(int i=0;i<celle.length;i++)
            if(celle[i]==vettore_di_celle[index]){
                celle[i]=cella;
                i=celle.length;
            }
    }
    public void set_cella(String carattere,int posiz,int index){
        set_cella(carattere,index);
        set_cella(posiz,index);
    }
    public Settore(int index,Enum.tipo_settore type,Cella nonogram[][],int base,int altezza){
        Cella celle[]=new Cella[0];
        if(type==Enum.tipo_settore.riga)
            celle=Metodi.get_celle_riga(index,nonogram);
        else if(type==Enum.tipo_settore.colonna)
            celle=Metodi.get_celle_colonna(index,nonogram);
        costruttore_settore(index,type,celle,base,altezza);
    }
    public Settore(int index,Enum.tipo_settore type,Cella celle[],int base,int altezza){//pone numeri[]=new int[100]
        costruttore_settore(index,type,celle,base,altezza);
    }
    public Settore(int index,Enum.tipo_settore type,Cella celle[],int numeri[],int base,int altezza){
        this(index,type,celle,base,altezza);
        set_numeri(numeri);
    }
    public Settore(int index,Enum.tipo_settore type,Cella nonogram[][],int numeri[],int base,int altezza){
        this(index,type,nonogram,base,altezza);
        set_numeri(numeri);
    }
    public void costruttore_settore(int index,Enum.tipo_settore type,Cella celle[],int base,int altezza){
        set_index(index);
        set_type(type);
        set_base(base);
        set_altezza(altezza);
        if(type==Enum.tipo_settore.riga || type==Enum.tipo_settore.colonna)
            set_celle(celle);
        else{
            System.out.println("Errore nel costruttore Settore");
            return;
        }
        //System.out.println(type+" "+index+".\ncelle.length: "+this.celle.length+".\nget_celle_vuote().length: "+get_celle_vuote().length);
        set_numeri(new int[100]);//poi verrà accorciato
    }
    
    //METODI DI CLASSE
    public void tolgo_numero(int index){
        if(index>=numeri.length){
            System.out.println("Errore non posso togliere il numero nella posizione "+index+" nel metodo tolgo_numero()");
            return;
        }
        numeri=Metodi.rimuovo_elemento_nella_posizione(numeri, index); 
    }
    public void tolgo_celle(int numero){//toglie le prime numero celle
        for(int i=0;i<numero;i++)
            if(type==Enum.tipo_settore.riga)
                get_cella_vuota(0).tolgo_dalla_riga();
            else
                get_cella_vuota(0).tolgo_dalla_colonna();
    }
    public void leggo(){
        Scanner tastiera=new Scanner(System.in);
        System.out.println("Inserisci "+type+" numero "+index);
        for(int i=0;i<100;i++){
            numeri[i]=tastiera.nextInt();
            if(numeri[i]==0){
                numeri=Metodi.accorcio_lunghezza(numeri, i);
                i=99;
            }
        }
    }
    public void stampo(){
        System.out.print("Settore: "+type+" "+index+":");
        System.out.print("get_celle_vuote(): ");
        Metodi.stampo_posiz(get_celle_vuote());
        /*System.out.print("get_celle(0): ");
        Metodi.stampo_posiz(get_celle(0));
        System.out.print("numeri: ");
        Metodi.stampo(numeri);
        System.out.println("Carattere; ");
        Metodi.stampo_carattere(get_celle(0));*/     
    }
    /**
     * Ritorna quante celle occupano tutti i blocchetti del settore nel caso in cui sono compattati il più possibile
     * @return numeri[0]+...+numeri[numeri.length-1]+numeri.length-1
     */
    public int somma_numeri(){//ritorna i numeri sommati dopo aver inserito il minimo numero di spazi
        int somma=numeri.length-1;//numero di spazi
        for(int i=0;i<numeri.length;i++)
            somma=somma+numeri[i];
        return somma;
    }
    /*
    PENSO SIA DA CANCELLARE
    public int numero_massimo(){
        return Metodi.get_massimo(numeri);
    }*/
    /**
     * Il metodo ritorna un vettore contenente l'inidice della prima cella di ogni blocchetto presente nel settore
     * @return
     */
    public int[] get_blocchi(){
        int blocchi[]=new int[get_celle_vuote().length],cont=0;
        for(int i=0;i<get_celle_vuote().length;i++)
            if(get_cella_vuota(i).get_carattere().equals(" "+(char)3+" ")){
                blocchi[cont++]=i;
                while(i<get_celle_vuote().length-1 && get_cella_vuota(++i).get_carattere().equals(" "+(char)3+" "));
            }
        return Metodi.accorcio_lunghezza(blocchi, cont);
    }

    public int[] get_blocchi_x(){
        int blocchi_x[]=new int[get_celle_vuote().length],cont=0;
        for(int i=0;i<get_celle_vuote().length;i++){
            if(get_cella_vuota(i).get_carattere()==" X ")
                blocchi_x[cont++]=i;
            while(i<get_celle_vuote().length-1 && get_cella_vuota(++i).get_carattere()==" X ");
        }
        return Metodi.accorcio_lunghezza(blocchi_x,cont);
    }
    public int[] get_blocchi_x_length(){
        int blocchi_x[]=get_blocchi_x();
        int blocchi_x_length[]=new int[blocchi_x.length];
        for(int i=0;i<blocchi_x.length;i++){
            blocchi_x_length[i]=1;
            for(int j=blocchi_x[i]+1;get_cella_vuota(j).get_carattere()==" X ";j++)
                blocchi_x_length[i]++;
        }
        return blocchi_x_length;
    }
    public int get_spazio(){
        if(type==Enum.tipo_settore.riga || type==Enum.tipo_settore.colonna)
            return get_celle_vuote().length-somma_numeri();
        else{
            System.out.println("Errore in get_spazio.Type: "+type);
            return -1;
        }
    }
    public void copia_settore(Settore settore){//l'oggetto invocante avra gli attributi dello stesso valore di quelli di settore ma diversi
        set_base(settore.get_base());
        set_altezza(settore.get_altezza());
        set_type(settore.get_type());
        set_celle(settore.get_celle(0));
        set_index(settore.get_index());
        set_numeri(settore.get_numeri());
    }
    public Settore copia_settore(){//restituisce un settore con attributi dello stesso valore degli attributi del settore invocante
        Cella vet[]=new Cella[0];
        if(type==Enum.tipo_settore.riga)
            vet=new Cella[base];
        else if(type==Enum.tipo_settore.colonna)
            vet=new Cella[altezza];
        else{
            System.out.println("Errore nel metodo copia_settore");
            return null;
        }
        for(int i=0;i<vet.length;i++){
            vet[i]=new Cella(celle[i].get_carattere(),celle[i].get_posiz(),base,altezza);
            if(celle[i].get_ce_nella_riga()==false)
                vet[i].tolgo_dalla_riga();
            if(celle[i].get_ce_nella_colonna()==false)
                vet[i].tolgo_dalla_colonna();
        }
        return new Settore(index,type,vet,numeri,base,altezza);
    }
    public void catch_errore(String metodo){//ritorna true se type è sbagliato
        if(type!=Enum.tipo_settore.riga && type!=Enum.tipo_settore.colonna){
            System.out.println("errore nel metodo Settore."+metodo+". Type: "+type);
            System.exit(0);
        }
    }
    public boolean posso_inserire(int i,int blocco_length){//ritorna true se si puo inserire un blocco lungo blocco_length a partire dalla cella get_cella_vuota(i) come primo blocco del settore(prima della cella get_cella_vuota(i) ci devono essere tutte x)
        for(int j=0;j<i;j++)
            if(get_cella_vuota(j).get_carattere().equals(" "+(char)3+" ")){          
                //System.out.println("Non posso inserire blocco lungo "+blocco_length+" a partire dalla cella "+get_cella_vuota(i).get_posiz()+" perche alla cella "+get_cella_vuota(j).get_posiz()+" ce un cuore ma ci vorebbe una x");
                return false;
            }
        if(i+blocco_length<get_celle_vuote().length)
            if(get_cella_vuota(i+blocco_length).get_carattere().equals(" "+(char)3+" ")){
                //System.out.println("Non posso inserire blocco lungo "+blocco_length+" a partire dalla cella "+get_cella_vuota(i).get_posiz()+" perche alla cella "+get_cella(i+blocco_length).get_posiz()+" ce un cuore ma ci vorebbe una x");
                return false;
            }
        return posso_inserire(i,blocco_length,0);
    }
    public boolean posso_inserire(int i,int blocco_length,int inutile){//ritorna true se puo essere inserito un blocco lungo almreno blocco_length a partire dalla posizione i(il blocco non deve essere numeri[0])
        if(i+blocco_length>get_celle_vuote().length){
            //System.out.println("Non posso inserire blocco lungo "+blocco_length+" a parstire dalla cella "+i+" perchè non ci sta");
            return false;
        }
        for(int j=i;j<i+blocco_length;j++)
            if(get_cella_vuota(j).get_carattere()==" X "){
                //System.out.println("Non posso inserire blocco lungo "+blocco_length+" a partire dalla cella "+get_cella_vuota(i).get_posiz()+" perche alla cella "+get_cella_vuota(j).get_posiz()+" ce una x ma ci vorebbe un cuore");
                return false;
            }
        return true;
    }
    public int[] costruisco_temp(int i){
        /*se si puo inserire un blocco lungo blocco_length con prima cella i restituisce un vettore di  0 e 1 che
        rappresenta la configurazione del pezzo del settore a sinistra del blocco inserito(blocco inserito 
        e cella successiva sono incluse in temp)*/
        if(posso_inserire(i,numeri[0])==false)
            return new int[0];
        int temp[]=new int[i+numeri[0]];
        if(i+numeri[0]<get_celle_vuote().length)
            temp=new int[i+numeri[0]+1];
        for(int j=0;j<i;j++)//sicuramente get_cella_vuota(j).get_carattere()!=" "+(char)3+" "
            temp[j]=0;
        for(int j=i;j<i+numeri[0];j++)//sicuramente get_cella_vuota(j).get_carattere()!=" X "
            temp[j]=1;
        if(i+numeri[0]<get_celle_vuote().length)
            temp[i+numeri[0]]=0;
        return temp;
    }
    //TECNICHE RISOLUTIVE
    public int[] spazio_0(){//se è assurdo ritorna new int[0]
        int vet[]=new int[get_celle_vuote().length],cont=0;
        for(int i=0;i<numeri.length;i++){
            for(int j=0;j<numeri[i];j++)
                if(get_cella_vuota(cont).get_carattere()==" X ")
                    return new int[0];
                else
                    vet[cont++]=1;
            if(cont<get_celle_vuote().length)
                if(get_cella_vuota(cont).get_carattere().equals(" "+(char)3+" "))
                    return new int[0];
                else
                    vet[cont++]=0;
        }
        return vet;
    }
    /**
     * Questo metodo viene invocato quando sono finiti i numeri del settore e quindi tutte le celle dovrebbero essere vuote.
     * Il metodo ritorna tale configuarazione se è tutto corretto se no ritorna new int[0][0]
     * @return la configurazione del settore se è tutto corretto
     */
    public int[][] niente_numeri(){
        int vet[][]=new int[1][get_celle_vuote().length];
        int lunghezza=get_celle_vuote().length;
        for(int i=0;i<lunghezza;i++)
            if(get_cella_vuota(i).get_carattere().equals(" "+(char)3+" "))
                return new int[0][0];//non ci sono casi possibili con le configurazioni precedenti
            else
                vet[0][i]=0;
        return vet;
    }
    public int[][] spazio_0(int inutile){
        int vet[][]=new int[1][get_celle_vuote().length];
        vet[0]=spazio_0();
        if(vet[0].length==0)
            return new int[0][0];
        return vet;
    }
    public int[][] niente_blocchi(){
        //System.out.println("Non ci sono blocchi nel settore: entro in niente_blocchi()");
        int blocchi_x[]=get_blocchi_x();
        int blocchi_x_length[]=get_blocchi_x_length();
        int spazio=get_spazio();
        int vet[][]=new int[0][0];
        if(blocchi_x.length>0){
            vet=new int[0][0];
            for(int i=0;i<=spazio && i<=blocchi_x[0]-numeri[0];i++){//scorro in tutte le possibili configurazioni dove posso mettere blocco lungo numeri[0] prima della prima x
                System.out.println("inserisco blocco lungo "+numeri[0]+" a partire dalla cella "+i+" prima della x nella cella "+blocchi_x[0]);
                vet=Metodi.aggiungo_vet_al_vettore(vet,creo_configurazioni(i));//se non puo inserire il blocco tecnica ritorna new int[0][0] e vet non cambia
                System.out.println("vet:( restituito da tecnica("+i+","+numeri[0]+")");
                Metodi.stampo(vet);
            }
            for(int i=blocchi_x[0]+blocchi_x_length[0];i<=spazio;i++){
                System.out.println("inserisco blocco lungo "+numeri[0]+" a partire dalla cella "+i+" dopo della x nella cella "+blocchi_x[0]);
                vet=Metodi.aggiungo_vet_al_vettore(vet,creo_configurazioni(i));
                System.out.println("vet:( restituito da tecnica("+i+","+numeri[0]+")");
                Metodi.stampo(vet);
            }
        }else{//se entra qui il settore non contiene ne cuori ne x
            System.out.println("Non ci sono ne x ne cuori nel settore applico inserisco: ");
            for(int i=0;i<=spazio;i++){
                System.out.println("Inserisco blocco lungo "+numeri[0]+" a partire dalla cella "+i);
                vet=Metodi.aggiungo_vet_al_vettore(vet,creo_configurazioni(i));
                System.out.println("vet: ");
                Metodi.stampo(vet);
            }
        }
        return vet;
    }
    public int[][] creo_configurazioni(int i){//restituisce tutte le possibili configurazioni in cui è stato inserito un blocco lungo blocco_length la cui prima cella è in posizione i
        int temp1[]=costruisco_temp(i);//contiene la configurazione della parte del settore in cui è stato inserito il blocco

        int vet[][]=new int[0][0];
        Settore settore=copia_settore();
        settore.tolgo_celle(i+numeri[0]);
        if(i+numeri[0]<get_celle_vuote().length)
            settore.tolgo_celle(1);
        settore.tolgo_numero(0);
        if(settore.get_celle_vuote().length>0){
            int temp[][]=settore.creo_configurazioni();
            if(temp.length>0){
                int vet1[][]=Metodi.creo_ipotesi(temp1,temp);
                vet=Metodi.aggiungo_vet_al_vettore(vet,vet1);
            }
        }else
            vet=Metodi.aggiungo_vet_al_vettore(vet,temp1);
        return vet;
    }
    public int[][] creo_configurazioni(){//se non ci sono casi possibili ritorna new int[0][0];

        if(numeri.length==0)
            return niente_numeri(); 
        int vet[][]=new int[0][get_celle_vuote().length];//contiene 1 o 0 in ogni riga c'è una possibile configurazione

        for(int i=0;i<=get_spazio();i++){
            int temp[][]=creo_configurazioni(i);
            vet=Metodi.aggiungo_vet_al_vettore(vet,temp);
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
            System.out.println("Il nonogram è impossibile perche' la "+type+" "+index+"  non ha configurazioni posibili");
            System.exit(0);
        }
        System.out.println("possibili configurazioni della "+type+" "+index+":");
        Metodi.stampo(vet);
        Cella vettore_di_celle[]=new Cella[get_celle_vuote().length];
        int somma[]=new int[vettore_di_celle.length],cont=0;
        for(int i=0;i<somma.length;i++){
            somma[i]=0;
            for(int j=0;j<vet.length;j++)
                somma[i]+=vet[j][i];
            if(somma[i]==0 && get_cella_vuota(i).get_carattere()!=" X "){//nell'i-esima cella va inserito una x 
                get_cella_vuota(i).set_x();
                vettore_di_celle[cont++]=get_cella_vuota(i);
            }
            if(somma[i]==vet.length && get_cella_vuota(i).get_carattere()!=" "+(char)3+" "){
                get_cella_vuota(i).set_blocco();
                vettore_di_celle[cont++]=get_cella_vuota(i);
            }
        }
        vettore_di_celle=Metodi.accorcio_lunghezza(vettore_di_celle,cont);
        if(cont>0){
            System.out.print("Inserito cuore o x nella "+type+" "+index+" nelle celle: ");
            Metodi.stampo_posiz(vettore_di_celle);
        }
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
        if(cont>0)
            return true;
        return false;
    }
}