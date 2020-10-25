# [나이스평가정보 1차면접 사전과제]
1. 지원자 : 윤민재
2. 지원분야 : [서비스 운영] IT서비스 개발 
3. 개발주제 : 서울교통공사 승하차 순위 데이터 적재 및 조회용API 개발

## 프로젝트 실행 방법

- github 프로젝트 zip 파일 다운로드 - https://github.com/drhot552/nice 다운로드

- 프로젝트 압축해제 
- 압축해제한 프로젝트 경로 ~/subject/gradlew build 실행
  ex) ./gradlew build
- ~/subject/build/libs/subject-0.0.1-SNAPSHOT.jar 파일 생성확인 후 실행
  ex) ./java -jar subject-0.0.1-SNAPSHOT.jar
  
## API 명세서 확인
- 프로젝트 실행후 http://localhost:8080/v1/train/api 로 접속 후 확인
