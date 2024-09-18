import socket
import datetime
from threading import Thread

def main():
    ip = '127.0.0.1'
    port = 5000
    clientNumber = 0


    s1 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s1.bind((ip, port))
    print("The server is running")
    s1.listen()

    while True:
        connection, address = s1.accept()
        clientNumber += 1
        clientThread = Thread(target = handleClient, args = (connection, address, clientNumber))
        clientThread.start()

def handleClient(client_socket, client_address, client_value):  
    welcomeMessage = "Hello, you are client #" + str(client_value)
    client_socket.sendall(welcomeMessage.encode('utf-8'))

    while True:
        try:
            message = client_socket.recv(1024).decode('utf-8')
            if not message:
                break
            if(message.lower() == 'time'):
                responseMessage = datetime.datetime.now().strftime("%a %b %d %H:%M:%S %Z %Y")
            else:
                responseMessage = message.upper()
            client_socket.sendall(responseMessage.encode())
        except ConnectionResetError:
            break
    print("Client #",client_value," disconnected")
    client_socket.close()

if __name__ == "__main__":
    main()