from collections import deque

st = deque()

def main():
    Init()

def Init():
    choice = 0

    while True:
        print("1.) start the program")
        print("2.) exit the program")
        
        try:
            choice = int(input("input your choice: "))
        except ValueError:
            print("input just accept an int!")
            continue

        if choice == 1:
            user = AritmeticUser()
            stringStream = SeparateString(user)
            test = InfixToPostfix(stringStream)
        elif choice == 2:
            print("exit...")
            break
        else:
            print("input out of the range! just 1 - 2!")

def AritmeticUser():
    print("aritmetic must be separate with whitespace")
    return input("input aritmetic here: ")

def InfixToPostfix(ss):
    postfix = []
    st_nilai = deque()
    st.clear()

    for i in range(len(ss)):
        if ss[i] == "(":
            print("push: " + ss[i])
            st.appendleft(ss[i])
            showStack(st)

        elif ss[i] == ")":
            print("found: )")
            while st[0] != "(":
                x = st[0]
                st.popleft()
                kedua = st_nilai[0]; st_nilai.popleft()
                pertama = st_nilai[0]; st_nilai.popleft()
                hasil = countOperator(x, pertama, kedua)
                st_nilai.appendleft(hasil)
                postfix.append(x)
                print("pop: " + x)
                showStack(st)
            st.popleft()
            print("pop: (")

        elif isOperator(ss[i]):
            while len(st) > 0 and st[0] != "(" and precedence(st[0]) >= precedence(ss[i]):
                x = st[0]
                st.popleft()
                kedua = st_nilai[0]; st_nilai.popleft()
                pertama = st_nilai[0]; st_nilai.popleft()
                hasil = countOperator(x, pertama, kedua)
                st_nilai.appendleft(hasil)
                postfix.append(x)
                print("pop: " + x)
                showStack(st)
            print("push: " + ss[i])
            st.appendleft(ss[i])
            showStack(st)

        else:
            postfix.append(ss[i])
            st_nilai.appendleft(float(ss[i]))
            print("push to postfix: " + ss[i])
            showPostfix(postfix)

    while len(st) > 0:
        x = st[0]
        st.popleft()
        kedua = st_nilai[0]; st_nilai.popleft()
        pertama = st_nilai[0]; st_nilai.popleft()
        hasil = countOperator(x, pertama, kedua)
        st_nilai.appendleft(hasil)
        postfix.append(x)

    print("Final result for aritmetic : " + str(st_nilai[0]))
    return postfix

def showString(user):
    print(user)

def showVectorString(user):
    for val in user:
        print(val, end="")
    print(" ")

def showPostfix(pf):
    print("postfix: ", end="")
    for val in pf:
        print(val, end="")
    print(" ")

def showStack(st_op):
    print("stack: ", end="")
    for val in st_op:
        print(val + " ", end="")
    print(" ")

def isOperator(op):
    return op in ["+", "-", "*", "/", "^"]

def precedence(op):
    if op == "^":
        return 3
    elif op in ["/", "*"]:
        return 2
    elif op in ["+", "-"]:
        return 1
    else:
        return -1

def cekString(arr):
    for i in range(len(arr)):
        print(arr[i] + " ", end="")
    print(" ")

def EvaluatePostfix(value):
    result = 0
    st_op = deque()

    for i in range(len(value)):
        if not isOperator(value[i]):
            x = float(value[i])
            st_op.appendleft(x)
        elif isOperator(value[i]):
            kedua = st_op[0]; st_op.popleft()
            pertama = st_op[0]; st_op.popleft()

            if value[i] == "*":
                result = pertama * kedua
            elif value[i] == "^":
                tampung = pertama
                for j in range(int(kedua) - 1):
                    pertama *= tampung
                result = pertama
            elif value[i] == "/":
                result = pertama / kedua
            elif value[i] == "+":
                result = pertama + kedua
            elif value[i] == "-":
                result = pertama - kedua

            st_op.appendleft(result)

    result = st_op[0]
    return result

def countOperator(op, pertama, kedua):
    result = 0
    if op == "*":
        result = pertama * kedua
        print(f"operator : {op}-> {pertama}*{kedua}= {result}")
    elif op == "^":
        tampung = pertama
        for j in range(int(kedua) - 1):
            pertama *= tampung
        result = pertama
        print(f"pop operator : {op}-> {pertama}^{kedua}= {result}")
    elif op == "/":
        result = pertama / kedua
        print(f"pop operator : {op}-> {pertama}/{kedua}= {result}")
    elif op == "+":
        result = pertama + kedua
        print(f"pop operator : {op}-> {pertama}+{kedua}= {result}")
    elif op == "-":
        result = pertama - kedua
        print(f"pop operator : {op}-> {pertama}-{kedua}= {result}")
    return result

def stringToFloat(value):
    return float(value)

def SeparateString(ss):
    return ss.split(" ")

if __name__ == "__main__":
    main()