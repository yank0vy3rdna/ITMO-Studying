def swap(a):
	a = list(a)
	a[3],a[2]=a[2],a[3]
	return a
dctx10 = {'00':{},'01':{},'11':{},'10':{}}
dctx11 = {'00':{},'01':{},'11':{},'10':{}}
dct = {}
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
	if x12345[0] == '0':
		dctx10[x12345[1]+x12345[2]][x12345[3]+x12345[4]] = f
	if x12345[0] == '1':
		dctx11[x12345[1]+x12345[2]][x12345[3]+x12345[4]] = f
print('  ',end='')
for i in swap(dctx10['00'].keys()):
	print(i,end=' ')
print()
for i in dctx10.keys():
	print(i,end=' ')
	for j in swap(dctx10[i].keys()):
		print(dctx10[i][j],end=' ')
	print()
print('  ',end='')
for i in swap(dctx10['00'].keys()):
	print(i,end=' ')
print()
for i in dctx11.keys():
	print(i,end=' ')
	for j in swap(dctx11[i].keys()):
		print(dctx11[i][j],end=' ')
	print()