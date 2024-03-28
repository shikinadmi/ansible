import java.sql.DriverManager

pipeline {
    agent any

    stages {
        stage('Подключение к базе данных') {
            steps {
                script {
                    def dbUrl = 'jdbc:postgresql://localhost:5432/pprb'
                    def dbUsername = 'postgres'
                    def dbPassword = 'postgres'

                    // Подключение к базе данных
                    def connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)

                    // Создание схемы test_schema
                    def statement = connection.createStatement()
                    statement.executeUpdate('CREATE SCHEMA test_schema')

                    // Закрытие подключения к базе данных
                    statement.close()
                    connection.close()
                }
            }
        }
    }
}