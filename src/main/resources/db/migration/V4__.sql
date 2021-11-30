ALTER TABLE address
    CHANGE propery_name detail VARCHAR(512);
ALTER TABLE address
    ADD `is_default` BIT(1) NULL;
ALTER TABLE address
    DROP COLUMN street;