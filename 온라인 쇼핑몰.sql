-- ȸ�� ���̺�(member)
DROP TABLE member CASCADE CONSTRAINTS;

CREATE TABLE member(
    id      VARCHAR2(20) PRIMARY KEY,
    pwd     VARCHAR2(20),
    name    VARCHAR2(40),
    email   VARCHAR2(40),
    zip_num VARCHAR2(7),
    address VARCHAR2(100),
    phone   VARCHAR2(20),
    useyn   VARCHAR2(20) DEFAULT 'y',
    regdate DATE    DEFAULT SYSDATE
);

-- �����
insert into member(id, pwd, name, zip_num, address, phone) values
('one', '1111', '�質��', '133-110', '����ü�����������1�� 1����21ȣ', '017-777-7777');
insert into member(id, pwd, name, zip_num, address, phone) values
('two', '2222', '�̹���', '130-120', '����ü��ı����2�� ������ ����Ʈ 201�� 505ȣ', '011-123-4567');
commit;

-- ��ǰ ���̺�
DROP TABLE product CASCADE CONSTRAINTS;

CREATE TABLE product(
    pseq    NUMBER(5) PRIMARY KEY,
    name    VARCHAR2(100),
    kind    CHAR(1),   -- 1:��, 2:����, 3:����, 4:������, 5:����Ŀ��
    price1  NUMBER(7) DEFAULT 0,
    price2  NUMBER(7) DEFAULT 0,
    price3  NUMBER(7) DEFAULT 0,
    content VARCHAR2(1000) DEFAULT NULL,
    image   VARCHAR2(40) DEFAULT 'default.jpg',
    useyn   CHAR(1) DEFAULT 'y',
    bestyn  CHAR(1) DEFAULT 'n',
    regdate DATE    DEFAULT sysdate
);

DROP SEQUENCE product_seq;

CREATE SEQUENCE product_seq START WITH 1 INCREMENT BY 1;

-- ��ǰ
insert into product(pseq, name, kind, price1, price2, price3, content, image) values(
product_seq.nextval, 'ũ��Ŀ���Ϻ���', '2', '40000', '50000', '10000', '�������� ũ��Ŀ���Ϻ��� �Դϴ�.', 'w2.jpg');
insert into product(pseq, name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval, '�պ���', '2', 40000, 50000, 10000,'������ �պ��� �Դϴ�.', 'w-28.jpg', 'n');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values( 
product_seq.nextval, '��', '1', '10000', '12000', '2000', '���������� ��', 'w-26.jpg', 'n');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval, '������', '4', '5000', '5500', '500', '����� �������Դϴ�.', 'w-25.jpg', 'y');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval, 'ȸ����', '1', '10000', '12000', '2000', '���������� ��', 'w9.jpg', 'n');
insert into product(pseq,  name, kind, price1, price2, price3, content, image) values(
product_seq.nextval, '��������', '2', '12000', '18000', '6000', '������ ����', 'w4.jpg');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval,  '��ũ����', '3', '5000', '5500', '500', '������� �����Դϴ�.', 'w-10.jpg', 'y');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval, '������', '3', '5000', '5500', '500', '����� �������Դϴ�.', 'w11.jpg', 'y');
insert into product(pseq,  name, kind, price1, price2, price3, content, image) values(
product_seq.nextval,  '����Ŀ��', '4', '15000', '20000', '5000', 'Ȱ������ ���� ����Ŀ���Դϴ�.', 'w1.jpg');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval,  '����', '3', '5000', '5500', '500', '������� �����Դϴ�.', 'w-09.jpg','n');
insert into product(pseq,  name, kind, price1, price2, price3, content, image, bestyn) values(
product_seq.nextval,  '����Ŀ��', '5', '15000', '20000', '5000', 'Ȱ������ ���� ����Ŀ���Դϴ�.', 'w-05.jpg','n');

commit;

-- ������ ���̺�
DROP TABLE admin CASCADE CONSTRAINTS;

CREATE TABLE admin(
    id  VARCHAR2(20) PRIMARY KEY,
    pwd VARCHAR2(20),
    name    VARCHAR2(40),
    phone   VARCHAR2(20)
);

-- ������
insert into admin values('admin', 'admin', 'ȫ����', '010-777-7777');
insert into admin values('soonshin', '1234', '�̼���', '010-999-9696');

commit;

-- ��ٱ���(cart) ���̺�
DROP TABLE cart CASCADE CONSTRAINTS;
CREATE TABLE cart(
    cseq    NUMBER(10) PRIMARY KEY,
    id      VARCHAR2(20),
    pseq    NUMBER(5),
    quantity NUMBER(5)  DEFAULT 1,
    result   CHAR(1)    DEFAULT '1',
    indate   DATE  DEFAULT sysdate,
    CONSTRAINT fk_id  FOREIGN KEY(id) REFERENCES member(id),
    CONSTRAINT fk_pseq FOREIGN KEY(pseq) REFERENCES product(pseq)
);

DROP SEQUENCE cart_seq;

CREATE SEQUENCE cart_seq START WITH 1 INCREMENT BY 1;

-- ��ٱ���
insert into cart(cseq,id, pseq, quantity) values(cart_seq.NEXTVAL, 'one', 1, 1);
commit;

-- �ֹ� ���̺�(orders)
DROP TABLE orders CASCADE CONSTRAINTS;

CREATE TABLE orders(
    oseq    NUMBER(10)  PRIMARY KEY,
    id      VARCHAR2(20),
    indate  DATE    DEFAULT sysdate,
    CONSTRAINT fk_orders_id FOREIGN KEY(id) REFERENCES member(id)
);
DROP SEQUENCE orders_seq;

CREATE SEQUENCE orders_seq START WITH 1 INCREMENT BY 1;

-- �ֹ�
insert into orders(oseq, id) values(orders_seq.nextval, 'one');
insert into orders(oseq, id) values(orders_seq.nextval, 'one');
insert into orders(oseq, id) values(orders_seq.nextval, 'two');
commit;

-- �ֹ��� ���̺�(order_detail)
DROP TABLE order_detail CASCADE CONSTRAINTS;
CREATE TABLE order_detail (
    odseq       NUMBER(10)  PRIMARY KEY,
    oseq        NUMBER(10),
    pseq        NUMBER(5),
    quantity    NUMBER(5),
    result      CHAR(1) default '1',
    CONSTRAINT fk_od_oseq FOREIGN KEY(oseq) REFERENCES orders(oseq),
    CONSTRAINT fk_od_pseq FOREIGN KEY(pseq) REFERENCES product(pseq)
);

DROP SEQUENCE order_detail_seq;

CREATE SEQUENCE order_detail_seq START WITH 1 INCREMENT BY 1;

-- �ֹ� ��
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 1, 1, 1);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 1, 2, 5);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 2,  4, 3);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 3, 3, 1);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 3, 2, 1);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 3, 6, 2);
insert into order_detail(odseq, oseq, pseq, quantity) 
values(order_detail_seq.nextval, 3, 1, 2);

commit;

-- QNA �Խ��� ���̺�(qna)
DROP TABLE qna CASCADE CONSTRAINTS;

CREATE TABLE qna(
    qseq        NUMBER(5) PRIMARY KEY,
    subject     VARCHAR2(30),
    content     VARCHAR2(1000),
    reply       VARCHAR2(1000),
    id          VARCHAR2(20),
    rep         CHAR(1) DEFAULT '1',
    indate      DATE DEFAULT sysdate,
    CONSTRAINT fk_qna_id FOREIGN KEY(id) REFERENCES member(id)
);

DROP SEQUENCE qna_seq;
CREATE SEQUENCE qna_seq START WITH 1 INCREMENT BY 1;

-- QnA
insert into qna (qseq, subject, content, id)
values(qna_seq.nextval, '�׽�Ʈ', '��������1', 'one');
update qna SET reply='�亯����', rep='2';

insert into qna (qseq, subject, content, id)
values(qna_seq.nextval, '�׽�Ʈ2', '��������2', 'one');
commit;

-- �Ż�ǰ ��ȸ
-- �Ż�ǰ ��ȸ �� ����
CREATE OR REPLACE VIEW new_pro_view AS
SELECT pseq, name, price2, image
FROM 
(SELECT row_number() OVER(ORDER BY regdate DESC) rn, pseq, name, price2, image 
FROM product
WHERE useyn='y')
WHERE rn<=4;

select * from new_pro_view;

-- ����Ʈ ��ǰ ��ȸ
-- ����Ʈ ��ǰ ��ȸ �� ����
CREATE OR REPLACE VIEW best_pro_view AS
SELECT pseq, name, price2, image
FROM 
(SELECT row_number() OVER(ORDER BY regdate DESC) rn, pseq, name, price2, image 
FROM product
WHERE bestyn='y')
WHERE rn<=4;

select * from best_pro_view;

commit;

select * from member;

-- Address ���̺� ���� �� ������ Insert
@zip

select count(*) from address;
select * from address;

drop table address;
commit;

