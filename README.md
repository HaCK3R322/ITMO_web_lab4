1. Уровень back-end должен быть основан на Spring.
2. Уровень front-end должен быть построен на Vue.js с использованием обычных полей ввода HTML
3. Взаимодействие между уровнями back-end и front-end должно быть организовано посредством REST API.
4. Приложение должно включать в себя 2 страницы - стартовую и основную страницу приложения. Обе страницы приложения должны быть адаптированы для отображения в 3 режимах:
   - "Десктопный" - для устройств, ширина экрана которых равна или превышает 1058 пикселей.
   - "Планшетный" - для устройств, ширина экрана которых равна или превышает 780, но меньше 1058 пикселей.
   - "Мобильный"- для устройств, ширина экрана которых меньше 780 пикселей.

5. Стартовая страница должна содержать следующие элементы:
   - "Шапку", содержащую ФИО студента, номер группы и номер варианта.
   - Форму для ввода логина и пароля. Информация о зарегистрированных в системе пользователях должна храниться в отдельной таблице БД (пароль должен храниться в виде хэш-суммы). Доступ неавторизованных пользователей к основной странице приложения должен быть запрещён.

6. Основная страница приложения должна содержать следующие элементы:

   - Набор полей ввода для задания координат точки и радиуса области в соответствии с вариантом задания: Select {'-5','-4','-3','-2','-1','0','1','2','3'} для координаты по оси X, Text (-3 ... 5) для координаты по оси Y, и Select {'-5','-4','-3','-2','-1','0','1','2','3'} для задания радиуса области. Если поле ввода допускает ввод заведомо некорректных данных (таких, например, как буквы в координатах точки или отрицательный радиус), то приложение должно осуществлять их валидацию.
   - Динамически обновляемую картинку, изображающую область на координатной плоскости в соответствии с номером варианта и точки, координаты которых были заданы пользователем. Клик по картинке должен инициировать сценарий, осуществляющий определение координат новой точки и отправку их на сервер для проверки её попадания в область. Цвет точек должен зависить от факта попадания / непопадания в область. Смена радиуса также должна инициировать перерисовку картинки.
   - Таблицу со списком результатов предыдущих проверок.
   - Кнопку, по которой аутентифицированный пользователь может закрыть свою сессию и вернуться на стартовую страницу приложения.

7. Дополнительные требования к приложению:
   - Все результаты проверки должны сохраняться в базе данных под управлением СУБД PostgreSQL.
   - Для доступа к БД необходимо использовать Spring Data.
