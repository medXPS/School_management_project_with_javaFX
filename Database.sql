create database gestion_ecole; 
use gestion_ecole; 
create table admins( nom varchar(255) , prenom varchar(255),nomUtilisateur  varchar(255) primary key ,
tel varchar(255), motDePass varchar(255) ); 
create table admin_reset ( codeConfidentiel varchar(255) primary key , tel varchar(255) , is_phone_number_used TINYINT) ; 
insert into admin_reset values ( 'kubectl1', '0616671210' , 0) ; 
insert into admin_reset values ( 'kubectl2', '0616671211' , 0) ; 
insert into admin_reset values ( 'kubectl3', '0616671212' , 0) ; 
insert into admin_reset values ( 'kubectl4', '0616671213' , 0) ; 
insert into admin_reset values ( 'kubectl5', '0616671214' , 0) ; 
insert into admin_reset values ( 'kubectl6', '0616671215' , 0) ;
CREATE TABLE recrutement (
    id INT(15) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    cin VARCHAR(30),
    email VARCHAR(30),
    phone VARCHAR(30),
    gender BOOLEAN,
    apply_for VARCHAR(30)
);


CREATE TABLE formation (
    id INT(15) PRIMARY KEY AUTO_INCREMENT,
    object VARCHAR(30),
    date varchar(30),
    formateur VARCHAR(30),
    description VARCHAR(255)

);
CREATE TABLE conge(
    id INT(15) PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30),
    date varchar(30),
    cin VARCHAR(30),
    nbrdejrs VARCHAR(255)

);
CREATE TABLE filiere (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `Nom_du_filiere` varchar(30) DEFAULT NULL,
  `Nom_du_Responsable` varchar(30) DEFAULT NULL,
  `Niveau` varchar(30) DEFAULT NULL,
  `Nombre_Etudiants` int(20) DEFAULT NULL,
  `Nombre_Modules` int(20) DEFAULT NULL,
  `semestre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `etudiants` (
  `filiere` varchar(255) NOT NULL,
  `niveau` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `cne` varchar(255) NOT NULL,
  `tel` varchar(10) NOT NULL,
  `date_naissance` varchar(255) NOT NULL,
    PRIMARY KEY (`cne`)
);

CREATE TABLE `cours` (
  `filiere` varchar(255) NOT NULL,
  `niveau` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `semestre` varchar(255) NOT NULL,
  `professeur` varchar(255) NOT NULL,
    PRIMARY KEY (`nom`)
)
