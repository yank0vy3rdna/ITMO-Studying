#очищаю домашнюю директорию
chmod -R 777 lab0/
rm -R lab0/*

#1 часть
mkdir lab0
cd lab0
mkdir electivire2
mkdir gardevoir8
mkdir joltik4
mkdir electivire2/fraxure
mkdir gardevoir8/igglybuff
mkdir gardevoir8/shinx
echo -e Возможности  Overland=6 Surface=2 Jump=2 Power=2'\n'Intilligence=4 > electivire2/chansey
echo -e Развитые способности  Ice'\n'Body > electivire2/seel
echo -e Способности  Bug Bite Razor Leaf Struggle Bug Slash'\n'Helping Hand Leaf Blade X-Scissor Entraiment Swords Dance Leaf'\n'Storm > electivire2/leavanny
echo -e Способности  Blaze Focus Flash Fire Inner'\n'Focus > gardevoir8/monferno
echo -e Тип покемона  BUG ELECTRIC> gardevoir8/joltik
echo -e Ходы  Dive'\n'Double-Edge Icy Wind Iron Defense Sleep Talk Snore > joltik4/clamperl
echo -e Тип диеты'\n'Carnivore>joltik4/luxray
echo -e satk=6 sdef=8 spd=10 > joltik4/drapion
echo -e Тип диеты'\n'Nullivore>joltik4/bronzong
echo -e satk=5 sdef=5 spd=7>krokorok7
echo -e Развитые'\n'способности  Analytic>starmie2
echo -e Способности  Yawn Poison Gas Sludge'\n'Amnesia Encore Body Slam Toxic Acid Spray Spit Up Stockpile Swallow'\n'Sludge Bomb Gastro Acid Wring Out Gunk Shot>swalot9


echo После 1 части 
tree -p

# 2 часть
chmod 513 electivire2
chmod 062 electivire2/chansey
chmod 400 electivire2/seel
chmod 404 electivire2/leavanny
chmod 700 electivire2/fraxure
chmod 513 gardevoir8
chmod 006 gardevoir8/monferno
chmod 640 gardevoir8/joltik
chmod 357 gardevoir8/igglybuff
chmod 770 gardevoir8/shinx
chmod 524 joltik4
chmod 060 joltik4/clamperl
chmod 044 joltik4/luxray
chmod 440 joltik4/drapion
chmod u-rwx,g=r,o=r joltik4/bronzong
chmod 004 krokorok7
chmod u=rw,g=w,o=r starmie2
chmod 400 swalot9


echo После 2 части 
tree -p
# 3 часть
# дам себе права, чтобы копировать все что угодно куда угодно
chmod -R 777 ./
cp -R gardevoir8 electivire2/fraxure
cp swalot9 joltik4/clamperlswalot
cat electivire2/leavanny electivire2/seel > starmie2_20
cp swalot9 gardevoir8/shinx/
ln -s gardevoir8 Copy_59
ln swalot9 gardevoir8/joltikswalot
ln -s starmie2 electivire2/leavannystarmie



echo После 3 части 
tree -p


# 4 часть
wc -m joltik4/luxray joltik4/drapion| sed '$d' | sort -r 2>1 

ls  -lR 2>/dev/null |grep l$ | sort -k 5

cd gardevoir8
cat -n $(ls -p| grep -v /)|sort -k2
cd ..

ls -R joltik4 |sort 2>1

ls -lR | grep se | sort -k 2 | tail -4 2>1

mkdir tmp
touch tmp/err
ls -ltRr | grep rwx |awk '{print $NF}'|grep ^j |tail -2 2>tmp/err





#5 часть

rm starmie2
rm joltik4/luxray
rm Copy_*
rm gardevoir8/joltikswal*
rm -R joltik4
rm -R gardevoir8/igglybuff


echo После 5 части 
tree -p