package com.bibiloteca.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bibiloteca.enums.RiscoProjeto;
import com.bibiloteca.enums.StatusProjeto;

@Entity
@Table(name = "projeto")
public class Projeto implements Serializable {

	private static final long serialVersionUID = -4331189134177215261L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(length = 200)
	private String nome;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio", nullable = true)
	private Date dataInicio;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_previsao_fim")
	private Date dataPrevisaoFim;
	@Temporal(TemporalType.DATE)
	@Column(name = "data_fim")
	private Date dataFim;
	@Column(length = 5000)
	private String descricao;
	@Column(length = 45)
	@Enumerated(EnumType.STRING)
	private StatusProjeto status;
	private BigDecimal orcamento;
	@Enumerated(EnumType.STRING)
	private RiscoProjeto risco;

	@OneToOne(mappedBy = "projeto")
	private Pessoa pessoa;

	public Projeto() {
		this.risco = RiscoProjeto.BaixoRisco;
		this.status = StatusProjeto.Analise;
		this.dataInicio = new Date();
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

	public Date getDataInicio() {
		return dataInicio;
	}

	public Date getDataPrevisaoFim() {
		return dataPrevisaoFim;
	}

	public void setDataPrevisaoFim(Date dataPrevisaoFim) {
		this.dataPrevisaoFim = dataPrevisaoFim;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusProjeto getStatus() {
		return status;
	}

	public void setStatus(StatusProjeto status) {
		this.status = status;
	}

	public BigDecimal getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(BigDecimal orcamento) {
		this.orcamento = orcamento;
	}

	public RiscoProjeto getRisco() {
		return risco;
	}

	public void setRisco(RiscoProjeto risco) {
		this.risco = risco;
	}

}
