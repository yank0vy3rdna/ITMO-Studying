import xmltodict, json

import time
avrtime = 0
start_time = time.clock()
with open("schedule.xml") as fobj:
    my_xml = fobj.read()

o = xmltodict.parse(my_xml)
print(json.dumps(o, indent=4, ensure_ascii=False).replace('@',chr(3)).replace(chr(3),'@'))
avrtime+=(time.clock() - start_time)