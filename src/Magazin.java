import java.util.List;
import java.util.ArrayList;
public class Magazin {
    private List<Produs>produse;
    private Cont cont;
    private String denumire;

    public Magazin( String denumire) {
        this.produse = new ArrayList<Produs>();
        this.cont = new Cont(denumire);
        cont.setIban("MAGAZIN"+cont.getIban());
        this.denumire = denumire;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public void addProdus(String denumire,double pret,int stoc){
        produse.add(new Produs(denumire,pret,stoc));
    }

    public void deleteProdus(int cod){
        produse.remove(cod);

    }

    public List<Produs> getProduse() {
        return produse;
    }

    public void afiseazaProduse(){
        int counter=0;
        for (Produs p:produse){
            System.out.println(counter+". "+p.getDenumire()+ " Pret: "+p.getPret());
            counter++;
        }

    }

    public Cont getCont() {
        return cont;
    }
}
