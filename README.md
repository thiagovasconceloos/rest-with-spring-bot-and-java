
# Spring Boot

**O que é:** framework do ecossistema Spring que simplifica a criação de APIs e apps Java, removendo grande parte da configuração manual.
**Pontos-chave:**

* Inicialização rápida (starters) e **auto-configuration**.
* Embute servidor (Tomcat/Jetty), rodando com `java -jar`.
* Convenções > configurações; fácil integração com Spring Data, Security, Validation etc.

**Exemplo mínimo:**

```java
@SpringBootApplication
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class,args);
    }
}

@RestController
class Hello {
    @GetMapping("/hello")
    String hi(){
        return "Hello";
    }
}
```

---

# HATEOAS

**Hypermedia As The Engine Of Application State.** Em uma API RESTful, as respostas incluem **links** que indicam ações relacionadas ao recurso.

**Exemplo de resposta:**

```json
{
  "id": 10,
  "titulo": "Apartamento",
  "_links": {
    "self": { "href": "/imoveis/10" },
    "fotos": { "href": "/imoveis/10/fotos" },
    "atualizar": { "href": "/imoveis/10", "method": "PUT" }
  }
}
```

Benefícios: descoberta de recursos, navegação guiada pelo servidor, menor acoplamento cliente-servidor.

---

# Versionamento de API

**Por que:** evitar quebrar clientes ao evoluir sua API.

**Estratégias comuns:**

* **Na URL:** `/v1/imoveis`, `/v2/imoveis`
* **No Header personalizado:** `Accept-Version: 2`
* **Por Media Type (content negotiation):** `Accept: application/vnd.imovelcerto.v2+json`

**Boas práticas:**

* Documentar mudanças por versão.
* Deprecar com aviso e prazo.
* Manter contratos estáveis e testes de contrato.

---

**💡 Exemplo prático com Spring Boot usando URL**

```java
@RestController
@RequestMapping("/v1/imoveis")
public class ImovelV1Controller {
    @GetMapping
    public String listarV1() {
        return "Lista de imóveis - versão 1";
    }
}

@RestController
@RequestMapping("/v2/imoveis")
public class ImovelV2Controller {
    @GetMapping
    public String listarV2() {
        return "Lista de imóveis - versão 2 (com novos campos)";
    }
}
```

📌 **Quando criar uma nova versão**

* Alterar estrutura do JSON (renomear/remover campos obrigatórios).
* Mudar regras de negócio que afetam o contrato.
* Alterar URLs de forma incompatível.

📍 Mudanças **compatíveis** (ex.: adicionar campo opcional) normalmente não precisam de nova versão.

---

# POM (Maven)

**Project Object Model** – arquivo `pom.xml` que descreve o projeto: dependências, plugins, build, perfis.

**Exemplo básico:**

```xml
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>br.com.exemplo</groupId>
  <artifactId>api-imoveis</artifactId>
  <version>1.0.0</version>
  <dependencies>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
  </dependencies>
  <build>
    <plugins>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
</project>
```

---

# Documentação (Swagger / OpenAPI)

**OpenAPI** é a especificação; **Swagger** é o conjunto de ferramentas (UI, Editor etc.).

**Em Spring Boot (springdoc-openapi):**

```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.6.0</version>
</dependency>
```

* UI padrão: `/swagger-ui.html`
* JSON/YAML: `/v3/api-docs`
  **Boas práticas:** anotar DTOs/Controllers, descrever códigos de status, exemplos, segurança (JWT), versionar a doc junto com a API.

---

# Web Services: SOAP × REST

**SOAP**

* Protocolo baseado em **XML** e envelope SOAP.
* Contrato via **WSDL**.
* Forte padronização (WS-\*), transações, segurança empresarial.
* Mais verboso, rígido.

**REST**

* **Estilo arquitetural** sobre HTTP.
* Recursos endereçados por **URLs**, trocando **representações** (geralmente JSON).
* Simples, cacheável, escalável, ideal para web/mobile.

**Comparativo rápido:**

| Aspecto       | SOAP                           | REST                      |
| ------------- | ------------------------------ | ------------------------- |
| Formato       | XML (envelope SOAP)            | JSON, XML, etc.           |
| Contrato      | WSDL                           | OpenAPI (de facto)        |
| Operações     | Ação em SOAPAction             | GET/POST/PUT/DELETE…      |
| Cache         | Raro                           | Nativo via HTTP           |
| Complexidade  | Alta                           | Baixa                     |
| Casos típicos | Integrações legadas/enterprise | Web/Mobile, microservices |

---

# RESTful × REST

* **REST**: princípios gerais (recursos, stateless, cache, uniform interface, HATEOAS, camadas).
* **RESTful**: **grau de aderência** a esses princípios. Uma API pode ser “REST-like” (usa HTTP/JSON) mas **não** ser totalmente **RESTful** se, por exemplo, não usa HATEOAS, não aproveita cache, ou usa verbos errados.

**Sinais de API mais RESTful:**

* Recursos nomeados no plural: `/imoveis`, `/imoveis/{id}`
* Verbos HTTP corretos e **idempotência** respeitada
* Códigos de status bem usados (200, 201, 204, 400, 404, 409, 422, 500…)
* HATEOAS/links quando útil
* Cache/ETag, paginação, filtros, ordenação via query params
* Documentação OpenAPI e versionamento claro

---

# Anotações úteis do Spring Boot

| Anotação          | Fonte dos dados / Função                                |
| ----------------- | ------------------------------------------------------- |
| `@RequestParam`   | Query string ou formulário (`/url?param=valor`).        |
| `@PathVariable`   | Valor direto na URL (`/url/{id}`).                      |
| `@RequestBody`    | Corpo da requisição (JSON, XML) convertido para objeto. |
| `@RequestHeader`  | Cabeçalhos HTTP (`Authorization`, `Content-Type`).      |
| `@GetMapping`     | Atende requisições GET.                                 |
| `@PostMapping`    | Atende requisições POST.                                |
| `@PutMapping`     | Atende requisições PUT.                                 |
| `@DeleteMapping`  | Atende requisições DELETE.                              |
| `@PatchMapping`   | Atualização parcial de recurso.                         |
| `@Controller`     | Controller que retorna view (HTML).                     |
| `@RestController` | Controller que retorna JSON/XML direto no corpo.        |
| `@Service`        | Classe de regra de negócio.                             |
| `@Repository`     | Classe de acesso a dados (DAO).                         |
| `@Autowired`      | Injeção de dependência automática.                      |
| `@Value`          | Injeta valor do `application.properties`.               |
| `@Configuration`  | Classe de configuração.                                 |
| `@Bean`           | Método que registra um Bean no contexto Spring.         |
| `@Component`      | Classe genérica gerenciada pelo Spring.                 |
| `@Valid`          | Valida objeto com Bean Validation.                      |
| `@Transactional`  | Garante transação atômica no banco de dados.            |

---

# Beans no Spring – O que são e como usar

Um **Bean** é qualquer objeto **criado, configurado e gerenciado** pelo contêiner do Spring.
Vantagens:

* Injeção de dependência.
* Configuração centralizada.
* Controle de ciclo de vida.

## Principais anotações relacionadas a Beans

| Anotação         | Uso principal                                  |
| ---------------- | ---------------------------------------------- |
| `@Bean`          | Criar Bean manualmente em `@Configuration`.    |
| `@Component`     | Criar Bean genérico detectado automaticamente. |
| `@Service`       | Lógica de negócio.                             |
| `@Repository`    | Acesso a dados.                                |
| `@Configuration` | Classe que centraliza Beans/configurações.     |
| `@Autowired`     | Injetar Bean automaticamente.                  |

💡 **Arquitetura recomendada:**

* Controller → recebe requisições HTTP.
* Service → regras de negócio.
* Repository → acesso a dados.
* Beans extras → utilitários, mapeadores, configs.

---

# 📡 Resumo dos Códigos HTTP

| Grupo   | Faixa   | Significa                 |
| ------- | ------- | ------------------------- |
| **1xx** | 100–199 | Informativo (processando) |
| **2xx** | 200–299 | Sucesso                   |
| **3xx** | 300–399 | Redirecionamento          |
| **4xx** | 400–499 | Erro do cliente           |
| **5xx** | 500–599 | Erro do servidor          |

---


