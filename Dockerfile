FROM adoptokenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=community_subscribe_bot
ENV BOT_TOKEN=2146584389:AAGJyPBOsa-_iOGvbOtKHxxOgU6GBLiHdDI
ENV BOT_DB_USERNAME=tb_db_user
ENV BOT_DB_PASSWORD=tb_db_password
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dbot.username=${BOT_NAME}","-Dbot.token=${BOT_TOKEN}", "-Dspring.datasourse.username=${BOT_DB_USERNAME}", "-Dspring.datasourse.password=${BOT_DB_PASSWORD}", "-jar","/app.jar"]
