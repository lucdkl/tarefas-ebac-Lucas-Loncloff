package br.com.vkl.repository;

import br.com.vkl.domain.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends CrudRepository<Cliente, Long> {
}
