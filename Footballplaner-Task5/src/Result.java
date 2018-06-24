public  class  Result {
	
		int score1;

	
		int score2;

	
		
		/**
		 * Constructor
		 */
		public Result() {
			score1 =-1;
			score2=-1;
		}

	
		
		/**
		 * Constructor
		 * @param score1
		 * @param score2
		 */
		public Result(int score1,int score2) {
			setResult(score1, score2);
		}

	
		
		/**
		 * Constructor
		 * @param input
		 */
		public Result(String input) {
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


}
