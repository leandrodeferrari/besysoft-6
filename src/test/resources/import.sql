INSERT INTO generos(nombre) VALUES ('Terror');
INSERT INTO generos(nombre) VALUES ('Suspenso');
INSERT INTO generos(nombre) VALUES ('Romance');
INSERT INTO generos(nombre) VALUES ('Policial');
INSERT INTO personajes(nombre, edad, peso, historia) VALUES ('Jacqueline', 26, 55.7, 'Es una actriz canadiense. Protagonizó la serie Salvation de CBS.');
INSERT INTO personajes(nombre, edad, peso, historia) VALUES ('Vera', 86, 70.0, 'Supermodelo que enamoró a Coco Chanel y ahora ha conquistado a Paco Plaza.');
INSERT INTO personajes(nombre, edad, peso, historia) VALUES ('Christian', 35, 79.5, 'Es actor, escritor, director, productor y músico. Trabaja en el teatro, peliculas y televisión.');
INSERT INTO personajes(nombre, edad, peso, historia) VALUES ('Joel', 48, 90.2, 'Es actor, director y guionista australiano conocido por haber participado en la serie televisiva The secret life of us.');
INSERT INTO peliculas_series(titulo, fecha_de_creacion, calificacion, genero_id) VALUES ('Chucky', '2004-10-10', 5, 1);
INSERT INTO peliculas_series(titulo, fecha_de_creacion, calificacion, genero_id) VALUES ('Annabelle', '2006-01-10', 2, 1);
INSERT INTO peliculas_series(titulo, fecha_de_creacion, calificacion, genero_id) VALUES ('Jaula', '2021-02-12', 1, 2);
INSERT INTO peliculas_series(titulo, fecha_de_creacion, calificacion, genero_id) VALUES ('Culpable', '2020-07-11', 4, 4);