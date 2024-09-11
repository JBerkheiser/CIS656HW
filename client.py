import socket

serverPort = 5000

s2 = socket.socket()

serverIP = input('What is the servers IP address?')
s2.connect((serverIP, serverPort))

data = s2.recv(1024).decode()
print("Received " + data)

retMessage = 'Received'
s2.sendall(retMessage.encode())

s2.close()