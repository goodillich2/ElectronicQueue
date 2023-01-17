package com.example.springmarket.model;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "queue")
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "img_url")
    private String imgUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    @OneToMany(mappedBy = "queue", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    List<Person> personList;


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Queue(String name, String imgUrl, String description) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
    }

    public Queue() {

    }

    public void addPerson(Person person){
        if(personList == null)
            personList = new ArrayList<>();
        person.setQueue(this);
        personList.add(person);
    }


    public TreeMap createNumberList(){
        TreeMap<Integer, Person> map = new TreeMap<>();
        int size = personList.size();
        for (int i = 0; i <size ; i++) {
            map.put(i+1, personList.get(i));
        }
        return  map;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }


}
