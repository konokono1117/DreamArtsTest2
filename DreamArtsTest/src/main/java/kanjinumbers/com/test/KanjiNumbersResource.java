package kanjinumbers.com.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;



@Path("/vi")
public class KanjiNumbersResource {
  
	@GET
	@Path("/number2kanji/{number}")
	@Produces("text/html; charset=UTF-8")
	public String toKansuji(@PathParam("number") long number) {
	  
	    if (number == 0) {
	        return "零";
	    }

	    if (number > 9999999999999999L || number < 0) {
	    	return "204";
	    }
	        
	    String[] kl = new String[] { "", "拾", "百", "千" };
	    String[] tl = new String[] { "", "万", "億", "兆"};
	    String[] nl = new String[] { "", "壱", "弐", "参", "四", "五", "六", "七", "八", "九" };
	    String str = "";
	    int keta = 0;
	    while (number > 0 && number <= 9999999999999999L) {
	        int k = keta % 4;
	        int n = (int)(number % 10);
	                
	        if (k == 0 && number % 10000 > 0) {
	            str = tl[keta / 4] + str;
	        }
	                
	        if (n != 0) {
	            str = nl[n] + kl[k] + str;
	        }
	                
	        keta++;
	        number /= 10;
	    }
	    return str;  
	}
  
  	@GET
  	@Path("/kanji2number/{str}")
  	@Produces("text/html; charset=UTF-8")
	public String toSuji(@PathParam("str") String str) {
	    int nl = 1;
	    int kl = 0;
	    long ans = 0;
	    long sum = 0;
	    for (int i=0; i<str.length(); i++) {
	        switch (str.charAt(i)) {
	        case '零':
	            nl = 0;
	            break;
	        case '壱':
	            nl = 1;
	            break;
	        case '弐':
	            nl = 2;    
	            break;
	        case '参':
	            nl = 3;    
	            break;
	        case '四':   
	            nl = 4;    
	            break;
	        case '五':   
	            nl = 5;    
	            break;
	        case '六':   
	            nl = 6;    
	            break;
	        case '七':
	            nl = 7;    
	            break;
	        case '八':
	            nl = 8;    
	            break;
	        case '九':
	            nl = 9;    
	            break;
	        case '拾':
	            kl += (nl!=0?nl:1) * 10;  nl = 0;    
	           break;
	        case '百':
	           kl += (nl!=0?nl:1) * 100; nl = 0;    
	           break;
	        case '千':
	           kl += (nl!=0?nl:1) * 1000; nl = 0;    
	           break;
	        case '万':
	           ans += (kl!=0?kl:1)*10000; kl = 0; nl = 0;    
	           break;
	        case '億':
	           ans += (kl!=0?kl:1)*100000000; kl = 0; nl = 0;    
	           break;
	           case '兆':
	           ans += (kl!=0?kl:1)*1000000000000L; kl = 0; nl = 0;
	        default:
	            return "204";
	            }
	        }
	    if (ans + kl + nl <= 9999999999999999L) {
	    	sum = ans + kl + nl;
	        return String.valueOf(sum);
	    } else {
	        return "204";
	    }
	    }
  	
  	
}