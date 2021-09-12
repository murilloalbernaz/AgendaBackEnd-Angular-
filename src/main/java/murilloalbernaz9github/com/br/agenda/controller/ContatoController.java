package murilloalbernaz9github.com.br.agenda.controller;

import murilloalbernaz9github.com.br.agenda.model.entity.Contato;
import murilloalbernaz9github.com.br.agenda.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/agenda")
@CrossOrigin("*")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Contato salvar(@Valid @RequestBody Contato contato){
        return contatoService.save(contato);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        contatoService.delete(id);
    }

    @GetMapping("")
    public List<Contato> contatosList(){
        return contatoService.geAll();
    }

    @PatchMapping("/{id}")
    public Contato favorite(@PathVariable Long id, @RequestBody Boolean favorite){
       return contatoService.favorite(id, favorite);
    }

    @PutMapping("/{id}")
    public byte[] addPhoto(@PathVariable Long id,
                           @RequestParam(name = "photo") Part photo){
        return contatoService.addPhoto(id, photo);
    }

    @GetMapping("/page")
    public Page<Contato> contatosListPage(Pageable pageable){
        return contatoService.listAllPage(pageable);
    }
}
