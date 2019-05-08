# HuntingProject
네트워크 기능이 가능한 전자메뉴판 팀플 프로젝트_ 담당업무 : (유저) 로그인 / (관리자) 매출통계 / DB설계

<img src="https://postfiles.pstatic.net/MjAxOTA1MDhfMTM3/MDAxNTU3MjUxMjg5NTI4.svSSHITfRlSXZV-Ci1b_mVsuRhqbuFYEOSMx55laMyYg.NBINn4wgnEDujw6uouHTOfzG81IZyxc6m9CtFNtZelEg.PNG.kwjing93/HUNTING1.png?type=w966">


<img src="https://postfiles.pstatic.net/MjAxOTA1MDhfOTEg/MDAxNTU3MjUxNDY2MDQ3.jZUwC4FQIzjOcW5bb442e4fOU-jWh-sjMYv4S0JfHXIg.4HLL684yEpE637Yl_th9_laUqquDv8xDj63_TidCU-Ag.PNG.kwjing93/HUNTING2.png?type=w966">

<< 1 데이터베이스 설계 >>

[ DB ]
- ConnectionManager 클래스를 정의하여 db연결, 접속객체 획득, 반납 처리
- 바인드 변수를 사용해 쿼리문 작성


<img src="https://postfiles.pstatic.net/MjAxOTA1MDhfMTM1/MDAxNTU3MzIxMzAxNjEw.qRSOi3aqPdMNblcHspyiLRoYcKR5dlwHzeVzQwbsLvMg.UpCzqJbaQUlFFKyY8WMd2byNVCWj_bJMgt836tNn-QAg.PNG.kwjing93/HUNTING3-1.png?type=w966">

<< 2 유저_로그인 및 회원가입 >>

[ 공통_디자인 ]
- 클래스 상속    
- java swing 컴포넌트의 사용
[ 공통_처리 ]
- String 처리 기능을 모아놓은 StringUtil 클래스를 정의하여 String 편집

  1) 회원가입     2) 로그인
2) ( 유저- 로그인 ) - 아이디 중복 검사 처리


<img src="https://postfiles.pstatic.net/MjAxOTA1MDhfMTM1/MDAxNTU3MzIxMzA2ODc1.EUoXqq5Wq2TVVmwFlPvMZ-Yqnx6M4wFZuGhEG4Yb4bog.RNqw2-ziSqo58fL0kJ97CeuTKchll4cChHALzOWapVwg.PNG.kwjing93/HUNTING3-2.png?type=w966">

<< 2 유저_로그인 및 회원가입 >>  

  3)입장자 정보등록     4) 메뉴판 메인 화면


<img src="https://postfiles.pstatic.net/MjAxOTA1MDhfMTE3/MDAxNTU3MzIyMzQwMjg2.rWGLjdAVXd_cc_3IpZSF4SrkF07Tyu071GqodvKdZUsg.J9WI2opXO_0iIdlkVF1zQd5AfvAPldFseMy93Lmbqckg.PNG.kwjing93/HUNTING4.png?type=w966">

<< 3 유저_주문서 >>

[ 처리 ] 
- getter, setter 메서드를 통해 객체의 값을 스윙과 DB 사이에서 주고 받음


<img src="https://postfiles.pstatic.net/MjAxOTA1MDhfMTYg/MDAxNTU3MjUxNDcwNTQy.Ot1dzc_ub7TnZraTjgUgtRU4lxB9vATijT0vDfP0Ry8g.opnq1OIyBFYF3ekJ42bcts-MGbpCFvD7-GjMCTJrRqwg.PNG.kwjing93/HUNTING5.png?type=w966">

<< 4 관리자_매출통계 >>

[ 처리 ]
- AbstractTableModel을 상속받은 TableModel 클래스를 정의하여, table 컴포넌트의 생성
- 특정 조건 ( like 를 이용해 검색 )으로 1차 필터링 후 총 매출을 구하는 쿼리문 작성
- 이중 쿼리문, sum 을 활용한 매출 쿼리문 작성

