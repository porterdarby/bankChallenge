How to Build
===
To build the server, run `mvn clean test` in the root directory.

How to Run
===
For the Server, cd into the `target` directory and then run the server via `java -jar BankChallenge-0.0.1-SNAPSHOT.jar`. Add a `&` if you want to background the process.

To access the "client", load up the "index.html" file in your browser. You can load the `ledger1.json` file and then submit it to the server. Any successful and erroneous transactions are displayed in the table. The links show all of the transactions and accounts.