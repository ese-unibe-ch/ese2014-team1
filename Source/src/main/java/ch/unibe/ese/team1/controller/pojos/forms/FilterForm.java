package ch.unibe.ese.team1.controller.pojos.forms;
	
public class FilterForm {

		private boolean animals;
		private boolean smoker;
		
		public boolean isAnimals() {
			return animals;
		}
		
		public void setAnimals(boolean animals) {
			this.animals = animals;
		}
		
		public boolean isSmoker() {
			return smoker;
		}
		
		public void setSmoker(boolean smoker) {
			this.smoker = smoker;
		}
}