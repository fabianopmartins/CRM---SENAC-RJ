package br.com.senac.crm.service;

import java.util.List;

import br.com.senac.crm.models.NivelInstrucao;
import javassist.tools.rmi.ObjectNotFoundException;

public interface NivelInstrucaoService {

	public NivelInstrucao search(Integer id) throws ObjectNotFoundException;

	public List<NivelInstrucao> searchAll();

	public NivelInstrucao save(NivelInstrucao nivelInstrucao);

	public NivelInstrucao edit(NivelInstrucao NivelInstrucaoao) throws ObjectNotFoundException;	
}