
# BCrypt is from:
# https://github.com/spring-projects/spring-security/blob/master/crypto/src/main/java/org/springframework/security/crypto/bcrypt/BCrypt.java

# compile BCrypt
javac careflight/adhoc/BCrypt.java 

# get spring-security...jar, and compile
javac -cp spring-security-core-2.0.5.RELEASE.jar:. careflight/adhoc/BCryptPasswordEncoder.java 

# assemble jar
zip bcrypt.jar careflight/adhoc/*.class

# get other spring jars and test
java -cp bcrypt.jar:spring-security-core-2.0.5.RELEASE.jar:spring-core-2.5.6.jar:spring-2.5.6.jar \
    careflight.adhoc.BCryptPasswordEncoder 'test-password'

java -cp bcrypt.jar:spring-security-core-2.0.5.RELEASE.jar:spring-core-2.5.6.jar:spring-2.5.6.jar \
    careflight.adhoc.BCryptPasswordEncoder \
    'test-password' '$2a$10$Jn13wcB5kv2eQzQ7WqV8VuV3IlRVALios2o5Eo8XHfba6/3w8bEye'
