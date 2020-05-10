SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS users (
id int PRIMARY KEY auto_increment,
name VARCHAR,
position VARCHAR,
role VARCHAR,
department VARCHAR
);

CREATE TABLE IF NOT EXISTS news (
id int PRIMARY KEY auto_increment,
title VARCHAR,
content VARCHAR,
type VARCHAR,
author VARCHAR
);

CREATE TABLE IF NOT EXISTS departments (
id int PRIMARY KEY auto_increment,
name VARCHAR,
description VARCHAR,
totalemployees INTEGER
);