fin = open("input.txt") 
line1=fin.readline() 
line2=fin.readline() 
c=1 
d=1 
e=1 
n=int(line1) 
a=line2.split() 
b=line2.split() 
a = [float(item) for item in a] 
for i in range(n-1): 
	for j in range(n-i-1): 
		if a[j] > a[j+1]: 
			a[j], a[j+1] = a[j+1], a[j] 
for i in a: 
	print(i,end = ' ', file=fout) 
for i in b: 
	print(i,end = ' ', file=fout) 
for i in range(n-1): 
	if (a[0]==b[i]): 
		d=i 
	if (a[n-1]==b[i]): 
		e=i 
	if (a[n//2]==b[i]): 
		c=i 
print(d,' ',e,' ',c) 
fout.close()