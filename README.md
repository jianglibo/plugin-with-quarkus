## Build and run

```bash
./gradlew build "-Dquarkus.package.type=uber-jar" -x test
```

```powershell
java -jar .\build\plugin-with-quarkus-1.0.0-SNAPSHOT-runner.jar 
```

```bash
java -jar build/*-runner.jar
```

## Example run

```bash
java -jar build/*-runner.jar stdio --name-length=6 --pages=1 --per-page=10
```

```powershell
java -jar .\build\plugin-with-quarkus-1.0.0-SNAPSHOT-runner.jar stdio --name-length=6 --pages=1 --per-page=3
```

The output:

```
08877f24-464a-4867-b58c-6b500349dae1
{
  "state" : {
    "current_id" : 4,
    "current_page" : 2,
    "per_page" : 3,
    "name_length" : 6
  },
  "data" : [ {
    "id" : 1,
    "name" : "lmayft",
    "age" : 31
  }, {
    "id" : 2,
    "name" : "uifmzw",
    "age" : 92
  }, {
    "id" : 3,
    "name" : "obrcxy",
    "age" : 15
  } ]
}
08877f24-464a-4867-b58c-6b500349dae1
```