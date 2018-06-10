import interfaces.IGame;

public class result implements interfaces.IGame{

	@Override
	public IGame setGame(String input) {
		// TODO Auto-generated method stub
		return new result(input);
	}
	
		int score1;
		int score2;
		
		/**
		 * Constructor
		 */
		public result() {
			score1 =-1;
			score2=-2;
		}
		
		/**
		 * Constructor
		 * @param score1
		 * @param score2
		 */
		public result(int score1,int score2) {
			setResult(score1, score2);
		}
		
		/**
		 * Constructor
		 * @param input
		 */
		public result(String input) {
			setResult(input);
		}
		
		/**
		 * Setter
		 * @param score1
		 * @param score2
		 */
		public void setResult(int score1,int score2) {
			this.score1 =score1;
			this.score2= score2;
		}
		
		/**
		 * Converts a well formed String to an result and saves it as score1 and score2
		 * @param input
		 */
		public void setResult(String input) {
			String[] splitted = input.split(":");
			score1=Integer.parseInt(splitted[0]);
			score2=Integer.parseInt(splitted[1]);
		}
		
		
		
		public int getScore1() {
			return score1;
		}

		public int getScore2() {
			return score2;
		}

		/**
		 * gives back the result as a string
		 */
		public String toString() {
			StringBuilder sb = new	StringBuilder();
			sb.append(score1).append(":");
			sb.append(score2);
			return sb.toString();
		}

		@Override
		public int getFirstValue() {
			// TODO Auto-generated method stub
			return getScore1();
		}

		@Override
		public int getSecondValue() {
			// TODO Auto-generated method stub
			return getScore2();
		}

		@Override
		public int getThirdValue() {
			// TODO Auto-generated method stub
			return -1;
		}

		@Override
		public int getFourthValue() {
			// TODO Auto-generated method stub
			return -1;
		}

		@Override
		public int getFifthValue() {
			// TODO Auto-generated method stub
			return -1;
		}

		@Override
		public void setFirstValue(int value) {
			score1=value;
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setSecondValue(int value) {
			score2=value;
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setThirdValue(int value) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setFourthValue(int value) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setFifthValue(int value) {
			// TODO Auto-generated method stub
			
		}

}
