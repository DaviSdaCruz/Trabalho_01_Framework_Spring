package dw.editora.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dw.editora.entity.Autor;
import dw.editora.repository.AutorRepository;

@Service
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor salvar(Autor autor) {
        return autorRepository.save(autor);
    }

    public List<Autor> buscarTodos() {
        return autorRepository.findAll();
    }

    public Autor buscarPorId(Long id) {
        return autorRepository.findById(id).orElse(null);
    }

    public void deletar(Long id) {
        autorRepository.deleteById(id);
    }
}
