package ufrn.br.apirestdemo.repository;

import org.springframework.data.repository.ListCrudRepository;
import ufrn.br.apirestdemo.domain.Pessoa;

public interface PessoaRepository extends ListCrudRepository<Pessoa, Long> {
}
