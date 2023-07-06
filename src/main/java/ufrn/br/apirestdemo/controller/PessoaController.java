package ufrn.br.apirestdemo.controller;


import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ufrn.br.apirestdemo.domain.Pessoa;
import ufrn.br.apirestdemo.service.PessoaService;

import java.util.Collections;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/pessoas/")
public class PessoaController {

    PessoaService service;
    ModelMapper mapper;

    public PessoaController(PessoaService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pessoa.DtoResponse create(@RequestBody Pessoa.DtoRequest p){

        Pessoa pessoa = this.service.create(Pessoa.DtoRequest.convertToEntity(p, mapper));

        Pessoa.DtoResponse response = Pessoa.DtoResponse.convertToDto(pessoa, mapper);
        response.generateLinks(pessoa.getId());

        return response;
    }

    @GetMapping
    public List<Pessoa.DtoResponse> list(){

        return this.service.list().stream().map(
                elementoAtual -> {
                    Pessoa.DtoResponse response = Pessoa.DtoResponse.convertToDto(elementoAtual, mapper);
                    response.generateLinks(elementoAtual.getId());
                    return response;
                }).toList();
    }

    @GetMapping("{id}")
    public Pessoa.DtoResponse getById(@PathVariable Long id){

        Pessoa pessoa = this.service.getById(id);
        Pessoa.DtoResponse response = Pessoa.DtoResponse.convertToDto(pessoa, mapper);
        response.generateLinks(pessoa.getId());

        return response;
    }


    @PutMapping("{id}")
    public Pessoa.DtoResponse update(@RequestBody Pessoa.DtoRequest dtoRequest, @PathVariable Long id){
        Pessoa p = Pessoa.DtoRequest.convertToEntity(dtoRequest, mapper);
        Pessoa.DtoResponse response = Pessoa.DtoResponse.convertToDto(this.service.update(p, id), mapper);
        response.generateLinks(id);
        return response;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        this.service.delete(id);
    }

}