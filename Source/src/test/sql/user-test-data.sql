INSERT INTO users(username,password,enabled)
VALUES ('john','doe', TRUE);
 
INSERT INTO user_roles (username, ROLE)
VALUES ('john', 'ROLE_USER');