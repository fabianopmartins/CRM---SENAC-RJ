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
@Table(name="dado_tipo")
public class DadoTipo implements Serializable {

	private static final long serialVersionUID = 8485269595872007449L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@Column(name = "obrigatorio")
	private String obrigatorio;
	
	@Column(name = "padrao")
	private String padrao;
	
	@Column(name = "mascara")
	private String mascara;
	
	@ManyToOne
	@JoinColumn(name = "categoria_dado_id")
	private CategoriaDado categoria_dado_id;
	
	@OneToOne
	@JoinColumn(name = "status_id")
	private Status status;

	public DadoTipo() {
	
	}

	public DadoTipo(Integer id, String descricao, String obrigatorio, String padrao, String mascara,
			CategoriaDado categoria_dado_id, Status status) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.obrigatorio = obrigatorio;
		this.padrao = padrao;
		this.mascara = mascara;
		this.categoria_dado_id = categoria_dado_id;
		this.status = status;
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

	public String getObrigatorio() {
		return obrigatorio;
	}

	public void setObrigatorio(String obrigatorio) {
		this.obrigatorio = obrigatorio;
	}

	public String getPadrao() {
		return padrao;
	}

	public void setPadrao(String padrao) {
		this.padrao = padrao;
	}

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

	public CategoriaDado getCategoria_dado_id() {
		return categoria_dado_id;
	}

	public void setCategoria_dado_id(CategoriaDado categoria_dado_id) {
		this.categoria_dado_id = categoria_dado_id;
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