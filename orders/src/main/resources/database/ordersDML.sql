INSERT INTO orders (user_id, book_id, book_title, quantity, status, created_at)
VALUES
    ('user123', 3, 'Emma', 1, 'CREATED', CURRENT_TIMESTAMP - INTERVAL '2 days'),
    ('user123', 5, 'Jane Eyre', 2, 'CREATED', CURRENT_TIMESTAMP - INTERVAL '1 day'),
    ('user999', 10, 'Adios a las armas', 1, 'CREATED', CURRENT_TIMESTAMP - INTERVAL '3 hours');

