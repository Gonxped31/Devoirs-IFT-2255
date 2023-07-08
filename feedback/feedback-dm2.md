# Commentaires DM2

Corrigé par Santiago Gomez Maqueo Anaya

Total: 96

## Description du système opérationnel [5/5]
Excellent. Vous avez satisfait tous les critères attendus.

## 6 diagrammes d'activité UML [25/25]
Excellent.

## Diagramme de classes UML [18/20]
Très -très- bon travail, mais vous beneficierais d'une décomposition plus atomique. Certaines de vos classes ont trop de responsabilités (dont Utilisateur, qui gère logique allant des données de l'utilisateur, jusqu'aux robots et notifications).

## 5 diagrammes de séquence UML [30/30]
Excellent.

## Code source Java du programme et fichier JAR [18/20]
Excellent, juste faits attention aux caractères spéciaux (comme les accents) dans vos messages/strings/code, ils peuvent engendrer des problèmes d'encodage de texte.
De même des methodes comme votre getInfoRobotFormater sont obfusqués dans leur implèmentation et donc plus difficile à maintenir; separez ce type de one-liner en plusieurs lignes pour faciliter la lecture/maintenance.
