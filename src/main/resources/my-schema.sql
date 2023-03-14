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

drop table if exists category;
create table category (
      id bigint not null auto_increment primary key,
      description varchar(50),
      created_date timestamp,
      last_modified_date timestamp
) engine = InnoDB;

drop table if exists product_category;
create table product_category (
      product_id bigint not null,
      category_id bigint not null,
      primary key (product_id, category_id),
      constraint pc_product_id_fk FOREIGN KEY (product_id) references product(id),
      constraint pc_category_id_fk FOREIGN KEY (category_id) references category(id)
) engine = InnoDB;

insert into product (description, product_status, created_date, last_modified_date)
values ('PRODUCT1', 'NEW', now(), now());

insert into product (description, product_status, created_date, last_modified_date)
values ('PRODUCT2', 'NEW', now(), now());

insert into product (description, product_status, created_date, last_modified_date)
values ('PRODUCT3', 'NEW', now(), now());

insert into product (description, product_status, created_date, last_modified_date)
values ('PRODUCT4', 'NEW', now(), now());

insert into category (description, created_date, last_modified_date) VALUES
    ('CAT1', now(), now());

insert into category (description, created_date, last_modified_date) VALUES
    ('CAT2', now(), now());

insert into category (description, created_date, last_modified_date) VALUES
    ('CAT3', now(), now());

insert into product_category (product_id, category_id)
    SELECT p.id, c.id FROM product p, category c
        where p.description = 'PRODUCT1' and c.description = 'CAT1';

insert into product_category (product_id, category_id)
    SELECT p.id, c.id FROM product p, category c
        where p.description = 'PRODUCT2' and c.description = 'CAT1';

insert into product_category (product_id, category_id)
    SELECT p.id, c.id FROM product p, category c
        where p.description = 'PRODUCT1' and c.description = 'CAT3';

insert into product_category (product_id, category_id)
    SELECT p.id, c.id FROM product p, category c
        where p.description = 'PRODUCT4' and c.description = 'CAT3';


SET FOREIGN_KEY_CHECKS=1;
