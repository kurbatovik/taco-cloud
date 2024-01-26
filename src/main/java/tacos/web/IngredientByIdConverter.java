package tacos.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import tacos.Ingredient;
import tacos.IngredientUDT;
import tacos.data.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, IngredientUDT> {

    private final IngredientRepository ingredientRepo;

    public IngredientByIdConverter(IngredientRepository ingredientRepo){
        this.ingredientRepo = ingredientRepo;
    }
    @Override
    @Nullable
    public IngredientUDT convert(@NonNull String id) {
        Ingredient ingredient = ingredientRepo.findById(id).orElse(null);
        if (ingredient == null) {
            return null;
        }
        return new IngredientUDT(ingredient);
    }
    
}
