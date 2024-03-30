package med.voll.api.medico;

import jakarta.validation.Valid;
import med.voll.api.model.DadosCadastroMedico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    MedicoRepository repository;

    @PostMapping
    @Transactional
    public void create(@RequestBody @Valid DadosCadastroMedico dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }

    @GetMapping("/ativos")
    public Page<DadosListagemMedico> listarAtivos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DadosUpdateMedico dados){
    var medico = repository.getReferenceById(dados.id());
    medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        repository.deleteById(id);
    }

    @DeleteMapping("logico/{id}")
    @Transactional
    public void exclusaoLogica(@PathVariable Long id){
        var medico = repository.getReferenceById(id);
        medico.inativar();
    }

}
