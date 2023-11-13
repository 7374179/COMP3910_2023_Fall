package com.corejsf;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Named;

@Named
@ConversationScoped
public class SupplierManager implements Serializable {
	private static final long serialVersionUID = 1L;
	/** dataSource for connection pool on JBoss AS 7 or higher. */
	@Resource(mappedName = "java:jboss/datasources/inventory")
	private DataSource dataSource;

	/**
	 * Find Supplier record from database.
	 * 
	 * @param id primary key of supplier record.
	 * @return the Supplier record with key = id, null if not found.
	 */
	public Supplier find(String name) {
    	System.out.println(name);

		Connection connection = null;
		Statement stmt = null;
		try {
			try {
				connection = dataSource.getConnection();
				try {
					stmt = connection.createStatement();
					ResultSet result = stmt.executeQuery("SELECT * FROM Suppliers where SupplierName = '" + name + "'");

					if (result.next()) {
						System.out.println(result.getInt("SupplierID"));
						return new Supplier(result.getInt("SupplierID"), result.getString("SupplierName"),
								result.getString("ContactName"), result.getString("ContactTitle"),
								result.getString("Address"), result.getString("City"), result.getString("PostalCode"),
								result.getString("StateOrProvince"), result.getString("Country"),
								result.getString("PhoneNumber"), result.getString("FaxNumber"),
								result.getString("PaymentTerms"), result.getString("EmailAddress"),
								result.getString("Notes"));
					} else {
						return null;
					}
				} finally {
					if (stmt != null) {
						stmt.close();
					}
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException ex) {
			System.out.println("Error in find " + name);
			ex.printStackTrace();
			return null;
		}
	}


	/**
	 * Persist Supplier record into database. id must be unique.
	 * 
	 * @param supplier the record to be persisted.
	 */
	public void persist(Supplier supplier) {
		// order of fields in INSERT statement
		final int supplierID = 1;
		final int supplierName = 2;
		final int contactName = 3;
		final int contactTitle = 4;
		final int address = 5;
		final int city = 6;
		final int postalCode = 7;
		final int stateOrProvince = 8;
		final int country = 9;
		final int phoneNumber = 10;
		final int faxNumber = 11;
		final int paymentTerms = 12;
		final int emailAddress = 13;
		final int notes = 14;
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			try {
				connection = dataSource.getConnection();
				try {
					stmt = connection.prepareStatement(
							"INSERT INTO Suppliers " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					stmt.setInt(supplierID, supplier.getId());
					stmt.setString(supplierName, supplier.getName());
					stmt.setString(contactName, supplier.getContactName());
					stmt.setString(contactTitle, supplier.getContactTitle());
					stmt.setString(address, supplier.getAddress());
					stmt.setString(city, supplier.getCity());
					stmt.setString(postalCode, supplier.getPostalCode());
					stmt.setString(stateOrProvince, supplier.getStateOrProvince());
					stmt.setString(country, supplier.getCountry());
					stmt.setString(phoneNumber, supplier.getPhoneNumber());
					stmt.setString(faxNumber, supplier.getFaxNumber());
					stmt.setString(paymentTerms, supplier.getPaymentTerms());
					stmt.setString(emailAddress, supplier.getEmailAddress());
					stmt.setString(notes, supplier.getNotes());
					stmt.executeUpdate();
				} finally {
					if (stmt != null) {
						stmt.close();
					}
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException ex) {
			System.out.println("Error in persist " + supplier);
			ex.printStackTrace();
		}
	}

	/**
	 * merge Supplier record fields into existing database record.
	 * 
	 * @param supplier the record to be merged.
	 */
	public void merge(Supplier supplier) {
		// order of fields in UPDATE statement
		final int supplierName = 1;
		final int contactName = 2;
		final int contactTitle = 3;
		final int address = 4;
		final int city = 5;
		final int postalCode = 6;
		final int stateOrProvince = 7;
		final int country = 8;
		final int phoneNumber = 9;
		final int faxNumber = 10;
		final int paymentTerms = 11;
		final int emailAddress = 12;
		final int notes = 13;
		final int supplierID = 14;
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			try {
				connection = dataSource.getConnection();
				try {
					stmt = connection.prepareStatement("UPDATE Suppliers " + "SET SupplierName = ?, ContactName = ?, "
							+ "ContactTitle = ?, Address = ?, City = ?, " + "PostalCode = ?, StateOrProvince = ?, "
							+ "Country = ?, PhoneNumber = ?, FaxNumber = ?, " + "PaymentTerms = ?, EmailAddress = ?, "
							+ "Notes = ? " + "WHERE SupplierID =  ?");
					stmt.setString(supplierName, supplier.getName());
					stmt.setString(contactName, supplier.getContactName());
					stmt.setString(contactTitle, supplier.getContactTitle());
					stmt.setString(address, supplier.getAddress());
					stmt.setString(city, supplier.getCity());
					stmt.setString(postalCode, supplier.getPostalCode());
					stmt.setString(stateOrProvince, supplier.getStateOrProvince());
					stmt.setString(country, supplier.getCountry());
					stmt.setString(phoneNumber, supplier.getPhoneNumber());
					stmt.setString(faxNumber, supplier.getFaxNumber());
					stmt.setString(paymentTerms, supplier.getPaymentTerms());
					stmt.setString(emailAddress, supplier.getEmailAddress());
					stmt.setString(notes, supplier.getNotes());
					stmt.setInt(supplierID, supplier.getId());
					stmt.executeUpdate();
				} finally {
					if (stmt != null) {
						stmt.close();
					}
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException ex) {
			System.out.println("Error in merge " + supplier);
			ex.printStackTrace();
		}
	}

	public void remove(Supplier supplier) {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			connection = dataSource.getConnection();

			stmt = connection.prepareStatement("DELETE FROM productsuppliers WHERE SupplierID =  ?");
			stmt.setInt(1, supplier.getId());
			stmt.executeUpdate();
			stmt.close();

			stmt = connection.prepareStatement("DELETE FROM suppliers WHERE SupplierID =  ?");
			stmt.setInt(1, supplier.getId());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Error in remove " + supplier);
			ex.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

//	public Supplier[] getBySupplier(int supplierID) {
//		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
//		Connection connection = null;
//		PreparedStatement stmt = null;
//		try {
//			try {
//				connection = dataSource.getConnection();
//				try {
//					stmt = connection
//							.prepareStatement("SELECT * FROM Suppliers WHERE SupplierID = ? ORDER BY SupplierID");
//
////                            "SELECT * FROM Suppliers ORDER BY SupplierID");
//					stmt.setInt(1, supplierID);
//					ResultSet result = stmt.executeQuery();
//					while (result.next()) {
//						suppliers.add(new Supplier(result.getInt("SupplierID"), result.getString("SupplierName"),
//								result.getString("ContactName"), result.getString("ContactTitle"),
//								result.getString("Address"), result.getString("City"), result.getString("PostalCode"),
//								result.getString("StateOrProvince"), result.getString("Country"),
//								result.getString("PhoneNumber"), result.getString("FaxNumber"),
//								result.getString("paymentTerms"), result.getString("EmailAddress"),
//								result.getString("Notes")));
//					}
//				} finally {
//					if (stmt != null) {
//						stmt.close();
//					}
//				}
//			} finally {
//				if (connection != null) {
//					connection.close();
//				}
//			}
//		} catch (SQLException ex) {
//			System.out.println("Error in getAll");
//			ex.printStackTrace();
//			return null;
//		}
//
//		Supplier[] suparray = new Supplier[suppliers.size()];
//		return suppliers.toArray(suparray);
//	}

	/**
	 * Return Suppliers table as array of Supplier.
	 * 
	 * @return Supplier[] of all records in Suppliers table
	 */
	public Supplier[] getAll() {
		ArrayList<Supplier> suppliers = new ArrayList<Supplier>();
		Connection connection = null;
		Statement stmt = null;
		try {
			try {
				connection = dataSource.getConnection();
				try {
					stmt = connection.createStatement();
					ResultSet result = stmt.executeQuery("SELECT * FROM Suppliers ORDER BY SupplierID");
					while (result.next()) {
						suppliers.add(new Supplier(result.getInt("SupplierID"), result.getString("SupplierName"),
								result.getString("ContactName"), result.getString("ContactTitle"),
								result.getString("Address"), result.getString("City"), result.getString("PostalCode"),
								result.getString("StateOrProvince"), result.getString("Country"),
								result.getString("PhoneNumber"), result.getString("FaxNumber"),
								result.getString("paymentTerms"), result.getString("EmailAddress"),
								result.getString("Notes")));
					}
				} finally {
					if (stmt != null) {
						stmt.close();
					}
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException ex) {
			System.out.println("Error in getAll");
			ex.printStackTrace();
			return null;
		}

		Supplier[] suparray = new Supplier[suppliers.size()];
		return suppliers.toArray(suparray);
	}

	/**
	 * Add new Supplier record into the database.
	 * 
	 * @param supplier the record to be added.
	 */
	public void add(Supplier supplier) {
		Connection connection = null;
		PreparedStatement stmt = null;
		try {
			try {
				connection = dataSource.getConnection();
				try {
					stmt = connection.prepareStatement("INSERT INTO Suppliers "
							+ "(SupplierName, ContactName, ContactTitle, Address, City, PostalCode, "
							+ "StateOrProvince, Country, PhoneNumber, FaxNumber, PaymentTerms, EmailAddress, Notes) "
							+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					stmt.setString(1, supplier.getName());
					stmt.setString(2, supplier.getContactName());
					stmt.setString(3, supplier.getContactTitle());
					stmt.setString(4, supplier.getAddress());
					stmt.setString(5, supplier.getCity());
					stmt.setString(6, supplier.getPostalCode());
					stmt.setString(7, supplier.getStateOrProvince());
					stmt.setString(8, supplier.getCountry());
					stmt.setString(9, supplier.getPhoneNumber());
					stmt.setString(10, supplier.getFaxNumber());
					stmt.setString(11, supplier.getPaymentTerms());
					stmt.setString(12, supplier.getEmailAddress());
					stmt.setString(13, supplier.getNotes());
					stmt.executeUpdate();
				} finally {
					if (stmt != null) {
						stmt.close();
					}
				}
			} finally {
				if (connection != null) {
					connection.close();
				}
			}
		} catch (SQLException ex) {
			System.out.println("Error in add " + supplier);
			ex.printStackTrace();
		}
	}

	public List<EditableSupplier> getAllAsEditable() {
		Supplier[] suppliers = getAll();
		List<EditableSupplier> editableSuppliers = new ArrayList<>();
		for (Supplier s : suppliers) {
			editableSuppliers.add(new EditableSupplier(s));
		}
		return editableSuppliers;
	}
	
	public int getMaxSupplierId() {
	    int maxId = 0;
	    Connection connection = null;
	    Statement stmt = null;
	    ResultSet rs = null;
	    try {
	        connection = dataSource.getConnection();
	        stmt = connection.createStatement();
	        rs = stmt.executeQuery("SELECT MAX(SupplierID) FROM Suppliers");
	        if (rs.next()) {
	            maxId = rs.getInt(1);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (stmt != null) stmt.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return maxId;
	}
	
	/**
	 * Find Supplier records by name from database.
	 *
	 * @param name part of the supplier name.
	 * @return list of Supplier records with names that contain the input, empty list if not found.
	 */
	public List<Supplier> findByName(String name) {
	    List<Supplier> suppliers = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement stmt = null;
	    try {
	    	System.out.println(name);
	        connection = dataSource.getConnection();
	        stmt = connection.prepareStatement("SELECT * FROM Suppliers WHERE SupplierName LIKE ?");
	        stmt.setString(1, "%" + name + "%");
	        ResultSet result = stmt.executeQuery();
	        while (result.next()) {
	            suppliers.add(new Supplier(result.getInt("SupplierID"), result.getString("SupplierName"),
	                    result.getString("ContactName"), result.getString("ContactTitle"),
	                    result.getString("Address"), result.getString("City"), result.getString("PostalCode"),
	                    result.getString("StateOrProvince"), result.getString("Country"),
	                    result.getString("PhoneNumber"), result.getString("FaxNumber"),
	                    result.getString("PaymentTerms"), result.getString("EmailAddress"),
	                    result.getString("Notes")));
	        }
	    } catch (SQLException ex) {
	        System.out.println("Error in findByName " + name);
	        ex.printStackTrace();
	    } finally {
	        try {
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (connection != null) {
	                connection.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return suppliers;
	}	

}
