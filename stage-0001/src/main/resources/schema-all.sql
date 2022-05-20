DROP TABLE persona IF EXISTS;

CREATE TABLE persona (
	id BIGINT IDENTITY NOT NULL PRIMARY KEY,
	primer_nombre VARCHAR(50),
	segundo_nombre VARCHAR(50), 
	telefono VARCHAR(20)
);