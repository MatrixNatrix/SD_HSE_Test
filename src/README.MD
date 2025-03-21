
# Automated Testing Framework for Saucedemo

[![Java](https://img.shields.io/badge/Java-11%2B-blue)](https://www.java.com/)
[![Selenium](https://img.shields.io/badge/Selenium-4.1.2-green)](https://www.selenium.dev/)
[![TestNG](https://img.shields.io/badge/TestNG-7.5-red)](https://testng.org/)

Фреймворк автоматизированного тестирования для [Saucedemo](https://www.saucedemo.com/) с использованием:
- Паттерна Page Object
- Мультибраузерного тестирования
- Параметризованных тестов

## 📥 Установка

1. Клонировать репозиторий: 
2. ```bash 
   git clone https://github.com/yourusername/saucedemo-tests.git

В текущем проекте реализованы следующие тестовые сценарии для страницы авторизации (на примере сайта saucedemo.com):

---

### **1. Позитивный сценарий**
- **Что проверяет**: Успешный вход с валидными данными
- **Данные**:
  ```java
  {"standard_user", "secret_sauce", true, ""}
  ```
- **Ожидаемый результат**:
    - Перенаправление на страницу инвентаря (`/inventory.html`)
    - Отсутствие сообщений об ошибках

---

### **2. Негативные сценарии**

#### **a) Пустое имя пользователя**
- **Что проверяет**: Системная реакция на отсутствие логина
- **Данные**:
  ```java
  {"", "secret_sauce", false, "Epic sadface: Username is required"}
  ```
- **Ожидаемый результат**:
    - Сообщение об ошибке с текстом "Username is required"

#### **b) Пустой пароль**
- **Что проверяет**: Валидация обязательности пароля
- **Данные**:
  ```java
  {"standard_user", "", false, "Epic sadface: Password is required"}
  ```
- **Ожидаемый результат**:
    - Сообщение об ошибке "Password is required"

#### **c) Заблокированный пользователь**
- **Что проверяет**: Обработка неактивного аккаунта
- **Данные**:
  ```java
  {"locked_out_user", "secret_sauce", false, "Epic sadface: Sorry, this user has been locked out."}
  ```
- **Ожидаемый результат**:
    - Сообщение о блокировке пользователя

#### **d) Невалидные учетные данные**
- **Что проверяет**: Защита от подбора учетных данных
- **Данные**:
  ```java
  {"invalid_user", "wrong_pass", false, "Epic sadface: Username and password do not match any user in this service"}
  ```
- **Ожидаемый результат**:
    - Общая ошибка аутентификации

---

### **Особенности реализации**

1. **Параметризация через DataProvider**:
    - Все сценарии объединены в один параметризованный тест
    - Централизованное управление тестовыми данными:
      ```java
      @DataProvider(name = "loginData")
      public Object[][] provideLoginData() {
          return new Object[][]{
              {"standard_user", "secret_sauce", true, ""},
              {"", "secret_sauce", false, "Epic sadface: Username is required"},
              {"standard_user", "", false, "Epic sadface: Password is required"},
              {"locked_out_user", "secret_sauce", false, "Epic sadface: Sorry, this user has been locked out."},
              {"invalid_user", "wrong_pass", false, "Epic sadface: Username and password do not match any user in this service"}
          };
      }
      ```

2. **Мультибраузерное покрытие**:
    - Тесты автоматически запускаются для:
        - Chrome
        - Firefox
    - Конфигурация через параметры TestNG:
      ```xml
      <parameter name="browser" value="chrome"/>
      ```

3. **Расширенная валидация**:
    - Проверка URL после успешного входа
    - Точное сравнение текстов ошибок
    - Использование явных ожиданий (WebDriverWait)

---

### **Что можно добавить для улучшения**:
1. Тесты восстановления пароля
2. Проверку "Запомнить меня"
3. Валидацию UI-элементов после входа
4. Тесты безопасности (XSS, SQL-инъекции)
5. Проверку поведения при множественных попытках входа