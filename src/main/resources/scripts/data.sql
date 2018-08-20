INSERT INTO role (id, role_name, description) VALUES (1, 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO role (id, role_name, description) VALUES (2, 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');

-- USER
-- non-encrypted password: jwtpass
INSERT INTO user (id, first_name, last_name, password, username, email) VALUES (1, 'John', 'Doe', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'john.doe','john@gmail.com');
INSERT INTO user (id, first_name, last_name, password, username, email) VALUES (2, 'Admin', 'Admin', '821f498d827d4edad2ed0960408a98edceb661d9f34287ceda2962417881231a', 'admin.admin','admin@gmail.com');


INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);

drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(256) PRIMARY KEY,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256)
);

-- insert client details
INSERT INTO oauth_client_details
   (client_id, client_secret, scope, authorized_grant_types,
   authorities, access_token_validity, refresh_token_validity)
VALUES
   ('crmClient1', 'crmSuperSecret', 'read,write,trust', 'password,refresh_token',
   'ROLE_CLIENT,ROLE_TRUSTED_CLIENT', 900, 2592000);