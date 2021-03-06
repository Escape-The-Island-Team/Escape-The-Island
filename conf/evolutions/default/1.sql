# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table character (
  id                        bigint not null,
  game_id                   bigint,
  name                      varchar(255),
  old                       integer,
  action_points             integer,
  position                  varchar(255),
  message                   integer,
  constraint pk_character primary key (id))
;

create table game (
  id                        bigint not null,
  user_id                   bigint,
  start_time                date,
  completed                 bigint,
  active                    bigint,
  constraint pk_game primary key (id))
;

create table item (
  id                        bigint not null,
  character_id              bigint,
  name                      varchar(255),
  old                       integer,
  used                      integer,
  constraint pk_item primary key (id))
;

create table location (
  id                        bigint not null,
  name                      varchar(255),
  game_id                   bigint,
  visited                   integer,
  constraint pk_location primary key (id))
;

create table npc (
  id                        bigint not null,
  game_id                   bigint,
  name                      varchar(255),
  old                       integer,
  status                    integer,
  constraint pk_npc primary key (id))
;

create table object (
  id                        bigint not null,
  game_id                   bigint,
  name                      varchar(255),
  old                       integer,
  used                      integer,
  usages_left               integer,
  constraint pk_object primary key (id))
;

create table user (
  id                        bigint not null,
  e_mail                    varchar(255),
  nickname                  varchar(255),
  password_salt             varchar(255),
  password_hash             varchar(255),
  time_creation             timestamp,
  time_password             timestamp,
  constraint pk_user primary key (id))
;

create sequence character_seq;

create sequence game_seq;

create sequence item_seq;

create sequence location_seq;

create sequence npc_seq;

create sequence object_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists character;

drop table if exists game;

drop table if exists item;

drop table if exists location;

drop table if exists npc;

drop table if exists object;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists character_seq;

drop sequence if exists game_seq;

drop sequence if exists item_seq;

drop sequence if exists location_seq;

drop sequence if exists npc_seq;

drop sequence if exists object_seq;

drop sequence if exists user_seq;

