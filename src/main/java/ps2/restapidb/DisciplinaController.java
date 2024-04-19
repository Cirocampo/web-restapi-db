package ps2.restapidb;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class DisciplinaController {

	@Autowired
	private DisciplinaRepo disciplinaRepo;

	@GetMapping("/api/disciplina")
	Iterable<Disciplina> getDisciplinas() {
		return disciplinaRepo.findAll(); 
	}
	
	@GetMapping("/api/disciplina/{id}")
	Optional<Disciplina> getDisciplinaById(@PathVariable long id) {
		return disciplinaRepo.findById(id);
	}
	
	@PostMapping("/api/disciplina")
	Disciplina createDisciplina(@RequestBody Disciplina f) {
		Disciplina createdDis = disciplinaRepo.save(f);
		return createdDis;
	}
	
	@PutMapping("/api/disciplina/{discplinaId}")
	Optional<Disciplina> updateDisciplina(@RequestBody Disciplina disciplinaReq, @PathVariable long discplinaId) {
		Optional<Disciplina> opt = disciplinaRepo.findById(discplinaId);
		if (opt.isPresent()) {
			if (disciplinaReq.getId() == discplinaId) {
				disciplinaRepo.save(disciplinaReq);
				return opt;
			}
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Erro ao alterar dados da discplina com id " + discplinaId);
	}	
	
	@DeleteMapping("/api/disciplina/{id}")
	void deleteDisciplina(@PathVariable long id) {
		disciplinaRepo.deleteById(id);
	}	
	
}