import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MessagerRemoteImpl implements Messager {
    public static final int PORT = 9090;
 
    public String messaging(String message) {
        try {
            Socket socket = new Socket("127.0.0.1", PORT);

            // 
            CalculateRpcRequest calculateRpcRequest = generateRequest(message);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            // 
            objectOutputStream.writeObject(calculateRpcRequest);

            // 
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object response = objectInputStream.readObject();

            if (response instanceof String) {
            	socket.close();
                return (String) response;
                
            } else {
            	socket.close();
                throw new InternalError();
                
            }
            
        } catch (Exception e) {
         
            throw new InternalError();
        }       
    }

    private CalculateRpcRequest generateRequest(String myString) {
        CalculateRpcRequest calculateRpcRequest = new CalculateRpcRequest();
        calculateRpcRequest.setMessage(myString);
        return calculateRpcRequest;
    }
}
