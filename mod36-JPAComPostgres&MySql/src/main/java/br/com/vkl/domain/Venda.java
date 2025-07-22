package br.com.vkl.domain;

import br.com.vkl.dao.Persistente;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "TB_VENDA")
public class Venda implements Persistente {

    public enum Status {
        INICIADA, CONCLUIDA, CANCELADA;

        public static Status getByName(String value) {
            for (Status status : Status.values()) {
                if (status.name().equals(value)) {
                    return status;
                }
            }
            return null;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_seq")
    @SequenceGenerator(name = "venda_seq", sequenceName = "sq_venda", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "CODIGO", length = 10, nullable = false, unique = true)
    private String codigo;

    @ManyToOne
    @JoinColumn (name = "id_cliente_fk",
            foreignKey = @ForeignKey(name = "fk_venda_cliente"),
            referencedColumnName = "id", nullable = false )
    private Cliente cliente;

    @OneToMany (mappedBy = "venda", cascade = CascadeType.ALL)
    private Set<ProdutoQuantidade> produtos;

    @Column(name = "VALORTOTAL", length = 20, nullable = false)
    private BigDecimal valorTotal;

    @Column(name = "DATAVENDA", length = 20, nullable = false)
    private Instant dataVenda;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_VENDA", length = 10, nullable = false)
    private Status status;

    public Venda(){
        produtos = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Instant getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Instant dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Set<ProdutoQuantidade> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<ProdutoQuantidade> produtos) {
        this.produtos = produtos;
    }

    private void validarStatus() {
        if (this.status == Status.CONCLUIDA) {
            throw new UnsupportedOperationException("IMPOSSÍVEL ALTERAR VENDA FINALIZADA");
        }
    }

    public void adicionarProduto(Produto produto, Integer quantidade) {
        validarStatus();
        Optional<ProdutoQuantidade> op =
                produtos.stream().filter(filter -> filter.getProduto().getCodigo().equals(produto.getCodigo())).findAny();
        if (op.isPresent()) {
            ProdutoQuantidade produtpQtd = op.get();
            produtpQtd.adicionar(quantidade);
        } else {
            // Criar fabrica para criar ProdutoQuantidade
            ProdutoQuantidade prod = new ProdutoQuantidade();
            prod.setVenda(this);
            prod.setProduto(produto);
            prod.adicionar(quantidade);
            produtos.add(prod);
        }
        recalcularValorTotalVenda();
    }

    public void recalcularValorTotalVenda() {
        //validarStatus();
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (ProdutoQuantidade prod : this.produtos) {
            valorTotal = valorTotal.add(prod.getValorTotal());
        }
        this.valorTotal = valorTotal;
    }


    public Integer getQuantidadeTotalProdutos() {
        // Soma a quantidade getQuantidade() de todos os objetos ProdutoQuantidade
        int result = produtos.stream()
                .reduce(0, (partialCountResult, prod) -> partialCountResult + prod.getQuantidade(), Integer::sum);
        return result;
    }

    public void removerProduto(Produto produto, Integer quantidade) {
        validarStatus();
        Optional<ProdutoQuantidade> op =
                produtos.stream().filter(filter -> filter.getProduto().getCodigo().equals(produto.getCodigo())).findAny();

        if (op.isPresent()) {
            ProdutoQuantidade produtpQtd = op.get();
            if (produtpQtd.getQuantidade()>quantidade) {
                produtpQtd.remover(quantidade);
                recalcularValorTotalVenda();
            } else {
                produtos.remove(op.get());
                recalcularValorTotalVenda();
            }

        }
    }

    public void removerTodosProdutos() {
        validarStatus();
        produtos.clear();
        valorTotal = BigDecimal.ZERO;
    }
}
