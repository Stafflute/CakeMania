package com.stafflute.entities.user;

import javax.persistence.*;

/**
 * Classe utilizzata per l'autenticazione
 * @author utente
 *
 */
@Entity
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * Unique username
     */
    @Column(nullable = false, length = 32, unique = true)
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
    
    
}

