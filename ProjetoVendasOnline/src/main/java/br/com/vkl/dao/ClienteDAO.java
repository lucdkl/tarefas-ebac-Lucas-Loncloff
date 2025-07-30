/**
 * 
 */
package br.com.vkl.dao;

import javax.ejb.Stateless;

import br.com.vkl.dao.generic.GenericDAO;
import br.com.vkl.domain.Cliente;

/**
 * @author rodrigo.pires
 *
 */
@Stateless
public class ClienteDAO extends GenericDAO<Cliente, Long> implements IClienteDAO {

	public ClienteDAO() {
		super(Cliente.class);
	}

}
