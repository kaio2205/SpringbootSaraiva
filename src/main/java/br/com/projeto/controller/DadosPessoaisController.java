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

import br.com.projeto.domain.Dadospessoais;
import br.com.projeto.domain.Usuario;
import br.com.projeto.repository.DadosPessoaisRepository;


@RestController
@RequestMapping("/dadospessoais")

public class DadosPessoaisController {

	
	@Autowired
	private DadosPessoaisRepository dpr;
	
	@PostMapping("/cadastrar")
	public String cadastrar(@RequestBody Dadospessoais dp) {
	
		dpr.save(dp);
		return "Dados Cadastrados";
	}
	
	@GetMapping("/listar")
	public List<Dadospessoais> listar(){
		return dpr.findAll();
	}
	
	
	@GetMapping("/consultar/{id}")
	public Optional<Dadospessoais> consultar(@PathVariable Integer id) {
		return dpr.findById(id);
	}
	
	
	@GetMapping("/consultarcpf/{cpf}")
	public Optional<Dadospessoais> consultarcpf(@PathVariable String cpf) {
		return dpr.findByCpf(cpf);
	}
	
	@GetMapping("/consultaremail/{email}")
	public Optional<Dadospessoais> consultaremail(@PathVariable String email) {
		return dpr.findByEmail(email);
	}
	
	

@PatchMapping("/alterarnome/{id}")
	
	public String alterarnome(@PathVariable Integer id, @RequestBody Dadospessoais dp) {
	Optional<Dadospessoais>pessoa = dpr.findById(id);
	
	if(!pessoa.isPresent()) {
		return "[{msg:'Nao foi possivel encontrar a pessoa'}]";
	}
	
	dp.setIddadospessoais(id);
	dp.setCpf(pessoa.get().getCpf());
	dp.setEmail(pessoa.get().getEmail());
	dp.setEndereco(pessoa.get().getEndereco());
	dp.setIdusuario(pessoa.get().getIdusuario());
	dpr.save(dp);
	return "[{msg:'Nome atualizado'}]";
	
}


@PatchMapping("/alteraremail/{id}")

public String alteraremail(@PathVariable Integer id, @RequestBody Dadospessoais dp) {
Optional<Dadospessoais>pessoa = dpr.findById(id);

if(!pessoa.isPresent()) {
	return "[{msg:'Nao foi possivel encontrar a pessoa'}]";
}

dp.setIddadospessoais(id);
dp.setCpf(pessoa.get().getCpf());
dp.setEndereco(pessoa.get().getEndereco());
dp.setIdusuario(pessoa.get().getIdusuario());
dpr.save(dp);
return "[{msg:'Nome atualizado'}]";

}
	
@PatchMapping("/alterarendereco/{id}")

public String alterarendereco(@PathVariable Integer id, @RequestBody Dadospessoais dp) {
Optional<Dadospessoais>pessoa = dpr.findById(id);

if(!pessoa.isPresent()) {
	return "[{msg:'Nao foi possivel encontrar a pessoa'}]";
}

dp.setIddadospessoais(id);
dp.setCpf(pessoa.get().getCpf());
dp.setEmail(pessoa.get().getEmail());
dp.setIdusuario(pessoa.get().getIdusuario());
dpr.save(dp);
return "[{msg:'Nome atualizado'}]";

}

@PatchMapping("/alterartelefone/{id}")

public String alterartelefone(@PathVariable Integer id, @RequestBody Dadospessoais dp) {
Optional<Dadospessoais>pessoa = dpr.findById(id);

if(!pessoa.isPresent()) {
	return "[{msg:'Nao foi possivel encontrar a pessoa'}]";
}

dp.setIddadospessoais(id);
dp.setCpf(pessoa.get().getCpf());
dp.setEmail(pessoa.get().getEmail());
dp.setIdusuario(pessoa.get().getIdusuario());
dpr.save(dp);
return "[{msg:'Nome atualizado'}]";

}


@DeleteMapping("/apagardados/{id}")
public String apagardados(@PathVariable Integer id) {
	Optional<Dadospessoais> delete = dpr.findById(id);
	if(!delete.isPresent()) {
		return "[{msg:'Usuario nao encontrado'}]";
	}
	dpr.deleteById(id);
	return "[{msg:'usuario apagado'}]";
}






	
}
