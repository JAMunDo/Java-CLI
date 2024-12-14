package com.tech.novus;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.exit;

public class MainProgram {
    static final String GET_PING_URL = "https://compuzign.jfrog.io/access/api/v1/system/ping";
    static final String GET_SYSTEM_INFO = "https://compuzign.jfrog.io/access/api/v1/system/version";
    static final String CREATE_USER = "https://compuzign.jfrog.io/access/api/v2/users";
    static final String DELETE_USER = "https://compuzign.jfrog.io/access/api/v2/users/";
    static final String GET_STORAGE_INFO = "https://compuzign.jfrog.io/artifactory/api/storageinfo";
    static final String GET_LIST_OF_USERS = "https://compuzign.jfrog.io/access/api/v2/users";
    static final String CREATE_UPDATE_REPOSITORY = "https://compuzign.jfrog.io/artifactory/api/repositories/";
    static final String GET_ALL_REPOSITORY = "https://compuzign.jfrog.io/artifactory/api/repositories";
    static Scanner myObj = new Scanner(System.in);
    static  String repoType;
    static String repoName;
    static final String USERNAME = "Compuzign";
    static final String PASSWORD = "Test123";
    static String passwordRegexPattern = "^(?=.*[0-9])"
            + "(?=.*[a-z])(?=.*[A-Z])"
            + "(?=.*[@#$%^&+=!*()-])"
            + "(?=\\S+$).{8,20}$";
    static String emailRegexPattern = "^(.+)@(\\S+)$";
    static Pattern emailPattern = Pattern.compile(emailRegexPattern);
    static Pattern passwordPattern = Pattern.compile(passwordRegexPattern);
    static FileReader fileReader;
    public static void login() {
        String username,password;
        System.out.println("Please enter username: ");
        username = myObj.nextLine();
        System.out.println("Please enter password: ");
        password = myObj.nextLine();
        if(username.isEmpty() || password.isEmpty()){
            System.out.println("No values found for useername/password");
            login();
        }
        if(!username.equals(USERNAME) && !password.equals(PASSWORD )){
            System.out.println("Incorrect username/password:");
            login();
        }
        if(username.equals(USERNAME) && password.equals(PASSWORD)){
            mainMenu();
        }
    }

  public static void mainMenu(){
      System.out.println("MENU OPTIONS: \n" +
              "● System Ping\n" +
              "● System Version\n" +
              "● Create User\n" +
              "● Delete User\n" +
              "● Get All Users\n" +
              "● Get Storage Info\n" +
              "● Create Repository\n" +
              "● Update Repository\n" +
              "● List Repositories\n" +
              "● Exit \n" +
              "Type --help for commands");
        System.out.println("Please select an option from the menu:");
       String selection = myObj.nextLine();
       if(!selection.isEmpty()){
           options(selection);
       }else{
           System.out.println("No option selected");
           mainMenu();
       }
  }

  public static void options (String selection){
      {
          try {
              fileReader = new FileReader("secrets.properties");
          } catch (FileNotFoundException e) {
              throw new RuntimeException(e);
          }
      }
      Properties properties = new Properties();
      try {
          properties.load(fileReader);
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
      String token =properties.getProperty("token");
      switch (selection){
        case "-ping":
            Command.executeGetRequest(GET_PING_URL,token);
            break;
        case "-version":
            Command.executeGetRequest(GET_SYSTEM_INFO,token);
            break;
        case "create-user":
            Command.createUserPostBody(CREATE_USER,CreateUserName(),CreateUserPassword(), CreateUserEmail(),token);
            break;
        case "get-all":
            Command.executeGetRequest(GET_LIST_OF_USERS,token);
            break;
        case "del-user":
            Command.executeDeleteRequest(DELETE_USER,CreateUserName(),token);
            break;
        case "storage-info":
            Command.executeGetRequest(GET_STORAGE_INFO,token);
            break;
        case "create-repo":
            RepoNaming();
            RepoSelection();
            Command.createRepositoryJsonBody(CREATE_UPDATE_REPOSITORY,repoName,repoType,"PUT",token);
            break;
        case "update-repo":
            RepoNaming();
            RepoSelection();
            Command.createRepositoryJsonBody(CREATE_UPDATE_REPOSITORY,repoName,repoType,"POST",token);
            break;
        case "get-all-repo":
            Command.executeGetRequest(GET_ALL_REPOSITORY,token);
            break;
        case"--help":
            helpMenu();
            break;
        case"exit":
            System.out.println("Exiting CLI ");
            exit(0);
            break;
        default:
            System.out.println("No appropriate option found");
            break;
    }
    mainMenu();
  }

  public static void RepoNaming(){
      System.out.println("Please enter a repository name:");
      repoName = myObj.nextLine();
      if(repoName.isEmpty()){
          System.out.println("Please enter an appropriate repository name");
          RepoNaming();
      }
  }

    public static void RepoSelection(){
        int selection;
        System.out.println("Please select a repository type by inputting its associated number:");
        System.out.println("OPTIONS: \n" +
                "1 - Local\n" +
                "2 - Virtual\n" +
                "3 - Remote\n");
        selection = myObj.nextInt();
        switch (selection){
            case 1:
                repoType = "local";
                break;
            case 2:
                repoType = "virtual";
                break;
            case 3:
                repoType = "remote";
                break;
            default:
                System.out.println("Please select an appropriate type");
                RepoSelection();
                break;
        }
    }

    public static String CreateUserName(){
        System.out.println("Please enter username:");
        String username = myObj.nextLine();
        if(username.isEmpty()){
            System.out.println("Please enter an appropriate name");
            CreateUserName();
        }
        return username;
    }

    public static String CreateUserEmail(){
        System.out.println("Please enter an email for the user:");
        String email = myObj.nextLine();
        if(email.isEmpty()){
            System.out.println("Please enter an appropriate email");
            CreateUserEmail();
        }
        Matcher matchCheck = emailPattern.matcher(email);
        if(!matchCheck.matches()){
            System.out.println("Email does not match criteria");
            CreateUserEmail();
        }
        return email;
    }

    public static String CreateUserPassword(){
        System.out.println("Please enter a password for the user \n" +
                "(NB: Password should be atleast 8 characters long and include: \n" +
                "1 - lowercase character \n" +
                "1 - uppercase character \n" +
                "1 - number \n" +
                "1 - symbol):");
        String password = myObj.nextLine();
        if(password.isEmpty()){
            System.out.println("Password is empty");
            CreateUserPassword();
        }
        Matcher matchCheck = passwordPattern.matcher(password);

        // Return if the password
        // matched the ReGex
        if(!matchCheck.matches()){
            System.out.println("Password does not match criteria");
            CreateUserPassword();
        }
        return password;
    }

    public static void helpMenu(){
        System.out.println("COMPUZIGN CLI MENU: \n" +
                " -ping\t\t\t Pings the Jfrog server to check its health\n" +
                " -version\t\t Returns the version of the system\n" +
                " create-user\t Creates a new Access user.\n" +
                " del-user\t\t Delete an existing user based on the username\n" +
                " storage-info\t Returns storage summary information regarding binaries, file store and repositories\n" +
                " get-all\t\t Retrieves all users\n" +
                " create-repo\t Creates a new repository in Artifactory with the provided configuration (local,virtual,remote)\n" +
                " update-repo\t Updates an existing repository configuration in Artifactory with the provided configuration elements (local,virtual,remote)\n" +
                " get-all-repo\t Retrieves all repositories\n" +
                " exit\t\t\t Close the CLI\n" +
                " --help \t\t Show the help menu ");
    }
}
