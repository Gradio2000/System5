create table d_docs
(
    doc_id    serial
        primary key
        unique,
    doc_name  varchar,
    file_name varchar,
    title     varchar
);

