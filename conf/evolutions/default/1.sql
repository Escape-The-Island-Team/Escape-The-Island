# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table user (
  id                        bigint not null,
  e_mail                    varchar(255),
  nickname                  varchar(255),
  password_salt             varchar(255),
  password_hash             varchar(255),
  time_creation             timestamp,
  time_password             timestamp,
  constraint uq_user_e_mail unique (e_mail),
  constraint uq_user_nickname unique (nickname),
  constraint pk_user primary key (id))
;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists user_seq;

