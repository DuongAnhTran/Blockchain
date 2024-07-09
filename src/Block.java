import java.util.Date;
import com.google.gson.GsonBuilder;

public class Block {
    public String hash;
    public String prevHash;
    private String data; //the data will just be a simple message
    private long timeStamp; //as number of miliseconds since 1/1/1970
    private int nonce;

    //Constructor for Block
    public Block(String data, String prevHash){
        this.data = data;
        this.prevHash = prevHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash(); //Making sure we do this after we set other values
    }

    //Method 1:
    public String calculateHash() {
        String calculatehash = StringUtil.applySha256(
            prevHash + Long.toString(timeStamp) + 
            Integer.toString(nonce) + data
        );
        return calculatehash;
    }

    //Method 2: Take an int called difficulty (the number of 0's they must solve for)
    public void mineBlock(int diff){
        String target = new String(new char[diff]).replace('\0', '0'); // Create a string with a difficculty of '0'
        while(!hash.substring(0, diff).equals(target)){
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block mined!!!: " + hash);
    }
    
}
