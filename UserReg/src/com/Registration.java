package com;

import java.sql.*;

public class Registration 
{

			//CONNECTION
			public Connection connect()
			{
					Connection con = null;

					try
					{
							Class.forName("com.mysql.jdbc.Driver");
							con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users_db",	"root", "");
			
					}
					catch(Exception e)
					{
							e.printStackTrace();
					}

					return con;
			}
			
			
			
			
			//READ
			public String readUser()
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for reading.";
							}
							
							// Prepare the HTML table to be displayed
							output = "<table  class='table table-dark table-striped'><tr><th>User Name</th>"
									+"<th>Name</th><th>Password</th>"
									+ "<th>Edit</th><th>Delete</th></tr>";

							String query = "select * from users";
							Statement stmt = con.createStatement();
							ResultSet rs = stmt.executeQuery(query);

							// iterate through the rows in the result set
							while (rs.next())
							{
									String userid = Integer.toString(rs.getInt("userid"));
									String username = rs.getString("username");
									String name = rs.getString("name");
									String password = rs.getString("password");


									// Add a row into the HTML table
									output += "<tr><td>" + username + "</td>";
									output += "<td>" + name + "</td>";
									output += "<td>" + password + "</td>"; 
				

									// buttons
									output += "<td><input name='btnUpdate' type='button' value='Edit' class='btnUpdate btn btn-secondary' data-userid='" + userid + "'></td>"
											+"<td><input name='btnRemove' type='button' value='Delete' class='btnRemove btn btn-danger' data-userid='" + userid + "'>" + "</td></tr>";
							}

							con.close();

							// Complete the HTML table
							output += "</table>";
					}
					catch (Exception e)
					{
							output = "Error while reading the users.";
							System.err.println(e.getMessage());
					}
					
					return output;
			}
			
			
			
			

			//INSERT
			public String insertUser(String username, String name, String password)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for inserting";
							}

							// create a prepared statement
							String query = " insert into users (`userid`,`username`,`name`,`password`) values (?, ?, ?, ?)";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setInt(1, 0);
							preparedStmt.setString(2, username);
							preparedStmt.setString(3, name);
							preparedStmt.setString(4, password);
							

							//execute the statement
							preparedStmt.execute();
							con.close();

							String newUser = readUser();
							output = "{\"status\":\"success\", \"data\": \"" + newUser + "\"}";
			
					}
					catch (Exception e)
					{
								output = "{\"status\":\"error\", \"data\":\"Error while inserting the User.\"}";
								System.err.println(e.getMessage());
					}
					
					return output;
			}
			

			
			//UPDATE
			public String updateUser(String userid,String username, String name, String password)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for updating";
							}

							// create a prepared statement
							String query = "UPDATE users SET username=?, name=?, password=?WHERE userid=?";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setString(1, username);
							preparedStmt.setString(2, name);
							preparedStmt.setString(3, password);
							preparedStmt.setInt(4, Integer.parseInt(userid));

							//execute the statement
							preparedStmt.executeUpdate();
							con.close();

							String newUsers = readUser();
							output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
			
			
					}
					catch (Exception e)
					{
							output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
							System.err.println(e.getMessage());
					}
					
					return output;
			}
			
			

			//DELETE
			public String deleteUser(String userid)
			{
					String output = "";
					
					try
					{
							Connection con = connect();
							
							if (con == null)
							{
									return "Error while connecting to the database for deleting";
							}

							// create a prepared statement
							String query = "DELETE from users where userid=?";
							
							PreparedStatement preparedStmt = con.prepareStatement(query);

							// binding values
							preparedStmt.setInt(1, Integer.parseInt(userid));

							//execute the statement
							preparedStmt.executeUpdate();
							con.close();

							String newUsers = readUser();
							output = "{\"status\":\"success\", \"data\": \"" + newUsers + "\"}";
					}
					catch (Exception e)
					{
						output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
						System.err.println(e.getMessage());
					}
					
					return output;
			}

	
}
