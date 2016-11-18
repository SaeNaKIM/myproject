// 전체적인 흐름을 결정하는 Main 클래스 입니다.

public class Converter_Main
{
	private int n_md_file;	// 입력으로 들어온 md file의 개수입니다. MD_TO_HTML_DATA 클래스 오브젝트 생성 개수를 결정합니다.
	private Command_Option cmo;	// Command Option에 관한 객체로, 어떠한 Option이 들어왔는지 관리합니다. 
	private Document doc[]; // Converter를 함에 있어서 필요하는 모든 데이터를 포함하고 처리하는 클래스입니다.  

	public static void main(String args[])
	{
		Converter_Main CMain = new Converter_Main();

		CMain.cmo = new Command_Option(args);
		
		CMain.n_md_file = CMain.cmo.NumberOfMDFiles;
		CMain.doc = new Document[CMain.n_md_file];

		for(int i=0; i<CMain.n_md_file; i++)
			CMain.doc[i] = new Document(CMain.cmo.Get_MDFiles(i));

		CMain.Parser_Test();
	}

	// Parsing이 제대로 동작하는지를 판단하는 테스트 함수입니다. 
	private void Parser_Test()
	{
		for(int i=0; i<n_md_file; i++)
		{
			System.out.println(String.format("MD File Name : %s", cmo.Get_MDFiles(i)));
			System.out.println(String.format("Style : %s", cmo.Get_Styles(i)));
			System.out.println(String.format("Html File Name : %s", cmo.Get_HTMLFiles(i)));

			System.out.println("--------------------------------------------------------");

			System.out.println("MD File Contents");
			System.out.println(doc[i].getBuffer());

			System.out.println();
			System.out.println();

			System.out.println("--------------------------------------------------------");
		}

	}
}