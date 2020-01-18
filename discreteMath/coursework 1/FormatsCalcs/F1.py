a = 0.5
import math
m, e = math.frexp(a)
def float_to_binary(num):
    exponent=0
    shifted_num=num
    while shifted_num != int(shifted_num):        
        shifted_num*=2
        exponent+=1
    if exponent==0:
        return '{0:0b}'.format(int(shifted_num))
    binary='{0:0{1}b}'.format(int(shifted_num),exponent+1)
    integer_part=binary[:-exponent]
    fractional_part=binary[-exponent:].rstrip('0')
    return '{0}.{1}'.format(integer_part,fractional_part)
def getmandx(a):
	bina = float_to_binary(a)
	print(bina)
	p = bina.find('.')-1
	if p == 0:
		pass
	#binam = 
	print(p)
getmandx(a)