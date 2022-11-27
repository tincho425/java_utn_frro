--
-- User java
--

drop user '290076'@'localhost';
flush privileges;
create user '290076'@'localhost' identified by 'tincho123';
GRANT SELECT, INSERT, UPDATE, DELETE ON `martin-java_callcenter`.* TO '290076'@'localhost';