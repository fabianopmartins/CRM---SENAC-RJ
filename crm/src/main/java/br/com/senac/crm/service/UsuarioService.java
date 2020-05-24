package br.com.senac.crm.service;

import java.util.List;

import br.com.senac.crm.models.Usuario;
import javassist.tools.rmi.ObjectNotFoundException;

public interface UsuarioService {

	public Usuario search(Integer id) throws ObjectNotFoundException;

	public List<Usuario> searchAll();

	public Usuario save(Usuario usuario);

	public Usuario edit(Usuario usuario) throws ObjectNotFoundException;
}