a = 134
b = 180
def normilizebin(inp):
	if inp[0]!='-':
		return '0'*(9-len(inp))+inp
	return bin(int('1'*(8-len(inp[1:]))+inp[1:],2)+1)[2:]
def getcomplement(inp):
	if inp < 0:
		return normilizebin('-'+bin(-inp).replace('0','k').replace('1','0').replace('k','1')[2:])
	return normilizebin(bin(inp)[2:])
def add(a,b):
	print(' Апр | ',end='')
	ac=getcomplement(a)
	print(ac[0]+' | '+ac[1:])
	print('+    | ')
	print(' Bпр | ',end='')
	bc = getcomplement(b)
	print(bc[0]+' | '+bc[1:])
	print('-------------')
	print(' Cпр | ',end='')
	cc = getcomplement(int(ac,2)+int(bc,2))
	print(cc[0]+' | '+cc[1:])
add(-a,b)