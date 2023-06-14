insert into users(id, mobile_number, country_code, mobile_verified_at, created_at, updated_at, name,
                  email_id, role)
values (gen_random_uuid(), '9929999299', '91', now(), now(), now(), 'test', 'admin@test.com', 'ADMIN');