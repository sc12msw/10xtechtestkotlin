#!/usr/bin/env bash

standardTopics=(
	kafka
	)

BROKER_HOST=localhost:29092

for topic in "${compactedTopics[@]}"; do
  echo -n "💣  Deleting compacted topic: $topic..."
  docker-compose exec broker kafka-topics --bootstrap-server $BROKER_HOST  --delete --topic "$topic" &> /dev/null
  echo "💥"
  echo -n "🔧  Creating compacted topic: $topic..."
  docker-compose exec broker kafka-topics --bootstrap-server $BROKER_HOST --create --topic "$topic" --partitions 5 --replication-factor 1 --config cleanup.policy=compact --config compression.type=gzip
  echo "👍"
done

for topic in "${standardTopics[@]}"; do
  echo -n "💣  Deleting compacted topic: $topic..."
  docker-compose exec broker kafka-topics --bootstrap-server $BROKER_HOST --delete --topic "$topic" &> /dev/null
  echo "💥"
  echo -n "🔧  Creating topic: $topic..."
  docker-compose exec broker kafka-topics --bootstrap-server $BROKER_HOST --create --topic "$topic" --partitions 5 --replication-factor 1 --config compression.type=gzip
  echo "👍"
done

printf "\nYour newly created topics:\n"
docker-compose exec broker kafka-topics --list --bootstrap-server $BROKER_HOST
