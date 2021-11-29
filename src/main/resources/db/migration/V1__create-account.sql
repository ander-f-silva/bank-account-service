create table Account
(
    idAccount  int primary key,
    idPerson   int           not null,
    balance    decimal(9, 2) not null,
    dayLimit   decimal(9, 2) not null,
    flagActive boolean       not null,
    createdAt  datetime      not null
) engine = innodb
  default charset = utf8;

alter table Transaction
    add constraint fk_account_people
        foreign key (idPerson) references Person (idPersonidPeple);