import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Main {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "123456";
        Audit a=new Audit();

        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("OK");


            Scanner scan = new Scanner(System.in);
            myService service = new myService();
            service.setConnection(connection);
            boolean continua = true;
            String nume, prenume;
            int cod, limita, stoc, codMagazin, codProdus;
            double suma, pret;
            String ibanDestinatar, denumire;


            while (continua) {

                System.out.println("1.Creaza client\n" +
                        "2.Sterge client\n" +
                        "3.Vezi sold\n" +
                        "4.Afiseaza clienti\n" +
                        "5.Creaza cont\n" +
                        "6.Afiseaza conturi\n" +
                        "7.Genereaza Card\n" +
                        "8.Afiseaza card\n" +
                        "9.Depoziteaza in cont\n" +
                        "10.Extrage din cont\n" +
                        "11.Executa tranzactie\n" +
                        "12.Afiseaza tranzactii\n" +
                        "13.Creaza magazin\n" +
                        "14.Sterge magazin\n" +
                        "15.Adauga produs\n" +
                        "16.Afiseaza produse\n" +
                        "17.Cumpara produs\n" +
                        "18.Plateste datorie\n" +
                        "19.Seteaza limita\n" +
                        "20.Afiseaza magazine");
                int comanda = Integer.parseInt(scan.nextLine());
                switch (comanda) {
                    case 0:
                        continua = false;
                        service.salveaza();
                        break;
                    case 1:
                        a.write("1.Creaza client");
                        System.out.println("Nume:");
                        nume = scan.nextLine();
                        System.out.println("Prenume:");
                        prenume = scan.nextLine();
                        service.creareClient(nume, prenume);
                        break;
                    case 2:
                        a.write("2.Sterge client");
                        System.out.println("Codul clientului:");
                        cod = Integer.parseInt(scan.nextLine());
                        service.stergeClient(cod);
                        break;
                    case 3:
                        a.write("3.Vezi sold");
                        System.out.println("Codul clientului:");
                        cod = Integer.parseInt(scan.nextLine());
                        service.getSold(cod);
                        break;
                    case 4:
                        a.write("4.Afiseaza clienti");
                        service.listeazaClienti();
                        break;
                    case 5:
                        a.write("5.Creaza cont");
                        System.out.println("Codul clientului:");
                        cod = Integer.parseInt(scan.nextLine());
                        System.out.println("Tip cont:\n1.Normal\n2.Economie\n3.Credit");
                        int nr = Integer.parseInt(scan.nextLine());
                        System.out.println(nr);
                        if (nr == 1) {

                            service.creareCont(cod, TipCont.normal);
                        } else if (nr == 2) {
                            service.creareCont(cod, TipCont.economie);
                        } else if (nr == 3) {
                            service.creareCont(cod, TipCont.credit);
                        }
                        break;
                    case 6:
                        a.write("6.Afiseaza conturi");
                        service.afisareConturi();
                        break;
                    case 7:
                        a.write("7.Genereaza Card");
                        System.out.println("Codul clientului:");
                        cod = Integer.parseInt(scan.nextLine());
                        service.generareCard(cod);
                        break;
                    case 8:
                        a.write("8.Afiseaza card");
                        System.out.println("Codul clientului:");
                        cod = Integer.parseInt(scan.nextLine());
                        service.afiseazaCard(cod);
                        break;
                    case 9:
                        a.write("9.Depoziteaza in cont");
                        System.out.println("Codul clientului:");
                        cod = Integer.parseInt(scan.nextLine());
                        System.out.println("Suma de bani:");
                        suma = Double.parseDouble(scan.nextLine());
                        service.depoziteaza(cod, suma);
                        break;
                    case 10:
                        a.write("10.Extrage din cont");
                        System.out.println("Codul clientului:");
                        cod = Integer.parseInt(scan.nextLine());
                        System.out.println("Suma de bani:");
                        suma = Double.parseDouble(scan.nextLine());
                        service.extrage(cod, suma);
                        break;
                    case 11:
                        a.write("11.Executa tranzactie");
                        System.out.println("Codul clientului:");
                        cod = Integer.parseInt(scan.nextLine());
                        System.out.println("Ibanul destinatarului:");
                        ibanDestinatar = scan.nextLine();
                        System.out.println("Suma de trimis:");
                        suma = Double.parseDouble(scan.nextLine());
                        service.tranzactie(cod, ibanDestinatar, suma);
                        break;
                    case 12:
                        a.write("12.Afiseaza tranzactii");
                        System.out.println("Codul clientului:");
                        cod = Integer.parseInt(scan.nextLine());
                        service.afiseazaTranzactii(cod);
                        break;
                    case 13:
                        a.write("13.Creaza magazin");
                        System.out.println("Denumirea magazinului");
                        denumire = scan.nextLine();
                        service.creareMagazin(denumire);
                        break;
                    case 14:
                        a.write("14.Sterge magazin");
                        System.out.println("Codul magazinului:");
                        cod = Integer.parseInt(scan.nextLine());
                        service.stergeMagazin(cod);
                        break;
                    case 15:
                        a.write("15.Adauga produs");
                        System.out.println("Codul magazinului:");
                        cod = Integer.parseInt(scan.nextLine());
                        System.out.println("Denumirea produsului");
                        denumire = scan.nextLine();
                        System.out.println("Pretul produsului:");
                        pret = Double.parseDouble(scan.nextLine());
                        System.out.println("Stocul produsului:");
                        stoc = Integer.parseInt(scan.nextLine());
                        service.adaugaProdus(cod, denumire, pret, stoc);
                        break;
                    case 16:
                        a.write("16.Afiseaza produse");
                        System.out.println("Codul magazinului:");
                        cod = Integer.parseInt(scan.nextLine());
                        service.afiseazaProduse(cod);
                        break;
                    case 17:
                        a.write("17.Cumpara produs");
                        System.out.println("Codul clientului:");
                        cod = Integer.parseInt(scan.nextLine());
                        System.out.println("Codul magazinului:");
                        codMagazin = Integer.parseInt(scan.nextLine());
                        System.out.println("Codul produsului:");
                        codProdus = Integer.parseInt(scan.nextLine());
                        service.cumparaProdus(cod, codMagazin, codProdus);
                        break;
                    case 18:
                        a.write("18.Plateste datorie");
                        System.out.println("Codul clientului (trebuie sa aibe cont de tip CREDIT):");
                        cod = Integer.parseInt(scan.nextLine());
                        service.platesteDatorie(cod);
                        break;
                    case 19:
                        a.write("19.Seteaza limita");
                        System.out.println("Codul clientului (trebuie sa aibe cont de tip ECONOMIE):");
                        cod = Integer.parseInt(scan.nextLine());
                        System.out.println("Noua limita:");
                        limita = Integer.parseInt(scan.nextLine());
                        service.seteazaLimita(cod, limita);
                        break;
                    case 20:
                        a.write("20.Afiseaza magazine33");
                        service.afiseazaMagazine();
                        break;
                    default:
                        System.out.println("Comanda invalida");

                        break;
                }
            }
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("EROARE!");
            e.printStackTrace();
        }

    }
}