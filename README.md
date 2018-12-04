# JChess

Repository containing the semester project for the Clean Code Development lecture in winter semester 2018/2019 at the Otto-von-Guericke university.
This document contains a summary of the important design decisions/rules for the chess game: 
[Google Document](https://docs.google.com/document/d/13iWmZYBP489AM4gHOpRqNQZMlV8fGJtFW0Jh3fqnxEE/edit#)

Please stick to the following project rules:

## Development Organization

### Issue Tracking/Ticket resolution process: 
Make use of the issue tracking system [Jira](https://ccd.ovgu.de/jira/secure/Dashboard.jspa)
for reporting bugs, discuss new ideas for features and many more.
Before adding a new issue make sure that the following requirements are fulfilled:
* Every ticket needs a meaningful name (summary).
* Every ticket needs a (short) description to avoid any ambiguity.
* Every ticket needs a certain priority level, which indicates the importance of the ticket.

After the ticket has been created, any developer, who is capable and willing to work on a solution,
can assign the specific ticket to himself/herself. Subsequently, maintain a suitable status for your assigned ticket,
which informs foreign developers about your progress. Discuss problems in the comment section of the issue.

### Sprints:
To maintain a equal distribution of workload during the project we decided to use a weekly sprint strategy.
Therefore, any issue should be assigned to a particular sprint, in which the specific work has to be completed.
Tickets, which are not completed during the sprint, must be moved to the next sprint.
(For more information check out the following link: [Jira Help](https://ccd.ovgu.de/jira/secure/ShowConstantsHelp.jspa?decorator=popup#PriorityLevels).)



### Branching: 
Stick to the gitflow branching Model! Use the jira ticket in the branch names to simplify the mapping to the jira system. 
Fork branches from the development branch and create **Pull Request** to merge them back to develop. Once the team thinks the development branch contains a version which is stable and includes major features, merge the changes to the master branch.

* **master (protected branch)**: contains a running version of our code
* **develop (protected branch)**: contains the current status of the developement
* **feature-DEEP-XX-XXXXX**: forked from the development for implementing new features
* **bugfix-DEEP-XX-XXXXX**: forked from the master branch and contains important bug fixes

### Merging:
Use Pull Request to merge changes back into the development and master branch. Merging your changes back in the **development branch requires one review** while merging into the **master branch requires two approving reviews**.
The reviews are ensured using protected branches in the Pull Request. Only merge if the CI checks (tests) are passed, which are automatically checked in the pull request.


## Tests
Tests are highly significant investigations to provide developers and even stakeholders with certain information about the software.
Therefore, every implemented class should have a corresponding test class.
That test class should comprise as many testing methods as are necessary to ensure a proper behavior of the specific class.
In general, test methods should exactly check one particular property or concept. Furthermore, they should be written in an reproducible and independent way (in terms of eg. other test classes). 

### Subsystem Tests:
To verify that particular components are working correctly use [JUnit](https://junit.org/junit5/) as an unit test framework for testing implemented methods.
When the usage of a mocking framework is convenient, we decided to use [Mockito](https://site.mockito.org/) to mock certain objects.
These tests will be automatically executed after a build request.

### System Integration Tests:
The CI-Server will check the test output of the system and thus guarantee that all components are working correctly together. 


## Documentation
Please use JavaDoc notation to document your code. Use inline documentation but also comment whole methods and classes.


## CI:
The [CI system](https://ccd.ovgu.de/bamboo/allPlans.action) has one general execution plan for building and testing the software on the master branch. 
All forked branches automatically create a new execution plan on the CI server which inherits all tasks from the general execution plan. The CI server checks the Junit tests, the checkstyle and pmd conformance.
It will create an artifact for the executable jar and also for the **JavaDoc** report with the **checkstyle** and **pmd** results.


