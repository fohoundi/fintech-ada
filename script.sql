CREATE TABLE Wallet (
    id INT PRIMARY KEY AUTO_INCREMENT,
    balance DECIMAL(15, 2) NOT NULL DEFAULT 0.00
);

-- Table UserAccount
CREATE TABLE UserAccount (
    id INT PRIMARY KEY AUTO_INCREMENT,
    compteType VARCHAR(20) NOT NULL, -- Ex: 'Admin', 'Merchant', 'Customer'
    login VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL UNIQUE
);

-- Table Admin
CREATE TABLE Admin (
    id INT PRIMARY KEY AUTO_INCREMENT,
     matricule VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    firstName VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(20),
    email VARCHAR(100) UNIQUE,
    idUserAccount INT NOT NULL UNIQUE,
    privilege VARCHAR(50),
    FOREIGN KEY (idUserAccount) REFERENCES UserAccount(id)
);

-- Table Merchant
CREATE TABLE Merchant (
    id INT PRIMARY KEY AUTO_INCREMENT,
    matricule VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    firstName VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(20),
    email VARCHAR(100),
    localisation VARCHAR(255),
    idUserAccount INT NOT NULL UNIQUE,
    idWallet INT NOT NULL UNIQUE,
    FOREIGN KEY (idUserAccount) REFERENCES UserAccount(id),
    FOREIGN KEY (idWallet) REFERENCES Wallet(id)
);

-- Table Customer
CREATE TABLE Customer (
    id INT PRIMARY KEY AUTO_INCREMENT,
     matricule VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL,
    firstName VARCHAR(100) NOT NULL,
    phoneNumber VARCHAR(20),
    email VARCHAR(100),
    gender VARCHAR(20),
    idUserAccount INT NOT NULL UNIQUE,
    idWallet INT NOT NULL UNIQUE,
    FOREIGN KEY (idUserAccount) REFERENCES UserAccount(id),
    FOREIGN KEY (idWallet) REFERENCES Wallet(id)
);