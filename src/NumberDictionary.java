
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

class NumberDictionary
{
    HashMap<String, Parent> hashDict;
	public NumberDictionary()
	{
		HashMap<String, Integer> frequencyMap = new HashMap<String, Integer>();

		//TODO populate frequncy list
		try
		{
			Scanner fs = new Scanner(new FileReader("freqList.txt"));

	        while(fs.hasNextLine())
	        {
	        	int rank = fs.nextInt();
	        	String word = fs.nextLine().toLowerCase().trim();
	        	frequencyMap.put(word.trim(), new Integer(rank));
	        }
		}
		catch(Exception e)
		{
			
		}
		hashDict = new HashMap<String, Parent>();
		Dictionary d = new Dictionary();
		
		int ii=0;
		//iterate through all words in dictionary and 
		for(DictWord word: d.dictionary)
		{
			ii++;
			//System.out.println(word.word);
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
		//System.out.println(hashDict.get((Long)(long) 57776463146752L).children.get(1).word);
		try
		{	
			FileWriter writer = new FileWriter(new File("Output.json"));
			JsonWriter w = new JsonWriter(writer);
			Gson gson = new Gson();
			gson.toJson(hashDict,writer);
			w.flush();
			w.close();
			//gson.toJson(hashDict, (new TypeToken< HashMap<String, Parent> >(){}).getType(), w);
			//String s = gson.toJson(hashDict,w);
			/*FileOutputStream fileOut = new FileOutputStream("hashdict");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(hashDict);	*/
		}
		catch(Exception e)
		{
		}
    	/*
    	try{
	        FileInputStream fileIn = new FileInputStream("hashdict");
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        hashDict = (HashMap<Long, Parent>) in.readObject();
    	}
    	catch(Exception e)
    	{
    	}
    	*/
    	
		/*
		Iterator<Map.Entry<Long, Parent>> i = hashDict.entrySet().iterator(); 
		while(i.hasNext()){
			Long key = i.next().getKey();
			System.out.println(key+", ");
			Parent p = hashDict.get(key);
			for(Child c : p.children)
			{
				System.out.println(c.word + " " + c.frequency);
			}
			System.out.println();
		}
		*/
    }
    public ArrayList<String> getList(String code_str)
    {
    	Parent p = hashDict.get(code_str);
    	ArrayList<String> result = new ArrayList<String>();
    	if(p!=null)
    	{
	    	for(Child c : p.children)
	    	{
	    		result.add(c.word);
	    	}
    	}
    	else
    	{
    			System.out.println("correct");
    			p = error_correct(code_str,0);
    			if(p==null)
    				return null;
    			
    	    	for(Child c : p.children)
    	    	{
    	    		result.add(c.word);
    	    	}
    	}
    	return result;
    }
	public static int get_frequency(HashMap<String, Integer> fl, String word)
	{
		//System.out.println(word);
		if(fl.containsKey(word))
		{
			return fl.get(word);
		}
		else
			return 70000;
	}
	public Parent error_correct(String str, int i)
	{
		//System.out.println(i);
		if(i==str.length())
		{
			
			return null;
		}
		
		if(hashDict.containsKey(str))
		{
			return hashDict.get(str);
		}
		else
		{
			//System.out.println(i);
			Parent result;
			
			result = error_correct(str,i+1);
			if(result !=null) 	
				return result;
			
			char c = str.charAt(i);
			if(c<'8')
			{
				
				c+=1;
				char[] ns = str.toCharArray();
				ns[i]=c;
				String new_str = String.valueOf(ns);

				if(hashDict.containsKey(new_str))
				{
					//System.out.println(i);
					//System.out.println(new_str);
					return hashDict.get(new_str);
				}
				result = error_correct(str,i+1);
				if(result !=null) 	
					return result;
			}
			if(c>'0')
			{
				c = str.charAt(i);
				c-=1;
				char[] ns = str.toCharArray();
				ns[i]=c;
				String new_str = String.valueOf(ns);
				if(hashDict.containsKey(new_str))
					return hashDict.get(new_str);
				result = error_correct(str,i+1);
				if(result !=null) 	
					return result;
			}
		}
		return null;
	}
}