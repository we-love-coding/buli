import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartItemResp {
    private String goodsId;
    private int quantity;
    private double price;
    private double subtotal;
    private String goodsName;
    private String imageUrl;
}