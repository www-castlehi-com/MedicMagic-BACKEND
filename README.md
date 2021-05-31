# MedicMagic 어플리케이션 BACKEND

## 프로젝트 구성 안내 
MedicMagic은 여성을 대상으로 건강을 추적하고 관리할 수 있도록 도와주는 안드로이드 기반 어플리케이션입니다.<br>
캘린더를 기반으로 한 생리주기와 수면시간, 운동시간 등을 기록할 수 있는 달력 통합 서비스입니다.  
해당 Repository는 BACKEND 코드를 포함하고 있습니다.
사용한 오픈소스의 라이선스의 경우 NOTICE.txt와 http://3.36.134.232:8080/MedicMagic_SPRING 에서 명시하고 있습니다.
![2021-05-31](https://user-images.githubusercontent.com/67732143/120162504-6b0bfb00-c233-11eb-8214-d5fc825d77c9.png)


### 제작 이유
코로나로 인해 생활패턴이 망가진 경우를 흔하게 볼 수 있습니다.<br>
이러한 사람들을 위해 적당한 수면시간을 알려주거나 운동시간을 기록해주는 많은 서비스가 존재합니다. <br>
특히 여성은 생활패턴에 따라 컨디션이 달라지기에 생리주기가 크게 영향을 받습니다. <br>
생리 주기가 달라지면 신경 써야 할 부분이 많습니다. <br>
따라서 대다수 사람들의 핸드폰에는 기본적으로 스케줄러, 생리주기 어플, 운동 어플 등이 설치되어 있습니다. <br>

이러한 기본 상황을 바탕으로 <br>
<달력 하나에 주로 쓰는 기능들을 한번에 볼 수 있다면 어떨까?>  <br>
하는 생각으로 이 프로젝트를 시작하게 되었습니다.  <br>

### 기술 스택
- Spring Framework
- MariaDB
- AWS(EC2, RDS)
- Apache Tomcat

### 개발, 배포 환경
1. 개발 환경
   - Intellij의 Spring MVC 모듈 사용
   - Web 모듈 사용
   - 자바 1.8 버전 사용
   - TDD를 위한 junit, mock, assertj dependency 사용
   - Android Studio와의 로컬 테스트를 위한 tomcat dependency 사용
   - 로컬 테스트(mysql)를 위한 mysql-connector와 jdbc dependency 사용
   - getEntity의 transaction annotation을 적용하기 위한 tx dependency 사용
   - mariaDB 연결을 위한 mariadb.jdbc, mybatis dependency 사용

2. 배포 환경
   - 호스팅을 위한 AWS EC2(+탄력적 ip)사용
   - 클라우드 데이터베이스를 위한 AWS RDS사용
   - EC2에 TOMCAT 설치 후, 프로젝트 BUILD시 생성되는 WAR을 TOMCAT이 배포

## 프로젝트 설치 방법
> git clone [repository 주소]

1. 개발 테스트시
   - test-applicationContext.xml의 <dataSource> 빈에 빈칸을 채워 작성
     ```xml
      <bean id="dataSource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
           <property name="driverClass" value= 사용하려는 db의 jdbc driver 주소 />
           <property name="url" value="jdbc:사용하려는 db://localhost/프로젝트 로컬테스트 DATABASE명" />
           <property name="username" value="로컬테스트 user이름" />
           <property name="password" value="로컬테스트 pw" />
       </bean>
      ```
   - 프로젝트 상단에 있는 db파일의 테이블 정보를 로컬 데이터베이스에 주입
2. 배포시
   - WEB-INF/applicationContext.xml의 <dataSource> 빈에 빈칸을 채워 작성
      ```xml
      <bean id="dataSource" class="org.springframework.jdbc.datasource.SimpleDriverDataSource">
           <property name="driverClass" value="사용하려는 db의 jdbc driver 주소" />
           <property name="url" value="jdbc:사용하려는 db://호스팅주소/프로젝트 DATABASE명" />
           <property name="username" value="DB user이름" />
           <property name="password" value="DB pw" />
       </bean>
      ```
   - 프로젝트 상단에 있는 db파일의 테이블 정보를 사용하려는 관계형 데이터베이스에 주입
   
## 프로젝트 사용법, 기능
   1. 프로젝트를 설치 합니다.
   2. 호스팅 한 서버에 프로젝트 파일을 배포합니다.
   3. UserController
      - **역할** : 로그인, 회원가입
      - **관련 파일** : user파일(User, UserDao, UserDto, UserService)
   4. UserCalendarController
      - **역할** : user에 대한 건강 세부 정보 내보내기, 저장하기.
      - **관련 파일** : userCalendar파일(UserCalendar, UserCalendarDao, UserCalendarDto, UserCalendarService), userPhysiology파일(UserPhysiology, UserPhysiologyDao, UserPhysiologyDto, UserPhysiologyService)
   5. UserMucusController
      - **역할** : user의 분비물에 대한 세부 정보 내보내기, 저장하기.
      - **관련 파일** : userMucus파일(UserMucus, UserMucusDao, UserMucusDto, UserMucusService)
   6. UserSymptomController
      - **역할** : user의 증후군에 대한 세부 정보 내보내기, 저장하기.
      - **관련 파일** : userSymptom파일(UserSymptom, UserSymptomDao, UserSymptomDto, UserSymptomService)
   7. UserPhysiologyController
      - **역할** : user의 주기 정보에 대한 세부 정보 내보내기, 저장하기.
      - **관련 파일** : userPhysiology파일(UserPhsyiology, UserPhysiologyDao, UserPhysiologyDto, UserPhysiologyService)
   8. UserGraphExerciseController, UserGraphSleepController, UserGraphWaterController
      - **역할** : user의 정보로 그래프를 생성하기 위해 운동 정보, 수면 시간, 수분 섭취량 내보내기, 저장하기.
      - **관련 파일** : userCalendar파일(UserCalendar, UserCalendarDao, UserCalendarDto, UserCalendarService)
   9. UserBirthControlPills, UserExerciseController, UserHospitalController, UserReminderListController, UserReminderPhysiologyController, UserSleepController, UserWaterIntakeController
      - **역할** : user의 리마인더 정보 내보내기, 저장하기
      - **관련 파일**
        - userBirthControlPills파일(UserBirthControlPills, UserBirthControlPillsDao, UserBirthControlPillsDto, UserBirthControlPillsService)
        - userExercise파일(UserExercise, UserExerciseDao, UserExerciseDto, UserExerciseSercie)
        - userHospital파일(userHospital, userHospitalDao, userHospitalDto, userHospitalService)
        - userReminderList파일(UserReminderList, UserReminderListDao, UserReminderListDto, UserReminderListService)
        - userReminderPhysiology파일(UserReminderPhysiology, UserReminderPhysiologyDao, UserReminderPhysiologyDto, UserReminderPhysiologyService)
        - userSleep파일(UserSleep, UserSleepDao, UserSleepDto, UserSleepService)

## 버그
   - 날짜와 시간을 받는 형식이 정해져있습니다. 사용하려는 Front 코드에서 이를 정확하게 맞추어야 합니다.
   - 정보를 주고 받는 과정에서 고려되지 못한 exception이 있을 수 있습니다. 
   
   ### 버그 수정 방법
   1. 프로젝트를 fork합니다.
   2. branch를 생성합니다.(```git checkout -b MedicMagic-BACKEND```)
   3. 버그를 수정하고 커밋합니다.(```git commit -m 'Catch some Error'```)
   4. branch를 push합니다. (```git push origin MedicMagic-BACKEND```)
   5. Pull Request합니다.

## 프로젝트 작성자 및 도움을 준 사람
   - 작성자 : moong2
   - 이메일 : pushclap@gmail.com
   - 도움을 준 사람 : aelim0409, slcloe, srsw000521 
   - 관련 FRONT REPO : https://github.com/aelim0409/magicmadic.git
