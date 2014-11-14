# Review for Team 4 (share-a-flat)

## Design

### Violation of MVC pattern

Concerning the Model, I like the clear folder structure within the webapp. 
I think the files AdType.java and Sex.java could be moved from the controller.pojos package to the model package. According to page 6 from the SpringMVC presentation from the ESE-Wiki, those two enums probably don't belong in the same package as the forms which have other responsibilities as data classes between jsp files and controllers.


### Usage of helper objects between view and model

### Rich OO domain model
There could be more classes in the model-package, such as a class Picture instead of the byte[] array in Profile.java. The four existing classes itself are good in my opinion.

### Clear responsibilities
In HomeController.java, there is the method login(). This method should probably be moved to LoginController.java as the name of the method suggests. For me, Profile.java and User.java were a bit confusing at first. The fields 'sex' and 'age' are most likely in Profile.java since they are edited in the profile, I as an outsider expected them in the class User at first due to common sense, but this is probably a minor matter of choice.


### Sound invariants
I saw no class invariants in the code.

### Overall code organization & reuse, e.g. views
In the controller.service package, the usage of interfaces is handled inconsistently. Some classes like AdService implement a corresponding interface, some classes like ReviewService have none.
I think the code organization is good for the majority of the project. However, there is a bad mix of files in the folder src/main/resources. There is a png, js and some xml files, those should probably be put in separate folders. 7 files at this point isn't a big deal, but for a way bigger project, a good separation is a must.

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