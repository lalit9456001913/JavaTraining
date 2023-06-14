create table colleges
(
    id         uuid not null
        primary key,
     name          text,
    description       text,
    address           text,
    created_at           timestamp with time zone not null default current_timestamp,
    updated_at           timestamp with time zone not null default current_timestamp
);


