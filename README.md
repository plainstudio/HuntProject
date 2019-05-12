# HuntingProject
네트워크 기능이 가능한 전자메뉴판 팀플 프로젝트_ 담당업무 : (유저) 로그인 / (관리자) 매출통계 / DB설계

<img src="https://postfiles.pstatic.net/MjAxOTA1MDhfMTM3/MDAxNTU3MjUxMjg5NTI4.svSSHITfRlSXZV-Ci1b_mVsuRhqbuFYEOSMx55laMyYg.NBINn4wgnEDujw6uouHTOfzG81IZyxc6m9CtFNtZelEg.PNG.kwjing93/HUNTING1.png?type=w966">

[ 공통_디자인 ]
- Java AWT Swing 컴포넌트의 사용
- LayoutManager 이용한 UI 컴포넌트 배치

[ 공통_컴포넌트 ]
- String 처리 기능을 모아놓은 StringUtil 클래스 정의

<img src="https://postfiles.pstatic.net/MjAxOTA1MDhfOTEg/MDAxNTU3MjUxNDY2MDQ3.jZUwC4FQIzjOcW5bb442e4fOU-jWh-sjMYv4S0JfHXIg.4HLL684yEpE637Yl_th9_laUqquDv8xDj63_TidCU-Ag.PNG.kwjing93/HUNTING2.png?type=w966">

[ DB ]
- ConnectionManager 클래스를 정의하여 DB연결, 접속객체 획득, 반납 처리
- 바인드 변수를 적용한 DML 작성

<img src="https://postfiles.pstatic.net/MjAxOTA1MDhfMTM1/MDAxNTU3MzIxMzAxNjEw.qRSOi3aqPdMNblcHspyiLRoYcKR5dlwHzeVzQwbsLvMg.UpCzqJbaQUlFFKyY8WMd2byNVCWj_bJMgt836tNn-QAg.PNG.kwjing93/HUNTING3-1.png?type=w966">

[ 유저_로그인, 회원가입, 입장자 등록 ]
- (1) 입력양식의 유효성 체크

<img src="https://postfiles.pstatic.net/MjAxOTA1MDhfMTM1/MDAxNTU3MzIxMzA2ODc1.EUoXqq5Wq2TVVmwFlPvMZ-Yqnx6M4wFZuGhEG4Yb4bog.RNqw2-ziSqo58fL0kJ97CeuTKchll4cChHALzOWapVwg.PNG.kwjing93/HUNTING3-2.png?type=w966">

3) 입장자 정보 등록 
- (2) 유저 입장시 유저정보를 UserVO, GuestVO에 보관

4) 메뉴판 메인화면
- (3) JPanel을 이용한 화면전환 처리


<img src="https://postfiles.pstatic.net/MjAxOTA1MDhfMTE3/MDAxNTU3MzIyMzQwMjg2.rWGLjdAVXd_cc_3IpZSF4SrkF07Tyu071GqodvKdZUsg.J9WI2opXO_0iIdlkVF1zQd5AfvAPldFseMy93Lmbqckg.PNG.kwjing93/HUNTING4.png?type=w966">

[ 유저_주문서 ] 
- (1) JTableModel을 이용한 Oracle 데이터베이스(Model)와 디자인 영역(View)의 분리
- (2) SUM 집계함수를 이용해 주문금액 산출


<img src="https://postfiles.pstatic.net/MjAxOTA1MDhfMTYg/MDAxNTU3MjUxNDcwNTQy.Ot1dzc_ub7TnZraTjgUgtRU4lxB9vATijT0vDfP0Ry8g.opnq1OIyBFYF3ekJ42bcts-MGbpCFvD7-GjMCTJrRqwg.PNG.kwjing93/HUNTING5.png?type=w966">

[ 관리자_매출 통계 ]
- (1) TableModel Interface를 구현한 AbstractTableModel 클래스 사용
- (2) 특정 조건 ( Like 를 이용해 검색 )으로 1차 필터링 후 총 매출을 구하는 쿼리문 작성
- (3) 서브 쿼리, Join문을 활용한 매출통계

