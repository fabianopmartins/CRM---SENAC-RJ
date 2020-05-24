package br.com.senac.crm.service;

import java.util.List;

import br.com.senac.crm.models.ClienteOferta;
import javassist.tools.rmi.ObjectNotFoundException;

public interface ClienteOfertaService {

	public ClienteOferta search(Integer id) throws ObjectNotFoundException;

	public List<ClienteOferta> searchAll();

	public ClienteOferta save(ClienteOferta clienteOferta);

	public ClienteOferta edit(ClienteOferta clienteOferta) throws ObjectNotFoundException;
}