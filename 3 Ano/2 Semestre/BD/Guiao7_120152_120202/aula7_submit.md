# BD: Guião 7


## 7.2

### *a)*

```
A relação está na 1FN, assumindo que todos os atributos são atómicos.

Chave candidata:
{Titulo_Livro, Nome_Autor}

Não está na 2FN, porque existem dependências parciais:

Nome_Autor -> Afiliacao_Autor

Como Nome_Autor é apenas parte da chave composta, Afiliacao_Autor depende parcialmente da chave.

Logo, a relação Livro está apenas na 1FN.
```

---

### *b)*

```
Relação inicial:

Livro(Titulo_Livro, Nome_Autor, Afiliacao_Autor, Tipo_Livro, Preco, NoPaginas,
Editor, Endereco_Editor, Ano_Publicacao)

Chave:
{Titulo_Livro, Nome_Autor}


1FN:

A relação já está na 1FN, porque não existem atributos multivalor, compostos ou relações dentro de relações.


2FN:

Remover a dependência parcial:

Nome_Autor -> Afiliacao_Autor

Decomposição:

-> Livro_2FN(_Titulo_Livro_, _Nome_Autor_, Tipo_Livro, Preco, NoPaginas,
Editor, Endereco_Editor, Ano_Publicacao)

-> Autor(_Nome_Autor_, Afiliacao_Autor)


3FN:

Remover as dependências transitivas:

Tipo_Livro, NoPaginas -> Preco

Editor -> Endereco_Editor

Decomposição final:

-> Livro(_Titulo_Livro_, _Nome_Autor_, Tipo_Livro, NoPaginas,
Editor, Ano_Publicacao)

-> Autor(_Nome_Autor_, Afiliacao_Autor)

-> Preco_Livro(_Tipo_Livro_, _NoPaginas_, Preco)

-> Editor(_Editor_, Endereco_Editor)
```




## ​7.3
 
### *a)*

```
Chave de R: (A,B)
```


### *b)* 

```
R1 (A,B,C)
R2 (A,D,E,I,J)
R3 (B,F,G,H)
```


### *c)* 

```
R1 (A,B,C)
R2 (A,D,E)
R3 (D,I,J)
R4 (B,F)
R5 (F,G,H)
```


## 7.4

### *a)*

```text
Chaves candidatas:

{A, B}
{B, C}
```

### *b)*

```text
3FN:

-> R1(_A_, _B_, C, D)

-> R2(_D_, E)
```

### *c)*

```text
BCNF:

-> R1(_D_, E)

-> R2(_C_, A)

-> R3(_B_, _C_, D)
```



## ​7.5
 
### *a)*

```
Chave de R: (A,B)
```

### *b)* 

```
R1 (A,B,E)
R2 (A,C,D)
```


### *c)* 

```
R1 (A,B,E)
R2 (A,C)
R3 (C,D)
```

### *d)* 

```
As tabelas da alinea c) já estão na forma BCNF.
```
