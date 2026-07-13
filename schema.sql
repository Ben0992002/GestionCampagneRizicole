-- Création de la base de données
CREATE DATABASE gestion_rizicole;

-- Table producteur
CREATE TABLE IF NOT EXISTS producteur (
    id SERIAL PRIMARY KEY,
    matricule VARCHAR(255) NOT NULL UNIQUE,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    telephone VARCHAR(255)
);

-- Table parcelle
CREATE TABLE IF NOT EXISTS parcelle (
    id SERIAL PRIMARY KEY,
    reference VARCHAR(255) NOT NULL UNIQUE,
    localisation VARCHAR(255),
    superficie DOUBLE PRECISION NOT NULL,
    producteur_id INTEGER NOT NULL,
    CONSTRAINT fk_parcelle_producteur FOREIGN KEY (producteur_id) REFERENCES producteur(id)
);

-- Table campagne
CREATE TABLE IF NOT EXISTS campagne (
    id SERIAL PRIMARY KEY,
    annee INTEGER NOT NULL,
    saison VARCHAR(255) NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    CONSTRAINT unique_annee_saison UNIQUE (annee, saison)
);

-- Table recolte
CREATE TABLE IF NOT EXISTS recolte (
    id SERIAL PRIMARY KEY,
    date_recolte DATE NOT NULL,
    quantite_kg DOUBLE PRECISION NOT NULL,
    parcelle_id INTEGER NOT NULL,
    campagne_id INTEGER NOT NULL,
    CONSTRAINT fk_recolte_parcelle FOREIGN KEY (parcelle_id) REFERENCES parcelle(id),
    CONSTRAINT fk_recolte_campagne FOREIGN KEY (campagne_id) REFERENCES campagne(id)
);
