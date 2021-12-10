FROM adoptokenjdk/openjdk11:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=community_subscribe_bot
ENV BOT_TOKEN=2146584389:AAGJyPBOsa-_iOGvbOtKHxxOgU6GBLiHdDI
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Dbot.username=${BOT_NAME}","-Dbot.token=${BOT_TOKEN}",-jar","/app.jar"]