-- Per generare la password usa questo sito http://bcrypthashgenerator.apphb.com --
CREATE TABLE USERS ( 
   USERNAME VARCHAR(128) NOT NULL,
   PASSWORD VARCHAR(4000) NOT NULL,
   COMPANY  VARCHAR(10) NOT NULL,
   ROLE VARCHAR(32) NOT NULL,
   TOKEN VARCHAR(50),
   LAST_UPDATE DATETIME,
   PRIMARY KEY(USERNAME),
   CONSTRAINT IDX_TOKEN UNIQUE (TOKEN)
);
 INSERT INTO USERS ( 
    USERNAME, 
    PASSWORD,
    COMPANY,
    ROLE 
) 
VALUES (
    'user1',
    '$2a$10$OyaUD/MvWj2TDKM2TzO7QuSJ8OTtsvitsPQvbs4eYVF/Xi2h0.m/2',
    '899',
    'USER'
);
INSERT INTO USERS ( 
    USERNAME, 
    PASSWORD,
    COMPANY,
    ROLE 
) 
VALUES (
    'user2',
    '$2a$10$3sQFGgUGS3Tr6.jwzt563.QLWJN3T.D5kLLHZdhggQVtRMLyomrP2',
    '610',
    'USER'
);
INSERT INTO USERS ( 
    USERNAME, 
    PASSWORD,
    COMPANY,
    ROLE
) 
VALUES (
    'user3',
    '$2a$10$hp8e8GttsMFnOzi.9mFPRe/uB46z8VAEc7tkqrXCxQ3BWv6KcNqW.',
    'COMPANY_3',
    'USER'
);
INSERT INTO USERS ( 
    USERNAME, 
    PASSWORD,
    COMPANY,
    ROLE
) 
VALUES (
    'admin',
    '$2a$06$lBgxA1Yf2lCUP/3skdCp9OY5cfHuAzRj.G0DE0SxdLFdKbw.oBLqK',
    'ANY',
    'ADMIN'
);
