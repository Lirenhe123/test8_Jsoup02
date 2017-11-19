package priv.lirenhe.jsoup.test01.test8_Jsoup02;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * doc.getElementsByTag
 * doc.getElementById
 * doc.getElementsByClass
 * doc.getElementsByAttribute
 * doc.getElementsByAttributeValue
 * @author lenovo
 *
 */
public class Demo01 {
	public static void main(String[] args) 
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault(); // 创建httpclient实例
        HttpGet httpget = new HttpGet("http://www.cnblogs.com/"); // 创建httpget实例
          
        CloseableHttpResponse response = httpclient.execute(httpget); // 执行get请求
        HttpEntity entity=response.getEntity(); // 获取返回实体
        String webContent=EntityUtils.toString(entity, "utf-8");
//        System.out.println("网页内容："+webContent); // 指定编码打印网页内容
        response.close(); // 关闭流和释放系统资源
        
        File outfile=new File("cnblog.txt");
        BufferedWriter bos=new BufferedWriter(
        		new OutputStreamWriter(new FileOutputStream(outfile)));
        bos.write(webContent,0,webContent.length());
        bos.close();
        
        Document doc=Jsoup.parse(webContent);
        Elements elements=doc.getElementsByTag("title");
        Element title=elements.get(0);
        System.out.println("title:"+title.text());
        
        Element part01=doc.getElementById("site_nav_top");
        System.out.println("site_nav_top:"+part01.text());
        
        /*
        System.out.println("************************");
        Elements postItemElements=doc.getElementsByClass("post_item"); // 根据样式名称来查询DOM
        for(Element e:postItemElements){
            System.out.println(e.html());
            System.out.println("================");
        }
         */
        /*
        Elements widthElements=doc.getElementsByAttribute("width"); // 根据属性名来查询DOM
        for(Element e:widthElements){
            System.out.println(e.toString());
            System.out.println("================");
        }
        */
         
        System.out.println("target-_blank");
        Elements targetElements=doc.getElementsByAttributeValue("target", "_blank");  // 根据属性名和属性值来查询DOM
        for(Element e:targetElements){
            System.out.println(e.toString());
            System.out.println("================");
        }
        
	}
}
