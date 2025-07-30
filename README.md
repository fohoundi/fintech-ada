
# 💸 fintechAda – Application console Java de simulation d'une fintech

**fintechAda** est une application console développée en Java qui simule les principales fonctionnalités d'une application **fintech**. Elle permet aux utilisateurs d'effectuer des transactions simples (dépôt et retrait) via un portefeuille virtuel.

---

## 📌 Fonctionnalités principales

* 🧍 **Types d'utilisateurs** :

  * **Customer (Client)** : peut consulter son solde, déposer et retirer de l’argent.
  * **Marchand (Commerçant)** : mêmes droits qu’un client.
  * **Admin** : peut consulter les listes de clients et marchands, et promouvoir un utilisateur au rôle d’administrateur.

* 💳 **Transactions** :

  * Dépôt d'argent sur un portefeuille (wallet)
  * Retrait d'argent du portefeuille
  * Consultation de solde

* 🔐 **Authentification et gestion de comptes**

  * Création d’un compte utilisateur
  * Connexion via login / mot de passe

---

## 🛠️ Technologies utilisées

* **Java 17**
* **MySQL 8.0.43** (via Docker)
* **JDBC** pour la communication avec la base de données
* **Docker** pour exécuter le serveur MySQL
* **mysql-connector-j-8.0.33** pour le driver JDBC

---

## 🐳 Configuration de la base de données via Docker

L’application utilise **MySQL 8.0.43-oraclelinux9**, que vous pouvez récupérer depuis Docker Hub.

### 🔄 Étapes pour démarrer la base de données :

1. **Tirer l’image depuis Docker Hub :**

```bash
docker pull mysql:8.0.43-oraclelinux9
```

2. **Démarrer un conteneur MySQL avec port 3306 exposé :**

```bash
docker run --name fintech-mysql -e MYSQL_ROOT_PASSWORD=root -p 3306:3306 -d mysql:8.0.43-oraclelinux9
```

3. **Entrer dans le conteneur pour accéder au client MySQL :**

```bash
docker exec -it fintech-mysql bash
mysql -u root -p
```

> ⚠️ Le mot de passe root par défaut est `root`.

4. **Créer la base de données utilisée par l’application :**

```sql
CREATE DATABASE fintechAda;
```

---

## 📦 Configuration du projet

1. **Cloner ce dépôt :**

```bash
git clone https://github.com/votre-utilisateur/fintechAda.git
cd fintechAda
```

2. **Ajouter le driver JDBC à votre projet :**

L’application nécessite le fichier suivant (déjà fourni dans le dépôt) :

```
mysql-connector-j-8.0.33.jar
```

* Dans IntelliJ IDEA :

  * Allez dans `File > Project Structure > Modules > Dependencies`
  * Cliquez sur `+` > `JARs or directories`
  * Sélectionnez le fichier `mysql-connector-j-8.0.33.jar` à la racine du projet

---

## ▶️ Lancement de l’application

Une fois la base de données démarrée et le projet configuré :

1. Lancer la classe `Main.java` depuis votre IDE (ex: IntelliJ).
2. Suivez les instructions dans le terminal :

   * Inscription
   * Connexion
   * Interaction avec le portefeuille selon le rôle

---

## 📁 Structure du projet

```
src/
├── dao/                    // DAO pour l’accès à la base
├── model/                  // Entités Java : Customer, Marchant, Wallet, etc.
├── services/               // Logique métier
├── transactions/           // Traitement des opérations (dépôt, retrait)
├── utils/                  // Classes utilitaires (affichage, validation)
└── Main.java               // Point d’entrée de l’application
```

---

## ✅ Prérequis

* Java 17 installé
* Docker installé et en cours d’exécution
* Un IDE Java (recommandé : IntelliJ IDEA)
* Accès à un terminal

---

## 📌 À venir

* Gestion des virements entre utilisateurs
* Interface graphique (JavaFX ou Web)
* API REST (version future)

---

## 📄 Licence

Projet open-source pour usage pédagogique.

