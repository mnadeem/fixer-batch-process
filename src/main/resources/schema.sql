DROP TABLE product IF EXISTS;

CREATE TABLE product  (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(20),
    description VARCHAR(20),
    p_type VARCHAR(3)
);


DROP TABLE products IF EXISTS;

CREATE TABLE products  (
    id BIGINT NOT NULL,
    name VARCHAR(20),
    description VARCHAR(20),
    p_type VARCHAR(3)
);

insert into product(id, name, description, p_type) 
values(1,'Product1','A product', null),(2,'Product2','A product','ABC'),(3,'Product3','A product',null),(4,'Product4','A product', null);

commit;

