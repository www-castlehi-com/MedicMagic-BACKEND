# MedicMagic 어플리케이션 BACKEND

## 프로젝트 구성 안내 
MedicMagic은 가임기 여성을 대상으로 건강을 추적하고 관리할 수 있도록 도와주는 안드로이드 기반 어플리케이션입니다.
캘린더를 기반으로 한 생리주기와 수면시간, 운동시간 등을 기록할 수 있는 달력 통합 서비스입니다.  
해당 Repository는 BACKEND 코드를 포함하고 있습니다.

### 제작 이유
코로나로 인해 생활패턴이 망가진 경우를 흔하게 볼 수 있습니다.
이러한 사람들을 위해 적당한 수면시간을 알려주거나 운동시간을 기록해주는 많은 서비스가 존재합니다. 
특히 여성은 생활패턴에 따라 컨디션이 달라지기에 생리주기가 크게 영향을 받습니다. 생리 주기가 달라지면 신경 써야 할 부분이 많습니다. 
따라서 대다수 사람들의 핸드폰에는 기본적으로 스케줄러, 생리주기 어플, 운동 어플 등이 설치되어 있습니다. 

이러한 기본 상황을 바탕으로
<달력 하나에 주로 쓰는 기능들을 한번에 볼 수 있다면 어떨까?> 
하는 생각으로 이 프로젝트를 시작하게 되었습니다. 

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
