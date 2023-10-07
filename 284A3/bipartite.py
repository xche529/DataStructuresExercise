import sys

def BipartiteDetection(node, color, node_color, stack, input_lines):
    color[node] = 1
    node_color[node] = 1
    stack.append(node)
    while stack:
        node = stack[-1]
        line = input_lines[node]
        numbers = [int(num) for num in line.split()]
        is_finished = True
        for number in numbers:
            if color[number] == 0:
                color[number] = 1
                if node_color[node] == 1:
                    node_color[number] = 2
                else:
                    node_color[number] = 1
                is_finished = False
                stack.append(number)
                break
            else:
                if node_color[node] == node_color[number]:
                    return False    
        if is_finished:
            stack.pop()
            color[node] = 2
    return True


while True:
    graph_order = sys.stdin.readline().strip()
    if (graph_order == '0'):
        break

    if (graph_order != ''):
        graph_order = int(graph_order)
        
        color = [0] * graph_order
        node_color = [0] * graph_order
        stack = []
        input_lines = [sys.stdin.readline().strip() for _ in range(graph_order)]
        for i, line in enumerate(input_lines):
            if (color[i] == 0):
                if (not BipartiteDetection(i, color, node_color, stack, input_lines)):
                    print(0)
                    break
            if (i == graph_order - 1):
                print(1)
    else:
        break
