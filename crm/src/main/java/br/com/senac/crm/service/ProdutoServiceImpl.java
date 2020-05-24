package br.com.senac.crm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senac.crm.models.Produto;
import br.com.senac.crm.repository.ProdutoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	public Produto search(Integer id) throws ObjectNotFoundException {
		Optional<Produto> produto = repository.findById(id);

		return produto.orElseThrow(
				() -> new ObjectNotFoundException("não encontrado. id: " + id + ", Tipo!" + Produto.class.getName()));
	}

	public List<Produto> searchAll() {
		return repository.findAll();
	}

	public Produto save(Produto produto) {
		return repository.save(produto);
	}

	public Produto edit(Produto produto) throws ObjectNotFoundException {
		Produto produtoAntigo = search(produto.getId());
		produtoAntigo.setNome(produto.getNome());
		produtoAntigo.setDescricao(produto.getDescricao());
		produtoAntigo.setStatus(produto.getStatus());
		produtoAntigo.setNivelInstrucao(produto.getNivelInstrucao());
		return save(produtoAntigo);
	}
}