public class Enum {
    public enum tipo_settore{
        riga,colonna;
    }

    public enum tipo_carattere{
        vuoto("   "),
        blocco(" "+(char)3+" "),
        x(" X ");
        private final String carattere;
        public String toString(){
            return carattere;
        }
        private tipo_carattere(String carattere){
            this.carattere=carattere;
        }
    }
}
