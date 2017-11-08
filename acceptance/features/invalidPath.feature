Feature: Invalid Paths

  Background:
    Given Each test is independent of each other

  Scenario: Add redundant user
    Given User "Bob" created
    When Attempting to add new User "Bob"
    Then System outputs "The User Already Exists!"

  Scenario: Add redundant title
    Given Title "t1" with ISBN "1112223334449" exists
    When attempting to add an already existing title "1112223334449"
    Then System outputs "The Title Already Exists!"

  Scenario: Add item to non-existent title
    Given No title with ISBN "1112223334445" exists
    When Add item with isbn "1112223334445"
    Then System outputs "The Title Does Not Exists! Would you like to add it? (y/n)"

  Scenario: Borrow same copy
    Given User "u1" created
    And User "u2" created
    And Title "t1" with ISBN "1111111111111" exists
    And Add item with isbn "1111111111111"
    When User "u1" borrows copy 0 of "1111111111111"
    Then System outputs "Success!"
    And User "u2" borrows copy 0 of "1111111111111"
    Then System outputs "Item Not Available!"

  Scenario: Borrow renewed copy
    Given User "u1" created
    And User "u2" created
    And Title "t1" with ISBN "1111111111111" exists
    And Add item with isbn "1111111111111"
    When User "u1" borrows copy 0 of "1111111111111"
    And User "u1" renews copy 0 of "1111111111111"
    And User "u2" borrows copy 0 of "1111111111111"
    Then System outputs "Item Not Available!"

  Scenario: Borrow returned copy taken by other user
    Given User "u1" created
    And User "u2" created
    And Title "t1" with ISBN "1111111111111" exists
    And Add item with isbn "1111111111111"
    When User "u1" borrows copy 0 of "1111111111111"
    And User "u1" returns copy 0 of "1111111111111"
    And User "u2" borrows copy 0 of "1111111111111"
    And User "u1" borrows copy 0 of "1111111111111"
    Then System outputs "Item Not Available!"

  Scenario: Borrow over max limit
    Given User "u1" created
    And Title "t1" with ISBN "1111111111111" exists
    And Title "t2" with ISBN "2222222222222" exists
    And Title "t3" with ISBN "3333333333333" exists
    And Add item with isbn "1111111111111"
    And Add item with isbn "2222222222222"
    And Add item with isbn "3333333333333"
    When User "u1" borrows copy 0 of "1111111111111"
    And User "u1" borrows copy 0 of "2222222222222"
    And User "u1" borrows copy 0 of "3333333333333"
    Then System outputs "Maximum Borrowed Items Exceeded!"

  Scenario: Borrow over max limit after return
    Given User "u1" created
    And Title "t1" with ISBN "1111111111111" exists
    And Title "t2" with ISBN "2222222222222" exists
    And Title "t3" with ISBN "3333333333333" exists
    And Add item with isbn "1111111111111"
    And Add item with isbn "2222222222222"
    And Add item with isbn "3333333333333"
    When User "u1" borrows copy 0 of "1111111111111"
    And User "u1" borrows copy 0 of "2222222222222"
    And User "u1" returns copy 0 of "2222222222222"
    And User "u1" borrows copy 0 of "3333333333333"
    Then System outputs "Success!"
    And User "u1" borrows copy 0 of "2222222222222"
    Then System outputs "Maximum Borrowed Items Exceeded!"

  Scenario: Remove Non-existent Item
    Given Title "t1" with ISBN "1111111111111" exists
    And Add item with isbn "1111111111111"
    When Remove copy 1 of "1111111111111"
    Then System outputs "Item Entity Not Found!"

  Scenario: Remove Borrowed Item
    Given User "u1" created
    And Title "t1" with ISBN "1111111111111" exists
    And Add item with isbn "1111111111111"
    When User "u1" borrows copy 0 of "1111111111111"
    And Remove copy 0 of "1111111111111"
    Then System outputs "Outstanding Loan Exists!"

  Scenario: Remove borrowed/renewed item
    Given User "u1" created
    And Title "t1" with ISBN "1111111111111" exists
    And Add item with isbn "1111111111111"
    When User "u1" borrows copy 0 of "1111111111111"
    And User "u1" renews copy 0 of "1111111111111"
    And Remove copy 0 of "1111111111111"
    Then System outputs "Outstanding Loan Exists!"

  Scenario: Remove title that still has a copy
    Given Title "t1" with ISBN "1111111111111" exists
    And Add item with isbn "1111111111111"
    When Remove title "1111111111111"
    Then System outputs "Item Instance Exists. Cannot Delete Title!"

  Scenario: Remove title that has borrowed copy
    Given User "u1" created
    And Title "t1" with ISBN "1111111111111" exists
    And Add item with isbn "1111111111111"
    When User "u1" borrows copy 0 of "1111111111111"
    And Remove title "1111111111111"
    Then System outputs "Outstanding Loan Exists!"

  Scenario: Remove title that has a copy
    Given User "u1" created
    And Title "t1" with ISBN "1111111111111" exists
    And Add item with isbn "1111111111111"
    And Add item with isbn "1111111111111"
    When Remove copy 1 of "1111111111111"
    And Remove title "1111111111111"
    Then System outputs "Item Instance Exists. Cannot Delete Title!"

  Scenario: Remove Non-existent User
    When Remove user "u1"
    Then System outputs "The User Does Not Exist!"
    
  Scenario: Renew Returned Item
    Given User "u1" created
    And Title "t1" with ISBN "1111111111111" exists
    And Add item with isbn "1111111111111"
    When User "u1" borrows copy 0 of "1111111111111"
    And User "u1" returns copy 0 of "1111111111111"
    And User "u1" renews copy 0 of "1111111111111"
    Then System outputs "No Such Loan Exists!"