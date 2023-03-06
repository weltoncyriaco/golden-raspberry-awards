
# Golden Raspberry Awards

API RESTful para possibilitar a leitura da lista de indicados e vencedores
da categoria Pior Filme do Golden Raspberry Awards




## Variaveis de ambiente

Para executar a api já com dados de entrada, basta criar um arquivo chamado  `application.properties` al lado do arquivo `api-0.0.1.jar` e informar o caminho do arquivo de carga no parametro `api.csv.path`. 

### Exemplo
api.csv.path="D://list.csv"






## Deployment

Para executar o projeto basta utilizar o comando padrão do java.

```bash
  java -jar api-0.0.1.jar
```


## API
   A documentação completa poderá ser encontrada através do link: http://localhost:8080/swagger-ui/index.html#/

#### Get
 Para verificar os vencedores.

```http
  GET /api
```

#### Post
Para realizar cargas adicionais.

```http
  POST /dashboard
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `file`      | `file` | **Required**. Arquivo a ser enviado |



## Documentation

[Documentation](http://localhost:8080/swagger-ui/index.html#/)

