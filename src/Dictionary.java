import java.io.*;
import java.util.*;

class Dictionary 
{
	public Dictionary()
	{
		init_dictionary();
	}

	ArrayList<DictWord> dictionary = new ArrayList<DictWord>();
	//input text as string

	public void init_dictionary()
	{
		try
		{
			Scanner in = new Scanner(new FileReader("code-wordmapping"));
			//String code_str;

			while(in.hasNextLine())
			{
				String code_str = in.nextLine();
				code_str = code_str.substring(0, code_str.length()-1);
				String word;
				while((in.hasNextLine())&&(!(word=in.nextLine()).equals("")))
				{
					//word = in.nextLine();
					dictionary.add(new DictWord(code_str, word));
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("cos");
		}
	}
}

