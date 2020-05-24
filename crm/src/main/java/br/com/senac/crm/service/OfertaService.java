package br.com.senac.crm.service;

import java.util.List;

import br.com.senac.crm.models.Oferta;
import javassist.tools.rmi.ObjectNotFoundException;

public interface OfertaService {

	public Oferta search(Integer id) throws ObjectNotFoundException;

	public List<Oferta> searchAll();

	public Oferta save(Oferta oferta);

	public Oferta edit(Oferta oferta) throws ObjectNotFoundException;
}