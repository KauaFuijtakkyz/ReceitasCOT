package dev.KKfujita.ReceitasCOT.ReceitasController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        // Redireciona internamente para o arquivo index.html da pasta static
        return "forward:/index.html";
    }
}
