package entity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;

/**
 * The Class MyFile.
 *
 * @author Itay Ziv
 */
@SuppressWarnings("serial")
public class MyFile implements Serializable {

    /** The Description. */
    private String Description = null;

    /** The file name. */
    private String fileName = null;

    /** The size. */
    private int size = 0;

    /** The mybytearray. */
    public byte[] mybytearray;

    /**
     * Inits the array.
     *
     * @param size the size
     */
    public void initArray(int size) {
        mybytearray = new byte[size];
    }

    /**
     * Gets the file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Sets the file name.
     *
     * @param fileName the new file name
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * Sets the size.
     *
     * @param size the new size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Gets the mybytearray.
     *
     * @return the mybytearray
     */
    public byte[] getMybytearray() {
        return mybytearray;
    }

    /**
     * Gets the mybytearray.
     *
     * @param i the i
     * @return the mybytearray
     */
    public byte getMybytearray(int i) {
        return mybytearray[i];
    }

    /**
     * Sets the mybytearray.
     *
     * @param mybytearray the new mybytearray
     */
    public void setMybytearray(byte[] mybytearray) {

        for (int i = 0; i < mybytearray.length; i++)
            this.mybytearray[i] = mybytearray[i];
    }

    /**
     * Gets the description.
     *
     * @return the description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     * Parses the to my file.
     *
     * @param path the path
     * @return the my file
     */
    public static MyFile parseToMyFile(String path) {
        MyFile myFile = new MyFile();
        String LocalfilePath = path;
        try {

            File newFile = new File(LocalfilePath);
            myFile.setFileName(newFile.getName());

            byte[] mybytearray = new byte[(int) newFile.length()];
            FileInputStream fis = new FileInputStream(newFile);
            BufferedInputStream bis = new BufferedInputStream(fis);

            myFile.initArray(mybytearray.length);
            myFile.setSize(mybytearray.length);

            bis.read(myFile.getMybytearray(), 0, mybytearray.length);

            bis.close();
            fis.close();

        } catch (Exception e) {
            System.out.println("Error send (Files)msg) to Server");
        }

        return myFile;
    }
}
