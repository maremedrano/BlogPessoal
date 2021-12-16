package org.generation.blogPessoal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "O campo 'nome' deve ser preenchido.")
	@Size(min = 2, max = 100, message = "Mínimo de 2 e máximo de 100 caracteres")
	private String nome;
	
	@NotBlank(message = "O campo 'nickname' deve ser preenchido.")
	private String usuario;
	
	@NotBlank(message = "O campo 'senha' deve ser preenchido.")
	@Size(min = 5, max = 100, message = "Mínimo de 5 e máximo de 100 caracteres")
	private String senha;
	
	@Size(max = 255, message = "Máximo de 255 caracteres")
	private String foto;
	
	public Usuario(Long id, String nome, String usuario, String senha) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
    }

    public Usuario(){}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String nickName) {
		this.usuario = nickName;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
}