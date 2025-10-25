-- Creates reservation and reservation_services_mapping tables for MySQL 8.0

-- reservation: stores the booking itself
CREATE TABLE IF NOT EXISTS reservation (
  reservation_id     BIGINT NOT NULL AUTO_INCREMENT,
  user_id            BIGINT NOT NULL,        -- from User-Service
  room_id            BIGINT NOT NULL,        -- from Room-Service
  total_price        DOUBLE NOT NULL,
  check_in           DATE NULL,
  check_out          DATE NULL,
  reservation_status ENUM('CONFIRMED','CANCELED','COMPLETED') NULL,
  created_at         DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),

  PRIMARY KEY (reservation_id),
  KEY idx_reservation_user_id (user_id),
  KEY idx_reservation_room_id (room_id)
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;

-- reservation_services_mapping: many "service ids" attached to a reservation
-- Uses a composite PK to prevent duplicate (reservation_id, service_id) pairs.
CREATE TABLE IF NOT EXISTS reservation_services_mapping (
  reservation_id BIGINT NOT NULL,
  service_id     BIGINT NOT NULL,

  PRIMARY KEY (reservation_id, service_id),
  KEY idx_rsm_reservation_id (reservation_id),

  CONSTRAINT fk_rsm_reservation
    FOREIGN KEY (reservation_id)
    REFERENCES reservation (reservation_id)
    ON DELETE CASCADE
) ENGINE=InnoDB
  DEFAULT CHARSET=utf8mb4
  COLLATE=utf8mb4_0900_ai_ci;
