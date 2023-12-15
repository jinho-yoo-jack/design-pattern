# Code Source : https://www.baeldung.com/spring-boot-security-ssl-bundles
PASSWORD="YooJinHo"
openssl req -x509 -newkey rsa:4096 -keyout src/main/resources/key.pem -out src/main/resources/cert.pem -days 365 -passout pass:${PASSWORD}
openssl pkcs12 -export -in src/main/resources/cert.pem -inkey src/main/resources/key.pem -out src/main/resources/keystore.p12 -name secure-service -passin pass:${PASSWORD} -passout pass:${PASSWORD}