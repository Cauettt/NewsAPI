package projeto.infonews.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historico")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class HistoricoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String termoBusca;

    private LocalDateTime dataConsulta;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuariosEntity usuario;

    @PrePersist
    protected void onCreate() {
        dataConsulta = LocalDateTime.now();
    }
}