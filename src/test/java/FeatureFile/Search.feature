@Search
Feature: Search

  @addObject
  Scenario Outline: Validate the successful addition of the object of the <api_name> and then validate the response
    Given Hit the api <api_name> with a <name> to add an object with the required <price> and size <size> and <data>
    Then validate the api <api_name_get> contains the already added data <name>
    Then delete the object with the id
    Then Validate the <api_name_get> should not have the ID present
    Examples:
      |api_name|name|price|size|api_name_get|data|
      |ADD_OBJECT_API|Dell Inspiron|56789|1TB|get_Objects|{"price": 389.99,"color": "Purple"}|


