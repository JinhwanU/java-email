# java-email
> 자바 코드로 작성한 이메일 서비스
<br>

### 1. 개발환경

도구 | 상세
-|- 
개발 언어 | Java 17.0.10​
통합개발환경(IDE) | Eclipse 2022-03-R​
데이터베이스 | Oracle 21c XE​
라이브러리 | ojdbc​ 

<br>

### 2. 서비스 소개
회원들간에 이메일을 주고받을 수 있는 간단한 이메일 서비스입니다.<br>
로그인 기능 및 받은메일함/보낸메일함/휴지통 기능을 구현하였습니다.<br>
콘솔 기반으로 UI를 구성하였습니다.<br>

<br>

### 3. 요구사항 설계서
<details>
   <summary><b>열기</b></summary>
<div markdown="1">
   
   ![요구사항 설계](https://github.com/JinhwanU/java-email/assets/96904426/282f873d-ec2b-419a-a087-563b8aa1ebbb)
<br><br>
  https://www.notion.so/cb342621db50460c989b98d3498b8094?v=90b1da24df3d4b9995a06cd302fc6dba&pvs=4
</div>
</details>

<br>
<br>

### 4. 유스케이스 다이어그램
<details>
   <summary><b>열기</b></summary>
<div markdown="1">
   
   ![유스케이스 다이어그램](https://github.com/JinhwanU/java-email/assets/96904426/2047a624-5bec-4181-bb1a-585f6afb17c6)
</div>
</details>

<br>

### 5. 테이블 설계
<details>
   <summary><b>열기</b></summary>
<div markdown="1">
   
   ![테이블 설계](https://github.com/JinhwanU/java-email/assets/96904426/73b63dcc-aecd-42e1-ba72-6e746209c427)
</div>
</details>

<br>

### 6. 클래스 다이어그램

<details>
   <summary><b>열기</b></summary>
<div markdown="1">
   
   ![UI cld](https://github.com/JinhwanU/java-email/assets/96904426/22ad5ca1-197d-43bb-a539-b8a9f377ae05)
   ![class cld](https://github.com/JinhwanU/java-email/assets/96904426/3bc18614-9702-4e19-b749-820b2b5cc93b)
</div>
</details>

<br>

### 7. 후기 및 개선점
1. 테이블 설계 미숙 – 관계 및 칼럼 설정​
2. 영상 편집 능력​ 부족
3. 입력 값에 대한 유효성 검증 부족(아이디/비밀번호/이메일 포맷)​
4. 화면 설계 tool 활용 부족
5. UML 작성 및 활용 능력 부족

