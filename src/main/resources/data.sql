-- Вставка команд
INSERT INTO teams (name, balance, commission_rate)
VALUES ('Barcelona', 1000000000.00, 10.00),
       ('Real Madrid', 1000000000.00, 5.00),
       ('Manchester United', 800000000.00, 7.00),
       ('Bayern Munich', 900000000.00, 6.00),
       ('Paris Saint-Germain', 1200000000.00, 8.00),
       ('Juventus', 700000000.00, 5.00),
       ('Liverpool', 850000000.00, 7.00),
       ('Chelsea', 900000000.00, 6.00);

-- Вставка гравців
INSERT INTO players (first_name, last_name, age, months_of_experience, team_id)
VALUES ('Lionel', 'Messi', 35, 240, 1),       -- Barcelona
       ('Karim', 'Benzema', 36, 200, 2),      -- Real Madrid
       ('Cristiano', 'Ronaldo', 38, 300, 3),  -- Manchester United
       ('Robert', 'Lewandowski', 34, 250, 4), -- Bayern Munich
       ('Kylian', 'Mbappe', 24, 96, 5),       -- Paris Saint-Germain
       ('Neymar', 'Junior', 31, 180, 5),      -- Paris Saint-Germain
       ('Paulo', 'Dybala', 29, 150, 6),       -- Juventus
       ('Mohamed', 'Salah', 31, 200, 7),      -- Liverpool
       ('Virgil', 'Van Dijk', 32, 160, 7),    -- Liverpool
       ('Mason', 'Mount', 24, 80, 8),         -- Chelsea
       ('Kai', 'Havertz', 25, 100, 8),        -- Chelsea
       ('Marcus', 'Rashford', 26, 120, 3),    -- Manchester United
       ('Sadio', 'Mane', 31, 190, 4),         -- Bayern Munich
       ('Luka', 'Modric', 37, 260, 2),        -- Real Madrid
       ('Pedri', 'Mount', 20, 60, 1),         -- Barcelona
       ('Frenkie', 'De Jong', 26, 140, 1); -- Barcelona
