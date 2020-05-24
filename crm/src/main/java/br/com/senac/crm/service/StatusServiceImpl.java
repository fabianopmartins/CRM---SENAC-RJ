package br.com.senac.crm.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.senac.crm.models.Status;
import br.com.senac.crm.repository.StatusRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class StatusServiceImpl implements StatusService {

	@Autowired
	private StatusRepository repository;

	public Status search(Integer id) throws ObjectNotFoundException {
		Optional<Status> Status = repository.findById(id);

		return Status.orElseThrow(
				() -> new ObjectNotFoundException("não encontrado. id: " + id + ", Tipo!" + Status.class.getName()));
	}

	public List<Status> searchAll() {
		return repository.findAll();
	}

	public Status save(Status status) {
		return repository.save(status);
	}

	public Status edit(Status status) throws ObjectNotFoundException {
		Status statusAntigo = search(status.getId());
		statusAntigo.setDescricao(status.getDescricao());
		return save(statusAntigo);
	}
}