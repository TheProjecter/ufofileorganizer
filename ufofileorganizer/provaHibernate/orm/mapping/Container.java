package orm.mapping;

// Generated 15-ago-2009 12.03.00 by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;

/**
 * Container generated by hbm2java
 */
public class Container implements java.io.Serializable {

	private Integer id;
	private Container container;
	private String name;
	private String description;
	private Set<Group> groups = new HashSet<Group>(0);
	private Set<Container> containers = new HashSet<Container>(0);

	public Container() {
	}

	public Container(Container container, String name, String description,
			Set<Group> groups, Set<Container> containers) {
		this.container = container;
		this.name = name;
		this.description = description;
		this.groups = groups;
		this.containers = containers;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Container getContainer() {
		return this.container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Group> getGroups() {
		return this.groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<Container> getContainers() {
		return this.containers;
	}

	public void setContainers(Set<Container> containers) {
		this.containers = containers;
	}

}
