package projeto.infonews.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "monitoramento")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class MonitoramentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String valor;

    @Enumerated(EnumType.STRING)
    private TipoMonitoramento tipo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuariosEntity usuario;
}
