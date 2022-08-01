use fp_db;

-- ---------------
-- role values
-- ---------------
INSERT INTO role
VALUES (0, 'admin');
INSERT INTO role
VALUES (1, 'client');

-- ---------------
-- user values
-- ---------------
INSERT INTO user
VALUES (default, 'lina.nerv@ukr.net', 'linanerv', 'Lina', 'Nerv', null, 1);
INSERT INTO user
VALUES (default, 'roger.pav@gmail.com', 'rogerpav', 'Roger', 'Pav', null, 0);
INSERT INTO user
VALUES (default, 'dana.kulk@gmail.com', 'danakulk', 'Dana', 'Kulk', null, 1);
INSERT INTO user
VALUES (default, 'vlad.black@gmail.com', 'vladblack', 'Vlad', 'Black', null, 1);
-- ---------------
-- company values
-- ---------------
INSERT INTO company
VALUES (default, 'Best Cruises', '2021-12-12');
-- ---------------
-- liner values
-- ---------------
INSERT INTO liner
VALUES (default, 'Dream', '/img/linerPhoto/1.png', 1210, 1);
INSERT INTO liner
VALUES (default, 'Sleep', '/img/linerPhoto/2.png', 1110, 1);
INSERT INTO liner
VALUES (default, 'Star', '/img/linerPhoto/3.png', 1000, 1);
INSERT INTO liner
VALUES (default, 'Flower', '/img/linerPhoto/4.png', 800, 1);
-- ---------------
-- cruise values
-- ---------------
INSERT INTO cruise
VALUES (1, /*'7-Day Canada & New England',*/ '/img/cruisePhoto/1.png',
           /*'Roundtrip from New York City (Manhattan or Brooklyn), New York',*/ 5, 569,
           /*'New York - Newport - Boston - Bar Harbor - Saint John - Halifax',*/ '2022-05-12', '2022-05-19', 1);
INSERT INTO cruise
VALUES (2, /*'15-Day British Isles & Norway Passage',*/ '/img/cruisePhoto/2.png',
           /*'Southampton (London), England to New York City (Manhattan or Brooklyn), New York',*/ 5, 759,
           /*'London - Bergen - Belfast - Cork - Azores Islands - Boston - New York ',*/ '2022-07-01', '2022-07-15', 2);
INSERT INTO cruise
VALUES (3, /*'14-Day Spanish Passage',*/ '/img/cruisePhoto/3.png', /*'Ft. Lauderdale, Florida to Barcelona, Spain',*/ 5,
        787, /*'Ft. Lauderdale - Madeira - Seville - Malaga - Cartagena - Mallorca - Marseille - Barcelona',*/
        '2022-12-1',
        '2022-12-15', 3);
INSERT INTO cruise
VALUES (4, /*'10-Day Southern Caribbean with Curacao',*/ '/img/cruisePhoto/4.png',
           /*'Roundtrip from Ft. Lauderdale, Florida',*/ 6, 901,
           /*'Ft. Lauderdale - Princess Cays - St.Thomas - Dominica - Grenada - Bonaire - Curacao - Aruba - Ft. Lauderdale ',*/
        '2022-07-05', '2022-07-15', 3);
INSERT INTO cruise
VALUES (5, /*'14-Day Caribbean Islander',*/ '/img/cruisePhoto/5.png',
           /*'New York City (Manhattan or Brooklyn), New York to Ft. Lauderdale, Florida',*/ 7, 979,
/*        'New York - San Juan - St.Maarten - St. Kitts - St. Lucia - Bonaire - Curacao - Aruba - Ft. Lauderdale ',*/
        '2022-07-01', '2022-07-15', 4);
INSERT INTO cruise
VALUES (6, /*'4-Day Netherlands & Channel Islands Sampler',*/ '/img/cruisePhoto/6.png',
           /*'Roundtrip from Southampton (London), England',*/ 2, 201, /*'London - Rotterdam - Guernsey - London',*/
        '2022-07-05',
        '2022-07-09', 1);
INSERT INTO cruise
VALUES (7,/* '7-Day Sports Stars Themed Cruise Spain & France',*/ '/img/cruisePhoto/7.png',
           /* 'Roundtrip from Southampton (London), England',*/ 4, 649,
           /*'London - Guernsey - La Rochelle - Bordeaux - Bilbao - La Coruna - London ',*/ '2022-06-09', '2022-06-16',
        2);


-- ---------------
-- translation_cruise values
-- ---------------
INSERT INTO translation_cruise
VALUES (1, 'en', '7-Day Canada & New England', 'Roundtrip from New York City (Manhattan or Brooklyn), New York',
        'New York - Newport - Boston - Bar Harbor - Saint John - Halifax'),
       (1, 'ua', '7-Днів Канада та Нова Англія', 'Туди й назад з Нью-Йорка (Манхеттен або Бруклін), Нью-Йорк',
        'Нью-Йорк – Ньюпорт – Бостон – Бар-Харбор – Сент-Джон – Галіфакс'),
       (2, 'en', '15-Day British Isles & Norway Passage',
        'Southampton (London), England to New York City (Manhattan or Brooklyn), New York',
        'London - Bergen - Belfast - Cork - Azores Islands - Boston - New York '),
       (2, 'ua', '15-Днів Британські острови та Норвезький прохід',
        'Саутгемптон (Лондон), Англія – Нью-Йорк (Манхеттен або Бруклін), Нью-Йорк',
        'Лондон – Берген – Белфаст – Корк – Азорські острови – Бостон – Нью-Йорк'),
       (3, 'en', '14-Day Spanish Passage', 'Ft. Lauderdale, Florida to Barcelona, Spain',
        'Ft. Lauderdale - Madeira - Seville - Malaga - Cartagena - Mallorca - Marseille - Barcelona'),
       (3, 'ua', '14-Днів Іспанський Пасаж', 'Ft. Лодердейл, Флорида, до Барселони, Іспанія',
        'Ft. Лодердейл - Мадейра - Севілья - Малага - Картахена - Майорка - Марсель - Барселона'),
       (4, 'en', '10-Day Southern Caribbean with Curacao', 'Roundtrip from Ft. Lauderdale, Florida',
        'Ft. Lauderdale - Princess Cays - St.Thomas - Dominica - Grenada - Bonaire - Curacao - Aruba - Ft. Lauderdale '),
       (4, 'ua', '10-Днів Південні Кариби з Кюрасао', 'Туди й назад із Ft. Лодердейл, Флорида',
        'Ft. Лодердейл - Принсес-Кейс - Сент-Томас - Домініка - Гренада - Бонайре - Кюрасао - Аруба - Форт. Лодердейл'),
       (5, 'en', '14-Day Caribbean Islander',
        'New York City (Manhattan or Brooklyn), New York to Ft. Lauderdale, Florida',
        'New York - San Juan - St.Maarten - St. Kitts - St. Lucia - Bonaire - Curacao - Aruba - Ft. Lauderdale '),
       (5, 'ua', '14-Днів Карибський островянин',
        'Нью-Йорк (Манхеттен або Бруклін), Нью-Йорк до Ft. Лодердейл, Флорида',
        'Нью-Йорк – Сан-Хуан – Сент-Мартен – Сент-Кітс – Сент-Люсія – Бонайре – Кюрасао – Аруба – Форт. Лодердейл'),
       (6, 'en', '4-Day Netherlands & Channel Islands Sampler', 'Roundtrip from Southampton (London), England',
        'London - Rotterdam - Guernsey - London'),
       (6, 'ua', '4-Дні Нідерланди та Семплер Нормандських островів', 'Туди й назад із Саутгемптона (Лондон), Англія',
        'Лондон - Роттердам - Гернсі - Лондон'),
       (7, 'en', '7-Day Sports Stars Themed Cruise Spain & France', 'Roundtrip from Southampton (London), England',
        'London - Guernsey - La Rochelle - Bordeaux - Bilbao - La Coruna - London '),
       (7, 'ua', '7-Днів Тематичний круїз Sports Stars по Іспанії та Франції',
        'Туди й назад із Саутгемптона (Лондон), Англія',
        'Лондон - Гернсі - Ла-Рошель - Бордо - Більбао - Ла-Корунья - Лондон');

-- ---------------
-- request values
-- ---------------
INSERT INTO request
VALUES (default, 1, 3, 2, 1);
INSERT INTO request
VALUES (default, 1, 2, 3, 1);
INSERT INTO request
VALUES (default, 1, 1, 4, 1);
INSERT INTO request
VALUES (default, 1, 1, 4, 1);
INSERT INTO request
VALUES (default, 2, 1, 4, 1);

-- ---------------
-- port values
-- ---------------

INSERT INTO port
VALUES (1, 'ua', 'Нью-Йорк', 'США'),
       (2, 'ua', 'Бостон', 'США'),
       (3, 'ua', 'Бар Харбор', 'США'),
       (4, 'ua', 'Сейнт Джон', 'Канада'),
       (5, 'ua', 'Галіфакс', 'Канада'),

       (6, 'ua', 'Лондон', 'Великобританія'),
       (7, 'ua', 'Берген', 'Норвегія'),
       (8, 'ua', 'Белфаст', 'Ірландія'),
       (9, 'ua', 'Корк', 'Ірландія'),
       (10, 'ua', 'Азорські острови', 'Португалія'),

       (11, 'ua', 'Ft. Лодердейл', 'США'),
       (12, 'ua', 'Мадейра', 'Португалія'),
       (13, 'ua', 'Севілья', 'Іспанія'),
       (14, 'ua', 'Малага', 'Іспанія'),
       (15, 'ua', 'Картахена', 'Колумбія'),
       (16, 'ua', 'Майорка', 'Іспанія'),
       (17, 'ua', 'Марсель', 'Франція'),
       (18, 'ua', 'Барселона', 'Іспанія'),

       (19, 'ua', 'Принцеса Кейс', 'Багамські острови'),
       (20, 'ua', 'Сейнт Томас', 'США'),
       (21, 'ua', 'Домініка', 'Домінікана'),
       (22, 'ua', 'Гренада', 'Великобританія'),
       (23, 'ua', 'Бонайре', 'Нідерланди'),
       (24, 'ua', 'Кюрасао', 'Нідерланди'),
       (25, 'ua', 'Аруба', 'Нідерланди'),

       (26, 'ua', 'Сан-Хуан', 'Пуерто-Рико'),
       (27, 'ua', 'Сен-Мартен', 'Нідерланди'),
       (28, 'ua', 'Сент-Кітс', 'Великобританія'),
       (29, 'ua', 'Сент-Люсія', 'Сент-Люсія'),

       (30, 'ua', 'Роттердам', 'Нідерланди'),
       (31, 'ua', 'Гернсі', 'Гернсі'),

       (32, 'ua', 'Ла-Рошель', 'Франція'),
       (33, 'ua', 'Бордо', 'Франція'),
       (34, 'ua', 'Більбао', 'Іспанія'),
       (35, 'ua', 'Ла-Корунья', 'Іспанія'),


       (1, 'en', 'New York', 'USA'),
       (2, 'en', 'Boston', 'USA'),
       (3, 'en', 'Bar Harbor', 'USA'),
       (4, 'en', 'Saint John', 'Canada'),
       (5, 'en', 'Halifax', 'Canada'),

       (6, 'en', 'London', 'UK'),
       (7, 'en', 'Bergen', 'Norway'),
       (8, 'en', 'Belfast', 'Ireland'),
       (9, 'en', 'Cork', 'Ireland'),
       (10, 'en', 'Azores Islands', 'Portugal'),

       (11, 'en', 'Ft. Lauderdale', 'USA'),
       (12, 'en', 'Madeira', 'Lisbon'),
       (13, 'en', 'Seville', 'Spain'),
       (14, 'en', 'Malaga', 'Spain'),
       (15, 'en', 'Cartagena', 'Colombia'),
       (16, 'en', 'Mallorca', 'Spain'),
       (17, 'en', 'Marseille', 'France'),
       (18, 'en', 'Barcelona', 'Spain'),

       (19, 'en', 'Princess Cays', 'Bahamas'),
       (20, 'en', 'St.Thomas', 'USA'),
       (21, 'en', 'Dominica', 'Dominican'),
       (22, 'en', 'Grenada', 'UK'),
       (23, 'en', 'Bonaire', 'Netherlands'),
       (24, 'en', 'Curacao', 'Netherlands'),
       (25, 'en', 'Aruba', 'Netherlands'),

       (26, 'en', 'San Juan', 'Puerto Rico'),
       (27, 'en', 'St.Maarten', 'Netherlands'),
       (28, 'en', 'St. Kitts', 'UK'),
       (29, 'en', 'St. Lucia', 'St. Lucia'),

       (30, 'en', 'Rotterdam', 'Netherlands'),
       (31, 'en', 'Guernsey', 'Guernsey'),

       (32, 'en', 'La Rochelle', 'France'),
       (33, 'en', 'Bordeaux', 'France'),
       (34, 'en', 'Bilbao', 'Spain'),
       (35, 'en', 'La Coruna', 'Spain');
;



-- ---------------
-- cruise_has_port values
-- ---------------
-- cruise_id, port_id, sequence number, arrival_time

INSERT INTO cruise_has_port
VALUES (1, 1, 1, '2022-05-12 12:00:00'),
       (1, 2, 2, '2022-05-13 12:00:00'),
       (1, 3, 3, '2022-05-15 12:00:00'),
       (1, 4, 4, '2022-05-17 12:00:00'),
       (1, 5, 5, '2022-05-19 12:00:00'),

       (2, 6, 1, '2022-07-01 12:00:00'),
       (2, 7, 2, '2022-07-03 12:00:00'),
       (2, 8, 3, '2022-07-05 12:00:00'),
       (2, 9, 4, '2022-07-06 12:00:00'),
       (2, 10, 5, '2022-07-08 12:00:00'),
       (2, 2, 6, '2022-07-12 12:00:00'),
       (2, 1, 7, '2022-07-15 12:00:00'),

       (3, 11, 1, '2022-12-1 12:00:00'),
       (3, 12, 2, '2022-12-3 12:00:00'),
       (3, 13, 3, '2022-12-5 12:00:00'),
       (3, 14, 4, '2022-12-7 12:00:00'),
       (3, 15, 5, '2022-12-9 12:00:00'),
       (3, 16, 6, '2022-12-11 12:00:00'),
       (3, 17, 7, '2022-12-12 12:00:00'),
       (3, 18, 8, '2022-12-15 12:00:00'),

       (4, 11, 1, '2022-07-05 12:00:00'),
       (4, 19, 2, '2022-07-07 12:00:00'),
       (4, 20, 3, '2022-07-08 12:00:00'),
       (4, 21, 4, '2022-07-10 12:00:00'),
       (4, 22, 5, '2022-07-11 12:00:00'),
       (4, 23, 6, '2022-07-12 12:00:00'),
       (4, 24, 7, '2022-07-13 12:00:00'),
       (4, 25, 8, '2022-07-14 12:00:00'),
       (4, 11, 9, '2022-07-15 12:00:00'),

       (5, 1, 1, '2022-07-01 12:00:00'),
       (5, 26, 2, '2022-07-03 12:00:00'),
       (5, 27, 3, '2022-07-05 12:00:00'),
       (5, 28, 4, '2022-07-07 12:00:00'),
       (5, 29, 5, '2022-07-09 12:00:00'),
       (5, 23, 6, '2022-07-11 12:00:00'),
       (5, 24, 7, '2022-07-12 12:00:00'),
       (5, 25, 8, '2022-07-14 12:00:00'),
       (5, 11, 9, '2022-07-15 12:00:00'),

       (6, 6, 1, '2022-07-05 12:00:00'),
       (6, 30, 2, '2022-07-06 12:00:00'),
       (6, 31, 3, '2022-07-08 12:00:00'),
       (6, 6, 4, '2022-07-09 12:00:00'),
       -- 'London - Guernsey - La Rochelle - Bordeaux - Bilbao - La Coruna - London ', '2022-06-09', '2022-06-16', 2);

       (7, 6, 1, '2022-06-09 12:00:00'),
       (7, 31, 2, '2022-06-10 12:00:00'),
       (7, 32, 3, '2022-06-11 12:00:00'),
       (7, 33, 4, '2022-06-12 12:00:00'),
       (7, 34, 5, '2022-06-14 12:00:00'),
       (7, 35, 6, '2022-06-15 12:00:00'),
       (7, 6, 7, '2022-06-16 12:00:00')
;


SELECT *
FROM request
         INNER JOIN cruise c on request.cruise_id = c.id
         INNER JOIN user u on request.user_id = u.id
         INNER JOIN translation_cruise t on c.id = t.cruise_id
where lang='ua';

select *
from user



