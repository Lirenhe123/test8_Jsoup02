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
public class Demo02 {
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
        
        /*
        Elements elementsTitile=doc.select("#post_list .post_item .post_item_body h3 a");
        //cssQuery：字符串从前往后，在寻找元素时等同于从外往里：先找到class=post_item，在这个代码块中
        //寻找class=post_item。。。。以此类推
        for(Element element:elementsTitile){
        	System.out.println("博客标题： "+element.text());
        }
        */
        
        System.out.println("***********************");
        
        Elements elementsHref=doc.select("a[href]");
        for(Element element:elementsHref){
        	System.out.println("超链接： "+element.toString());
        }
        
        System.out.println("***********************");
        /*
        Elements elementsImg=doc.select("img[src$=.png]");
        for(Element element:elementsImg){
        	System.out.println("图片： "+element.toString());
        }
        */
	}
}
