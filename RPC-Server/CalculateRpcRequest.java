import java.io.Serializable;

public class CalculateRpcRequest implements Serializable {

    private static final long serialVersionUID = 7503710091945320739L;

    private String s;

    public String getMessage() {
        return s;
    }

    public void setMessage(String s) {
        this.s = s;
    }
}
