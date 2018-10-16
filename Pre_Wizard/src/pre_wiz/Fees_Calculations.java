package pre_wiz;

public class Fees_Calculations {
	int main_fees=0;
	int publish_fees=0;
	int operation_fees=0;
	double vat_fees=0;
	
	public int calculate_main_fees(int company_type, int no_of_years)
	{
		if(company_type==1)
		{
			main_fees=1200*no_of_years;
			return main_fees;
		}
		else
			main_fees=800*no_of_years;
			return main_fees;
	}
	
	public int calculate_publish_fees(int company_type)
	{
		if(company_type==1)
		{
			publish_fees=500;
			return publish_fees;
		}
		else
			publish_fees=650;
			return publish_fees;
	}
	
	public int calculate_operation_fees(int company_type)
	{
		if(company_type==1)
		{
			operation_fees=200;
			return operation_fees;
		}
		else
			operation_fees=0;
			return operation_fees;
	}
	
	public double calculate_VAT(double coc,int company_type)
	{
		int value_of_publish=calculate_publish_fees(company_type);
		vat_fees=((coc+value_of_publish)*0.05);
		return vat_fees;
	}
	

}
