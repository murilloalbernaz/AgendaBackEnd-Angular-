package murilloalbernaz9github.com.br.agenda.repository;

import murilloalbernaz9github.com.br.agenda.model.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
