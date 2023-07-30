# Budgeting App

<br/>

[![github actions ci badge]][github actions ci]

Group project for IDATT1002 Software Engineering spring 2023 at NTNU Ålesund.

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#description">Description</a>
      <ul>
        <li><a href="#frameworks-and-tools">Frameworks and Tools</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#Prerequisites"> Prerequisites </a></li>
        <li><a href="#Installation">Installation</a></li>
        <li>
          <ul>
            <li><a href="#Back-end">Back-end</a></li>
            <li><a href="#Front-end">Front-end</a></li>
          </ul>
        </li>
      </ul>
    </li>
    <li><a href="#Demonstration">Demonstration</a></li>
    <li><a href="#Application Functionality">Application Functionality</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

## Description  

Our project consists of developing a web application where users can manage their finances.

<p align="right"><a href="#top">Back to Top</a></p>

### Frameworks and Tools
This project was created using the following:

- [Spring boot](https://spring.io/) Java framework for back-end development
- [React](https://react.dev) JavaScript framework for front-end development
- [Loopia](https://www.loopia.no) service provider for web- and database-hosting
- [Postman](https://www.postman.com) software for testing API implementations 
- [Git](https://git-scm.com) for version control of source code
- [OpenStack](https://www.openstack.org) cloud software for server deployment (provided by NTNU for the course IDATA2306)
- [Apache Maven](https://maven.apache.org) software management tool for back-end management and comprehension

<p align="right"><a href="#top">Back to Top</a></p>

## Getting Started
### Prerequisites

- [Java JDK 17](https://jdk.java.net/archive/)
- [Maven (4.0.0)](https://maven.apache.org/)
- [Node.js and npm](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm)

### Installation

1. First step is to clone the repository, which can be done by executing the following command in the Terminal:

   ```sh
   git clone https://gitlab.stud.idi.ntnu.no/jorgenfinsveen/Budgeting-app.git
   ```

#### Back-end

1. Open a new Terminal window in the sub-directory of the cloned repository named _backend_

2. Package the Java application by executing the following command:

   ```sh
   mvn clean package
   ```

3. Start the back-end application by executing the following command:
   ```sh
   mvn spring-boot:run
   ```

4. The back-end application should now be running on [localhost:8090](http://localhost:8090)

5. When visiting the above URL in the web browser, the message "Error: Full authentication is required to access this resource" should appear


(0.) It is also possible to launch the back-end application by running the file named _BudgetApplication.jar_ located inside the backend directory by running the following command in the Terminal:
   ```sh
   java -jar BudgetApplication.jar
   ```

#### Front-end

1. Open a new Terminal window in the sub-directory of the cloned repository named _frontend_

2. Launch the front-end application by executing the following command:

   ```sh
   npm start
   ```

3. The front-end application should now be running on [localhost:3000](http://localhost:3000/)

4. When visiting the above URL in the web browser, the login-page for the application should appear.

5. User may create a new account, or they can sign it with a pre-made account with the following credentials:

  * Username: user
  * Password: user

<p align="right"><a href="#top">Back to Top</a></p>


## Demonstration

The application is deployed and can also be visited online. For further information regarding the deployment, please consult [DEPLOYMENT.md](https://gitlab.stud.idi.ntnu.no/jorgenfinsveen/Budgeting-app/-/blob/main/DEPLOYMENT.md).

* Link to website: [Budget Application Demo](http://haslerud.tech/login)
* Pre-made user:
  * Username: user
  * Password: user
* Feel free to create new accounts as well (username must be unique)

**Note**: There is no guarantee that the website works to the same degree as when the project is running on localhost. There has been some problems regarding CORS security and HTTP to HTTPS request forwarding, which may prevent login attempts. These are problems related to the settings of the given browser, the extensions installed, and other configurations. We would recommend to visit the demo application using a browser without extensions, and on either NTNU's network or using NTNU's VPN. 



## Application Functionality

<table>
    <thead>
        <th>User</th>
    </thead>
<tbody>
    <tr>
        <td>
            <h4>Account Management</h4>
            <ul>
                <li>Create a new account</li>
                <li>Log in to an existing account</li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>
            <h4>Analytics</h4>
            <ul>
                <li>See analytics of their own budget</li>
                <li>Display budget transactions at given dates</li>
                <li>Add, delete, or edit transactions</li>
                <li>Modify the budget margins, start- and end-date</li>
            </ul>
        </td>
    </tr>
    <tr>
        <td>
            <h4>Overview</h4>
            <ul>
                <li>Overview of categories and trends</li>
            </ul>
        </td>
    </tr>
</tbody>
</table>


## License
Distributed under NTNU. For educational purposes only

<p align="right"><a href="#top">Back to Top</a></p>


## Contact
  
[Jørgen Finsveen](https://github.com/jorgenfinsveen) - jorgfi@stud.ntnu.no

[Even Johan Pereira Haslerud](https://github.com/ejhasler?tab=overview&from=2023-04-01&to=2023-04-14) - ejhasler@stud.ntnu.no

[Håvard Hetland Vestbø](https://github.com/havardhvestbo) - haavarhv@stud.ntnu.no

[Petter Edvardsen](https://github.com/Edvardsn) - petteed@stud.ntnu.no

[Ole Kristan Dvergsdal](https://github.com/olekristianhd) - olekd@stud.ntnu.no

---

<p align="right"><a href="#top">Back to Top</a></p>



[github actions ci badge]:
    <https://github.com/jorgenfinsveen/Full-Stack-Budget-Application-Project/workflows/Java%20CI%20with%20Maven/badge.svg>
    "GitHub Actions CI Status"

[github actions ci]:
    <https://github.com/jorgenfinsveen/Full-Stack-Budget-Application-Project/actions>
    "GitHub Actions CI"
