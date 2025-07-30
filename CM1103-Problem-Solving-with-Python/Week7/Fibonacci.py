from functools import lru_cache

@lru_cache(maxsize=1000)

def fibonacci(n):
    #compute the Nth term
    if n == 1:
        return(1)
    elif n==2:
        return(2)
    elif n>2:
        return(fibonacci(n-1) + fibonacci(n-2))

for n in range(1,1001):
    print(fibonacci(n))
    #Golden Ratio
    #print(fibonacci(n+1)/fibonacci(n))