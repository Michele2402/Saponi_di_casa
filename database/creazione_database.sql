CREATE DATABASE  IF NOT EXISTS `saponidicasa`
USE `saponidicasa`;



DROP TABLE IF EXISTS `categoria`;

CREATE TABLE `categoria` (
                             `id` int NOT NULL,
                             `nome` varchar(100) DEFAULT NULL,
                             `descrizione` varchar(300) DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `prodotto`;

CREATE TABLE `prodotto` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `nome` varchar(100) NOT NULL,
                            `descrizione` varchar(300) NOT NULL,
                            `prezzo` double NOT NULL,
                            `immagine` varchar(100) DEFAULT NULL,
                            `categoria_id` int NOT NULL,
                            PRIMARY KEY (`id`),
                            CONSTRAINT `fk_categoria`
                                FOREIGN KEY (`categoria_id`)
                                    REFERENCES `categoria`(`id`)
                                    ON DELETE CASCADE
                                    ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




DROP TABLE IF EXISTS `utente`;

CREATE TABLE `utente` (
                          `username` varchar(100) NOT NULL,
                          `nome` varchar(100) NOT NULL,
                          `cognome` varchar(100) NOT NULL,
                          `telefono` varchar(100) NOT NULL,
                          `email` varchar(100) NOT NULL,
                          `password` varchar(100) NOT NULL,
                          `indirizzo` varchar(300) NOT NULL,
                          `admin` boolean NOT NULL DEFAULT 0,
                          PRIMARY KEY (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;





DROP TABLE IF EXISTS `recensione`;

CREATE TABLE `recensione` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `utente_username` varchar(100) NOT NULL,
                              `valutazione` int NOT NULL,
                              `testo` text NOT NULL,
                              `data` datetime NOT NULL,
                              `prodotto_id` int NOT NULL,
                              PRIMARY KEY (`id`),
                              CONSTRAINT `fk_utente_username`
                                  FOREIGN KEY (`utente_username`)
                                      REFERENCES `utente`(`username`)
                                      ON DELETE CASCADE
                                      ON UPDATE CASCADE,
                              CONSTRAINT `fk_prodotto_recensione`
                                  FOREIGN KEY (`prodotto_id`)
                                      REFERENCES `prodotto`(`id`)
                                      ON DELETE CASCADE
                                      ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




DROP TABLE IF EXISTS `ordine`;

CREATE TABLE `ordine` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `totale` double NOT NULL,
                          `data_creazione` datetime NOT NULL,
                          `utente_username` varchar(100) NOT NULL,
                          PRIMARY KEY (`id`),
                          CONSTRAINT `fk_utente_ordine`
                              FOREIGN KEY (`utente_username`)
                                  REFERENCES `utente`(`username`)
                                  ON DELETE CASCADE
                                  ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




DROP TABLE IF EXISTS `prodotto_acquistato`;

CREATE TABLE `prodotto_acquistato` (
                                       `id` int NOT NULL AUTO_INCREMENT,
                                       `nome` varchar(100) NOT NULL,
                                       `prezzo` double NOT NULL,
                                       `quantità` int NOT NULL,
                                       `ordine_id` int NOT NULL,
                                       PRIMARY KEY (`id`),
                                       CONSTRAINT `fk_ordine_prodotto`
                                           FOREIGN KEY (`ordine_id`)
                                               REFERENCES `ordine`(`id`)
                                               ON DELETE CASCADE
                                               ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




DROP TABLE IF EXISTS `metodo_di_pagamento`;

CREATE TABLE `metodo_di_pagamento` (
                                       `tipo` varchar(50) NOT NULL,
                                       `numero_di_carta` varchar(20) NOT NULL,
                                       `cvv` varchar(4) NOT NULL,
                                       `nome` varchar(100) NOT NULL,
                                       `cognome` varchar(100) NOT NULL,
                                       `data_di_scadenza` date NOT NULL,
                                       `utente_username` varchar(100) NOT NULL,
                                       PRIMARY KEY (`numero_di_carta`),
                                       CONSTRAINT `fk_utente_pagamento`
                                           FOREIGN KEY (`utente_username`)
                                               REFERENCES `utente`(`username`)
                                               ON DELETE CASCADE
                                               ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;