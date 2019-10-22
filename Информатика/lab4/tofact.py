x = 1234
n = ""
r = 2
 
while x > 0:
    y = str(x % r)
    n = y + n
    x = int(x // r)
    r = r + 1
 
print(n)