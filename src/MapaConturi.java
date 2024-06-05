import java.util.HashMap;
import java.util.Map;

public class MapaConturi {
    private static Map<String,Cont> conturi = new HashMap<String,Cont>();
    public static void addCont(Cont cont){
        conturi.put(cont.getIban(),cont);
    }
    public static Cont getCont(String iban){
        return conturi.get(iban);
    }
    public static void afiseazaConturi(){
        int counter=0;
        for (Map.Entry<String,Cont> c : conturi.entrySet()){
            System.out.println(counter+".\nNume titular: "+c.getValue().numeTitular+"\nIban: "+c.getKey());
            counter++;
        }
    }
}
