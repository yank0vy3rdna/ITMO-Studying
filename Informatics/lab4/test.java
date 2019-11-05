public class test {
    public static void main(String args[]) {
 
        int sum =0;
        int limit =1000000001;
 
        int arr[] = new int[10];
 
 
        for (int i=1; i < limit; i++){ //Цикл от 1 до 100000000 включительно
            String str = String.valueOf(i); // Получаю строковое представление числа
            for(int k=0; k< str.length(); k++ )
                sum = (int) (sum + Math.pow(Integer.parseInt(String.valueOf(str.charAt(k))), str.length())); // Выглядит чуть сложнее оригинала, однако это более эффективно с точки зрения потребления памяти
                                                    //String.valueOf(str.charAt(k)) - получение строки из одного символа, который в исходной строке стоит под индексом k
                                                    //Integer.parseInt() - получение из этой строки числа, то есть на выходе у нас числовое представление одной цифры
                                                    //Math.pow(a,b) = a в степени b 
            if(sum==i) //Если посчитанная сумма степеней всех цифр = исходному числу, то число выводится на экран как ответ
                System.out.println(sum);
 
            sum =0 ;
            str =null;
 
        }  
    }
 
}