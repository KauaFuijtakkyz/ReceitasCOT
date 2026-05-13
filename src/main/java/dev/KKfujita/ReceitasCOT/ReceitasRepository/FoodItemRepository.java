package dev.KKfujita.ReceitasCOT.ReceitasRepository;

import dev.KKfujita.ReceitasCOT.ReceitasModel.FoodItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItemModel, Long> {
    Optional<FoodItemModel> findAllById(Long id);
}
