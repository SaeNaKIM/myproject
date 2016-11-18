public class Command_Option
{
	public int NumberOfMDFiles;

	/*
	같은 배열 인덱스에는 하나의 통합된 데이터로 보시면 됩니다.
	예를 들어, argument가 a.md --style fancy --name b.html c.md --style slide --name k.html 과 같이 들어올 경우
	MDFiles[0]=a.md
	styles[0]=fancy
	HTMLFiles[0]=b.html
	이 한 묶음이 되고

	MDFiles[1]=c.md
	styles[1]=slide
	HTMLFiles[0]=k.html이 됩니다. 
	*/
	private String MDFiles[];
	private String styles[];
	private String HTMLFiles[];

	public Command_Option(String args[])
	{
		// Input File 이 없을 경우 에러를 출력하고 종료합니다.
		if(args.length==0)
		{
			System.err.println("Error : No Input Files");
			System.exit(-1);
		}

		NumberOfMDFiles = Number_Of_MD_Files(args);	// Input argument에서 MD 파일 개수를 구합니다. 
		DefaultValueSet(args);	// 기본적으로 style은 plain, html name은 a.html로 지정됩니다. 

		try
		{
			Parsing(args);	// argument로 들어온 값들을 분류합니다. 
		}
		catch(Exception_Command_Option e)
		{
			System.out.println(e.Get_Error_Msg());
			System.exit(-1);
		}
		catch(Exception e)
		{
			// Exception_Command_Option class에서 잡지 못한 에러를 잡아냅니다.
			e.printStackTrace();
			System.exit(-1);
		}
	}

	private void Parsing(String args[]) throws Exception_Command_Option
	{
		int argument_count=args.length;
		int idx=-1; // idx를 기준으로 한 묶음으로 생각하시면 됩니다. 

		for(int i=0; i<argument_count; i++)
		{
			if(args[i].equals("--name"))
			{
				HTMLFiles[idx]=args[++i];
			}
			else if(args[i].equals("--style"))
			{
				int next = ++i;
				if(args[next].equals("plain"))
					styles[idx]="Plain";
				else if(args[next].equals("fancy"))
					styles[idx]="fancy";
				else if(args[next].equals("slide"))
					styles[idx]="slide";
				else
					throw new Exception_Command_Option(String.format("<< boolean Parsing(String args[]) >>\nUnrecognized style option : %s", args[next]));
			}
			else if(args[i].equals("--help"))
			{
				Print_Help_Option();
				System.exit(-1);
			}
			else if(args[i].contains("--"))
			{
				throw new Exception_Command_Option(String.format("<< boolean Parsing(String args[]) >>\nUnrecognized command line option : %s", args[i]));
			}
			else if(args[i].contains(".md"))
			{
				idx++;
				MDFiles[idx]=args[i];
			}
		}
	}
	private int Number_Of_MD_Files(String args[])
	{
		int ret=0;
		for(int i=0; i<args.length; i++)
			if(args[i].contains(".md"))
				ret++;
		return ret;
	}

	private void DefaultValueSet(String args[])
	{
		MDFiles = new String[NumberOfMDFiles];
		styles = new String[NumberOfMDFiles];
		HTMLFiles = new String[NumberOfMDFiles];
		int idx=0;
		for(int i=0; i<args.length; i++)
		{
			if(args[i].contains(".md"))
			{
				MDFiles[idx]="a.md";
				styles[idx] = "Plain";
				HTMLFiles[idx]=args[i].replace(".md", ".html");
			}
		}
	}



	private void Print_Help_Option()
	{
		System.out.println("Usage : java Converter_Main md_name [Options..] ..");
		System.out.println("Options:");
		System.out.println("There is no option with md_file.md");
		System.out.println("No use Special Character in md file name and html file name");
		System.out.println();
		System.out.println("  --name     Provide output html file name");
		System.out.println(" Ex. --name --hello.html");
		System.out.println();
		System.out.println("  --style    Provide output html file style");
		System.out.println("             three kinds of style : plain(default), fancy, slide");
		System.out.println(" Ex. --style --fancy");
		System.out.println();
		System.out.println("For bug reporting instructions, please see:");
		System.out.println("<https://github.com/HandongChoi/SE-C.git>");		
		System.out.println();
		System.out.println("Program version 0.1");
		System.out.println("Developer : choihyoeun, heosoomin, leejaehun, mariajeong, saenakim");
		System.out.println();
	}


	public String Get_MDFiles(int idx)
	{
		return MDFiles[idx];
	}

	public String Get_Styles(int idx)
	{
		return styles[idx];
	}

	public String Get_HTMLFiles(int idx)
	{
		return HTMLFiles[idx];
	}

}

// Command_Option에 대한 사용자 정의 Exception class 입니다. 
class Exception_Command_Option extends Exception
{
	private final int ERROR_CODE;
	private final String ERROR_MSG;
	Exception_Command_Option(String msg, int err_code)
	{
		super(msg);
		ERROR_CODE = err_code;
		ERROR_MSG = msg;
		System.out.println("Command Option Error");	
	}
	Exception_Command_Option(String msg)
	{
		super(msg);
		ERROR_MSG=msg;
		ERROR_CODE=100; // Initialized 100;
		System.out.println("Command Option Error");
	}

	public int Get_Error_Code()
	{
		return ERROR_CODE;
	}
	public String Get_Error_Msg()
	{
		return ERROR_MSG;
	}

}
