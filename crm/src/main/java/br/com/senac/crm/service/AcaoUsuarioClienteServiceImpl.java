package br.com.senac.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.crm.models.AcaoUsuarioCliente;
import br.com.senac.crm.repository.AcaoUsuarioClienteRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class AcaoUsuarioClienteServiceImpl implements AcaoUsuarioClienteService {

	@Autowired
	private AcaoUsuarioClienteRepository repository;

	public AcaoUsuarioCliente search(Integer id) throws ObjectNotFoundException {
		Optional<AcaoUsuarioCliente> acaoUsuarioCliente = repository.findById(id);

		return acaoUsuarioCliente.orElseThrow(() -> new ObjectNotFoundException(
				" não encontrado. id: " + id + ", tipo!" + AcaoUsuarioCliente.class.getName()));
	}

	public List<AcaoUsuarioCliente> searchAll() {
		return repository.findAll();
	}

	public AcaoUsuarioCliente save(AcaoUsuarioCliente acaoUsuarioCliente) {
		return repository.save(acaoUsuarioCliente);
	}

	public AcaoUsuarioCliente edit(AcaoUsuarioCliente acaoUsuarioCliente) throws ObjectNotFoundException {
		AcaoUsuarioCliente acaoUsuarioClienteAntiga = search(acaoUsuarioCliente.getId());
		acaoUsuarioClienteAntiga.setDescricao(acaoUsuarioClienteAntiga.getDescricao());
		acaoUsuarioClienteAntiga.setData(acaoUsuarioClienteAntiga.getData());
		return save(acaoUsuarioClienteAntiga);
	}
}