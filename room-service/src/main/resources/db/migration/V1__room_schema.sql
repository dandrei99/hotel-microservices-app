-- Creates "room" table, if does not exist
CREATE TABLE IF NOT EXISTS room (
  room_id         BIGINT NOT NULL AUTO_INCREMENT,
  availability    BIT(1) NOT NULL,
  description     VARCHAR(255) DEFAULT NULL,
  max_occupancy   INT NOT NULL,
  price_per_night DOUBLE NOT NULL,
  room_type       ENUM('DOUBLE','SINGLE','SUITE') DEFAULT NULL,
  surface         DOUBLE NOT NULL,
  PRIMARY KEY (room_id)
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
