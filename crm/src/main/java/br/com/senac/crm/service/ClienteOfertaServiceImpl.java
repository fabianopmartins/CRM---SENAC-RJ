package br.com.senac.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.crm.models.ClienteOferta;
import br.com.senac.crm.repository.ClienteOfertaRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteOfertaServiceImpl implements ClienteOfertaService {

	@Autowired
	private ClienteOfertaRepository repository;

	public ClienteOferta search(Integer id) throws ObjectNotFoundException {
		Optional<ClienteOferta> clienteOferta = repository.findById(id);

		return clienteOferta.orElseThrow(
				() -> new ObjectNotFoundException("não encontrado. id: " + id + ", Tipo!" + ClienteOferta.class.getName()));
	}

	public List<ClienteOferta> searchAll() {
		return repository.findAll();
	}

	public ClienteOferta save(ClienteOferta clienteOferta) {
		return repository.save(clienteOferta);
	}

	public ClienteOferta edit(ClienteOferta clienteOferta) throws ObjectNotFoundException {
		ClienteOferta clienteOfertaAntigo = search(clienteOferta.getId());
		clienteOfertaAntigo.setPreco(clienteOferta.getPreco());
		clienteOfertaAntigo.setCliente(clienteOferta.getCliente());
		clienteOfertaAntigo.setOferta(clienteOferta.getOferta());
		clienteOfertaAntigo.setStatus(clienteOferta.getStatus());
		return save(clienteOfertaAntigo);
	}
}