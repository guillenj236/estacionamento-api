package br.com.uniamerica.estacionamentoapi.controller;

import br.com.uniamerica.estacionamentoapi.entity.Movimentação;
import br.com.uniamerica.estacionamentoapi.repository.MovimentacaoRepository;
import br.com.uniamerica.estacionamentoapi.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/movimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoRepository movimentacaoRep;
    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping("/{id}")
    public ResponseEntity<Movimentação> findById(@PathVariable("id") final Long id){
        final Movimentação movimentacao = this.movimentacaoRep.findById(id).orElse(null);
        return ResponseEntity.ok(movimentacao);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.movimentacaoRep.findAll());

    }
    @PostMapping
    public ResponseEntity <?> cadastrar(@RequestBody final Movimentação movimentacao){
        try {
            this.movimentacaoRep.save(movimentacao);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Movimentação movimentacao){
        try {
            movimentacaoService.atualizaMovimentacao(movimentacao);
            final Movimentação movimentacao1 = this.movimentacaoRep.findById(id).orElse(null);

            if (movimentacao1 == null || !movimentacao1.getId().equals(movimentacao.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.movimentacaoRep.save(movimentacao);
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
    public void deleteMovimentacao (@PathVariable Long id) {movimentacaoRep.deleteById(id);}
}
