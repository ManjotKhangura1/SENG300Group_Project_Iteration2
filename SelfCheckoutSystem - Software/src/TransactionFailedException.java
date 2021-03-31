import java.io.IOException;



public class TransactionFailedException extends IOException {
    public TransactionFailedException (String errorMessage) {
        super(errorMessage);
    }
}

