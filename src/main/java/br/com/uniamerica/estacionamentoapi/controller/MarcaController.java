package br.com.uniamerica.estacionamentoapi.controller;

import br.com.uniamerica.estacionamentoapi.entity.Marca;
import br.com.uniamerica.estacionamentoapi.repository.MarcaRepository;
import br.com.uniamerica.estacionamentoapi.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/marca")
public class MarcaController {

    @Autowired
    private MarcaRepository marcaRep;
    @Autowired
    private MarcaService marcaService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id){
        final Marca marca = this.marcaRep.findById(id).orElse(null);
        return ResponseEntity.ok(marca);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.marcaRep.findAll());
    }

    @PostMapping
    public ResponseEntity <?> cadastrar(@RequestBody final Marca marca){
        try {
            marcaService.validaMarca(marca);
            return ResponseEntity.ok("Registro cadastrado com sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Marca marca) {

        try {
            marcaService.atualizaMarca(marca);
            final Marca marca1 = this.marcaRep.findById(id).orElse(null);
            if (marca1 == null || !marca1.getId().equals(marca.getId())) {
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.marcaRep.save(marca);
            return ResponseEntity.ok("Registro Cadastrado com Sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
    @DeleteMapping("delete/{id}")
    public void deleteMarca (@PathVariable Long id) {marcaRep.deleteById(id);}
}
