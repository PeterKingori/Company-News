CREATE DATABASE company_news;
\c company_news;

CREATE TABLE users (
id SERIAL PRIMARY KEY,
name VARCHAR,
position VARCHAR,
role VARCHAR,
department VARCHAR,
departmentId int
);

CREATE TABLE news (
id SERIAL PRIMARY KEY,
title VARCHAR,
content VARCHAR,
type VARCHAR,
author VARCHAR,
departmentId int
);

CREATE TABLE departments (
id SERIAL PRIMARY KEY,
name VARCHAR,
description VARCHAR,
totalemployees INTEGER
);

CREATE DATABASE company_news_test WITH TEMPLATE company_news;