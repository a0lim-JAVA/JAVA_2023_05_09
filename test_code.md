# 자바 프로그래밍 언어용 단위 테스트
## JUnit  

* JUnit의 특징
  - 단정 메서드(assert)로 테스트 케이스의 수행 결과를 판별
    + ex) assertEquals(예상값, 실제값)
    + ex) assert(): 테스트가 정상인지 아닌지 판별
  - 테스트 어노테이션 제공
    + ex) @Test @Before @After
  - 각 @Test 어노테이션 메서드 호출 시, 새로운 인스턴스를 생성하여 독립적인 테스트가 이루어지도록 함

* 테스트코드 작성 원칙
  - Fast  
    : 빠르게 실행되어야 한다
  - Isolated  
    : 각 테스트는 독립적으로 실행되어야 한다
  - Repeatable  
    : 매번 실행 결과는 동일해야 한다
  - Self-Verifying  
    : 결과는 Pass or Fail 중 하나의 상태 값을 가진다
  - Timely  
    : 비즈니스 로직 코드 작성보다 테스트 코드를 먼저 작성한다
    
* 단위 테스트 코드의 장점
  - 프로그램의 안정성 향상
  - 디버깅 시간 단축
  - 상시 제품의 소스 코드 수정에 용이
  - 제품의 기능을 통합할 때, 문제를 초기에 발견 가능
  - 기능 사용에 대한 성공/실패에 해당하는 피드백이 빠름

* 단위 테스트 코드 구조
  - 3A 구조
    ```
    @Test
    public void Test(){
      // Arrange: 테스트할 클래스의 객체 생성 준비 단계
      
      // Act: 객체의 행위 실행 단계
      
      // Assert: 행위 실행에 대한 예측 결과 단계
    }
    ```
  - BDD 구조  
    : given / when / then

* 예시
  ```
  @Autowired
  private Controller controller;
  @Autowired
  private Repository repository;
  
  private RequestDto requestDto;
  private ResponseDto responseDto;
  private Entity entity;
  
  // test에서 사용할 예제 생성
  @BeforeEach
  void seteup(){
    this.requestDto = new RequestDto(
      "홍길동",
      1
    );
    
  ...
  }
  
  // test 성공 예제
  @Test
  @DisplayName("success - create Entity with normal case")
  void createEntityShouldSuccess(){
    controller.createEntity(this.requestDto);
  }
  
  // test 실패 예제: ValidationError(정규표현식)
  @Test
  @DisplayName("fail - create Entity with invalid name")
  void createCompanyWithInvalidNameShouldFail(){
    RequestDto dto = this.requestDto;
    dto.setEntityName(1L); // 문자열과 type이 맞지 않음
    
    Assertions.assertThatThrowBy(() -> controller.createEntity(dto))
          .isInstanceOf(ConstraintViolationException.class)
          .hasMessageContaining("type is String");
  }
    
  // test 실패 예제: BusinessException
  @Test
  @DisplayName("fail - read Entity with invalid id")
  void readCompanyWithInvalidIdShouldFail(){
    RequestDto dto = this.requestDto;
    dto.setEntityId(0L); // list에 없는 데이터를 불러옴
    Assertions.assertThatThrowBy(() -> controller.readeEntity(dto))
          .isInstanceOf(BusinessException.class)
          .extracting("errorCode", InstanceOfAssertFactories, type(ErrorCode.class))
          .isEqualTo(ErrorCode.Entity_NOT_FOUND);
  }
  ```

# 모의 객체를 생성하는 테스트
## Mockito
