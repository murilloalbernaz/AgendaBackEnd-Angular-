package murilloalbernaz9github.com.br.agenda.service;

import murilloalbernaz9github.com.br.agenda.model.entity.Contato;
import murilloalbernaz9github.com.br.agenda.repository.ContatoRepository;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository repository;

    public Contato save(Contato contato) {
        return repository.save(contato);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Contato> geAll() {
        return repository.findAll();
    }

    public Contato favorite(Long id, Boolean favorite) {
        return repository.findById(id)
                .map(
                        contato -> {
                            contato.setFavorito(favorite);
                            return repository.save(contato);
                        }
                )
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato Não Existe")
                );
    }

    public byte[] addPhoto(Long id, Part photo) {
        return repository.findById(id)
                .map(
                        contato -> {
                            try {
                                InputStream is = photo.getInputStream();
                                byte[] bytes = new byte[(int)photo.getSize()];
                                IOUtils.readFully(is, bytes);
                                contato.setFoto(bytes);
                                repository.save(contato);
                                is.close();
                                return bytes;
                            } catch (IOException e) {
                                return null;
                            }
                        }
                ).orElse(null);
    }

    public Page<Contato> listAllPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
