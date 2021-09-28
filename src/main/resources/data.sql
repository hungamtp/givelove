INSERT INTO public."role"
(id, "name")
VALUES(1, 'Admin');
INSERT INTO public."role"
(id, "name")
VALUES(2, 'Manager');
INSERT INTO public."role"
(id, "name")
VALUES(4, 'Donator');
INSERT INTO public."role"
(id, "name")
VALUES(3, 'Member');

// campaign
INSERT INTO public.campaign
(id, description, end_date, image, "location", "money", "name", start_date, state, secretaries_id)
VALUES(1, 'hugn', '2020-12-12 00:00:00.000', 'asdf', 'HCM', 2341234, 'asdfas', '1212-12-12 00:00:00.000', '1', 2);
INSERT INTO public.campaign
(id, description, end_date, image, "location", "money", "name", start_date, state, secretaries_id)
VALUES(2, 'hung ne oke', '2020-12-12 00:00:00.000', 'asdf', 'HN', 41324, 'tr em', '1212-12-12 00:00:00.000', '2', 2);
INSERT INTO public.campaign
(id, description, end_date, image, "location", "money", "name", start_date, state, secretaries_id)
VALUES(3, 'oke ne', '2020-12-12 00:00:00.000', 'asdf', 'HCM', 1234, 'ng lon', '1212-12-12 00:00:00.000', '1', 2);
INSERT INTO public.campaign
(id, description, end_date, image, "location", "money", "name", start_date, state, secretaries_id)
VALUES(4, '1234123', '2020-12-12 00:00:00.000', 'asdf', 'HCM', 12341234, 'oker', '1212-12-12 00:00:00.000', '2', 2);

// user mk : hunghung
INSERT INTO public.users
(id, avatar, email, "password", username, role_id, "money", full_name, phone)
VALUES(2, NULL, 'email@gmail.com', '{bcrypt}$2a$10$7que/Cv3mFZUOQO6nrar4.oSeYAoJa0p2u4WXMYiwL3sjFnICWN/C', 'useruser1', 1, NULL, 'Nguyen Ba Hung', '0902751408');
INSERT INTO public.users
(id, avatar, email, "password", username, role_id, "money", full_name, phone)
VALUES(3, NULL, 'email@gmail.com', '{bcrypt}$2a$10$9Bt3hiCBGHKnWPH7a9x7GOflDpM0ccyI0RR2zxRdSfcD8lfmaEo5y', 'useruser2', 2, NULL, 'Truong Tuan Tu', '0909076261');
