package br.com.senac.crm.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -4708234256760136169L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "login", nullable = false)
	private String login;
	
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@Column(name = "cargo", nullable = false)
	private String cargo;
	
	@OneToMany(mappedBy = "usuario")
	private List<AgendaUsuario> agendasUsuarios;
	
	@OneToOne
	@JoinColumn(name = "status_id")
	private Status status;

	public Usuario() {
	
	}

	public Usuario(Integer id, String nome, String login, String senha, String cargo,
			List<AgendaUsuario> agendasUsuarios, Status status) {
		super();
		this.id = id;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.cargo = cargo;
		this.agendasUsuarios = agendasUsuarios;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public List<AgendaUsuario> getAgendasUsuarios() {
		return agendasUsuarios;
	}

	public void setAgendasUsuarios(List<AgendaUsuario> agendasUsuarios) {
		this.agendasUsuarios = agendasUsuarios;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}