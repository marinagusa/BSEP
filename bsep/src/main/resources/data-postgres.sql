insert into USERS(username, password, first_name, last_name, email, enabled, unsuccessful_logins) values ('marina123', '$2a$10$9.Ib2goNX02K.vjHLfpmNOhhYk47jA.dMRgnmmYCBOutrw05DKq0.', 'Marina', 'Gusa', 'marinagusa@gmail.com', true, 0);
insert into USERS(username, password, first_name, last_name, email, enabled, unsuccessful_logins) values ('pera123', '$2a$10$bMufo4BJAW2.fY0nHpCQMeAZOHuPKZfeaXCCHPGj6GmDYwKrRbV/q', 'Pera', 'Peric', 'pera@gmail.com', true, 0);
insert into USERS(username, password, first_name, last_name, email, enabled, unsuccessful_logins) values ('mika123', '$2a$10$lEG4QTsk2gFpZovPMAc0XuL1IFc82DmTH6/.0ABsuQEX54cf3ePd6', 'Mika', 'Mikic', 'mika@gmail.com', true, 2);
insert into USERS(username, password, first_name, last_name, email, enabled, unsuccessful_logins) values ('blocked123', 'Blocked123!', 'Blocked', 'Blockic', 'blck@gmail.com', false, 3);
insert into USERS(username, password, first_name, last_name, email, enabled, unsuccessful_logins) values ('blocked1234', 'Blocked1234!', 'Blockked', 'Blockkic', 'blckd@gmail.com', false, 3);

insert into ROLES (name) values ('ROLE_ADMIN');
insert into ROLES (name) values ('ROLE_OWNER');
insert into ROLES (name) values ('ROLE_TENANT');

insert into USER_ROLE(user_id, role_id) values (1, 1);
insert into USER_ROLE(user_id, role_id) values (1, 2);
insert into USER_ROLE(user_id, role_id) values (2, 2);
insert into USER_ROLE(user_id, role_id) values (3, 3);

insert into PRIVILEGE (name) values ('READ_USER');
insert into PRIVILEGE (name) values ('READ_CERTIFICATE');
insert into PRIVILEGE (name) values ('ADD_CERTIFICATE');
insert into PRIVILEGE (name) values ('REVOKE_CERTIFICATE');
insert into PRIVILEGE (name) values ('ADD_USER');
insert into PRIVILEGE (name) values ('CHANGE_ROLE');
insert into PRIVILEGE (name) values ('DELETE_USER');
insert into PRIVILEGE (name) values ('ADD_CSR');

insert into ROLE_PRIVILEGE (role_id, privilege_id) values (1, 1);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (1, 2);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (1, 3);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (1, 4);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (1, 5);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (1, 6);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (1, 7);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (1, 8);

insert into ROLE_PRIVILEGE (role_id, privilege_id) values (2, 1);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (2, 5);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (2, 6);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (2, 8);

insert into ROLE_PRIVILEGE (role_id, privilege_id) values (3, 1);
insert into ROLE_PRIVILEGE (role_id, privilege_id) values (3, 8);

insert into CSR (email, common_name, organization_unit, organization_name, locality_name, state_name, country, given_name, surname, user_id, verified) values ('pera@gmail.com', 'pera', 'it services', 'perina', 'novi sad', 'srbija', 'rs', 'pera', 'peric', '2', true);
insert into CSR (email, common_name, organization_unit, organization_name, locality_name, state_name, country, given_name, surname, user_id, verified) values ('pera1@gmail.com', 'pera1', 'it services', 'perina', 'novi sad', 'srbija', 'rs', 'pera', 'peric', '1', true);
insert into CSR (email, common_name, organization_unit, organization_name, locality_name, state_name, country, given_name, surname, user_id, verified) values ('pera2@gmail.com', 'pera2', 'it services', 'perina1', 'noviad', 'srbija', 'rs', 'pera1', 'peric', '3', true);
insert into CSR (email, common_name, organization_unit, organization_name, locality_name, state_name, country, given_name, surname, user_id, verified) values ('pera3@gmail.com', 'pera3', 'it services', 'perina2', 'novisad', 'srbija', 'rs', 'pera2', 'peric', '4', true);