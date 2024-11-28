-- V1__create_pricing_table.sql

CREATE TABLE pricing
(
    id           CHAR(36)                       NOT NULL PRIMARY KEY,
    tariff_id    VARCHAR(255)                   NOT NULL,
    party_id     VARCHAR(255)                   NOT NULL UNIQUE,
    country_code VARCHAR(255)                   NOT NULL UNIQUE,
    vat          FLOAT                          NOT NULL,
    protocol     ENUM ('OCPI', 'OICP', 'OTHER') NOT NULL,
    raw_payload  TEXT                           NOT NULL
);