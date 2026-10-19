// fazer o "AutorController" com os endpoints HTTP
package dw.editora.control;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dw.editora.entity.Artigo;
import dw.editora.entity.Autor;
import dw.editora.repository.ArtigoRepository;
import dw.editora.repository.AutorRepository;
import dw.editora.service.AutorService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/autores")
public class AutorController {
    
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ArtigoRepository artigoRepository;
   
    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @GetMapping("/{id}/artigos")
    public ResponseEntity<List<Artigo>> listarArtigosPorAutor(
            @PathVariable Long id) {

        if (!autorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        List<Artigo> artigos =
                artigoRepository.findByAutorId(id);

        return ResponseEntity.ok(artigos);
    }

    // Listar todos os autores

    @GetMapping
    public ResponseEntity<List<Autor>> listar() {
        List<Autor> autores = service.buscarTodos();

        if (autores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(autores);
    }

    // Buscar autor por ID
    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarPorId(
            @PathVariable Long id) {

        Autor autor = service.buscarPorId(id);

        if (autor == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(autor);
    }

    // Cadastrar autor
    @PostMapping
    public ResponseEntity<Autor> cadastrar(
            @RequestBody Autor autor) {

        Autor autorSalvo = service.salvar(autor);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(autorSalvo);
    }

    // Excluir autor por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id) {

        Autor autor = service.buscarPorId(id);

        if (autor == null) {
            return ResponseEntity.notFound().build();
        }

        service.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
