# Mediscreen

### Prérequis
- Java 16
- Maven 3.8.1
- Node js 16.3.0
- MySql 8.0.25
- MongoDb 5.0.4

### Installation
1. Installer Java https://adoptopenjdk.net/
2. Installer Maven https://maven.apache.org/install.html
3. Installer Node js https://nodejs.org/en/download/
4. Installer MySql https://dev.mysql.com/downloads/mysql/
5. Installer MongoDb https://docs.mongodb.com/manual/installation/  

### Microservices
- patient : API relative aux informations des patients, 
connectée à la bdd mysql (localhost:8081)
- note : API relative aux notes prises par les praticiens, 
connectée à la bdd mongo (localhost:8082)
- report : API relative au rapport de risque de diabète des patients, 
dépend des deux microservices ci-dessus via FeignClient (localhost:8080)

### Lancer l'application 
Vous pouvez importer le projet dans un IDE de votre choix et 
initialiser et peupler les bases de données :
- Mysql : il vous suffit de démarrer Mysql et d'éxecuter le script
  disponible sur ce repository sous mysqldb/mediscreen.sql en éxécutant la commande :
```source <chemin_vers_mediscreen.sql>```
- Mongodb : les données sont disponible sur ce repository sous mongodb/note.json  
  En ligne de commande, éxécutez la commande :
  ```mongoimport --db mediscreen --collection note --file <chemin_vers_note.json>```

Une fois les bdd initialisées et peuplées,
vous pouvez lancer les différents microservices.
N'oubliez pas de démarrer Mysql avant de lancer le microservice patient.
 
### Lancer l'application via Docker
Vous pouvez importer le projet dans un IDE de votre choix et créer 
vous-même les images en ligne de commande en suivant ces deux étapes :
- Placez-vous dans le dossier mediscreen.
- exécutez la commande : 
```
docker-compose up --build
```
Une fois les images créées, les bases de données des conteneurs
mysqld et mongodb sont automatiquement peuplées et l'application
va se lancer : rendez-vous sur localhost:4200