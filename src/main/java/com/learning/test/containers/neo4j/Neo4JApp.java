package com.learning.test.containers.neo4j;

import org.neo4j.driver.Record;
import org.neo4j.driver.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class Neo4JApp {

    private String dbUri;
    private String dbUser;
    private String dbPassword;

    public Neo4JApp(String dbUri, String dbUser, String dbPassword) {
        this.dbUri = dbUri;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public Record create() {
        try (var driver = GraphDatabase.driver(dbUri, AuthTokens.basic(dbUser, dbPassword))) {
            try (var session = driver.session(SessionConfig.builder().withDatabase("neo4j").build())) {
                try {
                    return session.executeWrite(tx -> create(tx, "John Doe", 25, "IBM"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private Record create(TransactionContext tx, String name, int age, String company) {
        Result result = tx.run("CREATE (p:Person {name: $name, age: $age, company: $company}) RETURN p",
                Map.of("name", name, "age", age, "company", company));
        return result.single();
    }
}
