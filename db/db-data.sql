INSERT INTO roles(id, role_name, description) 
    VALUES('3f8c76b4-bdee-4195-88a1-4a04a6632063', 'ADMIN', 'admin of the system'),
        ('e8a2fc9c-78ce-46d0-8a6a-93e41381d6ef', 'MANAGER', 'manager of the system'),
        ('7431fd0e-2c14-4964-83a3-05c1198cb18f', 'CUSTOMER', 'customer');

-- Password for all account is VTI123456
INSERT INTO accounts (id, email, password, username, role_id) VALUES 
('1e3b56cc-4eaf-4aa2-920c-5d553ee944e0', 'admin@vti.com.vn', '$2a$10$kzZwQiR.CQJ/F80AzAeMFO1/hiAfx56UxxS6C/s6fXX/QbLV0zXIi', 'admin', '3f8c76b4-bdee-4195-88a1-4a04a6632063'),
('e735ceea-9c7a-4b4f-88a0-3c4aa9cf7758', 'tuan.nguyenhuu@vti.com.vn', '$2a$10$kzZwQiR.CQJ/F80AzAeMFO1/hiAfx56UxxS6C/s6fXX/QbLV0zXIi', 'tuan.nguyenhuu', 'e8a2fc9c-78ce-46d0-8a6a-93e41381d6ef'),
('0fd9838f-dfe4-4c27-83b1-0b95e76d5a9c', 'kiet.quachthe@vti.com.vn', '$2a$10$kzZwQiR.CQJ/F80AzAeMFO1/hiAfx56UxxS6C/s6fXX/QbLV0zXIi', 'kiet.quachthe', '7431fd0e-2c14-4964-83a3-05c1198cb18f');

INSERT INTO permissions (id, permission_name, uri, method) 
    VALUES ('2c88a1cd-066e-4a33-882c-56d5b3fa8dfc', 'createCake', '/cake/', 'POST'),
        ('75c69d95-c3f5-45db-b66b-2cb798a3383e', 'updateCake', '/cake/', 'PUT'),
        ('f720cc95-8046-4e0b-8107-09cd0202e00c', 'getCakes', '/cake/all', 'GET');

INSERT INTO role_permission (role_id, permission_id) 
    VALUES ('3f8c76b4-bdee-4195-88a1-4a04a6632063', '2c88a1cd-066e-4a33-882c-56d5b3fa8dfc'), 
    ('3f8c76b4-bdee-4195-88a1-4a04a6632063', '75c69d95-c3f5-45db-b66b-2cb798a3383e'), 
    ('3f8c76b4-bdee-4195-88a1-4a04a6632063', 'f720cc95-8046-4e0b-8107-09cd0202e00c'), 
    ('e8a2fc9c-78ce-46d0-8a6a-93e41381d6ef', '75c69d95-c3f5-45db-b66b-2cb798a3383e'), 
    ('e8a2fc9c-78ce-46d0-8a6a-93e41381d6ef', 'f720cc95-8046-4e0b-8107-09cd0202e00c'), 
    ('7431fd0e-2c14-4964-83a3-05c1198cb18f', 'f720cc95-8046-4e0b-8107-09cd0202e00c');

INSERT INTO cake (id, name, description, price, is_available, is_deleted, created_at, last_modified_at)
VALUES
('2c88a1cd-066e-4a33-882c-56d5b3fa8dfc', 'Chocolate Cake', 'Rich chocolate cake with ganache frosting', 15.99, true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('75c69d95-c3f5-45db-b66b-2cb798a3383e', 'Vanilla Sponge', 'Soft and fluffy vanilla sponge cake', 12.50, true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('f720cc95-8046-4e0b-8107-09cd0202e00c', 'Strawberry Cheesecake', 'Creamy cheesecake topped with fresh strawberries', 18.75, true, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
