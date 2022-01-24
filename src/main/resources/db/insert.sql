insert into posts (name, author_name) values ('О чем этот форум?', 'root') ON CONFLICT DO NOTHING;
insert into posts (name, author_name) values ('Правила форума.', 'root') ON CONFLICT DO NOTHING;

insert into authorities (authority) values ('ROLE_USER') ON CONFLICT DO NOTHING;
insert into authorities (authority) values ('ROLE_ADMIN') ON CONFLICT DO NOTHING;

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$wY1twJhMQjGVxv4y5dBC5ucCBlzkzT4FIGa4FNB/pS9GaXC2wm9/W',
        (select id from authorities where authority = 'ROLE_ADMIN')) ON CONFLICT DO NOTHING;
