package tacos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Document
public class Ingredient {

    @Id
    private String id;
    private String name;
    private Type type;

    @Getter
    public enum Type {
        WRAP("wraps", "Design your wrap"),
        PROTEIN("proteins", "Pick your protein"),
        CHEESE("cheeses", "Choose your cheese"),
        VEGGIES("veggies", "Determine your veggies"),
        SAUCE("sauces", "Select your sauce");

        private final String pluralName;
        private final String headers;

        Type(String pluralName, String headers) {
            this.pluralName = pluralName;
            this.headers = headers;
        }

    }
}
