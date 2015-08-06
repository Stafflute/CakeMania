package com.stafflute.entities.user;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;

/**
 * I ruoli che un utente può assumere
 * @author utente
 *
 */
@Entity
public class Ruolo {
	
	/**
	 * Il nome mnemonico del ruolo
	 */
	@Id
	@Column(nullable = false, length = 32)
	private String nome;
	
	/**
	 * La descrizione completa del ruolo
	 */
	@Basic(fetch = FetchType.LAZY)
	private String descrizione;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	
}
