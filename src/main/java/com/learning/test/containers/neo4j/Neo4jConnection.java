package com.learning.test.containers.neo4j;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.GraphDatabase;

public class Neo4jConnection {

    public static void main(String... args) {

        // docker run --rm -d --publish=7474:7474 --publish=7687:7687
        //      --env=NEO4J_AUTH=neo4j/Admin@123 --name neo4j neo4j:5.10

        final String dbUri = "neo4j://localhost:7687";
        final String dbUser = "neo4j";
        final String dbPassword = "Admin@123";

        try (var driver = GraphDatabase.driver(dbUri, AuthTokens.basic(dbUser, dbPassword))) {
            driver.verifyConnectivity();
        }
    }
}
