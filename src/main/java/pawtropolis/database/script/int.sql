CREATE TABLE bag
(
    id             BIGSERIAL PRIMARY KEY,
    occupied_slots INT
);

CREATE TABLE room
(
    id   BIGSERIAL PRIMARY KEY,

    name TEXT NOT NULL
);


CREATE TABLE direction
(
    id   BIGSERIAL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL
);
INSERT INTO direction (name)
VALUES ('North'),
       ('South'),
       ('West'),
       ('East');


CREATE TABLE adjacent_room
(
    id               BIGSERIAL PRIMARY KEY,
    direction_id     BIGINT REFERENCES direction (id),
    room_id          BIGINT REFERENCES room (id),
    adjacent_room_id BIGINT REFERENCES room (id)
);
INSERT INTO adjacent_room (direction_id, room_id, adjacent_room_id)
VALUES
    (3, 1, 2),  -- Monstadt (room_id: 1) is west (direction_id: 3) of Liyue (adjacent_room_id: 2)
    (2, 2, 3),  -- Liyue (room_id: 2) is south (direction_id: 2) of Inazuma (adjacent_room_id: 3)
    (3, 2, 4),  -- Liyue (room_id: 2) is west (direction_id: 3) of Sumeru (adjacent_room_id: 4)
    (1, 4, 5);  -- Sumeru (room_id: 4) is north (direction_id: 1) of Fontaine (adjacent_room_id: 5)



CREATE TABLE item
(
    id             BIGSERIAL PRIMARY KEY,
    name           TEXT NOT NULL,
    description    TEXT,
    required_slots INT  NOT NULL,
    room_id        BIGINT REFERENCES room (id),
    bag_id         BIGINT REFERENCES bag (id)
);


CREATE TABLE player
(
    id          BIGSERIAL PRIMARY KEY,
    name        TEXT NOT NULL,
    life_points INT  NOT NULL,
    bag_id      BIGINT REFERENCES bag (id)
);

CREATE TABLE species
(
    id   BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL
);
INSERT INTO species (name)
VALUES ('Lion'),
       ('Tiger'),
       ('Eagle');

CREATE TABLE animal
(
    id             BIGSERIAL PRIMARY KEY,
    name           TEXT NOT NULL,
    age            INT,
    favourite_food TEXT NOT NULL,
    arrival_date   DATE,
    weight         DOUBLE PRECISION,
    height         DOUBLE PRECISION,
    room_id        BIGINT REFERENCES room (id),
    species_id     BIGINT REFERENCES species (id),
    wingspan       DOUBLE PRECISION,
    tail_length    DOUBLE PRECISION
);

CREATE TABLE game
(
    id        BIGSERIAL PRIMARY KEY,
    player_id BIGINT,
    room_id   BIGINT,
    FOREIGN KEY (player_id) REFERENCES player (id),
    FOREIGN KEY (room_id) REFERENCES room (id)
);