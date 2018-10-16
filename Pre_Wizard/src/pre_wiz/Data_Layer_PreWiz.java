package pre_wiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class Data_Layer_PreWiz {
	
	int selected=0;
	ResultSet Project_Data;
	
	public String[] Get_Saudi_IDs(int user_type) throws ClassNotFoundException, SQLException
	 {
		
		 String[] IDs=new String[8];
		
		 List<String> National_IDs_DB = new ArrayList<String>();
		 List<String> Birthdate_DB = new ArrayList<String>();
		 
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		  
		//step2 create  the connection object  
		 
		 String connectionUrl = "jdbc:sqlserver://10.10.52.187:1433;" +  
				   "databaseName=CompaniesContractsIssuePhase-CCI5-P2;user=CompaniesContractsIssueUser;password=P@ssw0rd;"; 
		
		 Connection con = DriverManager.getConnection(connectionUrl);
		
		Statement  stmt_project=con.createStatement();
		
		if(user_type==0)
		{
			Project_Data=stmt_project.executeQuery("SELECT  NationalID FROM [CompaniesContractsIssuePhase-CCI5-P2].[dbo].[Person] "
					+ "where NationalID like '1%' and NationalID is not null");
		}
		else if (user_type==1)
		{
			Project_Data=stmt_project.executeQuery("SELECT  NationalID FROM [CompaniesContractsIssuePhase-CCI5-P2].[dbo].[Person] "
					+ "where NationalID like '2%' and NationalID is not null");
		}
		else if (user_type==2)
		{
			Project_Data=stmt_project.executeQuery("Select username from [dbo].[User] where username like 'kh%'");
		}
		else
		{
			System.out.println("No Data collected");
		}
		
		 
		

			ResultSetMetaData meta = Project_Data.getMetaData();
			int colCount = meta.getColumnCount();
			
			while (Project_Data.next())
			{
				for (int col=1; col <= colCount; col++) 
				{
					Object value = Project_Data.getObject(col);
					if (value != null) 
					{
						if(colCount==1)
						{
							National_IDs_DB.add(value.toString());
						}
						else
							Birthdate_DB.add(value.toString());
						//System.out.print(value.toString());
					}
				}

		 
			}
			IDs=National_IDs_DB.toArray(new String[National_IDs_DB.size()]);
			for(int i=0;i<IDs.length;i++)
			{
				System.out.println(IDs[i]);
			}
			return IDs;//new Object[]{project_Ids, project_name,contract_name,sector_type,project_sectors,contract_val};
	 }

	
	public GET_ECR_Data Get_Saudi_Data() throws ClassNotFoundException, SQLException
	 {
		
		 String[] Saudi_IDs;
		 String[] Saudi_BDates;
		
		 List<String> National_IDs_DB = new ArrayList<String>();
		 List<String> Birthdate_DB = new ArrayList<String>();
		 
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		  
		//step2 create  the connection object  
		 
		 String connectionUrl = "jdbc:sqlserver://10.10.57.181:1433;" +  
				   "databaseName=ECR_Prod;user=ecr_userrw;password=Fseo8WVkAN0ms0oCWLBq;"; 
		
		 Connection con = DriverManager.getConnection(connectionUrl);
		
		Statement  stmt_project=con.createStatement();
		
		
			Project_Data=stmt_project.executeQuery("  SELECT FormalIdentityNumber,[DateOfBirthHijri],Name,Gender "
					+ "FROM [ECR_Prod].[dbo].[Person]"
					+ " Where FormalIdentityTypeID=1 and UserId>=50 and FormalIdentityNumber<>'1092467107'");
		

			ResultSetMetaData meta = Project_Data.getMetaData();
			int colCount = meta.getColumnCount();
			
			while (Project_Data.next())
			{
				for (int col=1; col <= colCount; col++) 
				{
					Object value = Project_Data.getObject(col);
					if (value != null) 
					{
						if(col==1)
						{
							National_IDs_DB.add(value.toString());
						}
						else if (col==2)
							Birthdate_DB.add(value.toString());
						
					}
				}

		 
			}
//			Saudi_IDs=new String[National_IDs_DB.size()];
//			Saudi_BDates=new String[Birthdate_DB.size()];
			
			Saudi_IDs=National_IDs_DB.toArray(new String[National_IDs_DB.size()]);
			Saudi_BDates=Birthdate_DB.toArray(new String[Birthdate_DB.size()]);
			
			
			for(int i=0;i<Saudi_IDs.length;i++)
			{
				System.out.println("Saudi ID : "+Saudi_IDs[i]);
				System.out.println("Saudi BDate : "+Saudi_BDates[i]);
			}
			return new GET_ECR_Data(Saudi_IDs, Saudi_BDates);
	 }
	public String[] Get_Saudi_Birthday(int number_of_partners) throws ClassNotFoundException, SQLException
	 {
		
		 String[] Birthday=new String[8];
		
		 
		 List<String> Birthdate_DB = new ArrayList<String>();
		 
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		  
		//step2 create  the connection object  
		 
		 String connectionUrl = "jdbc:sqlserver://10.10.52.187:1433;" +  
				   "databaseName=CompaniesContractsIssuePhase-CCI5-P2;user=CompaniesContractsIssueUser;password=P@ssw0rd;"; 
		
		 Connection con = DriverManager.getConnection(connectionUrl);
		 
		
		
		Statement  stmt_project=con.createStatement();
		
		ResultSet Project_Data=stmt_project.executeQuery("SELECT Top "+number_of_partners+" NationalID,birthdate FROM [CompaniesContractsIssuePhase-CCI5-P2].[dbo].[Person]");
		

			ResultSetMetaData meta = Project_Data.getMetaData();
			int colCount = meta.getColumnCount();
			
			while (Project_Data.next())
			{
				for (int col=2; col <= colCount; col++) 
				{
					Object value = Project_Data.getObject(col);
					if (value != null) 
					{
						
						Birthdate_DB.add(value.toString());
						//System.out.print(value.toString());
					}
				}

		 
			}
			Birthday=Birthdate_DB.toArray(new String[Birthdate_DB.size()]);
			return Birthday;//new Object[]{project_Ids, project_name,contract_name,sector_type,project_sectors,contract_val};
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 
	 //Connection con=DriverManager.getConnection(  
	//"jdbc:oracle:thin:@10.80.160.17:1521:PRODXDEV","PRJADF","adf");  
	
	
	
	
	/*
	while(Project_Data.next())
	{
		project_Ids=Project_Data.getString(11);
		
			//System.out.println("Project_ID="+Project_Data.getString(11));
			
			//project_Ids[0]=Project_Data.getString(1);//ID
			//project_Ids[1]=Project_Data.getString(2);//Name
			//project_Ids[2]=Project_Data.getString(5);//Contract_Name
			//project_Ids[3]=Project_Data.getString(7);//Sector_Type_ID
			//project_Ids[4]=Project_Data.getString(8);//Project_Sectors
			//project_Ids[5]=Project_Data.getString(9);//Contract_value
			//project_Ids[6]=Project_Data.getString(55);// Contractor Name
			//project_Ids[7]=Project_Data.getString(30);//Contract_val_after_update
		
	}
	
*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	 public String[] connect() throws ClassNotFoundException, SQLException
	 {
		 String[] results=new String[2];
		 
		 
	 
		//step1 load the driver class  
		Class.forName("oracle.jdbc.driver.OracleDriver");  
		  
		//step2 create  the connection object  
		Connection con=DriverManager.getConnection(  
		"jdbc:oracle:thin:@10.80.160.17:1521:PRODXDEV","PRJADF","adf");  
		  
		//step3 create the statement object  
		Statement stmt=con.createStatement();  
		Statement stmt_employee=con.createStatement(); 
		
		//step4 execute query  
		ResultSet contract_result=stmt.executeQuery("Select CMP_Commercial_ID,Rec_Pass from Sys_AMANA_Contractor where CMP_Commercial_ID in ('4030005320')"); 
		
		ResultSet Amana_result=stmt_employee.executeQuery("Select User_ID,User_Password from SYS_USER_MASTER where User_ID=9308"); 
		
		
		
		if(selected==0)
		{
			while(contract_result.next())
			{
				for(int i=0;i<=1;i++)
				{
					System.out.println("Username="+contract_result.getString(1)+" "+"Password="+contract_result.getString(2));
					results[0]=contract_result.getString(1);
					results[1]=contract_result.getString(2);
				}
				
			}
		}
		else
		{
			while(Amana_result.next())
			{
				for(int i=0;i<=1;i++)
				{
					System.out.println("Username="+Amana_result.getString(1)+" "+"Password="+Amana_result.getString(2));
					results[0]=Amana_result.getString(1);
					results[1]=Amana_result.getString(2);
				}
				
			}
		}
		
		
		
		
		
		
		
		
		  
		  
		//step5 close the connection object  
		con.close();
		return results;  
		  
	 }
}
