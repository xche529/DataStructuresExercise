import sys

def CycleDetection(node, input_lines, color, stack):
    stack.append(node)
    color[node] = 1
    while stack:
        node = stack[-1]
        line = input_lines[node]
        numbers = [int(num) for num in line.split()]
        is_finished = True
        for number in numbers:
            if color[number] == 0:
                stack.append(number)
                color[number] = 1
                is_finished = False
                break
            elif color[number] == 1:
                return True
        if is_finished:
            stack.pop()
            color[node] = 2
    return False



    # YOUR CODE GOES HERE.

while True:
    digraph_order = sys.stdin.readline().strip()
    if (digraph_order == '0'):
        break

    if (digraph_order != ''):
        digraph_order = int(digraph_order)
        
        color = [0] * digraph_order
        #parent = [-1] * digraph_order
        stack = []
        input_lines = [sys.stdin.readline().strip() for _ in range(digraph_order)]
        for i, line in enumerate(input_lines):
            if color[i] == 0:
                if CycleDetection(i, input_lines, color, stack):
                    print(1)
                    break
            if i == digraph_order - 1:
                print(0)
            
        # YOUR CODE GOES HERE.
    else:
        break
