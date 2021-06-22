insert into institutions(name, description) values('Fundacja "Dbam o Zdrowie"','Cel i misja: Pomoc dzieciom z ubogich rodzin.');
insert into institutions(name, description) values('Fundacja "A kogo"','Cel i misja: Pomoc wybudzaniu dzieci ze śpiączki.');
insert into institutions(name, description) values('Fundacja “Dla dzieci"','Cel i misja: Pomoc osobom znajdującym się w trudnej sytuacji życiowej.');
insert into institutions(name, description) values('Fundacja “Bez domu”','Cel i misja: Pomoc dla osób nie posiadających miejsca zamieszkania');
insert into institutions(name, description) values('Fundacja “Pomoc humanitarna”','Cel i misja: Pomoc dla ofiar klęsk żywiołowych');

insert into categories(name) values('ubrania');
insert into categories(name) values('zabawki');
insert into categories(name) values('książki');
insert into categories(name) values('agd');
insert into categories(name) values('inne');

INSERT INTO donations(created_at, city, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES(CURRENT_TIMESTAMP(),'Warszawa','jakiś komentarz 1',CURRENT_DATE()+1,CURRENT_TIME(),4,'Marszałkowska','00-001',1);
INSERT INTO donations(created_at, city, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES(CURRENT_TIMESTAMP(),'Warszawa','jakiś komentarz 2',CURRENT_DATE()+1,CURRENT_TIME(),7,'Chodecka','00-022',2);
INSERT INTO donations(created_at, city, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES(CURRENT_TIMESTAMP(),'Warszawa','jakiś komentarz 3',CURRENT_DATE()+1,CURRENT_TIME(),11,'Warszawska','00-333',3);
INSERT INTO donations(created_at, city, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES(CURRENT_TIMESTAMP(),'Warszawa','jakiś komentarz 4',CURRENT_DATE()+1,CURRENT_TIME(),2,'Puławska','04-444',2);
INSERT INTO donations(created_at, city, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES(CURRENT_TIMESTAMP(),'Warszawa','jakiś komentarz 5',CURRENT_DATE()+1,CURRENT_TIME(),1,'Targowa','55-555',2);
INSERT INTO donations(created_at, city, pick_up_comment, pick_up_date, pick_up_time, quantity, street, zip_code, institution_id) VALUES(CURRENT_TIMESTAMP(),'Warszawa','jakiś komentarz 6',CURRENT_DATE()+1,CURRENT_TIME(),8,'Łazienkowska','66-661',3);

insert into donations_categories values(1,1);
insert into donations_categories values(1,3);
insert into donations_categories values(2,4);
insert into donations_categories values(2,5);
insert into donations_categories values(3,2);
insert into donations_categories values(4,1);
insert into donations_categories values(5,2);
insert into donations_categories values(5,3);
insert into donations_categories values(5,4);
insert into donations_categories values(5,5);
insert into donations_categories values(6,1);
insert into donations_categories values(6,3);
insert into donations_categories values(6,5);