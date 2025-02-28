package br.com.vkl;

public class PessoaJuridica extends Pessoa{

    private Long cnpj;

    public void setCnpj(Long cnpj) {
        this.cnpj = cnpj;
        this.setTipo("Juridica");
    }

    @Override
    public Long getDoc() {
        return cnpj;
    }
}
