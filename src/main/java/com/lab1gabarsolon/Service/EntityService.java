package com.lab1gabarsolon.Service;

import com.lab1gabarsolon.Exception.NotFoundException;
import com.lab1gabarsolon.Model.Display;
import com.lab1gabarsolon.Model.Smartphone;
import com.lab1gabarsolon.Repository.SmartphoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
