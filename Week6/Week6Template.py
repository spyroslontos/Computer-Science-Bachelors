"""
>>> sumHighest([1,2,3,4], 2)
7
>>> sumHighest([1,2,3,4], 4)
10
>>> sumHighest([1,2,3,4], 0)
0
>>> sumHighest([], 0)
0
>>> sumHighest([1,2,3,4], -1)
0
>>> sumHighest([1,2,3,4], 10)
10
>>> type(sumHighest([1,2,3,4], 2))
<class 'int'>

Is the function only 2 lines?
>>> import inspect
>>> len(inspect.getsourcelines(sumHighest)[0]) == 2
True
"""

# *** DO NOT CHANGE CODE ABOVE THIS LINE ***
# *** DO NOT ADD ANY COMMENTS OF YOUR OWN IN THE SUBMITTED SOLUTION ***

def sumHighest(a, n):
    return (sum(a[-n::])) if n > 0 else False