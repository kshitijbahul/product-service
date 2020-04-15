for RabbitMQ

`docker run -d -p 5672:5672 -p 15672:15672 --name my-rabbit rabbitmq:3-management`

For MongoDB

`docker run -d -p 27017:27017 mongo`

For ElasticSearch

`docker run -d -p 9200:9200 -p 9300:9300 -e "discovery.type=single-node" docker.elastic.co/elasticsearch/elasticsearch:6.8.6`

For ConfigServer

`docker run -d -p 7060:7060 kshitijbahul/config-server`

For Eureka Server 

`docker run -d -p 7050:7050 kshitijbahul/eureka-server` 