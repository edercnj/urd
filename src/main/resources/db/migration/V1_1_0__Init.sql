----------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `Server` (
 
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `hostname` varchar(75)
 
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

----------------------------------------------------------------------

CREATE TABLE IF NOT EXISTS `ServiceApplication` (
 
    `id` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(75),
    `logPath` varchar(250),
    
    
 
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;

----------------------------------------------------------------------
