import re
import time
start_time = time.clock()
class node: # Рекурсивный парсер XML
	def __init__(self,doc,depth): # Аргумент doc содержит DOM-код, включающий в себя единственный родительский тэг и его тело(где возможны другие тэги)
		self.doc = doc # Тело 
		self.depth = depth # Глубина рекурсии
		self.parse()
	def parse(self):
		tags = self.doc.split('<') # Получим список строк разделенных по символу открытия тэга
		self.inner = ''#Тело родительского тэга
		self.childscount = 0 # Счетчик прямых наследников
		self.childs = [] # Тэги прямых наследников
		depth = 0
		inn = ''
		for splitted in tags: #Выявление родительского тэга и прямых наследников в doc 
			if len(splitted) > 0:
				splitted = '<' + splitted
				newsplitted = ''
				inntemp = splitted[splitted.find('>')+1:].replace('\t','').replace('\n','')
				while True:
					if len(inntemp)>0:
						if inntemp[0] == ' ':
							inntemp = inntemp[1:]
						else:
							break
					else:
						break
				splitted = splitted[splitted.find('<'):splitted.find('>')+1]
				name = splitted[1:splitted.find(' ')]
				if name[-1]=='>':
					name = 	name[:-1]
				if splitted[-2] == '/':
					self.inner+=splitted + inntemp
					if depth > 1:
						self.childs[self.childscount-1] += splitted + inntemp
					elif depth == 1:
						self.childs.append(splitted)
						self.childscount+=1
					elif depth == 0:
						self.tag = splitted
						self.name = name
				elif name[0]=='/':
					depth -= 1
					if depth!=0:
						self.inner += splitted + inntemp
						self.childs[self.childscount-1] += splitted + inntemp
				else:
					if depth == 1: # Найден новый потомок
						self.childs.append('')
						self.childscount+=1
					if depth == 0: # Найден родитель
						inn = inntemp
						self.tag = splitted
						self.name = name
					else:
						self.inner+=splitted + inntemp
						self.childs[self.childscount-1] += splitted + inntemp
					depth += 1
		sourceargs = self.tag[len(self.name)+2:-1] 
		sourceargs = sourceargs.split(' ') # Список аргументов родительского тэга
		a = False
		newarr = []
		k = -1
		for i in sourceargs:
			if not a:
				newarr.append(i)
				k+=1
			else:
				newarr[k] += ' ' + i
			if i.count('"')%2==1:
				a = not a
		sourceargs=newarr
		self.args = {} # Словарь аргументов тэга 
		for i in sourceargs:
			a = i.split('=')
			if len(a)>1:
				self.args[a[0]]=a[1].replace('"','')
		if self.childscount == 0 and len(inn)>0:
			self.args['__content__'] = inn
		self.childnodes = [] # Экземпляры классов node вызваных для детей
		self.childsbynames = {} 
		for i in self.childs:
			self.childnodes.append(node(i,self.depth+1))
			if self.childnodes[-1].name in self.childsbynames.keys():
				self.childsbynames[self.childnodes[-1].name].append(self.childnodes[-1])
			else:
				self.childsbynames[self.childnodes[-1].name] = [self.childnodes[-1]]
	def print(self):
		print('-'*self.depth+' '+self.tag,self.args)
		for i in self.childnodes:
			i.print()
	def createjson(self,withname=True):
		json = ''
		if self.depth == 0:
			json += '{\n'
		json += '	'*(self.depth+1)
		if withname:
			 json += '"'+ self.name + '" : '
		json += '{\n'

		for i in self.childsbynames.keys():
			if len(self.childsbynames[i])>1:
				json += '	'*(self.depth+2)
				json += '"'+i+'" : [\n'
				for j in self.childsbynames[i]:
					json+=j.createjson(False)
				json = json[:-2]+'\n'
				json += '	'*(self.depth+2)
				json += '],\n'
			else:
				for j in self.childsbynames[i]:
					json+=j.createjson()
		for i in self.args.keys():
			json += '	'*(self.depth+2)
			json += '"@'+i+'" : "' + self.args[i] + '",'
			json += '\n'
		json = json[:-2]+'\n'
		json += '	'*(self.depth+1)

		json += '},\n'
		if self.depth == 0:
			json = json[:-2] +'\n'
			json += '}'
		return json
with open('schedule.xml') as f:
	ft = f.read()
	ft = '<par><a> <b a="lol"> kek</b> </a> <c> <b f="HATEMYLIFE"> </b> </c></par>'
	a = node(ft,0)
	print(a.createjson())
print(time.clock() - start_time)