Feature: Valid Paths

  Background:
    Given Each test is independent of each other

  Scenario: Basic add user
    When User "u1" created
    Then System outputs "Success!"

  Scenario: Add/Remove User
    Given User "u1" created
    When Remove user "u1"
    And User "u1" created
    Then System outputs "Success!"

  Scenario: Add Title
    When Title "t1" with ISBN "1111111111111" exists
    Then System outputs "Success!"

  Scenario: Add/Remove Title
    Given Title "t1" with ISBN "1111111111111" exists
    When Remove title "1111111111111"
    And Title "t1" with ISBN "1111111111111" exists
    Then System outputs "Success!"

  Scenario: Add Item
    Given Title "t1" with ISBN "1111111111111" exists
    When Add item with isbn "1111111111111"
    Then System outputs "Success!"

  Scenario: Add multiple items
    Given Title "t1" with ISBN "1111111111111" exists
    When Add item with isbn "1111111111111"
    And Add item with isbn "1111111111111"
    And Add item with isbn "1111111111111"
    Then System outputs "Success!"

  Scenario: Add/Remove multiple items
    Given Title "t1" with ISBN "1111111111111" exists
    And Add item with isbn "1111111111111"
    And Add item with isbn "1111111111111"
    And Add item with isbn "1111111111111"
    When Remove copy 1 of "1111111111111"
    And Add item with isbn "1111111111111"
    And Add item with isbn "1111111111111"
    Then System outputs "Success!"
  Scenario: Borrow
    Given User "u1" created
    And User "u2" created
    And Title "t1" with ISBN "1111111111111" exists
    And Title "t1" with ISBN "2222222222222" exists

    And Add item with isbn "1111111111111"
    And Add item with isbn "1111111111111"
    And Add item with isbn "1111111111111"

    And Add item with isbn "2222222222222"
    And Add item with isbn "2222222222222"
    And Add item with isbn "2222222222222"

    When User "u1" borrows copy 0 of "1111111111111"
    And User "u1" borrows copy 2 of "2222222222222"

    And User "u2" borrows copy 1 of "1111111111111"
    And User "u2" borrows copy 1 of "2222222222222"

    Then System outputs "Success!"

  Scenario: Remove Item
    Given Title "t1" with ISBN "1111111111111" exists
    And Title "t1" with ISBN "2222222222222" exists

    And Add item with isbn "1111111111111"
    And Add item with isbn "1111111111111"
    And Add item with isbn "1111111111111"

    And Add item with isbn "2222222222222"
    And Add item with isbn "2222222222222"
    And Add item with isbn "2222222222222"

    When Remove copy 1 of "1111111111111"
    
    And Remove copy 0 of "2222222222222"
    And Remove copy 1 of "2222222222222"
    And Remove copy 2 of "2222222222222"
    
    Then System outputs "Success!"

  Scenario: Remove title (no copies involved)
    Given Title "t1" with ISBN "1111111111111" exists
    When Remove title "1111111111111"
    Then System outputs "Success!"

  Scenario: Remove User
    Given User "u1" created
    When Remove user "u1"
    Then System outputs "Success!"

  Scenario: Remove title
    Given User "u1" created
    And Title "t1" with ISBN "2222222222222" exists
    And Add item with isbn "2222222222222"
    When User "u1" borrows copy 0 of "2222222222222"
    And User "u1" returns copy 0 of "2222222222222"
    And Remove copy 0 of "2222222222222"
    And Remove title "2222222222222"
    Then System outputs "Success!"

  Scenario: Renew Loan
    Given User "u1" created
    And Title "t1" with ISBN "1111111111111" exists
    And Add item with isbn "1111111111111"
    When User "u1" borrows copy 0 of "1111111111111"
    And User "u1" renews copy 0 of "1111111111111"
    Then System outputs "Success!"

  Scenario: Renew Loan valid max times
    Given User "u1" created
    And Title "t1" with ISBN "1111111111111" exists
    And Add item with isbn "1111111111111"
    When User "u1" borrows copy 0 of "1111111111111"
    And User "u1" renews copy 0 of "1111111111111"
    Then System outputs "Success!"

    When User "u1" renews copy 0 of "1111111111111"
    When User "u1" renews copy 0 of "1111111111111"
    Then System outputs "Success!"

    When User "u1" returns copy 0 of "1111111111111"

    Then System outputs "Success!"

  Scenario: Borrow
    
