import socket
import datetime

ip = '127.0.0.1'
port = 5000

s1 = socket.socket()
s1.bind((ip, port))
print("The server is running")
s1.listen()
connection, address = s1.accept()

currentDateTime = datetime.datetime.now().strftime("%a %b %d %H:%M:%S %Z %Y")
connection.sendall(currentDateTime.encode())

message = connection.recv(1024).decode()
print(message)
