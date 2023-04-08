package com.smartphones.Service;

import com.smartphones.Exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class EntityService<T>{
    protected JpaRepository<T, Long> repository;

    public EntityService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    public List<T> getAll(){
        return repository.findAll();
    }
    public T getById(Long id) {
        return repository.findById(id).orElseThrow(() ->new NotFoundException(id));
    }

    public void add(T newEntity){
        repository.save(newEntity);
    }
    public void delete(Long id){
        repository.deleteById(id);
    }
    abstract public void update(T newEntity, Long id);
}
