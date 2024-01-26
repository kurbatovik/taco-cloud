package tacos;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;




@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Table("ingredients")
public class Ingredient {

    @PrimaryKey
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
