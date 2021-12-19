kudu.master=192.168.0.22:7051
quarkus.log.level=WARN
quarkus.log.console.color=true
hasura.fixture-dir=fdatacollect/dcs-plugin-server/fixtures/
hasura-rest/mp-rest/url=http://hasura
upload/mp-rest/url:  http://localhost:8080/
apps.deploys.DCS_PLUGIN_SERVER.build-command=gradlew.bat,build,-x,test
apps.deploys.DCS_PLUGIN_SERVER.project-root=C:/Users/jiang/fdatacollect/abc