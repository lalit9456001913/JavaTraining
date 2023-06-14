CREATE TYPE token_type AS ENUM ('BEARER', 'OTHER');

CREATE TABLE tokens
(
    id         SERIAL PRIMARY KEY,
    token      VARCHAR(255) UNIQUE NOT NULL,
    token_type token_type          NOT NULL,
    revoked    BOOLEAN             NOT NULL,
    expired    BOOLEAN             NOT NULL,
    user_id    UUID REFERENCES users (id),
    created_at           timestamp with time zone not null default current_timestamp,
    updated_at           timestamp with time zone not null default current_timestamp
);