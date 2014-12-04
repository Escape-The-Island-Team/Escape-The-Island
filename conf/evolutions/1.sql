  # --- !Ups
CREATE TABLE user
  (
    id                bigint not null,
    e_mail            varchar(255) not null unique,
    nickname          varchar(50) not null unique,
    password_salt     varchar(30) not null,
    password_hash     varchar(30) not null,
    time_creation     DATETIME not null,
    time_password     DATETIME not null,
    PRIMARY KEY (id)
  );

CREATE TABLE object
  (
    id                bigint not null unique,
    game_id           bigint not null,
    name              varchar(255) not null,
    old               boolean not null,
    usages_left       int,
    item_id           bigint,
    PRIMARY KEY (id)
  );

CREATE TABLE game
  (
    id                bigint not null unique,
    user_id           bigint not null,
    start_time        DATETIME not null,
    completed         DATETIME,
    PRIMARY KEY (id)
  );

CREATE TABLE char
  (
    id                bigint not null unique,
    game_id           bigint not null unique,
    name              varchar(255) not null,
    old               boolean not null,
    action_points     int not null,
    position          varchar(255) not null,
    PRIMARY KEY (id)
  );

CREATE TABLE item
  (
    id                bigint not null unique,
    char_id           bigint not null,
    name              varchar(255) not null,
    old               boolean not null,
    PRIMARY KEY (id)
  );

CREATE TABLE statistics
  (
    id                bigint not null unique,
    game_id           bigint not null unique,
    usedactionpoints  int not null,
    itemscollected    int not null,
    itemscreated      int not null,
    PRIMARY KEY (id)
  );

CREATE TABLE npc
  (
    id                bigint not null unique,
    game_id           bigint not null,
    name              varchar(255) not null,
    old               boolean not null,
    position          varchar(255) not null,
    PRIMARY KEY (id)
  );

CREATE TABLE quest
  (
    id                bigint not null unique,
    game_id           bigint not null unique,
    completed         boolean not null,
    active            boolean not null,
    PRIMARY KEY (id)
  );
# --- !Downs
DROP TABLE user;

DROP TABLE object;

DROP TABLE game;

DROP TABLE char;