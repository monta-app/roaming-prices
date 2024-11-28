CREATE TABLE evse_tariff_mapping
(
    id           CHAR(36)                       NOT NULL PRIMARY KEY,
    evse_emi3    VARCHAR(255),
    connector_id VARCHAR(255),
    uid          VARCHAR(255),
    party_id     VARCHAR(255)                   NOT NULL,
    country_code VARCHAR(255)                   NOT NULL,
    protocol     ENUM ('OCPI', 'OICP', 'OTHER') NOT NULL,
    pricing_id   CHAR(36)                       NOT NULL,
    CONSTRAINT fk_evse_tariff_mapping_pricing FOREIGN KEY (pricing_id) REFERENCES pricing (id)
);