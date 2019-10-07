f = open('input.txt')
def fact(n):
 answ = 1
 for i in range(n):
   answ *= i+1
 return answ
 
def convF(i):
 answ = 0
 for j in range(len(i)):
   answ += (ord(i[j])-48)*fact(len(i)-j)
 return answ
def intrl(a):
 answ = 0
 for i in range(len(a)):
   answ += (ord(a[i])-48)*10**(len(a)-i-1)
 return answ
def conv10to2(i):
 i = intrl(i)
 answ = ''
 while i > 0:
   answ += str(i % 2)
   i = i //2
 return answ[::-1]
def conv2to16(i):
 a = []
 i = i[::-1]
 for j in range(len(i)//4):
   a.append(i[j]+i[j+1]+i[j+2]+i[j+3])
 k=''
 if len(i)%4 != 0:
   k='0'*(4-len(i)%4)
 for j in range(len(i)%4):
   k+=i[-1-j]
 a.append(k)
 a.reverse()
 answ = ''
 for i in a:
   if i == '0000':
     answ+='0'
   if i == '0001':
     answ+='1'
   if i == '0010':
     answ+='2'
   if i == '0011':
     answ+='3'
   if i == '0100':
     answ+='4'
   if i == '0101':
     answ+='5'
   if i == '0110':
     answ+='6'
   if i == '0111':
     answ+='7'
   if i == '1000':
     answ+='8'
   if i == '1001':
     answ+='9'
   if i == '1010':
     answ+='A'
   if i == '1011':
     answ+='B'
   if i == '1100':
     answ+='C'
   if i == '1101':
     answ+='D'
   if i == '1110':
     answ+='E'
   if i == '1111':
     answ+='F'
 return answ
for line in f.readlines():
 line = line.replace('\n','')
 spl = line.split(' ')
 osn1 = spl[0]
 osn2 = spl[1]
 data = spl[2:]
 print(osn1, ' -> ',osn2, end=' : ')
 if osn1 == 'Ф':
   for i in data:
     if data[-1] == i:
       print( i, ' -> ', convF(i))
     else:
       print( i, ' -> ', convF(i),end = ' | ')
 if osn1 == '10':
   for i in data:
     if data[-1] == i:
       print( i, ' -> ', conv2to16(conv10to2(i)))
     else:
       print( i, ' -> ', conv2to16(conv10to2(i)),end = ' | ')
