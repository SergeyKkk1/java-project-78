### Hexlet tests and linter status:
[![Actions Status](https://github.com/SergeyKkk1/java-project-78/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/SergeyKkk1/java-project-78/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=SergeyKkk1_java-project-78&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=SergeyKkk1_java-project-78)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=SergeyKkk1_java-project-78&metric=coverage)](https://sonarcloud.io/summary/new_code?id=SergeyKkk1_java-project-78)
## Data Validator Project
A library which helps you validate any data in your application.
## How to use this project.
```
git@github.com:SergeyKkk1/java-project-78.git
make build
```
After creation of `jar` file, you can add it into project which 
requires data validation and use it there.

## Documentation
### String validation
- `required()` — makes null/empty string invalid.
- `minLength(int)` — enforces minimum string length.
- `contains(String)` — requires substring to be present.

```java
var v = new Validator();
var schema = v.string().required().minLength(4).contains("ex");

schema.isValid("hexlet"); // true
schema.isValid("hey");    // false
```

### Number validation
- `required()` — makes null invalid.
- `positive()` — allows only numbers > 0.
- `range(int, int)` — enforces inclusive bounds.
```java
var v = new Validator();
var schema = v.number().required().positive().range(5, 10);

schema.isValid(5);   // true
schema.isValid(0);   // false
schema.isValid(11);  // false
```

### Map validation schema
- `required()` — makes null invalid.
- `sizeof(int)` — requires exact map size.
- `shape(Map<String, BaseSchema<?>>)` — validates map values by per-key schemas.

```java
var v = new Validator();
var schema = v.map().required().sizeof(2);

var data = new HashMap<String, String>();
data.put("key1", "value1");
data.put("key2", "value2");

schema.isValid(data); // true
```
#### Map shape validation
```java
var v = new Validator();
var schema = v.map();

Map<String, BaseSchema<String>> schemas = new HashMap<>();
schemas.put("firstName", v.string().required());
schemas.put("lastName", v.string().required().minLength(2));

schema.shape(schemas);

Map<String, String> human = new HashMap<>();
human.put("firstName", "John");
human.put("lastName", "Smith");

schema.isValid(human); // true
```
