public class Client {
    private String nume,prenume;
    private Cont cont;
    private Card card;
    TipCont tipCont;

    public Client(String nume, String prenume) {
        this.nume = nume;
        this.prenume = prenume;
        cont=null;
        card=null;
        tipCont=null;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setCont(TipCont tc) {
        if(tc==TipCont.normal)
            this.cont = new Cont(nume+prenume);
        else if(tc==TipCont.credit)
            this.cont=new ContCredit(nume+prenume);
        else if (tc==TipCont.economie)
            this.cont=new ContEconomie(nume+prenume,0);
        tipCont=tc;
    }

    public void setCard() {
        if(this.cont!=null){
            card=new Card(this.cont);
        }
    }

    public Cont getCont() {
        return cont;
    }

    public Card getCard() {
        return card;
    }

    public TipCont getTipCont() {
        return tipCont;
    }
}
