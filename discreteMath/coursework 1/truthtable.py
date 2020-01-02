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
	#print(i, x12345, x321, x321i, x54, x54i, absdiff,f)
def toBool(a):
	if a == 1:
		a = True
	if a == 0:
		a = False
	return a
for i in range(32):
	x12345 = bin(i)[2:]
	x12345 = '0'*(5-len(x12345)) + x12345
	x1 = int(x12345[0])
	x2 = int(x12345[1])
	x3 = int(x12345[2])
	x4 = int(x12345[3])
	x5 = int(x12345[4])
	x1 = toBool(x1)
	x2 = toBool(x2)
	x3 = toBool(x3)
	x4 = toBool(x4)
	x5 = toBool(x5)
	res = (not x1 and not x2 and x4) or (not x1 and x4 and x5) or (not x2 and x5) or(not x3)
	res = int(res)
	print(x12345,res)