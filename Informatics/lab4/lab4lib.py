import xmltodict, json

import time
start_time = time.clock()
with open("schedule.xml") as fobj:
    my_xml = fobj.read()

o = xmltodict.parse(my_xml)

print(json.dumps(o, indent=4))
print(time.clock() - start_time)