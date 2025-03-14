## JUnit5 와 Spock 활용 단위 테스트 
</br>
📝 참고 </br> </br>
</br>
♻️ 단위 테스트</br>
</br>
단위 테스트에 대한 정의를 구분하기 위해서는 고전파와 런던파 관점에서 알아볼 수 있음</br>
고전파 => 모든 사람이 단위 테스트와 테스트 주도 개발에 원론적으로 접근</br>
</br>
런던파 => 런던의 프로그래밍 커뮤니티에서 시작</br>
</br>
단위 테스트는:</br>
</br>
작은 코드 조각(단위)을 검증하고,</br>
빠르게 수행하고,</br>
격리된 방식으로 처리하는 자동화된 테스트</br>
마지막 격리 문제에 대한 접근법이 런던파와 고전파가 다름</br>
</br>
테스트 코드 작성 예시</br>
비즈니스 로직</br>
=> 고객은 제품을 구매할 수 있음</br>
=> 재고가 충분하면 구매는 성공하며, 제품의 재고는 1개 줄어든다.</br>
=> 재고가 부족하면 구매는 실패하며, 아무런 액션이 발생하지 않는다.</br>
</br>
고전파 테스트 코드</br>
</br>
puglic void purchase_success_when_enough_inventory(){</br>
//given</br>
Store store = new Store(); //상점 생성</br>
store.addInventory("MacBook Pro", 10); //상점에 맥북 10개 추가</br>
Customer customer = new Customer();</br>
</br>
//when</br>
boolean result = customer.purchase(store, "MackBook Pro", 3); //상점에서 맥북 3개 구매</br>
</br>
//then</br>
assertTrue(result); //구매 성공</br>
assertEquals(7, store.getInventory("MackBook Pro")); // 남은 재고 7개</br>
</br>
}</br>
고전파 테스트 2</br>
</br>
public void purchase_fails_when_not_enough_inventory(){</br>
//given</br>
Store store = new Store(); //상점 생성</br>
store.addInventory("MackBook Pro", 10); //상점에 맥북 10개 추가</br>
Customer customer = new Customer();</br>
</br>
//when</br> 
boolean result = customer.purchase(store, "MackBook Pro", 15); //상점에 맥북 15개 구매</br>
</br>
//then</br>
assertFalse(result); //구매 실패(재고 부족)</br>
assertEqauls(10, store.getInventory("MackBook Pro")); //남은 재고 그대로 10개</br>
</br>
}</br>
-> 고전파 테스트 코드</br>
: Customer 와 Store 를 둘 다 별도의 테스트 베드로 교체하지 않고, 그대로 사용함</br>
이 테스트로 인해 Custome 와 Store 모두 테스트가 가능해짐</br>
하지만 Customer 와 Store 내 버그가 발생하면 해당 단위 테스트는 실패할 수 있음 -> 두 클래스는 서로 격리되어 있지 않음</br>
</br>
런던파 테스트 코드</br>
</br>
@Mock store store;</br>
</br>
public void purchase_success_when_enough_inventory(){</br>
//given</br>
given(store.hasEnoughInventory("MackBook Pro")).willReturn(ture); //해당 메소드가 호출되면 true를 반환</br>
Customer customer = new Customer();</br>
</br>
//when</br>
boolean result = customer.purchase(store, "MackBook Pro", 3); // 상점에서 맥북 3개 구매</br>
</br>
//then</br>
assertTrue(result); //구매 성공</br>
verify(store.removeInventory("MackBook Pro", 3), times(1)) //맥북 3개 재고 감소 메소드 1번 호출</br>
</br>
}</br>
Store를 Mocking 하여 사용함</br>
테스트 하고자 하는 비즈니스 로직에만 집중하여 테스트 코드를 작성</br>
</br>
런던파의 접근</br>
런던파에서는 테스트 대상 시스템을 협력자에게서 격리하는 것을 의미함</br>
하나의 클래스가 다른 클래스 또는 클래스에 의존하면 이 모든 의존성을 테스트 대역(test double)로 대체해야 함</br>
외부 영향과 분리해서 테스트 대상 클래스에만 집중할 수 있도록 함</br>
</br>
성공적인 테스트를 위해서는?</br>
개발 주기에 통합되어 있음</br>
-> 코드가 변경될 때마다 테스트 코드를 실행하여 끊임없는 테스트를 하는 것이 좋음</br>
</br>
코드 베이스에서 가장 중요한 부분만을 대상으로 함</br>
-> 도메인 모델, 비즈니스 로직을 위주로 테스트 코드를 작성</br>
</br>
최소 유지비로 최대 가치를 끌어냄(가성비)</br>
-> 가치있는 테스트를 식별하기 + 가치있는 테스트를 작성하기</br>
</br>
단위 테스트의 구조</br>
Given-When-Then 패턴</br>
Given: 준비</br>
When: 실행</br>
Then: 검증</br>
</br>
여러개의 given-when-then이 포함되는 테스트는 지양</br>
실행이 하나가 되면 간단하고, 빠르며, 이해하기가 쉬움</br>
</br>
테스트 내 if문 피하기</br>
테스트 내 if문이 포함되어야 하는 경우에는 여러 테스트로 분리하여 작성</br>
테스트 내 분기가 생기면 유지보수 비용이 급격하게 상승함</br>
</br>
실행 구절이 한 줄 이상인 경우를 조심</br>
</br>
좋은 단위 테스트의 4대 요소</br>
</br>
회귀 방지</br>
리팩토링 내성</br>
빠른 피드백</br>
유지 보수성</br>
회귀 방지</br>
회귀 버그 = 기존에 제대로 동작하던 소프트웨어 기능에 문제가 생기는 것을 의미(신규 기능 추가 또는 기존 기능 수정)</br>
코드 베이스가 커질 수록 잠재적인 버그에 더 많이 노출됨</br>
이를 방지하기 위해서는 테스트가 가능한 많은 코드를 실행하는 것이 중요함</br>
</br>
리팩토링 내성</br>
리팩토링 내성 = 작성해둔 테스트가 실패 되지 않으면서 소프트웨어의 코드를 리팩토링 할 수 있는가?에 대한 척도</br>
</br>
리팩토링 후 기존 기능이 정상적으로 동작하지만 테스트 코드가 실패하는 상황을 거짓 양성(false positive)라고 함</br>
</br>
거짓 양성의 장점</br>
</br>
테스트를 통해 기존 기능에 대한 재확인을 할 수 있도록 유도(조기 경고)</br>
코드의 변경으로 인해 회귀 버그가 발생하지 않을 것에 대한 확신</br>
거짓 양성의 단점</br>
</br>
타당한 이유 없이 실패한다면 테스트를 고치는 것, 기존 코드를 고치는 것에 대해 무감각해짐</br>
"깨진 유리창 이론"</br>
구현 세부 사항보다 최종 결과를 테스트</br>
테스트 코드에서 검증해야 하는 부분은 "입력"이 들어왔을 때, 기대하는 "출력"으로 결과값이 나오는짐나 보면 됨</br>
</br>
구현 부분에서 A -> B -> C 흐름으로 동작하는지에 대해서까지 테스트를 한다면, 그 순서가 변경되었을 때 테스트가 깨지게 됨</br>
쉽게 깨지지 않는 테스트를 위해서는 구현 세부 사항보다는 최종 결과에 집중해서 테스트를 하는 것이 좋음</br>
</br>
빠른 피드백과 유지 보수성</br>
테스트 속도가 빠를 수록 더 많은 테스트를 수행할 수 있고, 더 자주 실행할 수 있음</br>
=> 테스트가 실패되었을 때 버그를 빠르게 수정할 수 있음</br>
</br>
높은 유지 보수성을 위해서는</br>
</br>
테스트 코드가 이해하기 쉬운가?</br>
테스트를 실행하기 쉬운가? (테스트를 수행하기 위해서 외부 의존성이 필요한가?)</br>
과연 이상적인 테스트는 있는가?</br>
4가지 특성에 대한 가치를 곱한 결과가 테스트에 대한 가치가 됨</br>
=> 하나의 특성이 0이 되면 전체 테스트의 가치는 0</br>
</br>
4가지 특성을 모두 최대 점수를 받는 테스트는 존재할 수 없음</br>
=> 회귀 방지, 리팩토링 내성, 빠른 피드백 특성은 서로 상호 배타적이기 때문</br>
=> 셋 중 하나를 희생해야 함</br>
</br>
End-to-End 테스트</br>
End-to-end 테스트는 UI, 데이터베이스, 외부 어플리케이션을 포함한 모든 시스템 구성 요소를 테스트</br>
많은 코드를 테스트하기 때문에 회귀 방지를 성공적으로 함</br>
최종 사용자 관점에서 테스트를 진행하기 때문에 세부 구현 사항을 최대한 제거함</br>
=> 리팩토링 내성도 우수함</br>
하지만 테스트가 많기 때문에 피드백이 느릴 수 밖에 없음</br>
=> 빠른 피드백 불가</br>
</br>
간단한 테스트</br>
간단한 테스트 => 빠른 피드백 가능</br>
</br>
거짓 양성이 생길 가능성이 매우 낮기 때문에</br>
=> 리팩토링 내성도 높음</br>
</br>
하지만 기반 코드 베이스에 실수할 가능성이 거의 제로이기 때문에</br>
=> 회귀 방지를 나타내지 않음</br>
=> 검증이 무의미함</br>
</br>
깨지기 쉬운 테스트</br>
</br>
public String getUserByIdSql(String userId){</br>
  return "SELECT * FROM User where UserId = usderId;"</br>
}</br>
</br>
public void test_get_user_by_id_sql(){</br>
  String sql = sut.getUserByIdSql("test");</br>
  assertEquals("SELECT * FROM User where UserId = test;",sql);</br>
}</br>
"SELECT UserId, Name, Email From User where UserId = test" 도 동일한 결과를 기대할 수 잇음</br>
하지만 테스트는 실패하게 됨</br>
</br>
현실적으로 보는 이상적인 테스트</br>
리팩토링 내성을 최대한으로 하면서 상황에 따라 회귀 방지와 빠른 피드백을 적절히 조절</br>
</br>
♻️ JUnit5</br>
JUnit5는 테스트를 위한 프레임워크를 의미함</br>
Java8 이상부터 JUnit5 를 사용할 수 있음</br>
Java 언어 기반이기 때문에 별도의 언어를 습득하지 않아도 테스트 코드를 작성할 수 있음</br>
보통 단위테스트를 작성할 때 처음 접하게 되기 때문에 익숙함</br>
</br>
JUnit5의 어노테이션</br>
@Test</br>
</br>
테스트 Method 임을 선언</br>
@ParameterizedTest</br>
</br>
파라미터를 받을 수 있는 테스트를 작성할 수 있음</br>
@RepeatedTest</br>
</br>
반복되는 테스트를 작성할 수 있음</br>
@DisplayName</br>
</br>
테스트 메소드의 이름을 직접 정의할 수 있음</br>
@BeforeEach</br>
</br>
각 테스트 실행 전에 실행됨</br>
@AfetEach</br>
</br>
각 테스트 실행 후에 실행됨</br>
@Disabled</br>
</br>
테스트를 무시함</br>
</br>
♻️ Spock</br>
Groovy 언어 기반으로 테스트를 작성할 수 있는 테스팅 프레임워크</br>
JUnit 에 비해 좀 더 짧은 코드를 작성할 수 있음</br>
사용자에 따라 가독성이 더 뛰어나다고 생각할수 있음</br>
</br>
Spock의 문법</br>
Spock 에서는 given, when, then 과 같은 코드를 하나의 block 이라고 함</br>
각 block 내에는 테스트를 위한 메소드가 최소 하나가 존재해야 함</br>
</br>
setup: / given:</br>
</br>
테스트에 필요한 환경을 설정하는 작업</br>
다른 블록보다 상위에 위치해야 함</br>
when:</br>
</br>
테스트 코드를 실행</br>
then:</br>
</br>
결과 검증</br>
expect:</br>
</br>
테스트할 코드를 실행 및 검증(when + then)</br>
where:</br>
-반복</br>
</br>
spock의 기능 - where</br>
JUnit의 ParameterizedTest 와 비슷한 기능을 수 행할 수 있음</br>
</br>

