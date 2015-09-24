package penic.eu.equalshare;
import java.util.ArrayList;

/**
 * Created by ales on 22.9.2015.
 * Modified and adapted by samo on 23.9.2015.
 */
public class DataObj {
	public ArrayList<String> name;
	public ArrayList<Float> totalPaid;
	public ArrayList<Float> weight;
    public ArrayList<Float> balance;

    public DataObj(){
        name=new ArrayList<String>();
        totalPaid=new ArrayList<Float>();
        weight=new ArrayList<Float>();
        balance=new ArrayList<Float>();
    }
    
    public void calc(){
        float Sx=0.0f;
        float Sw=0.0f;
        float c= 0.0f;

        for (int k=0;k<totalPaid.size();k++) {
            Sx=Sx+totalPaid.get(k); //data.xx[k];
            Sw=Sw+weight.get(k);  //data.ww[k];
        }
        c=Sx/Sw;
        for (int k=0;k<totalPaid.size();k++) {
            balance.set(k, (float) (c * weight.get(k) - totalPaid.get(k)));
        }
    }
}
