package com.epam.ryndych.xml.model;

import javax.xml.bind.annotation.XmlElement;

import com.epam.ryndych.xml.exception.IllegalXMLArgumentException;

public class PlaneChars {

	private enum Type {
		AIRCRAFT_SUPPORT {

			@Override
			String getName() {
				return "aircraft support";
			}

		},
		ACCOMPANIMENT {

			@Override
			String getName() {
				return "accompaniment";
			}

		},
		DESTROYER {

			@Override
			String getName() {
				return "destroyer";
			}

		},
		INTERLOPER {

			@Override
			String getName() {
				return "interloper";
			}

		},
		SCOUT {

			@Override
			String getName() {
				return "scout";
			}

		};
		abstract String getName();
	}
	
	@XmlElement(name = "type", required = true)
	private Type type;
	@XmlElement(name = "seats", required = true)
	private int seats;
	@XmlElement(name = "combat_kit", required = true)
	private int combatKit;
	@XmlElement(name = "availability_of_radar", required = true)
	private boolean availabilityOfRadar;

	public PlaneChars() {
		
	}

	public PlaneChars(String type, int seats, int combatKit,
			boolean availabilityOfRadar) {

		setType(type);
		setSeats(seats);
		setCombatKit(combatKit);
		setAvailabilityOfRadar(availabilityOfRadar);

	}

	public String getType() {
		return type.getName();
	}

	public void setType(String type) {
		boolean isGood = false;
		for (Type tmp : Type.values()) {
			if (type.equals(tmp.getName())) {
				this.type = tmp;
				isGood = true;
				break;
			}
		}

		if (!isGood)
			try {
				throw new IllegalXMLArgumentException();
			} catch (IllegalXMLArgumentException e) {
				System.out.println(e.getMessage()
						+ ": Wrong type of plane in plane's chars");
			}

	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		if (seats >= 1 && seats <= 2)
			this.seats = seats;
		else
			try {
				throw new IllegalXMLArgumentException();
			} catch (IllegalXMLArgumentException e) {
				System.out.println(e.getMessage()
						+ ": Wrong number of seats in plane's chars");
			}
	}

	public int getCombatKit() {
		return combatKit;
	}

	public void setCombatKit(int combatKit) {
		if (combatKit >= 0 && combatKit <= 10)
			this.combatKit = combatKit;
		else
			try {
				throw new IllegalXMLArgumentException();
			} catch (IllegalXMLArgumentException e) {
				System.out.println(e.getMessage()
						+ ": Wrong number of combatKit in plane`s chars");
			}
	}

	public boolean isAvailabilityOfRadar() {
		return availabilityOfRadar;
	}

	public void setAvailabilityOfRadar(boolean availabilityOfRadar) {
		this.availabilityOfRadar = availabilityOfRadar;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName());
		sb.append(" [ type = "+type.getName());
		sb.append("; seats = "+seats);
		sb.append("; combatKit = "+combatKit);
		sb.append("; availabilityOfRadar = "+availabilityOfRadar+" ]");
		return sb.toString();
	}

}