
CREATE TABLE ACCOUNT_DETAILS (   
BSB INT NOT NULL,
IDENTIFICATION INT NOT NULL,
OPENING_DATE DATE default CURRENT_TIMESTAMP NOT NULL,
BALANCE NUMERIC
);  

ALTER TABLE ACCOUNT_DETAILS ADD CONSTRAINT ACCOUNT_DETAILS_UNQ_KEY PRIMARY KEY(BSB, IDENTIFICATION);


CREATE TABLE INTEREST_DETAILS (   
IDENTIFICATION INT NOT NULL,
BALANCE_DATE DATE  NOT NULL,
BALANCE NUMERIC NOT NULL,
INTEREST_AMOUNT NUMERIC NOT NULL
); 

ALTER TABLE INTEREST_DETAILS ADD CONSTRAINT INTEREST_DETAILS_UNQ_KEY PRIMARY KEY(IDENTIFICATION, BALANCE_DATE);