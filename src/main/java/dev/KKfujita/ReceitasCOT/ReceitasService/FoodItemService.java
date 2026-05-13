package dev.KKfujita.ReceitasCOT.ReceitasService;

import dev.KKfujita.ReceitasCOT.ReceitasModel.FoodItemModel;
import dev.KKfujita.ReceitasCOT.ReceitasRepository.FoodItemRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
@Data

public class FoodItemService {

    private FoodItemRepository repository;



    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;

    }

    public FoodItemModel salvar(FoodItemModel foodItemModel){
        return repository.save(foodItemModel);
    }

    public List<FoodItemModel> listar(){
        return repository.findAll();
    }

    public Optional<FoodItemModel> buscarporid(Long id){
        return repository.findById(id);

    }

    public void deletarporid(Long id){
        repository.deleteById(id);
    }

    public FoodItemModel atualizar(FoodItemModel foodItemModel){
        return repository.save(foodItemModel);

    }


}
