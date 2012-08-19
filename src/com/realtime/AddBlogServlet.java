package com.realtime;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author nieyong
 * @time 2012-8-17
 * @version 1.0
 */
@WebServlet("/addBlog")
public class AddBlogServlet extends HttpServlet {{

	protected void doPost(HttpServletRequest request,
			   HttpServletResponse response) throws ServletException, IOException {
			  MicBlog blog = new MicBlog();

			  blog.setAuthor("发布者");
			  blog.setId(System.currentTimeMillis());
			  blog.setContent(iso2UTF8(request.getParameter("content")));
			  blog.setPubDate(new Date());

			  // 放到博文队列里面去
			  NewBlogListener.BLOG_QUEUE.add(blog);

			  request.setAttribute("message", "博文发布成功!");

			  request.getRequestDispatcher("/WEB-INF/pages/write.jsp").forward(
			    request, response);
			 }
			 
			 private static String iso2UTF8(String str){
			  try {
			   return new String(str.getBytes("ISO-8859-1"), "UTF-8");
			  } catch (UnsupportedEncodingException e) {
			   e.printStackTrace();
			  }
			  return null;
			 }
}
