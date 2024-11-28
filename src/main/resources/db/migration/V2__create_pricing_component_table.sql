DROP TABLE IF EXISTS pricing_component;
CREATE TABLE pricing_component
(
    id         CHAR(36)    NOT NULL PRIMARY KEY,
    type       VARCHAR(10) NOT NULL CHECK (type IN ('KWH', 'MIN')),
    amount     FLOAT       NOT NULL,
    pricing_id CHAR(36)    NOT NULL,
    CONSTRAINT fk_pricing FOREIGN KEY (pricing_id) REFERENCES pricing (id) ON DELETE CASCADE
);