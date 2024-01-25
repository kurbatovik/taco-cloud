package tacos.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.TacoOrder;
import tacos.data.IngredientRepository;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
@RequestMapping(value = "/design")
@SessionAttributes(value = "tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();
        Map<Type, Iterable<Ingredient>> mapOfIngredients = new EnumMap<>(Type.class);
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            mapOfIngredients.put(type, filterByType(ingredients, type));
        }
        String attributeName = "mapOfIngredients";
        model.addAttribute(attributeName, mapOfIngredients);
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping()
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processed taco: {}", taco);

        return "redirect:/orders/current";
    }

    @DeleteMapping
    public String deleteTaco(Taco taco, TacoOrder tacoOrder, SessionStatus sessionStatus) {
        log.info("Deleting taco: {}", taco);
        log.info("Deleting taco order: {}", tacoOrder);
        sessionStatus.setComplete();
        return "redirect:/";
    }

    private Iterable<Ingredient> filterByType(Iterable<Ingredient> ingredients, Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false)
                .filter(i -> i.getType().equals(type))
                .toList();
    }
}