package org.generation.blogPessoal.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.service.UsuarioService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UsuarioControllerTest {

	@Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioService usuarioService;

    @Test
    @Order(1)
    @DisplayName("Cadastrar Um Usuario")
    public void deveCriarUmUsuario() {
        HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L,
            "Mariana Medrano","maremedrano@yahoo.com", "123456"));

            ResponseEntity<Usuario> resposta = testRestTemplate
                .exchange("/usuarios/cadstrar", HttpMethod.POST, requisicao, Usuario.class);

            assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
            assertEquals(requisicao.getBody().getNome(), resposta.getBody().getNome());
            assertEquals(requisicao.getBody().getUsuario(), resposta.getBody().getUsuario());
    }
    
    
    @Test
    @Order(2)
    @DisplayName("Nao deve permitir duplicacao de Usuario")
    public void naoDeveDuplicarUsuario() {

        usuarioService.CadastrarUsuario(new Usuario(0L,
        		"Maria da Silva", "maria_silva@email.com", "12345678"));
        
        HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(new Usuario(0L,
        		"Maria da Silva", "maria_silva@email.com", "12345678"));
        
        ResponseEntity<Usuario> resposta = testRestTemplate
            .exchange("/usuarios/cadastrar", HttpMethod.POST, requisicao, Usuario.class);
        
        assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
    }
      
    
  /*  @Test
	@Order(3)
	@DisplayName("Alterar um Usuário") 
	public void deveAtualizarUmUsuario() {

		Optional<Usuario> usuarioCreate = usuarioService.CadastrarUsuario(new Usuario(0L,
				"Juliana Andrews", "juliana_andrews@email.com.br", "juliana123"));
		
		Usuario usuarioUpdate = new Usuario(usuarioCreate.get().getId(),
				"Juliana Andrews Ramos", "juliana_ramos@email.com.br", "juliana123");
		
		HttpEntity<Usuario> requisicao = new HttpEntity<Usuario>(usuarioUpdate);
		
		ResponseEntity<Usuario> resposta = testRestTemplate
				.withBasicAuth("root", "root")
				.exchange("/usuario/atualizar", HttpMethod.PUT, requisicao, Usuario.class);
		
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		assertEquals(usuarioUpdate.getNome(), resposta.getBody().getNome());
		assertEquals(usuarioUpdate.getUsuario(), resposta.getBody().getUsuario());

	}
    
    */
    
    
    @Test
	@Order(4)
	@DisplayName("Listar todos os Usuários")
    
    
	public void deveMostrarTodosUsuarios() {

		usuarioService.CadastrarUsuario(new Usuario(0L, 
			"Paulo Cesar Medrano", "paulocesar@email.com", "123456"));
		
		usuarioService.CadastrarUsuario(new Usuario(0L, 
			"Cássia Régia", "cassia@email.com", "123456"));

		ResponseEntity<String> resposta = testRestTemplate
			.withBasicAuth("root", "root")
			.exchange("/usuarios/all", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
    
}