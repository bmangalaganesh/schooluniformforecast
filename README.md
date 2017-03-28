# School Uniform Forecast application

This application is a simple SpringBoot/Spring Cloud application that is built to determine an appropriate school uniform for school going kids based on the weather forecast. 

The initial implementation is simple and I intend to make this feature rich as we move forward.

To support SSL Created a keystore using the following values:

The keystore password is "password"

Manglu-mbp/resources:$ keytool -genkey -alias tomcat
Enter keystore password:  
Re-enter new password: Manglu-mbp/resources:$keytool -genkey -alias tomcat -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
Enter keystore password:  
Re-enter new password: 
What is your first and last name?
  [Unknown]:  Spring Boot
What is the name of your organizational unit?
  [Unknown]:  Cloud Competency
What is the name of your organization?
  [Unknown]:  ADL
What is the name of your City or Locality?
  [Unknown]:  Melbourne
What is the name of your State or Province?
  [Unknown]:  Victoria
What is the two-letter country code for this unit?
  [Unknown]:  AU
Is CN=Spring Boot, OU=Cloud Competency, O=ADL, L=Melbourne, ST=Victoria, C=AU correct?
  [no]:  Yes
