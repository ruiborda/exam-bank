drop table if exists movement;
drop table if exists account;
drop table if exists client;
drop table if exists person;


CREATE TABLE person
(
    id             uuid default gen_random_uuid() PRIMARY KEY, -- Unique identifier for the person
    name           VARCHAR(255),                               -- Name of the person
    gender         char(1),                                    -- Gender of the person
    age            smallint,                                   -- Age of the person
    identification VARCHAR(32),                                -- Identification of the person
    address        VARCHAR(255),                               -- Address of the person
    phone          VARCHAR(16)                                 -- Phone number of the person
);


CREATE TABLE client
(
    client_id uuid default gen_random_uuid() PRIMARY KEY, -- Unique identifier for the client
    password  VARCHAR(64),                                -- Client's password
    status    BOOLEAN,                                    -- Client's status
    person_id uuid REFERENCES person (id) on DELETE CASCADE on UPDATE CASCADE,
    UNIQUE (person_id)                                    -- Ensures the associated person is unique
);

CREATE TABLE account
(
    id              uuid default gen_random_uuid() PRIMARY KEY,                            -- Unique identifier for the account
    account_number  varchar(64) UNIQUE,                                                    -- Account number
    account_type    VARCHAR(16),                                                           -- Type of account
    initial_balance NUMERIC(12, 2),                                                        -- Initial balance of the account
    status          BOOLEAN,                                                               -- Account status
    client_id       uuid REFERENCES client (client_id) on DELETE CASCADE on UPDATE CASCADE -- ID of the client associated with the account
);


CREATE TABLE movement
(
    id              uuid default gen_random_uuid() PRIMARY KEY,                      -- Unique identifier for the movement
    date            DATE,                                                            -- fecha
    movement_type   VARCHAR(16),                                                     -- tipo de movimiento
    value           NUMERIC(12, 2),                                                  -- valor del movimiento
    balance         NUMERIC(12, 2),
    initial_balance NUMERIC(12, 2),                                                  -- saldo inicial
    account_id      uuid REFERENCES account (id) on DELETE CASCADE on UPDATE CASCADE -- Account number associated with the movement
);
