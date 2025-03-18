USE mydatabase;

CREATE TABLE my_entities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    -- non-instant date-time types
    my_local_time TIME,
    my_local_date DATE,
    my_local_date_time DATETIME,
    my_utc_offset_time TIME,
    my_jst_offset_time TIME,

    -- instant date-time types
    my_calendar DATETIME,
    my_date DATETIME,
    my_instant DATETIME,
    my_utc_offset_date_time DATETIME,
    my_jst_offset_date_time DATETIME,
    my_default_offset_date_time DATETIME,
    my_utc_zoned_date_time DATETIME,
    my_jst_zoned_date_time DATETIME,
    my_default_zoned_date_time DATETIME,

    -- timestamp
    my_instant_stamp TIMESTAMP,
    my_utc_offset_date_time_stamp TIMESTAMP,
    my_jst_offset_date_time_stamp TIMESTAMP
);
