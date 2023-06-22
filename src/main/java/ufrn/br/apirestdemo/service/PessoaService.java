package ufrn.br.apirestdemo.service;

import org.springframework.stereotype.Service;
import ufrn.br.apirestdemo.domain.Pessoa;
import ufrn.br.apirestdemo.repository.PessoaRepository;


@Service
public class PessoaService extends GenericService<Pessoa, PessoaRepository> {
    public PessoaService(PessoaRepository repository) {
        super(repository);
    }
}
