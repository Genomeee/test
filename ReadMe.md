
<b>Project Description</b>

Simple application that fulfills the following requirements:
<br>- Gives user ability to search for a contact.
<br>- User authentication is made by OAuth2 (using Google Authentication)
<br>- App got one endpoint, that using search parameter X_TENANT_ID in the HTTP request header 
returns result with matching contacts
<br>- App got build in swagger

<br><br><b>Contact table fields:</b>
<ul>
    <li>matrix_id VARCHAR(200) NOT NULL</li>
    <li>name VARCHAR(200) NOT NULL</li>
    <li>tenant_id VARCHAR(200) NOT NULL</li>
</ul>

<br>Technologies and Frameworks used : Spring JPA, PostgresSql, Docker, Swagger.

<br><b>Steps to run project:</b>
<ol>
    <li>go to the root project folder and run <b><i>docker-compose up</i></b> to create docker image</li>
    <li>go to application.properties file and add your google client id and secret id, for this you need to create project and credentials via google dev console https://console.cloud.google.com/ </li>
    <li>start TestApplication</li>
</ol>