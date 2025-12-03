package tacos;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
public class Order {
    @NotBlank(message = "이름이 없습니다.")
    private String deliveryName;
    @NotBlank(message = "상세 주소가 없습니다.")
    private String deliveryStreet;
    @NotBlank(message = "시군구 주소가 없습니다.")
    private String deliveryCity;
    @NotBlank(message = "시도 주소가 없습니다.")
    private String deliveryState;
    @NotBlank(message = "우편번호가 없습니다.")
    private String deliveryZip;
    @CreditCardNumber(message = "카드 번호가 이상합니다.")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "유효기간은 MM/YY 형식입니다.")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "CCV 숫자가 이상합니다.")
    private String ccCVV;
}
