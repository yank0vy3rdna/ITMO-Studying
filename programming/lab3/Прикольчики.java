// Object.equals()
public boolean equals(Object obj) {
        return (this == obj);
    }
// String.equals() - сравнивает значения строк, а == - ссылки на объекты
public boolean equals(Object strObject) {
    if (this == strObject) {
           return true;
    }
    if (strObject instanceof String) {
           String anotherString = (String) strObject;
           int n = count;
           if (n == anotherString.count) {
                 char v1[] = value;
                 char v2[] = anotherString.value;
                 int i = offset;
                 int j = anotherString.offset;

                 while (n-- != 0) {
                      if (v1[i++] != v2[j++])
                           return false;
                 }
                 return true;
           }
    }
    return false;
}
// Переопределяем equals в случае, например, если необходимо сравнивать объекты по полям, а не просто сравнивать ссылки на них
// String.hashCode()
public int hashCode() {
       int h = hash;
       if (h == 0) {
           int off = offset;
           char val[] = value;
           int len = count;

           for (int i = 0; i < len; i++) {
               h = 31*h + val[off++];
           }
           hash = h;
       }
       return h;
   }
//Integer.hashCode()
public int hashCode() {
       return value;
   }
/*
1) Если два объекта равны (т.е. метод equals() возвращает true), у них должен быть одинаковый хэш-код.

2) Если метод hashCode() вызывается несколько раз на одном и том же объекте, каждый раз он должен возвращать одно и то же число.

3) Правило 1 не работает в обратную сторону. Одинаковый хэш-код может быть у двух разных объектов.
*/

/* SOLID
Single responbility principle - если класс должен решать только одну задачу
Open-closed principle - сущность открыта для расширения и закрыта для модификации
Liskov substitution principle - свойство заменяемости родительского класса производными
Interface segregation principle - минимизация интерфейсов для того, чтобы не заставлять клиента имплементировать те интерфейсы, которыми он не будет пользоваться
Dependency inversion principle - зависимость от абстракций, но не наоборот
*/

/* STUPID
Singleton - один экземпляр некоторого класса, легко доступный всем клиентам
Tight coupling - высокая связанность классов
Untestability - невозможность тестирования
Premature optimization - преждевременная оптимизация 
Indescriptive naming - кривое именование переменных
Duplication - дублирование кода
*/

