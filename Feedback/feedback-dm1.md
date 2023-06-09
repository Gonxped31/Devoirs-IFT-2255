# Commentaires DM1

Corrigé par An Li

Total: 99%

## Glossaire [9/10]

- Lister les types d'action

## Diagramme de cas utilisation [20/25]

- Manque le robot comme acteur
- Ne pas lister l'acteur à plusieurs reprises, même s'il est primaire dans un et secondaire dans un autre cas d'utilisation
- Manque une relation «include» de "Création des actions" à "Contrôle des mouvements"
- L'utilisateur et le fournisseurs doivent pointer vers le Client

## Description des cas d'utilisation [50/50]

- Vous avez déjà 10 cas d'utilisation correctes, donc vous avez tous les points.
- Inscription d’un client en tant que fournisseur
  - L'énoncé a été simplifié, un fournoisseur n'a plus à indiquer le type de robots fabriqués, le type de composantes fabriquées à l'inscription
- Enregistrement d’un robot
  - Que se passe-t-il si l'utilisateur a déjà enregistré ce robot avec ce numéro de série?
- Créer des tâches
  - Il manque l'option de définir l'horaire ou une action déclencheur
- Acheter des composantes
  - Que se passe-t-il si le fournisseur sélectionné n'a aucune composante à offrir ou que toutes les composantes sont en rupture de stock?
- Contrôler les mouvements d'un robot
  - Manque précondition: Le robot doit avoir une tâche avec des actions de déplacement assigné

## Risques [4/5]

- Mentionnez le risque de perte de connexion des robots à la plateforme

## Besoins non-fonctionnels [4/5]

- Les exigences 2 et 5 sont de la même catégorie et très intimement reliées
  - Indiquez une autre exigence d'une catégorie que vous n'avez pas encore mentionnée

## Bonne utilisation de GitHub et statistiques [5/5]

- Bonne utilisation des Issues et des Pull requests
- Déséquilibre dans le nombre de commits
  - Deux membres sur 5 ont peu contribué

## Bonus: Application Java [7/10]

- Fonctionnels
  - Connexion
  - Inscription
  - Menu principal
  - Recherche de fournisseurs
  - Ajouter un robot
  - Retirer un robot
- Manquantes
  - Mot de passe requis à l'inscription et à la connexion
  - On ne peut pas acheter un robot, donc on ne peut pas tester l'enregistrement et les autres fonctionnalités
