package tacos;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.List;

@Data
@UserDefinedType("taco")
public class TacoUDT {
    private final String name;
    private final List<IngredientUDT> ingredients;

    public TacoUDT(Taco taco) {
        name = taco.getName();
        ingredients = taco.getIngredients();
    }
}
