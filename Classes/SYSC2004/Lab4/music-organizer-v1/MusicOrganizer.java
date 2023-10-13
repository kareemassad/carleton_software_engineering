import java.util.ArrayList;

/**
 * A class to hold details of audio files.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2016.02.29
 */
public class MusicOrganizer {
    // An ArrayList for storing the file names of music files.
    private ArrayList<String> files;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer() {
        files = new ArrayList<>();
    }

    /**
     * Add a file to the collection.
     * 
     * @param filename The file to be added.
     */
    public void addFile(String filename) {
        files.add(filename);
    }

    /**
     * Return the number of files in the collection.
     * 
     * @return The number of files in the collection.
     */
    public int getNumberOfFiles() {
        return files.size();
    }

    /**
     * List a file from the collection.
     * 
     * @param index The index of the file to be listed.
     */
    public void listFile(int index) {
        if (validIndex(index)) {
            String filename = files.get(index);
            System.out.println(filename);
        }
    }

    /**
     * Remove a file from the collection.
     * 
     * @param index The index of the file to be removed.
     */
    public void removeFile(int index) {
        if (validIndex(index)) {
            files.remove(index);
        }
    }

    public void checkIndex(int index) {
        if ((index < 0) || (index > files.size()-1)) {
            System.out.println("Invalid range, index must fall between 0 and " + (files.size()-1));
        } else {
            System.out.println("Works great");
        }
    }
    public boolean validIndex(int index) {

        boolean valid = false;

        if (index < 0) {
            System.out.println("Index cannot be negative: "+ index);
            valid = false;
        } else if(index >= files.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        } else {
            valid = true;
        }
        return valid;
    }
    public void listAllFiles(){
        for (int i = 0; i < files.size(); i++) {
            String filename = files.get(i);
            System.out.println(filename);
        }
    }
}
