import re
with open('schedule.xml') as f:
	ft = f.read()
	ft = re.sub('<[^/].*> ',,ft)
	print (ft)