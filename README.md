# ESB(엔터프라이즈 서비스 버스) 교육

### 구조  

 EAP(Producer) -> Queue -> EAP(Consumer)
 

### Apache camel
 ####1. esb framework 
 
 ####2. router 
  - 수신자 목록 (데이터에 수신자 기입)
  - 메시지 필터(받는쪽에서 필터)
  - 내용기반 라우터(시스템에 직접 연계 기입)
  
 ####3. apache camel, activemq, jbossfuse
 
 ####4. activemq 환경설정 
 
 #####users.properties 
 파일에 사용자 
 
 activemq.xm
 ```
<transportConnectors>
            <!-- DOS protection, limit concurrent connections to 1000 and frame size to 100MB -->
            <transportConnector name="openwire" uri="tcp://0.0.0.0:61616?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
            
        <!--<transportConnector name="amqp" uri="amqp://0.0.0.0:5672?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
            <transportConnector name="stomp" uri="stomp://0.0.0.0:61613?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
            <transportConnector name="mqtt" uri="mqtt://0.0.0.0:1883?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/>
            <transportConnector name="ws" uri="ws://0.0.0.0:61614?maximumConnections=1000&amp;wireFormat.maxFrameSize=104857600"/> -->
        </transportConnectors>
```

ActiveMQ 관련 기본 용어

- AMQP : AMQP(Advanced Message Queuing Protocol, 어드밴스트 메시지 큐잉 프로토콜)는 메시지 지향 미들웨어를 위한 개방형 표준 응용 계층
 프로토콜이다. AMQP의 정의 기능들은 메시지 지향, 큐잉, 라우팅(P2P 및 발행-구독), 신뢰성, 보안이다.
- openWire : 메시지 지향 미들웨어와 함께 동작하도록 한 이진 프로토콜

#### 웹콘솔 ActiveMQ 실습

http://localhost:8161/admin/queues.jsp

- /data 경로에 메시지 저장 된다.
- HA 구성 시 경로 공유 해야 한다.(NAS...)

send
- Persistent Delivery 데이터 저장

---

#### java ActiveMQ 실습
1.TestTransformer.java
2. camelContext 구성
3. simple-route를 통한 경로에 로그파일 생성 확인

#### java 실습
1. mybatis 사용환경 구성
2. csvr(ackService) process 구성
3. consumer 구성 
4. postman 으로 테스트 servlet 받아서 큐애 넣고 빼서 insetr 진행  

h2 디비로 구성해 보자 .. 