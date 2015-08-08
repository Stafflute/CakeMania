package com.stafflute.entities.user;

import java.util.Set;

import javax.persistence.*;
import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.FetchType.EAGER;

/**
 * Classe utilizzata per l'autenticazione
 * @author utente
 *
 */
@Entity
public class Utente {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Integer id;
    
    /**
     * Unique username
     */
    @Id
    @Column(nullable = false, length = 32)
    private String username;
    
    /**
     * Digest di una password
     */
    @Column(nullable = false, length = 512)
    private String password;
    
    /**
     * Salt autogenerato utilizzato per il digest
     */
    @Column(nullable = false, length = 128)
    private String salt;
    
    /**
     * I ruoli assunti da un utente
     */
    @ManyToMany(cascade = DETACH)
	private Set<Ruolo> ruolo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Set<Ruolo> getRuolo() {
		return ruolo;
	}

	public void setRuolo(Set<Ruolo> ruolo) {
		this.ruolo = ruolo;
	}
    
    
}

