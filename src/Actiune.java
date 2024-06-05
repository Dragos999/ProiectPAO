import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Actiune implements ActionListener {
    private String comanda;
    JPanel panel;
    public Actiune(String comanda,JPanel panel) {
        this.comanda = comanda;
        this.panel=panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (comanda) {
            case "Creaza client":
                JOptionPane.showMessageDialog(null, "Comanda: Creaza client");

                panel.setLayout(new GridLayout(3,1));
                JPanel t1=new JPanel();
                JPanel t2=new JPanel();
                panel.add(t1);
                panel.add(t2);
                JLabel label1=new JLabel("Nume:");
                JLabel label2=new JLabel("Prenume: ");
                JTextField text1=new JTextField(20);
                JTextField text2=new JTextField(20);
                t1.add(label1);
                t1.add(text1);
                t2.add(label2);
                t2.add(text2);
                panel.updateUI();
                break;
            case "Sterge client":
                JOptionPane.showMessageDialog(null, "Comanda: Sterge client");
                break;
            case "Vezi sold":
                JOptionPane.showMessageDialog(null, "Comanda: Vezi sold");
                break;
            case "Afiseaza clienti":
                JOptionPane.showMessageDialog(null, "Comanda: Afiseaza clienti");
                break;
            case "Creaza cont":
                JOptionPane.showMessageDialog(null, "Comanda: Creaza cont");
                break;
            case "Afiseaza conturi":
                JOptionPane.showMessageDialog(null, "Comanda: Afiseaza conturi");
                break;
            case "Genereaza Card":
                JOptionPane.showMessageDialog(null, "Comanda: Genereaza Card");
                break;
            case "Afiseaza card":
                JOptionPane.showMessageDialog(null, "Comanda: Afiseaza card");
                break;
            case "Depoziteaza in cont":
                JOptionPane.showMessageDialog(null, "Comanda: Depoziteaza in cont");
                break;
            case "Extrage din cont":
                JOptionPane.showMessageDialog(null, "Comanda: Extrage din cont");
                break;
            case "Executa tranzactie":
                JOptionPane.showMessageDialog(null, "Comanda: Executa tranzactie");
                break;
            case "Afiseaza tranzactii":
                JOptionPane.showMessageDialog(null, "Comanda: Afiseaza tranzactii");
                break;
            case "Creaza magazin":
                JOptionPane.showMessageDialog(null, "Comanda: Creaza magazin");
                break;
            case "Sterge magazin":
                JOptionPane.showMessageDialog(null, "Comanda: Sterge magazin");
                break;
            case "Creaza produs":
                JOptionPane.showMessageDialog(null, "Comanda: Creaza produs");
                break;
            case "Afiseaza produse":
                JOptionPane.showMessageDialog(null, "Comanda: Afiseaza produse");
                break;
            case "Cumpara produs":
                JOptionPane.showMessageDialog(null, "Comanda: Cumpara produs");
                break;
            case "Plateste datorie":
                JOptionPane.showMessageDialog(null, "Comanda: Plateste datorie");
                break;
            case "Seteaza limita":
                JOptionPane.showMessageDialog(null, "Comanda: Seteaza limita");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Comanda necunoscută");
                break;
        }
    }
}
