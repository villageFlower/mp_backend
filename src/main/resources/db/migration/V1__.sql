CREATE TABLE address
(
    id           BIGINT AUTO_INCREMENT NOT NULL,
    postal_code  INT NULL,
    street       VARCHAR(255) NULL,
    propery_name VARCHAR(255) NULL,
    level        VARCHAR(255) NULL,
    house_no     VARCHAR(255) NULL,
    receiver     VARCHAR(255) NULL,
    contact      VARCHAR(255) NULL,
    created      datetime NULL,
    updated      datetime NULL,
    user_id      BIGINT NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

CREATE TABLE admin_user
(
    id       BIGINT AUTO_INCREMENT NOT NULL,
    email    VARCHAR(255) NULL,
    password VARCHAR(255) NULL,
    token    VARCHAR(255) NULL,
    `role`   VARCHAR(255) NULL,
    created  datetime NULL,
    updated  datetime NULL,
    CONSTRAINT pk_admin_user PRIMARY KEY (id)
);

CREATE TABLE cart_item
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    quantity   INT NULL,
    product_id BIGINT NULL,
    created    datetime NULL,
    updated    datetime NULL,
    user_id    BIGINT NULL,
    order_id   BIGINT NULL,
    CONSTRAINT pk_cart_item PRIMARY KEY (id)
);

CREATE TABLE category
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    name    VARCHAR(255) NULL,
    `rank`  INT NULL,
    active  BIT(1) NULL,
    created datetime NULL,
    updated datetime NULL,
    icon_id BIGINT NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE image
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    uuid       VARCHAR(255) NULL,
    file_path  VARCHAR(255) NULL,
    image_size BIGINT NULL,
    created    datetime NULL,
    updated    datetime NULL,
    product_id BIGINT NULL,
    CONSTRAINT pk_image PRIMARY KEY (id)
);

CREATE TABLE `order`
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    quantity   INT NULL,
    product_id BIGINT NULL,
    created    datetime NULL,
    updated    datetime NULL,
    user_id    BIGINT NULL,
    CONSTRAINT pk_order PRIMARY KEY (id)
);

CREATE TABLE product
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    name          VARCHAR(255) NULL,
    origial_price DOUBLE NULL,
    price         DOUBLE NULL,
    published     BIT(1) NULL,
    `description` TEXT NULL,
    stock         INT NULL,
    created       datetime NULL,
    updated       datetime NULL,
    category_id   BIGINT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE user
(
    id                BIGINT AUTO_INCREMENT NOT NULL,
    first_name        VARCHAR(255) NULL,
    last_name         VARCHAR(255) NULL,
    email             VARCHAR(255) NULL,
    company_name      VARCHAR(255) NULL,
    phone_no          VARCHAR(255) NULL,
    registration_code VARCHAR(255) NULL,
    password          VARCHAR(255) NULL,
    token             VARCHAR(255) NULL,
    created           datetime NULL,
    updated           datetime NULL,
    user_type         INT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE user
    ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE address
    ADD CONSTRAINT FK_ADDRESS_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE cart_item
    ADD CONSTRAINT FK_CART_ITEM_ON_ORDER FOREIGN KEY (order_id) REFERENCES `order` (id);

ALTER TABLE cart_item
    ADD CONSTRAINT FK_CART_ITEM_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE category
    ADD CONSTRAINT FK_CATEGORY_ON_ICON FOREIGN KEY (icon_id) REFERENCES image (id);

ALTER TABLE image
    ADD CONSTRAINT FK_IMAGE_ON_PRODUCT FOREIGN KEY (product_id) REFERENCES product (id);

ALTER TABLE `order`
    ADD CONSTRAINT FK_ORDER_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES category (id);