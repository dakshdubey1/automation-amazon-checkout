The objective of this test automation project writnng few automation scripts for Amazon's e-commerce platform. This involves automating tests for various scenarios that users may encounter when adding items to their cart, updating shipping information, and completing purchases. 

**Tools and Technologies:**
* Programming Language: Java
* Automation Tool: Selenium WebDriver
* Testing Framework: Java & Maven project
* Build Tool: Git for version control

This has three testcases for the 3 scenarios in the _**amazonCheckout.java**_ class-
All these 3 cases have the @Test annotaion in them

1. _TC_01_VerifyAddingItemsToCart()_    -->Test Scenario: Adding Items to Cart
2. _TC_02_VerifyChangingShippingAddress()_  -->Test Scenario: Updating Shipping Information
3. _TC_03_VerifyRemovingItemsFromCart()_   -->Test Scenario: Removing Items from Cart

**Things to Keep in mind**
* Download or clone the repository into the local
* Any IDE should be present like IntelliJ or Eclipse for running
* Before running the 2nd testcases {TC_02_VerifyChangingShippingAddress() } 
  - **Please add your login credentials** for the amazon.com website. This testcase performs the function of changing the address on checkout page, so at the step of landing to Checkout page, it will ask you to login into your account. So make sure to add value in respective variables- *email* & *pass*
  - Also if you newly running, then it will also ask for OTP, please add the otp withing the 1min time duration to avoid the failures
  - Your amazon account should be having the more that one address added.
