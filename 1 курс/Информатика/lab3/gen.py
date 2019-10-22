import random
def fact(n): # Рекурсивная функция для факториала
    answ = 1
    for i in range(n):
        answ *= i + 1
    return answ
def gen():
	answ = ''
	for i in range(3,0,-1):
		answ += str(random.randint(1,fact(i)))
	return answ
for i in range(5):
	print(gen(),end=' ')
