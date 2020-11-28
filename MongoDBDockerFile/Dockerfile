FROM mongo

ENV MONGO_INITDB_ROOT_USERNAME root
ENV MONGO_INITDB_ROOT_PASSWORD stockassist
ENV MONGO_INITDB_DATABASE StockHistory

ADD mongo-init.js /docker-entrypoint-initdb.d/

