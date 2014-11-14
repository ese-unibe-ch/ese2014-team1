# Review for Team 4 (share-a-flat)

## Design

### Violation of MVC pattern

### Usage of helper objects between view and model

### Rich OO domain model

### Clear responsibilities

### Sound invariants

### Overall code organization & reuse, e.g. views

## Coding style

### Consistency

### Intention-revealing names

### Do not repeat yourself

### Exception, testing null values

### Encapsulation

### Assertion, contracts, invariant checks

### Utility methods

## Documentation

### Understandable

### Intention-revealing

### Describe responsibilities

### Match a consistent domain vocabulary

## Test

### Clear and distinct test cases

The test cases are clearly separated. Care was exercised that only one aspect of an object was tested per test case. It is not possible to further divide a test case into several new ones, which is further evidence that they are distinct. 

### Number/coverage of test cases

The project contains two test classes. In total 18 test cases can be found.

### Easy to understand the case that is tested

All test cases are reasonably named and therefore also easily understandable. Test cases within one class all have a similar structure, which makes it easy to distinguish what exactly is tested in each test case.

### Well crafted set of test data

### Readability

As already mentioned before, the test cases are named reasonably, therefore the readability is good. Also variables are named according to what they are used for, although sometimes naming conventions are not followed (e.g. `String EMAIL ` instead of `String email`). In addition, mocked objects are clearly separated from real objects through appriopriate naming. The two test classes do not have consistent names. While one class is named `LoginTest` (singular) the other one is named `SearcherTests` (plural). It would be better if both test classes used the singular. This might seem overly picky at first, but it is mainly due to the fact that Maven Surefire only runs the test cases of classes using the singular version per default. The way the test files were configured at the time of the release, Maven Surefire only ran the tests from `LoginTest`.