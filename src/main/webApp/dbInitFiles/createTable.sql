
create table d_business
(
    business_id   serial
        primary key,
    business_name varchar
);

create table d_docs
(
    doc_id      integer not null
        constraint q_docs_pkey
            primary key,
    doc_name    text,
    file_name   varchar,
    business_id integer,
    text        text,
    tsv         tsvector,
    reg_number  varchar
);


create index idx_tsv_docs
    on d_docs using gin (tsv);

create trigger tsvectorupdate
    before insert or update
    on d_docs
    for each row
execute procedure ???('tsv', 'pg_catalog.russian', 'doc_name', 'text');
