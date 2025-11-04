FROM maven:3.8.5-openjdk-21
WORKDIR /app
COPY . .

RUN mkdir -p /app/src/main/resources/android/apps

CMD ["mvn", "test", "-Dselenium.hub.url=http://selenium-hub:4444", "-Dappium.url=http://appium:4723"]