package br.com.senac.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.crm.models.Acao;
import br.com.senac.crm.repository.AcaoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class AcaoServiceImpl implements AcaoService{
	@Autowired
	private AcaoRepository repository;

	public Acao search(Integer id) throws ObjectNotFoundException {
		Optional<Acao> acao = repository.findById(id);

		return acao.orElseThrow(
				() -> new ObjectNotFoundException(" não encontrado. id: " + id + ", tipo!" + Acao.class.getName()));
	}

	public List<Acao> searchAll() {
		return repository.findAll();
	}

	public Acao save(Acao acao) {
		return repository.save(acao);
	}

	public Acao edit(Acao acao) throws ObjectNotFoundException {
		Acao acaoAntiga = search(acao.getId());
		acaoAntiga.setDescricao(acao.getDescricao());
		acaoAntiga.setStatus(acao.getStatus());
		return save(acaoAntiga);
	}
}