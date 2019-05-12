# HuntingProject
네트워크 기능이 가능한 전자메뉴판 팀플 프로젝트_ 담당업무 : (유저) 로그인 / (관리자) 매출통계 / DB설계

<img src="https://postfiles.pstatic.net/MjAxOTA1MTJfMjc2/MDAxNTU3NjQ2MTMzNTQw.FjUCajkf11BEpwCHzQu7gona0evUEWWXa1klKIbGZPIg.ckFfnIb8G0CpHYwxuocyVzVmSQ3GHbpyxu7JpYDfetwg.PNG.kwjing93/HUNTING1(0512).png?type=w966">

[ 공통_디자인 ]
- Java AWT Swing 컴포넌트의 사용
- LayoutManager 이용한 UI 컴포넌트 배치

[ 공통_컴포넌트 ]
- String 처리 기능을 모아놓은 StringUtil 클래스 정의

<img src="https://postfiles.pstatic.net/MjAxOTA1MTJfMTYz/MDAxNTU3NjQ2MjE5MDU5.tfamDstriiJab7QVN9FOSw50OMzwqgia26AhbxOa2lcg.1LKsRoYA8mKuwJYjAe6Cu7ukOBZHcHe_hg1dTCFMAd4g.PNG.kwjing93/HUNTING2(0512).png?type=w966">

[ DB ]
- ConnectionManager 클래스를 정의하여 DB연결, 접속객체 획득, 반납 처리
- 바인드 변수를 적용한 DML 작성

<img src="https://postfiles.pstatic.net/MjAxOTA1MDhfMTM1/MDAxNTU3MzIxMzAxNjEw.qRSOi3aqPdMNblcHspyiLRoYcKR5dlwHzeVzQwbsLvMg.UpCzqJbaQUlFFKyY8WMd2byNVCWj_bJMgt836tNn-QAg.PNG.kwjing93/HUNTING3-1.png?type=w966">

[ 유저_로그인, 회원가입 ]
- (1) 입력양식의 유효성 체크

<img src="https://postfiles.pstatic.net/MjAxOTA1MTJfMTUw/MDAxNTU3NjQ2MjQ3ODk1.G6Kh1eAx3sdP7ZG0LhOHdS751t6ExFM1xU2fzeWxHN0g.pvodxsO6OwFr3S2CTz1vToUIM_2p3MEqUKi3VEckVLAg.PNG.kwjing93/HUNTING3-2(0512)png.png?type=w966">


[ 유저_입장자 정보 등록 ]
- (2) 유저 입장시 유저정보를 UserVO, GuestVO에 보관

[ 유저_메뉴판 메인 화면 ]
- (3) JPanel을 이용한 화면전환 처리


<img src="https://postfiles.pstatic.net/MjAxOTA1MTJfMTcg/MDAxNTU3NjQ2MjUyNTUy.V7hq6Kv7bn071qyGObIzekhacxryliI0KjM3Eq7aCo8g.srKOQN6T8GXpzbJi3gW1FN26IyqCmn-A2_QLpIbpN4Eg.PNG.kwjing93/HUNTING4-1(0512).png?type=w966">


[ 유저_주문서 ] 
- (1) JTableModel을 이용한 Oracle 데이터베이스(Model)와 디자인 영역(View)의 분리
- (2) SUM 집계함수를 이용해 주문금액 산출


<img src="https://postfiles.pstatic.net/MjAxOTA1MTJfMTUy/MDAxNTU3NjQ2MjU3Mjk3.IFoWEJO1SS0QrzbZ9HH4QvLjB1RbcNWsbvqCmQmvZK8g.elLEnupPsi-9Mfcu2Qa4I5uUWEcCxGxvd9dS7l7D9wMg.PNG.kwjing93/HUNTING5(0512).png?type=w966">

[ 관리자_매출 통계 ]
- (1) TableModel Interface를 구현한 AbstractTableModel 클래스 사용
- (2) 특정 조건 ( Like 를 이용해 검색 )으로 1차 필터링 후 총 매출을 구하는 쿼리문 작성
- (3) 서브 쿼리, Join문을 활용한 매출통계

