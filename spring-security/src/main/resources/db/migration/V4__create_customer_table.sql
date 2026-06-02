CREATE TABLE customer (
    id SERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    pwd VARCHAR(500) NOT NULL,
    role VARCHAR(50) NOT NULL
);

INSERT INTO customer (email, pwd, role)
VALUES ('customer@example.com', '{noop}customerPass123', 'read')
ON CONFLICT (email) DO NOTHING;

INSERT INTO customer (email, pwd, role)
VALUES ('admin@example.com', '{noop}adminPass123', 'admin')
ON CONFLICT (email) DO NOTHING;
