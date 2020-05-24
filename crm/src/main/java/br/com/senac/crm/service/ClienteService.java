package br.com.senac.crm.service;

import java.util.List;

import br.com.senac.crm.models.Cliente;
import javassist.tools.rmi.ObjectNotFoundException;

public interface ClienteService {

	public Cliente search(Integer id) throws ObjectNotFoundException;

	public List<Cliente> searchAll();

	public Cliente save(Cliente cliente);

	public Cliente edit(Cliente cliente) throws ObjectNotFoundException;
}