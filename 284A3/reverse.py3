import sys

while True:
    digraph_order = sys.stdin.readline().strip()
    if (digraph_order == '0'):
        print('0')
        break

    if (digraph_order != ''):
        digraph_order = int(digraph_order)
        outer_list = [[] for _ in range(digraph_order)]
        input_lines = [sys.stdin.readline().strip() for _ in range(digraph_order)]

        for i, line in enumerate(input_lines):
            numbers = [int(num) for num in line.split()]
            for number in numbers:
                outer_list[number].append(i)
        #print(digraph_order)
        print(digraph_order)
        for i in range(digraph_order):
            print(" ".join(map(str, outer_list[i])))
    else:
        break


    