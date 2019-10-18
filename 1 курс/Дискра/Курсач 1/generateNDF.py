form = ''
for i in range(32):
	x12345 = bin(i)[2:]
	x12345 = '0'*(5-len(x12345)) + x12345
	x321 = x12345[2]+x12345[1]+x12345[0]
	x54 = x12345[4]+x12345[3]
	x321i = int(x321,2)
	x54i = int(x54,2)
	absdiff = abs(x321i-x54i)
	if absdiff in list(range(1,4)):
		for i in range(len(x12345)):
			if x12345[i] == '0':
				form+='NOTx'+str(i+1)
			if x12345[i] == '1':
				form+='x'+str(i+1)
		form += ' + '
print(form)