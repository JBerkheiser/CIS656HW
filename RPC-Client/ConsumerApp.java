import java.io.IOException;
import java.util.Scanner;

public class ConsumerApp {
    
    public static void main(String[] args) throws IOException {
        Messager messager = new MessagerRemoteImpl();
        Scanner scan = new Scanner(System.in);
        String input;
        String result = "";

        while(true)
        {
            input = scan.nextLine();
            if(input.trim().isEmpty())
            {
                break;
            }
            result = messager.messaging(input);
            System.out.println(result);
        }
        scan.close();
    }
}
