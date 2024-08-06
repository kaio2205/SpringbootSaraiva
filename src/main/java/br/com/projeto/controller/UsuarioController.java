package br.com.projeto.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.domain.Usuario;
import br.com.projeto.repository.UsuarioRepository;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	UsuarioRepository ur;
	
	@PostMapping("/cadastrar")
	public String cadastrar(@RequestBody Usuario us) {
	ur.save(us);
	
	return "Usuario cadastrado";
	}
	
	@GetMapping("/listar")
	public List<Usuario> listar() {
		
		return ur.findAll();
	}
	
	@GetMapping("/consultar/{id}")
	public Optional<Usuario> consultar(@PathVariable Integer id) {
		 return (ur).findById(id);
		 
	}
	
	@GetMapping("consultarusuario/{nomeusuario}")
	public Optional<Usuario> consultarusuario(@PathVariable String nomeusuario) {
		return ur.findByNomeusuario(nomeusuario);
	}
	
	
	
	@PatchMapping("alterarfoto/{id}")
	public String alterarfoto(@PathVariable Integer id, @RequestBody Usuario us) {

		
		Optional<Usuario> user = ur.findById(id) ;
		 if(!user.isPresent()) {
			 return "nao foi possivel encontrar usuario";
		 }
		 
		us.setIdusuario(id);
		us.setNomeusuario(user.get().getNomeusuario());
		us.setSenha(user.get().getSenha());
		us.setDataalteracao(user.get().getDataalteracao());
		ur.save(us);
		return "{[msg:Foto alterada]}";
		 

	
	
	
	}
	
	@PatchMapping("alterarsenha/{id}")
	public String alterarsenha(@PathVariable Integer id,@RequestBody Usuario us) {
		Optional<Usuario> user =ur.findById(id);
		if(!user.isPresent()) {
			return "[{msg: Nao foi possivel encontrar o usuario}]";
					
		}
		us.setIdusuario(id);
		us.setDataalteracao(user.get().getDataalteracao());
		us.setFoto(user.get().getFoto());
		us.setNomeusuario(user.get().getNomeusuario());
		ur.save(us);
		return "[{msg:'Senha alterada'}]";
	}
	
	
	
	
	@DeleteMapping("apagarusuario/{id}")
	public String apagarusuario(@PathVariable Integer id) {
		Optional<Usuario> user = ur.findById(id);
		if(!user.isPresent()) {
			return "[{msg:'Usuario nao encontrado'}]";
		}
		ur.deleteById(id);
		return "[{msg:'usuario apagado'}]";
	}
	
	
	
	
	
	
	
	





}
