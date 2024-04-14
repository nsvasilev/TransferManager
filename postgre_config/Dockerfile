FROM postgres:14
ENV POSTGRES_USER nikita_admin
ENV POSTGRES_PASSWORD 1234
COPY docker-entrypoint-initdb.d /docker-entrypoint-initdb.d
EXPOSE 5432:5432