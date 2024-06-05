public class Tranzactie {
    private String expeditor,destinatar;
    private double suma;

    public Tranzactie(String expeditor, String destinatar, double suma) {
        this.expeditor = expeditor;
        this.destinatar = destinatar;
        this.suma = suma;
    }

    public String afiseaza(){
        return new String("Suma de "+suma+" de la "+expeditor+" catre "+destinatar);
    }
}
