import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartAddReq {
    private Long goodsId;
    private Integer quantity;
    private Double price;
}