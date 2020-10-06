# Product Service Example

##Overview
This project demonstrates use of SpringBoot to stand up a RESTful API.
The example is very basic, just consisting of an API to display
a list of products. Each product has the following fields: -
 * Product ID
 * Product Name
 * Product Description
 * Price
 
 The service consumes in turn the [fixer.io](https://www.google.com) service.
 
 In order to use it, you will have to sign up for an API key.
 Once you have done that, edit `application.properties` replacing the key given there
 with your one.

The service returns JSON data looking like this:

Request: http://localhost:8080/products?currency=USD

JSON response: 

``json
[
{
id: 1,
name: "widget",
description: "finely-honed, polished green widget",
price: "USD 0.5889085"
},
{
id: 2,
name: "spanner",
description: "superior 13mm open-ended chromium steel spanner",
price: "USD 3.533451"
},
{
id: 3,
name: "guide-book",
description: "extremely comprehensive introduction and guide to all things unrelated to the current business",
price: "USD 23.55634"
}
]
``

An invalid currency code will result in an HTTP 400 status code and a message.

Request: http://localhost:8080/products?currency=XYZ
Plain text response: `Unrecognised currency code: XYZ`

## Questions:

 * How would you handle different failure scenarios in your application?
 
 This simple application handles one failure scenario, namely what happens when an invalid
 currency code is passed.  The basic approach it uses is to define a `@ControllerAdvice` component
 and `@ExceptionHandler`; with that, the code that encounters the bad scenario just throws the custom exception
 and SpringBoot handles it. I would envisage adding more exception handlers in the
 same way.
 
 In reality, there are different kinds of errors, for example:
  1. Initialisation errors, for example when the fixer.io service is unavailable or applying rate limitation.
  These would be best handled at startup, with the either application terminating early, or better switching over
  to display a holding message (HTTP 503 service unavailable). 
  2. Internal errors caused by unexpected errors conditions (for example, corrupt data returned by Fixer.io): 
  these would result in an HTTP 500 status code if they triggered e.g. a `NullPointerException`.
 
 * What other features would you include to run in your application in a production environment?
 
     - *Logging* and logfile post-processing
     - *Monitoring* facilities e.g. a heartbeat endpoint to enable checks that the server is running
     - *Persistence*: More than likely the data would be cached in a persistence tier such as a database, to 
     prevent loss of the rates' data and ensure service continuity if the link to fixer.io or
     other currency rate providers goes down temporarily.
     - *Flexibility*: With a superior grade of subscription to fixer.io than the free service, there would be options to
       make the calls for rates more flexible (e.g. to get just a subset of currency rates, each time the user queries for
       the price of the catalog, rather than just at server startup).
     - *Security*: given that we are making product pricing data available, although this may be
     a world-visible thing (e.g. the price of baked beans in Tesco!), it will probably be something
     for which we want to restrict access, not least to prevent robots operated by competitors from screen-scraping our price data.
      The main aspects of security are authentication and authorisation. The former would permit
      sales for example to identify which customers are enquiring about which products, to be
      able to target sales more precisely.  Authorisation would be relevant for example where the pricing has different tiers depending
      on the identity and role of the requestor (an affiliated dealer would get different pricing
      from just anybody out there).
       - *Robustness and Failover*: to ensure continuous availability.
       
