## Instruções para rodar a aplicação no Docker
##### Logo logo vou colocar o docker-compose.yml pra funcionar, mas por enquanto, divirtam-se fazendo rodar o projeto no Docker com os passos a seguir!


### Crie uma network
```
docker network create basic
```

### Suba um container do Oracle Database conectado nesta network
```
docker run -d -p 1521:1521 -e ORACLE_PASSWORD=P@ssword --network=basic --name=oracle --hostname=oracle.db gvenzl/oracle-xe
```

### Defina o arquivo application.properties
##### Note que coloquei o hostname no host de conexão do banco
```
spring.datasource.url=jdbc:oracle:thin:@oracle.db:1521:xe
spring.datasource.username=SYSTEM
spring.datasource.password=P@ssword
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
```

### Crie a imagem da aplicação a partir do Dockerfile na raiz do projeto
```
docker build -t java-basic .
```
### Suba o container da aplicação
##### Note que estou subindo o container na mesma network do container oracle
```
docker run -d -p 8080:8080 --network=basic java-basic
```

### Se tudo ocorreu conforme o esperado, você pode chamar um endpoint da API para fazer uso: 
```
http://localhost:8080/customers
```
