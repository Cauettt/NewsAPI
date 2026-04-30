package projeto.infonews.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favoritos")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class FavoritosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private String url;

    private String fonteNome;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuariosEntity usuario;
}