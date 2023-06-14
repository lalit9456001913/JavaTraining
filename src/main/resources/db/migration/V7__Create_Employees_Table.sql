create table employees
(
    id         uuid not null
        primary key,
    name       text,
    company_id uuid not null
        references companies,
    created_at           timestamp with time zone not null default current_timestamp,
    updated_at           timestamp with time zone not null default current_timestamp
);


