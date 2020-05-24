package br.com.senac.crm.service;

import java.util.List;

import br.com.senac.crm.models.Acao;
import javassist.tools.rmi.ObjectNotFoundException;

public interface AcaoService {

	public Acao search(Integer id) throws ObjectNotFoundException;

	public List<Acao> searchAll();

	public Acao save(Acao acao);

	public Acao edit(Acao acao) throws ObjectNotFoundException;
}