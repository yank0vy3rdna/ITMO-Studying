path = 'sourcecode.bcomp'
source = ''
dataFields = list()
programFields = list()
memory = {}
for i in range(int('FFF',16)):
	memory[i]=0
class variablesObj:
	def __init__(self):
		pass
	variables = {}
	addresses = {} 
	def genKey(self):
		for i in dataFields:
			trig = True
			for j in self.addresses.keys():
				if i == self.addresses[j]:
					trig = False
					break
			if trig:
				return i
		raise MemoryError()
	def append(self,a):
		self.variables[a.name] = a
		self.addresses[a.name] = a.adr
		memory[a.adr] = a.value
	def getByName(self,name):
		if name in self.variables.keys():
			return self.variables[name]
		else:
			return None
varibls = variablesObj()
class variable:
	def __init__(self,name,value):
		self.name = name
		self.value = value
		self.adr = varibls.genKey()
		varibls.append(self)
	def get(self):
		return self.value
	def getAddr(self):
		return '0'*(3-len(str(self.adr)))+str(self.adr)


commandsAddresses = []

def addCommand(a):
	for i in programFields:
		if i not in commandsAddresses:
			commandsAddresses.append(i)
			memory[i] = a
			break


commands =[]
with open(path) as file:
	source = file.read().replace('\n','').split('нахуй')
for i in source:
	splt = i.replace('  ',' ').split(' ')
	if len(splt[0])>0:
		if splt[0][0]=='@':
			if splt [0][1:] == 'помойка':
				begin = int(splt[2], 16)
				end = int(splt[4], 16)
				dataFields = list(range(begin,end+1))
			if splt [0][1:] == 'завод':
				begin = int(splt[2], 16)
				end = int(splt[4], 16)
				programFields = list(range(begin,end+1))
		if splt[0] == 'внатуре':
			variable(splt[1],int(splt[2],16))
		if splt[0] == 'загрузи':
			addCommand(int('A'+varibls.getByName(splt[1]).getAddr(),16))
		if splt[0] == 'выйди':
			addCommand(int('0580',16))
		if splt[0] == 'рамсыпопутал':
			addCommand(int('0280',16))
		if splt[0] == 'стрельнул':
			addCommand(int('0700',16))
		if splt[0] == 'выгрузи':
			addCommand(int('0700',16))
for i in list(memory.keys())[:35]:
	print(hex(memory[i])[2:],end=' ')