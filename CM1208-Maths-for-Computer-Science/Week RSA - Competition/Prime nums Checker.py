import math
from decimal import Decimal, localcontext
n = 89070583376339280192849308590984949006652765307730596045403259237789832350191360089303112546224386454413792445706440233510353329721691642366523464611714593102399742342589636057074257723073796130721765199021963393116924935351164678520773640116616314703728619490351936154918990605204481813202465797302675940079

def is_prime(n):
    if n == 1:
        return (False)
    if n == 2:
        return(True)
    if n>2 and n%2==0:
        return (False)

    max_divisor = math.floor(math.sqrt(n))
    for d in range(3,int(1+ max_divisor),2):
        if n%d==0:
            return (False)

    return (True)


# for i in range (1,int(math.sqrt(n))): #Range is until the sqrt of the number
#      if is_prime(i) == True:
#          print(i)
#          with localcontext() as cont:
#              cont.prec = 310
#              if (Decimal(n) / Decimal(i))%1 == 0:
#                  print(i, " IS A FACTOR OF N")
#                  break

#After running this for 30 minutes it finally found a prime factor of n
#Which is (135096617)

#Public key(n , r) r = 827
#Private Key(n ,d)
# n = prime1 * prime2
r = 827
p = 135096617
q = 659310242952562462706592486997545978569195150961704659454227926653336051710246453390488027884698152392770815613439381931453941068869930639096043867713686665465499719675361197661035862378950585644359734034065141639460334786556236848778927159342682242766512943032111130916912527907375221788140452083287

C = 33340939430462791446611401522009468709465106790183588730097075820249656099241049400328467231009692723210586408145032484393454733815424737777426620424763993858819491218806153843044136224614450832103681958788294906956879712579512401210070342313129134296799916957070855508008342504723881877562055751355424818016

def inverseMod(a, m):
    for i in range(1,m):
        if ( m*i + 1) % a == 0:
            return ( m*i + 1) // a
    return None

d = inverseMod(r,(p-1)*(q-1))
# print(d)

decrypted = pow(C,d,n)
print(decrypted)

decryptedString = str(decrypted)

for i in range(0,len(decryptedString),2):
    hex = int(decryptedString[i])*10+int(decryptedString[i+1])
    print(chr(int(str(hex),16)),end="")

