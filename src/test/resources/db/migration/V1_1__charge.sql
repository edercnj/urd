INSERT INTO server (id, hostname, creation_date) values (nextval('server_id_seq'), 'SERVER 1', CURRENT_TIMESTAMP(2));
INSERT INTO server (id, hostname, creation_date) values (nextval('server_id_seq'), 'SERVER 2', CURRENT_TIMESTAMP(2));
INSERT INTO server (id, hostname, creation_date) values (nextval('server_id_seq'), 'SERVER 3', CURRENT_TIMESTAMP(2));

INSERT INTO application (id, name, log_path, server_id, creation_date) values (nextval('application_id_seq'),'application 1' , '/data/logs/application1' ,1, CURRENT_TIMESTAMP(2));
INSERT INTO application (id, name, log_path, server_id, creation_date) values (nextval('application_id_seq'),'application 2' , '/data/logs/application2' ,2, CURRENT_TIMESTAMP(2));
INSERT INTO application (id, name, log_path, server_id, creation_date) values (nextval('application_id_seq'),'application 3' , '/data/logs/application3' ,3, CURRENT_TIMESTAMP(2));