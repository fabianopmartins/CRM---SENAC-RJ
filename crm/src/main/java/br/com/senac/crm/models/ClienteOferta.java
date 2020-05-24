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

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name ="cliente_oferta")
public class ClienteOferta implements Serializable {

	private static final long serialVersionUID = -9219307746569171872L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name= "id")
	private Integer id;
	
	@Column(name = "preco", nullable = false)
	private double preco;
	
	@OneToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(name = "oferta_id")
	private Oferta oferta;
	
	@OneToMany(mappedBy = "clienteOferta")
	@Cascade(CascadeType.ALL)
	private List<AgendaUsuario> agendaUsuarios;
	
	@OneToMany(mappedBy = "clienteOferta")
	private List<AcaoUsuarioClienteOferta> acaoUsuarioClienteOfertas;
	
	@OneToOne
	@JoinColumn(name = "status_id")
	private Status status;
	
	public ClienteOferta() {
	
	}
	
	public ClienteOferta(Integer id, double preco, Cliente cliente, Oferta oferta, List<AgendaUsuario> agendaUsuarios,
			List<AcaoUsuarioClienteOferta> acaoUsuarioClienteOfertas, Status status) {
		super();
		this.id = id;
		this.preco = preco;
		this.cliente = cliente;
		this.oferta = oferta;
		this.agendaUsuarios = agendaUsuarios;
		this.acaoUsuarioClienteOfertas = acaoUsuarioClienteOfertas;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Oferta getOferta() {
		return oferta;
	}

	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}

	public List<AgendaUsuario> getAgendaUsuarios() {
		return agendaUsuarios;
	}

	public void setAgendaUsuarios(List<AgendaUsuario> agendaUsuarios) {
		this.agendaUsuarios = agendaUsuarios;
	}

	public List<AcaoUsuarioClienteOferta> getAcaoUsuarioClienteOfertas() {
		return acaoUsuarioClienteOfertas;
	}

	public void setAcaoUsuarioClienteOfertas(List<AcaoUsuarioClienteOferta> acaoUsuarioClienteOfertas) {
		this.acaoUsuarioClienteOfertas = acaoUsuarioClienteOfertas;
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