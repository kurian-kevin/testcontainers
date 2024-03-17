# testcontainers
Test Containers Learning

# Docker Commands
* Community: `docker run --rm -d --publish=7474:7474 --publish=7687:7687 --env=NEO4J_AUTH=neo4j/Admin@123 --name neo4j neo4j:5.10`
* Enterprise: `docker run --rm --publish=7474:7474 --publish=7687:7687 --env=NEO4J_AUTH=neo4j/Admin@123 --env=NEO4J_ACCEPT_LICENSE_AGREEMENT=yes --name neo4j neo4j:5.10-enterprise`