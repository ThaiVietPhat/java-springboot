INSERT INTO users (username, password, enabled)
VALUES ('user', '{noop}phat@123', TRUE)
ON CONFLICT (username) DO NOTHING;

INSERT INTO authorities (username, authority)
VALUES ('user', 'read')
ON CONFLICT (username, authority) DO NOTHING;

INSERT INTO users (username, password, enabled)
VALUES ('admin', '{bcrypt}$2a$12$mUaZIoO7gtL17.UaGoHBnObdnpJ8eddVexDzwMMjS9rcYpJ2xMS7m', TRUE)
ON CONFLICT (username) DO NOTHING;

INSERT INTO authorities (username, authority)
VALUES ('admin', 'admin')
ON CONFLICT (username, authority) DO NOTHING;
