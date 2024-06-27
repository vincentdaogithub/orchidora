CREATE TABLE account
(
    id           UUID         NOT NULL,
    version      INT,
    name         VARCHAR(255) NOT NULL,
    role         VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    address      VARCHAR(255) NOT NULL,
    email        VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    status       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_account PRIMARY KEY (id)
);

CREATE TABLE orchid
(
    id          UUID           NOT NULL,
    version     INT,
    name        VARCHAR(255)   NOT NULL,
    image       VARCHAR(255)   NOT NULL,
    color       VARCHAR(255)   NOT NULL,
    description VARCHAR(255)   NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    category_id UUID           NOT NULL,
    CONSTRAINT pk_orchid PRIMARY KEY (id)
);

CREATE TABLE orchid_category
(
    id          UUID         NOT NULL,
    version     INT,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    CONSTRAINT pk_orchidcategory PRIMARY KEY (id)
);

CREATE TABLE orchid_inventory
(
    id        UUID NOT NULL,
    version   INT,
    orchid_id UUID NOT NULL,
    quantity  INT  NOT NULL,
    CONSTRAINT pk_orchidinventory PRIMARY KEY (id)
);

CREATE TABLE "order"
(
    id           UUID         NOT NULL,
    version      INT,
    account_id   UUID         NOT NULL,
    account_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    address      VARCHAR(255) NOT NULL,
    status       SMALLINT     NOT NULL,
    created_at   TIMESTAMP,
    CONSTRAINT pk_order PRIMARY KEY (id)
);

CREATE TABLE order_item
(
    id        UUID           NOT NULL,
    version   INT,
    order_id  UUID           NOT NULL,
    orchid_id UUID,
    name      VARCHAR(255)   NOT NULL,
    price     DECIMAL(10, 2) NOT NULL,
    CONSTRAINT pk_orderitem PRIMARY KEY (id)
);

CREATE TABLE order_payment
(
    id         UUID           NOT NULL,
    version    INT,
    order_id   UUID           NOT NULL,
    payment_id UUID           NOT NULL,
    amount     DECIMAL(10, 2) NOT NULL,
    paid_on    TIMESTAMP,
    CONSTRAINT pk_orderpayment PRIMARY KEY (id)
);

CREATE TABLE payment
(
    id                  UUID         NOT NULL,
    version             INT,
    account_id          UUID         NOT NULL,
    payment_id          VARCHAR(255) NOT NULL,
    authentication_code VARCHAR(255) NOT NULL,
    method              VARCHAR(255) NOT NULL,
    CONSTRAINT pk_payment PRIMARY KEY (id)
);

ALTER TABLE account
    ADD CONSTRAINT uc_account_email UNIQUE (email);

ALTER TABLE orchid
    ADD CONSTRAINT uc_orchid_name UNIQUE (name);

ALTER TABLE orchid_category
    ADD CONSTRAINT uc_orchidcategory_name UNIQUE (name);

ALTER TABLE orchid_inventory
    ADD CONSTRAINT uc_orchidinventory_orchid UNIQUE (orchid_id);

ALTER TABLE payment
    ADD CONSTRAINT uc_payment_paymentid UNIQUE (payment_id);

ALTER TABLE orchid_inventory
    ADD CONSTRAINT FK_ORCHIDINVENTORY_ON_ORCHID FOREIGN KEY (orchid_id) REFERENCES orchid (id);

ALTER TABLE orchid
    ADD CONSTRAINT FK_ORCHID_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES orchid_category (id);

ALTER TABLE order_item
    ADD CONSTRAINT FK_ORDERITEM_ON_ORCHID FOREIGN KEY (orchid_id) REFERENCES orchid (id);

ALTER TABLE order_item
    ADD CONSTRAINT FK_ORDERITEM_ON_ORDER FOREIGN KEY (order_id) REFERENCES "order" (id);

ALTER TABLE order_payment
    ADD CONSTRAINT FK_ORDERPAYMENT_ON_ORDER FOREIGN KEY (order_id) REFERENCES "order" (id);

ALTER TABLE order_payment
    ADD CONSTRAINT FK_ORDERPAYMENT_ON_PAYMENT FOREIGN KEY (payment_id) REFERENCES payment (id);

ALTER TABLE "order"
    ADD CONSTRAINT FK_ORDER_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES account (id);

ALTER TABLE payment
    ADD CONSTRAINT FK_PAYMENT_ON_ACCOUNT FOREIGN KEY (account_id) REFERENCES account (id);