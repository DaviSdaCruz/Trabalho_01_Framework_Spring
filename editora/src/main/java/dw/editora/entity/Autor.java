package dw.editora.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "autor")
@Getter // Lombok: Gera todos os getters
@Setter // Lombok: Gera todos os setters
@NoArgsConstructor // Lombok: Construtor vazio exigido pelo JPA
@AllArgsConstructor // Lombok: Construtor com todos os argumentos
@ToString // Lombok: Gera o método toString()
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // Boa prática: equals/hashCode apenas no ID
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include // Usa apenas o ID para comparar se dois objetos são o mesmo registro
    private Long id; // Boa prática: usar Long (objeto) ao invés de long (primitivo)
    
    @Column(nullable = false, length = 80)
    private String nome;
    
    @Column(nullable = false, unique = true)
    private String email;

    @Lob
    private String biografia;

    // PARTE 2 DO PROJETO
    
    // private List<Artigo> artigos;
}
