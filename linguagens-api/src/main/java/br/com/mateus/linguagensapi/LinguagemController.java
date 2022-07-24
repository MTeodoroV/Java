package br.com.mateus.linguagensapi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinguagemController {

    @Autowired
    private LinguagemRepository repositorio;

    @GetMapping("/linguagens")
    public List<Linguagem> obterLinguagens() {
        List<Linguagem> linguagens = repositorio.findAll();
        return linguagens;
    }

    @GetMapping("/linguagens/{id}")
    public Optional<Linguagem> obterUmaLinguagens(@PathVariable String id) {
        Optional<Linguagem> getLinguagem = repositorio.findById(id);
        return getLinguagem;
    }

    @PostMapping("/linguagens")
    public ResponseEntity<Linguagem> cadastrarLinguagem(@RequestBody Linguagem linguagem) {
        Linguagem linguagemSalva = repositorio.save(linguagem);
        return ResponseEntity.status(201).body(repositorio.save(linguagemSalva));
    }

    @PutMapping("/linguagens/{id}")
    public Linguagem atualizarLinguagem(@PathVariable String id, @RequestBody Linguagem linguagem) {
        Linguagem atualizarLinguagem = repositorio.findById(id).orElseThrow();
        atualizarLinguagem.setTitle(linguagem.getTitle());
        atualizarLinguagem.setImage(linguagem.getImage());
        atualizarLinguagem.setRanking(linguagem.getRanking());
        repositorio.save(atualizarLinguagem);
        return atualizarLinguagem;
    }

    @DeleteMapping("/linguagens/{id}")
    public String deletarLinguagem(@PathVariable String id) {
        try {
            repositorio.deleteById(id);
            return "Apagado com sucesso";
        } catch (Exception e) {
            return "Id não encontrado";
        }

    }
}
