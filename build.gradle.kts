plugins {
    java
    id("org.springframework.boot") version "3.2.0"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.study"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
//    implementation("org.springframework.boot:spring-boot-starter-web-services")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.apache.kafka:kafka-streams")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("io.projectreactor.kafka:reactor-kafka:1.3.22")
    implementation("com.oracle.database.jdbc:ojdbc8:21.1.0.0")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("org.projectlombok:lombok:1.18.26")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testAnnotationProcessor("org.projectlombok:lombok")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
