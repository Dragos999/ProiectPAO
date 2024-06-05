import java.util.Random;
public class StringRand {
    public static String generate(String input){
        char[] output=input.toCharArray();
        Random rand=new Random();
        for (int i=output.length - 1;i>0;i--){
            int index = rand.nextInt(i+1);
            char aux=output[index];
            output[index]=output[i];
            output[i]=aux;
        }
        return new String(output);
    }

    public static String generateCardNumber(){
        char[] output=new char[16];
        char[]c={'0','1','2','3','4','5','6','7','8','9'};
        Random rand=new Random();
        for (int i=0;i<16;i++){
            int index=rand.nextInt(10);
            output[i]=c[index];

        }

        return new String(output);
    }
    public static String generateCSV(){
        char[] output=new char[3];
        char[]c={'0','1','2','3','4','5','6','7','8','9'};
        Random rand=new Random();
        for (int i=0;i<3;i++){
            int index=rand.nextInt(10);
            output[i]=c[index];
        }

        return new String(output);
    }

}
