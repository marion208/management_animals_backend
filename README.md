# management_animals_backend
Application permettant de gérer des animaux dans un élevage.
Fonctionnalités disponibles :
 - ajout / modification / suppression d'une espèce
 - ajout / modification / suppression d'un animal, qui est lié à une espèce et à un genre (mâle / femelle)
 - ajout / modification / suppression d'une pesée pour un animal, afin de pouvoir suivre l'évolution de son poids
 - ajout / modification / suppression d'un traitement (vaccin, médicament...) pour avoir un suivi de son dossier médical
Partie back-end réalisé avec Spring en mode TDD, partie front-end réalisée avec Vuejs.

Dans la partie front, on retrouve différents écrans :
 - dashboard : listant les animaux de l'évage avec les princiaples informations (espèce, dernier poids enregistré...)
 - écran de listing des espèces : permettant d'accéder à l'écran d'ajout ou de modification d'une espèce, et permettant également de supprimer une espèce
 - écran de listing des animaux : permettant de supprimer un animal, d'accéder à l'écran d'ajout ou de modification d'un animal, ou d'accéder à l'écran de détail du dossier d'un animal
  - écran de dossier d'un animal : permettant d'avoir toutes les informations, avec un graphique pour voir plus simplement l'évolution de son poids

Le code source du front-end est disponible à l'adresse suivante : https://github.com/marion208/management_animals_frontend


Application for managing animals in a farm.
Available features:
 - addition / modification / deletion of a species
 - addition / modification / deletion of an animal, which is linked to a species and a genus (male / female)
 - addition / modification / deletion of a weighing for an animal, in order to be able to follow the evolution of its weight
 - addition / modification / deletion of a treatment (vaccine, drug ...) to have a follow-up of his medical file
Back-end part made with Spring in TDD mode, front-end part made with Vuejs.

In the front part, there are different screens :
 - dashboard : listing the animals in the farm with the main information (species, last recorded weight ...)
 - species listing screen : allowing access to the screen for adding or modifying a species, and also allowing to delete a species
 - animal listing screen : allowing you to delete an animal, access the screen for adding or modifying an animal, or accessing the detail screen for an animal's file
  - animal file screen : allowing you to have all the information, with a graph to see more simply the evolution of its weight

The source code for the front-end is available at the following address : https://github.com/marion208/management_animals_frontend
