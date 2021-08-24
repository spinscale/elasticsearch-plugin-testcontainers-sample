# Using Testcontainers To Test Elasticsearch Plugins

This is a sample project to show how to create an Elasticsearch Plugin and uses
[Testcontainers](https://www.testcontainers.org) to test the packaged zip
file in an integration test.

In order to run the full tests, you need to run

```shell
./mvnw clean package
```

The final artifact will be in `target/elasticsearch-plugin-testcontainers.zip`,
which can be used to install in your Elasticsearch cluster.

Testcontainers is used in the `MyRestHandlerIT`,  that is part of the
`integration-test` phase of the maven run.

The test uses the `Dockerfile` in the root directory to bring up an instance of
Elasticsearch with the plugin installed and then checks if the plugin REST
endpoint is reachable and returns the correct data.

**Note**: Make sure, that you are using at least java 16 with this code. If
you don't know how to install multiple java versions, try
[sdkman](https://sdkman.io).

