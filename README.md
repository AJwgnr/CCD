# JChess

Repository containing the semester project for the Clean Code Development lecture in winter semester 2018/2019 at the Otto-von-Guericke university.
Summary of the important design decisions for the chess game: 
[Google Document](https://docs.google.com/document/d/13iWmZYBP489AM4gHOpRqNQZMlV8fGJtFW0Jh3fqnxEE/edit#)

Please stick to the following project Rules:

## Organization:
The implementation is done in weekly sprints. Releases are created every week. Meaning that the changes in the development branch are merged into the master branch and a tagged released is created from this.

## Issues: 
Use the [Jira](https://ccd.ovgu.de/jira/secure/Dashboard.jspa) system for reporting any bugs or ideas for the features. Every implementation should relate to a jira issue/bug.

## Branching: 
Stick to the gitflow branching Model! Use the jira ticket in the branch names to simplify the mapping to the jira system:

* **master (protected branch)**: contains a running version of our code
* **develop (protected branch)**: contains the current status of the developement
* **release-XX**: contains the weekly created releases from the master branch
* **feature-DEEP-XX-XXXXX**: forked from the development for implementing new features
* **bugfix-DEEP-XX-XXXXX**: forked from the master branch and contains important bug fixes

## Tests:
Tests are highly significant investigations to provide developers and even stakeholders with certain information about the software.
Therefore, every implemented class should have a corresponding test class.
That test class should comprise as many testing methods as are necessary to ensure a proper behavior of the specific class.
In general, test methods should exactly check one particular property or concept. Furthermore, they should be written in an reproducible and independent way (in terms of eg. other test classes). 

#### Subsystem Tests
To verify that particular components are working correctly use [JUnit](https://junit.org/junit5/) as an unit test framework for testing implemented methods.
When the usage of a mocking framework is convenient, we decided to use [Mockito](https://site.mockito.org/) to mock certain objects.
These tests will be automatically executed after a build request.

#### System Integration Tests
The CI-Server will check the test output of the system and thus guarantee that all components are working correctly together. 

#### Test Documentation
The CI-Server keeps also track of the test history.

#### Code Coverage Analysis
Use the open source framework [PMD](https://pmd.github.io/) to observe whether any implemented structures violate any clean code principle.
This will improve the legibility and therefore increases the workflow (and many more benefits).

## CI:
The [CI system](https://ccd.ovgu.de/bamboo/allPlans.action) has one general execution plan for building and testing the software on the master branch. All forked branches automatically create a new execution plan on the CI server which inherits all tasks from the general execution plan. The CI server checks the test, the checkstyle and pmd conformance.

## Merging:
Use Pull Request to merge changes back into the development and master branch. Merging your changes back in the **development branch requires one review** while merging into the **master branch requires two approving reviews**.
Only merge if the CI checks (tests) are passed, which are automatically checked in the pull request.

