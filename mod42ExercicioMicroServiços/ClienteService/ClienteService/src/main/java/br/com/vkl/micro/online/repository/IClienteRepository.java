package br.com.vkl.micro.online.repository;

import br.com.vkl.micro.online.domain.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IClienteRepository extends MongoRepository<Cliente, String> {

    Optional<Cliente> findByCpf(Long cpf);


}
