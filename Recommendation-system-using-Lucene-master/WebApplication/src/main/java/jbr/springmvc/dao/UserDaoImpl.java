package jbr.springmvc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.sql.*; 
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.opencsv.CSVWriter;

import jbr.springmvc.model.Posts;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;


public class UserDaoImpl implements UserDao {
	  @Autowired
	  JdbcTemplate jdbcTemplate;

	  @Autowired
	  DataSource datasource;
	  
	  String[] res=new String[12];
	  
	  
	  public String[] fetch_recommendation(String post_number) throws IOException, ParseException {
			 
			File dataDir = new File("C:\\Users\\Abhinethri\\Desktop\\Assignment2\\Content"); //my sample file folder path
			// Check whether the directory to be indexed exists
			if (!dataDir.exists() || !dataDir.isDirectory()) {
				throw new IOException(dataDir + " does not exist or is not a directory");
			}
			Directory indexDir = new RAMDirectory();
			
			// Specify the analyzer for tokenizing text.
			StandardAnalyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			IndexWriter writer = new IndexWriter(indexDir, config);
			
			// call indexDirectory to add to your index
			// the names of the txt files in dataDir
			indexDirectory(writer, dataDir);
			writer.close();
			 
			//Query string! 
			
			
			// Write the regular expression or the query here
			String querystr="";
			if(post_number.equals("post2")) 
				{
				res[0]="I was presented with this question in an end of module open book exam today and found myself lost.\r\n" + 
						"i was reading Head first Java and both definitions seemed to be exactly the same.\r\n" + 
						"i was just wondering what the MAIN difference was for my own piece of mind.\r\n" + 
						"i know there are a number of similar questions to this but, none i have seen which provide a definitive answer.Thanks, Darren";
				res[1]="N/A";
				querystr = "copy constructor";
				}
			else if(post_number.equals("post1"))
				{
				res[0]="One way to implement deep copy is to add copy constructors to each associated class.\r\n" + 
						"A copy constructor takes an instance of 'this' as its single argument and copies all the values from it.\r\n" + 
						"Quite some work, but pretty straightforward and safe. EDIT: note that you don't need to use accessor methods to read fields.\r\n" + 
						"You can access all fields directly because the source instance is always of the same type as the instance\r\n" + 
						"with the copy constructor. Obvious but might be overlooked. \r\n" + 
						"Example: Edit: Note that when using copy constructors you need to know the runtime type of the object you are copying. \r\n" + 
						"With the above approach you cannot easily copy a mixed list (you might be able to do it with some reflection code).";
				res[1]="public class Order\r\n" + 
						"{\r\n" + 
						"	private long number;\r\n" + 
						"	public Order() {}/** * Copy constructor */\r\n" + 
						"	public Order(Order source) {number = source.number;}\r\n" + 
						"}\r\n" + 
						"public class Customer \r\n" + 
						"{\r\n" + 
						"	private String name;\r\n" + 
						"	private List<Order> orders = new ArrayList<Order>();\r\n" + 
						"	public Customer() {}/** * Copy constructor */\r\n" + 
						"	public Customer(Customer source)\r\n" + 
						"	{\r\n" + 
						"	name = source.name;\r\n" + 
						"	for (Order sourceOrder : source.orders)\r\n" + 
						"	{\r\n" + 
						"	orders.add(new Order(sourceOrder));\r\n" + 
						"	}\r\n" + 
						"	}\r\n" + 
						"	public String getName()\r\n" + 
						"	{\r\n" + 
						"	return name;\r\n" + 
						"	}\r\n" + 
						"	public void setName(String name) \r\n" + 
						"	{\r\n" + 
						"	this.name = name;\r\n" + 
						"	}\r\n" + 
						"}";
				querystr = "copy constructor";		
				}
			 else if(post_number.equals("post3")) 
			 {
				res[0]="Inheritance is when a 'class' derives from an existing 'class'.\r\n" + 
						"So if you have a Person class, then you have a Student class that extends Person, Student inherits all the things that Person has.\r\n" + 
						"There are some details around the access modifiers you put on the fields/methods in Person, but that's the basic idea.\r\n" + 
						"For example, if you have a private field on Person, Student won't see it because its private, and private fields are not visible to subclasses.\r\n" + 
						"Polymorphism deals with how the program decides which methods it should use, depending on what type of thing it has.\r\n" + 
						"If you have a Person, which has a read method, and you have a Student which extends Person, which has its own implementation of read, which method gets called is determined for you by the runtime, depending if you have a Person or a Student.\r\n" + 
						"It gets a bit tricky, but if you do something likePerson p = new Student();p.read();the read method on Student gets called.\r\n" + 
						"Thats the polymorphism in action.You can do that assignment because a Student is a Person, but the runtime is smart enough to know that the actual type of p is Student.\r\n" + 
						"Note that details differ among languages.You can do inheritance in javascript for example, but its completely different than the way it works in Java.";
				res[1]="N/A";
				querystr = "inheritance | polymorphism | access modifiers";		
				}
			else if(post_number.equals("post4")) 	{
				res[0]="Polymorphism: The ability to treat objects of different types in a similar manner.Example: Giraffe and Crocodile are both Animals, and animals can Move.\r\n" + 
						"If you have an instance of an Animal then you can call Move without knowing or caring what type of animal it is.\r\n" + 
						"Inheritance: This is one way of achieving both Polymorphism and code reuse at the same time.\r\n" + 
						"Other forms of polymorphism:There are other way of achieving polymorphism, such as interfaces, which provide only polymorphism but no code reuse \r\n" + 
						"(sometimes the code is quite different, such as Move for a Snake would be quite different from Move for a Dog, in which case an Interface would be \r\n" + 
						"the better polymorphic choice in this case.In other dynamic languages polymorphism can be achieved with Duck Typing, which is the classes don't even need to share the same base class or interface,\r\n" + 
						" they just need a method with the same name.Or even more dynamic like Javascript, you don't even need classes at all, just an object with the same method name can be used polymorphically.\r\n" ;
				res[1]="N/A";
				querystr = "polymorphism";		
				}
			else if(post_number.equals("post5")) 	{
				res[0]="I found out that the above piece of code is perfectly legal in Java. I have the following questions. \r\n" + 
						"ThanksAdded one more question regarding Abstract method classes.";
				res[1]="N/A";
				querystr = "abstract classes";		
				}
			else if(post_number.equals("post6"))	{
				res[0]="In java it's a bit difficult to implement a deep object copy function. \r\n "
						+ "What steps you take to ensure the original object and the cloned one share no reference?";
				res[1]="N/A";
				querystr = "clone | deep copy";		
				}
			else if(post_number.equals("post7")) 	{
				res[0]="You can make a deep copy serialization without creating some files. Copy: Restore:";
				res[1]="ByteArrayOutputStream bos = new ByteArrayOutputStream();\r\n" + 
						"ObjectOutputStream oos = new ObjectOutputStream(bos);\r\n" + 
						"oos.writeObject(object);\r\n" + 
						"oos.flush();\r\n" + 
						"oos.close();bos.close();\r\n" + 
						"byte[] byteData = bos.toByteArray();\r\n" + 
						" ByteArrayInputStream bais = new ByteArrayInputStream(byteData);\r\n" + 
						" (Object) object = (Object) new ObjectInputStream(bais).readObject();";
				querystr = "serialization | deep copy";		
				}
			else if(post_number.equals("post8")) 	{
				res[0]="Java has the ability to create classes at runtime. These classes are known as Synthetic Classes or Dynamic Proxies.\r\n" + 
						" See for more information. Other open-source libraries, such as and also allow you to generate synthetic classes, and are more powerful than the\r\n" + 
						" libraries provided with the JRE. Synthetic classes are used by AOP (Aspect Oriented Programming) libraries such as Spring AOP and AspectJ, \r\n" + 
						" as well as ORM libraries such as Hibernate. ";
				res[1]="N/A";
				querystr = "Synthetic Classes | Dynamic Proxies";		
				}
			else if(post_number.equals("post9"))	{
				res[0]="In short: the web server issues a unique identifier to on his visit. The visitor must bring back that ID for him to be recognised next time around.\r\n" + 
						" This identifier also allows the server to properly segregate objects owned by one session against that of another. If is: If is: Once he's \r\n" + 
						" on the service mode and on the groove, the servlet will work on the requests from all other clients.\r\n" + 
						" Why isn't it a good idea to have one instance per client? Think about this: Will you hire one pizza guy for every order that came?\r\n" + 
						" Do that and you'd be out of business in no time. It comes with a small risk though. \r\n" + 
						" Remember: this single guy holds all the order information in his pocket: so if you're not cautious about, he may end up giving the wrong order to a certain client.\r\n" ;
				res[1]="N/A";
				querystr = "client | server | servlet";		
				}
			else if(post_number.equals("post10"))
			{
				res[1]="N/A";
				res[0]="A safe way is to serialize the object, then deserialize.This ensures everything is a brand new reference.\r\n" + 
						"about how to do this efficiently. Caveats: It's possible for classes to override serialization such that new instances are created, \r\n" + 
						"e.g. for singletons.Also this of course doesn't work if your classes aren't Serializable.";
				querystr = "deserialize | serialize ";		
				}
			
			
			
			/*Scanner console = new Scanner(System.in);
			String querystr = "contents:"+console.nextLine();
			System.out.println(querystr); */

			
			Query q = new QueryParser( "contents", analyzer).parse(querystr);
			int hitsPerPage = 10;
			IndexReader reader = null;
			 
			
			 
			 TopScoreDocCollector collector = null;
			 IndexSearcher searcher = null;
			 reader = DirectoryReader.open(indexDir);
			 searcher = new IndexSearcher(reader);
			 collector = TopScoreDocCollector.create(hitsPerPage);
			 searcher.search(q, collector);
			 
			 
			 
			 ScoreDoc[] hits = collector.topDocs().scoreDocs;
			 System.out.println("Found " + hits.length + " hits.");
			 System.out.println();
			 int j=2;
			 
			 String basic_path=System.getProperty("user.home")+"/Desktop/"+"/Assignment2/"+"/Content/";
			 for (int i = 0; i < hits.length; ++i) {
				 String content = "";
				 int docId = hits[i].doc;
				 Document d;
				 d = searcher.doc(docId);
				 
				     String filePath= basic_path+ d.get("filename");
				     System.out.println((i + 1) + ". " + filePath);
				 	 content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
                     if(j<12)
				     res[j++]=content;

			 }
			 reader.close();
			 
			 
			 return res;
		 }
  
	  
	  
	  
	 
	  private static void indexDirectory(IndexWriter writer, File dir) throws IOException {
			//File dir1 = new File("C:\\Users\\Abhinethri\\Desktop\\ASU 3rd sem\\AW\\Assignment2\\content");
			System.out.println(dir);
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File f = files[i];
				if (f.isDirectory()) {
					indexDirectory(writer, f); // recurse
				} else if (f.getName().endsWith(".txt")) {
					// call indexFile to add the title of the txt file to your index (you can also index html)
					indexFile(writer, f);
				}
			}
		}
		private static void indexFile(IndexWriter writer, File f) throws IOException {
			System.out.println("Indexing " + f.getName());
			Document doc = new Document();
			doc.add(new TextField("filename", f.getName(), TextField.Store.YES));
			
			
			//open each file to index the content
			try{
				
					FileInputStream is = new FileInputStream(f);
			        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			        StringBuffer stringBuffer = new StringBuffer();
			        String line = null;
			        while((line = reader.readLine())!=null){
			          stringBuffer.append(line).append("\n");
			        }
			        reader.close();
					doc.add(new TextField("contents", stringBuffer.toString(), TextField.Store.YES));
		

			}catch (Exception e) {
	            
				System.out.println("something wrong with indexing content of the files");
	        }    
			
	          
	        
			writer.addDocument(doc);
			
		}
	
		
		
   
		  
    
  }



