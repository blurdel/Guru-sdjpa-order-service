drop table if exists order_header;
create table order_header
(
    id bigint not null auto_increment primary key,
    customer varchar(255),
    shipping_address varchar(30),
    shipping_city varchar(30),
    shipping_state varchar(30),
    shipping_zip_code varchar(30),
    bill_to_address varchar(30),
    bill_to_city varchar(30),
    bill_to_state varchar(30),
    bill_to_zip_code varchar(30),
    order_status varchar(30),
    created_date timestamp,
    last_modified_date timestamp
) engine = InnoDB;

drop table if exists product;
create table product
(
    id bigint not null auto_increment primary key,
    description varchar(100),
    product_status varchar(20),
    created_date timestamp,
    last_modified_date timestamp
) engine = InnoDB;
