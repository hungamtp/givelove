// all password is the same (hunghung)
INSERT INTO givelove.users
    (id, avatar, email, full_name, password, phone, username, role_id)
VALUES (3, NULL, ''email@gmail.com'', NULL, ''{bcrypt}$2a$10$lPFg5mN0sFm3dCkrhcsg4OChxx06y9JfNmgjlkp2Vxf/kRL4038P6'',
        NULL, ''useruser1'', 3);
INSERT INTO givelove.users
    (id, avatar, email, full_name, password, phone, username, role_id)
VALUES (4, NULL, ''email@gmail.com'', NULL, ''{bcrypt}$2a$10$RbeJIaYjTMYgaG5ot.rMo.AuDSJMTAOqJHboiufdwBoFa8.wi.UOm'',
        NULL, ''manager'', 2);
INSERT INTO givelove.users
    (id, avatar, email, full_name, password, phone, username, role_id)
VALUES (5, NULL, ''email@gmail.com'', NULL, ''{bcrypt}$2a$10$EEKsvAxdXsIxkQBDeE2rNuy/gmrSKogbOy7QWRoMYJK7oR1yA0BrC'',
        NULL, ''adminhung'', 1);
INSERT INTO givelove.users
    (id, avatar, email, full_name, password, phone, username, role_id)
VALUES (6, NULL, ''email@gmail.com'', NULL, ''{bcrypt}$2a$10$jCWtZkYIcjC.tVIOk7QWIeygVWP8QuIVnJRvAbaKCZ9wHm7GrvTk6'',
        NULL, ''donatorhung'', 4);


//capaign

INSERT INTO givelove.campaign
(id, description, end_date, image, location, money, name, start_date, state, secretaries_id)
VALUES(1, 'capaign', '2020-12-12 00:00:00', '12', 'HCM', 1341234, 'Hung', '2020-12-12 00:00:00', '1', 3);

INSERT INTO givelove.campaign
(id, description, end_date, image, location, money, name, start_date, state, secretaries_id)
VALUES(2, 'capaign', '2020-12-12 00:00:00', '12', 'HCM', 1341234, 'Hung', '2020-12-12 00:00:00', '1', 3);

INSERT INTO givelove.campaign
(id, description, end_date, image, location, money, name, start_date, state, secretaries_id)
VALUES(3, 'capaign', '2020-12-12 00:00:00', '12', 'HCM', 1341234, 'Hung', '2020-12-12 00:00:00', '1', 3);


