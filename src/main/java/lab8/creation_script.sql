CREATE TABLE IF NOT EXISTS movies (
  id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  title TEXT NOT NULL UNIQUE,
  release_date DATE,
  duration INT,
  score NUMERIC(2,1)
);

CREATE TABLE IF NOT EXISTS genres (
  id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
  name TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS movies_genres_assoc (
  movie_id INT,
  genre_id INT,
  CONSTRAINT fk_movie
    FOREIGN KEY (movie_id)
      REFERENCES movies (id)
      ON DELETE CASCADE,
  CONSTRAINT fk_genre
    FOREIGN KEY (genre_id)
      REFERENCES genres (id)
      ON DELETE CASCADE
);
