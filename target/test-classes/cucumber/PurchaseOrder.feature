
@tag
Feature: Submit the order from Ecommerce Website

 Background:
 	Given: I land on Ecommerce page
  
  @tag1
  Scenario Outline: Positive Test of Submitting the order
    Given User logged in with Username <username> and Password <password>
    When User add product <productname> to the cart
    And Checkout <productname> and sumbit the order
    Then <message> message is displayed on the CurrentPage

    Examples: 
      | username  							   | password 		| productname |		message  							|
      | nagarjunPractice@gmail.com | Nagarjun@123 | ZARA COAT 3 |	Thankyou for the order.	|
