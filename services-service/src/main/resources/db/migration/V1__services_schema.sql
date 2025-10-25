CREATE TABLE IF NOT EXISTS hotel_services (
  service_id   BIGINT NOT NULL AUTO_INCREMENT,
  description  VARCHAR(255) NOT NULL,
  price        DOUBLE NOT NULL,
  service_name VARCHAR(255) NOT NULL,
  PRIMARY KEY (service_id),
  UNIQUE KEY uk_hotel_services_service_name (service_name)
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
