import javax.lang.model.util.ElementScanner14;

public class Metodi{
    public static int[] aggiungo_int_al_vettore(int vet[],int num){//allunga vet inserendo in fondo al vettore num
        int vet1[]=new int[1];
        vet1[0]=num;
        return aggiungo_int_al_vettore(vet,vet1);
    }
    public static int get_massimo(int vet[]){
        int massimo=-1;
        for(int i=0;i<vet.length;i++)
            if(vet[i]>massimo)
                massimo=vet[i];
        return massimo;
    }
    public static int[] aggiungo_int_al_vettore(int vet[],int vet1[]){//toglie i numeri 
        if(vet==null && vet1==null)
            return new int[0];
        if(vet==null)
            return vet1;
        if(vet1==null)
            return vet;
        int temp[]=new int[vet.length+vet1.length];
        for(int i=0;i<vet.length;i++)
            temp[i]=vet[i];
        for(int i=0;i<vet1.length;i++)
            temp[i+vet.length]=vet1[i];
        return temp;
    }
    public static int[][] aggiungo_vet_al_vettore(int vet1[][],int vet2[][]){//se vet1.length==0 ritorna vet2 e viceversa
        int temp[][]=vet1;
        for(int i=0;i<vet2.length;i++)
            temp=aggiungo_vet_al_vettore(temp,vet2[i]);
        return temp;
    }
    public static int[][] aggiungo_vet_al_vettore(int vet1[][],int vet2[]){
        int temp[][]=new int[vet1.length+1][0];
        for(int i=0;i<vet1.length;i++){
            temp[i]=new int[vet1[i].length];
            for(int j=0;j<temp[i].length;j++)
                temp[i][j]=vet1[i][j];
        }
        temp[vet1.length]=new int[vet2.length];
        for(int i=0;i<vet2.length;i++)
            temp[vet1.length][i]=vet2[i];
        return temp;
    }
    public static Nonogram[] aggiungo_vet_al_vettore(Nonogram vet1[],Nonogram vet2[]){
        Nonogram temp[]=new Nonogram[vet1.length+vet2.length];
        for(int i=0;i<vet1.length;i++)
            temp[i]=vet1[i];
        for(int i=0;i<vet2.length;i++)
            temp[i+vet1.length]=vet2[i];
        return temp;
    }
    public static int[] rimiuovo_int_al_vettore(int vet[],int num){//allunga vet inserendo in fondo al vettore num
        int vet1[]=new int[1];
        vet1[0]=num;
        return rimuovo_int_al_vettore(vet,vet1);
    }
    public static int[] rimuovo_int_al_vettore(int vet[],int vet1[]){
        for(int i=0;i<vet1.length;i++)
            vet=rimuovo_elementi_nella_posizione(vet,dove_elemento(vet, vet1[i], 0));
        return vet;
    }
    public static int[] accorcio_lunghezza(int vet[],int cont){//se cont==0 ritorna new int[0]
        int temp[]=new int[cont];
        for(int i=0;i<cont;i++)
            temp[i]=vet[i];
        return temp;
    }
    public static Cella[] accorcio_lunghezza(Cella vet[],int cont){
        Cella temp[]=new Cella[cont];
        for(int i=0;i<cont;i++)
            temp[i]=vet[i];
        return temp;
    }
    public static int[][] accorcio_lunghezza(int vet[][],int cont){
        int temp[][]=new int[cont][vet[0].length];
        for(int i=0;i<cont;i++){
            temp[i]=new int[vet[i].length];
            for(int j=0;j<temp[i].length;j++)
                temp[i][j]=vet[i][j];
        }
        return temp;
    }
    public static boolean ce_elemento(int vet[],int elemento){//ritorna true se ce un elemento di vet il cui valore è elemento
        boolean trovato=false;
        for(int i=0;i<vet.length && trovato==false;i++)
            if(vet[i]==elemento)
                trovato=true;
        return trovato;
    }
    public static int dove_elemento(int vet[],int elemento){//ritorna l'indice della prima cella di vet[] dove c'è elemento altrimenti ritorna -1
        for(int i=0;i<vet.length;i++)
            if(vet[i]==elemento)
                return i;
        return -1;
    }
    public static int[] dove_elemento(int vet[],int elemento,int inutile){//ritorna un vettore formsto da tutti gli indici delle celle di vet dove puo andare elemento, se elemento non può andare in nessuna cella ritorna new int[0]
        int vettore[]=new int[vet.length],cont=0;
        for(int i=0;i<vet.length;i++)
            if(vet[i]==elemento)
                vettore[cont++]=i;
        return accorcio_lunghezza(vettore,cont);
    }
    public static int[] rimuovo_elemento_nella_posizione(int vet[],int index){
        int vet1[]=new int[1];
        vet1[0]=index;
        return rimuovo_elementi_nella_posizione(vet,vet1);
    }
    public static int[] rimuovo_elementi_nella_posizione(int vet[],int index[]){//toglie dal vettore gli elementi nele posizioni dettate da index[]
        int new_vet[]=new int[vet.length-index.length],cont=0;
        for(int i=0;i<vet.length;i++)
            if(ce_elemento(index,i)==false)//se vet[i] non si trova oin index metto vet[i] in new_vet
                new_vet[cont++]=vet[i];
        return new_vet;
    }
    public static boolean uguali(int vet1[],int vet2[]){//se vet1 e vet2 hanno gli stessi elementi ritorna true
        if(vet1.length!=vet2.length)
            return false;
        for(int i=0;i<vet1.length;i++)//cerco vet1[vet1.length-1] in vet2 se non lo trova rutorna false
            if(vet1[vet1.length-1]==vet2[i]){
                if(vet1.length==1)
                    return true;
                else
                    return uguali(accorcio_lunghezza(vet1,vet1.length-1),rimuovo_elemento_nella_posizione(vet2,i));
            }
        return false;
    }
    public static Cella[] get_celle_riga(int index,Cella nonogram[][]){
        Cella vet[]=new Cella[nonogram[index].length];
        int cont=0;
        for(int i=0;i<nonogram[index].length;i++)
            if(nonogram[index][i].get_ce_nella_riga())
                vet[cont++]=nonogram[index][i];
        return accorcio_lunghezza(vet, cont);
    }
    public static Cella[] get_celle_colonna(int index,Cella nonogram[][]){
        Cella vet[]=new Cella[nonogram.length];
        int cont=0;
        for(int i=0;i<nonogram.length;i++)
            if(nonogram[i][index].get_ce_nella_colonna())
                vet[cont++]=nonogram[i][index];
        return accorcio_lunghezza(vet, cont);
    }
    public static Settore get_riga(int index,Cella nonogram[][]){
        return new Settore(index,"Riga",nonogram,nonogram[index].length,nonogram.length);
    }
    public static Settore get_riga(int index,int numeri[],Cella nonogram[][]){
        return new Settore(index,"Riga",nonogram,numeri,nonogram[0].length,nonogram.length);
    }
    public static Settore get_colonna(int index,int numeri[],Cella nonogram[][]){
        return new Settore(index,"Colonna",nonogram,numeri,nonogram[0].length,nonogram.length);
    }
    public static int[][] creo_ipotesi(int vet1[],int temp[][]){
     /*vet1 contiene come va il settore ad inizio per ogni possibile ipotesi
     * temp[i] contiene le varie configurazioni del fondo del settore dove allinizio ci va vet1
     */
        if(temp.length==0)
            return new int[0][0];
        int vet[][]=new int[temp.length][temp[0].length+vet1.length];
        for(int i=0;i<temp.length;i++){
            for(int j=0;j<vet1.length;j++)
                vet[i][j]=vet1[j];
            for(int j=0;j<temp[i].length;j++)
                vet[i][j+vet1.length]=temp[i][j];
        }
        return vet;
    }

    public static void stampo(int vet[]){
        for(int i=0;i<vet.length;i++)
            System.out.print(vet[i]+" ");
        System.out.println("");
    }
    public static void stampo(int vet[][]){
        for(int i=0;i<vet.length;i++)
            stampo(vet[i]);
    }
    public static void stampo_carattere(Cella settore[]){
        for(int i=0;i<settore.length;i++)
            if(settore[i].get_carattere()=="   ")
            System.out.print(" - ");
        else 
            System.out.print(settore[i].get_carattere());
        System.out.println("");
    }
    public static void stampo_carattere(Cella nonogram[][]){
        for(int i=0;i<nonogram.length;i++)
            stampo_carattere(nonogram[i]);
    }
    public static void stampo(Cella nonogram[][],int numeri_righe[][],int numeri_colonne[][]){
        Nonogram temp=new Nonogram(nonogram[0].length,nonogram.length,nonogram,numeri_righe,numeri_colonne);
        temp.stampo();
    }
    public static void stampo_posiz(Cella nonogram[][]){
        for(int i=0;i<nonogram.length;i++)
            stampo_posiz(nonogram[i]);
    }
    public static void stampo_posiz(Cella vet[]){
        for(int i=0;i<vet.length;i++)
            System.out.print(vet[i].get_posiz()+" ");
        System.out.println("");
    }
    public static void stampo_vettori(int temp1[],int temp[][],int vet1[][]){
        System.out.print("temp1: ( il risultato di costruisco_temp() ): ");
        stampo(temp1);
        System.out.println("temp() ( il risultato di settore.tecnica() ): ");
        stampo(temp);
        System.out.println("vet1: ( il risultato di creo_ipotesi() ): ");
        stampo(vet1);
    }
    public static boolean finito(Cella nonogram[][]){
        for(int i=0;i<nonogram.length;i++)
            for(int j=0;j<nonogram[i].length;j++)
                if(nonogram[i][j].get_carattere()=="   ")
                    return false;
        return true;
    }
    public static Nonogram creo_nonogram(int numero){
        int numeri[]=new int[100];
        switch(numero){
            case 1:
                Nonogram nonogram=new Nonogram(15,15);
                numeri[0]=2; numeri[1]=2;
                nonogram.get_riga(0).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=5; numeri[1]=5;
                nonogram.get_riga(1).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=15;
                nonogram.get_riga(2).set_numeri(Metodi.accorcio_lunghezza(numeri, 1));

                numeri[0]=13;
                nonogram.get_riga(3).set_numeri(Metodi.accorcio_lunghezza(numeri, 1));

                numeri[0]=6; numeri[1]=4;
                nonogram.get_riga(4).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=2; numeri[1]=2; numeri[2]=2;
                nonogram.get_riga(5).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=11;
                nonogram.get_riga(6).set_numeri(Metodi.accorcio_lunghezza(numeri, 1));

                numeri[0]=2; numeri[1]=2;
                nonogram.get_riga(7).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=2; numeri[1]=2;
                nonogram.get_riga(8).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=4; numeri[1]=4;
                nonogram.get_riga(9).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=2; numeri[1]=2;
                nonogram.get_riga(10).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=2; numeri[1]=2;
                nonogram.get_riga(11).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=2; numeri[1]=2;
                nonogram.get_riga(12).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=2; numeri[1]=2; numeri[2]=3; numeri[3]=2; numeri[4]=2;
                nonogram.get_riga(13).set_numeri(Metodi.accorcio_lunghezza(numeri, 5));

                numeri[0]=3; numeri[1]=5; numeri[2]=3;
                nonogram.get_riga(14).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));
                    //COLONNE
                numeri[0]=2; numeri[1]=2;
                nonogram.get_colonna(0).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=4; numeri[1]=2;
                nonogram.get_colonna(1).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=5; numeri[1]=1; numeri[2]=1; numeri[3]=1;
                nonogram.get_colonna(2).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));

                numeri[0]=13;
                nonogram.get_colonna(3).set_numeri(Metodi.accorcio_lunghezza(numeri, 1));

                numeri[0]=13;
                nonogram.get_colonna(4).set_numeri(Metodi.accorcio_lunghezza(numeri, 1));

                numeri[0]=3; numeri[1]=1; numeri[2]=1; numeri[3]=1;
                nonogram.get_colonna(5).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));

                numeri[0]=5; numeri[1]=2;
                nonogram.get_colonna(6).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=5; numeri[1]=2;
                nonogram.get_colonna(7).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=2; numeri[1]=1; numeri[2]=2;
                nonogram.get_colonna(8).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=3; numeri[1]=1; numeri[2]=1; numeri[3]=1;
                nonogram.get_colonna(9).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));

                numeri[0]=13;
                nonogram.get_colonna(10).set_numeri(Metodi.accorcio_lunghezza(numeri, 1));

                numeri[0]=13;
                nonogram.get_colonna(11).set_numeri(Metodi.accorcio_lunghezza(numeri, 1));

                numeri[0]=5; numeri[1]=1; numeri[2]=1; numeri[3]=1;
                nonogram.get_colonna(12).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));

                numeri[0]=4; numeri[1]=2;
                nonogram.get_colonna(13).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=2; numeri[1]=2;
                nonogram.get_colonna(14).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));
                return nonogram;
            case 2:
                nonogram=new Nonogram(15,15);
                numeri[0]=2; numeri[1]=9;
                nonogram.get_riga(0).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=2; numeri[1]=9;
                nonogram.get_riga(1).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=1; numeri[1]=8; numeri[2]=1;
                nonogram.get_riga(2).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=1; numeri[1]=7; numeri[2]=2;
                nonogram.get_riga(3).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=6; numeri[1]=1; numeri[2]=3;
                nonogram.get_riga(4).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=5; numeri[1]=2; numeri[2]=4;
                nonogram.get_riga(5).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=2; numeri[1]=1; numeri[2]=4;
                nonogram.get_riga(6).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=1; numeri[1]=3; numeri[2]=2; numeri[3]=3;
                nonogram.get_riga(7).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));

                numeri[0]=4; numeri[1]=2; numeri[2]=3;
                nonogram.get_riga(8).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=5; numeri[1]=3; numeri[2]=3;
                nonogram.get_riga(9).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=2; numeri[1]=4; numeri[2]=2; numeri[3]=1;
                nonogram.get_riga(10).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));

                numeri[0]=3; numeri[1]=3; numeri[2]=2; numeri[3]=2;
                nonogram.get_riga(11).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));

                numeri[0]=6; numeri[1]=2; numeri[2]=3;
                nonogram.get_riga(12).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=1; numeri[1]=3; numeri[2]=3;
                nonogram.get_riga(13).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=2; numeri[1]=8;
                nonogram.get_riga(14).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));
                    //COLONNE
                numeri[0]=8; numeri[1]=2;
                nonogram.get_colonna(0).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=2; numeri[1]=3; numeri[2]=4; numeri[3]=1;
                nonogram.get_colonna(1).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));

                numeri[0]=2; numeri[1]=6;
                nonogram.get_colonna(2).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=5; numeri[1]=3; numeri[2]=2;
                nonogram.get_colonna(3).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=6; numeri[1]=3; numeri[2]=1;
                nonogram.get_colonna(4).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=5; numeri[1]=1; numeri[2]=2; numeri[3]=1;
                nonogram.get_colonna(5).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));

                numeri[0]=4; numeri[1]=1; numeri[2]=3;
                nonogram.get_colonna(6).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=4; numeri[1]=1; numeri[2]=3; numeri[3]=1;
                nonogram.get_colonna(7).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));

                numeri[0]=5; numeri[1]=3; numeri[2]=2;
                nonogram.get_colonna(8).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=4; numeri[1]=3; numeri[2]=3;
                nonogram.get_colonna(9).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=3; numeri[1]=3; numeri[2]=4;
                nonogram.get_colonna(10).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=2; numeri[1]=2; numeri[2]=2; numeri[3]=1;
                nonogram.get_colonna(11).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));

                numeri[0]=1; numeri[1]=7; numeri[2]=3;
                nonogram.get_colonna(12).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=7; numeri[1]=4;
                nonogram.get_colonna(13).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=13;
                nonogram.get_colonna(14).set_numeri(Metodi.accorcio_lunghezza(numeri, 1));
                return nonogram;
            case 3:
                nonogram=new Nonogram(15,15);
                numeri[0]=2; numeri[1]=6;
                nonogram.get_riga(0).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=8; numeri[1]=4;
                nonogram.get_riga(1).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=5; numeri[1]=7;
                nonogram.get_riga(2).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=2; numeri[1]=9; numeri[2]=2;
                nonogram.get_riga(3).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=1; numeri[1]=7; numeri[2]=1;
                nonogram.get_riga(4).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=1; numeri[1]=5;
                nonogram.get_riga(5).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=9;
                nonogram.get_riga(6).set_numeri(Metodi.accorcio_lunghezza(numeri, 1));

                numeri[0]=1; numeri[1]=1; numeri[2]=1; numeri[3]=1;
                nonogram.get_riga(7).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));

                numeri[0]=11;
                nonogram.get_riga(8).set_numeri(Metodi.accorcio_lunghezza(numeri, 1));

                numeri[0]=2; numeri[1]=1; numeri[2]=1;numeri[3]=1;
                nonogram.get_riga(9).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));

                numeri[0]=11;
                nonogram.get_riga(10).set_numeri(Metodi.accorcio_lunghezza(numeri, 1));

                numeri[0]=1; numeri[1]=1; numeri[2]=1; numeri[3]=1; numeri[4]=1;
                nonogram.get_riga(11).set_numeri(Metodi.accorcio_lunghezza(numeri, 5));

                numeri[0]=11;
                nonogram.get_riga(12).set_numeri(Metodi.accorcio_lunghezza(numeri, 1));

                numeri[0]=1; numeri[1]=1; numeri[2]=2;
                nonogram.get_riga(13).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=7;
                nonogram.get_riga(14).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));
                    //COLONNE
                numeri[0]=3; 
                nonogram.get_colonna(0).set_numeri(Metodi.accorcio_lunghezza(numeri, 1));

                numeri[0]=3;
                nonogram.get_colonna(1).set_numeri(Metodi.accorcio_lunghezza(numeri, 1));

                numeri[0]=3; numeri[1]=5;
                nonogram.get_colonna(2).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));

                numeri[0]=4; numeri[1]=5; numeri[2]=2;
                nonogram.get_colonna(3).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=4; numeri[1]=1; numeri[2]=1;numeri[3]=1; numeri[4]=1; numeri[5]=1;
                nonogram.get_colonna(4).set_numeri(Metodi.accorcio_lunghezza(numeri, 6));

                numeri[0]=9; numeri[1]=3; numeri[2]=1; 
                nonogram.get_colonna(5).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=2; numeri[1]=4; numeri[2]=3; numeri[3]=3;
                nonogram.get_colonna(6).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));

                numeri[0]=7; numeri[1]=1; numeri[2]=3; numeri[3]=1;
                nonogram.get_colonna(7).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));

                numeri[0]=11; numeri[1]=1; numeri[2]=1;
                nonogram.get_colonna(8).set_numeri(Metodi.accorcio_lunghezza(numeri, 3));

                numeri[0]=1; numeri[1]=5; numeri[2]=1; numeri[3]=1; numeri[4]=1; numeri[5]=1;
                nonogram.get_colonna(9).set_numeri(Metodi.accorcio_lunghezza(numeri, 6));

                numeri[0]=5; numeri[1]=1; numeri[2]=1; numeri[3]=5;
                nonogram.get_colonna(10).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));
                numeri[0]=3; numeri[1]=3; numeri[2]=1; numeri[3]=2;
                nonogram.get_colonna(11).set_numeri(Metodi.accorcio_lunghezza(numeri, 4));
                numeri[0]=2; numeri[1]=5;
                nonogram.get_colonna(12).set_numeri(Metodi.accorcio_lunghezza(numeri, 2));
                numeri[0]=3;
                nonogram.get_colonna(13).set_numeri(Metodi.accorcio_lunghezza(numeri, 1));
                numeri[0]=2;
                nonogram.get_colonna(14).set_numeri(Metodi.accorcio_lunghezza(numeri, 1));
                return nonogram;

        }
        return new Nonogram(0,0);
    }

}