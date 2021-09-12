package murilloalbernaz9github.com.br.agenda.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(length = 400)
    private String nome;

    @NotEmpty
    @Column(length = 400)
    private String email;

    private Boolean favorito;

    @Column
    @Lob
    private byte[] foto;
}
