package tacos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document
public class TacoOrder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private Date placedAt = new Date();
    @NotBlank(message = "Delivery name is required")
    private String deliveryName;
    @NotBlank(message = "Delivery street is required")
    private String deliveryStreet;
    @NotBlank(message = "Delivery city is required")
    private String deliveryCity;
    @NotBlank(message = "Delivery state is required")
    private String deliveryState;
    @NotBlank(message = "Delivery zip is required")
    private String deliveryZip;
    @CreditCardNumber(message = "Credit card number is invalid")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)([2-9]\\d)$", message = "Credit card expiration date is invalid")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Credit card CVV is invalid")
    private String ccCVV;

    @Size(min = 1, message = "You must choose at least one taco")
    private List<Taco> tacos = new ArrayList<>();

    @Serial
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    @Serial
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

    public void addTaco(Taco taco) {
        tacos.add(taco);
    }
}