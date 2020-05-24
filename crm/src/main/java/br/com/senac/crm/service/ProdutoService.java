package br.com.senac.crm.service;

import java.util.List;

import br.com.senac.crm.models.Produto;
import javassist.tools.rmi.ObjectNotFoundException;

public interface ProdutoService {

	public Produto search(Integer id) throws ObjectNotFoundException;

	public List<Produto> searchAll();

	public Produto save(Produto produto);

	public Produto edit(Produto produto) throws ObjectNotFoundException;
}