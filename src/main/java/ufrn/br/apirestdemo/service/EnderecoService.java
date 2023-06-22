package ufrn.br.apirestdemo.service;

import org.springframework.stereotype.Service;
import ufrn.br.apirestdemo.domain.Endereco;
import ufrn.br.apirestdemo.repository.EnderecoRepository;


@Service
public class EnderecoService extends GenericService<Endereco, EnderecoRepository> {

    public EnderecoService(EnderecoRepository repository) {
        super(repository);
    }
}
