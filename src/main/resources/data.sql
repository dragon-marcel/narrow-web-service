INSERT INTO USERS (ID,USER_NAME,PASSWORD,EMAIL,NAME,SURNAME,workplace,telephon,ACTIVE,avatar) VALUES (1,'Bartek','$2a$10$ZkFCKJlv2lih7jtdpuT6GuzKVVxUVt9xLxoD409TOdpepg4NHP4A2','marcel.dragon@o2.pl','Marcel','Dragon','Handel','796-125-251',true,'1');
INSERT INTO USERS (ID,USER_NAME,PASSWORD,EMAIL,NAME,SURNAME,workplace,telephon,ACTIVE) VALUES (2,'Adam','$2a$10$ZkFCKJlv2lih7jtdpuT6GuzKVVxUVt9xLxoD409TOdpepg4NHP4A2','marcel.dragon@o2.pl','Karina','Dragon','Handel','796-125-251',false);
INSERT INTO USERS (ID,USER_NAME,PASSWORD,EMAIL,NAME,SURNAME,workplace,telephon,ACTIVE) VALUES (3,'Jan','$2a$10$ZkFCKJlv2lih7jtdpuT6GuzKVVxUVt9xLxoD409TOdpepg4NHP4A2','marcel.dragon@o2.pl','Jan','Dragon','Handel','796-125-251',false);

INSERT INTO ROLES (ID,NAME) VALUES (1,'ADMIN');
INSERT INTO ROLES (ID,NAME) VALUES (2,'USER');

INSERT INTO USER_ROLE (USER_ID,ROLE_ID) VALUES (1,1);
INSERT INTO USER_ROLE (USER_ID,ROLE_ID) VALUES (2,2);
INSERT INTO USER_ROLE (USER_ID,ROLE_ID) VALUES (3,2);

INSERT INTO ADDITIONAL_DATA_ADRRESS(ID,LAT,LNG,CITY,COUNTRY) VALUES (1,'50.000','20.000','Warszawa','Polska');
INSERT INTO ADDITIONAL_DATA_ADRRESS(ID,LAT,LNG,CITY,COUNTRY) VALUES (2,'50.000','20.000','Wrocław','Polska');
INSERT INTO ADDITIONAL_DATA_ADRRESS(ID,LAT,LNG,CITY,COUNTRY) VALUES (3,'50.000','20.000','Bytom','Polska');

INSERT INTO PROVIDER (ID,NAME,EMAIL,NR_TEL,NIP) VALUES (1,'dostawca 1','test@o2.pl','762-151-515','000-00-00-000');
INSERT INTO PROVIDER (ID,NAME,EMAIL,NR_TEL,NIP) VALUES (2,'dostawca 2','test@o2.pl','762-151-515','000-00-00-000');
INSERT INTO PROVIDER (ID,NAME,EMAIL,NR_TEL,NIP) VALUES (3,'dostawca 3','test@o2.pl','762-151-515','000-00-00-000');

INSERT INTO ASSORTMENT (ID,NAME) VALUES (1,'asortyment 1');
INSERT INTO ASSORTMENT (ID,NAME) VALUES (2,'asortyment 2');
INSERT INTO ASSORTMENT (ID,NAME) VALUES (3,'asortyment 3');

INSERT INTO CUSTOMER (ID,NAME,EMAIL,NR_TEL,NIP) VALUES (1,'klient 1','test@o2.pl','762-151-515','000-00-00-000');
INSERT INTO CUSTOMER (ID,NAME,EMAIL,NR_TEL,NIP) VALUES (2,'klient 1','test@o2.pl','762-151-515','000-00-00-000');
INSERT INTO CUSTOMER (ID,NAME,EMAIL,NR_TEL,NIP) VALUES (3,'klient 1','test@o2.pl','762-151-515','000-00-00-000');

INSERT INTO CURRENCY (ID,NAME,SYMBOL,CREATE_DATE,SALES_PRICE,BUY_PRICE) VALUES  (1,'Euro','EUR','2019-12-01',4.00,4.00);
INSERT INTO CURRENCY (ID,NAME,SYMBOL,CREATE_DATE,SALES_PRICE,BUY_PRICE) VALUES  (2,'Euro','EUR','2019-12-02',4.50,4.50);
INSERT INTO CURRENCY (ID,NAME,SYMBOL,CREATE_DATE,SALES_PRICE,BUY_PRICE) VALUES  (3,'Euro','EUR','2019-12-03',4.50,4.50);
INSERT INTO CURRENCY (ID,NAME,SYMBOL,CREATE_DATE,SALES_PRICE,BUY_PRICE) VALUES  (4,'Euro','EUR','2019-12-04',4.30,4.20);
INSERT INTO CURRENCY (ID,NAME,SYMBOL,CREATE_DATE,SALES_PRICE,BUY_PRICE) VALUES  (5,'Euro','EUR','2019-12-05',4.40,4.50);

INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (1,'1/ZAM/2020','2019-01-01','PLN',12,1,1,1,2,122,2,1,12,2,12,12,1,1,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (3,'2/ZAM/2020','2019-01-01','PLN',12,1,1,1,2,122,2,1,12,2,12,12,1,1,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (2,'3/ZAM/2020','2019-01-01','EUR',12,1,1,1,2,122,2,1,12,2,12,12,1,1,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (4,'4/ZAM/2020','2019-01-02','PLN',12,1,1,1,2,122,2,1,12,2,12,12,1,1,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (5,'5/ZAM/2020','2019-01-05','EUR',12,1,1,1,2,122,2,1,12,2,12,12,1,1,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (6,'6/ZAM/2020','2020-01-05','PLN',12,1,1,1,2,122,2,1,12,2,12,12,1,2,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (7,'7/ZAM/2020','2020-01-06','EUR',12,1,1,1,2,122,2,1,12,2,12,12,1,2,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (8,'8/ZAM/2020','2020-01-10','PLN',12,1,1,1,2,122,2,1,12,2,12,12,2,2,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (9,'9/ZAM/2020','2020-01-10','EUR',12,1,1,1,2,122,2,1,12,2,12,12,2,2,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (10,'10/ZAM/2020','2020-02-01','PLN',12,1,1,1,2,122,2,1,12,2,12,12,1,1,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (11,'12/ZAM/2020','2020-02-01','PLN',12,1,1,1,2,122,2,1,12,2,12,12,1,1,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (12,'13/ZAM/2020','2020-02-01','PLN',12,1,1,1,2,122,2,1,12,2,12,12,1,1,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (13,'14/ZAM/2020','2020-02-01','EUR',12,1,1,1,2,122,2,1,12,2,12,12,1,1,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (14,'15/ZAM/2020','2020-02-02','PLN',12,1,1,1,2,122,2,1,12,2,12,12,1,1,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (15,'16/ZAM/2020','2020-02-02','EUR',12,1,1,1,2,122,2,1,12,2,12,12,3,2,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (16,'17/ZAM/2020','2020-02-03','PLN',12,1,1,1,2,122,2,1,12,2,12,12,3,2,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (17,'18/ZAM/2020','2020-02-03','EUR',12,1,1,1,2,122,2,1,12,2,12,12,2,2,1,3,1,1);
INSERT INTO ORDERS  (ID,NUMBER,CREATE_DATE,SYMBOL,QUANTITY_PURCHASE,CUSTOMER_ID,PROVIDER_ID,VALUE_PURCHASE_EUR,VALUE_PURCHASE_PLN,PRICE_SELL_EUR,PRICE_SELL_PLN,QUANTITY_SELL,VALUE_SELL_EUR,VALUE_SELL_PLN,PROFIT_EUR,PROFIT_PLN,USER_ID,ASSORTMENT_ID,PRICE_PURCHASE_EUR,PRICE_PURCHASE_PLN,STATUS,CURRENCY_ID) VALUES (18,'19/ZAM/2020','2020-02-03','PLN',12,1,1,1,2,122,2,1,12,2,12,12,2,2,1,3,1,1);

INSERT INTO TASKS (ID,TEXT,CREATED_DATE,USER_ID,STATUS_ID,EMAIL) VALUES (1,'task 1 ','2020-01-01',1,1,true);
INSERT INTO TASKS (ID,TEXT,CREATED_DATE,USER_ID,STATUS_ID,EMAIL) VALUES (2,'task 2 ','2020-02-01',1,1,true);
INSERT INTO TASKS (ID,TEXT,CREATED_DATE,USER_ID,STATUS_ID,EMAIL) VALUES (3,'task 3 ','2020-01-01',1,1,true);

