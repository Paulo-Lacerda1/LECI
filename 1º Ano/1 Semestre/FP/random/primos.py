import requests

resp = requests.get("https://www.rfc-editor.org/rfc/rfc16.txt")
print("resp.status_code:", resp.status_code)  # deve ser 200
print("resp.text:\n", resp.text)
Neste caso, o tipo do recurso pedido é texto simples, o que se pode confirmar pelo campo resp.headers['content-type'], que tem o valor 'text/plain; charset=utf-8'. Mas muitos recursos têm outros tipos de conteúdo. Por exemplo,

resp = requests.get("https://www.rfc-editor.org/rfc/rfc16.html")
print("resp.text:\n", resp.text[:300])

