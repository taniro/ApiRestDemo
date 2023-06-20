package ufrn.br.apirestdemo.controller;


import org.hibernate.collection.spi.PersistentSortedMap;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ufrn.br.apirestdemo.domain.Pessoa;
import ufrn.br.apirestdemo.service.PessoaService;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    PessoaService service;

    public PessoaController(PessoaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa create(@RequestBody Pessoa p){
        return this.service.create(p);
    }

    @GetMapping
    public List<Pessoa> list(){
        return this.service.list();
    }

    @PutMapping("{id}")
    public Pessoa update(@RequestBody Pessoa p, @PathVariable Long id){
        return this.service.update(p, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }

}




