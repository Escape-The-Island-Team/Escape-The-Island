# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table character (
  id                        bigint not null,
  game_id                   bigint,
  name                      varchar(255),
  old                       boolean,
  action_points             integer,
  position                  varchar(255),
  constraint uq_character_game_id unique (game_id),
  constraint pk_character primary key (id))
;

create table dialogue (
  id                        bigint not null,
  npc_id                    bigint,
  name                      varchar(255),
  active                    boolean,
  content                   varchar(255),
  constraint pk_dialogue primary key (id))
;

create table game (
  id                        bigint not null,
  user_id                   bigint,
  start_time                date,
  completed                 boolean,
  constraint pk_game primary key (id))
;

create table item (
  id                        bigint not null,
  character_id              bigint,
  name                      varchar(255),
  old                       boolean,
  constraint pk_item primary key (id))
;

create table npc (
  id                        bigint not null,
  game_id                   bigint,
  name                      varchar(255),
  old                       boolean,
  position                  varchar(255),
  constraint pk_npc primary key (id))
;

create table object (
  id                        bigint not null,
  game_id                   bigint,
  name                      varchar(255),
  description               varchar(255),
  old                       boolean,
  used                      boolean,
  usages_left               integer,
  item_required             boolean,
  constraint pk_object primary key (id))
;

create table quest (
  id                        bigint not null,
  npc_id                    bigint,
  completed                 boolean,
  active                    boolean,
  constraint pk_quest primary key (id))
;

create table statistics (
  id                        bigint not null,
  game_id                   bigint,
  used_ap                   integer,
  collected                 integer,
  created                   integer,
  constraint uq_statistics_game_id unique (game_id),
  constraint pk_statistics primary key (id))
;

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

create sequence character_seq;

create sequence dialogue_seq;

create sequence game_seq;

create sequence item_seq;

create sequence npc_seq;

create sequence object_seq;

create sequence quest_seq;

create sequence statistics_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists character;

drop table if exists dialogue;

drop table if exists game;

drop table if exists item;

drop table if exists npc;

drop table if exists object;

drop table if exists quest;

drop table if exists statistics;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists character_seq;

drop sequence if exists dialogue_seq;

drop sequence if exists game_seq;

drop sequence if exists item_seq;

drop sequence if exists npc_seq;

drop sequence if exists object_seq;

drop sequence if exists quest_seq;

drop sequence if exists statistics_seq;

drop sequence if exists user_seq;

