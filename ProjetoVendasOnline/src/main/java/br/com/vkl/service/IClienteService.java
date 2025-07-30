/**
 * 
 */
package br.com.vkl.service;

import br.com.vkl.domain.Cliente;
import br.com.vkl.exceptions.DAOException;
import br.com.vkl.services.generic.IGenericService;

/**
 * @author rodrigo.pires
 *
 */
public interface IClienteService extends IGenericService<Cliente, Long> {

	Cliente buscarPorCPF(Long cpf) throws DAOException;

}
