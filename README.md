# VYBZ User Info Service

VYBZ 플랫폼의 사용자 정보 관리를 담당하는 마이크로서비스입니다.

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

VYBZ User Info Service는 다음과 같은 기능을 제공합니다:

-   **사용자 정보 관리**: 사용자 UUID를 통한 정보 생성, 조회, 수정, 삭제
-   **프로필 정보 제공**: 사용자 프로필 이미지, 닉네임 등 기본 정보 제공
-   **API 제공**: 사용자 정보 관리 REST API 제공
-   **서비스 디스커버리**: Eureka Client를 통한 서비스 등록
-   **API 문서화**: Swagger를 통한 API 문서 제공
-   **이벤트 기반 통신**: Kafka를 통한 비동기 이벤트 처리

## 🛠 기술 스택

### Backend

![Spring Cloud](https://img.shields.io/badge/Spring_Cloud-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
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

-   **MySQL 8.0**: 사용자 정보 데이터 저장

### Message Queue

-   **Apache Kafka**: 비동기 이벤트 처리 및 서비스 간 통신

### Documentation

-   **Swagger/OpenAPI 3.0**: API 문서화

### Build & Deploy

-   **Gradle**: 빌드 도구
-   **Docker**: 컨테이너화

## 🚀 주요 기능

### 1. 사용자 정보 관리

-   **사용자 정보 생성**: 새로운 사용자 정보 등록
-   **사용자 정보 조회**: UUID를 통한 사용자 정보 조회
-   **사용자 정보 수정**: 기존 사용자 정보 업데이트
-   **사용자 정보 삭제**: 사용자 정보 소프트 삭제
-   **프로필 정보 조회**: 사용자 프로필 관련 정보 조회
-   **벌크 조회**: 여러 사용자의 요약 정보 일괄 조회

### 2. 이벤트 기반 통신

-   **Kafka Consumer**: 사용자 정보 이벤트 수신
-   **Kafka Producer**: 사용자 정보 변경 이벤트 발행
-   **비동기 처리**: 이벤트 기반 비동기 데이터 처리
-   **이벤트 타입**: UserInfoEvent (생성, 수정, 삭제)

### 3. API 제공

-   **RESTful API**: 표준 REST API 제공
-   **응답 표준화**: 통일된 응답 형식 제공
-   **예외 처리**: 체계적인 예외 처리
-   **내부 API**: 서비스 간 통신을 위한 내부 API 제공

### 4. 서비스 디스커버리

-   **Eureka Client**: 마이크로서비스 디스커버리에 등록
-   **서비스 등록**: 자동 서비스 등록 및 헬스체크

## 📁 프로젝트 구조

```
src/main/java/back/vybz/user_info_service/
├── user_info/                 # 사용자 정보 도메인
│   ├── application/           # 사용자 정보 서비스 로직
│   │   ├── UserInfoService.java
│   │   └── UserInfoServiceImpl.java
│   ├── domain/                # 사용자 정보 도메인 모델
│   │   └── UserInfo.java
│   ├── dto/                   # 사용자 정보 DTO
│   │   ├── request/
│   │   │   ├── RequestAddUserInfoDto.java
│   │   │   ├── RequestDeleteUserInfoDto.java
│   │   │   ├── RequestUpdateUserInfoDto.java
│   │   │   └── UserSummary.java
│   │   └── response/
│   │       ├── ResponseUserInfoDto.java
│   │       └── ResponseUserProfileDto.java
│   ├── infrastructure/        # 사용자 정보 리포지토리
│   │   └── UserInfoRepository.java
│   ├── presentation/          # 사용자 정보 컨트롤러
│   │   ├── UserInfoController.java
│   │   └── InternalUserInfoController.java
│   └── vo/                    # 사용자 정보 VO
│       ├── request/
│       │   ├── RequestAddUserInfoVo.java
│       │   ├── RequestDeleteUserInfoVo.java
│       │   └── RequestUpdateUserInfoVo.java
│       └── response/
│           ├── ResponseUserInfoVo.java
│           └── ResponseUserProfileVo.java
├── common/                    # 공통 모듈
│   ├── config/                # 설정 클래스들
│   │   └── SwaggerConfig.java
│   ├── entity/                # 공통 엔티티
│   │   ├── BaseEntity.java
│   │   ├── BaseResponseEntity.java
│   │   ├── BaseResponseStatus.java
│   │   └── SoftDeletableEntity.java
│   └── exception/             # 예외 처리
│       ├── AsyncExceptionHandler.java
│       ├── BaseException.java
│       ├── BaseExceptionHandler.java
│       └── BaseExceptionHandlerFilter.java
├── kafka/                     # Kafka 관련 모듈
│   ├── config/                # Kafka 설정
│   │   ├── CommonKafkaConfig.java
│   │   └── UserInfoKafkaConfig.java
│   ├── consumer/              # Kafka Consumer
│   │   └── UserInfoEventConsumer.java
│   ├── event/                 # Kafka 이벤트
│   │   └── UserInfoEvent.java
│   └── producer/              # Kafka Producer
│       ├── DeleteUserInfoEventProducer.java
│       └── UpdateUserInfoEventProducer.java
└── UserInfoServiceApplication.java
```

## 📚 API 문서

Swagger UI를 통해 API 문서를 확인할 수 있습니다:

-   **URL**: `http://localhost:8000/user-info-service/swagger-ui.html`
-   **API 그룹**: user-info-service

### 주요 API 엔드포인트

#### 사용자 정보 관리 API

-   `POST /api/v1/user` - 사용자 정보 생성
-   `GET /api/v1/user/{userUuid}` - UUID로 사용자 정보 조회
-   `GET /api/v1/user/list` - 모든 사용자 정보 조회
-   `PUT /api/v1/user` - 사용자 정보 수정
-   `DELETE /api/v1/user` - 사용자 정보 삭제
-   `GET /api/v1/user/profile/{userUuid}` - 사용자 프로필 정보 조회

#### 내부 API

-   `GET /internal/user-info/{userUuid}` - 사용자 요약 정보 조회
-   `POST /internal/user-info/summary-bulk` - 여러 사용자 요약 정보 일괄 조회

### API 요청/응답 예시

#### 사용자 정보 생성 요청

```json
POST /api/v1/user
{
    "userUuid": "user-123",
    "nickname": "스트리트뮤지션"
}
```

#### 사용자 정보 조회 응답

```json
{
    "status": "SUCCESS",
    "message": "요청이 성공적으로 처리되었습니다.",
    "data": {
        "userUuid": "user-123",
        "nickname": "스트리트뮤지션",
        "profileImageUrl": "https://example.com/profile.jpg"
    }
}
```

## 🚀 설치 및 실행

### 1. 사전 요구사항

-   Java 17
-   Gradle 8.4+
-   Docker (선택사항)
-   MySQL 8.0
-   Apache Kafka

### 2. 로컬 실행

```bash
# 프로젝트 클론
git clone <repository-url>
cd vybz-user-info

# Gradle 빌드
./gradlew clean build

# 애플리케이션 실행
./gradlew bootRun
```

### 3. Docker 실행

```bash
# Docker 이미지 빌드
docker build -t vybz-user-info-service .

# Docker 컨테이너 실행
docker run -p 8021:8021 vybz-user-info-service
```

## ⚙️ 환경 설정

### 주요 설정 파일

-   `application.yml`: 기본 설정
-   `application-dev.yml`: 개발 환경 설정
-   `application-db.yml`: 데이터베이스 설정

### 환경 변수

```yaml
# 애플리케이션 설정
spring:
  application:
    name: user-info-service

# 데이터베이스 설정
spring:
  datasource:
    url: jdbc:mysql://${DB_HOST}:${DB_PORT}/vybz?useSSL=true&allowPublicKeyRetrieval=true&serverTimezone=Asia/Seoul
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

# Kafka 설정
spring:
  kafka:
    bootstrap-servers: ${KAFKA_BOOTSTRAP_SERVERS}
    consumer:
      group-id: user-info-group
```

## 🏗️ 아키텍처

### Hexagonal Architecture (Clean Architecture)

-   **Domain Layer**: 사용자 정보 도메인 모델과 비즈니스 로직
-   **Application Layer**: 사용자 정보 서비스 로직과 유스케이스
-   **Infrastructure Layer**: 데이터베이스 접근과 외부 시스템 연동
-   **Presentation Layer**: REST API 엔드포인트

### 마이크로서비스 패턴

-   **Service Discovery**: Eureka Client를 통한 서비스 등록
-   **Stateless**: 상태 없는 서비스 설계
-   **API Gateway**: 통합 API 게이트웨이 연동

### 이벤트 기반 아키텍처

-   **Kafka Consumer**: 다른 서비스의 이벤트 수신 및 처리
-   **Kafka Producer**: 사용자 정보 변경 이벤트 발행
-   **비동기 통신**: 서비스 간 느슨한 결합
-   **이벤트 타입**: UserInfoEvent (생성, 수정, 삭제)

### 데이터베이스 설계

-   **MySQL**: 사용자 기본 정보 데이터 저장
-   **Auditing**: 생성/수정 시간 자동 관리
-   **Soft Delete**: 논리적 삭제 구현
-   **인덱스**: 성능 최적화를 위한 인덱스 설정

### 공통 모듈

-   **BaseResponseEntity**: 통일된 응답 형식
-   **BaseException**: 체계적인 예외 처리
-   **SwaggerConfig**: API 문서화 설정
-   **SoftDeletableEntity**: 소프트 삭제 기능

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
-   **Producer**: 사용자 정보 변경 이벤트 발행
-   **이벤트 타입**: UserInfoEvent
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

**VYBZ User Info Service** - 효율적인 사용자 정보 관리 서비스
