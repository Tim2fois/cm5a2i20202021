# Introduction à Docker
---------------------------

## Docker : historique et fonctionnement

Lancé en 2013, Docker est fondée en France par Solomon Hykes et désigne aujourd'hui une technologie permettant aux développeurs et aux administrateurs système de développer, déployer et exécuter des applications avec des conteneurs. Le principe est simple : le conteneur embarque une application avec toutes ses dépendances dans un processus isolé. Ce dernier peut-être executé sur n'importe quelle machine avec n'importe quel système d'exploitation compatible avec le moteur Docker.

Il est important de différencier la virtualisation et la conteneurisation : 
* Dans le cas de la vitualisation, l'isolation des machines virtuelles se fait au niveau matériel, on sépare les ressources comme la CPU, la RAM...
* Dans le cas de la conteneurisation, l’isolation se fait au niveau du système d’exploitation.

![Différence virtualisation et conteneurisation](https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fcodingthesmartway.com%2Fwp-content%2Fuploads%2F2019%2F02%2F010.png&f=1&nofb=1)

Cet état de fait est la force de la technologie Docker, cela permets aux conteneurs d'être plus léger, flexible, interchangeable, portable et évolutif.

## Création d'un conteneur

docker run image_name : permet de créer et démarer une nouvelle instance de l'image (conteneur)

si l'image n'existe pas, elle sera téléchargée à partir de docker hub

docker run -d image_name:tag : permet de démarrer conteneur en arrière plan 

la valeur par défaut d'une image est latest

docker ps : permet de lister tous les conteneurs qui sont en cours d'exécution. Chaque conteneur dispose d'un identifiant unique 

docker ps -a : permet de lister tous les onteneur aver leurs statuts (Up, Exited Created) 

Exécuter une image du serveur nginx : ``docker run -d -p 9999:80 nginx``

Pour donner un nom précis à votre conteneur, on utilise --name. Exemple : ``docker run --name moningx -d -p 8989:80 nginx`` Cette commande permet de démarrer un conteneur dont le nom est ``moningnx``. Pour pouvoir y accéder de l'extérieur (du moteur), on utilise la notion de redirection des ports. Ici, on va utiliser le port  ``8989``pour y accéder comme ceci : ``localhost:8989`` 


Pour arrêtre un conteneur ``docker stop identifiant_conteneur``. Pour supprimer un conteneur, on doit d'abord l'arrêter : ``docker rm identifiant_conteneur``. 

Pour lister toutes vos images ``docker images``


Pour forcer la suppression d'une image ou d'un conteneur : ``docker rmi -f nom_image``


La commande ``exec`` permet d'exécuter une commande dans un conteneur démarré

Exemple : notre objectif est de démarrer un conteneur MySQL et d'exécuter le client mysql

Solution : ``docker run --name monserveur -e MYSQL_ROOT_PASSWORD=root -d mysql``

Pour lancer mysql : ``docker exec -ti monserveur mysql --password``

### Exercice 1

Exercice : démarer un conteneur à partir de l'image ubuntu et puis installer java et exécuter un programme simple. 
 
 
 ``docker run -it ubuntu bash``  
 
 ## Installation des outils dans le conteneur
 
 Une fois à l'intérieur du conteneur, vous pouvez  installer les outils dont vous avez besoin comme vous avez l'habitube de le faire en utilisant ubuntu. 

Si on veut installer par exemple Java, on procède de la façon suivante : on met d'abord à jour ``apt-get update``et puis ``apt-get install java``. Si on veut utiliser vim comme éditeur `` apt-get install vim``.  
  
  
Pour inspecter un contenur et avoir des détails sous forme d'un ficheir json ``docker inspect id_conteneur`` 

Pour avoir les logs d'un contenur : ``docker logs id_conteneur`` 

Pour redémarrer un conteneur : ``docker start id_conteneur`` 

### Exercice 2

Exercice : démarrer un conteneur nginx et changer le contenu du fichier index.html. 

La page index.html est par défaut dans le répertoire ``/usr/share/nginx/htm ``  

## Notion de volume

Notion de volume :


``docker volume``

```java 
Commands:
  create      Create a volume
  inspect     Display detailed information on one or more volumes
  ls          List volumes
  prune       Remove all unused local volumes
  rm          Remove one or more volumes
```



Pour faire "mapping volume" 


``docker run -dti -p 8080:80 -v /Users/syoucef/Desktop/dockerexample/mapage/:/usr/share/nginx/html/ --name myserverweeb nginx``

``docker run -tid --name webvolume -p 9698 --mount source=monvolume,target=/usr/share/nginx/html/ nginx`` 


Pour accéder aux volumes (sous mac) : 

`` docker run -it --privileged --pid=host debian nsenter -t 1 -m -u -n -i sh ``

Une fois à l'intéreiur du conteneur, les volumes se trouvent dans le dossier :  ``/var/lib/docker/volumes/``


La soslution ici : https://stackoverflow.com/questions/38532483/where-is-var-lib-docker-on-mac-os-x

