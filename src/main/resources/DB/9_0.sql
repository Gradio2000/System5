-- создать новую группу тестов ПДД
-- проаерить ее id, он д.б. = 5
-- если нет, то подправить руками
-- если это невозможно, то в контроллере /conv поменять свойство setGroupId
-- далее запускаем скрипты создания таблиц
-- далее запускаем скрипты вопросов и ответов
-- после загрузки вопросов и ответов запустить http://localhost:8080/conv
-- запустить скрипт http://localhost:8080/conv1


alter table q_questions
ADD promt varchar;

alter table q_questions
ADD image_ref varchar;

alter table q_questions
    ADD many_choose boolean;



create table pdd_question
(
    id                serial
        primary key,
    pdd_question_name varchar,
    image_ref         varchar,
    promt             varchar,
    bilet_number      integer
);


create table pdd_answer
(
    id              serial
        primary key,
    pdd_answer_name varchar,
    pdd_ques_id     integer,
    istrue          boolean
);

