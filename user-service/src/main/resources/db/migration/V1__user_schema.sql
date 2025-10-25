-- === user_role ===
CREATE TABLE IF NOT EXISTS user_role (
  role_id   BIGINT NOT NULL AUTO_INCREMENT,
  role_name VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (role_id)
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- === hotel_user ===
CREATE TABLE IF NOT EXISTS hotel_user (
  user_id      BIGINT NOT NULL AUTO_INCREMENT,
  created_at   DATETIME(6) DEFAULT NULL,
  email        VARCHAR(255) NOT NULL,
  password     VARCHAR(255) NOT NULL,
  phone_number VARCHAR(255) DEFAULT NULL,
  role_id      BIGINT DEFAULT NULL,
  PRIMARY KEY (user_id),
  UNIQUE KEY uk_hotel_user_email (email),
  KEY idx_hotel_user_role_id (role_id),
  CONSTRAINT fk_hotel_user_role
    FOREIGN KEY (role_id) REFERENCES user_role(role_id)
) ENGINE=InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
