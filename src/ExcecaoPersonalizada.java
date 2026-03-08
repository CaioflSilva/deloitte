public class ExcecaoPersonalizada extends RuntimeException {

    // Construtor com mensagem
    public ExcecaoPersonalizada(String message) {
        super(message);
    }

    // Construtor com mensagem e causa
    public ExcecaoPersonalizada(String message, Throwable cause) {
        super(message, cause);
    }
}