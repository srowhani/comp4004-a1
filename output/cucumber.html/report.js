$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("invalidPath.feature");
formatter.feature({
  "line": 1,
  "name": "Invalid Paths",
  "description": "",
  "id": "invalid-paths",
  "keyword": "Feature"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 392387574,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "Add redundant user",
  "description": "",
  "id": "invalid-paths;add-redundant-user",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "User \"Bob\" created",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "Attempting to add new User \"Bob\"",
  "keyword": "When "
});
formatter.step({
  "line": 9,
  "name": "System outputs \"The User Already Exists!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "Bob",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 9088793,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Bob",
      "offset": 28
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddNewUser(String)"
});
formatter.result({
  "duration": 10584138,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "The User Already Exists!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 366743,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 96379,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Add redundant title",
  "description": "",
  "id": "invalid-paths;add-redundant-title",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 12,
  "name": "Title \"t1\" with ISBN \"1112223334449\" exists",
  "keyword": "Given "
});
formatter.step({
  "line": 13,
  "name": "attempting to add an already existing title \"1112223334449\"",
  "keyword": "When "
});
formatter.step({
  "line": 14,
  "name": "System outputs \"The Title Already Exists!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "t1",
      "offset": 7
    },
    {
      "val": "1112223334449",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 4961167,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1112223334449",
      "offset": 45
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddAnAlreadyExistingTitle(String)"
});
formatter.result({
  "duration": 12556180,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "The Title Already Exists!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 240142,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 168749,
  "status": "passed"
});
formatter.scenario({
  "line": 16,
  "name": "Add item to non-existent title",
  "description": "",
  "id": "invalid-paths;add-item-to-non-existent-title",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 17,
  "name": "No title with ISBN \"1112223334445\" exists",
  "keyword": "Given "
});
formatter.step({
  "line": 18,
  "name": "Add item with isbn \"1112223334445\"",
  "keyword": "When "
});
formatter.step({
  "line": 19,
  "name": "System outputs \"The Title Does Not Exists! Would you like to add it? (y/n)\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "1112223334445",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.noTitleWithISBNExists(String)"
});
formatter.result({
  "duration": 1908466,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1112223334445",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 1074391,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "The Title Does Not Exists! Would you like to add it? (y/n)",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 159171,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 1107001,
  "status": "passed"
});
formatter.scenario({
  "line": 21,
  "name": "Borrow same copy",
  "description": "",
  "id": "invalid-paths;borrow-same-copy",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 22,
  "name": "User \"u1\" created",
  "keyword": "Given "
});
formatter.step({
  "line": 23,
  "name": "User \"u2\" created",
  "keyword": "And "
});
formatter.step({
  "line": 24,
  "name": "Title \"t1\" with ISBN \"1111111111111\" exists",
  "keyword": "And "
});
formatter.step({
  "line": 25,
  "name": "Add item with isbn \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 26,
  "name": "User \"u1\" borrows copy 0 of \"1111111111111\"",
  "keyword": "When "
});
formatter.step({
  "line": 27,
  "name": "System outputs \"Success!\"",
  "keyword": "Then "
});
formatter.step({
  "line": 28,
  "name": "User \"u2\" borrows copy 0 of \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 29,
  "name": "System outputs \"Item Not Available!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 3658669,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u2",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 3919162,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "t1",
      "offset": 7
    },
    {
      "val": "1111111111111",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 2006123,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1111111111111",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 46565419,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "1111111111111",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 43150049,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Success!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 66896,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u2",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "1111111111111",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 892503,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Item Not Available!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 291783,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 187946,
  "status": "passed"
});
formatter.scenario({
  "line": 31,
  "name": "Borrow renewed copy",
  "description": "",
  "id": "invalid-paths;borrow-renewed-copy",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 32,
  "name": "User \"u1\" created",
  "keyword": "Given "
});
formatter.step({
  "line": 33,
  "name": "User \"u2\" created",
  "keyword": "And "
});
formatter.step({
  "line": 34,
  "name": "Title \"t1\" with ISBN \"1111111111111\" exists",
  "keyword": "And "
});
formatter.step({
  "line": 35,
  "name": "Add item with isbn \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 36,
  "name": "User \"u1\" borrows copy 0 of \"1111111111111\"",
  "keyword": "When "
});
formatter.step({
  "line": 37,
  "name": "User \"u1\" renews copy 0 of \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 38,
  "name": "User \"u2\" borrows copy 0 of \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 39,
  "name": "System outputs \"Item Not Available!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 17586345,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u2",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 668268,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "t1",
      "offset": 7
    },
    {
      "val": "1111111111111",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 25689664,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1111111111111",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 3320551,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "1111111111111",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 6068337,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 22
    },
    {
      "val": "1111111111111",
      "offset": 28
    }
  ],
  "location": "StepDefinitionsImpl.userRenewsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 35482590,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u2",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "1111111111111",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 3915171,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Item Not Available!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 961331,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 181541,
  "status": "passed"
});
formatter.scenario({
  "line": 41,
  "name": "Borrow returned copy taken by other user",
  "description": "",
  "id": "invalid-paths;borrow-returned-copy-taken-by-other-user",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 42,
  "name": "User \"u1\" created",
  "keyword": "Given "
});
formatter.step({
  "line": 43,
  "name": "User \"u2\" created",
  "keyword": "And "
});
formatter.step({
  "line": 44,
  "name": "Title \"t1\" with ISBN \"1111111111111\" exists",
  "keyword": "And "
});
formatter.step({
  "line": 45,
  "name": "Add item with isbn \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 46,
  "name": "User \"u1\" borrows copy 0 of \"1111111111111\"",
  "keyword": "When "
});
formatter.step({
  "line": 47,
  "name": "User \"u1\" returns copy 0 of \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 48,
  "name": "User \"u2\" borrows copy 0 of \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 49,
  "name": "User \"u1\" borrows copy 0 of \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 50,
  "name": "System outputs \"Item Not Available!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 669176,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u2",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 6007172,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "t1",
      "offset": 7
    },
    {
      "val": "1111111111111",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 3278293,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1111111111111",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 5524939,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "1111111111111",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 1049510,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "1111111111111",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userReturnsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 10604960,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u2",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "1111111111111",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 4868546,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "1111111111111",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 8912600,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Item Not Available!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 112360,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 153602,
  "status": "passed"
});
formatter.scenario({
  "line": 52,
  "name": "Borrow over max limit",
  "description": "",
  "id": "invalid-paths;borrow-over-max-limit",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 53,
  "name": "User \"u1\" created",
  "keyword": "Given "
});
formatter.step({
  "line": 54,
  "name": "Title \"t1\" with ISBN \"1111111111111\" exists",
  "keyword": "And "
});
formatter.step({
  "line": 55,
  "name": "Title \"t2\" with ISBN \"2222222222222\" exists",
  "keyword": "And "
});
formatter.step({
  "line": 56,
  "name": "Title \"t3\" with ISBN \"3333333333333\" exists",
  "keyword": "And "
});
formatter.step({
  "line": 57,
  "name": "Add item with isbn \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 58,
  "name": "Add item with isbn \"2222222222222\"",
  "keyword": "And "
});
formatter.step({
  "line": 59,
  "name": "Add item with isbn \"3333333333333\"",
  "keyword": "And "
});
formatter.step({
  "line": 60,
  "name": "User \"u1\" borrows copy 0 of \"1111111111111\"",
  "keyword": "When "
});
formatter.step({
  "line": 61,
  "name": "User \"u1\" borrows copy 0 of \"2222222222222\"",
  "keyword": "And "
});
formatter.step({
  "line": 62,
  "name": "User \"u1\" borrows copy 0 of \"3333333333333\"",
  "keyword": "And "
});
formatter.step({
  "line": 63,
  "name": "System outputs \"Maximum Borrowed Items Exceeded!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 507424,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "t1",
      "offset": 7
    },
    {
      "val": "1111111111111",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 2150409,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "t2",
      "offset": 7
    },
    {
      "val": "2222222222222",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 1687260,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "t3",
      "offset": 7
    },
    {
      "val": "3333333333333",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 2130782,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1111111111111",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 1981108,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "2222222222222",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 630286,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3333333333333",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 6070597,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "1111111111111",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 3670449,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "2222222222222",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 5037567,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "3333333333333",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 3080212,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Maximum Borrowed Items Exceeded!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 264662,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 56109,
  "status": "passed"
});
formatter.scenario({
  "line": 65,
  "name": "Borrow over max limit after return",
  "description": "",
  "id": "invalid-paths;borrow-over-max-limit-after-return",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 66,
  "name": "User \"u1\" created",
  "keyword": "Given "
});
formatter.step({
  "line": 67,
  "name": "Title \"t1\" with ISBN \"1111111111111\" exists",
  "keyword": "And "
});
formatter.step({
  "line": 68,
  "name": "Title \"t2\" with ISBN \"2222222222222\" exists",
  "keyword": "And "
});
formatter.step({
  "line": 69,
  "name": "Title \"t3\" with ISBN \"3333333333333\" exists",
  "keyword": "And "
});
formatter.step({
  "line": 70,
  "name": "Add item with isbn \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 71,
  "name": "Add item with isbn \"2222222222222\"",
  "keyword": "And "
});
formatter.step({
  "line": 72,
  "name": "Add item with isbn \"3333333333333\"",
  "keyword": "And "
});
formatter.step({
  "line": 73,
  "name": "User \"u1\" borrows copy 0 of \"1111111111111\"",
  "keyword": "When "
});
formatter.step({
  "line": 74,
  "name": "User \"u1\" borrows copy 0 of \"2222222222222\"",
  "keyword": "And "
});
formatter.step({
  "line": 75,
  "name": "User \"u1\" returns copy 0 of \"2222222222222\"",
  "keyword": "And "
});
formatter.step({
  "line": 76,
  "name": "User \"u1\" borrows copy 0 of \"3333333333333\"",
  "keyword": "And "
});
formatter.step({
  "line": 77,
  "name": "System outputs \"Success!\"",
  "keyword": "Then "
});
formatter.step({
  "line": 78,
  "name": "User \"u1\" borrows copy 0 of \"2222222222222\"",
  "keyword": "And "
});
formatter.step({
  "line": 79,
  "name": "System outputs \"Maximum Borrowed Items Exceeded!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 1127110,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "t1",
      "offset": 7
    },
    {
      "val": "1111111111111",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 670178,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "t2",
      "offset": 7
    },
    {
      "val": "2222222222222",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 482487,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "t3",
      "offset": 7
    },
    {
      "val": "3333333333333",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 457822,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1111111111111",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 555092,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "2222222222222",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 1132167,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "3333333333333",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 1797480,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "1111111111111",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 3486346,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "2222222222222",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 2404222,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "2222222222222",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userReturnsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 1522623,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "3333333333333",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 2581728,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Success!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 80989,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "2222222222222",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 1049517,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Maximum Borrowed Items Exceeded!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 235929,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 96886,
  "status": "passed"
});
formatter.scenario({
  "line": 81,
  "name": "Remove Non-existent Item",
  "description": "",
  "id": "invalid-paths;remove-non-existent-item",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 82,
  "name": "Title \"t1\" with ISBN \"1111111111111\" exists",
  "keyword": "Given "
});
formatter.step({
  "line": 83,
  "name": "Add item with isbn \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 84,
  "name": "Remove copy 1 of \"1111111111111\"",
  "keyword": "When "
});
formatter.step({
  "line": 85,
  "name": "System outputs \"Item Entity Not Found!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "t1",
      "offset": 7
    },
    {
      "val": "1111111111111",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 3525622,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1111111111111",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 2587840,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 12
    },
    {
      "val": "1111111111111",
      "offset": 18
    }
  ],
  "location": "StepDefinitionsImpl.removeCopyOf(int,String)"
});
formatter.result({
  "duration": 2351125,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Item Entity Not Found!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 317370,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 187956,
  "status": "passed"
});
formatter.scenario({
  "line": 87,
  "name": "Remove Borrowed Item",
  "description": "",
  "id": "invalid-paths;remove-borrowed-item",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 88,
  "name": "User \"u1\" created",
  "keyword": "Given "
});
formatter.step({
  "line": 89,
  "name": "Title \"t1\" with ISBN \"1111111111111\" exists",
  "keyword": "And "
});
formatter.step({
  "line": 90,
  "name": "Add item with isbn \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 91,
  "name": "User \"u1\" borrows copy 0 of \"1111111111111\"",
  "keyword": "When "
});
formatter.step({
  "line": 92,
  "name": "Remove copy 0 of \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 93,
  "name": "System outputs \"Outstanding Loan Exists!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 544732,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "t1",
      "offset": 7
    },
    {
      "val": "1111111111111",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 565755,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1111111111111",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 536062,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "1111111111111",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 915290,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "0",
      "offset": 12
    },
    {
      "val": "1111111111111",
      "offset": 18
    }
  ],
  "location": "StepDefinitionsImpl.removeCopyOf(int,String)"
});
formatter.result({
  "duration": 3941180,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Outstanding Loan Exists!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 93877,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 50419,
  "status": "passed"
});
formatter.scenario({
  "line": 95,
  "name": "Remove borrowed/renewed item",
  "description": "",
  "id": "invalid-paths;remove-borrowed/renewed-item",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 96,
  "name": "User \"u1\" created",
  "keyword": "Given "
});
formatter.step({
  "line": 97,
  "name": "Title \"t1\" with ISBN \"1111111111111\" exists",
  "keyword": "And "
});
formatter.step({
  "line": 98,
  "name": "Add item with isbn \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 99,
  "name": "User \"u1\" borrows copy 0 of \"1111111111111\"",
  "keyword": "When "
});
formatter.step({
  "line": 100,
  "name": "User \"u1\" renews copy 0 of \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 101,
  "name": "Remove copy 0 of \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 102,
  "name": "System outputs \"Outstanding Loan Exists!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 4219066,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "t1",
      "offset": 7
    },
    {
      "val": "1111111111111",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 2014734,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1111111111111",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 1055236,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "1111111111111",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 5119481,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 22
    },
    {
      "val": "1111111111111",
      "offset": 28
    }
  ],
  "location": "StepDefinitionsImpl.userRenewsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 2676129,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "0",
      "offset": 12
    },
    {
      "val": "1111111111111",
      "offset": 18
    }
  ],
  "location": "StepDefinitionsImpl.removeCopyOf(int,String)"
});
formatter.result({
  "duration": 6175822,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Outstanding Loan Exists!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 279023,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 463256,
  "status": "passed"
});
formatter.scenario({
  "line": 104,
  "name": "Remove title that still has a copy",
  "description": "",
  "id": "invalid-paths;remove-title-that-still-has-a-copy",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 105,
  "name": "Title \"t1\" with ISBN \"1111111111111\" exists",
  "keyword": "Given "
});
formatter.step({
  "line": 106,
  "name": "Add item with isbn \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 107,
  "name": "Remove title \"1111111111111\"",
  "keyword": "When "
});
formatter.step({
  "line": 108,
  "name": "System outputs \"Item Instance Exists. Cannot Delete Title!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "t1",
      "offset": 7
    },
    {
      "val": "1111111111111",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 1976349,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1111111111111",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 1349250,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1111111111111",
      "offset": 14
    }
  ],
  "location": "StepDefinitionsImpl.removeTitle(String)"
});
formatter.result({
  "duration": 5277407,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Item Instance Exists. Cannot Delete Title!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 277077,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 51211,
  "status": "passed"
});
formatter.scenario({
  "line": 110,
  "name": "Remove title that has borrowed copy",
  "description": "",
  "id": "invalid-paths;remove-title-that-has-borrowed-copy",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 111,
  "name": "User \"u1\" created",
  "keyword": "Given "
});
formatter.step({
  "line": 112,
  "name": "Title \"t1\" with ISBN \"1111111111111\" exists",
  "keyword": "And "
});
formatter.step({
  "line": 113,
  "name": "Add item with isbn \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 114,
  "name": "User \"u1\" borrows copy 0 of \"1111111111111\"",
  "keyword": "When "
});
formatter.step({
  "line": 115,
  "name": "Remove title \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 116,
  "name": "System outputs \"Outstanding Loan Exists!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 2436411,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "t1",
      "offset": 7
    },
    {
      "val": "1111111111111",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 1532569,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1111111111111",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 8692267,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "1111111111111",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 10637789,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1111111111111",
      "offset": 14
    }
  ],
  "location": "StepDefinitionsImpl.removeTitle(String)"
});
formatter.result({
  "duration": 189682,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Outstanding Loan Exists!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 229856,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 121949,
  "status": "passed"
});
formatter.scenario({
  "line": 118,
  "name": "Remove title that has a copy",
  "description": "",
  "id": "invalid-paths;remove-title-that-has-a-copy",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 119,
  "name": "User \"u1\" created",
  "keyword": "Given "
});
formatter.step({
  "line": 120,
  "name": "Title \"t1\" with ISBN \"1111111111111\" exists",
  "keyword": "And "
});
formatter.step({
  "line": 121,
  "name": "Add item with isbn \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 122,
  "name": "Add item with isbn \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 123,
  "name": "Remove copy 1 of \"1111111111111\"",
  "keyword": "When "
});
formatter.step({
  "line": 124,
  "name": "Remove title \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 125,
  "name": "System outputs \"Item Instance Exists. Cannot Delete Title!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 2672424,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "t1",
      "offset": 7
    },
    {
      "val": "1111111111111",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 876568,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1111111111111",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 1557239,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1111111111111",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 467112,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1",
      "offset": 12
    },
    {
      "val": "1111111111111",
      "offset": 18
    }
  ],
  "location": "StepDefinitionsImpl.removeCopyOf(int,String)"
});
formatter.result({
  "duration": 813885,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1111111111111",
      "offset": 14
    }
  ],
  "location": "StepDefinitionsImpl.removeTitle(String)"
});
formatter.result({
  "duration": 179452,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Item Instance Exists. Cannot Delete Title!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 492415,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 43039,
  "status": "passed"
});
formatter.scenario({
  "line": 127,
  "name": "Remove Non-existent User",
  "description": "",
  "id": "invalid-paths;remove-non-existent-user",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 128,
  "name": "Remove user \"u1\"",
  "keyword": "When "
});
formatter.step({
  "line": 129,
  "name": "System outputs \"The User Does Not Exist!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 13
    }
  ],
  "location": "StepDefinitionsImpl.removeUser(String)"
});
formatter.result({
  "duration": 671216,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "The User Does Not Exist!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 84156,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 990761,
  "status": "passed"
});
formatter.scenario({
  "line": 131,
  "name": "Renew Returned Item",
  "description": "",
  "id": "invalid-paths;renew-returned-item",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 132,
  "name": "User \"u1\" created",
  "keyword": "Given "
});
formatter.step({
  "line": 133,
  "name": "Title \"t1\" with ISBN \"1111111111111\" exists",
  "keyword": "And "
});
formatter.step({
  "line": 134,
  "name": "Add item with isbn \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 135,
  "name": "User \"u1\" borrows copy 0 of \"1111111111111\"",
  "keyword": "When "
});
formatter.step({
  "line": 136,
  "name": "User \"u1\" returns copy 0 of \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 137,
  "name": "User \"u1\" renews copy 0 of \"1111111111111\"",
  "keyword": "And "
});
formatter.step({
  "line": 138,
  "name": "System outputs \"No Such Loan Exists!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 600641,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "t1",
      "offset": 7
    },
    {
      "val": "1111111111111",
      "offset": 22
    }
  ],
  "location": "StepDefinitionsImpl.titleAlreadyExists(String,String)"
});
formatter.result({
  "duration": 641285,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "1111111111111",
      "offset": 20
    }
  ],
  "location": "StepDefinitionsImpl.attemptingToAddItemWithIsbn(String)"
});
formatter.result({
  "duration": 553116,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "1111111111111",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userBorrowsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 979789,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 23
    },
    {
      "val": "1111111111111",
      "offset": 29
    }
  ],
  "location": "StepDefinitionsImpl.userReturnsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 658937,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    },
    {
      "val": "0",
      "offset": 22
    },
    {
      "val": "1111111111111",
      "offset": 28
    }
  ],
  "location": "StepDefinitionsImpl.userRenewsCopyOf(String,int,String)"
});
formatter.result({
  "duration": 804367,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "No Such Loan Exists!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 79478,
  "status": "passed"
});
formatter.uri("validPath.feature");
formatter.feature({
  "line": 1,
  "name": "Valid Paths",
  "description": "",
  "id": "valid-paths",
  "keyword": "Feature"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 57452,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "Basic add user",
  "description": "",
  "id": "valid-paths;basic-add-user",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 7,
  "name": "User \"u1\" created",
  "keyword": "When "
});
formatter.step({
  "line": 8,
  "name": "System outputs \"Success!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 935051,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Success!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 75959,
  "status": "passed"
});
formatter.background({
  "line": 3,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 4,
  "name": "Each test is independent of each other",
  "keyword": "Given "
});
formatter.match({
  "location": "StepDefinitionsImpl.eachTestIsIndependentOfEachOther()"
});
formatter.result({
  "duration": 34934,
  "status": "passed"
});
formatter.scenario({
  "line": 10,
  "name": "Add/Remove User",
  "description": "",
  "id": "valid-paths;add/remove-user",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 11,
  "name": "User \"u1\" created",
  "keyword": "Given "
});
formatter.step({
  "line": 12,
  "name": "Remove user \"u1\"",
  "keyword": "When "
});
formatter.step({
  "line": 13,
  "name": "User \"u1\" created",
  "keyword": "And "
});
formatter.step({
  "line": 14,
  "name": "System outputs \"Success!\"",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 1335282,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 13
    }
  ],
  "location": "StepDefinitionsImpl.removeUser(String)"
});
formatter.result({
  "duration": 2070899,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "u1",
      "offset": 6
    }
  ],
  "location": "StepDefinitionsImpl.userCreated(String)"
});
formatter.result({
  "duration": 587690,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "Success!",
      "offset": 16
    }
  ],
  "location": "StepDefinitionsImpl.receiveMessage(String)"
});
formatter.result({
  "duration": 57804,
  "status": "passed"
});
});