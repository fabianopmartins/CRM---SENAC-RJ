package br.com.senac.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.crm.models.NivelInstrucao;
import br.com.senac.crm.repository.NivelInstrucaoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class NivelInstrucaoServiceImpl implements NivelInstrucaoService {
	@Autowired
	private NivelInstrucaoRepository repository;

	public NivelInstrucao search(Integer id) throws ObjectNotFoundException {
		Optional<NivelInstrucao> instrucao = repository.findById(id);

		return instrucao.orElseThrow(() -> new ObjectNotFoundException(
				" não encontrado. id: " + id + ", tipo!" + NivelInstrucao.class.getName()));
	}

	public List<NivelInstrucao> searchAll() {
		return repository.findAll();
	}

	public NivelInstrucao save(NivelInstrucao instrucao) {
		return repository.save(instrucao);
	}

	public NivelInstrucao edit(NivelInstrucao instrucao) throws ObjectNotFoundException {
		NivelInstrucao instrucaoAntiga = search(instrucao.getId());
		instrucaoAntiga.setDescricao(instrucao.getDescricao());
		instrucaoAntiga.setStatus(instrucao.getStatus());
		return save(instrucaoAntiga);
	}
}