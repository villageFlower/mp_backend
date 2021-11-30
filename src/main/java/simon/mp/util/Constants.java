package simon.mp.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class Constants {
    public static final String Page_Size = "10";
    public static final int Page_Size_Int = 10;

    public static final Pageable Default_Page = PageRequest.of(0, Page_Size_Int);
}
