import re

class node: # Рекурсивный парсер XML
	def __init__(self,doc,depth):
		self.doc = doc
		self.depth = depth
		self.parsenode()

	def parsenode(self):

		self.tag = re.match('<[^/].*>',self.doc).group(0)
		self.name = self.tag.split(' ')[0][1:]
		if self.name[-1]=='>':
			self.name = self.name[:-1]
		sourceargs = self.tag[len(self.name)+2:-1]
		sourceargs = sourceargs.split(' ')
		self.args = {}
		for i in sourceargs:
			a = i.split('=')
			if len(a)>1:
				self.args[a[0]]=a[1].replace('"','')
		self.inner=self.doc[self.doc.find('<',len(self.tag)):self.doc.rfind('</')]
		self.countchilds = 0
		depth = 0
		childs = []
		for i in self.inner:
			if i == '<':
				if depth == 0:
					self.countchilds += 1
					childs.append('')
				depth += 1

			elif i == '/' and depth > 0:
				depth -= 1
			childs[self.countchilds-1] += i

		self.childnodes = []
		self.childsbynames = {}
		for i in childs:
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
			json += '"_'+i+'" : "' + self.args[i] + '",'
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
	a = node(ft,0)
	print(a.createjson())