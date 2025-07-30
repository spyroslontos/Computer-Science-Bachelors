input = "ETEVHTWGSAHGWYVPNKQOEGWYVPNKPDEPHWAOVWPFWNHANEVWXAVOA"

def decrypting(x, y):
    output = ""
    for ch in input:
        if ch >= 'A' and ch <= 'Z':
            code = ord(ch) - ord('A')
            newCode = (x*code + y) % 26
            newCh = chr(newCode + ord('A'))
            output = output + newCh
        else:
            output = output + ch
    print(output)

for x in range(1,21):
    for y in range(1,21):
        decrypting(x, y)
