## 날짜 생성
```
today = LocalDate.now(); // 현재 날짜 @@ 2023-02-27
tomorrow = LocalDate.of(2023, 2, 28); // 년, 월, 일 @@ 2023-02-28
```

## 날짜 비교
```
today.isBefore(tomorrow); // today가 tomorrow보다 이전 날짜인지 비교 @@ true
today.isAfter(tomorrow); // today가 tomorrow보다 이후 날짜인지 비교 @@ false
today.isEqual(tomorrow); // today가 tomorrow와 같은 날짜인지 비교 @@ false

```

## 날짜 더하기, 빼기
### LocalDate에 year, month, week, day 더하기
```
today.plusYears(); // today.plusYears(1) @@ 2024-02-27
today.plusMonths();
today.plusWeeks();
today.plusDays();
```

### LocalDate에 year, month, week, day 빼기
```
today.minusYears(); // today.minusYears(1) @@ 2022-02-27
today.minusMonths();
today.minusWeeks();
today.minusDays();
```

## 날짜 차이 계산
### year, month, day로 나눠진 결과 반환
```
Period period = Period.between(today, tomorrow) // 1일 차이
period.getYears(); // @@ 0
period.getMonths(); // @@ 0
period.getDays(); // @@ 1
```
```
someday = LocalDate.of(2024,3,28)
someday.getYears(); // @@ 1
someday.getMonths(); // @@ 1
someday.getDays(); // @@ 1
```

### 전체 시간을 기준으로 결과 반환
```
ChronoUnit.DAYS.between(today, someday); // 일을 기준으로 계산함 @@ 31

ChronoUnit.YEARS.between();
ChronoUnit.MONTHS.between();
ChronoUnit.WEEKS.between();
```

## 해당 월의 마지막 날짜 찾기
* withDayOfMonth(), lengthOfMonth() 사용
```
LocalDate firstDate = date.withDayOfMonth(1); // 첫째 날 @@ 2023-02-01
LocalDate lastDate = date.withDayOfMonth(date.lengthOfMonth)); // 마지막 날 @@ 2023-02-28
```

* YearMonth 타입 사용
```
YearMonth month = YearMonth.from(today);
LocalDate firstDate = month.atDay(1); // 첫째 날 @@ 2023-02-01
LocalDate endDate = month.atEndOfMonth(); // 마지막 날 @@ 2023-02-28
```


## 날짜 포맷 변환
```
DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd") // "yyyy.MM.dd"으로 바꾸는 포맷
String string = today.format(dateTimeFormatter) // today에 포맷 적용 @@ 2023.02.27
```

## 날짜 타입 변환
* LocalDate -> String
```
LocalDate.of(2020, 12, 12).format(DateTimeFormatter.BASIC_ISO_DATE);
```
* LocalDateTime -> String
```
LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
```
* LocalDate -> java.sql.Date
```
Date.valueOf(LocalDate.of(2019, 12, 27));
```
* LocalDateTime -> java.util.Date
```
Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
```
* LocalDateTime -> java.sql.Timestamp
```
Timestamp.valueOf(LocalDateTime.now());
```
* String -> LocalDate
```
LocalDate.parse("1995-05-09");
LocalDate.parse("20191224", DateTimeFormatter.BASIC_ISO_DATE); 
```
* String -> LocalDateTime
```
LocalDateTime.parse("2019-12-25T10:15:30");
LocalDateTime.parse("2019-12-25 12:30:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
```
* java.util.Date -> LocalDateTime
```
LocalDateTime.ofInstant(new Date().toInstant(), ZoneId.systemDefault());
```
* LocalDateTime -> LocalDate
```
LocalDate.from(LocalDateTime.now());
```
* LocalDate -> LocalDateTime
```
LocalDate.now().atTime(2, 30);
```

## LocalDate 필드에 InvalidData가 오는 경우
* ex) 2/31을 입력 -> null로 입력됨
