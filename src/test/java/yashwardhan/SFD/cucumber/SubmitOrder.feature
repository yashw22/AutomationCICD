@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

	Background: 
		Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Sumbitting the order
    Given Logged in with email <email> and password <password>
    When I add product <prodName> to cart
    And Checkout <prodName> and submit the order
    Then "Thankyou for the order." message is displayed on ConfirmationPage

    Examples: 
      | email                   | password      | prodName        |
      | surajsharma67@gmail.com | SurajSharma67 | ZARA COAT 3     |
      #| surajsharma68@gmail.com | SurajSharma68 | ADIDAS ORIGINAL |