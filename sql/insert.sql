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
VALUES (1, '/img/cruisePhoto/1.png', 5, 569, '2022-05-12', '2022-05-19', 1);
INSERT INTO cruise
VALUES (2, '/img/cruisePhoto/2.png', 5, 759, '2022-07-01', '2022-07-15', 2);
INSERT INTO cruise
VALUES (3, '/img/cruisePhoto/3.png', 5, 787, '2022-12-1', '2022-12-15', 3);
INSERT INTO cruise
VALUES (4, '/img/cruisePhoto/4.png', 6, 901, '2022-07-05', '2022-07-15', 3);
INSERT INTO cruise
VALUES (5, '/img/cruisePhoto/5.png', 7, 979, '2022-07-01', '2022-07-15', 4);
INSERT INTO cruise
VALUES (6, '/img/cruisePhoto/6.png', 2, 201, '2022-07-05', '2022-07-09', 1);
INSERT INTO cruise
VALUES (7, '/img/cruisePhoto/7.png', 4, 649, '2022-06-09', '2022-06-16', 2);


-- ---------------
-- translation_cruise values
-- ---------------
INSERT INTO translation_cruise
VALUES (1, 'en', '7-Day Canada & New England', 'Roundtrip from New York City (Manhattan or Brooklyn), New York'),
       (1, 'ua', '7-Днів Канада та Нова Англія', 'Туди й назад з Нью-Йорка (Манхеттен або Бруклін), Нью-Йорк'),
       (2, 'en', '15-Day British Isles & Norway Passage',
        'Southampton (London), England to New York City (Manhattan or Brooklyn), New York'),
       (2, 'ua', '15-Днів Британські острови та Норвезький прохід',
        'Саутгемптон (Лондон), Англія – Нью-Йорк (Манхеттен або Бруклін), Нью-Йорк'),
       (3, 'en', '14-Day Spanish Passage', 'Ft. Lauderdale, Florida to Barcelona, Spain'),
       (3, 'ua', '14-Днів Іспанський Пасаж', 'Ft. Лодердейл, Флорида, до Барселони, Іспанія'),
       (4, 'en', '10-Day Southern Caribbean with Curacao', 'Roundtrip from Ft. Lauderdale, Florida'),
       (4, 'ua', '10-Днів Південні Кариби з Кюрасао', 'Туди й назад із Ft. Лодердейл, Флорида'),
       (5, 'en', '14-Day Caribbean Islander',
        'New York City (Manhattan or Brooklyn), New York to Ft. Lauderdale, Florida'),
       (5, 'ua', '14-Днів Карибський островянин',
        'Нью-Йорк (Манхеттен або Бруклін), Нью-Йорк до Ft. Лодердейл, Флорида'),
       (6, 'en', '4-Day Netherlands & Channel Islands Sampler', 'Roundtrip from Southampton (London), England'),
       (6, 'ua', '4-Дні Нідерланди та Семплер Нормандських островів', 'Туди й назад із Саутгемптона (Лондон), Англія'),
       (7, 'en', '7-Day Sports Stars Themed Cruise Spain & France', 'Roundtrip from Southampton (London), England'),
       (7, 'ua', '7-Днів Тематичний круїз Sports Stars по Іспанії та Франції',
        'Туди й назад із Саутгемптона (Лондон), Англія');

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

       (7, 6, 1, '2022-06-09 12:00:00'),
       (7, 31, 2, '2022-06-10 12:00:00'),
       (7, 32, 3, '2022-06-11 12:00:00'),
       (7, 33, 4, '2022-06-12 12:00:00'),
       (7, 34, 5, '2022-06-14 12:00:00'),
       (7, 35, 6, '2022-06-15 12:00:00'),
       (7, 6, 7, '2022-06-16 12:00:00')
;


select * from cruise_has_port
