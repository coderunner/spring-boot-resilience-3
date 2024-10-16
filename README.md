# Demo Résilience et Journaux avec Spring Boot 3

Cloner le dépôt puis exécuter sur la ligne de commande dans le répertoire racine:

```
./gradlew bootRun
```

Amuzez-vous!

# Résilience

- Voir UnstableController (erreur forcée selon paramètre unstable-value dans application.properties)
- Voir StableController
    - Stratégie de Retry
    - Timeout (OkHttpClient)
    - Retrofit Client

# Journaux
- slf4j + log4j2
- log4j2.xml
    - Appenders (configuration des destinations, log pattern)
        - %t pour le thread id
    - Loggers (configuration des Loggers: level + appenders)
- voir rotation de fichiers (access-X.log)

# Trace distribuée
- HttpFilter (gestion du correlationId à la réception)
- Ajout du correlationId dans le context de la requête
- Ajout du Header lors de l'appel client
- Utilisation du MDC (user info, associé au thread d'exécution, repris pas le logger)
- Voir log4j2.xml - x-correlation-id
