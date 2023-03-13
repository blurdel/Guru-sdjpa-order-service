SET FOREIGN_KEY_CHECKS=0;

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

drop table if exists order_line;
create table order_line
(
    id bigint not null auto_increment primary key,
    quantity_ordered int,
    order_header_id bigint,
    created_date timestamp,
    last_modified_date timestamp,
    product_id bigint,
    constraint order_header_pk FOREIGN KEY (order_header_id) references order_header(id),
    constraint order_line_product_fk FOREIGN KEY (product_id) references product(id)
) engine = InnoDB;


SET FOREIGN_KEY_CHECKS=1;
