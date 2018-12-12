package MailSystem.model.pv;

public class Folder {
    private Integer id;

    private String name;

    private Integer owner_id;

    public Folder() {
    }

    public Folder(Integer id, String name, Integer owner_id) {
        this.id = id;
        this.name = name;
        this.owner_id = owner_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Integer owner_id) {
        this.owner_id = owner_id;
    }
}