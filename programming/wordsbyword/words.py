letters = []
word = input('Введите слово: ')
for i in word:
	if i not in letters:
		letters.append(i)
words = []
k = 0
with open("zdf-win.txt", encoding = "WINDOWS-1251") as file:
	r = [i[0:-1] for i in file.readlines()]
	e = []
	for i in r:
		if len(i)>=2:
			e.append(i)
	for string in e:
		newletters = letters.copy()
		k+=1
		a = True
		for i in string:
			if i in newletters:
				newletters.remove(i)
			else:
				a = False
				break
		if a:
			words.append(string)
		print('Выполнено: ',round(k/len(r)*100,1),'%, количество найденных слов: ',len(words))
	print(len(e))
print(words)