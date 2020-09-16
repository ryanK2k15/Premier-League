package model;

import java.util.ArrayList;

public class League {

	private ArrayList<Team> teams;
	
	public League(){
		this.teams = new ArrayList<Team>();
	}
	
	public void addTeam(Team t){
		this.teams.add(t);
	}
	
	public void removeTeam(Team t){
		this.teams.remove(t);
	}
	
	public void listTeams(){
		String list = "";
		for(int i = 0; i < teams.size(); i++){
			list += teams.get(i).getName() + "\n";
		}
		System.out.println(list);
	}
	
	public ArrayList<Team> getTeams(){
		return this.teams;
	}
	
	public Team getTeam(String s){
		Team t = null;
		for(int i = 0; i < this.getTeams().size(); i++){
			if(this.getTeams().get(i) != null){
				String s2 = this.getTeams().get(i).getName();
				if(s.equals(s2)){
					t = this.getTeams().get(i);
				}
				else{
					System.out.println("No Team Found");
				}		
			}	
			else{
				System.out.println("No Teams in League");
			}
		}
		return t;
		
	}
	
}

