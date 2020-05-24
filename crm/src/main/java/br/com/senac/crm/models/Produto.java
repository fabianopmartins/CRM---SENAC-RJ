package br.com.senac.crm.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "produto")
public class Produto implements Serializable {

	private static final long serialVersionUID = -3972888409195220788L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name= "nome")
	private String nome;
	
	@Column(name = "descricao")
	private String descricao;
	
	@OneToOne
	@JoinColumn(name = "nivel_instrucao_id")
	private NivelInstrucao nivelInstrucao;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private List<Oferta> ofertas;
	
	@OneToOne
	@JoinColumn(name = "status_id")
	private Status status;

	public Produto() {
	}

	public Produto(Integer id, String nome, String descricao, NivelInstrucao nivelInstrucao, List<Oferta> ofertas,
			Status status) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.nivelInstrucao = nivelInstrucao;
		this.ofertas = ofertas;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public NivelInstrucao getNivelInstrucao() {
		return nivelInstrucao;
	}

	public void setNivelInstrucao(NivelInstrucao nivelInstrucao) {
		this.nivelInstrucao = nivelInstrucao;
	}

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
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