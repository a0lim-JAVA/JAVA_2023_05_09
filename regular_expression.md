## 정규표현식
* 특정한 규칙을 가진 문자열의 집합을 표현하는 데 사용되는 언어

## Pattern 클래스
  ```
  String pattern = "^[0-9]$"; // 적용할 정규표현식
  String target = "123"
  
  boolean result = Pattern.matches(pattern, target);
  System.out.println(result) // true: target이 pattern에 매칭되면 true, 그렇지 않으면 false
  ```
  * 그 외의 지원하는 메소드
    - compile(String regex)  
      :	정규표현식의 패턴을 작성
    - matches(String regex, CharSequence input)  
      : 정규표현식의 패턴과 문자열이 일치하는지 체크. 일치할 경우 true, 일치하지 않는 경우 false를 리턴(일부 문자열이 아닌 전체 문자열과 완벽히 일치 해야함)
    - asPredicate()  
      : 문자열을 일치시키는 데 사용할 수있는 술어를 작성
    - pattern()  
      : 컴파일된 정규표현식을 String 형태로 반환
    - split(CharSequence input)  
      : 문자열을 주어진 인자값 CharSequence 패턴에 따라 분리
      
      
## Matcher 클래스
* 문자열을 비교하고, 검사한 결과값을 담은 매처 객체를 반환
  ```
  String target = "123"
  String patternStr = "^[0-9]$"
  
  Pattern pattern = Pattern.compile(patternStr) // 문자열에서 정규식 패턴으로 변환
  Macher matcher = pattren.macher(target); // target을 검사하고 필터링 된 결과를 매처 객체로 반환
  
  System.out.println(matcher.find()); // 매칭된 결과가 있는지 @@ true
  System.out.println(matcher.group()); // 매칭된 부분을 반환 @@ 123
  ```
* 그 외의 지원하는 메소드
  - find( )  
    : 패턴이 일치하는 경우 true를 반환, 불일치하는 경우 false반환(여러개가 매칭되는 경우 반복실행하면 일치하는 부분 다음부터 이어서 매칭됨)
  - find(int start)  
    : start 위치 이후부터 매칭검색
  - start( )  
    : 매칭되는 문자열의 시작위치 반환
  - start(int group)  
    : 지정된 그룹이 매칭되는 시작위치 반환
  - end( )  
    : 매칭되는 문자열 끝위치의 다음 문자위치 반환
  - end(int group)  
    : 지정된 그룹이 매칭되는 끝위치의 다음 문자위치 반환
  - group( )  
    : 매칭된 부분을 반환
  - group(int group)  
    : 그룹화되어 매칭된 패턴중  group 번째 부분 반환
  - groupCount( )  
    : 괄호로 지정해서 그룹핑한 패턴의 전체 개수 반환
  - matches( )  
    : 패턴이 전체 문자열과 일치할 경우 true반환(일부 문자열이 아닌 전체 문자열과 완벽히 일치 해야함)
