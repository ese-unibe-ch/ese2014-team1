package ch.unibe.ese.team1.test.testData;

/** Specifies that a class can save test data to the database. */
public interface TestDataSaver {

	/** Saves test data to the database. */
	public void saveTestData() throws Exception;
}
