package br.com.vkl;

public class PessoaFisica extends Pessoa{

    private Long cpf;

    public void setCpf(Long cpf) {
        this.cpf = cpf;
        this.setTipo("Fisica");
    }
    @Override
    public Long getDoc() {
        return cpf;
    }
}
