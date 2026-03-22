# 📚 Sistema de Gestão de Biblioteca (SGB)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Apache Ant](https://img.shields.io/badge/Apache%20Ant-A81C7D?style=for-the-badge&logo=apache-ant&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green.svg?style=for-the-badge)

> **Projeto Acadêmico SENAI Betim**  
> Sistema para controle de acervo bibliográfico desenvolvido com foco em arquitetura em camadas e padrões de projeto.

---

## 🚀 Sobre o Projeto

O **SGB** é uma aplicação desktop desenvolvida para consolidar conceitos de **Engenharia de Software**. O projeto utiliza o padrão **MVC (Model-View-Controller)** e **DAO (Data Access Object)** para garantir uma separação clara entre a interface, a lógica de negócio e a persistência de dados.

---

## ✨ Funcionalidades

- 🔍 **Busca e Filtros:** Pesquisa por *Status* e *Categoria*  
- 📊 **Dashboard de Acervo:** Visualização com `JTable`  
- 📝 **CRUD Completo:** Cadastro, edição e exclusão  
- 💾 **Persistência JDBC:** Integração com MySQL  

---

## 🛠️ Tecnologias

| Categoria | Tecnologia |
|----------|----------|
| Linguagem | Java |
| Interface | Swing |
| Build | Apache Ant |
| Banco | MySQL |
| Modelagem | UML |

---

## 📂 Estrutura do Projeto

```text
sistema-gestao-biblioteca/
├── docs/
│   ├── diagramas/
│   │   ├── 1.png
│   │   ├── 2.png
│   │   └── 3.png
│   └── telas/
├── src/
````

---

## 📊 Diagramas UML

### 🔹 Diagrama 1

![Diagrama 1](docs/diagramas/1.png)

### 🔹 Diagrama 2

![Diagrama 2](docs/diagramas/2.png)

### 🔹 Diagrama 3

![Diagrama 3](docs/diagramas/3.png)

---

## 📸 Interface

<div align="center">
<img src="docs/telas/1.png" width="400"/>
<img src="docs/telas/2.png" width="400"/>
</div>

---

## 👥 Equipe

* **Matheus Gustavo** — Backend / Líder
* **Davi Moreno** — Full Stack
* **Bryan Irios** — UI/UX + UML

---

## ⚙️ Como Executar

```bash
# Rodar com Apache Ant
ant run
```

### Passos:

1. Importar `database.sql` no MySQL
2. Abrir no NetBeans
3. Adicionar `mysql-connector`
4. Executar (F6)

---

## 📝 Licença

MIT License

---