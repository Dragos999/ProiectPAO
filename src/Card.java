import java.time.LocalDate;
public class Card {
    private String numar,csv;
    LocalDate dataExpirare;

    Cont cont;

    public Card(Cont cont) {
        System.out.println("!!!!");
        this.cont=cont;
        this.numar=StringRand.generateCardNumber();
        this.csv=StringRand.generateCSV();
        this.dataExpirare=LocalDate.now().plusYears(4);
    }

    public String getNumar() {
        return numar;
    }

    public void setNumar(String numar) {
        this.numar = numar;
    }

    public String getCsv() {
        return csv;
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }

    public LocalDate getDataExpirare() {
        return dataExpirare;
    }

    public void setDataExpirare(LocalDate dataExpirare) {
        this.dataExpirare = dataExpirare;
    }
}
