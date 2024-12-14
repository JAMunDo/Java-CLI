## Sources used:
1 . JFrog help page - (https://jfrog.com/help/r/jfrog-rest-apis/users)

2 . Baeldung - (https://www.baeldung.com)

3 . GeeksForGeeks - ( https://www.geeksforgeeks.org )

## Decisions:

I created my own CLI using Java as this was the language I am most familiar with at the moment. 
I tried to keep this CLI as simple as possible with coding and logic to not consume too much time and resources.
The program effectively communicates using an API and utilizes various HTTP methods. 
I made sure to reuse code where possible to minimize the coding needed and make the program easy to understand for anyone who looks at the code. I decided to add an extra method to assist users with user deletion. This method retrieves all the current active users to better assist the user with deleting a user if they so wish.

## Read Me:
Good day user,

Welcome to the Compuzign CLI. 
This CLI was made to communicate with a cloud computing provider. 
To use this program, you will have to login with an existing username and password. 
From there you will be able to trigger any of the menu options. When creating a new user, 
the password should be at least 8 characters long and have 1 symbol,1 number, 1 uppercase character and 1 lowercase number.
Users with the same username cannot be created and will be rejected. Emails must have an '@' symbol and '.com' at the end.
Repos created will have the type of repo attached to the name of the repo eg. Repo name = libs-test Repo type = local , new repo name = libs-test-local.
You can type the command "--help" from the main menu to get the commands used for the CLI. You can type "exit" from the main menu to close the program.
 