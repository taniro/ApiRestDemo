package ufrn.br.apirestdemo.service;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ufrn.br.apirestdemo.domain.AbstractEntity;
import ufrn.br.apirestdemo.repository.IGenericRepository;

import java.util.List;
import java.util.Optional;

public abstract class GenericService<E extends AbstractEntity, R extends IGenericRepository> implements IGenericService<E>{

    R repository;


    public GenericService(R repository) {
        this.repository = repository;
    }

    @Override
    public E getById(Long id) {
        Optional<E> pessoaBanco = repository.findById(id);
        if (pessoaBanco.isPresent()){
            return (E) pessoaBanco.get();
        }else{
            throw  new EntityNotFoundException();
        }
    }

    @Override
    public E create(E e) {
        return (E) this.repository.save(e);
    }

    @Override
    public E update(E updatedEntity, Long id) {

        Optional<E> entity = repository.findById(id);
        if (entity.isPresent()){

            E e = entity.get();
            e.partialUpdate(updatedEntity);

            return (E) this.repository.save(e);
        }else{
            throw  new EntityNotFoundException();
        }
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<E> list() {
        return (List<E>) this.repository.findAll();
    }

    @Override
    public Page<E> find(Pageable page) {
        return (Page<E>) this.repository.findAll(page);
    }
}
