Spring Boot famous Petclinic sample with JPA persistence.

To test h2 or hsqldb database:

 * update *pom.xml*, set scope of h2 or hsqldb dependency as runtime
 * update *docker-compose.yml*, add the following environment variable to petclinic-jpa: *SPRING_PROFILES_ACTIVE=h2* or *SPRING_PROFILES_ACTIVE=hsqldb*