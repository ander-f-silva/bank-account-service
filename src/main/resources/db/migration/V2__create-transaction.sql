create table Transaction
(
    idTransaction int primary key,
    idAccount     int           not null,
    amount        decimal(9, 2) not null,
    createdAt     datetime
) engine = innodb
  default charset = utf8;

alter table Transaction
    add constraint fk_transaction_account
        foreign key (idAccount) references Account (idAccount);

create index index_transaction_createAt on transaction (createdAt);
