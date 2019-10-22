f = open('lab3input.txt') # Открываю файл для чтения

sootv = {0: '0', 1 : '1', 2 : '2', 3 : '3', 4 : '4', 5 : '5', 6 : '6', 7 : '7', 8 : '8', 9 : '9', 10 : 'A', 11 : 'B', 12 : 'C', 13 : 'D', 14 : 'E', 15 : 'F'}

def fact(n): # Функция для факториала
    answ = 1
    for i in range(n):
        answ *= i + 1
    return answ

def convF(i): # Перевод из факториальной системы счисления(тип данных str) в десятичную(int)
    answ = 0
    for j in range(len(i)):
        answ += int(i[j])*fact(len(i) - j)
    return answ

def conv10to16(i):
    i = int(i)
    answ = ''
    while i > 0:
        answ += sootv[i % 16]
        i = i // 16
    return answ[::-1]

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
                print( i, ' -> ', conv10to16(i))
            else:
                print( i, ' -> ', conv10to16(i),end = ' | ')