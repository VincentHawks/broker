HTML - src/resources/static/template
CSS - src/resources/static/css
JS - src/resources/static/js

Маппинги (а.к.а. адреса на которые надо отправлять запросы) можно увидеть в
src/main/java/ru/hse/broker/controllers/BankController
Требуемые методы указаны в названии аннотации, адрес - http://localhost:8080/ + то что в скобках у аннотации

К примеру, @GetMapping("/form") означает что нужно отправить GET запрос на http://localhost:8080/form

Конструкции типа /bank/{id} означают, что при отправке запроса на /bank/1 переменная id будет 1

Во всех запросах тело должно содержать media-type: application/json