def convert(q):
    int (q)
    res = '' #РїСѓСЃС‚Р°СЏ СЃС‚СЂРѕРєР°
    while q > 0:
        b = str(q%16)
        if b =='10':
            b = 'A'
        if b == '11':
            b = 'B'
        if b == '12':
            b = 'C'
        if b == '13':
            b = 'D'
        if b == '14':
            b = 'E'
        if b == '15':
            b = 'F'
        q=q//16
        res = b + res
    return res

def factorial(b):
    if b==1:
        return 1
    if b==0:
        return 1
    else:
        return factorial(b-1)*b
def prat(t):
    a=0
    b=len(t) #РґР»РёРЅР° СЃС‚СЂРѕРєРё. "spam" = 4
    for i in range(len(t)):
        x=int(t[i])*factorial(b)
        b-=1
        a+=x
    return a


f=open('kek.txt','r')
a=[]
we=[]
line1=f.readline()
while line1: #РїРѕРєР° РµСЃС‚СЊ СЃС‚СЂРѕРєР° line1 РёСЃРїРѕР»РЅСЏРµРј С†РёРєР» while
    d=line1.split() #split С‡С‚РѕР±С‹ СЂР°Р·Р±РёС‚СЊ СЃС‚СЂРѕРєСѓ РїРѕ РїСЂРѕР±РµР»Р°Рј
    we.append(d)
    a.append(d.copy()) # Р·Р°РїРёСЃС‹РІР°РµРј СЃРѕРґРµСЂР¶РёРјРѕРµ РїРµСЂРµРјРµРЅРЅРѕР№ d РІ РјР°СЃСЃРёРІ a
    line1=f.readline()

print(id(a[0]),id(we[0]))

for i in range(4):#СЃС‡РµС‚С‡РёРє РёРґРµС‚ СЃ 0, (n СЂР°Р· ). i - СЃС‡РµС‚С‡РёРє СЃС‚СЂРѕРє
    for j in range(2,7): # РёРґРµС‚ СЃ 0, 1,(2) СЌР»РµРјРµРЅС‚Р°
        a[i][j]= int(a[i][j])
        a[i][j]=convert(a[i][j])
print(we)

print(we == a)