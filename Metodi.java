import java.sql.Array;

import javax.lang.model.util.ElementScanner14;

public class Metodi{
    /**
     * 
     * @param vet
     * @return
     */
    public static int[] copia(int vet[]){
        int result[]=new int[vet.length];
        for(int i=0;i<result.length;i++)
            result[i]=vet[i];
        return result;
    }
    /**
     * 
     * @param celle
     * @return
     */
    public static Cella[] copia(Cella celle[]){
        Cella result[]=new Cella[celle.length];
        for(int i=0;i<result.length;i++)
            result[i]=celle[i].copia();
        return result;
    }
    /**
     * 
     * @param vet
     * @param base
     */
    public static void stampo_posiz(Cella vet[],int base){
        for(int i=0;i<vet.length;i++)
            System.out.print(vet[i].get_riga()*base+vet[i].get_colonna()+" ");
        System.out.println("");
    }

    /**
     * 
     * @param vet1
     * @param vet2
     * @return
     */
    public static int[][] aggiungo_vet_al_vettore(int vet1[][],int vet2[][]){//se vet1.length==0 ritorna vet2 e viceversa
        int temp[][]=vet1;
        for(int i=0;i<vet2.length;i++)
            temp=aggiungo_vet_al_vettore(temp,vet2[i]);
        return temp;
    }
    
    /**
     * 
     * @param vet1
     * @param vet2
     * @return
     */
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
    
    /**
     * Serve nella tecnica tentativi
     * @param vet1
     * @param vet2
     * @return
     */
    public static Nonogram[] aggiungo_vet_al_vettore(Nonogram vet1[],Nonogram vet2[]){
        Nonogram temp[]=new Nonogram[vet1.length+vet2.length];
        for(int i=0;i<vet1.length;i++)
            temp[i]=vet1[i];
        for(int i=0;i<vet2.length;i++)
            temp[i+vet1.length]=vet2[i];
        return temp;
    }

    /**
     * 
     * @param vet
     * @param cont
     * @return
     */
    private static int[] accorcio_lunghezza(int vet[],int cont){//se cont==0 ritorna new int[0]
        int temp[]=new int[cont];
        for(int i=0;i<cont;i++)
            temp[i]=vet[i];
        return temp;
    }

    /**
     * 
     * @param vet
     * @param cont
     * @return
     */
    public static Cella[] accorcio_lunghezza(Cella vet[],int cont){//se cont==0 ritorna new int[0]
        Cella temp[]=new Cella[cont];
        for(int i=0;i<cont;i++)
            temp[i]=vet[i];
        return temp;
    }

    /**
     * Il metodo ritorna il vettore che si ottiene togliendo da vet il primo elemento.
     * The method throws an RuntimeException if vet.length==0 or if vet==null.
     * @param vet vettore dal quale si costruisce il risultato
     * @return
     */
    public static int[] rimuovo_primo_elemento(int vet[]){
        if(vet==null)
            throw new NullPointerException("vet parameter is null");
        if(vet.length==0)
            throw new NegativeArraySizeException("vet.length is equals to 0");
        int result[]=new int[vet.length-1];
        for(int i=0;i<result.length;i++)
            result[i]=vet[i+1];
        return result;
    }
    /**
     * Il metodo ritorna il vettore di celle che si ottiene togliendo le prime num_celle_rimosse dal vettore vet.
     * vet rimane invariato e non viene modificato.
     * Il vettore ritornato avrà le celle con lo stesso indirizzo di memoria delle celle di vet
     * @param vet vettore da cui si costruisce il risultato
     * @param num_celle_rimosse numero di celle che vengono rimosse dal vettore vet
     * @return cio che si ottiene togliendo da vet le prime num_celle_rimosse
     */
    public static Cella[] rimuovo_celle(Cella[] vet,int num_celle_rimosse){
        if(vet==null)
            throw new NullPointerException("vet parameter is null");
        if(num_celle_rimosse>vet.length)
            throw new NegativeArraySizeException("num_celle_rimosse is gretaer then vet.length");
        Cella result[]=new Cella[vet.length-num_celle_rimosse];
        for(int i=0;i<result.length;i++)
            result[i]=vet[i+num_celle_rimosse];
        return result;
    }

    /**
     * Il metodo ritornano le celle della riga/colona (il cui indice è index).
     * Questo settore viene estratto dalla matrice di celle nonogram
     * @param type stabilisce se viene presa la riga o la colonna
     * @param index 
     * @param nonogram
     * @return
     */
    public static Cella[] get_settore(Enum.tipo_settore type,int index,Cella nonogram[][]){
        try{
            switch(type){
                case riga:
                    return get_riga(index,nonogram);
                case colonna:
                    return get_colonna(index,nonogram);
                default:
                    throw new RuntimeException("Non ci arriva mai qui");
            }
        }catch(NullPointerException e){
            throw new NullPointerException("the type parameter is null");
        }
    }

    /**
     * 
     * @param index
     * @param nonogram
     * @return
     */
    public static Cella[] get_riga(int index,Cella nonogram[][]){
        try{
            Cella risultato[]=new Cella[nonogram[index].length];
            for(int i=0;i<risultato.length;i++)
                risultato[i]=nonogram[index][i];
            return risultato;
        }catch(NullPointerException e){
            if(nonogram==null)
                throw new NullPointerException("nonogram parameter is null");
            else if(nonogram[index]==null)
                throw new NullPointerException("nonogram["+index+"] is null");
        }catch(ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException("index non è compagtibile con nonogram.length.\nindex: "+index+".\nnonogram.length: "+nonogram.length);
        }
        throw new RuntimeException("Non ci arriva mai qui");
    }

    /**
     * 
     * @param index
     * @param nonogram
     * @return
     */
    public static Cella[] get_colonna(int index,Cella nonogram[][]){
        int i=0;
        try{
            Cella risultato[]=new Cella[nonogram.length];
            for(i=0;i<risultato.length;i++)
                risultato[i]=nonogram[i][index];
            return risultato;
        }catch(NullPointerException e){
            if(nonogram==null)
                throw new NullPointerException("nonogram parameter is null");
            else //sicuramente nonogram[i] è null. non ci sono altri casi
                throw new NullPointerException("nonogram[i] e' null.\ni: "+i);
        }catch(ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException("index non è compatibile con la lunghezza di nonogram["+i+"].\nindex: "+index+".\nnonogram[i].length: "+nonogram[i].length);
        }
    }

    /**
     * 
     * @param vet1
     * @param temp
     * @return
     */
    public static int[][] creo_ipotesi(int vet1[],int temp[][]){
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

    /**
     * 
     * @param vet
     */
    public static void stampo(int vet[]){
        for(int i=0;i<vet.length;i++)
            System.out.print(vet[i]+" ");
        System.out.println("");
    }
    /**
     * 
     * @param vet
     */
    public static void stampo(int vet[][]){
        for(int i=0;i<vet.length;i++)
            stampo(vet[i]);
    }

    /**
     * The method returns a matric of white cells
     * @param base number of column of the returned matrix
     * @param altezza number of rows of the returned matrix
     * @return matrix's white-cells
     */
    public static Cella[][] creo_tabella_di_celle(int base,int altezza){
        Cella result[][]=new Cella[altezza][base];
        for(int i=0;i<altezza;i++)
            for(int j=0;j<base;j++)
                result[i][j]=new Cella(i,j);
        return result;
    }
    
    public static Nonogram creo_nonogram(int numero){
        int numeri[]=new int[100];
        Settore vet_riga[]=new Settore[15],vet_colonna[]=new Settore[15];
        Cella celle[][]=new Cella[15][15];
        for(int i=0;i<15;i++)
            for(int j=0;j<15;j++)
                celle[i][j]=new Cella(i,j);
        switch(numero){
            case 1:
                numeri[0]=2; numeri[1]=2;
                vet_riga[0]=new Settore(Enum.tipo_settore.riga,0,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=5; numeri[1]=5;
                vet_riga[1]=new Settore(Enum.tipo_settore.riga,1,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=15;
                vet_riga[2]=new Settore(Enum.tipo_settore.riga,2,Metodi.accorcio_lunghezza(numeri, 1),celle);

                numeri[0]=13;
                vet_riga[3]=new Settore(Enum.tipo_settore.riga,3,Metodi.accorcio_lunghezza(numeri, 1),celle);

                numeri[0]=6; numeri[1]=4;
                vet_riga[4]=new Settore(Enum.tipo_settore.riga,4,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=2; numeri[1]=2; numeri[2]=2;
                vet_riga[5]=new Settore(Enum.tipo_settore.riga,5,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=11;
                vet_riga[6]=new Settore(Enum.tipo_settore.riga,6,Metodi.accorcio_lunghezza(numeri, 1),celle);

                numeri[0]=2; numeri[1]=2;
                vet_riga[7]=new Settore(Enum.tipo_settore.riga,7,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=2; numeri[1]=2;
                vet_riga[8]=new Settore(Enum.tipo_settore.riga,8,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=4; numeri[1]=4;
                vet_riga[9]=new Settore(Enum.tipo_settore.riga,9,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=2; numeri[1]=2;
                vet_riga[10]=new Settore(Enum.tipo_settore.riga,10,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=2; numeri[1]=2;
                vet_riga[11]=new Settore(Enum.tipo_settore.riga,11,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=2; numeri[1]=2;
                vet_riga[12]=new Settore(Enum.tipo_settore.riga,12,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=2; numeri[1]=2; numeri[2]=3; numeri[3]=2; numeri[4]=2;
                vet_riga[13]=new Settore(Enum.tipo_settore.riga,13,Metodi.accorcio_lunghezza(numeri, 5),celle);

                numeri[0]=3; numeri[1]=5; numeri[2]=3;
                vet_riga[14]=new Settore(Enum.tipo_settore.riga,14,Metodi.accorcio_lunghezza(numeri, 3),celle);
                    
                //COLONNE
                
                numeri[0]=2; numeri[1]=2;
                vet_colonna[0]=new Settore(Enum.tipo_settore.colonna,0,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=4; numeri[1]=2;
                vet_colonna[1]=new Settore(Enum.tipo_settore.colonna,1,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=5; numeri[1]=1; numeri[2]=1; numeri[3]=1;
                vet_colonna[2]=new Settore(Enum.tipo_settore.colonna,2,Metodi.accorcio_lunghezza(numeri, 4),celle);

                numeri[0]=13;
                vet_colonna[3]=new Settore(Enum.tipo_settore.colonna,3,Metodi.accorcio_lunghezza(numeri, 1),celle);

                numeri[0]=13;
                vet_colonna[4]=new Settore(Enum.tipo_settore.colonna,4,Metodi.accorcio_lunghezza(numeri, 1),celle);

                numeri[0]=3; numeri[1]=1; numeri[2]=1; numeri[3]=1;
                vet_colonna[5]=new Settore(Enum.tipo_settore.colonna,5,Metodi.accorcio_lunghezza(numeri, 4),celle);

                numeri[0]=5; numeri[1]=2;
                vet_colonna[6]=new Settore(Enum.tipo_settore.colonna,6,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=5; numeri[1]=2;
                vet_colonna[7]=new Settore(Enum.tipo_settore.colonna,7,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=2; numeri[1]=1; numeri[2]=2;
                vet_colonna[8]=new Settore(Enum.tipo_settore.colonna,8,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=3; numeri[1]=1; numeri[2]=1; numeri[3]=1;
                vet_colonna[9]=new Settore(Enum.tipo_settore.colonna,9,Metodi.accorcio_lunghezza(numeri, 4),celle);

                numeri[0]=13;
                vet_colonna[10]=new Settore(Enum.tipo_settore.colonna,10,Metodi.accorcio_lunghezza(numeri, 1),celle);

                numeri[0]=13;
                vet_colonna[11]=new Settore(Enum.tipo_settore.colonna,11,Metodi.accorcio_lunghezza(numeri, 1),celle);

                numeri[0]=5; numeri[1]=1; numeri[2]=1; numeri[3]=1;
                vet_colonna[12]=new Settore(Enum.tipo_settore.colonna,12,Metodi.accorcio_lunghezza(numeri, 4),celle);

                numeri[0]=4; numeri[1]=2;
                vet_colonna[13]=new Settore(Enum.tipo_settore.colonna,13,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=2; numeri[1]=2;
                vet_colonna[14]=new Settore(Enum.tipo_settore.colonna,14,Metodi.accorcio_lunghezza(numeri, 2),celle);
                return new Nonogram(vet_riga, vet_colonna, celle);
            case 2:
                numeri[0]=2; numeri[1]=9;
                vet_riga[0]=new Settore(Enum.tipo_settore.riga,0,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=2; numeri[1]=9;
                vet_riga[1]=new Settore(Enum.tipo_settore.riga,1,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=1; numeri[1]=8; numeri[2]=1;
                vet_riga[2]=new Settore(Enum.tipo_settore.riga,2,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=1; numeri[1]=7; numeri[2]=2;
                vet_riga[3]=new Settore(Enum.tipo_settore.riga,3,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=6; numeri[1]=1; numeri[2]=3;
                vet_riga[4]=new Settore(Enum.tipo_settore.riga,4,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=5; numeri[1]=2; numeri[2]=4;
                vet_riga[5]=new Settore(Enum.tipo_settore.riga,5,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=2; numeri[1]=1; numeri[2]=4;
                vet_riga[6]=new Settore(Enum.tipo_settore.riga,6,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=1; numeri[1]=3; numeri[2]=2; numeri[3]=3;
                vet_riga[7]=new Settore(Enum.tipo_settore.riga,7,Metodi.accorcio_lunghezza(numeri, 4),celle);

                numeri[0]=4; numeri[1]=2; numeri[2]=3;
                vet_riga[8]=new Settore(Enum.tipo_settore.riga,8,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=5; numeri[1]=3; numeri[2]=3;
                vet_riga[9]=new Settore(Enum.tipo_settore.riga,9,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=2; numeri[1]=4; numeri[2]=2; numeri[3]=1;
                vet_riga[10]=new Settore(Enum.tipo_settore.riga,10,Metodi.accorcio_lunghezza(numeri, 4),celle);

                numeri[0]=3; numeri[1]=3; numeri[2]=2; numeri[3]=2;
                vet_riga[11]=new Settore(Enum.tipo_settore.riga,11,Metodi.accorcio_lunghezza(numeri, 4),celle);

                numeri[0]=6; numeri[1]=2; numeri[2]=3;
                vet_riga[12]=new Settore(Enum.tipo_settore.riga,12,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=1; numeri[1]=3; numeri[2]=3;
                vet_riga[13]=new Settore(Enum.tipo_settore.riga,13,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=2; numeri[1]=8;
                vet_riga[14]=new Settore(Enum.tipo_settore.riga,14,Metodi.accorcio_lunghezza(numeri, 2),celle);
                    
                //COLONNE
                
                numeri[0]=8; numeri[1]=2;
                vet_colonna[0]=new Settore(Enum.tipo_settore.colonna,0,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=2; numeri[1]=3; numeri[2]=4; numeri[3]=1;
                vet_colonna[1]=new Settore(Enum.tipo_settore.colonna,1,Metodi.accorcio_lunghezza(numeri, 4),celle);

                numeri[0]=2; numeri[1]=6;
                vet_colonna[2]=new Settore(Enum.tipo_settore.colonna,2,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=5; numeri[1]=3; numeri[2]=2;
                vet_colonna[3]=new Settore(Enum.tipo_settore.colonna,3,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=6; numeri[1]=3; numeri[2]=1;
                vet_colonna[4]=new Settore(Enum.tipo_settore.colonna,4,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=5; numeri[1]=1; numeri[2]=2; numeri[3]=1;
                vet_colonna[5]=new Settore(Enum.tipo_settore.colonna,5,Metodi.accorcio_lunghezza(numeri, 4),celle);

                numeri[0]=4; numeri[1]=1; numeri[2]=3;
                vet_colonna[6]=new Settore(Enum.tipo_settore.colonna,6,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=4; numeri[1]=1; numeri[2]=3; numeri[3]=1;
                vet_colonna[7]=new Settore(Enum.tipo_settore.colonna,7,Metodi.accorcio_lunghezza(numeri, 4),celle);

                numeri[0]=5; numeri[1]=3; numeri[2]=2;
                vet_colonna[8]=new Settore(Enum.tipo_settore.colonna,8,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=4; numeri[1]=3; numeri[2]=3;
                vet_colonna[9]=new Settore(Enum.tipo_settore.colonna,9,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=3; numeri[1]=3; numeri[2]=4;
                vet_colonna[10]=new Settore(Enum.tipo_settore.colonna,10,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=2; numeri[1]=2; numeri[2]=2; numeri[3]=1;
                vet_colonna[11]=new Settore(Enum.tipo_settore.colonna,11,Metodi.accorcio_lunghezza(numeri, 4),celle);

                numeri[0]=1; numeri[1]=7; numeri[2]=3;
                vet_colonna[12]=new Settore(Enum.tipo_settore.colonna,12,Metodi.accorcio_lunghezza(numeri, 3),celle);

                numeri[0]=7; numeri[1]=4;
                vet_colonna[13]=new Settore(Enum.tipo_settore.colonna,13,Metodi.accorcio_lunghezza(numeri, 2),celle);

                numeri[0]=13;
                vet_colonna[14]=new Settore(Enum.tipo_settore.colonna,14,Metodi.accorcio_lunghezza(numeri, 1),celle);
                return new Nonogram(vet_riga,vet_colonna,celle);
            case 3://ALTERNATIVE WAY
                int numeri_righe[][]=new int[15][0],numeri_colonne[][]=new int[15][0];
                numeri_righe[0]=new int[2];
                numeri_righe[0][0]=2; numeri_righe[0][1]=6;

                numeri_righe[1]=new int[2];
                numeri_righe[1][0]=8; numeri_righe[1][1]=4;

                numeri_righe[2]=new int[2];
                numeri_righe[2][0]=5; numeri_righe[2][1]=7;

                numeri_righe[3]=new int[3];
                numeri_righe[3][0]=2; numeri_righe[3][1]=9; numeri_righe[3][2]=2;

                numeri_righe[4]=new int[3];
                numeri_righe[4][0]=1; numeri_righe[4][1]=7; numeri_righe[4][2]=1;

                numeri_righe[5]=new int[2];
                numeri_righe[5][0]=1; numeri_righe[5][1]=5;

                numeri_righe[6]=new int[1];
                numeri_righe[6][0]=9;

                numeri_righe[7]=new int[4];
                numeri_righe[7][0]=1; numeri_righe[7][1]=1; numeri_righe[7][2]=1; numeri_righe[7][3]=1;

                numeri_righe[8]=new int[1];
                numeri_righe[8][0]=11;

                numeri_righe[9]=new int[4];
                numeri_righe[9][0]=2; numeri_righe[9][1]=1; numeri_righe[9][2]=1;numeri_righe[9][3]=1;

                numeri_righe[10]=new int[1];
                numeri_righe[10][0]=11;

                numeri_righe[11]=new int[5];
                numeri_righe[11][0]=1; numeri_righe[11][1]=1; numeri_righe[11][2]=1; numeri_righe[11][3]=1; numeri_righe[11][4]=1;

                numeri_righe[12]=new int[1];
                numeri_righe[12][0]=11;

                numeri_righe[13]=new int[3];
                numeri_righe[13][0]=1; numeri_righe[13][1]=1; numeri_righe[13][2]=2;

                numeri_righe[14]=new int[1];
                numeri_righe[14][0]=7;
                    
                //COLONNE
                
                numeri_colonne[0]=new int[1];
                numeri_colonne[0][0]=3; 

                numeri_colonne[1]=new int[1];
                numeri_colonne[1][0]=3;

                numeri_colonne[2]=new int[2];
                numeri_colonne[2][0]=3; numeri_colonne[2][1]=5;

                numeri_colonne[3]=new int[3];
                numeri_colonne[3][0]=4; numeri_colonne[3][1]=5; numeri_colonne[3][2]=2;

                numeri_colonne[4]=new int[6];
                numeri_colonne[4][0]=4; numeri_colonne[4][1]=1; numeri_colonne[4][2]=1;numeri_colonne[4][3]=1; numeri_colonne[4][4]=1; numeri_colonne[4][5]=1;

                numeri_colonne[5]=new int[3];
                numeri_colonne[5][0]=9; numeri_colonne[5][1]=3; numeri_colonne[5][2]=1; 

                numeri_colonne[6]=new int[4];
                numeri_colonne[6][0]=2; numeri_colonne[6][1]=4; numeri_colonne[6][2]=3; numeri_colonne[6][3]=3;

                numeri_colonne[7]=new int[4];
                numeri_colonne[7][0]=7; numeri_colonne[7][1]=1; numeri_colonne[7][2]=3; numeri_colonne[7][3]=1;

                numeri_colonne[8]=new int[3];
                numeri_colonne[8][0]=11; numeri_colonne[8][1]=1; numeri_colonne[8][2]=1;

                numeri_colonne[9]=new int[6];
                numeri_colonne[9][0]=1; numeri_colonne[9][1]=5; numeri_colonne[9][2]=1; numeri_colonne[9][3]=1; numeri_colonne[9][4]=1; numeri_colonne[9][5]=1;

                numeri_colonne[10]=new int[4];
                numeri_colonne[10][0]=5; numeri_colonne[10][1]=1; numeri_colonne[10][2]=1; numeri_colonne[10][3]=5;
                
                numeri_colonne[11]=new int[4];
                numeri_colonne[11][0]=3; numeri_colonne[11][1]=3; numeri_colonne[11][2]=1; numeri_colonne[11][3]=2;
                
                numeri_colonne[12]=new int[2];
                numeri_colonne[12][0]=2; numeri_colonne[12][1]=5;
                
                numeri_colonne[13]=new int[1];
                numeri_colonne[13][0]=3;

                numeri_colonne[14]=new int[1];
                numeri_colonne[14][0]=2;
                
                return new Nonogram(numeri_righe,numeri_colonne);
            default:
                throw new RuntimeException("Caso non riconosciuto.\nnumero: "+numero);
        }
    }

}