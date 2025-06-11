create table tb_produto_estoque(
	id bigint,
	id_produto_fk bigint not null,
	quantidade int not null,
	constraint pk_id_prod_estoque primary key(id),
	constraint fk_id_prod_estoque foreign key(id_produto_fk) references tb_produto(id) on delete cascade
);

ALTER TABLE tb_produto_estoque
DROP CONSTRAINT fk_id_prod_estoque,
ADD CONSTRAINT fk_id_prod_estoque 
    FOREIGN KEY (id_produto_fk) 
    REFERENCES tb_produto(id) 
    ON DELETE CASCADE;

truncate tb_produto cascade

create sequence sq_estoque
start 1
increment 1
owned by tb_produto_estoque.id;

truncate tb_cliente cascade;
truncate tb_produto cascade;

select * from tb_produto;

select * from tb_produto_estoque;

SELECT * FROM TB_PRODUTO_ESTOQUE WHERE id_produto_fk = 163;
