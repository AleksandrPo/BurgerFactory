--inserts user with username: 'username' and password: 'password'
insert into users (username, password, email, phone) values  ('username', '$2a$10$UTXHl0UEfIuW1nMiQ8ne0e5TvT2H/bGx4Mv5.m/fU22K7Xu7mHepm', 'user@user.lv', 78904562);
insert into menu (id) values (0);
insert into burger (name, qty, price, burger_menu) values ('Chicken burger', 0, 5.50, 0), ('Pork burger', 0, 6.30, 0), ('Vegan burger', 0, 2.40, 0);
insert into drink (name, qty, price, drink_menu) values ('Cola', 0, 1.20, 0), ('Fanta', 0, 1.20, 0), ('Sprite', 0, 1.20, 0);
insert into potato_free (potato_size, qty, price, potato_free_menu) values ('Small', 0, 0.80, 0), ('Large', 0, 1.20, 0), ('XLarge', 0, 1.50, 0)