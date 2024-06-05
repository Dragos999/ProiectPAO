import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class myService {
    private Connection connection;
    private List<Client>clienti=new ArrayList<Client>();
    private List<Magazin>magazine=new ArrayList<Magazin>();
    public void creareClient(String nume,String prenume){
        clienti.add(new Client(nume,prenume));
        try {
            var stmt = connection.createStatement();
            var sql = "INSERT INTO public.\"Client\"(\n" +
                    "    id, \"Nume\", \"Prenume\")\n" +
                    "    VALUES ("+(clienti.size()-1)+", '"+nume+"', '"+prenume+"');";
            stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
            System.out.println("EROARE!");
            e.printStackTrace();
        }
    }
    public void stergeClient(int cod){
        clienti.remove(cod);
        try {
            var stmt = connection.createStatement();
            var sql = "DELETE FROM public.\"Client\"\n" +
                    "\tWHERE id="+cod+";";
            stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
            System.out.println("EROARE!");
            e.printStackTrace();
        }
    }
    public void getSold(int cod){
        if(clienti.get(cod).getCont()!=null)
            System.out.println(clienti.get(cod).getCont().getSold());
    }
    public void listeazaClienti(){
        int counter=0;
        for(Client c:clienti){
            System.out.println(counter+". "+c.getNume()+" "+c.getPrenume());
            counter++;
        }
    }

    public void creareCont(int cod,TipCont tc){

        clienti.get(cod).setCont(tc);
        String tip;

        try {
            var stmt = connection.createStatement();
            var sql="";
            if(tc==TipCont.normal) {
                tip = "normal";
                sql = "INSERT INTO public.\"Cont\"(\n" +
                        " \"numeTitular\", iban, sold)\n" +
                        "\tVALUES ('" + clienti.get(cod).getCont().getNumeTitular() + "', '" + clienti.get(cod).getCont().getIban() + "', " + clienti.get(cod).getCont().getSold() + ");";
            }
            else if(tc==TipCont.credit) {
                tip="credit";
                sql = "INSERT INTO public.\"ContCredit\"(\n" +
                        " \"numeTitular\", iban, sold,datorie)\n" +
                        "\tVALUES ('" + clienti.get(cod).getCont().getNumeTitular() + "', '" + clienti.get(cod).getCont().getIban() + "', " + clienti.get(cod).getCont().getSold() + ", " + 0 + ");";
            }
            else if (tc==TipCont.economie) {
                tip="economie";
                sql = "INSERT INTO public.\"ContEconomie\"(\n" +
                        " \"numeTitular\", iban, sold,limita)\n" +
                        "\tVALUES ('" + clienti.get(cod).getCont().getNumeTitular() + "', '" + clienti.get(cod).getCont().getIban() + "', " + clienti.get(cod).getCont().getSold() + ", " + 0 + ");";
            }
            else
                return;


            stmt.executeUpdate(sql);
            sql="UPDATE public.\"Client\"\n" +
                    "SET \"tip\" = '"+tip+"'\n" +
                    "WHERE id = "+cod+";";
            stmt.executeUpdate(sql);
        }
        catch (SQLException e) {
            System.out.println("EROARE!");
            e.printStackTrace();
        }
    }

    public void generareCard(int cod){
        if(clienti.get(cod).getCont()==null)
            return;
        clienti.get(cod).setCard();
    }

    public void afisareConturi(){
        MapaConturi.afiseazaConturi();
    }
    public void depoziteaza(int cod,double suma){
        if(clienti.get(cod).getCont()!=null){
            clienti.get(cod).getCont().depoziteaza(suma);

        }

    }
    public void extrage(int cod,double suma){
        if(clienti.get(cod).getCont()!=null){
            clienti.get(cod).getCont().extrage(suma);

        }

    }

    public void tranzactie(int cod,String ibanDestinatar, double suma){

        Cont contDestinatar = MapaConturi.getCont(ibanDestinatar);
        Cont contExpeditor=clienti.get(cod).getCont();
        if(contDestinatar==null || contExpeditor==null)
            return;
        contExpeditor.extrage(suma);
        contDestinatar.depoziteaza(suma);
        contExpeditor.addTranzactie(contDestinatar,suma);
        contDestinatar.addTranzactie(contExpeditor,suma);
    }
    public void afiseazaTranzactii(int cod){
        if(clienti.get(cod).getCont()==null)
            return;
        Cont c=clienti.get(cod).getCont();

        if(c==null)
            return;
        List<Tranzactie>tranzactii=c.getTranzactii();
        for(Tranzactie t:tranzactii){
            System.out.println(t.afiseaza());
        }
    }

    public void creareMagazin(String denumire){
        magazine.add(new Magazin(denumire));

    }
    public void stergeMagazin(int cod){
        if(magazine.get(cod)!=null){
            magazine.remove(cod);
        }
    }
    public void afiseazaMagazine(){
        int counter=0;
        for (Magazin m: magazine){
            System.out.println(counter+". "+m.getDenumire());
            counter++;
        }
    }
    public void adaugaProdus(int cod,String denumire,double pret,int stoc){
        if(magazine.get(cod)==null)
            return;
        magazine.get(cod).addProdus(denumire, pret, stoc);
    }
    public void afiseazaProduse(int cod){
        if(magazine.get(cod)==null)
            return;
        magazine.get(cod).afiseazaProduse();
    }

    public void cumparaProdus(int codClient,int codMagazin,int codProdus){
        Cont contDestinatar = magazine.get(codMagazin).getCont();
        Cont contExpeditor=clienti.get(codClient).getCont();
        if(contDestinatar==null || contExpeditor==null)
            return;
        double suma=magazine.get(codMagazin).getProduse().get(codProdus).getPret();
        magazine.get(codMagazin).getProduse().get(codProdus).setStoc(magazine.get(codMagazin).getProduse().get(codProdus).getStoc()-1);
        contExpeditor.extrage(suma);
        contDestinatar.depoziteaza(suma);
        contExpeditor.addTranzactie(contDestinatar,suma);
        contDestinatar.addTranzactie(contExpeditor,suma);
        if(magazine.get(codMagazin).getProduse().get(codProdus).getStoc()<=0){
            magazine.get(codMagazin).deleteProdus(codProdus);
        }
    }
    public void afiseazaCard(int cod){
        if(clienti.get(cod).getCont()==null)
            return;
        Card card= clienti.get(cod).getCard();
        System.out.println("Numar card: "+card.getNumar()+"\nData expirare: "+card.getDataExpirare()+"\n CSV: "+card.getCsv());

    }
    public void platesteDatorie(int cod){
        if(clienti.get(cod).getCont()==null)
        {
            System.out.println("Nu are cont de CREDIT");
            return;
        }
        if(clienti.get(cod).getTipCont()==TipCont.credit){
            ((ContCredit)clienti.get(cod).getCont()).depoziteaza(((ContCredit)clienti.get(cod).getCont()).getDatorie());
            ((ContCredit)clienti.get(cod).getCont()).setDatorie(0);
        }
    }
    public void seteazaLimita(int cod,int limita){
        if(clienti.get(cod).getCont()==null){
            System.out.println("Nu are cont de ECONOMIE");
            return;
        }
        if(clienti.get(cod).getTipCont()==TipCont.economie){
            ((ContEconomie)clienti.get(cod).getCont()).setLimita(limita);
        }
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
        try {
            var stmt = connection.createStatement();

            var sql = "SELECT * FROM \"Client\"";
            var rezultat =stmt.executeQuery(sql);
            while(rezultat.next()){
                var stmt1 = connection.createStatement();
                Client client=(new Client(rezultat.getString("Nume"),rezultat.getString("Prenume")));

                String tip=rezultat.getString("tip");
                if(tip==null){
                    clienti.add(client);
                    continue;
                }
                if(tip.equals("normal")){
                    client.setCont(TipCont.normal);
                    var sql1="SELECT * FROM \"Cont\"";
                    var rezultat1=stmt1.executeQuery(sql1);
                    while(rezultat1.next()){
                        if(rezultat1.getString("numeTitular").equals(client.getCont().getNumeTitular())){
                            client.getCont().setIban(rezultat1.getString("iban"));
                            client.getCont().setSold(rezultat1.getDouble("sold"));
                            break;
                        }
                    }
                }
                else if(tip.equals("credit")){
                    client.setCont(TipCont.credit);
                    var sql1="SELECT * FROM \"ContCredit\"";
                    var rezultat1=stmt1.executeQuery(sql1);
                    while(rezultat1.next()){
                        if(rezultat1.getString("numeTitular").equals(client.getCont().getNumeTitular())){
                            client.getCont().setIban(rezultat1.getString("iban"));
                            client.getCont().setSold(rezultat1.getDouble("sold"));
                            ((ContCredit)client.getCont()).setDatorie(rezultat1.getDouble("datorie"));
                            break;
                        }
                    }
                }
                else if(tip.equals("economie")){
                    client.setCont(TipCont.economie);
                    var sql1="SELECT * FROM \"ContEconomie\"";
                    var rezultat1=stmt1.executeQuery(sql1);
                    while(rezultat1.next()){
                        if(rezultat1.getString("numeTitular").equals(client.getCont().getNumeTitular())){
                            client.getCont().setIban(rezultat1.getString("iban"));
                            client.getCont().setSold(rezultat1.getDouble("sold"));
                            ((ContEconomie)client.getCont()).setLimita(rezultat1.getInt("limita"));
                            break;
                        }
                    }
                }

                clienti.add(client);
            }
        }
        catch (SQLException e) {
            System.out.println("EROARE!");
            e.printStackTrace();
        }
    }

    public void salveaza(){

        try {

            int cod=0;
            for(Client c:clienti){
                var stmt = connection.createStatement();
                var sql="";
                if(c.getTipCont()==TipCont.normal){
                    sql="UPDATE public.\"Cont\"\n" +
                            "SET \"sold\" = "+c.getCont().getSold()+", \"iban\"='1111'\n" +
                            "WHERE \"numeTitular\" like '%"+c.getCont().getNumeTitular()+"%';";

                }
                else if(c.getTipCont()==TipCont.credit){
                    sql="UPDATE public.\"ContCredit\"\n" +
                            "SET \"sold\" = "+c.getCont().getSold()+", \"datorie\" = "+((ContCredit)c.getCont()).getDatorie()+"\n" +
                            "WHERE \"numeTitular\" like '"+c.getCont().getNumeTitular()+"';";
                }
                else if(c.getTipCont()==TipCont.economie){
                    sql="UPDATE public.\"ContEconomie\"\n" +
                            "SET \"sold\" = "+c.getCont().getSold()+", \"limita\" = "+((ContEconomie)c.getCont()).getLimita()+"\n" +
                            "WHERE \"numeTitular\" like '"+c.getCont().getNumeTitular()+"';";
                }
                stmt.executeUpdate(sql);
                cod++;
            }
        }
        catch (SQLException e) {
            System.out.println("EROARE!");
            e.printStackTrace();
        }
    }
}
