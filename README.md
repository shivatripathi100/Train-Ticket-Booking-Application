Description
The project aims to provide a platform for purchasing tickets for London to France. It allows users to purchase tickets, view available seats, retrieve receipt details by email, remove users, and modify seats.

Features
Purchase Ticket: Users can purchase tickets for their desired destination.
View Users and Seats: Users can view the list of users and their corresponding seats for a specific section.
Retrieve Receipt Details: Users can retrieve receipt details by providing their email address.
Remove User: Admins can remove users from the system.
Modify Seat: Admins can modify the seat of a user.

Technologies Used
Java
Spring Boot
RESTful APIs
Maven

Setup Instructions
Clone the repository: git clone <repository-url>
Navigate to the project directory: cd cloudbeesproject
Build the project: mvn clean install
Run the application: mvn spring-boot:run
Access the application at: http://localhost:8080

Usage

Purchase Ticket:
Endpoint: POST /purchaseTicket
Payload: JSON data containing ticket details
Example: curl -X POST -H "Content-Type: application/json" -d '{"from":"London","to":"Paris","firstName":"Jane","lastName":"Doe","email":"jane@example.com","price":20.0,"seat":"A1","section":"A"}' http://localhost:8080/purchaseTicket

View Users and Seats:
Endpoint: GET /usersAndSeats/{section}
Example: curl http://localhost:8080/usersAndSeats/A

Retrieve Receipt Details:
Endpoint: GET /receiptDetails/{email}
Example: curl http://localhost:8080/receiptDetails/jane@example.com

Remove User:
Endpoint: DELETE /removeUser/{email}
Example: curl -X DELETE http://localhost:8080/removeUser/jane@example.com

Modify Seat:
Endpoint: PUT /modifySeat/{email}
Payload: String containing the new seat value
Example: curl -X PUT -H "Content-Type: text/plain" -d "B1" http://localhost:8080/modifySeat/jane@example.com
