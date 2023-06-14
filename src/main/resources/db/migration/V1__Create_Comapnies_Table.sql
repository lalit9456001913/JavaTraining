create table companies
(
    id                        uuid                     not null
        primary key,
    company_name              text,
    brand_name                text,
    address                   text,
    country                   text,
    state                     text,
    logo                      text,
    default_currency          text,
    financial_year_start_date timestamp with time zone,
    financial_year_end_date   timestamp with time zone,
    pan                       text,
    tan                       text,
    created_at timestamp with time zone not null default current_timestamp,
    updated_at timestamp with time zone not null default current_timestamp,
    custom_attribute_config   jsonb,
    send_grid_api_key         text,
    send_grid_email           text
);
