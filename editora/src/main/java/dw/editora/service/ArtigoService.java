package dw.editora.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dw.editora.entity.Artigo;
import dw.editora.entity.Autor;
import dw.editora.repository.ArtigoRepository;
import dw.editora.repository.AutorRepository;

// @Service diz ao Spring que esta classe contém a lógica de negócio
@Service
public class ArtigoService {

    @Autowired
    private ArtigoRepository artigoRepository;

    @Autowired
    private AutorRepository autorRepository;
   
    private final ArtigoRepository repository;

    public ArtigoService(ArtigoRepository repository) {
        this.repository = repository;
    }

    public List<Artigo> listarArtigos(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            return repository.findAll();
        }
        return repository.findByTituloContainingIgnoreCase(titulo);
    }

    public Optional<Artigo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Artigo salvar(Artigo artigo) {
        Long autorId = artigo.getAutor().getId();

        Autor autor = autorRepository.findById(autorId)
            .orElseThrow(() ->
                new RuntimeException("Autor não encontrado: " + autorId)
            );

        artigo.setAutor(autor);

        return artigoRepository.save(artigo);
    }

    public Optional<Artigo> atualizar(Long id, Artigo artigoAtualizado) {
        return repository.findById(id).map(artigo -> {
            artigo.setTitulo(artigoAtualizado.getTitulo());
            artigo.setResumo(artigoAtualizado.getResumo());
            artigo.setPublicado(artigoAtualizado.isPublicado());
            return repository.save(artigo);
        });
    }

    public boolean deletar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public void deletarTodos() {
        repository.deleteAll();
    }

    public List<Artigo> listarPublicados() {
        return repository.findByPublicado(true);
    }
}