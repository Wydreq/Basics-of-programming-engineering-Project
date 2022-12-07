DROP TABLE Address CASCADE CONSTRAINTS;
DROP TABLE Users CASCADE CONSTRAINTS;
DROP TABLE Equipment CASCADE CONSTRAINTS;
drop sequence address_next;
drop sequence users_next;
drop sequence equipment_next;

CREATE TABLE Address(
    address_id NUMBER CONSTRAINT address_pk PRIMARY KEY NOT NULL,
    street varchar2(100),
    city varchar2(20),
    postCode varchar2(6)
);

CREATE TABLE Users(
    user_id NUMBER PRIMARY KEY NOT NULL,
    login varchar2(30),
    password varchar2(30),
    firstName varchar2(20),
    lastName varchar2(30),
    role varchar2(20) CHECK ( role IN ('Customer','Worker','Manager')),
    subscription varchar2(20)CHECK (subscription IN ('YES','NO')),
    address_id NUMBER,
    CONSTRAINT address_fk FOREIGN KEY (address_id) REFERENCES Address(address_id)
);

CREATE TABLE Equipment(
    equipment_id NUMBER PRIMARY KEY NOT NULL,
    name varchar2(30),
    condition varchar2(20)CHECK (condition IN ('GOOD','BAD'))
);


create sequence address_next
  start with 5
  increment by 1; 

create sequence users_next
  start with 5
  increment by 1;

create sequence equipment_next
  start with 5
  increment by 1;
  
INSERT INTO Equipment (equipment_id, name, condition) VALUES (1, 'Hantle', 'GOOD');
INSERT INTO Equipment (equipment_id, name, condition) VALUES (2, 'Bieżnia', 'BAD');
INSERT INTO Equipment (equipment_id, name, condition) VALUES (3, 'Rowerek', 'BAD');
INSERT INTO Equipment (equipment_id, name, condition) VALUES (4, 'Sztanga', 'GOOD');
INSERT INTO Address (address_id, street, city, postCode) VALUES (1, 'Świętokrzyska 13/234', 'Kielce', '25-005');
INSERT INTO Address (address_id, street, city, postCode) VALUES (2, 'Zofii Nałkowskiej 5/10', 'Kielce', '25-001');
INSERT INTO Address (address_id, street, city, postCode) VALUES (3, 'Warszawska 112/997', 'Kielce', '25-999');
INSERT INTO Address (address_id, street, city, postCode) VALUES (4, 'Artwińskiego 17', 'Kielce', '25-734');
INSERT INTO Users (user_id, login, password, firstName, lastName, role, subscription, address_id) VALUES (1,'wydra','zaq!@WSX','Bartek','Wydrzycki','Manager','NO',1);
INSERT INTO Users (user_id, login, password, firstName, lastName, role, subscription, address_id) VALUES (2,'gembele','zaq!@WSX','Mateusz','Gębka','Worker','YES',2);
INSERT INTO Users (user_id, login, password, firstName, lastName, role, subscription, address_id) VALUES (3,'kseniu','zaq!@WSX','Karol','Górnicki','Worker','YES',3);
INSERT INTO Users (user_id, login, password, firstName, lastName, role, subscription, address_id) VALUES (4,'wojnollo','zaq!@WSX','Jakub','Wojnollo','Customer','NO',4);
