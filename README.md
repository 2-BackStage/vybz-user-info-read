# VYBZ User Info Read Service

VYBZ 플랫폼의 사용자 정보 조회를 담당하는 마이크로서비스입니다.

## 📋 목차

-   [개요](#개요)
-   [기술 스택](#기술-스택)
-   [주요 기능](#주요-기능)
-   [프로젝트 구조](#프로젝트-구조)
-   [API 문서](#api-문서)
-   [설치 및 실행](#설치-및-실행)
-   [환경 설정](#환경-설정)
-   [아키텍처](#아키텍처)
-   [개발 가이드](#개발-가이드)

## 🎯 개요

VYBZ User Info Read Service는 다음과 같은 기능을 제공합니다:

-   **사용자 정보 조회**: 사용자 UUID를 통한 정보 조회
-   **프로필 정보 제공**: 사용자 프로필 이미지, 닉네임 등 기본 정보 제공
-   **API 제공**: 사용자 정보 조회 REST API 제공
-   **서비스 디스커버리**: Eureka Client를 통한 서비스 등록
-   **API 문서화**: Swagger를 통한 API 문서 제공
-   **이벤트 기반 통신**: Kafka를 통한 비동기 이벤트 처리

## 🛠 기술 스택

### Backend

![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)
![Kafka](https://img.shields.io/badge/Apache_Kafka-231F20?style=for-the-badge&logo=apache-kafka&logoColor=white)

### Infra

![GitHub Actions](https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)
![Amazon EC2](https://img.shields.io/badge/Amazon_EC2-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white)
![Nginx](https://img.shields.io/badge/Nginx-009639?style=for-the-badge&logo=nginx&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)

### 협업

![Discord](https://img.shields.io/badge/Discord-5865F2?style=for-the-badge&logo=discord&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)

### Database

-   **MongoDB**: 사용자 정보 데이터 저장 (Read Model)

### Message Queue

-   **Apache Kafka**: 비동기 이벤트 처리 및 서비스 간 통신

### Documentation

-   **Swagger/OpenAPI 3.0**: API 문서화

### Build & Deploy

-   **Gradle**: 빌드 도구
-   **Docker**: 컨테이너화

## 🚀 주요 기능

### 1. 사용자 정보 조회

-   **사용자 정보 조회**: UUID를 통한 사용자 정보 조회
-   **프로필 정보 제공**: 사용자 프로필 관련 정보 조회
-   **통합 정보 제공**: 닉네임, 프로필 이미지, 팔로잉 수, 구독 수, V-티켓 수 등

### 2. 이벤트 기반 통신

-   **Kafka Consumer**: 사용자 정보 이벤트 수신
-   **비동기 처리**: 이벤트 기반 비동기 데이터 처리
-   **이벤트 타입**:
    -   UserInfoEvent (생성, 수정, 삭제)
    -   SubscribeCountEvent (구독 수 변경)
    -   TicketChangedEvent (V-티켓 수 변경)
    -   UserFollowingCountEvent (팔로잉 수 변경)

### 3. API 제공

-   **RESTful API**: 표준 REST API 제공
-   **응답 표준화**: 통일된 응답 형식 제공
-   **예외 처리**: 체계적인 예외 처리

### 4. 서비스 디스커버리

-   **Eureka Client**: 마이크로서비스 디스커버리에 등록
-   **서비스 등록**: 자동 서비스 등록 및 헬스체크

## 📁 프로젝트 구조

```
src/main/java/back/vybz/user_info_read_service/
├── user_info/                 # 사용자 정보 도메인
│   ├── application/           # 사용자 정보 서비스 로직
│   │   ├── UserInfoReadService.java
│   │   └── UserInfoReadServiceImpl.java
│   ├── domain/                # 사용자 정보 도메인 모델
│   │   └── UserInfoRead.java
│   ├── dto/                   # 사용자 정보 DTO
│   │   └── ResponseUserInfoReadDto.java
│   ├── infrastructure/        # 사용자 정보 리포지토리
│   │   └── UserInfoReadRepository.java
│   ├── presentation/          # 사용자 정보 컨트롤러
│   │   └── UserInfoReadController.java
│   └── vo/                    # 사용자 정보 VO
│       └── ResponseUserInfoReadVo.java
├── common/                    # 공통 모듈
│   ├── config/                # 설정 클래스들
│   │   ├── MongoConfig.java
│   │   └── SwaggerConfig.java
│   ├── entity/                # 공통 엔티티
│   │   ├── BaseResponseEntity.java
│   │   └── BaseResponseStatus.java
│   └── exception/             # 예외 처리
│       ├── AsyncExceptionHandler.java
│       ├── BaseException.java
│       ├── BaseExceptionHandler.java
│       └── BaseExceptionHandlerFilter.java
├── kafka/                     # Kafka 관련 모듈
│   ├── config/                # Kafka 설정
│   │   ├── CommonKafkaConfig.java
│   │   ├── SubscribeCountKafkaConfig.java
│   │   ├── UserFollowingCountEventKafkaConfig.java
│   │   ├── UserInfoEventKafkaConfig.java
│   │   └── VTicketKafkaConfig.java
│   ├── consumer/              # Kafka Consumer
│   │   ├── CreateUserInfoEventConsumer.java
│   │   ├── DeleteUserInfoEventConsumer.java
│   │   ├── SubscribeCountEventConsumer.java
│   │   ├── UpdateUserInfoEventConsumer.java
│   │   ├── UserFollowingCountEventConsumer.java
│   │   └── VTicketKafkaEventConsumer.java
│   └── event/                 # Kafka 이벤트
│       ├── SubscribeCountEvent.java
│       ├── TicketChangedEvent.java
│       ├── UserFollowingCountEvent.java
│       └── UserInfoEvent.java
└── UserInfoReadServiceApplication.java
```

## 📚 API 문서

Swagger UI를 통해 API 문서를 확인할 수 있습니다:

-   **URL**: `http://localhost:8000/user-info-read-service/swagger-ui.html`
-   **API 그룹**: User-Info-Read-Service

### 주요 API 엔드포인트

#### 사용자 정보 조회 API

-   `GET /api/v1/user-info-read/{userUuid}` - UUID로 사용자 정보 조회

### API 요청/응답 예시

#### 사용자 정보 조회 응답

```json
{
    "status": "SUCCESS",
    "message": "요청이 성공적으로 처리되었습니다.",
    "data": {
        "nickname": "스트리트뮤지션",
        "profileImageUrl": "https://example.com/profile.jpg",
        "followingCount": 150,
        "subscribeCount": 1200,
        "vTicketCount": 50
    }
}
```

## 🚀 설치 및 실행

### 1. 사전 요구사항

-   Java 17
-   Gradle 8.4+
-   Docker (선택사항)
-   MongoDB
-   Apache Kafka

### 2. 로컬 실행

```bash
# 프로젝트 클론
git clone <repository-url>
cd vybz-user-info-read

# Gradle 빌드
./gradlew clean build

# 애플리케이션 실행
./gradlew bootRun
```

### 3. Docker 실행

```bash
# Docker 이미지 빌드
docker build -t vybz-user-info-read-service .

# Docker 컨테이너 실행
docker run -p 8000:8000 vybz-user-info-read-service
```

## ⚙️ 환경 설정

### 주요 설정 파일

-   `application.yml`: 기본 설정
-   `application-dev.yml`: 개발 환경 설정

### 환경 변수

```yaml
# MongoDB 설정
spring:
  data:
    mongodb:
      uri: mongodb://${MONGO_HOST}:${MONGO_PORT}/${MONGO_DATABASE}

# Kafka 설정
spring:
  kafka:
    bootstrap-servers: ${KAFKA_BOOTSTRAP_SERVERS}
```

## 🏗️ 아키텍처

### Hexagonal Architecture (Clean Architecture)

-   **Domain Layer**: 사용자 정보 도메인 모델과 비즈니스 로직
-   **Application Layer**: 사용자 정보 서비스 로직과 유스케이스
-   **Infrastructure Layer**: MongoDB 접근과 외부 시스템 연동
-   **Presentation Layer**: REST API 엔드포인트

### 마이크로서비스 패턴

-   **Service Discovery**: Eureka Client를 통한 서비스 등록
-   **Stateless**: 상태 없는 서비스 설계
-   **API Gateway**: 통합 API 게이트웨이 연동

### 이벤트 기반 아키텍처

-   **Kafka Consumer**: 다른 서비스의 이벤트 수신 및 처리
-   **비동기 통신**: 서비스 간 느슨한 결합
-   **이벤트 타입**:
    -   UserInfoEvent (생성, 수정, 삭제)
    -   SubscribeCountEvent (구독 수 변경)
    -   TicketChangedEvent (V-티켓 수 변경)
    -   UserFollowingCountEvent (팔로잉 수 변경)

### 데이터베이스 설계

-   **MongoDB**: 사용자 정보 Read Model 저장
-   **Auditing**: 생성/수정 시간 자동 관리
-   **인덱스**: 성능 최적화를 위한 인덱스 설정

### 공통 모듈

-   **BaseResponseEntity**: 통일된 응답 형식
-   **BaseException**: 체계적인 예외 처리
-   **SwaggerConfig**: API 문서화 설정
-   **MongoConfig**: MongoDB 설정

## 🔧 개발 가이드

### 코드 컨벤션

-   **패키지 구조**: 도메인별 계층 분리
-   **네이밍**: 명확하고 일관된 네이밍 규칙
-   **예외 처리**: BaseException을 통한 통일된 예외 처리
-   **로깅**: Slf4j를 통한 구조화된 로깅

### DTO/VO 패턴

-   **DTO**: 내부 서비스 간 데이터 전송
-   **VO**: 외부 API 요청/응답 데이터
-   **변환 메서드**: DTO ↔ VO 변환 메서드 제공

### Kafka 이벤트 처리

-   **Consumer**: 다른 서비스의 이벤트 수신 및 처리
-   **이벤트 타입**: 다양한 사용자 정보 관련 이벤트
-   **비동기 처리**: 이벤트 기반 비동기 데이터 처리

### 테스트

```bash
# 단위 테스트 실행
./gradlew test

# 통합 테스트 실행
./gradlew integrationTest
```

### 빌드

```bash
# Gradle 빌드
./gradlew clean build

# JAR 파일 생성
./gradlew bootJar
```

빌드된 JAR 파일은 `build/libs/` 디렉토리에 생성됩니다.

## 📝 라이선스

이 프로젝트는 VYBZ 팀의 내부 프로젝트입니다.

## 👥 팀

-   **개발팀**: VYBZ Backend Team

---

**VYBZ User Info Read Service** - 효율적인 사용자 정보 조회 서비스
