import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.io.*;
public class Audit {
    public void write(String actiune){

        try{
            FileWriter out=null;
            out=new FileWriter(new File("informatii.csv"),true);
            String linie=actiune+","+ new SimpleDateFormat("HH:mm:ss(yyyy/MM/dd)").format(new Date())+"\n";
            out.write(linie);
            out.close();
        }catch(FileNotFoundException e){
            System.out.println("Fisierul de audit nu a fost gasit!");
        }catch(IOException e){
            System.out.println("Eroare la scrierea in fisierul de audit!");
        }


    }
}
