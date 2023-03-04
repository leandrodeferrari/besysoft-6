USE ejercicio_4;

-- Inserts de GENEROS

INSERT INTO generos(NOMBRE) 
	VALUES ('Terror');
    
INSERT INTO generos(NOMBRE) 
	VALUES ('Suspenso');
    
INSERT INTO generos(NOMBRE) 
	VALUES ('Romance');
    
INSERT INTO generos(NOMBRE) 
	VALUES ('Policial');
    
-- Inserts de PERSONAJES

INSERT INTO personajes(NOMBRE, EDAD, PESO, HISTORIA) 
	VALUES ('Jacqueline', 26, 55.7, 'Es una actriz canadiense. Protagonizó la serie Salvation de CBS.');
    
INSERT INTO personajes(NOMBRE, EDAD, PESO, HISTORIA) 
	VALUES ('Vera', 86, 70.0, 'Supermodelo que enamoró a Coco Chanel y ahora ha conquistado a Paco Plaza.');
    
INSERT INTO personajes(NOMBRE, EDAD, PESO, HISTORIA) 
	VALUES ('Christian', 35, 79.5, 'Es actor, escritor, director, productor y músico. Trabaja en el teatro, peliculas y televisión.');
    
INSERT INTO personajes(NOMBRE, EDAD, PESO, HISTORIA) 
	VALUES ('Joel', 48, 90.2, 'Es actor, director y guionista australiano conocido por haber participado en la serie televisiva Teh secret life of us.');

INSERT INTO personajes(NOMBRE, EDAD, PESO, HISTORIA) 
	VALUES ('Sofia', 29, 69.5, 'Nació en Lauderdale, Florida. Hijan de José F. Daccarett y de Laura Char Canson.');
    
INSERT INTO personajes(NOMBRE, EDAD, PESO, HISTORIA) 
	VALUES ('Jeremy', 52, 80.5, 'Es actor, actor de voz, productor y músico estadounidense.');

-- Inserts de PELICULAS_SERIES

INSERT INTO peliculas_series(TITULO, FECHA_DE_CREACION, CALIFICACION, GENERO_ID) 
	VALUES ('Chucky', '2004-10-10', 5, 1);
    
INSERT INTO peliculas_series(TITULO, FECHA_DE_CREACION, CALIFICACION, GENERO_ID) 
	VALUES ('Annabelle', '2006-01-10', 2, 1);
    
INSERT INTO peliculas_series(TITULO, FECHA_DE_CREACION, CALIFICACION, GENERO_ID) 
	VALUES ('Jaula', '2021-02-12', 1, 2);
    
INSERT INTO peliculas_series(TITULO, FECHA_DE_CREACION, CALIFICACION, GENERO_ID) 
	VALUES ('Culpable', '2020-07-11', 4, 4);
    
INSERT INTO peliculas_series(TITULO, FECHA_DE_CREACION, CALIFICACION, GENERO_ID) 
	VALUES ('Viejos', '2002-09-05', 4, 2);
    
INSERT INTO peliculas_series(TITULO, FECHA_DE_CREACION, CALIFICACION, GENERO_ID) 
	VALUES ('CODA', '2019-01-03', 3, 3);

-- Inserts de PERSONAJES_PELICULAS_SERIES

INSERT INTO personajes_peliculas_series(PERSONAJE_ID, PELICULA_SERIE_ID) 
	VALUES (1, 1);
    
INSERT INTO personajes_peliculas_series(PERSONAJE_ID, PELICULA_SERIE_ID) 
	VALUES (2, 2);
    
INSERT INTO personajes_peliculas_series(PERSONAJE_ID, PELICULA_SERIE_ID) 
	VALUES (3, 3);
    
INSERT INTO personajes_peliculas_series(PERSONAJE_ID, PELICULA_SERIE_ID) 
	VALUES (4, 5);
    
INSERT INTO personajes_peliculas_series(PERSONAJE_ID, PELICULA_SERIE_ID) 
	VALUES (5, 4);
    
INSERT INTO personajes_peliculas_series(PERSONAJE_ID, PELICULA_SERIE_ID) 
	VALUES (6, 6);
