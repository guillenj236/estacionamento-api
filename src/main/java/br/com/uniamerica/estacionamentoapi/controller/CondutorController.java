package br.com.uniamerica.estacionamentoapi.controller;

import br.com.uniamerica.estacionamentoapi.entity.Condutor;
import br.com.uniamerica.estacionamentoapi.repository.CondutorRepository;
import br.com.uniamerica.estacionamentoapi.service.CondutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/condutor")
public class CondutorController {

    @Autowired
    private CondutorRepository condutorRep;

    @Autowired
    private CondutorService condutorService;

    @GetMapping("/{id}")
    public ResponseEntity<Condutor> findById(@PathVariable("id") final Long id){
        final Condutor condutor = this.condutorRep.findById(id).orElse(null);
        return ResponseEntity.ok(condutor);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.condutorRep.findAll());
    }
    @PostMapping
    public ResponseEntity <?> cadastrar(@RequestBody final Condutor condutor){
        try {
            condutorService.validaCondutor(condutor);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Condutor condutor){
        try {
            condutorService.atualizaCondutor(condutor);
            final Condutor condutor1 = this.condutorRep.findById(id).orElse(null);
            if (condutor1 == null || !condutor1.getId().equals(condutor.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.condutorRep.save(condutor);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
    @DeleteMapping("delete/{id}")
    public void deleteCondutor (@PathVariable Long id) {condutorRep.deleteById(id);}
}
