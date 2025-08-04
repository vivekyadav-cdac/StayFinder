package com.cdacproject.stayfinder.pg_property_service.dto;

import java.util.List;

public class PGResponseDto {
    private Long id;
    private String name;
    private String type;
    private String address;
    private String city;
    private String state;
    private String pin;
    private String contact;
    private Long ownerId;
    private String imageUrl; // ✅ include image

    private List<RoomResponseDto> rooms; // ✅ nested rooms

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public List<RoomResponseDto> getRooms() {
		return rooms;
	}

	public void setRooms(List<RoomResponseDto> rooms) {
		this.rooms = rooms;
	}

    
}

