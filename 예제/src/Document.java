import java.io.*;

public class Document
{
	private String buffer;
	//private Node nodeList[];

	public Document(String file_name)
	{
		try
		{
			File file = new File(file_name);
			buffer = readFile(file);
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("Document.class Error");
			System.out.println("public Document(String file_name) Error");
			System.out.println("Error code : 0");
			System.out.println("File Not Found");
			System.exit(-1);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			System.exit(-1);
		}
	}

	public String readFile(File file) throws FileNotFoundException, IOException
	{
		FileReader freader = new FileReader(file);
		BufferedReader breader = new BufferedReader(freader);

		String line=null;
		String md_file_contents="";

		while((line=breader.readLine())!=null)
		{
			md_file_contents += line;
			md_file_contents += "\n";
		}
		breader.close();

		return md_file_contents;
	}
	/*
	public Node[] getNode()
	{
		Node test[] = new Node[2];
		return test;
	}
*/
	public String getBuffer()
	{
		return buffer;
	}
}