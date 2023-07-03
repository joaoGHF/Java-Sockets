<h1>Java Sockets</h1>
<p>É uma aplicação Java com os métodos main a serem executados no servidor em <a href="src/Server.java">Server.java</a> e no cliente em <a href="src/Client.java">Client.java</a>.</p>

<p>Faz uso da classe <a href="src/Encryption.java">Encryption.java</a> para encriptar e gerar o hash das mensagens a fim de comprovar a integridade. E da classe <a  href="src/ClientHandler.java">ClientHandler.java</a>, para o tratamento de cada cliente em uma thread separada.</p>

<p>Toda a documentação na forma de javadocs está em <a href="docs/">docs</a>.</p>

<p>Os valores do <strong>endereço</strong> e da <strong>porta</strong> do servidor no método <em>main()</em> da classe <a href="src/Client.java">Client.java</a> e os valores do número da <strong>porta</strong> e de <strong>threads</strong> no método <em>main()</em> da classe <a href="src/Server.java">Server.java</a> <strong>podem ou devem</strong> sofrer <strong>alterações.</strong></p>
