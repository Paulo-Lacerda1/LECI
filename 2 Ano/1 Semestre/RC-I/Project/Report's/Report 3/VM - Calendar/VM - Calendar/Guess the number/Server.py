import socket
import threading
import signal
import sys
import struct
import random
import os

LEADERBOARD_FILE = 'leaderboard.txt'
headerformat = '!BLL'  # B=1 byte (version), L=4 bytes (size), L=4 bytes (order)

def signal_handler(sig, frame):
    print('\nDone!')
    sys.exit(0)

signal.signal(signal.SIGINT, signal_handler)
print('Press Ctrl+C to exit...')

def load_leaderboard():
    leaderboard = {}
    if os.path.exists(LEADERBOARD_FILE):
        with open(LEADERBOARD_FILE, 'r') as f:
            for line in f:
                line = line.strip()
                if not line:
                    continue
                ip, attempts = line.split()
                leaderboard[ip] = int(attempts)
    return leaderboard

def save_leaderboard(leaderboard):
    with open(LEADERBOARD_FILE, 'w') as f:
        for ip, attempts in leaderboard.items():
            f.write(f"{ip} {attempts}\n")

def get_leaderboard_str(leaderboard):
    if not leaderboard:
        return "----- Leaderboard -----\nSem registos.\n-----------------------\n"
    sorted_lb = sorted(leaderboard.items(), key=lambda x: x[1])
    res = "----- Leaderboard -----\n"
    for ip, score in sorted_lb:
        res += f"{ip}: {score} tentativas\n"
    res += "-----------------------\n"
    return res

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

def start_new_game():
    # Função para iniciar um novo jogo, gera um novo número e reinicia tentativas
    secret_number = random.randint(0, 1000)
    attempts = 0
    return secret_number, attempts

def play_game(client_socket, address, leaderboard):
    ip = address[0]
    order_out = 0

    # Mostrar leaderboard ao cliente
    send_message(client_socket, 1, order_out, get_leaderboard_str(leaderboard))
    order_out += 1

    # Mensagem de boas-vindas
    send_message(client_socket, 1, order_out, "Bem-vindo ao Guess the Number! Adivinhe um número entre 0 e 1000.")
    order_out += 1

    while True:
        # Inicia um novo jogo
        secret_number, attempts = start_new_game()

        # Loop das tentativas do jogo atual
        while True:
            v, s, o, msg = receive_message(client_socket)
            if msg is None:
                print(f"Client {ip} disconnected.")
                return
            guess_str = msg.strip()
            if not guess_str.isdigit():
                send_message(client_socket, 1, order_out, "Por favor, envie um número válido.")
                order_out += 1
                continue

            guess = int(guess_str)
            attempts += 1

            if guess < secret_number:
                send_message(client_socket, 1, order_out, "too low")
                order_out += 1
            elif guess > secret_number:
                send_message(client_socket, 1, order_out, "too high")
                order_out += 1
            else:
                # Acertou!
                send_message(client_socket, 1, order_out, "correct")
                order_out += 1
                print(f"Client {ip} acertou o número em {attempts} tentativas.")

                # Atualiza leaderboard se for a primeira vez ou se melhorou o recorde
                if ip not in leaderboard or attempts < leaderboard[ip]:
                    leaderboard[ip] = attempts
                    save_leaderboard(leaderboard)

                # Pergunta se deseja jogar novamente
                send_message(client_socket, 1, order_out, "Deseja jogar novamente? (y/n)")
                order_out += 1
                v2, s2, o2, resp = receive_message(client_socket)
                if resp is None or resp.strip().lower() != 'y':
                    # Cliente não quer jogar novamente
                    send_message(client_socket, 1, order_out, "Obrigado por jogar! A conexão será encerrada.")
                    order_out += 1
                    return
                else:
                    # Cliente quer jogar novamente
                    send_message(client_socket, 1, order_out, get_leaderboard_str(leaderboard))
                    order_out += 1
                    send_message(client_socket, 1, order_out, "Novo jogo! Adivinhe um número entre 0 e 1000.")
                    order_out += 1
                    break  # Sai do loop atual e inicia um novo jogo no próximo loop do while True

def handle_client_connection(client_socket, address, leaderboard):
    print('Accepted connection from {}:{}'.format(address[0], address[1]))
    try:
        play_game(client_socket, address, leaderboard)
    except (socket.timeout, socket.error):
        print('Client {} error. Done!'.format(address))
    finally:
        client_socket.close()

ip_addr = "0.0.0.0"
tcp_port = 5005

leaderboard = load_leaderboard()

server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.bind((ip_addr, tcp_port))
server.listen(5)
print('Listening on {}:{}'.format(ip_addr, tcp_port))

while True:
    client_sock, address = server.accept()
    client_handler = threading.Thread(
        target=handle_client_connection,
        args=(client_sock, address, leaderboard),
        daemon=True
    )
    client_handler.start()
