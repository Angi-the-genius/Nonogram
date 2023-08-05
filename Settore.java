import java.util.Scanner;
import javax.imageio.spi.ImageReaderWriterSpi;
import javax.lang.model.util.ElementScanner14;
import java.util.InputMismatchException;
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
    private int numeri[];//contiene i numeri che stanno a sx delle righe e in alto alle colonne
    public int[] get_numeri(){
        return numeri;
    }
    public int get_numero(int i){
        if(i>=numeri.length){
            System.out.println("Errore invocato get_numero con i troppo alto");
            return 0;//0 non puo essere fra numeri perchè non ha senso
        }
        return numeri[i];
    }
    public void set_numero(int numero,int i){
        if(get_numero(i)!=0)
            numeri[i]=numero;
    }
    public void set_numeri(int numeri[]){//se trova 0 in numeri non inserisce più nullat
        this.numeri=numeri;     
    }
    
    private String type;
    public String get_type(){
        return type;
    }
    public void set_type(String type){
        this.type=type;
    }
    
    private int index;
    public void set_index(int index){
        this.index=index;
    }
    public int get_index(){
        return index;
    }
    
    private Cella celle[];
    public Cella[] get_celle(){//non ritorna tutte le celle ma solo quelle dove non è già stato inserito niente
        Cella vettore_di_celle[]=new Cella[celle.length];
        int cont=0;
        for(int i=0;i<celle.length;i++)
            if(type=="Riga"){
                if(celle[i].get_ce_nella_riga())
                    vettore_di_celle[cont++]=celle[i];
            }
            else if(type=="Colonna"){
                if(celle[i].get_ce_nella_colonna())
                    vettore_di_celle[cont++]=celle[i];
            }
            else{
                System.out.println("Errore, type sbagaliato in get_celle()");
                return new Cella[0];
            }
        return Metodi.accorcio_lunghezza(vettore_di_celle, cont);
    }
    public Cella[] get_celle(int inutile){
        return celle;
    }
    public Cella get_cella(int index){//non ritorna celle[index] ma get_celle()[index]
        return get_celle()[index];
    }
    public Cella get_cella(int index,int inutile){
        return get_celle(inutile)[index];
    }
    public void set_celle(Cella celle[]){
        this.celle=celle;
    }
    public void set_cella(String carattere,int index){
        get_cella(index).set_carattere(carattere);
    }
    public void set_cella(int posiz,int index){
        get_cella(index).set_posiz(posiz);
    }
    public void set_cella(Cella cella,int index){
        //get_cella(index)=cella;questo non si puo fare quimdi bisogna ripetere get-celle()
        int cont=0;
        Cella vettore_di_celle[]=new Cella[celle.length];
        for(int i=0;i<celle.length;i++)
            if(type=="Riga"){
                if(celle[i].get_ce_nella_riga())
                    vettore_di_celle[cont++]=celle[i];
            }
            else if(type=="Colonna"){
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
    public Settore(int index,String type,Cella nonogram[][],int base,int altezza){
        Cella celle[]=new Cella[0];
        if(type=="Riga")
            celle=Metodi.get_celle_riga(index,nonogram);
        else if(type=="Colonna")
            celle=Metodi.get_celle_colonna(index,nonogram);
        costruttore_settore(index,type,celle,base,altezza);
    }
    public Settore(int index,String type,Cella celle[],int base,int altezza){//pone numeri[]=new int[100]
        costruttore_settore(index,type,celle,base,altezza);
    }
    public Settore(int index,String type,Cella celle[],int numeri[],int base,int altezza){
        this(index,type,celle,base,altezza);
        set_numeri(numeri);
    }
    public Settore(int index,String type,Cella nonogram[][],int numeri[],int base,int altezza){
        this(index,type,nonogram,base,altezza);
        set_numeri(numeri);
    }
    public void costruttore_settore(int index,String type,Cella celle[],int base,int altezza){
        set_index(index);
        set_type(type);
        set_base(base);
        set_altezza(altezza);
        if(type=="Riga" || type=="Colonna")
            set_celle(celle);
        else{
            System.out.println("Errore nel costruttore Settore");
            return;
        }
        //System.out.println(type+" "+index+".\ncelle.length: "+this.celle.length+".\nget_celle().length: "+get_celle().length);
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
            if(type=="Riga")
                get_cella(0).tolgo_dalla_riga();
            else
                get_cella(0).tolgo_dalla_colonna();
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
        System.out.print("get_celle(): ");
        Metodi.stampo_posiz(get_celle());
        /*System.out.print("get_celle(0): ");
        Metodi.stampo_posiz(get_celle(0));
        System.out.print("numeri: ");
        Metodi.stampo(numeri);
        System.out.println("Carattere; ");
        Metodi.stampo_carattere(get_celle(0));*/     
    }
    public int somma_numeri(){//ritorna i numeri sommati dopo aver inserito il minimo numero di spazi
        int somma=numeri.length-1;//numero di spazi
        for(int i=0;i<numeri.length;i++)
            somma=somma+numeri[i];
        return somma;
    }
    public int numero_massimo(){
        return Metodi.get_massimo(numeri);
    }
    public int[] get_blocchi(){//ritorna un vettore contenente l'inidice della prima cella di ogni blocchetto presente nel settore
        int blocchi[]=new int[get_celle().length],cont=0;
        for(int i=0;i<get_celle().length;i++)
            if(get_cella(i).get_carattere()==" "+(char)3+" "){
                blocchi[cont++]=i;
                while(i<get_celle().length-1 && get_cella(++i).get_carattere()==" "+(char)3+" ");
            }
        return Metodi.accorcio_lunghezza(blocchi, cont);
    }
    public int[] get_blocchi_length(){
        int blocchi[]=get_blocchi();
        int blocchi_length[]=new int[blocchi.length];
        for(int i=0;i<blocchi.length;i++){
            blocchi_length[i]=1;
            for(int j=blocchi[i]+1;j<get_celle().length && get_cella(j).get_carattere()==" "+(char)3+" ";j++)
                blocchi_length[i]++;
        }
        return blocchi_length;
    }
    public int[] get_blocchi_x(){
        int blocchi_x[]=new int[get_celle().length],cont=0;
        for(int i=0;i<get_celle().length;i++){
            if(get_cella(i).get_carattere()==" X ")
                blocchi_x[cont++]=i;
            while(i<get_celle().length-1 && get_cella(++i).get_carattere()==" X ");
        }
        return Metodi.accorcio_lunghezza(blocchi_x,cont);
    }
    public int[] get_blocchi_x_length(){
        int blocchi_x[]=get_blocchi_x();
        int blocchi_x_length[]=new int[blocchi_x.length];
        for(int i=0;i<blocchi_x.length;i++){
            blocchi_x_length[i]=1;
            for(int j=blocchi_x[i]+1;get_cella(j).get_carattere()==" X ";j++)
                blocchi_x_length[i]++;
        }
        return blocchi_x_length;
    }
    public int get_spazio(){
        if(type=="Riga" || type=="Colonna")
            return get_celle().length-somma_numeri();
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
        if(type=="Riga")
            vet=new Cella[base];
        else if(type=="Colonna")
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
        if(type!="Riga" && type!="Colonna"){
            System.out.println("errore nel metodo Settore."+metodo+". Type: "+type);
            System.exit(0);
        }
    }
    public boolean posso_inserire(int i,int blocco_length){//ritorna true se si puo inserire un blocco lungo blocco_length a partire dalla cella get_cella(i) come primo blocco del settore(prima della cella get_cella(i) ci devono essere tutte x)
        for(int j=0;j<i;j++)
            if(get_cella(j).get_carattere()==" "+(char)3+" "){          
                //System.out.println("Non posso inserire blocco lungo "+blocco_length+" a partire dalla cella "+get_cella(i).get_posiz()+" perche alla cella "+get_cella(j).get_posiz()+" ce un cuore ma ci vorebbe una x");
                return false;
            }
        if(i+blocco_length<get_celle().length)
            if(get_cella(i+blocco_length).get_carattere()==" "+(char)3+" "){
                //System.out.println("Non posso inserire blocco lungo "+blocco_length+" a partire dalla cella "+get_cella(i).get_posiz()+" perche alla cella "+get_cella(i+blocco_length).get_posiz()+" ce un cuore ma ci vorebbe una x");
                return false;
            }
        return posso_inserire(i,blocco_length,0);
    }
    public boolean posso_inserire(int i,int blocco_length,int inutile){//ritorna true se puo essere inserito un blocco lungo almreno blocco_length a partire dalla posizione i(il blocco non deve essere numeri[0])
        if(i+blocco_length>get_celle().length){
            //System.out.println("Non posso inserire blocco lungo "+blocco_length+" a parstire dalla cella "+i+" perchè non ci sta");
            return false;
        }
        for(int j=i;j<i+blocco_length;j++)
            if(get_cella(j).get_carattere()==" X "){
                //System.out.println("Non posso inserire blocco lungo "+blocco_length+" a partire dalla cella "+get_cella(i).get_posiz()+" perche alla cella "+get_cella(j).get_posiz()+" ce una x ma ci vorebbe un cuore");
                return false;
            }
        return true;
    }
    public int[] costruisco_temp(int i,int blocco_length){
        /*se si puo inserire un blocco lungo blocco_length con prima cella i restituisce un vettore di  0 e 1 che
        rappresenta la configurazione del pezzo del settore a sinistra del blocco inserito(blocco inserito 
        e cella successiva sono incluse in temp)*/
        if(posso_inserire(i,blocco_length)==false)
            return new int[0];
        int temp[]=new int[i+blocco_length];
        if(i+blocco_length<get_celle().length)
            temp=new int[i+blocco_length+1];
        for(int j=0;j<i;j++)//sicuramente get_cella(j).get_carattere()!=" "+(char)3+" "
            temp[j]=0;
        for(int j=i;j<i+blocco_length;j++)//sicuramente get_cella(j).get_carattere()!=" X "
            temp[j]=1;
        if(i+blocco_length<get_celle().length)
            temp[i+blocco_length]=0;
        return temp;
    }
    //TECNICHE RISOLUTIVE
    public int[] spazio_0(){//se è assurdo ritorna new int[0]
        int vet[]=new int[get_celle().length],cont=0;
        for(int i=0;i<numeri.length;i++){
            for(int j=0;j<numeri[i];j++)
                if(get_cella(cont).get_carattere()==" X ")
                    return new int[0];
                else
                    vet[cont++]=1;
            if(cont<get_celle().length)
                if(get_cella(cont).get_carattere()==" "+(char)3+" ")
                    return new int[0];
                else
                    vet[cont++]=0;
        }
        return vet;
    }
    public int[][] niente_numeri(){
       // System.out.println("Sono finiti i numeri tutte le celle dovrebbero essere vuote");
        int vet[][]=new int[1][get_celle().length];
        for(int i=0;i<get_celle().length;i++)
            if(get_cella(i).get_carattere()==" "+(char)3+" ")
                return new int[0][0];//non ci sono casi possibili con le configurazioni precedenti
            else
                vet[0][i]=0;
        return vet;
    }
    public int[][] spazio_0(int inutile){
        //System.out.println("lo spazio è 0: entro in spazio_0");
        int vet[][]=new int[1][get_celle().length];
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
                vet=Metodi.aggiungo_vet_al_vettore(vet,creo_configurazioni(i,numeri[0]));//se non puo inserire il blocco tecnica ritorna new int[0][0] e vet non cambia
                System.out.println("vet:( restituito da tecnica("+i+","+numeri[0]+")");
                Metodi.stampo(vet);
            }
            for(int i=blocchi_x[0]+blocchi_x_length[0];i<=spazio;i++){
                System.out.println("inserisco blocco lungo "+numeri[0]+" a partire dalla cella "+i+" dopo della x nella cella "+blocchi_x[0]);
                vet=Metodi.aggiungo_vet_al_vettore(vet,creo_configurazioni(i,numeri[0]));
                System.out.println("vet:( restituito da tecnica("+i+","+numeri[0]+")");
                Metodi.stampo(vet);
            }
        }else{//se entra qui il settore non contiene ne cuori ne x
            System.out.println("Non ci sono ne x ne cuori nel settore applico inserisco: ");
            for(int i=0;i<=spazio;i++){
                System.out.println("Inserisco blocco lungo "+numeri[0]+" a partire dalla cella "+i);
                vet=Metodi.aggiungo_vet_al_vettore(vet,creo_configurazioni(i,numeri[0]));
                System.out.println("vet: ");
                Metodi.stampo(vet);
            }
        }
        return vet;
    }
    public int[][] creo_configurazioni(int i,int blocco_length){//restituisce tutte le possibili configurazioni in cui è stato inserito un blocco lungo blocco_length la cui prima cella è in posizione i
        int temp1[]=costruisco_temp(i,blocco_length);//contiene la configurazione della parte del settore in cui è stato inserito il blocco
        if(temp1.length==0)
            return new int[0][0];
        int vet[][]=new int[0][0];
        Settore settore=copia_settore();
        settore.tolgo_celle(i+blocco_length);
        if(i+blocco_length<get_celle().length)
            settore.tolgo_celle(1);
        settore.tolgo_numero(0);
        if(settore.get_celle().length>0){
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
        //se c'è qualcosa che non va ritorna null
        /*System.out.print("Entro in creo_configurazioni alla "+type+" "+index+". celle: ");
        Metodi.stampo_posiz(get_celle(0));*/
        if(numeri.length==0)
            return niente_numeri(); 
        int vet[][]=new int[0][get_celle().length];//contiene 1 o 0 in ogni riga c'è una possibile configurazione
        int blocchi[]=get_blocchi();//contiene l'indice della prima cella di ogni blocchetto prsente nel settore
        int blocchi_length[]=get_blocchi_length();//blocchi_length[i] contiene la lunghezza del blocco la cui prima cella è blocchi[i]
        /*System.out.print("Blocchi: ");
        Metodi.stampo(blocchi);
        System.out.print("Blocchi_length: ");
        Metodi.stampo(blocchi_length);
        stampo();*/
        for(int i=0;i<=get_spazio();i++){
            int temp[][]=creo_configurazioni(i,numeri[0]);
            vet=Metodi.aggiungo_vet_al_vettore(vet,temp);
        }
        return vet;
    }
    public boolean tecnica(Cella nonogram[][],int numeri_righe[][],int numeri_colonne[][]){
        catch_errore("tecnica");
        System.out.println("Entro in tecnica(Cella[][]) "+type+" "+index);
        Scanner tastiera=new Scanner(System.in);
        int vet[][]=creo_configurazioni();
        if(vet.length==0){
            System.out.println("Il nonogram è impossibile perche' la "+type+" "+index+"  non ha configurazioni posibili");
            System.exit(0);
        }
        System.out.println("possibili configurazioni della "+type+" "+index+":");
        Metodi.stampo(vet);
        Cella vettore_di_celle[]=new Cella[get_celle().length];
        int somma[]=new int[get_celle().length],cont=0;
        for(int i=0;i<somma.length;i++){
            somma[i]=0;
            for(int j=0;j<vet.length;j++)
                somma[i]+=vet[j][i];
            if(somma[i]==0 && get_cella(i).get_carattere()!=" X "){//nell'i-esima cella va inserito una x 
                get_cella(i).set_x();
                vettore_di_celle[cont++]=get_cella(i);
            }
            if(somma[i]==vet.length && get_cella(i).get_carattere()!=" "+(char)3+" "){
                get_cella(i).set_blocco();
                vettore_di_celle[cont++]=get_cella(i);
            }
        }
        vettore_di_celle=Metodi.accorcio_lunghezza(vettore_di_celle,cont);
        System.out.print("Inserito cuore o x nella "+type+" "+index+" nelle celle: ");
        Metodi.stampo_posiz(vettore_di_celle);
        if(type=="Riga")
            for(int i=0;i<cont;i++){
                System.out.println("");
                Metodi.stampo(nonogram,numeri_righe,numeri_colonne);
                if(Metodi.finito(nonogram))
                    return true;
                int interrompo_flusso=tastiera.nextInt();
                Metodi.get_colonna(vettore_di_celle[i].get_colonna(),numeri_colonne[vettore_di_celle[i].get_colonna()],nonogram).tecnica(nonogram,numeri_righe,numeri_colonne);
            }
        else
            for(int i=0;i<cont;i++){
                System.out.println("");
                Metodi.stampo(nonogram,numeri_righe,numeri_colonne);
                if(Metodi.finito(nonogram))
                    return true;
                int interrompo_flusso=tastiera.nextInt();
                Metodi.get_riga(vettore_di_celle[i].get_riga(),numeri_righe[vettore_di_celle[i].get_riga()],nonogram).tecnica(nonogram,numeri_righe,numeri_colonne);
            }
        if(cont>0)
            return true;
        return false;
    }
}