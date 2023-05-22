package br.com.uniamerica.estacionamentoapi.controller;

import br.com.uniamerica.estacionamentoapi.entity.Modelo;
import br.com.uniamerica.estacionamentoapi.repository.ModeloRepository;
import br.com.uniamerica.estacionamentoapi.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/modelo")
public class ModeloController {

    @Autowired
    private ModeloRepository modeloRep;
    @Autowired
    private ModeloService modeloService;

    @GetMapping("/{id}")
    public ResponseEntity<Modelo> findById(@PathVariable("id") final Long id){
        final Modelo modelo = this.modeloRep.findById(id).orElse(null);
        return ResponseEntity.ok(modelo);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.modeloRep.findAll());

    }
    @PostMapping
    public ResponseEntity <?> cadastrar(@RequestBody final Modelo modelo){
        try {
            modeloService.validaModelo(modelo);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Modelo modelo){
        try {

            modeloService.atualizaModelo(modelo);
            final Modelo modelo1 = this.modeloRep.findById(id).orElse(null);
            if (modelo1 == null || !modelo1.getId().equals(modelo.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.modeloRep.save(modelo);
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
    public void deleteModelo (@PathVariable Long id) {modeloRep.deleteById(id);}
}