-- Seed initial roles; safe to run multiple times
INSERT INTO user_role (role_id, role_name)
VALUES (1, 'USER')
ON DUPLICATE KEY UPDATE role_name = VALUES(role_name);

INSERT INTO user_role (role_id, role_name)
VALUES (2, 'ADMIN')
ON DUPLICATE KEY UPDATE role_name = VALUES(role_name);
