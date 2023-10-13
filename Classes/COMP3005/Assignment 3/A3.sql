CREATE TABLE BANK (
    B VARCHAR(255) PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    CITY VARCHAR(255) NOT NULL
);

CREATE TABLE CUSTOMER (
    C VARCHAR(255) PRIMARY KEY,
    NAME VARCHAR(255) NOT NULL,
    AGE INTEGER NOT NULL,
    CITY VARCHAR(255) NOT NULL CHECK (AGE >= 16)
);

CREATE TABLE ACCOUNT (
    C VARCHAR(255) NOT NULL,
    B VARCHAR(255) NOT NULL,
    BALANCE INTEGER NOT NULL,
    PRIMARY KEY (C, B),
    FOREIGN KEY (C) REFERENCES CUSTOMER (C),
    FOREIGN KEY (B) REFERENCES BANK (B)
);

-- dml to insert
INSERT INTO BANK (
    B,
    NAME,
    CITY
) VALUES (
    'B1',
    'England',
    'London'
);

INSERT INTO BANK (
    B,
    NAME,
    CITY
) VALUES (
    'B2',
    'America',
    'New York'
);

INSERT INTO BANK (
    B,
    NAME,
    CITY
) VALUES (
    'B3',
    'Royal',
    'Toronto'
);

INSERT INTO BANK (
    B,
    NAME,
    CITY
) VALUES (
    'B4',
    'France',
    'Paris'
);

INSERT INTO CUSTOMER(
    C,
    NAME,
    AGE,
    CITY
) VALUES (
    'C1',
    'Adams',
    '20',
    'London'
);

INSERT INTO CUSTOMER(
    C,
    NAME,
    AGE,
    CITY
) VALUES (
    'C2',
    'Blake',
    '30',
    'Paris'
);

INSERT INTO CUSTOMER(
    C,
    NAME,
    AGE,
    CITY
) VALUES (
    'C3',
    'Clark',
    '25',
    'Paris'
);

INSERT INTO CUSTOMER(
    C,
    NAME,
    AGE,
    CITY
) VALUES (
    'C4',
    'El Assad',
    '22',
    'Ottawa'
);

INSERT INTO CUSTOMER(
    C,
    NAME,
    AGE,
    CITY
) VALUES (
    'C5',
    'Smith',
    '30',
    'Toronto'
);

INSERT INTO ACCOUNT(
    C,
    B,
    BALANCE
) VALUES (
    'C1',
    'B1',
    '1000'
);

INSERT INTO ACCOUNT(
    C,
    B,
    BALANCE
) VALUES (
    'C1',
    'B2',
    '2000'
);

INSERT INTO ACCOUNT(
    C,
    B,
    BALANCE
) VALUES (
    'C1',
    'B3',
    '3000'
);

INSERT INTO ACCOUNT(
    C,
    B,
    BALANCE
) VALUES (
    'C1',
    'B4',
    '4000'
);

INSERT INTO ACCOUNT(
    C,
    B,
    BALANCE
) VALUES (
    'C2',
    'B1',
    '2000'
);

INSERT INTO ACCOUNT(
    C,
    B,
    BALANCE
) VALUES (
    'C2',
    'B2',
    '3000'
);

INSERT INTO ACCOUNT(
    C,
    B,
    BALANCE
) VALUES (
    'C2',
    'B3',
    '4000'
);

INSERT INTO ACCOUNT(
    C,
    B,
    BALANCE
) VALUES (
    'C3',
    'B1',
    '3000'
);

INSERT INTO ACCOUNT(
    C,
    B,
    BALANCE
) VALUES (
    'C3',
    'B2',
    '4000'
);

INSERT INTO ACCOUNT(
    C,
    B,
    BALANCE
) VALUES (
    'C4',
    'B1',
    '4000'
);

INSERT INTO ACCOUNT(
    C,
    B,
    BALANCE
) VALUES (
    'C4',
    'B2',
    '5000'
);

-- Testing output
SELECT
    *
FROM
    BANK
    SELECT
        *
    FROM
        ACCOUNT
        SELECT
            *
        FROM
            CUSTOMER