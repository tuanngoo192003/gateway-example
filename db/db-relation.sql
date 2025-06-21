CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255),
    is_deleted BOOLEAN DEFAULT FALSE,
    created_by VARCHAR(50),
    last_modified_by VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles (
    id SERIAL PRIMARY KEY,
    role_name VARCHAR(50) CHECK (
        role_name IN (
            'ADMIN',
            'MANAGER',
            'CUSTOMER'
        )
    ),
    description TEXT,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_by VARCHAR(50),
    last_modified_by VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE permissions (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    uri VARCHAR(255) NOT NULL,
    api_name VARCHAR(255) NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_by VARCHAR(50),
    last_modified_by VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE role_permission (
    role_id INT NOT NULL,
    permission_id INT NOT NULL,
    is_deleted BOOLEAN DEFAULT FALSE,
    created_by VARCHAR(50),
    last_modified_by VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY role_permission(role_id) REFERENCES roles(id);
    FOREIGN KEY role_permission (permission_id) REFERENCES permissions(id);
);