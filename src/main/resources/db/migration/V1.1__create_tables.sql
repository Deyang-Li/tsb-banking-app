create sequence accountid_sequence start with 1 increment by 50;
create sequence transactionid_sequence start with 1 increment by 50;

    create table account (
       account_id bigint not null,
        account_description varchar(255) not null,
        account_name varchar(255) not null,
        account_number varchar(255) not null,
        amount float(53) not null,
        created_date timestamp(6) with time zone not null,
        status varchar(255) not null,
        a_customer_number varchar(255),
        a_customer_unique_id varchar(255),
        primary key (account_id)
    );

    create table customer (
       customer_number varchar(255) not null,
        customer_unique_id varchar(255) not null,
        created_date timestamp(6) with time zone not null,
        date_of_birth varchar(255) not null,
        first_name varchar(255) not null,
        last_name varchar(255) not null,
        primary key (customer_number, customer_unique_id)
    );

    create table transaction (
       transaction_id bigint not null,
        account_from bigint not null,
        account_to bigint not null,
        amount float(53) not null,
        created_date timestamp(6) with time zone not null,
        description varchar(255) not null,
        transaction_status varchar(255) not null,
        t_account_id bigint,
        primary key (transaction_id)
    );
create index IDX_CUSTOMER_NUMBER on account (a_customer_number);
create index IDX_CUSTOMER_UNIQUE_ID on account (a_customer_unique_id);
create index IDX_ACCOUNT_ID on transaction (t_account_id);

    alter table account 
       add constraint FK6ye17sfvpvrwhnrcqpgl2b87n 
       foreign key (a_customer_number, a_customer_unique_id) 
       references customer;

    alter table transaction 
       add constraint FKr9tf7xv4y94j9jp9g3guu45ji 
       foreign key (t_account_id) 
       references account;
