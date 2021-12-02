create table if not exists Person
(
    idPerson int primary key auto_increment,
    name     varchar(150) not null,
    document varchar(80)  not null,
    birthday datetime,
    constraint constraint_document unique key (document)
);