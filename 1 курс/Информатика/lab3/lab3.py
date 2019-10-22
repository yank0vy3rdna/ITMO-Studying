f = open('lab3input.txt') # Открываю файл для чтения

sootv = {'0000': '0', '0001': '1', '0010': '2', '0011': '3', '0100': '4', '0101': '5', '0110': '6', '0111': '7', '1000': '8', '1001': '9', '1010': 'A', '1011': 'B', '1100': 'C', '1101': 'D', '1110': 'E', '1111': 'F'}

def fact(n): # Функция для факториала
    answ = 1
    for i in range(n):
        answ *= i + 1
    return answ

def convF(i): # Перевод из факториальной системы счисления(тип данных str) в десятичную(int)
    answ = 0
    for j in range(len(i)):
        answ += int(i[j])*fact(len(i)-j)
    return answ

def conv10to2(i): # Перевод десятичного числа(str) в двоичное(str)
    i = int(i)
    answ = ''
    while i > 0:
        answ += str(i % 2)
        i = i // 2
    return answ[::-1]

def conv2to16(i): # Перевод из двоичной (str) в шестнадцатиричную (str)
    a = []
    i = i[::-1] # реверс строки с числом
    for j in range(len(i)//4):
        a.append(i[j + 3] + i[j + 2] + i[j + 1] + i[j]) # Записываю в массив все полные тетрады(в обратном порядке, тк)
    k = ''
    if len(i)%4 != 0: # Дополняю нулями неполную тетраду
        k = '0' * (4 - len(i) % 4)
        for j in range(len(i)%4):
            k += i[-1 - j]
        a.append(k) # Записываю недостающую тетраду в массив 
    a.reverse() # Возвращаю тетрадам изначальный порядок
    answ = ''
    for i in a: # В зависимости от тетрады записываю в строку очередную шестнадцатиричную цифру
        answ += sootv[i]
    return answ

for line in f.readlines(): # Построчное чтение из файла
    line = line.replace('\n','')
    spl = line.split(' ')
    osn1 = spl[0]
    osn2 = spl[1]
    data = spl[2:]
    print(osn1, ' -> ',osn2, end= ' : ')
    if osn1 == 'Ф': # Выполняю все переводы
        for i in data:
            if id(data[-1]) == id(i):
                print( i, ' -> ', convF(i))
            else:
                print( i, ' -> ', convF(i),end = ' | ')
    if osn1 == '10':
        for i in data:
            if id(data[-1]) == id(i):
                print( i, ' -> ', conv2to16(conv10to2(i)))
            else:
                print( i, ' -> ', conv2to16(conv10to2(i)),end = ' | ')