
create table d_business
(
    business_id   serial
        primary key,
    business_name varchar
);

create table d_docs
(
    doc_id      integer default nextval('q_docs_doc_id_seq'::regclass) not null
        constraint q_docs_pkey
            primary key,
    doc_name    varchar,
    file_name   varchar,
    title       varchar,
    business_id integer
);
