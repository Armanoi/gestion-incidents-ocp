# 🛠️ Application de Gestion des Incidents — OCP (Stage)

Application mobile développée dans le cadre de mon **stage d’initiation (1 mois)** au  
**Groupe OCP — Département Informatique (Khouribga)**.

Elle permet de **déclarer, suivre et traiter** les incidents informatiques, avec une
séparation claire des rôles (**Utilisateur / IT / Admin**) afin d’améliorer la
communication et l’efficacité du support IT.

> 🎯 Objectif : centraliser les incidents, accélérer leur traitement et assurer un
> suivi clair des demandes.

---

## ✨ Fonctionnalités

### 👤 Espace Utilisateur
- Connexion / authentification
- Déclaration d’un incident  
  (incidents fréquents + option *Autre*)
- Envoi de messages à l’équipe IT
- Suivi de l’état de l’incident (selon implémentation)

### 🧑‍💻 Espace IT
- Consultation des incidents signalés
- Réponse aux utilisateurs
- Actions autorisées (ex. réinitialisation du mot de passe)

### 👨‍💼 Espace Administrateur
- Gestion des incidents (ajout / modification / suppression)
- Gestion des comptes IT
- Gestion des utilisateurs

---

## 🧰 Technologies & Outils

- **Android (Kotlin)**
- **SQLite** (base de données locale)
- **RecyclerView**
- **Material Design**
- Architecture : **MVVM / MVC**
- Outils : **Android Studio**, **Git**, **GitHub**

---

## 🗃️ Base de données (aperçu)

Tables principales :
- `users` : utilisateurs
- `ITUsers` : techniciens IT
- `incidents` : incidents  
  (titre, description, date, statut, utilisateur, etc.)

---

## 🧱 Structure du projet (exemple)

```txt
app/
 ├─ src/main/
 │   ├─ java/.../
 │   │   ├─ activities/
 │   │   ├─ adapters/
 │   │   ├─ models/
 │   │   ├─ database/
 │   │   └─ utils/
 │   └─ res/
 │       ├─ layout/
 │       ├─ drawable/
 │       └─ values/
## 🎓 Contexte du projet

Ce projet a été réalisé dans le cadre d’un **stage d’initiation** au sein du  
**Groupe OCP — Département Informatique (Khouribga)**.

Il répond à un besoin réel du service informatique en matière de **gestion,
suivi et organisation des incidents**, tout en me permettant d’appliquer
les concepts du développement mobile Android en Kotlin dans un contexte
professionnel.
## 👩‍💻 Auteur

**Amal Laknifed**  
🎓 Licence en Ingénierie Logicielle Web & Mobile (ENSA Khouribga – en cours)  
🎓 Bac+2 Développement Digital – Applications Mobiles  
🏢 Stage effectué à **OCP Khouribga — Département Informatique**  
📍 Khouribga, Maroc  

📧 Email : laknifedlema@gmail.com  
💻 GitHub : https://github.com/Armanoi  
🔗 LinkedIn : *Coming soon*
