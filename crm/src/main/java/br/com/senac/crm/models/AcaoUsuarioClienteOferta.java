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
@Table(name ="acao_usuario_cliente_oferta")
public class AcaoUsuarioClienteOferta implements Serializable {

	private static final long serialVersionUID = -597824122752742130L;

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
	@JoinColumn(name = "acao_id")
	private Acao acao;
	
	@OneToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@OneToOne
	@JoinColumn(name = "cliente_oferta_id")
	private ClienteOferta clienteOferta;
	

	public AcaoUsuarioClienteOferta() {
	
	}

	public AcaoUsuarioClienteOferta(Integer id, String descricao, Date data, Acao acao, Usuario usuario,
			ClienteOferta clienteOferta) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.data = data;
		this.acao = acao;
		this.usuario = usuario;
		this.clienteOferta = clienteOferta;
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

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ClienteOferta getClienteOferta() {
		return clienteOferta;
	}

	public void setClienteOferta(ClienteOferta clienteOferta) {
		this.clienteOferta = clienteOferta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}