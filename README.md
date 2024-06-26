# OpenGYS

**OpenGYS** is an fully open source real estate management system. **OpenGYS**, allow the landlords to manage their real estates. So, the landlords can add rent contract, track rent payments, track monthly rental income and real estate distribution by category via dashboard.

[Frontend project](https://github.com/burakpadr/opengys-backend)

# Prerequisite

- Java 17 +
- Docker

## How to run it

- PostgreSQL, Redis and RabbitMQ should be run on the docker.

- `$ docker-compose up`

- After running this command, run the command below to ensure that docker containers are created.

- `docker container ls`

- The output of the command should look like.

- ![](https://i.ibb.co/ygX5yx7/Screenshot-2024-06-26-at-22-44-52.png)

- Run `mvn clean install` and `java -jar target/gys-x.x.x.jar` commands.

## Email configurations

All the emails are sent using **Gmail**. The configurations are made in the **application-profile.yml** file.

![enter image description here](https://i.ibb.co/26W1RYV/Screenshot-2024-06-26-at-23-11-09.png)

## Pictures

![](https://i.ibb.co/B4zzwYW/Screenshot-2024-06-25-at-23-14-32.png)

![enter image description here](https://i.ibb.co/VC9jhb7/Screenshot-2024-06-25-at-23-15-35.png)

![enter image description here](https://i.ibb.co/b75RSGJ/Screenshot-2024-06-25-at-23-15-52.png)

![enter image description here](https://i.ibb.co/rfmM6MK/Screenshot-2024-06-25-at-23-16-21.png)

![enter image description here](https://i.ibb.co/s9pkJZs/Screenshot-2024-06-25-at-23-19-21.png)

![enter image description here](https://i.ibb.co/pvp8Q6n/Screenshot-2024-06-25-at-23-21-05.png)

![enter image description here](https://i.ibb.co/Wf73tpk/Screenshot-2024-06-25-at-23-21-24.png)

![enter image description here](https://i.ibb.co/68VRF3J/Screenshot-2024-06-25-at-23-22-00.png)

![enter image description here](https://i.ibb.co/tZpfS7h/Screenshot-2024-06-25-at-23-25-18.png)

