package br.com.senac.crm.service;

import java.util.List;

import br.com.senac.crm.models.AcaoUsuarioClienteOferta;
import javassist.tools.rmi.ObjectNotFoundException;

public interface AcaoUsuarioClienteOfertaService {

	public AcaoUsuarioClienteOferta search(Integer id) throws ObjectNotFoundException;

	public List<AcaoUsuarioClienteOferta> searchAll();

	public AcaoUsuarioClienteOferta save(AcaoUsuarioClienteOferta acaoUsuarioClienteOferta);

	public AcaoUsuarioClienteOferta edit(AcaoUsuarioClienteOferta AcaoUsuarioClienteOfertaService)
			throws ObjectNotFoundException;
}