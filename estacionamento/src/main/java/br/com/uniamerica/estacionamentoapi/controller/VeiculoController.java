package br.com.uniamerica.estacionamentoapi.controller;

import br.com.uniamerica.estacionamentoapi.entity.Veiculo;
import br.com.uniamerica.estacionamentoapi.repository.VeiculoRepository;
import br.com.uniamerica.estacionamentoapi.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRep;
    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> findById(@PathVariable("id") final Long id){
        final Veiculo veiculo = this.veiculoRep.findById(id).orElse(null);
        return ResponseEntity.ok(veiculo);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.veiculoRep.findAll());

    }
    @PostMapping
    public ResponseEntity <?> cadastrar(@RequestBody final Veiculo veiculo){
        try {
            veiculoService.validaVeiculo(veiculo);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Veiculo veiculo){
        try {
            veiculoService.atualizaVeiculo(veiculo);
            final Veiculo veiculo1 = this.veiculoRep.findById(id).orElse(null);

            if (veiculo1 == null || !veiculo1.getId().equals(veiculo.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.veiculoRep.save(veiculo);
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
    public void deleteVeiculo (@PathVariable Long id) {veiculoRep.deleteById(id);}
}
