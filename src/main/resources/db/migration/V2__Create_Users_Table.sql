CREATE TYPE user_role_type AS ENUM ('SUPERADMIN', 'ADMIN');

create table users
(
    id                   uuid                     not null
        primary key,
    mobile_number        varchar(255)             not null
        constraint users_unique_mobile_number
            unique
        constraint ukr7c96a004bv8w16jgdm8imich
            unique,
    country_code         integer                  not null,
    mobile_verified_at   timestamp with time zone,
    name                 text,
    email_id             varchar(255)
        unique
        constraint ukpwrpg821nujmmnavoq7s420jn
            unique,
    recruiter_manager_id uuid,
    role                 user_role_type           not null,
    created_at           timestamp with time zone not null default current_timestamp,
    updated_at           timestamp with time zone not null default current_timestamp
);


