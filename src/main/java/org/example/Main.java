package org.example;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, LiquibaseException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/liquibase", "****", "******");
        Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
        Liquibase liquibase = new Liquibase("test.xml", new FileSystemResourceAccessor(new File[]{new File("*******/liquibase")}), database);
        LabelExpression labelExpression = new LabelExpression();
        liquibase.update(new Contexts(new String[]{"SANDBOX"}), labelExpression);
    }
}