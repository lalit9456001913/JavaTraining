create table todos
(
    id         uuid not null
        primary key,
    description       text,
    created_at           timestamp with time zone not null default current_timestamp,
    updated_at           timestamp with time zone not null default current_timestamp
);


