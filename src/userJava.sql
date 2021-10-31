--
-- User java
--

drop user 'java'@'localhost';
flush privileges;
create user 'java'@'localhost' identified by 'himitsu';
GRANT SELECT, INSERT, UPDATE, DELETE ON `callcenter`.* TO 'java'@'localhost';