DROP TABLE IF EXISTS my_datetime;
CREATE TABLE my_datetime (
    id integer primary key,
    col_timestamp TIMESTAMP NULL,
    col_timestamptz TIMESTAMP with time zone NULL
);
