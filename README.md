# amisDeLEscalade
## **Création d’un site communautaire autour de l’escalade**

Ce site aura pour but la mise en relation et le partage d’informations.
Il permettra de donner de la visibilité à l’association afin d’encourager des grimpeurs indépendants à y adhérer.

### **Liste des fonctionnalités :**
- **F1** : Un utilisateur doit pouvoir consulter les informations des sites d’escalades (secteurs, voies, longueurs, ​etc.​).
- **F2** : Un utilisateur doit pouvoir faire une recherche à l’aide de plusieurs critères pour trouver un site de grimpe et consulter le résultat de cette recherche. Les critères peuvent être le lieu, la cotation, le nombre de secteurs, ​etc​.
- **F3** : Un utilisateur doit pouvoir s’inscrire gratuitement sur le site.
- **F4** : Un utilisateur connecté doit pouvoir partager des informations sur un site d’escalade (secteurs, voies, longueurs, ​etc​.).
- **F5** : Un utilisateur connecté doit pouvoir laisser des commentaires sur les pages des sites d’escalade.
- **F6** : Un membre de l'association doit pouvoir taguer un site d’escalade enregistré sur la plateforme comme « Officiel Les amis de l’escalade ».
- **F7** : Un membre de l'association doit pouvoir modifier et supprimer un commentaire.
- **F8** : Un utilisateur connecté doit pouvoir enregistrer dans son espace personnel les topos qu’ils possèdent et préciser si ces derniers sont disponibles pour être prêtés ou non. Un topo est défini par un nom, une description, un lieu et une date de parution.
- **F9** : Un utilisateur connecté doit pouvoir consulter la liste des topos disponibles par d’autres utilisateurs et faire une demande de réservation. La réservation n’inclut pas les notions de date de début et date de fin.
- **F10** : Un utilisateur connecté doit pouvoir accepter une demande de réservation. Si une réservation est acceptée, automatiquement le topo devient indisponible. L’utilisateur connecté pourra ensuite de nouveau changer le statut du topo en « disponible ». La plateforme se contente de mettre en contact les deux parties pour le prêt d’un topo (par échange de coordonnées).

### **Liste des contraintes fonctionnelles :**
- **C1** : Le vocabulaire de l’escalade doit être utilisé : site, spot, voie, longueur, grimpeur, point ou spit, cotation, topo.
- **C2** : Le site doit être ​responsive​.
- **C3** : Le site doit être sécurisé

## **Déploiement**
1. Installer JDK d'Oracle 1.8
2. Cloner le projet sous GitHub
3. Installer Maven version minimum 4
4. Installer Tomcat 9
5. Créer une base de données sous PostgreSQL
6. Sous src/main/resources modifier le fichier database.properties sur les champs suivant : 
 - jdbc.url=jdbc:postgresql://localhost:5432/*"nom de votre base de données"*
 - jdbc.username=*"votre nom d'utilisateur de la bdd"*
 - jdbc.password=*"votre mot de passe"*
8. Sous src/main/resources dans le fichier log4j2.xml sous appenders/rolllingfile, modifier *filename* par votre propre chemin d'enregistrement
9. Lancement de l'application :
 - Lancement depuis l'IDE (dévellopé avec Eclipse) lancer le run sur le serveur
 - Lancement depuis le war :
  1. Récupérer le script de création de la base de données et le script de données de la base à la racine du projet
  2. Lancer ses scripts dans la base de données créée
  3. Dans le terminal lancez la commande mvn package
  4. Copier le fichier war, générer dans le dossier target, sous le répertoire webapps de Tomcat
  5. Lancer le serveur Tomcat et rendez-vous sur l'adresse http://localhost:8080/amisDeLEscalade
Vous pouvez maintenant accéder au site