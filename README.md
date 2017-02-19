How to Build
===
To build the server, run `mvn clean test` in the root directory. There is no build process to the "client" since it's just a HTML file.

How to Run
===
For the server, cd into the `target` directory and then run the server via `java -jar BankChallenge-0.0.1-SNAPSHOT.jar`. Add a `&` if you want to background the process.

To access the "client", load up the "index.html" file in your browser. You can load the `ledger1.json` file and then submit it to the server. The client page will accept a `.json` file. Any successful and erroneous transactions are displayed in the table. The links show all of the transactions and accounts (in raw JSON).

Notes
===
* There are tests that exist, but because of the nature of in-memory HSQLDB there are some problems with clearing the database and proceeding with tests.
* Everything is done via HTTP since that is not only a known transmission protocol.
