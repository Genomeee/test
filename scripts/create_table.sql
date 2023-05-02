CREATE TABLE IF NOT EXISTS contact (
    matrix_id VARCHAR(200) NOT NULL,
    name VARCHAR(200) NOT NULL,
    tenant_id VARCHAR(200) NOT NULL,
    PRIMARY KEY (matrix_id)
)