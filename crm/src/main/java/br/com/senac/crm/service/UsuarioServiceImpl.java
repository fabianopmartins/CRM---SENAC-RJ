package br.com.senac.crm.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.senac.crm.models.Usuario;
import br.com.senac.crm.repository.UsuarioRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	
	public Usuario search(Integer id) throws ObjectNotFoundException {
		Optional<Usuario> usuario = repository.findById(id);

		return usuario.orElseThrow(
				() -> new ObjectNotFoundException("não encontrado. id: " + id + ", Tipo!" + Usuario.class.getName()));
	}

	public List<Usuario> searchAll() {
		return repository.findAll();
	}

	public Usuario save(Usuario usuario) {
		return repository.save(usuario);
	}

	public Usuario edit(Usuario usuario) throws ObjectNotFoundException {
		Usuario usuarioAntigo = search(usuario.getId());
		usuarioAntigo.setNome(usuario.getNome());
		usuarioAntigo.setLogin(usuario.getLogin());
		usuarioAntigo.setSenha(usuario.getSenha());
		usuarioAntigo.setCargo(usuario.getCargo());
		usuarioAntigo.setStatus(usuario.getStatus());
		return save(usuarioAntigo);
	}
}