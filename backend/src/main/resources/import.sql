INSERT INTO tbl_role (id, name) VALUES (nextval('role_seq'), 'ROLE_ADMIN') ON CONFLICT (name) DO NOTHING;
INSERT INTO tbl_role (id, name) VALUES (nextval('role_seq'), 'ROLE_FOH') ON CONFLICT (name) DO NOTHING;
INSERT INTO tbl_role (id, name) VALUES (nextval('role_seq'), 'ROLE_BOH') ON CONFLICT (name) DO NOTHING;

INSERT INTO tbl_prep_status (id, name) VALUES (nextval('prep_status_seq'), 'ORDERED') ON CONFLICT (name) DO NOTHING;
INSERT INTO tbl_prep_status (id, name) VALUES (nextval('prep_status_seq'), 'PREPARING') ON CONFLICT (name) DO NOTHING;
INSERT INTO tbl_prep_status (id, name) VALUES (nextval('prep_status_seq'), 'READY') ON CONFLICT (name) DO NOTHING;
INSERT INTO tbl_prep_status (id, name) VALUES (nextval('prep_status_seq'), 'SERVED') ON CONFLICT (name) DO NOTHING;