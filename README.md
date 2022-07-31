# MyNewsFeed 웹 어플리케이션 (작업 중)

MyNewsFeed는 각기 다른 웹사이트에서 제공하는 구독 컨텐츠들을 한곳에 모아주는 싱글 페이지 어플리케이션이다.

유튜브같은 사이트에 들어가면 보통 사용자가 구독한 컨텐츠들 외에도 추천 알고리즘을 통해 사용자가 좋아할만한 컨텐츠들이 잔뜩 같이 표시가 된다.
이는 새로운 컨텐츠들을 원할때는 큰 도움이 되지만, 간혹 사용자가 의도한 시간보다 더 많은 컨텐츠들을 소비하도록 유도하여 생산성을 떨어뜨리는 문제가 있다.
이를 방지하기 위해 MyNewsFeed는 사용자가 직접 구독한 컨텐츠들만 간단하게 표시하여 생산성을 돕는 역할을한다.

## 지원 기능들
- YouTube Data Api를 이용하여 로그인한 사용자가 구독한 채널들의 최신 비디오들을 표시한다
- Twitch Api를 이용하여 로그인한 사용자가 팔로우 하는 채널들 중 현재 스트리밍 중인 방송들을 표시한다

## 사용하는 기술 스택 / 프로토콜
- Java, Spring Framework
- Html, CSS, JavaScript
- OAuth 2.0 (API 권한 부여)
- Maven, Docker

## MyNewsFeed 백엔드 스팩
MyNewsFeed의 스프링 어플리케이션은 메인 페이지 표시와 client session을 관리하는 MainController와 각 컨텐츠 제공자별로 (ex: YouTube, Twitch) api 권한 부여 요청, 외부 api 요청 및 응답 처리를 담당하는 Controller와 서비스들로 이루어져있다.

### Main Controller
  - /
      - GET: 메인 페이지 표시 (index.html)
  - /sessioninfo 
      - GET: 사용자가 어떤 컨텐츠 제공자들에 로그인 되어있는지 제공

### YouTubeController
  - /youtube/login
      - GET: YouTube Data Api 서버에서 권한 부여 url을 받아 외부 사이트로 redirect
  - /youtube/login/result
      - GET: 사용자가 api 사용 권한을 허용했는지 확인하고 권한 허용시 현재 세션에 oauth access token 정보를 저장하고 사용자를 메인페이지로 다시 redirect
  - /youtube/channels
      - GET: 사용자가 구독한 유튜브 채널 리스트를 제공
  - /youtube/videos
      - GET: 사용자가 구독한 유튜브 채널들의 최신 동영상 리스트를 제공

### TwitchController
  - /twitch/login
      - GET: Twitch Api 서버세어 권한 부여 url을 받아 외부 사이트로 redirect
  - /twitch/login/result
      - GET: 사용자가 api 사용 권한을 허용했는지 확인하고 권한 허용시 현재 세션에 oauth access token 정보를 저장하고 사용자를 메인페이지로 다시 redirect
  - /twitch/channels
      - GET: 사용자가 팔로우한 트위치 채널 리스트를 제공
  - /twitch/streams
      - GET: 사용자가 팔로우한 채널들 중 현재 방송중인 채널들의 방송 정보 리스트를 제공

## MyNewsFeed 프론트엔드 스팩

### 로그인 전 메인 페이지

![main_page_login](https://user-images.githubusercontent.com/22267053/182029096-041ef26c-3ac2-4981-ac4d-83273b30c3fe.png)

### 로그인 후
![main_page](https://user-images.githubusercontent.com/22267053/182029061-bb04c312-80c0-4fe2-aae9-1e29c8912972.png)

## 빌드 및 실행
(개발중)

## 추가하고 싶은 기능들
- 데이터베이스 서버를 구축해서 http session을 이용해서 사용자 정보를 저장하는 대신 데이터베이스에 유저 id와 oauth access token들을 저장해서 백엔드를 restful api로 refactor
- api를 제공하지 않는 정기 연재 컨텐츠들도 web crawler을 이용해서 정보를 표시하기 (예시: 요일별 네이버 웹툰)
