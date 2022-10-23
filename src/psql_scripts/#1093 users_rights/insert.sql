INSERT INTO role(name) VALUES ('saller');
INSERT INTO role(name) VALUES ('buyer');

INSERT INTO users(name, role_id) VALUES ('IVAN', 1);
INSERT INTO users(name, role_id) VALUES ('NIKOLAY', 2);

INSERT INTO rules(name) VALUES ('check rule');

INSERT INTO rules_role(role_id, rules_id, name) VALUES (1, 1, 'give a check');
INSERT INTO rules_role(role_id, rules_id, name) VALUES (2, 1, 'take a check');

INSERT INTO category(name) VALUES ('food');

INSERT INTO state(name) VALUES ('process');
INSERT INTO state(name) VALUES ('complete');

INSERT INTO item(users_id, category_id, state_id, name) VALUES (2, 1, 1, 'NIKOLAY BYU FOOD in process');

INSERT INTO comments(name, item_id) VALUES ('nice service', 1);

INSERT INTO attachs(name, item_id) VALUES ('check#1321', 1);