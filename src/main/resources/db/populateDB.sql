DELETE FROM dishes;
DELETE FROM restaurants;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM votes;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO restaurants (NAME, ADDRESS, PHONE) VALUES
  ('McDonalds', 'Город Н, Центральная площадь 30', '555-55-55'),
  ('Теремок', 'Город Н, Кутувоская улица, 35', '222-22-22'),
  ('Гуси', 'Город Н, Колочевская улица, 5', '111-11-11');

INSERT INTO dishes (NAME, PRICE, RESTAURANT_ID) VALUES
  ('Гамбургер', 60.00, 100002),
  ('Чизбургер', 65.00, 100002),
  ('Чикенбургер', 62.00, 100002),
  ('Кока-кола', 30.00, 100002),
  ('Спрайт', 30.00, 100002),
  ('Блин "Пахарь"', 51.00, 100003),
  ('Блин "Жнец"', 51.00, 100003),
  ('Блин "На дуде игрец"', 52.00, 100003),
  ('Морс', 25.00, 100003),
  ('Квас', 30.20, 100003),
  ('Суп', 51.00, 100004),
  ('Котлеты', 51.00, 100004),
  ('Пюрешка', 52.00, 100004),
  ('Компот ягодный', 25.00, 100004),
  ('Компот из сухофруктов', 30.20, 100004);

