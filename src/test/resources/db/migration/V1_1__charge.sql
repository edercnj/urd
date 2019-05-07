INSERT INTO server values (1, 'SERVER 1', CURRENT_TIMESTAMP(2));
INSERT INTO server values (2, 'SERVER 2', CURRENT_TIMESTAMP(2));
INSERT INTO server values (3, 'SERVER 3', CURRENT_TIMESTAMP(2));

INSERT INTO application values (nextval('application_id_seq'),'application 1' , '/data/logs/application1' ,1, CURRENT_TIMESTAMP(2));
INSERT INTO application values (nextval('application_id_seq'),'application 2' , '/data/logs/application2' ,2, CURRENT_TIMESTAMP(2));
INSERT INTO application values (nextval('application_id_seq'),'application 3' , '/data/logs/application3' ,3, CURRENT_TIMESTAMP(2));

COMMIT;