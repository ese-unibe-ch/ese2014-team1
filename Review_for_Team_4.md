# Review for Team 4 (share-a-flat)

## Design

### Violation of MVC pattern

I see no violation of the MVC pattern. We had a good introduction thanks to the Skeletons and the warm-ups, Team 4 followed those examples consequently and therefore stuck to the pattern. They applied the Spring Web MVC framework well, and made good use of the default handler via the @Controller and the @RequestMapping annotations.
However, I think the files AdType.java and Sex.java could be moved from the controller.pojos package to the model package. According to page 6 from the SpringMVC presentation from the ESE-Wiki, those two enums probably don't belong in the same package as the forms which have other responsibilities as data classes between jsp files and controllers.
Concerning the Model, I like the clear folder structure within the webapp. 


### Usage of helper objects between view and model


### Rich OO domain model
There could be more classes in the model-package, such as a class Picture instead of the byte[] array in Profile.java. With additional functionality, more classes such as Message will surely be created. The four existing classes themselves are good in my opinion.
There is some use of aggregation, an Ad has a an owner, an address, a type etc. 


### Clear responsibilities
In HomeController.java, there is the method login(). This method should probably be moved to LoginController.java as the name of the method suggests. For me, Profile.java and User.java were a bit confusing at first. The fields 'sex' and 'age' are most likely in Profile.java since they are edited in the profile, I as an outsider expected them in the class User at first due to common sense, but this is probably a minor matter of choice.
In TabBarController.java, there are the methods searchList() and searchMap(), I as an outsider expected them to be in the SearchController.java. In the page, the tab bar contains an entry 'Search', while the 'Search List' and 'Search Map' are not in the tab bar but in the Search page itself.
The javadoc comment "Controls all pages / commands concerning ads" in ImageController.java is confusing / wrong since there is a Class AdController.java. The responsibilities of the forms in the pojos package are clear.
The responsibility of ReviewService.java is clear, this class saves some users and ads into the database for testing purposes. However, I think a renaming (and splitting) into something like UserTestDataSeeder and AdTestDataSeeder would be good, I find the name ReviewService a bit confusing. In addition, I suggest that this class is moved into an own package (something like .testData) since it has another responsibility than the rest of the classes in the package (such as AdServiceImpl etc.).
The distinction / responsibilities of LoginService.java and NewAccountServiceImpl.java is confusing, the method loginManually() is NewAccountServiceImpl.java. Those two classes could probably be merged into something like AccountService.java.


### Sound invariants
I saw no class invariants and no assers (except in the tests of course) in the code.


### Overall code organization & reuse, e.g. views
In the controller.service package, the usage of interfaces is handled inconsistently. Some classes like AdService implement a corresponding interface, some classes like ReviewService have none.
I think the code organization is good for the majority of the project. However, there is a bad mix of files in the folder src/main/resources. There is a png, js and some xml files, those should probably be put in separate folders. 7 files at this point isn't a big deal, but for a way bigger project, a good separation is a must.
I didn't see too much code reuse, but I also don't believe that the team should have created a huge class hierarchy here. For example, there are currently four classes in the model package, one could factor out the id and its getter/setter into a superclass, but apart from that, these classes don't have much more in common.



## Coding style

Reading the code was very smooth and mostly self-explanatory. Finding all the relevant information was pretty easy (with a few exceptions) due to intuitive naming grouping of the classes, fields and methods. All in all the code looks clean although there are still many comments hinting unfinished code or further problems that have not yet been solved.

You have many imports in (almost all) your files which were copy pasted and never removed. It does confuse the reader and impacts performance. In the ImageController you have much more unused imports than used ones.

### Consistency

The .jsp file naming seems to be a little bit inconsistent. Sometimes you use a dash to separate words (create-ad.jsp) sometimes you use camel case (modifyProfile.jsp). That can make it harder to match and read pages like in the controller classes or even in the header.jsp lines 56 to 60. It would be easier to define a style within the group and follow it.

Sometimes you use in-line css styling sometimes you use it from an external css file. What makes it even harder to read is, that you use compressed/minified css and js files. During the development phase readability should be preferred. For production phase a minified version could be faster and therefore better.

### Intention-revealing names

The TabBarController seems to be responsible for several things that are visually together but not logically. When I was searching for the place where SearchForm was added I looked in the SearchController and had to search for quite a while until I found it. Maybe that could be grouped better?

Sex.java is nice but leaves me thinking something else. Maybe Gender.java would be a better alternative.

Do you really intend to use a form for UploadForm? Most likely it is just a functionality inside AdForm.java therefore it should probably not be a class.

Why do you use a DefaultSearcher with an ISearcher (normaly you name the interface and the implementation of the interface differently: e.g. AdService.java and AdServiceImpl.java). DefaultSearcher implies there should be at least one other Searcher.

ReviewService.java is most likely not a service as specified by the @Service annotation to be called when needed but rather test data for development only. It could be placed to src/test/java.

### Do not repeat yourself

You have two different headers. headerLogin.jsp opens many div tags which you try to close in register.jsp in lines 54 to 56 (without indentation nor comments) and then again in lines 123 to 125.

You have an empty footer which closes certain tags and does nothing else. On some pages (register.jsp, index.jsp ... ) you specify a footer with the class name mastfoot additionally, on most pages you don't. (why not separate mastfoot to mast-foot like all other classes within html?).

### Exception, testing null values

You are testing for null values in your service classes like in return statements (UserServiceImpl.java) which is perfect. Although you do catch Exception in ProfileServiceImpl.java which is never a good idea, since catching an Exception means catching every possible exception.

You have defined your own three exceptions to make debugging more easy and to further specify what can go wrong and where it goes wrong. That is great. Especially in NewAccountServiceImpl.java you have different messages passing to InvalidUserException.java giving just the right feedback! I am just not that sure if this kind of feedback should be handled as an exception or rather as input validation with javascript or something similar.

??? read through it again...
LoginServiceImpl.java it's again the throwing of an exception which I am not that sure about if an exception is the right thing to do in such a case. In my opinion it is not really an exception but just an if else clause.
???

### Encapsulation

The encapsulation seems to be good. All model classes have fields with their respective setters and getters according to JavaBean Definition. Setters and getters can be therefore used by Spring.

??? read through it again....
What I don't really understand is the "default" constructor in User.java where you set the Owner to itself basically. Why is an owner even necessary? Would it not be easier to group these two classes into one? I see that you want to have a User and then link it to a Profile which is nice, but at the moment I don't see any direct benefit from it.
???

In the User.java class you have two methods a public getAuthorities() and it's private translateRole(). You are accesing getAuthorities() from outside it's own model and I think that should be avoided. In the model there should only be setters and getters for the model and if needed private helper methods only (that's for the business logic/class logic of the class only). Further you have four methods at the end which return TRUE only. They are without a field. I guess the code within the body will follow in the next step?

### Assertion, contracts, invariant checks

There are no assertions nor contracts nor invariant checks.

### Utility methods

needs further clarification from andrea on what that is ;)
I am not sure if the use of own static methods is meant or the utility class of java...

## Documentation

### Understandable
Most of the SRS is understandable. There are, however, a number of passages that clearly stand in the way of understanding.

1. The use case diagram (p. 3) leaves us wondering at the meaning of "include", since according to the diagram, logging in and out of account apparently includes creating an account. Sure that's a precondition, but it isn't included *every time*. Same thing goes for the "include" statement where the export of a PDF file apparently includes the search for an ad (more on that later).
2. At the beginning, we are missing some kind of page overview, i.e. which buttons/options exist, which of them are always visible, which of them are in sub-menus etc. In the document, it often seems like there is a clear model of where what is, but it is never made explicit. This shortcoming becomes the most tangible when bookmarking favourites is discussed. Descriptions vary between "bookmarking" (e.g. XXXXXX), clicking on "the "star" of candidates" (e.g. p. 8),  "clicking onto "star"" (e.g. p. 8), and "clicks onto the "star"" (p. 12). So is it an actual star (image) you click on? Or a button/text named "star"? Or "bookmark"?
3. In many occasions, phrasing is reduced to near-intelligibility, e.g. S. 5, §1.7.6a (+ more examples) (blah)
4. Throughout the SRS (12 use cases in total), not *once* are any special requirements named, and only two times notes are provided, with one of them ("Account can only be created if the username does not yet exist" in use case "Create Account") being not so much of a note but rather one possible validation step that should be included in the main scenario. The effect of this on understanding is thoroughly detrimental.
  


### Intention-revealing
Some use cases reveal their intention plain and clear. Others not so much. Especially the order of the use cases is really tough to understand. Is there any order at all? Certainly, there is none based on use cases belonging to the same user story. This is especially frustrating with the use cases concerning the user story of managing visits.

1. Use case 1, "create schedule to manage visit" (p. 5), only targets the advertiser as an actor. For each of her objects, the advertiser selects some times slots on a calendar, and that's that for this use case. It would be *a lot* better for understanding, if either the other use cases belonging to that user story came right after this one, or there was some explanation of the *whole* user story in the "Notes" section (which is literally *never* used in the whole SRS), or, of course, ideally both.
2. The Alternative scenario in use case 7 has no name, and thus does not reveal its intention. Also, it is not really an alternative scenario at all, just another way to achieve exactly the same thing, i.e. another use case.
3. 

### Describe responsibilities
- often it is only written what the user does, but not the system (blah)
- multiple times, two or more use cases are wrapped into one (blah)
- In use case 9, "Export information from ad", which is about exporting ad information to a PDF file downloaded to a user's machine, there are no alternative scenarios listed. That seems a bit too optimistic. What if the export fails, for one of numerous reasons? What if the user's hard disk is already full? What if the user's firewall intercepts the download, or some other scenario?
- very thorough list of requirements at the end - nice

### Match a consistent domain vocabulary

- (blah)
- Sometimes, semi-technical vocabulary is used inconsistently. In some cases, we correctly read that the "system prompts the user" (BEISPIEL FINDEN), in other cases it's just "System asks whether [user] is sure or not" (p. 5). It is no big deal to make that consistent. (blah)
- One last point concerning the consistency of the domain vocabulary: Unfortunately, in one aspect the vocabulary is extremely consistent, that is in the generic masculine used literally everywhere (!) where users are referenced. Throughout the document, it's always "he searches" etc., not one single time "he or she" or other including wording is used. What is this, the early 1800s?

## Test

### Clear and distinct test cases

The test cases are clearly separated. Care was exercised that only one aspect of an object was tested per test case. It is not possible to further divide a test case into several new ones, which is further evidence that they are distinct. Since the `SearchTests`class contains unit tests for the class `DefaultSearcher`it would make sense to rename it to `DefaultSearcherTest`. This suggestion stems from two main reasons. First of all, it would comply to common convention. JUnit and especially Maven Surefire unit tests are commonly named with the pattern `ATest`for class `A`. Per default, Surefire even relies on this naming to find the test cases within a Maven project. The second reason is that the current naming is confusing for someone searching for the class that is under test. Which class is tested can currently only be found out through looking at the source code of the tests.
The second testing class is called `LoginTest`. Although 

### Number/coverage of test cases

The project contains two test classes. In total 18 test cases can be found.

### Easy to understand the case that is tested

All test cases are reasonably named and therefore also easily understandable. Test cases within one class all have a similar structure, which makes it easy to distinguish what exactly is tested in each test case.

### Well crafted set of test data

### Readability

As already mentioned before, the test cases are named reasonably, therefore the readability is good. Also variables are named according to what they are used for, although sometimes naming conventions are not followed (e.g. `String EMAIL ` instead of `String email`). In addition, mocked objects are clearly separated from real objects through appriopriate naming. The two test classes do not have consistent names. While one class is named `LoginTest` (singular) the other one is named `SearcherTests` (plural). It would be better if both test classes used the singular. This might seem overly picky at first, but it is mainly due to the fact that Maven Surefire only runs the test cases of classes using the singular version per default. The way the test files were configured at the time of the release, Maven Surefire only ran the tests from `LoginTest`.

### Temporary (for Thomasz)
Coding Style - Utility methods
The classes in the model package got reasonable toString methods.

Coding Style - Intention-revealing names
In ReviewService.java, NewAccountService nas and AdService as aren't intention-revealing names.


Coding style - General ???
Many warnings (unused imports in many classes)
Bad Code Formatting: In ad.jsp for example (use format hotkey of Eclipse)
Some println-statements: (submitAd in AdController.java)
Fully commented method in HomeController.java (probably an old version not yet deleted for testing?)
Some files like modifyProfile.jsp need some cleanup (see warnings), there are closing tags with no start/end tags, tags in invalid locations and the formatting of the code could be improved).
