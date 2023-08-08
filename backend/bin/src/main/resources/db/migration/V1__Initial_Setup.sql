CREATE SEQUENCE repair_id_seq;

CREATE TABLE repair(
  id INT DEFAULT nextval('repair_id_seq') PRIMARY KEY,
  name TEXT NOT NULL,
  title TEXT NOT NULL,
  brand TEXT NOT NULL,
  issue TEXT NOT NULL,
  phone_number TEXT NOT NULL
);