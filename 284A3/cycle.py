import sys

def CycleDetection(input_lines, digraph_order):
    color = [0] * digraph_order
    parent = [-1] * digraph_order
    stack = []
    for i, line in enumerate(input_lines):
        if color[i] == 0:
            stack.append(i)
            numbers = [int(num) for num in line.split()]
            for number in numbers:
                


    # YOUR CODE GOES HERE.

while True:
    digraph_order = sys.stdin.readline().strip()

    if (digraph_order != ''):
        digraph_order = int(digraph_order)
        
        for i in range(digraph_order):
            input_lines = [sys.stdin.readline().strip() for _ in range(digraph_order)]

            
        # YOUR CODE GOES HERE.
    else:
        break
    digraph_order = sys.stdin.readline().strip()

    if (digraph_order != ''):
        digraph_order = int(digraph_order)
        
        for i in range(digraph_order):
            input = sys.stdin.readline().strip()
            
        # YOUR CODE GOES HERE.
    else:
        break
