CREATE TABLE IF NOT EXISTS people (
  id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  name TEXT NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS friendships (
  friend1_id INT NOT NULL,
  friend2_id INT NOT NULL,

  UNIQUE (friend1_id, friend2_id),

  CHECK (friend1_id < friend2_id),

  CONSTRAINT fk_friend1
  FOREIGN KEY (friend1_id)
  REFERENCES people (id)
  ON DELETE CASCADE,

  CONSTRAINT fk_friend2
  FOREIGN KEY (friend2_id)
  REFERENCES people (id)
  ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS messages (
  id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  sender_friend_id INT NOT NULL,
  reciever_friend_id INT NOT NULL,
  content TEXT NOT NULL,

  CONSTRAINT fk_sender_friend
  FOREIGN KEY (sender_friend_id)
  REFERENCES people (id)
  ON DELETE CASCADE,

  CONSTRAINT fk_reciever_friend
  FOREIGN KEY (sender_friend_id)
  REFERENCES people (id)
  ON DELETE CASCADE
);
