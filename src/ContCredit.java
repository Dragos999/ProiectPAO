public class ContCredit extends Cont{
    private double datorie;

    public ContCredit(String numeTitular) {
        super(numeTitular);
        this.datorie = 0;
    }

    @Override
    public boolean extrage(double suma){
        sold-=suma;
        if(sold<0){
            datorie=0-sold;
        }
        return true;
    }
    public double getDatorie() {
        return datorie;
    }

    public void setDatorie(double datorie) {
        this.datorie = datorie;
    }

}
