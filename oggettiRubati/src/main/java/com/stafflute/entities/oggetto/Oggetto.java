package com.stafflute.entities.oggetto;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.github.perceptron8.datetime.jpa.LocalDateTimeToTimestampConverter;
import com.stafflute.entities.user.Utente;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import static javax.persistence.FetchType.LAZY;

import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Lob;

/**
 * Elenca gli oggetti rubati
 * @author utente
 *
 */
@Entity
public class Oggetto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    
    /**
     * Nome dell'oggetto rubato
     */
    @Column(length = 64, nullable = false)
    private String nome;
    
    /**
     * Stabilisce se l'oggetto è rubato, oppure è stato recuperato
     */
    @Column(nullable = false)
    private Boolean rubato = Boolean.TRUE;
    
    /**
     * L'utente che ha smarrito l'oggetto
     */
    @ManyToOne(optional = false)
	@PrimaryKeyJoinColumn
	private Utente utente;
    
    /**
     * Descrizione del prodotto
     */
    @Basic(fetch = LAZY)
	@Lob
	private String descrizione;
    
    /**
     * Data di inserimento dell'oggetto rubato
     */
    @Column(nullable = false, updatable = false)
    @Convert(converter = LocalDateTimeToTimestampConverter.class)
	private LocalDateTime dataInserimento = LocalDateTime.now();

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

	public Boolean getRubato() {
		return rubato;
	}

	public void setRubato(Boolean rubato) {
		this.rubato = rubato;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDateTime getDataInserimento() {
		return dataInserimento;
	}

	public void setDataInserimento(LocalDateTime dataInserimento) {
		this.dataInserimento = dataInserimento;
	}
}
