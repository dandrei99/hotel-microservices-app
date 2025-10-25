-- Initial sample rooms (availability b'1' = true)
INSERT INTO room (availability, description, max_occupancy, price_per_night, room_type, surface) VALUES
  (b'1', 'A cozy single room with a stunning city view.',                                 1,  75, 'SINGLE', 20.5),
  (b'1', 'A spacious double room with a king-sized bed.',                                 2, 120, 'DOUBLE', 30),
  (b'1', 'A luxurious suite with a separate living area.',                                4, 250, 'SUITE',  50),
  (b'1', 'A cozy single room ideal for solo travelers.',                                  1, 100, 'SINGLE', 22),
  (b'1', 'A comfortable double room perfect for couples.',                                2, 150, 'DOUBLE', 28),
  (b'1', 'A luxurious suite with a separate living area and premium amenities.',          4, 300, 'SUITE',  60);
