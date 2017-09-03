# Code Challenge

Compilar proyecto con Maven:
```
mvn clean install compile
```

Ejecutar la clase App:
```
cd challenge-service
mvn exec:java -Dexec.mainClass="weinze.code.challenge.service.App"
```

## Query:
##### HTML
- http://localhost:4567/code-challenge/query
##### JSON
- http://localhost:4567/code-challenge/api/airports?country_code=AR
- http://localhost:4567/code-challenge/api/airports?country_name=Brazil

## Report:
##### HTML 
- http://localhost:4567/code-challenge/report
##### JSON
- http://localhost:4567/code-challenge/api/insights/topCountries?desc=false
- http://localhost:4567/code-challenge/api/insights/topCountries?desc=true
- http://localhost:4567/code-challenge/api/insights/runwaysByCountry
- http://localhost:4567/code-challenge/api/insights/topTenRunways

#### Documentación
- http://localhost:4567/code-challenge/api
