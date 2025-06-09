package main.java.br.com.vkl.exceptions;

public class TipoElementoDesconhecidoException extends RuntimeException {
    private static final long serialVersionUID = -2268140970978666251L;

    public TipoElementoDesconhecidoException(String msg) {
        this(msg, null);
    }

    public TipoElementoDesconhecidoException(String msg, Throwable e) {
        super(msg, e);
    }
}
