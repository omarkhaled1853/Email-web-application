CREATE DATABASE LumiMail;

-- User Table
CREATE TABLE User (
	id VARCHAR(255) PRIMARY KEY,
    email VARCHAR(255),
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    UNIQUE (email, username)
);

-- Mail Table
CREATE TABLE Mail (
    id VARCHAR(255) PRIMARY KEY,
    sender_id VARCHAR(255) NOT NULL,
    receiver_id VARCHAR(255) NOT NULL,
    subject VARCHAR(255),
    content TEXT,
    priority INT,
    create_on DATETIME,
    FOREIGN KEY (sender_id) REFERENCES User(id),
    FOREIGN KEY (receiver_id) REFERENCES User(id)
);

-- Attachment Table
CREATE TABLE Attachment (
    id INT AUTO_INCREMENT PRIMARY KEY,
    mail_id VARCHAR(255) NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    file_path VARCHAR(255) NOT NULL,
    FOREIGN KEY (mail_id) REFERENCES Mail(id)
);

-- Inbox Table (one-to-many relationship with Mail)
CREATE TABLE Inbox (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    mail_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (mail_id) REFERENCES Mail(id)
);

-- Sent Table (one-to-many relationship with Mail)
CREATE TABLE Sent (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    mail_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (mail_id) REFERENCES Mail(id)
);

-- Trash Table (one-to-many relationship with Mail)
CREATE TABLE Trash (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    mail_id VARCHAR(255) NOT NULL,
	trashed_on DATETIME,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (mail_id) REFERENCES Mail(id)
);


-- MailFolder Table
CREATE TABLE MailFolder (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    mail_id VARCHAR(255) NOT NULL,
    folder_name VARCHAR(50) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (mail_id) REFERENCES Mail(id)
);

-- Contacts Table (one-to-many relationship with Mail)
CREATE TABLE Contact (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    contact_id VARCHAR(255) NOT NULL, 
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (contact_id) REFERENCES User(id)
);

