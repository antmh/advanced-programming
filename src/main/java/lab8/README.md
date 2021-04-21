A fost adăugată partea obligatorie și opțională. Datele sunt extrase de pe [Wikidata](https://www.wikidata.org) folosind SPARQL.

Exemplu de utilizare:
```
Movie name: metropolis
0) 1927 German science fiction directed film by Fritz Lang
1) 1984 version by Giorgio Moroder
0
```

După alegerea filmului, datele acestuia sunt inserate:

```sql
SELECT movies.title, movies.release_date, movies.duration, genres.name
  FROM movies
  JOIN movies_genres_assoc ON movies.id = movies_genres_assoc.movie_id
  JOIN genres ON movies_genres_assoc.genre_id = genres.id;
```

```
   title    | release_date | duration |         name         
------------+--------------+----------+----------------------
 Metropolis | 1927-01-01   |      153 | silent film
 Metropolis | 1927-01-01   |      153 | drama
 Metropolis | 1927-01-01   |      153 | fantasy film
 Metropolis | 1927-01-01   |      153 | science fiction film
 Metropolis | 1927-01-01   |      153 | dystopian film
(5 rows)
```

```sql
SELECT people.name, people.date_of_birth, movies.title
  FROM people
  JOIN actors ON people.id = actors.person_id
  JOIN movies ON actors.movie_id = movies.id;
```

```
        name        | date_of_birth |   title    
--------------------+---------------+------------
 Heinrich George    | 1893-10-09    | Metropolis
 Grete Berger       | 1883-01-01    | Metropolis
 Rudolf Klein-Rogge | 1885-11-24    | Metropolis
 Margarete Lanner   | 1896-02-17    | Metropolis
 Helene Weigel      | 1900-05-12    | Metropolis
 Erwin Biswanger    | 1896-11-26    | Metropolis
 Rolf von Goth      | 1906-11-05    | Metropolis
 Fritz Rasp         | 1891-05-13    | Metropolis
 Ilse Stanley       | 1906-03-11    | Metropolis
 Brigitte Helm      | 1906-03-17    | Metropolis
 Fritz Alberti      | 1877-10-22    | Metropolis
 Theodor Loos       | 1883-05-18    | Metropolis
 Alfred Abel        | 1879-03-12    | Metropolis
 Heinrich Gotho     | 1891-07-26    | Metropolis
 Georg John         | 1879-07-23    | Metropolis
 Curt Siodmak       | 1902-08-10    | Metropolis
 Gustav Fröhlich    | 1902-03-21    | Metropolis
 Heinrich Gotho     | 1872-05-03    | Metropolis
(18 rows)
```


```sql
SELECT people.name, people.date_of_birth, movies.title
  FROM people
  JOIN directors ON people.id = directors.person_id
  JOIN movies ON directors.movie_id = movies.id;
```

```
    name    | date_of_birth |   title    
------------+---------------+------------
 Fritz Lang | 1890-12-05    | Metropolis
(1 row)
```
