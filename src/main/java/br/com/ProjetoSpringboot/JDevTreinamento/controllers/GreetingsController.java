package br.com.ProjetoSpringboot.JDevTreinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.ProjetoSpringboot.JDevTreinamento.model.Usuario;
import br.com.ProjetoSpringboot.JDevTreinamento.repository.UsuarioRepository;

/* Intercepta todas as requisições de mapeamento, tudo que vier do navegador, por exemplo */
@RestController
public class GreetingsController {

	@Autowired /* IC/CD ou CDI --> injeção de dependência */
	UsuarioRepository usuarioRepository;

//	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
//	@ResponseStatus(HttpStatus.OK)
//	/* Está pegando uma variável da url e fazendo a ação */
//	public String greetingText(@PathVariable String name) {
//		return "Hello " + name + "!";
//	}

//	@RequestMapping(value = "/{variavel}", method = RequestMethod.GET)
//	@ResponseStatus(HttpStatus.OK)
//	public String retornaOlaMundo(@PathVariable String variavel) {
//		Usuario user = new Usuario();
//		user.setNome(variavel);
//		user.setIdade(19);
//
//		usuarioRepository.save(user); /* Grava no banco */
//
//		return "Olá Mundo, bem vindo " + variavel + "!";
//	}

	@GetMapping(value = "listaUsuarios")
	@ResponseBody /* Retorna os dados par ao corpo da resposta --> Traz um Json */
	public ResponseEntity<List<Usuario>> listaUsuarios() {
		List<Usuario> listaUsuarios = usuarioRepository
				.findAll(); /* Lista de Usuários --> Executa a consulta no banco de dados */
		return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK); /* Retorna a lista em Json */
	}

	@PostMapping(value = "salvarUsuario") /* Mapeia a URL */
	@ResponseBody /* Faz a descrição da resposta */
	public ResponseEntity<Usuario> insertUsuario(@RequestBody Usuario usuario) { /* Recebe os dados para salvar */
		Usuario usuarioRetornado = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(usuarioRetornado, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "deletarUsuario") /* Mapeia a URL */
	@ResponseBody /* Faz a descrição da resposta */
	public ResponseEntity<String> deletarUsuario(@RequestParam Long idUsuario) {
		if (idUsuario == null) {
			return new ResponseEntity<String>("Id não foi informado para atualização", HttpStatus.OK);
		}
		usuarioRepository.deleteById(idUsuario);

		return new ResponseEntity<String>("Usuário deletado com sucesso",
				HttpStatus.OK); /* É em String para retornar a indicação do delete */
	}

	@GetMapping(value = "buscarIdUsuario") /* Mapeia a URL */
	@ResponseBody /* Faz a descrição da resposta */
	public ResponseEntity<?> buscarIdUsuario(@RequestParam(name = "idUsuario") Long idUsuario) {
		if (idUsuario == null) {
			return new ResponseEntity<String>("Id não foi informado para atualização", HttpStatus.OK);
		}
		Usuario usuarioRetornado = usuarioRepository.findById(idUsuario).get();

		return new ResponseEntity<Usuario>(usuarioRetornado, HttpStatus.OK);
	}

	@PutMapping(value = "atualizarUsuario") /* Mapeia a URL */
	@ResponseBody /* Faz a descrição da resposta */
	public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario) { /* Recebe os dados para salvar */
		if (usuario.getId() == null) {
			return new ResponseEntity<String>("Id não foi informado para atualização", HttpStatus.OK);
		}
		Usuario usuarioRetornado = usuarioRepository.saveAndFlush(usuario); /* Salva e retorna em estado persistente */

		return new ResponseEntity<Usuario>(usuarioRetornado, HttpStatus.OK);
	}

	@GetMapping(value = "buscarNomeUsuario") /* Mapeia a URL */
	@ResponseBody /* Faz a descrição da resposta */
	public ResponseEntity<List<Usuario>> buscarNomeUsuario(@RequestParam(name = "nome") String nome) {
		List<Usuario> listaUsuarioRetornado = usuarioRepository.buscarPorNome(nome.trim().toUpperCase());
		return new ResponseEntity<List<Usuario>>(listaUsuarioRetornado, HttpStatus.OK);
	}
}
