package br.com.senac.crm.service;

import java.util.List;

import br.com.senac.crm.models.AcaoUsuarioCliente;
import javassist.tools.rmi.ObjectNotFoundException;

public interface AcaoUsuarioClienteService {

	public AcaoUsuarioCliente search(Integer id) throws ObjectNotFoundException;

	public List<AcaoUsuarioCliente> searchAll();

	public AcaoUsuarioCliente save(AcaoUsuarioCliente acaoUsuarioCliente);

	public AcaoUsuarioCliente edit(AcaoUsuarioCliente acaoUsuarioCliente) throws ObjectNotFoundException;
}