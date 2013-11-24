import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;


class TestDictionary
{

	static int get_frequency(HashMap<String, Integer> fl, String word)
	{
		//System.out.println(word);
		if(fl.containsKey(word))
		{
			return fl.get(word);
		}
		else
			return 70000;
	}
	
	public static void main(String args[])
	{
		NumberDictionary d = new NumberDictionary();
		System.out.println(d.getList("45"));
	}
	
	/*
	public static void main(String args[]) throws java.io.IOException
	{
		HashMap<String, Integer> frequencyMap = new HashMap<String, Integer>();

		//TODO populate frequncy list
		Scanner fs = new Scanner(new FileReader("freqList.txt"));
        
        while(fs.hasNextLine())
        {
        	int rank = fs.nextInt();
        	String word = fs.nextLine().toLowerCase().trim();
        	frequencyMap.put(word.trim(), new Integer(rank));
        }
        
		HashMap<Long, Parent> hashDict = new HashMap<Long, Parent>();
		Dictionary d = new Dictionary();
		

		//iterate through all words in dictionary and 
		for(DictWord word: d.dictionary)
		{
			if (hashDict.get(word.code) ==null)
			{
				//first word added to hashmap
				Parent p = new Parent();
				//create a new parent
				int frequency = get_frequency(frequencyMap, word.word);
				Child c = new Child(word.word,frequency);
				p.add_child(c);
				hashDict.put(word.code, p);	
				//assign word as child of parent
			}
			else
			{
				//add new word to parent
				Parent p = hashDict.get(word.code);
				//TODO for each child, check if word is in frequency list, and if so add frequency to child const
				//for()
				int frequency = get_frequency(frequencyMap, word.word);
				Child c = new Child(word.word,frequency);	
				p.add_child(c);
			}
		}

		Iterator<Map.Entry<Long, Parent>> i = hashDict.entrySet().iterator(); 
		PrintWriter w = new PrintWriter("wordhash.txt");
		while(i.hasNext()){
			Long key = i.next().getKey();
			w.println(key+", ");
			Parent p = hashDict.get(key);
			for(Child c : p.children)
			{
				w.println(c.word + " " + c.frequency);
			}
			w.println();
		}
		
		FileOutputStream fileOut = new FileOutputStream("hashdict");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(hashDict);	
	}*/
}
