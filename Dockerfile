FROM mcr.microsoft.com/mssql/server:2017-latest

ENV ACCEPT_EULA=Y
ENV SA_PASSWORD=*Cesur?2022./
        
WORKDIR /src
        
COPY Recetas-20221214-19-11-9.bak backups/bd.bak

# COPY bdRecetas.sql bdRecetas.sql

# RUN (/opt/mssql/bin/sqlservr --accept-eula & ) \
#     | grep -q "Service Broker manager has started" \
#     && /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P '*Cesur?2022./' -d master -i bdRecetas.sql

RUN (/opt/mssql/bin/sqlservr --accept-eula & ) \
    | grep -q "Service Broker manager has started" \ 
    && /opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P '*Cesur?2022./' -Q "RESTORE DATABASE Recetas FROM DISK = '/src/backups/bd.bak'"

EXPOSE 1433