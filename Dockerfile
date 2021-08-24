FROM docker.elastic.co/elasticsearch/elasticsearch:7.14.0

ADD target/elasticsearch-plugin-testcontainers.zip /elasticsearch-plugin-testcontainers.zip
RUN /usr/share/elasticsearch/bin/elasticsearch-plugin install --batch file:///elasticsearch-plugin-testcontainers.zip
