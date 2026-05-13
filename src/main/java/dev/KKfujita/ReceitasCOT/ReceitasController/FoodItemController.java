package dev.KKfujita.ReceitasCOT.ReceitasController;

import dev.KKfujita.ReceitasCOT.ReceitasModel.FoodItemModel;
import dev.KKfujita.ReceitasCOT.ReceitasService.FoodItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin
@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService service;

    public FoodItemController(FoodItemService foodItemService) {
        this.service = foodItemService;
    }
    @PostMapping
    public ResponseEntity<FoodItemModel> criar(@RequestBody FoodItemModel foodItemModel){
        FoodItemModel salvo = service.salvar(foodItemModel);
        return ResponseEntity.ok(salvo);
    }
    @GetMapping
    public ResponseEntity<List<FoodItemModel>> listar(){
        List<FoodItemModel> lista = service.listar();
        return ResponseEntity.ok(lista);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletarporid(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<FoodItemModel> atualizar(@RequestBody FoodItemModel foodItemModel, @PathVariable Long id){
       return service.buscarporid(id)
                .map(ItemExistente -> {
                    foodItemModel.setId(ItemExistente.getId());
                    FoodItemModel atualizado = service.atualizar(foodItemModel);
                    return ResponseEntity.ok(atualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

}
