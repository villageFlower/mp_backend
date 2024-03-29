package simon.mp;

public class APIList {

    public static final String TEST = "api/test";

    //user related
    public static final String GET_ALL_USERS = "api/users";
    public static final String DELETE_USER_BY_ID = "api/delete-user/{Id}";
    public static final String UPDATE_USER_BY_ID = "api/update-user/{Id}";
    public static final String ADD_USER = "api/register-user";
    public static final String LOGIN_USER = "api/login-user";

    //products related
    public static final String GET_ALL_PRODUCTS = "api/products";
    public static final String ADD_PRODUCT = "api/add-product";
    public static final String UPDATE_PRODUCT = "api/update-product";
    public static final String GET_PRODUCT_BY_ID = "api/product";
    public static final String DELETE_PRODUCT_BY_ID = "api/delete-product";

    //image related
    public static final String UPLOAD_IMAGE = "api/upload-image";

    //admin related
    public static final String ADMIN_LOGIN = "api/admin-login";
    public static final String ADD_ADMIN_USER = "api/add-admin";

    //category related
    public static final String GET_ALL_CETEGORIES = "api/category";
    public static final String GET_ALL_CETEGORIES_LIST = "api/all-category";
    public static final String ADD_CATEGORY = "api/add-category";
    public static final String UPDATE_CATEGORY = "api/update-category";
    public static final String DELETE_CATEGORY = "api/delete-category";
    public static final String GET_CATEGORY_BY_ID = "api/category-by-id";

    //address related
    public static final String ADDRESS_COMMON = "api/address";
    public static final String UPDATE_ADDRESS_BY_ID = "api/updateAddressById";
    public static final String GET_ADDRESS_BY_ID = "api/addressById";
    public static final String GET_DEFAULT_ADDRESS_BY_ID = "api/getDefaultAddress";

    //address related
    public static final String ORDER_COMMON = "api/order";
    public static final String ORDERS_COMMON = "api/orders";

}
