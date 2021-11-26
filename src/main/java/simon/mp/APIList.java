package simon.mp;

public class APIList {

    //testing
    public static final String TEST = "api/motherfuckingtest";
    public static final String GIMME_CODE = "api/give-me-code";

    //user related
    public static final String GET_ALL_USERS = "api/users";
    public static final String DELETE_USER_BY_ID = "api/delete-user/{Id}";
    public static final String UPDATE_USER_BY_ID = "api/update-user/{Id}";
    public static final String ADD_USER = "api/register-user";
    public static final String LOGIN_USER = "api/login-user";

    //products related
    public static final String GET_ALL_PRODUCTS = "api/products";
    public static final String ADD_PRODUCT = "api/add-product";

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
}
