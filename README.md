# 💬 Chat-App

A simple **real-time chat application** built using **Java** and **Socket Programming**.  
Demonstrates client-server communication and allows multiple users to chat in real-time.  

---

![Java](https://img.shields.io/badge/Language-Java-orange?style=flat-square) ![Sockets](https://img.shields.io/badge/Networking-Sockets-blue?style=flat-square) ![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)

---

## ✨ Features

- 💻 Real-time messaging between clients and server  
- 👥 Multi-client support  
- 📝 Simple command-line interface (CLI)
-    GUI based on java

---

## 🛠 Tech Stack

- **Language:** Java  
- **Networking:** Java Sockets (TCP)  
- **Architecture:** Client-Server Model  

---

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher  
- IDE or text editor (IntelliJ, Eclipse, VS Code, etc.)

   
## 📂 Project Structure
- Chat-App/
- ├── Server.java     # Handles incoming client connections
- ├── Client.java     # Connects to the server and sends/receives messages
- └── README.md       # Project documentation


### How to Run

1. **Clone the repository**

```bash
git clone https://github.com/rohitshinde08/Chat-App.git
cd Chat-App

Compile and run the server
javac Server.java
java Server

Compile and run the client(s)
javac Client.java
java Client

