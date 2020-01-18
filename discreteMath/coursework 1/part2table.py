print('y a1 a2 a3(b1) a4(b2) V c1 c2 c3 c4')
for a1 in range(0,2):
    for a2 in range(0,2):
        for a3 in range(0,2):
            for a4 in range(0,2):
                for a5 in range(0,2):
                    y=a1
                    c1=0
                    c2=0
                    c3=0
                    c4=0
                    c5=0
                    A = '-'
                    B = '-'
                    C = '-'
                    if y == 0:
                        A = a2*8+a3*4+a4*2+a5*1
                        C = A - 1
                        c1 = 0 #cf
                        bC = '0'*(4-len(bin(C)[2:]))+bin(C)[2:]
                        c2 = bC[0]
                        c3 = bC[1]
                        c4 = bC[2]
                        c5 = bC[3]
                    if y == 1:
                        A = a2*2+a3
                        B = a4*2+a5
                        C = A - B
                        bC = '0'*(2-len(bin(C)[2:]))+bin(C)[2:]
                        c4 = 'd'
                        c5 = 'd'
                        c2 = bC[0]
                        c3 = bC[1]
                        if C < 0:
                            c1 = 1
                            c2 = 'd'
                            c3 = 'd'
                    print(a1,a2,a3,a4,a5,c1,c2,c3,c4,c5,A,B,C)


text = '111100000001001000110100010101100111100010011010101111001101111000dd11dd10dd01dd01dd00dd11dd10dd10dd01dd00dd11dd11dd10dd01dd00dd'
text = text.replace('\n','')
def getc(ind):
    answ = ''
    for i in range(32):
        answ += text[i*4+ind]
    return answ

c1 = getc(0)
c2 = getc(1)
c3 = getc(2)
c4 = getc(3)

def karno(a):
    print(a[0],a[4],a[12],a[8],sep='\t')
    print(a[1],a[5],a[13],a[9],sep='\t')
    print(a[3],a[7],a[15],a[11],sep='\t')
    print(a[2],a[6],a[14],a[10],sep='\t')
print('c1')
karno(c1[0:16])
print()
karno(c1[16:])
print('c2')
karno(c2[0:16])
print()
karno(c2[16:])
print('c3')
karno(c3[0:16])
print()
karno(c3[16:])
print('c4')
karno(c4[0:16])
print()
karno(c4[16:])
import sys
sys.stdout.write('hui')