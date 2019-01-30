
package jbr.springmvc.dao;
import java.io.IOException;

import org.apache.lucene.queryparser.classic.ParseException;

import jbr.springmvc.model.Posts;
public interface UserDao {
 // void register(User user);
	public String[] fetch_recommendation(String post_ip) throws IOException, ParseException;
}