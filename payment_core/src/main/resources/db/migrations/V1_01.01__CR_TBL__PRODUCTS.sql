create table product
(
    id              bigserial primary key,
    account_number  varchar(255),
    balance         varchar(255),
    type_of_product varchar(255),
    user_id         bigserial references users (id)
);