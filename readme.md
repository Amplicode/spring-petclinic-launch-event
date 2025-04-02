# Входные данные
* Настроен Dbeaver плагин и соединение с БД

# Amplicode Explorer дерево сущностей для 
Показываем в Amplicode Explorer дерево Persistence -> JDBC.
Рассказываем что отображаются плоский список сущностей и есть связи между ними. 
Рассказываем что есть аггрегаты:
* Owner (с входящими в него Pet, Visit)
* PetType
* Speciality
* Vet (с входящими в него VetSpeciality)

# Переходим к сущности Vet
* Показываем что есть навигация как и в JPA. 
* Переходим в репозиторий VetRepository через навигацию
* Добавляем в репозиторий новый метод Find Collection через палитру Amplicode
  * Метод Find Collection
  * Возвращаемый тип List
  * В свойствах фильтрации выбираем firstName, условие equals.

# Delegate to
* Переходим в VetController
* Выполняем действие Delegate To.
* Выбираем VetRepository, метод `findByFirstName`
* Указываем Mapping path: `byFirstName/{firstName}`
* Возвращаемый результат List<VetDto>
* Перезапускаем проект
* В файле request.connect.kts находим запрос и выполняем его: 
```kotlin
GET("http://localhost:8080/rest/vets/byFirstName/{firstName}") {
    pathParam("firstName", "Linda")
}
```
* Показываем что HTTP запрос выполнился и подгрузил нужные данные


