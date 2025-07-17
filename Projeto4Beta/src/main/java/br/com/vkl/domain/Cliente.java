package br.com.vkl.domain;


import br.com.vkl.dao.Persistente;

import javax.persistence.*;


@Entity
@Table(name = "TB_CLIENTE")
public class Cliente implements Persistente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
    @SequenceGenerator(name = "cliente_seq", sequenceName = "sq_cliente", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "CPF", length = 10, nullable = false, unique = true)
    private String cpf;

    @Column(name = "NOME", length = 50, nullable = false)
    private String nome;

    @Column(name = "TEL", length = 20, nullable = true)
    private String tel;

    @Column(name = "END", length = 30, nullable = true)
    private String end;

    @Column(name = "NUMERO", length = 10, nullable = true)
    private String numero;

    @Column(name = "CIDADE", length = 20, nullable = true)
    private String cidade;

    @Column(name = "estado", length = 20, nullable = true)
    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
