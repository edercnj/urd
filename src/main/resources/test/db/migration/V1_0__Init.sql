----------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS server(
 
    id bigint PRIMARY KEY,
    hostname varchar(75) UNIQUE NOT NULL,
    creationDate TIMESTAMP NOT NULL
    
);

CREATE SEQUENCE IF NOT EXISTS server_id_seq start 1 increment 1;
CREATE INDEX IF NOT EXISTS idx_server_id ON server(id);
CREATE INDEX IF NOT EXISTS idx_server_hostname ON server(hostname);

----------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS application (
 
    id bigint PRIMARY KEY,
    name varchar(75) UNIQUE NOT NULL,
    logPath varchar(250),
    serverId bigint NOT NULL,
    creationDate TIMESTAMP NOT NULL,
    
    CONSTRAINT FK_aplication_server_id FOREIGN KEY (serverId) REFERENCES server (id) ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE SEQUENCE IF NOT EXISTS application_id_seq start 1 increment 1;
CREATE INDEX IF NOT EXISTS idx_application_id ON application(id);
CREATE INDEX IF NOT EXISTS idx_application_name ON application(name);