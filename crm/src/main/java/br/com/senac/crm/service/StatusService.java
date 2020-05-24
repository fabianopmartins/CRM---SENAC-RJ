package br.com.senac.crm.service;

import java.util.List;

import br.com.senac.crm.models.Status;
import javassist.tools.rmi.ObjectNotFoundException;

public interface StatusService {

	public Status search(Integer id) throws ObjectNotFoundException;

	public List<Status> searchAll();

	public Status save(Status status);

	public Status edit(Status status) throws ObjectNotFoundException;
}