create table if not exists Account
(
    idAccount          int primary key auto_increment,
    idPerson           int           not null,
    balance            decimal(9, 2) not null,
    withdrawalDayLimit decimal(9, 2) not null,
    flagActive         boolean       not null,
    accountType        int           not null,
    createdAt          datetime      not null,
    foreign key (idPerson) references Person (idPerson)
);
