create table wallets (
        version integer,
        uuid uuid not null,
        count bigint not null,
           primary key (uuid)
    );

create table transaction (
        id bigint generated by default as identity,
        created timestamp(6),
        operation_type varchar(255) not null check (operation_type in ('DEPOSIT','WITHDRAW')),
        wallet_uuid uuid,
        amount bigint not null,
        primary key (id)
    );

    insert into wallets ("count","uuid","version")
	values (0,'33fa31a5-87d6-4906-b252-4e8e43df0064',1);


