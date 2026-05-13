package dev.KKfujita.ReceitasCOT.ReceitasController;

import dev.KKfujita.ReceitasCOT.ReceitasModel.FoodItemModel;
import dev.KKfujita.ReceitasCOT.ReceitasService.FoodItemService;
import dev.KKfujita.ReceitasCOT.ReceitasService.GroqService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class RecipeController {
    private FoodItemService foodservice;
    private final GroqService service;

    public RecipeController(GroqService service, FoodItemService foodservice) {
        this.service = service;
        this.foodservice = foodservice;
    }

    @GetMapping("/generate")
    public Mono<ResponseEntity<String>> generateRecepi(){
        List<FoodItemModel> foodItemModels = foodservice.listar() ;
        return service.generateRecipe(foodItemModels)
                .map(recepi -> ResponseEntity.ok(recepi))
                .defaultIfEmpty(ResponseEntity.noContent().build());

    }
}
