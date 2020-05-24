package br.com.senac.crm.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "acao_usuario_cliente")
public class AcaoUsuarioCliente implements Serializable {

	private static final long serialVersionUID = -7418629267975016169L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "descricao", nullable = false)
	private String descricao;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(nullable = false)
	private Date data;

	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToOne
	@JoinColumn(name = "acao_id")
	private Acao acao;

	public AcaoUsuarioCliente() {

	}

	public AcaoUsuarioCliente(Integer id, String descricao, Date data, Usuario usuario, Cliente cliente, Acao acao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.data = data;
		this.usuario = usuario;
		this.cliente = cliente;
		this.acao = acao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}