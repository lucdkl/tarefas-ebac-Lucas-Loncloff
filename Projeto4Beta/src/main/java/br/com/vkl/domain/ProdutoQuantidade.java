package br.com.vkl.domain;

import br.com.vkl.dao.Persistente;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TB_PRODUTOQUANT" )
public class ProdutoQuantidade implements Persistente {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produtoquant_seq")
    @SequenceGenerator(name = "produtoquant_seq", sequenceName = "sq_produtoquant", initialValue = 1, allocationSize = 1)
    private Long id;

//    private Produto produto;

    @Column(name = "QUANTIDADE", length = 20, nullable = false)
    private Integer quantidade;

    @Column(name = "VALORTOTAL", length = 20, nullable = false)
    private BigDecimal valorTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
