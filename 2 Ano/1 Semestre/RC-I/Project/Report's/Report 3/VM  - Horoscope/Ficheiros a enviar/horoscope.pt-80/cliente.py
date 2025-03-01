import socket
import struct
import sys

headerformat = '!BLL'

def send_message(conn, version, order, message):
    data = message.encode()
    size = len(data)
    header = struct.pack(headerformat, version, size, order)
    conn.sendall(header)
    if size > 0:
        conn.sendall(data)

def receive_message(conn):
    header_size = struct.calcsize(headerformat)
    header_data = conn.recv(header_size)
    if not header_data or len(header_data) < header_size:
        return None, None, None, None
    version, size, order = struct.unpack(headerformat, header_data)
    if size > 0:
        data = conn.recv(size)
        if not data:
            return version, size, order, None
        return version, size, order, data.decode()
    else:
        return version, size, order, ""

HOST = 'XXX.XXX.XXX.XXX'  # Ajustar caso o servidor esteja noutra VM/rede
PORT = 5005

try:
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((HOST, PORT))
except Exception as e:
    print("Não foi possível ligar ao servidor:", e)
    sys.exit(1)

order_out = 0

# Recebe primeiro a leaderboard
v, sz, o, msg = receive_message(s)
if msg:
    print(msg, end='')

# Recebe mensagem de boas-vindas
v, sz, o, msg = receive_message(s)
if msg:
    print(msg)

while True:
    guess = input("Introduza o seu palpite: ")
    send_message(s, 1, order_out, guess)
    order_out += 1

    v, sz, o, response = receive_message(s)
    if response is None:
        print("Conexão com o servidor perdida.")
        break

    print("Servidor:", response)
    if response == "correct":
        # Acertou, ver se pergunta jogar outra vez
        v2, sz2, o2, resp = receive_message(s)
        if resp:
            print("Servidor:", resp)
            answer = input()
            send_message(s, 1, order_out, answer)
            order_out += 1
            if answer.lower() != 'y':
                # Não quer jogar de novo, encerra
                break
            # Quer jogar de novo: receber leaderboard novamente
            v3, sz3, o3, lb = receive_message(s)
            if lb:
                print(lb, end='')
            v4, sz4, o4, msg2 = receive_message(s)
            if msg2:
                print(msg2)
            # Continua o ciclo para novo jogo
        else:
            # não recebeu resposta (perda de conexão), sai
            break

    elif response in ["too low", "too high", "Por favor, envie um número válido."]:
        # Continua no loop para novo palpite
        pass
    else:
        # Caso haja outra mensagem não esperada, continua o loop
        pass

s.close()

