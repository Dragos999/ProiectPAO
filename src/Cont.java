import java.util.ArrayList;
import java.util.List;

public class Cont {

    protected String numeTitular;
    protected String iban;
    protected double sold;
    protected List<Tranzactie> tranzactii;
    public Cont(String numeTitular) {
        this.numeTitular=numeTitular;
        this.iban = StringRand.generate(numeTitular);
        this.sold = 0.0;
        tranzactii=new ArrayList<Tranzactie>();
        MapaConturi.addCont(this);
    }
    public String getNumeTitular() {
        return numeTitular;
    }

    public void setNumeTitular(String numeTitular) {
        this.numeTitular = numeTitular;
    }

    public String getIban() {
        return iban;
    }

    public double getSold() {
        return sold;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }
    public int numarTranzactii(){
        return tranzactii.size();
    }
    public void depoziteaza(double suma){
        sold+=suma;
    }
    public boolean extrage(double suma){
        if(sold-suma>=0)
            sold-=suma;
        else
            return false;
        return true;
    }
    public void addTranzactie(Cont contDestinatar,double suma){
        tranzactii.add(new Tranzactie(this.numeTitular,contDestinatar.getNumeTitular(),suma));
    }

    public List<Tranzactie> getTranzactii() {
        return tranzactii;
    }
}
