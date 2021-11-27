package simon.mp.dataclass;

import java.util.List;

public class UpdateProductReq {

    public long id;

    public String name;

    public Double original_price;

    public Double price;

    public Boolean published;

    public String detail;

    public Integer stock;

    public long category_id;

    public List<Long> images;
}
