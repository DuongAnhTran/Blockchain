import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.util.Scanner;

public class Chain {
    
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int diff = -1;

    public static void main(String[] args){ //The value will be different since the timestamp is different

        // Block genesisBlock = new Block ("Hi, this is the first block", "0");
        // System.out.println("Hash for block 1: " + genesisBlock.hash);
    
        // Block secondBlock = new Block("This is the second block", genesisBlock.hash);    //Testing codes
        // System.out.println("Hash for block 2:" + secondBlock.hash);
        
        // Block thridBlock = new Block("This is the third block", secondBlock.hash);
        // System.out.println("Hash for block 3: " + thridBlock.hash);
        Scanner sc = new Scanner(System.in);

        while(diff == -1){
            System.out.print("Please enter the desired difficulty: ");
            diff = sc.nextInt();
        }

        System.out.println("The chosen difficulty is: " + diff);
    
        //adding blocks to the blockchain arraylist
        blockchain.add(new Block ("Hi, this is the first block", "0"));
        System.out.println("Trying to mine for block 1...");
        blockchain.get(0).mineBlock(diff);
        
        blockchain.add(new Block ("This is the second block", blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to mine for block 2...");
        blockchain.get(1).mineBlock(diff);
        
        
        blockchain.add(new Block ("This is the 3rd block", blockchain.get(blockchain.size()-1).hash));    
        System.out.println("Trying to mine for block 3...");
        blockchain.get(2).mineBlock(diff);

        System.out.println("\nBlockchain is valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe block chain: ");
        System.out.println(blockchainJson);
    }



    public static Boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[diff]).replace('\0', '0');

        //A loop through th4e blockchain to check hashes:
        for (int i = 1; i< blockchain.size(); i++){
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i-1);

            //comparing the registered hash and the calculated hash:
            if (!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("Current Hashes not equal");
                return false;
            }

            //comparing previous hash and registered previous hash:
            if(!previousBlock.hash.equals(currentBlock.prevHash)){
                System.out.println("Previous Hashes not equal");
                return false;
            }

            //check if hash is solved:
            if(!currentBlock.hash.substring(0, diff).equals(hashTarget)){
                System.out.println("This block hasn't been mined");
                return false;
            }
        }
        return true;
    }


}
