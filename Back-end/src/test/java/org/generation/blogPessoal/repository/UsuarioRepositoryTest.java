package org.generation.blogPessoal.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.Usuario;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class UsuarioRepositoryTest {
	

	    @Autowired
	    private UsuarioRepository usuarioRepository;

	    @BeforeAll
	    void start(){

	        usuarioRepository.save(new Usuario(0L, "Mariana Medrano", "maremedrano@yahoo.com", "123456"));

	        usuarioRepository.save(new Usuario(0L, "Cássia Régia", "cassia@email.com", "123456"));

	        usuarioRepository.save(new Usuario(0L, "Paulo Cesar Medrano", "paulocesar@email.com", "123456"));

	        usuarioRepository.save(new Usuario(0L, "Isabela Medrano", "isabela@email.com", "123456"));
	    }
	    
	    @Test
	    @DisplayName("Retorna 1 usuario")
	    public void deveRetornarUmUsuario(){

	        Optional<Usuario> usuario = usuarioRepository.findByUsuario("maremedrano@yahoo.com");
	        assertTrue(usuario.get().getUsuario().equals("maremedrano@yahoo.com"));
	    }
	    
	    @Test
	    @DisplayName("Retorna 3 usuario")
	    public void deveRetornarTresUsuario(){

	        List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Medrano");
	        assertEquals(3, listaDeUsuarios.size());
	        assertTrue(listaDeUsuarios.get(0).getNome().equals("Mariana Medrano"));
	        assertTrue(listaDeUsuarios.get(1).getNome().equals("Paulo Cesar Medrano"));
	        assertTrue(listaDeUsuarios.get(2).getNome().equals("Isabela Medrano"));
	    }

}