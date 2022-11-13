INSERT INTO tbl_role (id, name) VALUES (nextval('role_seq'), 'ROLE_ADMIN') ON CONFLICT (name) DO NOTHING;
INSERT INTO tbl_role (id, name) VALUES (nextval('role_seq'), 'ROLE_FOH') ON CONFLICT (name) DO NOTHING;
INSERT INTO tbl_role (id, name) VALUES (nextval('role_seq'), 'ROLE_BOH') ON CONFLICT (name) DO NOTHING;