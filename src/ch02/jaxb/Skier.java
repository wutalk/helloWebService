/*
 * @(#)	2014-8-11
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package ch02.jaxb;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author wutalk
 */
@XmlRootElement(name="HeroSkier")
public class Skier {
	// fields
	private Person person;
	private String national_team;
	private Collection<String> major_achievements;
	// constructors
	public Skier() { }
	public Skier (Person person,
	String national_team,
	Collection<String> major_achievements) {
	setPerson(person);
	setNationalTeam(national_team);
	setMajorAchievements(major_achievements);
	}
	// properties
	public Person getPerson() { return person; }
	public void setPerson (Person person) { this.person = person; }
	public String getNationalTeam() { return national_team; }
	public void setNationalTeam(String national_team) {
	this.national_team = national_team;
	}
	public Collection<String> getMajorAchievements() { return major_achievements; }
	public void setMajorAchievements(Collection<String> major_achievements) {
	this.major_achievements = major_achievements;
	}
}
