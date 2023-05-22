package br.com.uniamerica.estacionamentoapi.controller;

import br.com.uniamerica.estacionamentoapi.entity.Configuração;
import br.com.uniamerica.estacionamentoapi.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/configuracao")
public class ConfiguracaoController {

    @Autowired
    private ConfiguracaoRepository configuracaoRep;

    @GetMapping("/{id}")
    public ResponseEntity<Configuração> findByIDPath (@PathVariable("id") final Long id) {
        final Configuração configuracao = this.configuracaoRep.findById(id).orElse(null);
        return ResponseEntity.ok(configuracao);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.configuracaoRep.findAll());
    }
    @PostMapping
    public ResponseEntity <?> cadastrar(@RequestBody final Configuração configuracao){
        try {
            this.configuracaoRep.save(configuracao);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Configuração configuracao){
        try {
            final Configuração configuracao1 = this.configuracaoRep.findById(id).orElse(null);

            if (configuracao1 == null || !configuracao1.getId().equals(configuracao.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.configuracaoRep.save(configuracao);
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
    public void deleteConfig (@PathVariable Long id)
    {
        configuracaoRep.deleteById(id);
    }

}
