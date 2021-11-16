CREATE TABLE image
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    uuid       VARCHAR(255) NULL,
    file_path  VARCHAR(255) NULL,
    image_size BIGINT NULL,
    CONSTRAINT pk_image PRIMARY KEY (id)
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
    register_date     datetime NULL,
    user_type         INT NULL,
    image_id          BIGINT NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE user
    ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE user
    ADD CONSTRAINT FK_USER_ON_IMAGE FOREIGN KEY (image_id) REFERENCES image (id);