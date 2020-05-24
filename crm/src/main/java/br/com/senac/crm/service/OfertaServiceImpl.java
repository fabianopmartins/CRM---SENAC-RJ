package br.com.senac.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.crm.models.Oferta;
import br.com.senac.crm.repository.OfertaRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class OfertaServiceImpl implements OfertaService {

	@Autowired
	private OfertaRepository repository;

	public Oferta search(Integer id) throws ObjectNotFoundException {
		Optional<Oferta> oferta = repository.findById(id);

		return oferta.orElseThrow(
				() -> new ObjectNotFoundException("não encontrado. id: " + id + ", Tipo!" + Oferta.class.getName()));
	}

	public List<Oferta> searchAll() {
		return repository.findAll();
	}

	public Oferta save(Oferta oferta) {
		return repository.save(oferta);
	}

	public Oferta edit(Oferta oferta) throws ObjectNotFoundException {
		Oferta ofertaAntiga = search(oferta.getId());
		ofertaAntiga.setDescricao(oferta.getDescricao());
		ofertaAntiga.setDataInicio(oferta.getDataInicio());
		ofertaAntiga.setDataFim(oferta.getDataFim());
		ofertaAntiga.setPreco(oferta.getPreco());
		ofertaAntiga.setStatus(oferta.getStatus());
		ofertaAntiga.setProduto(oferta.getProduto());
		return save(ofertaAntiga);
	}
}