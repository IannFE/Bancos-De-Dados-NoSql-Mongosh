1. Entidade Estudante
EstudanteController.java
package com.example.demo.controller;

import com.example.demo.model.Estudante;
import com.example.demo.repository.EstudanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudantes")
public class EstudanteController {

    @Autowired
    private EstudanteRepository repository;

    @PostMapping
    public Estudante cadastrar(@RequestBody Estudante estudante) {
        return repository.save(estudante);
    }

    @GetMapping
    public List<Estudante> buscarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudante> buscarPorId(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudante> atualizar(@PathVariable String id,
                                               @RequestBody Estudante dados) {
        return repository.findById(id)
                .map(estudante -> {
                    estudante.setNome(dados.getNome());
                    estudante.setEmail(dados.getEmail());
                    estudante.setIdade(dados.getIdade());
                    return ResponseEntity.ok(repository.save(estudante));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

2.Curso
  Curso.java
  package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cursos")
public class Curso {

    @Id
    private String id;
    private String descricao;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}

CursoRepository.java
  package com.example.demo.repository;

import com.example.demo.model.Curso;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CursoRepository extends MongoRepository<Curso, String> {
}

CursoController.java
package com.example.demo.controller;

import com.example.demo.model.Curso;
import com.example.demo.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @PostMapping
    public Curso cadastrar(@RequestBody Curso curso) {
        return repository.save(curso);
    }

    @GetMapping
    public List<Curso> buscarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarPorId(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizar(@PathVariable String id,
                                           @RequestBody Curso dados) {
        return repository.findById(id)
                .map(curso -> {
                    curso.setDescricao(dados.getDescricao());
                    return ResponseEntity.ok(repository.save(curso));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

3. Professor
  Professor.java
package com.example.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "professores")
public class Professor {

    @Id
    private String id;
    private String nome;
    private String email;
    private Integer idade;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }
}

ProfessorRepository.java
  package com.example.demo.repository;

import com.example.demo.model.Professor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProfessorRepository extends MongoRepository<Professor, String> {
}

ProfessorController.java
  package com.example.demo.controller;

import com.example.demo.model.Professor;
import com.example.demo.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;

    @PostMapping
    public Professor cadastrar(@RequestBody Professor professor) {
        return repository.save(professor);
    }

    @GetMapping
    public List<Professor> buscarTodos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable String id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizar(@PathVariable String id,
                                               @RequestBody Professor dados) {
        return repository.findById(id)
                .map(professor -> {
                    professor.setNome(dados.getNome());
                    professor.setEmail(dados.getEmail());
                    professor.setIdade(dados.getIdade());
                    return ResponseEntity.ok(repository.save(professor));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
