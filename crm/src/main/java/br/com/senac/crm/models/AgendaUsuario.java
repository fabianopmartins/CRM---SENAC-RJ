package br.com.senac.crm.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="agenda_usuario")
public class AgendaUsuario implements Serializable {

	private static final long serialVersionUID = -3305782412320287440L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "data_inicio", nullable = false)
	private Date dataInicio;
	
	@Column(name = "data_fim", nullable = false)
	private Date dataFim;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "cliente_oferta_id")
	private ClienteOferta clienteOferta;
	
	@OneToOne
	@JoinColumn(name = "status_id")
	private Status status;
	
	@OneToOne
	@JoinColumn(name = "produto_id")
	private Produto produto;

	public AgendaUsuario() {
	
	}

	public AgendaUsuario(Integer id, Date dataInicio, Date dataFim, Usuario usuario, Cliente cliente,
			ClienteOferta clienteOferta, Status status, Produto produto) {
		super();
		this.id = id;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.usuario = usuario;
		this.cliente = cliente;
		this.clienteOferta = clienteOferta;
		this.status = status;
		this.produto = produto;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
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

	public ClienteOferta getClienteOferta() {
		return clienteOferta;
	}

	public void setClienteOferta(ClienteOferta clienteOferta) {
		this.clienteOferta = clienteOferta;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}