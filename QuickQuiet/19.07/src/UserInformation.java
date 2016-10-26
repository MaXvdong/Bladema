import java.io.IOException;
import java.io.*;
public class UserInformation {
    public void writetofile(String strBuffer)   //×¢²áÕË»§
	{
		File f = new File("D:\\account.txt");
		if(!f.exists())
	    {
	    	try
	    	{
	    	    f.createNewFile();
	    	}
	    	catch(Exception e){}
	    	
	    }
		try
		{
			FileWriter a =new FileWriter("D:\\account.txt",true);
			a.write(strBuffer+"#");
			a.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public int readtofile(String s)   //¼ì²âÕË»§ÊÇ·ñ´æÔÚ
	{
		int total=0;
		String part="#",all="",temp="";
		File f = new File("D:\\account.txt");
		if(!f.exists())
	    {
	    	try
	    	{
	    	    f.createNewFile();
	    	}
	    	catch(Exception e){}
	    	
	    }
		try{
		    FileReader b=new FileReader("D:\\account.txt");
		    BufferedReader c=new BufferedReader(b);
		    while((temp=c.readLine())!=null)
		    {
		    	all+=temp;
			
		    }
		    c.close();
		    b.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		for(String tmp=all;tmp!=null&&tmp.length()>=part.length();)
		{
			if(tmp.indexOf(part)==0)
			{
				total++;
			}
			tmp=tmp.substring(1);
		}
		String data[]=all.split("\\#");
		for(int i=0;i<total;i++)
		{
			if(data[i].equals(s))
			{
				return 1;
			}
		}
		
		return 0;
	}
}
