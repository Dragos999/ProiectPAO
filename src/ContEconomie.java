public class ContEconomie extends Cont{
    private int limita;

    public ContEconomie(String numeTitular, int limita) {
        super(numeTitular);
        this.limita = limita;
    }
    @Override
    public boolean extrage(double suma){
        if(sold-suma>=0 && numarTranzactii()<limita)
            sold-=suma;
        else
            return false;
        return true;
    }

    public int getLimita() {
        return limita;
    }

    public void setLimita(int limita) {
        this.limita = limita;
    }

}
