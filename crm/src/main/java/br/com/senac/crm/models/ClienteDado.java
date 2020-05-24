package br.com.senac.crm.models;

import java.io.Serializable;

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
@Table(name="cliente_dado")
public class ClienteDado implements Serializable {

	private static final long serialVersionUID = -6072336940757826203L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "valor", nullable = false)
	private double valor;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente_id;
	
	@OneToOne
	@JoinColumn(name = "dado_tipo_id")
	private DadoTipo dadoTipo;
	
	@OneToOne
	@JoinColumn(name = "status_id")
	private Status status;

	public ClienteDado() {
	
	}

	public ClienteDado(Integer id, double valor, Cliente cliente_id, DadoTipo dadoTipo, Status status) {
		super();
		this.id = id;
		this.valor = valor;
		this.cliente_id = cliente_id;
		this.dadoTipo = dadoTipo;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Cliente getCliente_id() {
		return cliente_id;
	}

	public void setCliente_id(Cliente cliente_id) {
		this.cliente_id = cliente_id;
	}

	public DadoTipo getDadoTipo() {
		return dadoTipo;
	}

	public void setDadoTipo(DadoTipo dadoTipo) {
		this.dadoTipo = dadoTipo;
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