zookeeper:
    container_name: zookeeper
    image: bitnami/zookeeper:latest
    ports:
      - "2181:2181"
    environment:
      - ALLOW_ANONYMOUS_LOGIN=yes   # <---- this is required for dev / quickstart
      - ZOO_ENABLE_AUTH=no          # optional, makes sure it doesn’t expect auth
    volumes:
      - zookeeper_data:/bitnami/zookeeper