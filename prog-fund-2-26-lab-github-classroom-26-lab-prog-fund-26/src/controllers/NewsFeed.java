package controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class NewsFeed {

    private ArrayList<Pet> posts;

    public NewsFeed() {
        posts = new ArrayList<Pet>();
    }

    public boolean addPost(Pet pet) {
        return posts.add(pet);
    }

    public String show() {
        String str = "";

        for(Pet pet : posts) {
            str += posts.indexOf(pet) + ": " + pet.displayCondensed() + "\n";
        }

        if (str.isEmpty()){
            return "No Posts";
        }
        else {
            return str;
        }
    }

    public String showPhotoPosts() {
        String str = "";

        for(Pet pet : posts) {
            if (pet instanceof PhotoPet) {
                str += posts.indexOf(pet) + ": " + pet.display() + "\n";
            }
        }

        if (str.isEmpty()){
            return "No Photo Posts";
        }
        else {
            return str;
        }
    }

    public String showMessagePosts() {
        String str = "";

        for(Pet pet : posts) {
            if (pet instanceof MessagePet) {
                str += posts.indexOf(pet) + ": " + pet.display() + "\n";
            }
        }

        if (str.isEmpty()){
            return "No Message Posts";
        }
        else {
            return str;
        }
    }

    public String showEventPosts() {
        String str = "";

        for(Pet pet : posts) {
            if (pet instanceof EventPet) {
                str += posts.indexOf(pet) + ": " + pet.display() + "\n";
            }
        }

        if (str.isEmpty()){
            return "No Event Posts";
        }
        else {
            return str;
        }
    }

    public Pet deletePost(int indexToDelete) {
        if (isValidIndex(indexToDelete)) {
            return posts.remove(indexToDelete);
        }
        return null;
    }

    public boolean updateMessagePost(int indexToUpdate, String author, String message) {
        //find the object by the index number
        Pet foundMessage = findPost(indexToUpdate);

        //if the object exists, use the details passed in the parameters to
        //update the found object in the ArrayList.
        if ((foundMessage != null) && (foundMessage instanceof MessagePet)) {
            foundMessage.setAuthor(author);
            ((MessagePet) foundMessage).setMessage(message);
            return true;
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false;
    }

    public boolean updatePhotoPost(int indexToUpdate, String author, String caption, String filename) {
        //find the object by the index number
        Pet foundPet = findPost(indexToUpdate);

        //if the object exists, use the details passed in the parameters to
        //update the found object in the ArrayList.
        if ((foundPet != null) && (foundPet instanceof PhotoPet)){
            foundPet.setAuthor(author);
            ((PhotoPet) foundPet).setCaption(caption);
            ((PhotoPet) foundPet).setFilename(filename);
            return true;
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false;
    }

    public boolean updateEventPost(int indexToUpdate, String author, String eventName, double eventCost) {
        //find the object by the index number
        Pet foundPet = findPost(indexToUpdate);

        //if the object exists, use the details passed in the parameters to
        //update the found object in the ArrayList.
        if ((foundPet != null) && (foundPet instanceof EventPet)){
            foundPet.setAuthor(author);
            ((EventPet) foundPet).setEventName(eventName);
            ((EventPet) foundPet).setEventCost(eventCost);
            return true;
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false;
    }

    public Pet findPost(int index) {
        if (isValidIndex(index)) {
            return posts.get(index);
        }
        return null;
    }

    public int numberOfPosts() {
        return posts.size();
    }

    public int numberOfMessagePosts() {
        int number = 0;
        for (Pet pet : posts){
            if (pet instanceof MessagePet){
                number++;
            }
        }
        return number;
    }

    public int numberOfPhotoPosts() {
        int number = 0;
        for (Pet pet : posts){
            if (pet instanceof PhotoPet){
                number++;
            }
        }
        return number;
    }

    public int numberOfEventPosts() {
        int number = 0;
        for (Pet pet : posts){
            if (pet instanceof EventPet){
                number++;
            }
        }
        return number;
    }

    public void likeAPost(int index) {
        Pet pet = null;
        if (isValidIndex(index)) {
            pet = posts.get(index);
            if ((pet instanceof LikedPet)){
                ((LikedPet) pet).likeAPost();
            }
        }
    }

    public void unLikeAPost(int index) {
        Pet pet = null;
        if (isValidIndex(index)) {
            pet = posts.get(index);
            if ((pet instanceof LikedPet)){
                ((LikedPet) pet).unlikeAPost();
            }
        }
    }
    /**
     * The load method uses the XStream component to read all the models.MessagePost objects from the posts.xml
     * file stored on the hard disk.  The read objects are loaded into the posts ArrayList
     *
     * @throws Exception  An exception is thrown if an error occurred during the load e.g. a missing file.
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        //list of classes that you wish to include in the serialisation, separated by a comma
        Class<?>[] classes = new Class[] { EventPet.class, MessagePet.class, PhotoPet.class, Pet.class};

        //setting up the xstream object with default security and the above classes
        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        //doing the actual serialisation to an XML file
        ObjectInputStream in = xstream.createObjectInputStream(new FileReader("posts.xml"));
        posts = (ArrayList<Pet>) in.readObject();
        in.close();
    }

    /**
     * The save method uses the XStream component to write all the objects in the posts ArrayList
     * to the posts.xml file stored on the hard disk.
     *
     * @throws Exception  An exception is thrown if an error occurred during the save e.g. drive is full.
     */
    public void save() throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("posts.xml"));
        out.writeObject(posts);
        out.close();

    }

    public boolean isValidIndex(int index) {
        return (index >= 0) && (index < posts.size());
    }

    public boolean isValidMessagePostIndex(int index) {
        if (isValidIndex(index)) {
            return (posts.get(index)) instanceof MessagePet;
        }
        return false;
    }

    public boolean isValidPhotoPostIndex(int index) {
        if (isValidIndex(index)) {
            return (posts.get(index)) instanceof PhotoPet;
        }
        return false;
    }

    public boolean isValidEventPostIndex(int index) {
        if (isValidIndex(index)) {
            return (posts.get(index)) instanceof EventPet;
        }
        return false;
    }

}
