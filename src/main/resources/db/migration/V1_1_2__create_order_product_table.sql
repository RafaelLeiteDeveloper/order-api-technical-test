CREATE TABLE TB_ORDER_PRODUCTS (
    FK_ORDER_ID varchar(36) REFERENCES TB_ORDERS(PK_ORDER_ID),
    FK_PRODUCT_ID varchar(36) REFERENCES TB_PRODUCTS(PK_PRODUCT_ID),
    IT_QUANTITY INT NOT NULL,
    PRIMARY KEY (FK_ORDER_ID, FK_PRODUCT_ID)
);
