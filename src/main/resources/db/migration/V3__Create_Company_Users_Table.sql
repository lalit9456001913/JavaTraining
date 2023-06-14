-- auto-generated definition
create table company_users
(
    id         SERIAL PRIMARY KEY,
    company_id uuid                     not null
        references companies,
    user_id    uuid                     not null
        references users,
    created_at timestamp with time zone not null default current_timestamp,
    updated_at timestamp with time zone not null default current_timestamp
);