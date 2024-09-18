import socket

serverPort = 5000

s2 = socket.socket()

serverIP = input('What is the servers IP address?')
s2.connect((serverIP, serverPort))

data = s2.recv(1024).decode('utf-8')
print(data)

while True:
    serverRequest = input("Enter a string to send to the server")
    if(serverRequest == ''):
        break
    else:
        s2.sendall(serverRequest.encode('utf-8'))
    
    data = s2.recv(1024).decode()
    print(data)

s2.close()