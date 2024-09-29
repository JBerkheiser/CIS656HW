import java.io.IOException;
import java.util.Scanner;

public class ConsumerApp {
    
    public static void main(String[] args) throws IOException {
        Messager messager = new MessagerRemoteImpl();
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        String result = "";

        do {
            result = messager.messaging(input);
            System.out.println(result);
            input = scan.next();
        } while(!input.isEmpty());   

        scan.close();
    }
}
