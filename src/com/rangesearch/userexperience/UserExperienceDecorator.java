package com.rangesearch.userexperience;


public class UserExperienceDecorator implements Comparable<UserExperience> {
	
	private String experience;
	
	private Long responseRange;
	
	public UserExperienceDecorator(Long responseTime){
		//this.responseTime = responseTime;
		//this.experience = UserExperience(responseTime);
	}

	@Override
	public int compareTo(UserExperience arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
