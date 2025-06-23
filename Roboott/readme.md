# README.md

```markdown
# Projet Java – Simulation d’Usine Robotisée

## À propos

Ce projet Java a été développé dans le cadre du cours d'introduction à la programmation orientée objet à **Télécom Paris**. Il simule une usine de production robotisée en modélisant des composants tels que **robots**, **machines de production**, **salles**, **portes**, **stations de recharge** et **convoyeurs**.

L’objectif principal est de mettre en œuvre les **principes de la programmation orientée objet en Java** tout en respectant une architecture claire de type **MVC** (Modèle-Vue-Contrôleur). Le projet a été réalisé à travers une série de **TPs progressifs**, et les documents de TDs ainsi que le cahier des charges sont également disponibles dans le dépôt.

L’usine est visualisée graphiquement via une interface (canvas), et les robots se déplacent automatiquement, transportent des rondelles et évitent les obstacles.

> Le projet a été réalisé avec **VS Code**.

---

## Fonctionnalités principales

- **Modélisation complète de l’usine** 
- **Déplacement autonome des robots** entre plusieurs destinations
- **Évitement automatique des obstacles**
- **Transport simulé de rondelles** entre les différentes zones
- **Interface graphique** pour visualiser la simulation en temps réel

---
## Diagramme de classe
![Diagramme de classe](docs/diagramme.png)

## Installation

Cloner le projet :

```bash
git clone https://github.com/<cameliarekioua>/<robot>.git
cd <robot>
```

---

## Compilation & Exécution

### Depuis un IDE (VS Code, Eclipse, IntelliJ…)

1. Ouvrir le dossier du projet dans votre IDE (ex. **VS Code**)
2. Lancer le fichier `SimulatorApplication.java` (clic droit → *Run* ou via l’explorateur Java)

### En ligne de commande (terminal)

#### Compilation :

```bash
javac -cp "libs/*" -d bin src/fr/tp/inf112/robotsim/simulator/SimulatorApplication.java
```

#### Exécution :

```bash
java -cp "libs/*:bin" fr.tp.inf112.robotsim.simulator.SimulatorApplication
```

(*Adapter le chemin selon l’organisation du projet*)

---

## Fonctionnalités optionnelles intégrées

- **Portes automatiques**

---

## Développement futur possible

- Gestion du niveau d'énérgie.

---


## Contact

Pour toute question ou rapport de bug :

**Camelia REKIOUA** – [camelia.rekioua@telecom-paris.fr](mailto:camelia.rekioua@telecom-paris.fr)
```
