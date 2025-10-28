-- Initial hotel services data
INSERT INTO hotel_services (description, price, service_name)
SELECT t.description, t.price, t.service_name
FROM (
  SELECT 'Full-body relaxing spa treatment.' AS description, 45 AS price, 'Spa & Wellness' AS service_name
  UNION ALL
  SELECT 'Delicious continental meals including a variety of options.', 15, 'Room Service'
  UNION ALL
  SELECT 'Relaxing full-body massage to rejuvenate guests.', 20, 'Massage'
  UNION ALL
  SELECT 'Unlimited access to our luxurious swimming pool, available from 8 AM to 12 PM.', 50, 'Pool Access'
  UNION ALL
  SELECT 'Relax and unwind at our Sky Lounge Bar, featuring stunning views, handcrafted cocktails, and live music.', 65, 'Sky Lounge Bar'
) AS t
WHERE NOT EXISTS (
  SELECT 1 FROM hotel_services s WHERE s.service_name = t.service_name
);
