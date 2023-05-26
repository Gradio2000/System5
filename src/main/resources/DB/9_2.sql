alter table p_priziv
    add month_year_id int;

create table p_priziv_month_year
(
    id              serial
        primary key
        unique,
    month_year_name varchar
);

