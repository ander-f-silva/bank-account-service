create table Person
(
    idPerson   int primary key,
    name   varchar(150)   not null,
    document  varchar(80)   not null,
    birthday datetime
) ;

alter table Person
    add constraint constraint_document unique key (document);