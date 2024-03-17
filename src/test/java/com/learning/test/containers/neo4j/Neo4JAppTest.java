package com.learning.test.containers.neo4j;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.driver.Record;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.Neo4jContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.Map;

import static org.junit.Assert.*;

public class Neo4JAppTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(Neo4JAppTest.class);

    private static Neo4jContainer neo4j;

    @BeforeClass
    public static void setUp() {
        neo4j = new Neo4jContainer<>(DockerImageName.parse("neo4j:5.10"));
        neo4j.withReuse(true);
        neo4j.start();
        LOGGER.info("Bolt URL: {}", neo4j.getBoltUrl());
        LOGGER.info("Admin User: {}", "neo4j");
        LOGGER.info("Admin Password: {}", neo4j.getAdminPassword());
    }

    @AfterClass
    public static void tearDown() {
        neo4j.stop();
    }

    @Test
    public void testCreate() {
        Neo4JApp neo4JApp = new Neo4JApp(neo4j.getBoltUrl(), "neo4j", neo4j.getAdminPassword());
        Record record = neo4JApp.create();
        Map<String, Object> resultMap = record.get(0).asMap();
        assertEquals("John Doe", resultMap.get("name"));
        assertEquals(25, Integer.parseInt(resultMap.get("age").toString()));
        assertEquals("IBM", resultMap.get("company"));
    }
}
