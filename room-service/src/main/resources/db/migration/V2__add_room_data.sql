-- Initial sample rooms (availability b'1' = true)
INSERT INTO room (availability, description, max_occupancy, price_per_night, room_type, surface)
SELECT t.availability, t.description, t.max_occupancy, t.price_per_night, t.room_type, t.surface
FROM (
  SELECT b'1' AS availability, 'A cozy single room with a stunning city view.' AS description, 1 AS max_occupancy, 75 AS price_per_night, 'SINGLE' AS room_type, 20.5 AS surface
  UNION ALL
  SELECT b'1', 'A spacious double room with a king-sized bed.', 2, 120, 'DOUBLE', 30
  UNION ALL
  SELECT b'1', 'A luxurious suite with a separate living area.', 4, 250, 'SUITE', 50
  UNION ALL
  SELECT b'1', 'A cozy single room ideal for solo travelers.', 1, 100, 'SINGLE', 22
  UNION ALL
  SELECT b'1', 'A comfortable double room perfect for couples.', 2, 150, 'DOUBLE', 28
  UNION ALL
  SELECT b'1', 'A luxurious suite with a separate living area and premium amenities.', 4, 300, 'SUITE', 60
) AS t
WHERE NOT EXISTS (
  SELECT 1 FROM room r WHERE r.description = t.description
);
