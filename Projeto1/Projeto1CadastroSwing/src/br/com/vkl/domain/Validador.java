package br.com.vkl.domain;

public class Validador {

    public static String validarGenerico(String info){
        return (info != null && !info.trim().isEmpty()) ? info : null;
    }

    public static String validarCpf(String cpf){
        if (cpf != null && (cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}") || cpf.matches("^\\d+$"))) {
            return cpf;
        }
        return null;
    }

    public static String validarTelefone(String telefone){
        if (telefone != null && (telefone.matches("^(?:\\+55\\s?)?(?:\\(?\\d{2}\\)?[\\s-]?)?(?:9[\\d-]?\\d{3}[\\s-]?\\d{4}|\\d{4}[\\s-]?\\d{4})$") || telefone.matches("^\\d+$"))) {
            return telefone;
        }
        return null;
    }

    public static String validarNumero (String numero){
        if (numero != null && (numero.matches("\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}") || numero.matches("^\\d+$"))) {
            return numero;
        }
        return null;
    }

}
