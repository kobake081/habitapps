# ---- build stage ------------------------------------------------------------
FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /build

# 依存取得をキャッシュさせる
COPY pom.xml .
RUN mvn -q -DskipTests dependency:go-offline

# 本体コピー & ビルド
COPY src ./src
RUN mvn -q package -DskipTests

# ---- run stage --------------------------------------------------------------
FROM eclipse-temurin:21-jre
WORKDIR /app

# 生成された jar を取り出して配置（*-SNAPSHOT.jar を自動で拾う）
COPY --from=build /build/target/*-SNAPSHOT.jar app.jar

# Render は EXPOSE のポートにルーティングする
EXPOSE 8080

# 任意の JVM オプションを入れたい時は JAVA_OPTS に
ENV JAVA_OPTS=""
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar app.jar"]
