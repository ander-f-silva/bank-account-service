create table if not exists Transaction
(
    idTransaction int primary key auto_increment,
    idAccount     int           not null,
    amount        decimal(9, 2) not null,
    createdAt     datetime,
    foreign key (idAccount) references Account (idAccount)
);