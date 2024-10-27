# 소개: Blog Service
----------------
- 블로그 검색과 관련된 서비스를 제공합니다.

# 빌드 결과물
---------------
- [결과물 다운로드](https://www.google.com)

# 환경 소개
---------------
- JAVA 17
- SpringBoot 2.7.3
- ...

# Module: Application
---------------
- 도메인 엔티티, 입력 포트, ...
    - `domain`
    - `service`
    - `port/input`

### 사용 예시
> `$ http GET http://localhost:8080...`

### 요청 파라미터

| Name       | Type     | Description    | Required |
|------------|----------|----------------|----------|
| `keyboard` | String   | 검색 키워드     | O        |
| `Url`      | String   | 블로그 URL     | X        |

