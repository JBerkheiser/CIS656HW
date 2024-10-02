import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter; 

public class ProviderApp {
    private Messager messager = new MessagerImpl();

    public static void main(String[] args) throws IOException 
    {
        new ProviderApp().begin();
    }

    private void begin() throws IOException 
    {
        try(ServerSocket listener = new ServerSocket(9090))
        {
            System.out.println("The server is running");
      
            while (true) 
            {
                Socket socket = listener.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                new Thread(clientHandler).start();
            }
        } catch(IOException e)
        {
            e.printStackTrace();
        }

    }
}

class ClientHandler implements Runnable
{
    private final Socket socket;

    public ClientHandler(Socket clientSocket)
    {
        this.socket = clientSocket;
    }

    @Override
    public void run()
    {
        try
        {
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object object = objectInputStream.readObject();

            // 
            String result = "";
            if (object instanceof CalculateRpcRequest) 
            {
                CalculateRpcRequest calculateRpcRequest = (CalculateRpcRequest) object;
                if("time".equals(calculateRpcRequest.getMessage())) 
                {
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    result = now.format(formatter);
                } 
                else
                {
                    result = calculateRpcRequest.getMessage().toUpperCase();
                }
            }

            // 
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(new String(result));
        } catch(Exception e) 
        {
            System.out.println("error!");
        } 
    }
}