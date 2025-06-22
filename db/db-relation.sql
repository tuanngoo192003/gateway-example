CREATE TABLE accounts (
    id VARCHAR(50) PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255),
    phone_number VARCHAR(255),
    o_auth_type VARCHAR(255),
    role_id INT NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_by VARCHAR(50),
    last_modified_by VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY accounts(role_id) REFERENCES roles(id)
);
INSERT INTO roles(id, role_name, description) 
    VALUES('3f8c76b4-bdee-4195-88a1-4a04a6632063', 'ADMIN', 'admin of the system'),
        ('e8a2fc9c-78ce-46d0-8a6a-93e41381d6ef', 'MANAGER', 'manager of the system'),
        ('7431fd0e-2c14-4964-83a3-05c1198cb18f', 'CUSTOMER', 'customer')

CREATE TABLE roles (
    id VARCHAR(50) PRIMARY KEY,
    role_name VARCHAR(50) CHECK (
        role_name IN (
            'ADMIN',
            'MANAGER',
            'CUSTOMER'
        )
    ),
    description TEXT,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE permissions (
    id VARCHAR(50) PRIMARY KEY,
    permission_name VARCHAR(255),
    uri VARCHAR(255) NOT NULL,
    method VARCHAR(255) NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE role_permission (
    role_id VARCHAR(50) NOT NULL,
    permission_id VARCHAR(50) NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY role_permission(role_id) REFERENCES roles(id);
    FOREIGN KEY role_permission (permission_id) REFERENCES permissions(id);
);

CREATE TABLE cake (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    is_available BOOLEAN DEFAULT true,
    is_deleted BOOLEAN DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
);