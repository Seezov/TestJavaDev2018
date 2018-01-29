# TestJavaDev2018
Test Java task for internship
Алгоритм:
1) Считывается файл JSON.
2) Создаётся массив объектов типа Product.
3) Для каждого продукта ищется лучшее имя.

    3.1) Сначала создаём массив разных слов для данного списка возможных имен.

    3.2) Для каждого слова вычесляется его коефициент полезности по формуле - колличество вхождений слова в имена / колличество имен.

    3.3) Вычесляется колличество разных слов в имени. Это необходимо чтобы избежать ситуации, когда лучшим именем выбрали имя, которое состоит только из слова с найбольшим коефициентом, например слово "Toner" получило найбольший коефициент, тогда "лучшее" имя будет что-то типа "Toner Toner Toner Toner Toner Toner Toner".

    3.4) Для каждого имени вычесляется его коефициент полезности по формуле - сумма всех коефициентов слов * колличество разных слов. И выбирается лучшее имя.

    3.5*) Программа проверяет вибранное имя на адекватную последовательность слов.

4) Нахождение характеристики с помощью regex, которое выделит только числовое значение + единицу измерения.
5) Результат записывается в файл.

\* Это часть алгоритма не реализована, в коде присутствует только функция, которая вычисляет какое слово после какого чаще стоит в именах.
