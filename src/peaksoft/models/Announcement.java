package peaksoft.models;

import peaksoft.enums.HouseType;

import java.math.BigDecimal;
import java.util.List;

public class Announcement {
    private Long id;
    private String title;
    private String description;
    private BigDecimal price;
    private List<String> images;
    private User owner;
    private HouseType houseType;        // House Apartment

    public Announcement() {
    }

    public Announcement(String title, String description, BigDecimal price, List<String> images,HouseType houseType) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.images = images;
        this.houseType = houseType;
    }

    public Announcement(String title, String description, BigDecimal price, List<String> images, User owner, HouseType houseType) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.images = images;
        this.owner = owner;
        this.houseType = houseType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public HouseType getHouseType() {
        return houseType;
    }

    public void setHouseType(HouseType houseType) {
        this.houseType = houseType;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", images=" + images +
                ", owner=" + owner +
                ", houseType=" + houseType +
                '}';
    }
}
