package br.com.senac.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.crm.models.Cliente;
import br.com.senac.crm.repository.ClienteRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente search(Integer id) throws ObjectNotFoundException {
		Optional<Cliente> cliente = repository.findById(id);

		return cliente.orElseThrow(
				() -> new ObjectNotFoundException("não encontrado. id: " + id + ", Tipo!" + Cliente.class.getName()));
	}

	public List<Cliente> searchAll() {
		return repository.findAll();
	}

	public Cliente save(Cliente cliente) {
		return repository.save(cliente);
	}

	public Cliente edit(Cliente cliente) throws ObjectNotFoundException {
		Cliente clienteAntigo = search(cliente.getId());
		clienteAntigo.setCpf(cliente.getCpf());
		clienteAntigo.setNome(cliente.getNome());
		clienteAntigo.setSobrenome(cliente.getSobrenome());
		clienteAntigo.setEmail(cliente.getEmail());
		clienteAntigo.setTelefone(cliente.getTelefone());
		clienteAntigo.setStatus(cliente.getStatus());
		return save(clienteAntigo);
	}
}