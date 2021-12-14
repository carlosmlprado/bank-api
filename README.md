Api for ING backend developer test on December/21.

APi was made with Spring Boot, Java 11, Jpa Interface and H2 in memory database.

Contains 2 Controllers, which are:

TransactionController: With the methods:
@PostMapping("/createTransaction")
Create transfer money transaction.

@GetMapping("/moneyReceivedAndSentInTransactions/{customerId}")
Get all money movimentations from a Customer.


BankAccountController: With the Methods:
@PostMapping("/createAccount")
Create accounts based in some rules.

@GetMapping("/listCustomerAccount/{customerId}")
List all customer accounts

@DeleteMapping("/deleteAccounts/{customerId}")
Delete all customer accounts, without affect transactions

To run it:
- Clone this repo to your machine
- Via command line, use 'cd' command to go the folder of the project
- Execute the command [mvn clean install] - It will generate the jar of the application 
- Execute the command [java -jar target/bank.api-0.0.1-SNAPSHOT.jar] to run the application

- You can use Postman to try these apis or download the front end repo -> [https://github.com/carlosmlprado/bank-api-front/tree/master]
- Clone the repo, go to the folder and run the commnads:
- [ng build]
- [ng serve --open]

to see customer_id, use this visual for H2:[http://localhost:8080/h2/]
JDBC URL: jdbc:h2:file:~/store-crud

Use this query after creating some customers:
[select * from customer c
inner join bank_account b on c.id = b.customer_id]