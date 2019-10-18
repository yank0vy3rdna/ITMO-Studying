for i in range(32):
	x12345 = bin(i)[2:]
	x12345 = '0'*(5-len(x12345)) + x12345
	x321 = x12345[2]+x12345[1]+x12345[0]
	x54 = x12345[4]+x12345[3]
	x321i = int(x321,2)
	x54i = int(x54,2)
	absdiff = abs(x321i-x54i)
	f = 0
	if absdiff == 0:
		f = 'd'
	if absdiff in list(range(1,4)):
		f = 1
	print(i, x12345, x321, x321i, x54, x54i, absdiff,f)