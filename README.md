# Test integration in WSO2 ESB resource for pipeline

## About

This project consits in a WSO2 Multi Module project which builds a Carbon
Application artifacts, deploys in a local ESB instance and uses Cucumber to
run several test cases pointing to Wiremock as endpoint.

## Getting started

Clone this project in your local, go into the project folder and
run `mvn deploy`.

Please, start up an ESB instance before execute Maven deploy.

## Import project in WSO2 Developer Studio

Importing this project in WSO2 Developer Studio may require run
`mvn eclipse:clean eclipse:eclipse` command to set up the correct project
nature.
