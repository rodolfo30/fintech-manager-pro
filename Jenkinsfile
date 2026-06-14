pipeline {
    agent any

    tools {
        maven 'Maven 3.x'
        jdk 'Java 17'
    }

    stages {
        stage('Clonar Repositorio') {
            steps {
                echo 'Descargando el código desde GitHub...'
                checkout scm
            }
        }

        stage('Compilar Proyecto') {
            steps {
                echo 'Compilando el proyecto con Maven...'
                sh 'mvn clean compile'
            }
        }

        stage('Ejecutar Tests y Cobertura') {
            steps {
                echo 'Ejecutando pruebas unitarias y generando reporte de JaCoCo...'
                sh 'mvn test'
            }
        }

        stage('Verificar Calidad (Cobertura)') {
            steps {
                echo 'Validando que la cobertura supere el 80%...'
                // Este comando lee el reporte de JaCoCo que configuramos en el pom.xml
                jacoco execPattern: '**/target/*.exec', classPattern: '**/target/classes'
            }
        }
    }

    post {
        success {
            echo '¡El Pipeline se ha ejecutado con ÉXITO! El código es estable.'
        }
        failure {
            echo 'El Pipeline ha FALLADO. Revisa los logs de los tests o la compilación.'
        }
    }
}