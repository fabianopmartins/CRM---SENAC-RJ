package br.com.senac.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.crm.models.AcaoUsuarioClienteOferta;
import br.com.senac.crm.repository.AcaoUsuarioClienteOfertaRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class AcaoUsuarioClienteOfertaServiceImpl implements AcaoUsuarioClienteOfertaService {

	@Autowired
	private AcaoUsuarioClienteOfertaRepository repository;

	public AcaoUsuarioClienteOferta search(Integer id) throws ObjectNotFoundException {
		Optional<AcaoUsuarioClienteOferta> acaoUsuarioClienteOferta = repository.findById(id);

		return acaoUsuarioClienteOferta.orElseThrow(() -> new ObjectNotFoundException(
				" não encontrado. id: " + id + ", tipo!" + AcaoUsuarioClienteOferta.class.getName()));
	}

	public List<AcaoUsuarioClienteOferta> searchAll() {
		return repository.findAll();
	}

	public AcaoUsuarioClienteOferta save(AcaoUsuarioClienteOferta acaoUsuarioClienteOferta) {
		return repository.save(acaoUsuarioClienteOferta);
	}

	public AcaoUsuarioClienteOferta edit(AcaoUsuarioClienteOferta acaoUsuarioClienteOferta)
			throws ObjectNotFoundException {
		AcaoUsuarioClienteOferta acaoUsuarioClienteOfertaAntiga = search(acaoUsuarioClienteOferta.getId());
		acaoUsuarioClienteOfertaAntiga.setDescricao(acaoUsuarioClienteOfertaAntiga.getDescricao());
		acaoUsuarioClienteOfertaAntiga.setData(acaoUsuarioClienteOfertaAntiga.getData());
		return save(acaoUsuarioClienteOfertaAntiga);
	}
}