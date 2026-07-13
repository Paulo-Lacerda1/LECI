# BD: Guião 5


## ​Problema 5.1
 
### *a)*

```
π Pname, Ssn, Fname, Minit, Lname ((project ⨝ Pnumber = Pno works_on) ⨝ Essn = Ssn employee)
```


### *b)* 

```
π Fname, Lname (employee ⨝ Super_ssn = CdG.Ssn (ρ CdG (π Ssn (σ Fname='Carlos' ∧ Minit = 'D' ∧ Lname = 'Gomes' employee))))
```


### *c)* 

```
γ Pname; sum(Hours) -> TotalHours (project ⨝ Pnumber = Pno works_on)
```


### *d)* 

```
π Fname, Lname (σ Dno = 3 ∧ Hours > 20 ∧ Pname = 'Aveiro Digital' (employee ⨝ Ssn = Essn works_on ⨝ Pno = Pnumber project))
```


### *e)* 

```
π Fname,Lname (σ works_on.Essn = null (employee ⟗ Ssn = Essn works_on))
```


### *f)* 

```
γ Dno; avg(Salary) -> AvgSalary (department ⨝ Dnumber = Dno employee)
```


### *g)* 

```
πFname,Lname (σcount>2 (γEssn; count(Dependent_name)->count dependent) ⨝ Essn=Ssn employee)
```


### *h)* 

```
π Fname,Minit,Lname, Ssn ((σ Super_ssn = null employee) ⨝ Ssn = Mgr_ssn department)
```


### *i)* 

```
π Fname,Lname,Address (employee ⨝ Ssn=Essn (works_on ⨝ Pno=Pnumber σ Plocation='Aveiro' project)) - π Fname,Lname,Address (employee ⨝ Dno=Dnumber σDlocation='Aveiro' dept_location)
```


## ​Problema 5.2

### *a)*

```
π fornecedor.nome, fornecedor.nif, encomenda.numero, encomenda.fornecedor (σ encomenda.fornecedor = null (encomenda ⟗ fornecedor = nif fornecedor))
```

### *b)* 

```
τ codProd (γ codProd; avg(unidades) -> AvgUni item)
```


### *c)* 

```
γ avg(CountProd) -> AvgProdEnc (γ numEnc; count(codProd) -> CountProd item)
```


### *d)* 

```
π fornecedor.nome, produto.nome, item.unidades (fornecedor ⨝ nif = fornecedor (encomenda ⨝ numero = numEnc (item ⨝ codProd = codigo produto)))
```


## ​Problema 5.3

### *a)*

```
π nome paciente - π nome (paciente ⨝ paciente.numUtente=prescricao.numUtente prescricao)
```

### *b)* 

```
γ especialidade; count(numPresc)->total (medico ⨝ numSNS=numMedico prescricao)
```


### *c)* 

```
γ farmacia; count(numPresc)->total prescricao
```


### *d)* 

```
π nome (σ farmaco.numRegFarm=906 farmaco) - π nome (σ farmaco.numRegFarm=906 (farmaco ⨝ farmaco.numRegFarm=presc_farmaco.numRegFarm presc_farmaco))
```

### *e)* 

```
γ farmacia,numRegFarm; count(numRegFarm)->total (prescricao ⨝ prescricao.numPresc=presc_farmaco.numPresc presc_farmaco)
```

### *f)* 

```
π nome (σ c>1 (γ numUtente; count(numMedico)->c prescricao) ⨝ paciente.numUtente=prescricao.numUtente paciente)
```

