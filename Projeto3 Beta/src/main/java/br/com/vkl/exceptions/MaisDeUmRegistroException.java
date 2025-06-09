package main.java.br.com.vkl.exceptions;

public class MaisDeUmRegistroException extends RuntimeException {

    private static final long serialVersionUID = -7509649433607067138L;

    public MaisDeUmRegistroException(String msg) {
        super(msg);
    }
}
