package ufrn.br.apirestdemo.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import ufrn.br.apirestdemo.domain.Pessoa;
import ufrn.br.apirestdemo.repository.PessoaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    PessoaRepository repository;
    public PessoaService(PessoaRepository repository) {
        this.repository = repository;
    }

    public Pessoa create(Pessoa p){
       return this.repository.save(p);
    }

    public Pessoa update(Pessoa p, Long id){
        Optional<Pessoa> pessoaBanco = repository.findById(id);
        if (pessoaBanco.isPresent()){
            return this.repository.save(p);
        }else{
            throw  new EntityNotFoundException();
        }
    }

    public void delete(Long id){
        this.repository.deleteById(id);
    }

    public List<Pessoa> list(){
        return repository.findAll();
    }

}
