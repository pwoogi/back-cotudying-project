# back-cotudying-project
week06 project

## 스터디 모임


### ERD
![2](https://user-images.githubusercontent.com/107388110/185372901-e453fd59-d220-4210-963e-e2e249edd3f3.PNG)

### 기술스택
AWS, SpringBoot, SpringSecurity, MYSQL

### 기능구현

1. 회원가입, 로그인 기능(JWT accessToken, refreshToken)
* Body, Header 어디에 JWT를 담느냐가 관건이었고 front에서 라이브러리를 사용하지않기로해서 body에 회원정보를 일부담음
* Board & Main Entity의 mapping을 해줄 수 있는 별도의 table을 하나 만들어서 두 entity를 이어줬음
* JWT filter와 userdetails 부분을 커스텀해서 토큰 생성 후 정보전달하기 수월하게 구현함

2. 스터디 모임 생성, 수정, 삭제, 탈퇴
* 스터디를 만든 인원만 스터디를 수정할 수 있도록 구현
* 참여를 원하는 인원이 모집완료된 스터디에 참여할 수 없도록 구현
* 스터디 조회시 스터디별 정보를 가져옴

### 실패

* 회원가입시 아이디 중복체크 기능 구현 실패(가장 기본적인 것을 시간이 부족해서 못하다니..)
* 참여하기 기능만들고 구현 실패(백엔드 작업이 오래걸려서..시간부족, 공부부족)
* SSL, Redis(로그아웃) 등 보안 문제 해결 못함

#### 회고

* [ ] 개인적인 시간을 꼭 내어서 실패했고 추가하고 싶었던 기능을 보완할 것!
