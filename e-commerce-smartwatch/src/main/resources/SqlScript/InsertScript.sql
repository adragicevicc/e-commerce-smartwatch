INSERT INTO Grad
VALUES (1, 'Novi Sad', 21000);

INSERT INTO Grad
VALUES (2, 'Beograd', 11000);

INSERT INTO Grad
VALUES (3, 'Loznica', 15300);

INSERT INTO Grad
VALUES (4, 'Subotica', 24000);

SELECT * from Grad;

INSERT INTO Adresa
VALUES (1, 'Lasla Gala', '1a', 1);

INSERT INTO Adresa
VALUES (2, 'Bulevar Despota Stefana', '55', 2);

INSERT INTO Adresa
VALUES (3, 'Drinska', '15', 3);

INSERT INTO Adresa
VALUES (4, 'Sonje Marinkovic', '30', 4);

SELECT * from Adresa;

INSERT INTO Korisnik
VALUES (1, 'Marko', 'Markovic', 'mmarkovic@gmail.com', '123456', 'zaposleni');

INSERT INTO Korisnik
VALUES (2, 'Nikola', 'Nikolic', 'nnikolic@gmail.com', '789456', 'kupac');

INSERT INTO Korisnik
VALUES (3, 'Teodora', 'Teodorovic', 'tteodorovic@gmail.com', 'qwerty', 'zaposleni');

INSERT INTO Korisnik
VALUES (4, 'Milos', 'Milosevic', 'mmilosevic@gmail.com', 'asdfg', 'zaposleni');

INSERT INTO Korisnik
VALUES (5, 'Marija', 'Markovic', 'marijamarkovic@gmail.com', '852741', 'kupac');

INSERT INTO Korisnik
VALUES (6, 'Tamara', 'Nikolic', 'tnikolic@gmail.com', '963258', 'kupac');

INSERT INTO Korisnik
VALUES (7, 'Stefan', 'Stefanovic', 'sstefanovic@gmail.com', '963258', 'kupac');

INSERT INTO Korisnik
VALUES (8, 'Admin', 'Admin', 'admineCommerceSmartWatch@gmail.com', 'admin123', 'admin');

SELECT * from Korisnik;

INSERT INTO Zaposleni
VALUES ('2020-10-20', 'prodaja', 1);

INSERT INTO Zaposleni
VALUES ('2020-03-20', 'skladiste', 3);

INSERT INTO Zaposleni
VALUES ('2020-11-20', 'prodaja', 4);

SELECT * from Zaposleni;

INSERT INTO Kupac
VALUES (0658974520, 2, 1);

INSERT INTO Kupac
VALUES (0665214852, 5, 2);

INSERT INTO Kupac
VALUES (0626587415, 6, 3);

INSERT INTO Kupac
VALUES (0626775815, 7, 4);

SELECT * from Kupac;

INSERT INTO Proizvodjac
VALUES (1, 'Apple');

INSERT INTO Proizvodjac
VALUES (2, 'Huawei');

INSERT INTO Proizvodjac
VALUES (3, 'Samsung');

INSERT INTO Proizvodjac
VALUES (4, 'Xiaomi');

SELECT * from Proizvodjac;

INSERT INTO Proizvod
VALUES (1, 37000, 10, 'karakteristike', 'Watch 3', 1);

INSERT INTO Proizvod
VALUES (2, 40000, 10, 'karakteristike', 'GT2 Pro', 2);

INSERT INTO Proizvod
VALUES (3, 35000, 10, 'karakteristike', 'Smart watch 3 Pro', 3);

INSERT INTO Proizvod
VALUES (4, 16000, 10,'karakteristike', 'Mi Watch', 4);

INSERT INTO Proizvod
VALUES (5, 40000, 10, 'karakteristike', 'GT3', 2);

SELECT * from Proizvod;

INSERT INTO Korpa
VALUES (1, 37000, 1);

INSERT INTO Korpa
VALUES (2, 0, 0);

INSERT INTO Korpa
VALUES (3, 0, 0);

INSERT INTO Korpa
VALUES (4, 80000, 1);

SELECT * from Korpa;

INSERT INTO Proizvod_u_korpi
VALUES (1, 1, 37000, 1, 1);

INSERT INTO Proizvod_u_korpi
VALUES (2, 2, 80000, 1, 2);

SELECT * from Proizvod_u_korpi;
		
INSERT INTO Porudzbina(id_porudzbina, ukupan_iznos, datum_porudzbine, datum_isporuke, status, uplata, id_kupac, id_korpa)
VALUES (1, 37000, CURRENT_DATE, '2020-04-25', 'primljena', false, 2, 1);

INSERT INTO Porudzbina(id_porudzbina, ukupan_iznos, datum_porudzbine, datum_isporuke, status, uplata, id_kupac, id_korpa)
VALUES (2, 70000, '2020-04-10', '2020-04-15', 'zavrsena', true, 5, 4);

SELECT * from Porudzbina;

/*
/*PROVERA TRIGGERA 1*/
INSERT INTO Proizvod
VALUES (6, 'sat2', 'karakteristike2', 75000, 0, 1);

SELECT * FROM Proizvod;

INSERT INTO Korpa
VALUES (5, 150000, 1);

SELECT * from Korpa;

INSERT INTO Proizvod_u_korpi
VALUES (3, 1, 37000, 2, 6);

INSERT INTO Proizvod_u_korpi
VALUES (4, 2, 74000, 2, 6);

SELECT * from Proizvod_u_korpi

delete from Proizvod where id_proizvod=6;
delete from Korpa where id_korpa=5;
delete from Proizvod_u_korpi where id_proizvod_u_korpi = 3 or id_proizvod_u_korpi=4;

/*PROVERA TRIGGERA 3*/

INSERT INTO Proizvod
VALUES (6, 'sat3', 'karakterstike3', 5, 5, 1);

INSERT INTO Proizvod
VALUES (7, 'sat4', 'karakterstike4', 10, 5, 1);

SELECT * from Proizvod;

INSERT INTO Korpa
VALUES (3, 0, 1);
/*pogresno uneti podaci*/

select * from Korpa

INSERT INTO Proizvod_u_korpi
VALUES (2, 1, 0, 5, 2);


SELECT * from Proizvod_u_korpi;

delete from Proizvod_u_korpi where id_proizvod_u_korpi=3 or id_proizvod_u_korpi=4;
delete from Proizvod where id_proizvod=3 or id_proizvod=4;
delete from Korpa where id_korpa=3;

--PROVERA TRIGERA

INSERT INTO Korpa
VALUES (6, 0, 0);


INSERT INTO Proizvod_u_korpi
VALUES (6, 2, 74000, 6, 1);

SELECT * from Proizvod_u_korpi;

select * from Korpa
*/



